package org.paidaki.sudoku.model.enums;

import java.util.ArrayList;

public enum SudokuValue {
    EMPTY("_"), A("1"), B("2"), C("3"), D("4"), E("5"),
    F("6"), G("7"), H("8"), I("9"), J("A"), K("B"), L("C"),
    M("D"), N("E"), O("F"), P("G"), Q("H"), R("I"), S("J"),
    T("K"), U("L"), V("M"), W("N"), X("O"), Y("P"), Z("Q");

    private String value;

    SudokuValue(String value) {
        this.value = value;
    }

    public static ArrayList<SudokuValue> getValuesList(int size) {
        ArrayList<SudokuValue> available = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            available.add(getValueByIndex(i));
        }
        return available;
    }

    public static SudokuValue getValueByIndex(int index) {
        return SudokuValue.values()[index];
    }

    public String getValue() {
        return value;
    }


    //TODO Remove
    @Override
    public String toString() {
        return this.getValue();
    }
}
