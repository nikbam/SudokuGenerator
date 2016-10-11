package org.paidaki.sudoku.strategies;

import org.paidaki.sudoku.strategies.hiddensubsets.HiddenPair;
import org.paidaki.sudoku.strategies.hiddensubsets.HiddenQuadruple;
import org.paidaki.sudoku.strategies.hiddensubsets.HiddenTriple;
import org.paidaki.sudoku.strategies.intersections.LockedCandidates1;
import org.paidaki.sudoku.strategies.intersections.LockedCandidates2;
import org.paidaki.sudoku.strategies.singles.FullHouse;
import org.paidaki.sudoku.strategies.singles.HiddenSingle;
import org.paidaki.sudoku.strategies.singles.NakedSingle;

public class StrategyFactory {

    public static final int FULL_HOUSE = 0;
    public static final int HIDDEN_SINGLE = 1;
    public static final int NAKED_SINGLE = 2;
    public static final int LOCKED_CANDIDATES_1 = 3;
    public static final int LOCKED_CANDIDATES_2 = 4;
    public static final int HIDDEN_PAIR = 5;
    public static final int HIDDEN_TRIPLE = 6;
    public static final int HIDDEN_QUADRUPLE = 7;

    private static final int MIN_STRATEGY_ID = 0;
    private static final int MAX_STRATEGY_ID = 7;

    private StrategyFactory() {
    }

    public static boolean validStrategyID(int strategyID) {
        return strategyID >= MIN_STRATEGY_ID && strategyID <= MAX_STRATEGY_ID;
    }

    public static Strategy getStrategy(int strategyID) {
        Strategy strategy;

        switch (strategyID) {
            case FULL_HOUSE:
                strategy = new FullHouse();
                break;
            case HIDDEN_SINGLE:
                strategy = new HiddenSingle();
                break;
            case NAKED_SINGLE:
                strategy = new NakedSingle();
                break;
            case LOCKED_CANDIDATES_1:
                strategy = new LockedCandidates1();
                break;
            case LOCKED_CANDIDATES_2:
                strategy = new LockedCandidates2();
                break;
            case HIDDEN_PAIR:
                strategy = new HiddenPair();
                break;
            case HIDDEN_TRIPLE:
                strategy = new HiddenTriple();
                break;
            case HIDDEN_QUADRUPLE:
                strategy = new HiddenQuadruple();
                break;
            default:
                strategy = null;
                break;
        }
        return strategy;
    }
}
