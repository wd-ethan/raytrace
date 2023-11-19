package scene.primitives;

public class Resolution {

    public Resolution(final int columns, final int rows) {
        mColumns = columns;
        mRows = rows;
    }

    public Resolution(){
        this(500, 500);
    }

    final int mColumns;
    final int mRows;
}
