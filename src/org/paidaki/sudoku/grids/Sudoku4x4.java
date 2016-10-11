package org.paidaki.sudoku.grids;

import org.paidaki.sudoku.model.Sudoku;

public class Sudoku4x4 extends Sudoku {

    private static final int SIZE = 4;
    private static final int BLOCK_ROWS = 2;
    private static final int BLOCK_COLS = 2;

    public Sudoku4x4() {
        super(SIZE, BLOCK_ROWS, BLOCK_COLS);
    }

    @Override
    public Sudoku clone() {
        Sudoku sudokuClone = new Sudoku4x4();

        cloneSudoku(sudokuClone);
        return sudokuClone;
    }
}
