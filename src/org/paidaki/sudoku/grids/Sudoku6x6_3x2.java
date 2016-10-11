package org.paidaki.sudoku.grids;

import org.paidaki.sudoku.model.Sudoku;

public class Sudoku6x6_3x2 extends Sudoku {

    private static final int SIZE = 6;
    private static final int BLOCK_ROWS = 3;
    private static final int BLOCK_COLS = 2;

    public Sudoku6x6_3x2() {
        super(SIZE, BLOCK_ROWS, BLOCK_COLS);
    }

    @Override
    public Sudoku clone() {
        Sudoku sudokuClone = new Sudoku6x6_3x2();

        cloneSudoku(sudokuClone);
        return sudokuClone;
    }
}
