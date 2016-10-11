package org.paidaki.sudoku.grids;

import org.paidaki.sudoku.model.Sudoku;

public class Sudoku20x20_4x5 extends Sudoku {

    private static final int SIZE = 20;
    private static final int BLOCK_ROWS = 4;
    private static final int BLOCK_COLS = 5;

    public Sudoku20x20_4x5() {
        super(SIZE, BLOCK_ROWS, BLOCK_COLS);
    }

    @Override
    public Sudoku clone() {
        Sudoku sudokuClone = new Sudoku20x20_4x5();

        cloneSudoku(sudokuClone);
        return sudokuClone;
    }
}
