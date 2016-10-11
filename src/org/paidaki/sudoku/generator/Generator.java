package org.paidaki.sudoku.generator;

import org.paidaki.sudoku.model.Cell;
import org.paidaki.sudoku.model.DifficultyMetrics;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.model.Sudoku;
import org.paidaki.sudoku.solver.Solver;

import java.util.ArrayList;
import java.util.Collections;

import static org.paidaki.sudoku.SudokuManager.RAND;

public class Generator {

    private Solver solver = new Solver();

    public void generateSudoku(Sudoku sudoku) {
        generateTerminalSudoku(sudoku);
        sudoku.setSolution(sudoku.clone());

        ArrayList<Integer> cellIndexes = new ArrayList<>();
        int searchCount = DifficultyMetrics.INVALID;

        for (int i = 0; i < sudoku.getFullSize(); i++) {
            cellIndexes.add(i);
        }
        Collections.shuffle(cellIndexes, RAND);

        while (!cellIndexes.isEmpty()) {
            Cell cell = sudoku.getCells()[cellIndexes.remove(0)];

            SudokuValue oldValue = cell.getValue();
            cell.removeValue();

            int counter = solver.uniqueSearchCount(sudoku);
            if (counter == -1) {
                cell.setNewValue(oldValue);
            } else {
                searchCount = (counter > searchCount) ? counter : searchCount;
            }
        }
        sudoku.getDifficultyMetrics().setSearchCount(searchCount);
        sudoku.getDifficultyMetrics().setGivens(solver.countGivens(sudoku));
        sudoku.getDifficultyMetrics().setMinRegionGivens(solver.findMinRegionGivens(sudoku));
        sudoku.getDifficultyMetrics().setHardestStrategy(solver.findHardestStrategy(sudoku));
        sudoku.getDifficultyMetrics().updateDifficulty();
    }

    private void generateTerminalSudoku(Sudoku sudoku) {
        int currentCell = 0;

        while(currentCell < sudoku.getFullSize()) {
            Cell cell = sudoku.getCells()[currentCell];
            ArrayList<SudokuValue> available = cell.getAvailable();

            if (!available.isEmpty()) {
                int index = RAND.nextInt(available.size());
                SudokuValue value = available.get(index);

                if (sudoku.isCellValueValid(cell, value)) {
                    cell.setValue(value);
                    currentCell++;
                }
                available.remove(index);
            } else {
                cell.setValue(SudokuValue.EMPTY);
                cell.resetAvailable();

                currentCell--;
            }
        }
        sudoku.emptyAvailable();
    }
}
