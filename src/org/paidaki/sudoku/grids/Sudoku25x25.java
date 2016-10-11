package org.paidaki.sudoku.grids;

import org.paidaki.sudoku.model.Sudoku;

public class Sudoku25x25 extends Sudoku {

    private static final int SIZE = 25;
    private static final int BLOCK_ROWS = 5;
    private static final int BLOCK_COLS = 5;

    public Sudoku25x25() {
        super(SIZE, BLOCK_ROWS, BLOCK_COLS);
    }

    @Override
    public Sudoku clone() {
        Sudoku sudokuClone = new Sudoku25x25();

        cloneSudoku(sudokuClone);
        return sudokuClone;
    }
}
