package org.paidaki.sudoku.solver;

import org.paidaki.sudoku.model.*;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.strategies.Strategy;
import org.paidaki.sudoku.strategies.StrategyFactory;

import java.util.ArrayList;

public class Solver {

    private static final Strategy STRATEGIES[] = {
            StrategyFactory.getStrategy(StrategyFactory.FULL_HOUSE),
            StrategyFactory.getStrategy(StrategyFactory.HIDDEN_SINGLE),
            StrategyFactory.getStrategy(StrategyFactory.NAKED_SINGLE),
            StrategyFactory.getStrategy(StrategyFactory.LOCKED_CANDIDATES_1),
            StrategyFactory.getStrategy(StrategyFactory.LOCKED_CANDIDATES_2),
            StrategyFactory.getStrategy(StrategyFactory.HIDDEN_PAIR),
            StrategyFactory.getStrategy(StrategyFactory.HIDDEN_TRIPLE),
            StrategyFactory.getStrategy(StrategyFactory.HIDDEN_QUADRUPLE)
            //TODO Add more strategies
    };

    public int uniqueSearchCount(Sudoku originalSudoku) {
        Sudoku sudoku = originalSudoku.clone();
        ArrayList<Cell> emptyCells = new ArrayList<>();
        ArrayList<ArrayList<SudokuValue>> initialAvailable = new ArrayList<>();

        for (Cell c : sudoku.getCells()) {
            if (c.isEmpty()) {
                emptyCells.add(c);
                initialAvailable.add(new ArrayList<>(c.getAvailable()));
            }
        }
        boolean done = false;
        int currentCell = 0;
        int solutions = 0;
        int searchCount = 0;

        while (!done) {
            Cell cell = emptyCells.get(currentCell);
            ArrayList<SudokuValue> initial = initialAvailable.get(currentCell);
            ArrayList<SudokuValue> available = cell.getAvailable();

            if (!available.isEmpty()) {
                SudokuValue value = available.get(0);

                if (sudoku.isCellValueValid(cell, value)) {
                    cell.setValue(value);
                    currentCell++;
                    searchCount++;

                    if (currentCell > emptyCells.size() - 1) {
                        solutions++;
                        currentCell--;
                        done = solutions > 1;
                    }
                }
                available.remove(value);
            } else {
                cell.setValue(SudokuValue.EMPTY);
                available.addAll(initial);

                currentCell--;
                done = currentCell < 0;
            }
        }
        return (solutions == 1) ? searchCount : -1;
    }

    public int countGivens(Sudoku sudoku) {
        int givens = 0;

        for (Cell c : sudoku.getCells()) {
            if (!c.isEmpty()) {
                givens++;
            }
        }
        return givens;
    }

    public int findMinRegionGivens(Sudoku sudoku) {
        int minRegionGivens = Integer.MAX_VALUE;

        for (Row r : sudoku.getRows()) {
            int sum = 0;

            for (Cell c : r.getCells()) {
                if (!c.isEmpty()) {
                    sum++;
                }
            }
            minRegionGivens = (sum < minRegionGivens) ? sum : minRegionGivens;
        }
        for (Column col : sudoku.getColumns()) {
            int sum = 0;

            for (Cell c : col.getCells()) {
                if (!c.isEmpty()) {
                    sum++;
                }
            }
            minRegionGivens = (sum < minRegionGivens) ? sum : minRegionGivens;
        }
        for (Block b : sudoku.getBlocks()) {
            int sum = 0;

            for (Cell c : b.getCells()) {
                if (!c.isEmpty()) {
                    sum++;
                }
            }
            minRegionGivens = (sum < minRegionGivens) ? sum : minRegionGivens;
        }
        return (minRegionGivens == Integer.MAX_VALUE) ? -1 : minRegionGivens;
    }

    public Strategy findHardestStrategy(Sudoku originalSudoku) {
        Sudoku sudoku = originalSudoku.clone();
        Strategy hardestStrategy = null;
        int currentStrategyIndex = 0;

        while (currentStrategyIndex < STRATEGIES.length) {
            Strategy strategy = STRATEGIES[currentStrategyIndex];

            if (strategy.applyStrategy(sudoku)) {
                hardestStrategy = (hardestStrategy == null || strategy.compareTo(hardestStrategy) >= 0) ? strategy : hardestStrategy;
                currentStrategyIndex = 0;
            } else {
                currentStrategyIndex++;
            }
        }
        return hardestStrategy;
    }
}
