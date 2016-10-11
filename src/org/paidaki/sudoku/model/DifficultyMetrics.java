package org.paidaki.sudoku.model;

import org.paidaki.sudoku.model.enums.Difficulty;
import org.paidaki.sudoku.solver.DifficultyEvaluator;
import org.paidaki.sudoku.strategies.Strategy;

public class DifficultyMetrics {

    public static final int INVALID = -1;

    private int givens;
    private int minRegionGivens;
    private int searchCount;
    private Strategy hardestStrategy;
    private Difficulty difficulty;

    public DifficultyMetrics() {
        givens = INVALID;
        minRegionGivens = INVALID;
        searchCount = INVALID;
        hardestStrategy = null;
        difficulty = Difficulty.INVALID;
    }

    public DifficultyMetrics(DifficultyMetrics original) {
        givens = original.getGivens();
        minRegionGivens = original.getMinRegionGivens();
        searchCount = original.getSearchCount();
        hardestStrategy = original.getHardestStrategy();
        updateDifficulty();
    }

    public boolean isValid() {
        return givens != INVALID && minRegionGivens != INVALID && searchCount != INVALID && hardestStrategy != null;
    }

    public void updateDifficulty() {
        if (isValid()) {
            difficulty = DifficultyEvaluator.evaluate(this);
        } else {
            difficulty = Difficulty.INVALID;
        }
    }

    public int getGivens() {
        return givens;
    }

    public void setGivens(int givens) {
        this.givens = givens;
    }

    public int getMinRegionGivens() {
        return minRegionGivens;
    }

    public void setMinRegionGivens(int minRegionGivens) {
        this.minRegionGivens = minRegionGivens;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    public Strategy getHardestStrategy() {
        return hardestStrategy;
    }

    public void setHardestStrategy(Strategy hardestStrategy) {
        this.hardestStrategy = hardestStrategy;
    }
}
