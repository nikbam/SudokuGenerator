package org.paidaki.sudoku.strategies.hiddensubsets;

import org.paidaki.sudoku.model.*;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.strategies.Strategy;
import org.paidaki.sudoku.strategies.StrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HiddenTriple extends Strategy {

    private static final String NAME = "Hidden Triple";
    private static final String SHORT_NAME = "HT";
    private static final int DIFFICULTY_INDEX = 6;

    private static final int SUBSET_NUM = 3;

    public HiddenTriple() {
        super(StrategyFactory.HIDDEN_TRIPLE, NAME, SHORT_NAME, DIFFICULTY_INDEX);
    }

    @Override
    public boolean applyStrategy(Sudoku sudoku) {
        for (Row row : sudoku.getRows()) {
            if (checkRegion(row)) {
                return true;
            }
        }
        for (Column column : sudoku.getColumns()) {
            if (checkRegion(column)) {
                return true;
            }
        }
        for (Block block : sudoku.getBlocks()) {
            if (checkRegion(block)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRegion(Region region) {
        ArrayList<ArrayList<Cell>> subsets = new ArrayList<>();
        HashMap<SudokuValue, ArrayList<Cell>> candidates = new HashMap<>();

        for (Map.Entry<SudokuValue, ArrayList<Cell>> entry : region.getAvailableCount().entrySet()) {
            SudokuValue key = entry.getKey();
            ArrayList<Cell> cellList = entry.getValue();

            if (cellList.size() > 1 && cellList.size() <= SUBSET_NUM) {
                candidates.put(key, cellList);
            }
            //TODO
        }
        return false;
    }
}
