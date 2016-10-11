package org.paidaki.sudoku.solver;

import org.paidaki.sudoku.model.DifficultyMetrics;
import org.paidaki.sudoku.model.enums.Difficulty;
import org.paidaki.sudoku.strategies.Strategy;

public class DifficultyEvaluator {

    private DifficultyEvaluator() {
    }

    public static Difficulty evaluate(DifficultyMetrics metrics) {
        double score = evaluateGivens(metrics.getGivens()) +
                evaluateMinRegionGivens(metrics.getMinRegionGivens()) +
                evaluateSearchCount(metrics.getSearchCount()) +
                evaluateHardestStrategy(metrics.getHardestStrategy());

        long RoundedScore = Math.round(score);
        Difficulty difficulty = Difficulty.INVALID;

        if (RoundedScore == 1) {
            difficulty = Difficulty.VERY_EASY;
        } else if (RoundedScore == 2) {
            difficulty = Difficulty.EASY;
        } else if (RoundedScore == 3) {
            difficulty = Difficulty.MEDIUM;
        } else if (RoundedScore == 4) {
            difficulty = Difficulty.HARD;
        } else if (RoundedScore == 5) {
            difficulty = Difficulty.VERY_HARD;
        }
        return difficulty;
    }

    private static double evaluateGivens(int givens) {
        int score = -1;
        double weight = 0.2;

        if (givens > 28) {
            score = 1;
        } else if (givens > 25) {
            score = 2;
        } else if (givens > 22) {
            score = 3;
        } else if (givens > 19) {
            score = 4;
        } else if (givens > 16) {
            score = 5;
        }
        return score * weight;
    }

    private static double evaluateMinRegionGivens(int minRegionGivens) {
        int score = -1;
        double weight = 0.2;

        if (minRegionGivens > 4) {
            score = 1;
        } else if (minRegionGivens == 4) {
            score = 2;
        } else if (minRegionGivens == 3) {
            score = 3;
        } else if (minRegionGivens == 2) {
            score = 4;
        } else if (minRegionGivens == 0) {
            score = 5;
        }
        return score * weight;
    }

    private static double evaluateSearchCount(int searchCount) {
        int score = -1;
        double weight = 0.2;

        if (searchCount < 100) {
            score = 1;
        } else if (searchCount < 1000) {
            score = 2;
        } else if (searchCount < 10000) {
            score = 3;
        } else if (searchCount < 100000) {
            score = 4;
        } else if (searchCount >= 100000) {
            score = 5;
        }
        return score * weight;
    }

    private static double evaluateHardestStrategy(Strategy hardestStrategy) {
        int score = -1;
        double weight = 0.4;

        //TODO
        return score * weight;
    }
}
