package org.paidaki.sudoku.model;

public class Block extends Region {

    private int numOfRows, numOfColumns;
    private Row[] rows;
    private Column[] columns;

    public Block(int index, int numOfRows, int numOfColumns, int sudokuSize) {
        super(index, numOfRows * numOfColumns, sudokuSize);

        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;

        rows = new Row[numOfRows];
        columns = new Column[numOfColumns];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Row(i, numOfColumns, getSize());
        }
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new Column(i, numOfRows, getSize());
        }
    }

    public Row[] getRows() {
        return rows;
    }

    public Column[] getColumns() {
        return columns;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }
}
