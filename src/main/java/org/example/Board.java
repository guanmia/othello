package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {

    private final int rowSize;
    private final int columnSize;
    private final char[][] gameBoard;
    private final List<Coordinate> moveHistory;
    public Board (int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        gameBoard = new char[rowSize][columnSize];
        moveHistory = new ArrayList<>();
    }

    public void putDisk(char disk, int row, int clown) {
        gameBoard[row][clown] = disk;

    }

    public void displayView() {
        for (int i = 0; i < rowSize; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < columnSize; j++) {

                System.out.print(gameBoard[i][j]);
            }
            System.out.println("\r");
        }
        System.out.println(" abcdefgh");
    }

    public Coordinate getMove(char turn) {
        System.out.println(turn + " Please enter your move:");
        Scanner in = new Scanner(System.in);
        String move = in.nextLine();
        System.out.println("You entered move " + move);
        Coordinate playerMove = new Coordinate(move);
        moveHistory.add(playerMove);
        return playerMove;

    }

    public Coordinate getMove(String move) {
        Coordinate playerMove = new Coordinate(move);
        moveHistory.add(playerMove);
        return playerMove;
    }

    public List<Coordinate> getMoveHistory() {
        return moveHistory;
    }
}
