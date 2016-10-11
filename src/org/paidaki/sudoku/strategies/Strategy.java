package org.paidaki.sudoku.strategies;

import org.paidaki.sudoku.model.Sudoku;

public abstract class Strategy implements Comparable<Strategy> {

    private int id, difficultyIndex;
    private String name;
    private String shortName;

    public Strategy(int id, String name, String shortName, int difficultyIndex) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.difficultyIndex = difficultyIndex;
    }

    @Override
    public int compareTo(Strategy otherStrategy) {
        return Integer.compare(difficultyIndex, otherStrategy.getDifficultyIndex());
    }

    public abstract boolean applyStrategy(Sudoku sudoku);

    public int getDifficultyIndex() {
        return difficultyIndex;
    }
}
