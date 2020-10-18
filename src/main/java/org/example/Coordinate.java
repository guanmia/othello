package org.example;

public class Coordinate {
    private int row = -1;
    private int col = -1;
    private static final char[] rowCoordinates = {'1','2','3','4','5','6','7','8'};
    private static final char[] colCoordinates = {'a','b','c','d','e','f','g','h'};

    public Coordinate (String move) {
        char[] chars = move.toCharArray();
        if (chars.length == 2) {
            for (char c : chars) {
                for (int i = 0; i < rowCoordinates.length; i++) {
                    if (c == rowCoordinates[i]) {
                        row = i;
                        break;
                    }
                }

                for (int i = 0; i < colCoordinates.length; i++) {
                    if (c == colCoordinates[i]) {
                        col = i;
                        break;
                    }
                }
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
