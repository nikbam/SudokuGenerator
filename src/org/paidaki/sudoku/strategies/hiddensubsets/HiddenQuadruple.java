package org.paidaki.sudoku.strategies.hiddensubsets;

import org.paidaki.sudoku.model.Sudoku;
import org.paidaki.sudoku.strategies.Strategy;
import org.paidaki.sudoku.strategies.StrategyFactory;

public class HiddenQuadruple extends Strategy {

    private static final String NAME = "Hidden Quadruple";
    private static final String SHORT_NAME = "HQ";
    private static final int DIFFICULTY_INDEX = 7;

    public HiddenQuadruple() {
        super(StrategyFactory.HIDDEN_QUADRUPLE, NAME, SHORT_NAME, DIFFICULTY_INDEX);
    }

    @Override
    public boolean applyStrategy(Sudoku sudoku) {
        //TODO
        return false;
    }
}
