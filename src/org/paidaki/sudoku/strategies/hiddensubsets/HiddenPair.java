package org.paidaki.sudoku.strategies.hiddensubsets;

import org.paidaki.sudoku.model.*;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.strategies.Strategy;
import org.paidaki.sudoku.strategies.StrategyFactory;
import org.paidaki.sudoku.strategies.hiddensubsets.helper.HiddenPairKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HiddenPair extends Strategy {

    private static final String NAME = "Hidden Pair";
    private static final String SHORT_NAME = "HP";
    private static final int DIFFICULTY_INDEX = 5;

    private static final int SUBSET_NUM = 2;

    public HiddenPair() {
        super(StrategyFactory.HIDDEN_PAIR, NAME, SHORT_NAME, DIFFICULTY_INDEX);
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
        HashMap<HiddenPairKey, AtomicInteger> pairs = new HashMap<>();

        for (Map.Entry<SudokuValue, ArrayList<Cell>> entry : region.getAvailableCount().entrySet()) {
            SudokuValue key = entry.getKey();
            ArrayList<Cell> cellList = entry.getValue();

            if (cellList.size() == SUBSET_NUM) {
                HiddenPairKey pairKey = new HiddenPairKey(cellList);

                pairs.putIfAbsent(pairKey, new AtomicInteger(0));
                pairs.get(pairKey).getAndIncrement();

                for (HiddenPairKey k : pairs.keySet()) {
                    if (pairKey.equals(k)) {
                        pairKey = k;
                        pairKey.values.add(key);
                        break;
                    }
                }
                if (pairs.get(pairKey).get() == SUBSET_NUM) {
                    boolean found = false;

                    for (Cell cell : pairKey.cells) {
                        int currentCellIndex = 0;

                        while (currentCellIndex < cell.getAvailable().size()) {
                            SudokuValue sv = cell.getAvailable().get(currentCellIndex);

                            if (!pairKey.values.contains(sv)) {
                                cell.removeAvailable(sv);
                                currentCellIndex = 0;
                                found = true;
                            } else {
                                currentCellIndex++;
                            }
                        }
                    }
                    if (found) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
