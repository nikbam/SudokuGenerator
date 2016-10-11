package org.paidaki.sudoku.grids;

import org.paidaki.sudoku.model.Sudoku;

public class Sudoku16x16 extends Sudoku {

    private static final int SIZE = 16;
    private static final int BLOCK_ROWS = 4;
    private static final int BLOCK_COLS = 4;

    public Sudoku16x16() {
        super(SIZE, BLOCK_ROWS, BLOCK_COLS);
    }

    @Override
    public Sudoku clone() {
        Sudoku sudokuClone = new Sudoku16x16();

        cloneSudoku(sudokuClone);
        return sudokuClone;
    }
}
