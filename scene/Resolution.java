package scene;

public class Resolution {

    Resolution(){
        this(500, 500);
    }

    Resolution(final int columns, final int rows) {
        mColumns = columns;
        mRows = rows;
    }

    final int mColumns;
    final int mRows;
}
