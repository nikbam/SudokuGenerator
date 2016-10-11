package org.paidaki.sudoku.strategies.intersections;

import org.paidaki.sudoku.model.*;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.strategies.Strategy;
import org.paidaki.sudoku.strategies.StrategyFactory;

import java.util.ArrayList;
import java.util.Map;

public class LockedCandidates2 extends Strategy {

    private static final String NAME = "Locked Candidates Type 2";
    private static final String SHORT_NAME = "LC2";
    private static final int DIFFICULTY_INDEX = 4;

    public LockedCandidates2() {
        super(StrategyFactory.FULL_HOUSE, NAME, SHORT_NAME, DIFFICULTY_INDEX);
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
        return false;
    }

    private boolean checkRegion(Region region) {
        for (Map.Entry<SudokuValue, ArrayList<Cell>> entry : region.getAvailableCount().entrySet()) {
            SudokuValue key = entry.getKey();
            ArrayList<Cell> cellList = entry.getValue();
            boolean onSameBlock = true;

            if (cellList.size() > 1) {
                Block block = cellList.get(0).getBlock();

                for (int i = 1; i < cellList.size(); i++) {
                    Cell cell = cellList.get(i);

                    if (cell.getBlock() != block) {
                        onSameBlock = false;
                    }
                }
                if (!onSameBlock) {
                    continue;
                }
                ArrayList<Cell> blockCells = block.getAvailableCount().get(key);
                boolean found = false;
                int currentCellIndex = 0;

                while (currentCellIndex < blockCells.size()) {
                    Cell cell = blockCells.get(currentCellIndex);

                    if (!cellList.contains(cell)) {
                        cell.removeAvailable(key);
                        currentCellIndex = 0;
                        found = true;
                    } else {
                        currentCellIndex++;
                    }
                }
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }
}
