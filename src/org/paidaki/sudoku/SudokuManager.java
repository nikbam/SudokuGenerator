package org.paidaki.sudoku;

import org.paidaki.sudoku.grids.Sudoku9x9;
import org.paidaki.sudoku.model.Cell;
import org.paidaki.sudoku.model.Sudoku;
import org.paidaki.sudoku.model.enums.SudokuValue;
import org.paidaki.sudoku.strategies.StrategyFactory;

import java.util.Random;

public class SudokuManager {

    public static final Random RAND = new Random(56456132);

    private SudokuManager() {
    }

    public static void start() {
        // Naked Single
        String nakedSingle = "412736589000000106568010370000850210100000008087090000030070865800000000000908401";

        // Hidden Single
        String hiddenSingle = "028007000016083070000020851137290000000730000000046307290070000000860140000300700";

        // Full House
        String fullHouse = "80073900637046500004018200900060004005430061006050000040085307000027106410094000";

        // Locked Candidates Type 1
        String lockedCandidate1 = "984000000002500040001904002006097230003602000209035610195768423427351896638009751";
//        String lockedCandidate1 = "340006070080000930002030060000010000097364850000002000000000000000608090000923785";

        // Locked Candidates Type 2
        String lockedCandidate2 = "318005406000603810006080503864952137123476958795318264030500780000007305000039641";

        // Hidden Pair
//        String hiddenPair = "049132000081479000327685914096051800075028000038046005853267000712894563964513000";
        String hiddenPair = "000060000000042736006730040094000068000096407607050923100000085060080271005010094";

        Sudoku sudoku = new Sudoku9x9();
        int strategy = StrategyFactory.HIDDEN_PAIR;

        for (int i = 0; i < sudoku.getFullSize(); i++) {
            Cell cell = sudoku.getCells()[i];
            cell.setNewValue(SudokuValue.getValueByIndex(Character.getNumericValue(hiddenPair.charAt(i))));
        }

        System.out.println(sudoku);
        System.out.println(sudoku.detailedRepresentation());
        System.out.println(sudoku.oneLineRepresentation());

        System.out.println(StrategyFactory.getStrategy(strategy).applyStrategy(sudoku));

        System.out.println(sudoku);
        System.out.println(sudoku.detailedRepresentation());
        System.out.println(sudoku.oneLineRepresentation());

        System.out.println(StrategyFactory.getStrategy(strategy).applyStrategy(sudoku));

        System.out.println(sudoku);
        System.out.println(sudoku.detailedRepresentation());
        System.out.println(sudoku.oneLineRepresentation());

        System.out.println(StrategyFactory.getStrategy(strategy).applyStrategy(sudoku));

        System.out.println(sudoku);
        System.out.println(sudoku.detailedRepresentation());
        System.out.println(sudoku.oneLineRepresentation());
    }
}
