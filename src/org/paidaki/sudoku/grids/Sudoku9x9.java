package org.paidaki.sudoku.grids;

import org.paidaki.sudoku.model.Sudoku;

public class Sudoku9x9 extends Sudoku {

    private static final int SIZE = 9;
    private static final int BLOCK_ROWS = 3;
    private static final int BLOCK_COLS = 3;

    public Sudoku9x9() {
        super(SIZE, BLOCK_ROWS, BLOCK_COLS);
    }

    @Override
    public Sudoku clone() {
        Sudoku sudokuClone = new Sudoku9x9();

        cloneSudoku(sudokuClone);
        return sudokuClone;
    }
}
