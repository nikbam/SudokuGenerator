package org.paidaki.sudoku.grids;

import org.paidaki.sudoku.model.Sudoku;

public class Sudoku20x20_5x4 extends Sudoku {

    private static final int SIZE = 20;
    private static final int BLOCK_ROWS = 5;
    private static final int BLOCK_COLS = 4;

    public Sudoku20x20_5x4() {
        super(SIZE, BLOCK_ROWS, BLOCK_COLS);
    }

    @Override
    public Sudoku clone() {
        Sudoku sudokuClone = new Sudoku20x20_5x4();

        cloneSudoku(sudokuClone);
        return sudokuClone;
    }
}
