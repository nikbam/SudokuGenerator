package org.paidaki.sudoku.strategies.singles;

import org.paidaki.sudoku.model.*;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.strategies.Strategy;
import org.paidaki.sudoku.strategies.StrategyFactory;

import java.util.ArrayList;
import java.util.Map;

public class HiddenSingle extends Strategy {

    private static final String NAME = "Hidden Single";
    private static final String SHORT_NAME = "HS";
    private static final int DIFFICULTY_INDEX = 1;

    public HiddenSingle() {
        super(StrategyFactory.HIDDEN_SINGLE, NAME, SHORT_NAME, DIFFICULTY_INDEX);
    }

    @Override
    public boolean applyStrategy(Sudoku sudoku) {
        for (Row r : sudoku.getRows()) {
            if (checkRegion(r)) {
                return true;
            }
        }
        for (Column c : sudoku.getColumns()) {
            if (checkRegion(c)) {
                return true;
            }
        }
        for (Block b : sudoku.getBlocks()) {
            if (checkRegion(b)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRegion(Region region) {
        for (Map.Entry<SudokuValue, ArrayList<Cell>> entry : region.getAvailableCount().entrySet()) {
            SudokuValue key = entry.getKey();
            ArrayList<Cell> cellList = entry.getValue();

            if (cellList.size() == 1) {
                Cell emptyCell = cellList.get(0);
                emptyCell.setNewValue(key);
                return true;
            }
        }
        return false;
    }
}
