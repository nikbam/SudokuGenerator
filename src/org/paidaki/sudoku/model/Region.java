package org.paidaki.sudoku.model;

import org.paidaki.sudoku.model.enums.SudokuValue;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Region {

    private int index, size;
    private Cell[] cells;
    private HashMap<SudokuValue, ArrayList<Cell>> availableCount;

    public Region(int index, int size, int sudokuSize) {
        this.index = index;
        this.size = size;

        cells = new Cell[size];
        availableCount = new HashMap<>();

        for (int i = 1; i <= sudokuSize; i++) {
            availableCount.putIfAbsent(SudokuValue.getValueByIndex(i), new ArrayList<>());
        }
    }

    public void addCellToAvailableCount(Cell cell) {
        for (ArrayList<Cell> cellList : availableCount.values()) {
            cellList.add(cell);
        }
    }

    public void clearAvailableCount() {
        availableCount.values().forEach(ArrayList::clear);
    }

    public boolean contains(SudokuValue value) {
        for (Cell c : cells) {
            if (c.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return size;
    }

    public Cell[] getCells() {
        return cells;
    }

    public HashMap<SudokuValue, ArrayList<Cell>> getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(HashMap<SudokuValue, ArrayList<Cell>> availableCount) {
        this.availableCount = availableCount;
    }
}
