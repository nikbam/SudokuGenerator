package org.paidaki.sudoku.strategies.singles;

import org.paidaki.sudoku.model.*;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.strategies.Strategy;
import org.paidaki.sudoku.strategies.StrategyFactory;

import java.util.ArrayList;
import java.util.Map;

public class FullHouse extends Strategy {

    private static final String NAME = "Full House";
    private static final String SHORT_NAME = "FH";
    private static final int DIFFICULTY_INDEX = 0;

    public FullHouse() {
        super(StrategyFactory.FULL_HOUSE, NAME, SHORT_NAME, DIFFICULTY_INDEX);
    }

    @Override
    public boolean applyStrategy(Sudoku sudoku) {
        for (Row row : sudoku.getRows()) {
            if (checkRegion(row)) {
                return true;
            }
        }
        for (Column column : sudoku.getColumns()) {
            if (checkRegion(column)) {
                return true;
            }
        }
        for (Block block : sudoku.getBlocks()) {
            if (checkRegion(block)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRegion(Region region) {
        int fixed = 0;
        Cell emptyCell = null;
        SudokuValue newValue = null;

        for (Map.Entry<SudokuValue, ArrayList<Cell>> entry : region.getAvailableCount().entrySet()) {
            SudokuValue key = entry.getKey();
            ArrayList<Cell> cellList = entry.getValue();

            if (cellList.isEmpty()) {
                fixed++;
            } else {
                emptyCell = cellList.get(0);
                newValue = key;
            }
        }
        if (emptyCell != null && fixed == region.getSize() - 1) {
            emptyCell.setNewValue(newValue);
            return true;
        }
        return false;
    }
}
