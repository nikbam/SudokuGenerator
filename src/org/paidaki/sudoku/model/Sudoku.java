package org.paidaki.sudoku.model;

import org.paidaki.sudoku.model.enums.SudokuValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Sudoku {

    private int singleSize, blockSize, blockRows, blockCols;
    private Sudoku solution;
    private Row[] rows;
    private Column[] columns;
    private Block[] blocks;
    private Cell[] cells;
    private DifficultyMetrics difficultyMetrics;

    public Sudoku(int singleSize, int blockRows, int blockCols) {
        this.singleSize = singleSize;
        this.blockRows = blockRows;
        this.blockCols = blockCols;
        this.blockSize = singleSize / blockRows * singleSize / blockCols;
        this.solution = null;

        rows = new Row[singleSize];
        columns = new Column[singleSize];
        blocks = new Block[blockSize];
        cells = new Cell[singleSize * singleSize];
        difficultyMetrics = new DifficultyMetrics();

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Row(i, singleSize, singleSize);
        }
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new Column(i, singleSize, singleSize);
        }
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(i, blockRows, blockCols, singleSize);
        }
        initialize(singleSize, blockRows, blockCols);
    }

    @Override
    public abstract Sudoku clone();

    public void cloneSudoku(Sudoku sudokuClone) {
        for (int i = 0; i < blockSize; i++) {
            sudokuClone.getRows()[i].setAvailableCount(cloneAvailableCount(rows[i].getAvailableCount(), sudokuClone));
            sudokuClone.getColumns()[i].setAvailableCount(cloneAvailableCount(columns[i].getAvailableCount(), sudokuClone));
            sudokuClone.getBlocks()[i].setAvailableCount(cloneAvailableCount(blocks[i].getAvailableCount(), sudokuClone));

            Row cloneBlockRows[] = sudokuClone.getBlocks()[i].getRows();
            Row blockRows[] = blocks[i].getRows();
            for (int j = 0; j < cloneBlockRows.length; j++) {
                Cell cloneCells[] = cloneBlockRows[j].getCells();
                Cell cells[] = blockRows[j].getCells();

                for (int k = 0; k < cloneCells.length; k++) {
                    Cell cloneCell = cloneCells[k];
                    Cell cell = cells[k];

                    cloneCell.setValue(cell.getValue());
                    cloneCell.setAvailable(new ArrayList<>(cell.getAvailable()));
                }
                cloneBlockRows[j].setAvailableCount(cloneAvailableCount(blockRows[j].getAvailableCount(), sudokuClone));
            }
            Column cloneBlockColumns[] = sudokuClone.getBlocks()[i].getColumns();
            Column blockColumns[] = blocks[i].getColumns();
            for (int j = 0; j < cloneBlockColumns.length; j++) {
                cloneBlockColumns[j].setAvailableCount(cloneAvailableCount(blockColumns[j].getAvailableCount(), sudokuClone));
            }
        }
        sudokuClone.setSolution(solution);
        sudokuClone.setDifficultyMetrics(new DifficultyMetrics(difficultyMetrics));
    }

    private HashMap<SudokuValue, ArrayList<Cell>> cloneAvailableCount(HashMap<SudokuValue, ArrayList<Cell>> original, Sudoku sudokuClone) {
        HashMap<SudokuValue, ArrayList<Cell>> clone = new HashMap<>();

        for (Map.Entry<SudokuValue, ArrayList<Cell>> entry : original.entrySet()) {
            SudokuValue key = entry.getKey();
            ArrayList<Cell> value = entry.getValue();

            ArrayList<Cell> cells = new ArrayList<>();
            for (Cell c : value) {
                int row = c.getRow().getIndex();
                int column = c.getColumn().getIndex();

                cells.add(sudokuClone.getRows()[row].getCells()[column]);
            }
            clone.put(key, cells);
        }
        return clone;
    }

    private void initialize(int size, int blockRows, int blockCols) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int cellIndex = (i * size) + (j % size);
                int blockIndex = (i / blockRows) * blockRows + j / blockCols;
                int blockRowIndex = i % blockRows;
                int blockColumnIndex = j % blockCols;
                int blockCellIndex = blockRowIndex * blockCols + blockColumnIndex;
                Cell cell = new Cell(rows[i], columns[j], blocks[blockIndex]);

                cells[cellIndex] = cell;
                rows[i].getCells()[j] = cell;
                columns[j].getCells()[i] = cell;
                blocks[blockIndex].getRows()[blockRowIndex].getCells()[blockColumnIndex] = cell;
                blocks[blockIndex].getColumns()[blockColumnIndex].getCells()[blockRowIndex] = cell;
                blocks[blockIndex].getCells()[blockCellIndex] = cell;
            }
        }
    }

    public boolean isCellValueValid(Cell cell, SudokuValue value) {
        return cell.isValueValid(value);
    }

    public boolean isSolved() {
        for (Cell c : cells) {
            if (c.getValue() == SudokuValue.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public void emptyAvailable() {
        for (int i = 0; i < blockSize; i++) {
            rows[i].clearAvailableCount();
            columns[i].clearAvailableCount();
            blocks[i].clearAvailableCount();

            for (Row r : blocks[i].getRows()) {
                for (Cell cell : r.getCells()) {
                    cell.getAvailable().clear();
                }
                r.clearAvailableCount();
            }
            for (Column c : blocks[i].getColumns()) {
                c.clearAvailableCount();
            }
        }
    }

    public Row[] getRows() {
        return rows;
    }

    public Column[] getColumns() {
        return columns;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public Cell[] getCells() {
        return cells;
    }

    public int getFullSize() {
        return singleSize * singleSize;
    }

    public int getSingleSize() {
        return singleSize;
    }

    public DifficultyMetrics getDifficultyMetrics() {
        return difficultyMetrics;
    }

    public void setDifficultyMetrics(DifficultyMetrics difficultyMetrics) {
        this.difficultyMetrics = difficultyMetrics;
    }

    public Sudoku getSolution() {
        return solution;
    }

    public void setSolution(Sudoku solution) {
        this.solution = solution;
    }

    //TODO Remove
    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();

        for (int i = 0; i < rows.length; i++) {
            if (i % blockRows == 0) {
                strB.append("  ");

                for (int j = 0; j < singleSize; j++) {
                    strB.append("- ");

                    if ((j + 1) % blockCols == 0) {
                        strB.append("  ");
                    }
                }
                strB.append("\n");
            }
            strB.append("| ");

            for (int j = 0; j < rows[i].getCells().length; j++) {
                Cell c = rows[i].getCells()[j];
                strB.append(c.getValue().getValue()).append(" ");

                if ((j + 1) % blockCols == 0) {
                    strB.append("| ");
                }
            }
            strB.append("\n");
        }
        strB.append("  ");

        for (int j = 0; j < singleSize; j++) {
            strB.append("- ");

            if ((j + 1) % blockCols == 0) {
                strB.append("  ");
            }
        }
        strB.append("\n");

        return strB.toString();
    }

    public String oneLineRepresentation() {
        StringBuilder strB = new StringBuilder();

        for (Row r : rows) {
            for (Cell c : r.getCells()) {
                strB.append(c.getValue().getValue().equals("_") ? String.valueOf(0) : c.getValue().getValue());
            }
        }
        return strB.toString();
    }

    public String detailedRepresentation() {
        StringBuilder strB = new StringBuilder();
        ArrayList<SudokuValue> values = SudokuValue.getValuesList(singleSize);

        for (int i = 0; i < rows.length; i++) {
            if (i % blockRows == 0) {
                strB.append("  ");

                for (int j = 0; j < singleSize; j++) {
                    for (int k = 0; k < singleSize + 2; k++) {
                        strB.append("-");
                    }
                    strB.append(" ");

                    if ((j + 1) % blockCols == 0) {
                        strB.append("  ");
                    }
                }
                strB.append("\n");
            }
            strB.append("| ");

            for (int j = 0; j < rows[i].getCells().length; j++) {
                Cell c = rows[i].getCells()[j];
                strB.append("(");

                for (SudokuValue sv : values) {
                    if (c.getAvailable().contains(sv)) {
                        strB.append(sv.getValue());
                    } else {
                        if (c.getValue() == sv) {
                            strB.append(sv.getValue());
                        } else {
                            strB.append(" ");
                        }
                    }
                }
                strB.append(")").append(" ");

                if ((j + 1) % blockCols == 0) {
                    strB.append("| ");
                }
            }
            strB.append("\n");
        }
        strB.append("  ");

        for (int j = 0; j < singleSize; j++) {
            for (int k = 0; k < singleSize + 2; k++) {
                strB.append("-");
            }
            strB.append(" ");

            if ((j + 1) % blockCols == 0) {
                strB.append("  ");
            }
        }
        strB.append("\n");

        return strB.toString();
    }
}
