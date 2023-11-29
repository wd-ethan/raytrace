package com.tracer.primitives;

import com.tracer.primitives.Pixel;

import java.util.Iterator;

public class Resolution implements Iterable<Pixel> {

    public Resolution(final int columns, final int rows) {
        mColumns = columns;
        mRows = rows;
    }

    public Resolution(){
        this(500, 500);
    }

    private final int mColumns;
    private final int mRows;

    public int width() {
        return mColumns;
    }

    public int height() {
        return mRows;
    }

    @Override
    public Iterator<Pixel> iterator() {
        return new Iterator<>() {

            private int curCol = -1;
            private int curRow = -1;

            @Override
            public boolean hasNext() {
                return curRow < mRows - 1 || curCol < mColumns - 1;
            }

            @Override
            public synchronized Pixel next() {
                final int nextCol = curCol < mColumns - 1 ? curCol + 1 : 0;
                final int nextRow = nextCol == 0 ? curRow + 1 : curRow;

                final Pixel nextPixel = new Pixel(mColumns, mRows, nextCol, nextRow);

                curRow = nextRow;
                curCol = nextCol;

                return nextPixel;
            }
        };
    }
}