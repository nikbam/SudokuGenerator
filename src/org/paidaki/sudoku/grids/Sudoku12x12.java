package org.paidaki.sudoku.grids;

import org.paidaki.sudoku.model.Sudoku;

public class Sudoku12x12 extends Sudoku {

    private static final int SIZE = 12;
    private static final int BLOCK_ROWS = 4;
    private static final int BLOCK_COLS = 3;

    public Sudoku12x12() {
        super(SIZE, BLOCK_ROWS, BLOCK_COLS);
    }

    @Override
    public Sudoku clone() {
        Sudoku sudokuClone = new Sudoku12x12();

        cloneSudoku(sudokuClone);
        return sudokuClone;
    }
}
