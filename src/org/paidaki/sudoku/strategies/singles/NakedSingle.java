package org.paidaki.sudoku.strategies.singles;

import org.paidaki.sudoku.model.Cell;
import org.paidaki.sudoku.model.Sudoku;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.strategies.Strategy;
import org.paidaki.sudoku.strategies.StrategyFactory;

public class NakedSingle extends Strategy {

    private static final String NAME = "Naked Single";
    private static final String SHORT_NAME = "NS";
    private static final int DIFFICULTY_INDEX = 2;

    public NakedSingle() {
        super(StrategyFactory.NAKED_SINGLE, NAME, SHORT_NAME, DIFFICULTY_INDEX);
    }

    @Override
    public boolean applyStrategy(Sudoku sudoku) {
        for (Cell c : sudoku.getCells()) {
            if (c.getAvailable().size() == 1) {
                SudokuValue newValue = c.getAvailable().get(0);

                c.setNewValue(newValue);
                return true;
            }
        }
        return false;
    }
}
