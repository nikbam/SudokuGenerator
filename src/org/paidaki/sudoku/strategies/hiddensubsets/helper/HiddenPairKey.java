package org.paidaki.sudoku.strategies.hiddensubsets.helper;

import org.paidaki.sudoku.model.Cell;
import org.paidaki.sudoku.model.enums.SudokuValue;

import java.util.ArrayList;

public class HiddenPairKey {

    public ArrayList<SudokuValue> values;
    public final ArrayList<Cell> cells;

    public HiddenPairKey(ArrayList<Cell> cells) {
        values = new ArrayList<>();
        this.cells = cells;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HiddenPairKey)) {
            return false;
        }
        HiddenPairKey key = (HiddenPairKey) obj;

        for (Cell cell : cells) {
            if (!key.cells.contains(cell)) {
                return false;
            }
        }
        return cells.size() == key.cells.size();
    }

    @Override
    public int hashCode() {
        final int prime = 1;    // Prime = 1 because we want the key to be the same regardless the order of the cells
        int hash = 31;

        for (Cell cell : cells) {
            hash = prime * hash + ((cell == null) ? 0 : cell.hashCode());
        }
        return cells.isEmpty() ? -1 : hash;     // To distinguish between empty cells and all null cells
    }
}
