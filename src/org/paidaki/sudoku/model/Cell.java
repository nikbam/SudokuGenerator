package org.paidaki.sudoku.model;

import org.paidaki.sudoku.model.enums.SudokuValue;

import java.util.ArrayList;

public class Cell {

    private int sudokuSize;
    private Row row, blockRow;
    private Column column, blockColumn;
    private Block block;
    private SudokuValue value;
    private ArrayList<SudokuValue> available;

    public Cell(Row row, Column column, Block block) {
        this.sudokuSize = row.getSize();
        this.row = row;
        this.column = column;
        this.block = block;
        this.value = SudokuValue.EMPTY;
        this.available = SudokuValue.getValuesList(sudokuSize);

        blockRow = block.getRows()[row.getIndex() % block.getNumOfRows()];
        blockColumn = block.getColumns()[column.getIndex() % block.getNumOfColumns()];

        this.row.addCellToAvailableCount(this);
        this.column.addCellToAvailableCount(this);
        this.block.addCellToAvailableCount(this);
        blockRow.addCellToAvailableCount(this);
        blockColumn.addCellToAvailableCount(this);
    }

    public boolean isEmpty() {
        return value == SudokuValue.EMPTY;
    }

    public void resetAvailable() {
        for (int i = 1; i <= sudokuSize; i++) {
            available.add(SudokuValue.getValueByIndex(i));
        }
    }

    private void fixAddAvailable() {
        for (int i = 1; i <= sudokuSize; i++) {
            addAvailable(SudokuValue.getValueByIndex(i));
        }
    }

    private void fixEmptyAvailable() {
        while (!available.isEmpty()) {
            removeAvailable(available.get(0));
        }
    }

    public void setValue(SudokuValue value) {
        this.value = value;
    }

    public ArrayList<SudokuValue> getAvailable() {
        return available;
    }

    public void removeValue() {
        if (value == SudokuValue.EMPTY) {
            return;
        }
        SudokuValue oldValue = value;
        value = SudokuValue.EMPTY;
        fixAddAvailable();

        for (Cell cell : row.getCells()) {
            cell.addAvailable(oldValue);
        }
        for (Cell cell : column.getCells()) {
            cell.addAvailable(oldValue);
        }
        for (Cell cell : block.getCells()) {
            cell.addAvailable(oldValue);
        }
    }

    public void setNewValue(SudokuValue newValue) {
        if (newValue == SudokuValue.EMPTY) {
            removeValue();
            return;
        }
        value = newValue;
        fixEmptyAvailable();

        for (Cell cell : row.getCells()) {
            cell.removeAvailable(value);
        }
        for (Cell cell : column.getCells()) {
            cell.removeAvailable(value);
        }
        for (Cell cell : block.getCells()) {
            cell.removeAvailable(value);
        }
    }

    public boolean isValueValid(SudokuValue value) {
        return !(row.contains(value) || column.contains(value) || block.contains(value));
    }

    public void addAvailable(SudokuValue value) {
        if (isEmpty() && !available.contains(value) && isValueValid(value)) {
            available.add(value);
            row.getAvailableCount().get(value).add(this);
            column.getAvailableCount().get(value).add(this);
            block.getAvailableCount().get(value).add(this);
            blockRow.getAvailableCount().get(value).add(this);
            blockColumn.getAvailableCount().get(value).add(this);
        }
    }

    public void removeAvailable(SudokuValue value) {
        if (available.remove(value)) {
            row.getAvailableCount().get(value).remove(this);
            column.getAvailableCount().get(value).remove(this);
            block.getAvailableCount().get(value).remove(this);
            blockRow.getAvailableCount().get(value).remove(this);
            blockColumn.getAvailableCount().get(value).remove(this);
        }
    }

    public SudokuValue getValue() {
        return value;
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    public Block getBlock() {
        return block;
    }

    public void setAvailable(ArrayList<SudokuValue> available) {
        this.available = available;
    }
}
