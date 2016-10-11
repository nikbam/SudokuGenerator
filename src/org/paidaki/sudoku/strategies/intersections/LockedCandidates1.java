package org.paidaki.sudoku.strategies.intersections;

import org.paidaki.sudoku.model.*;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.strategies.Strategy;
import org.paidaki.sudoku.strategies.StrategyFactory;

import java.util.ArrayList;
import java.util.Map;

public class LockedCandidates1 extends Strategy {

    private static final String NAME = "Locked Candidates Type 1";
    private static final String SHORT_NAME = "LC1";
    private static final int DIFFICULTY_INDEX = 3;

    public LockedCandidates1() {
        super(StrategyFactory.FULL_HOUSE, NAME, SHORT_NAME, DIFFICULTY_INDEX);
    }

    @Override
    public boolean applyStrategy(Sudoku sudoku) {
        for (Block block : sudoku.getBlocks()) {
            for (Map.Entry<SudokuValue, ArrayList<Cell>> entry : block.getAvailableCount().entrySet()) {
                SudokuValue key = entry.getKey();
                ArrayList<Cell> cellList = entry.getValue();
                boolean onSameRow = true;
                boolean onSameColumn = true;

                if (cellList.size() > 1) {
                    Row row = cellList.get(0).getRow();
                    Column column = cellList.get(0).getColumn();

                    for (int i = 1; i < cellList.size(); i++) {
                        Cell cell = cellList.get(i);

                        if (cell.getRow() != row) {
                            onSameRow = false;
                        }
                        if (cell.getColumn() != column) {
                            onSameColumn = false;
                        }
                    }
                    ArrayList<Cell> regionCells;

                    if (onSameRow) {
                        regionCells = row.getAvailableCount().get(key);
                    } else if (onSameColumn) {
                        regionCells = column.getAvailableCount().get(key);
                    } else {
                        continue;
                    }
                    boolean found = false;
                    int currentCellIndex = 0;

                    while (currentCellIndex < regionCells.size()) {
                        Cell cell = regionCells.get(currentCellIndex);

                        if (cell.getBlock() != block) {
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
        }
        return false;
    }
}
