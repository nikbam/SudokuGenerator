package org.paidaki.sudoku.grids;

import org.paidaki.sudoku.model.Sudoku;

public class Sudoku6x6_2x3 extends Sudoku {

    private static final int SIZE = 6;
    private static final int BLOCK_ROWS = 2;
    private static final int BLOCK_COLS = 3;

    public Sudoku6x6_2x3() {
        super(SIZE, BLOCK_ROWS, BLOCK_COLS);
    }

    @Override
    public Sudoku clone() {
        Sudoku sudokuClone = new Sudoku6x6_2x3();

        cloneSudoku(sudokuClone);
        return sudokuClone;
    }
}
