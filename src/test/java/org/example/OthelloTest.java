package org.example;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class OthelloTest {
    @Test
    public void gameOverTest() {
        char DARK = 'X';
        char LIGHT = 'O';
        char NO_DISK = '-';
        char[][] grid = new char[8][8];
        for (int i = 0; i < grid.length; i++) {
            for (int j=0; j < grid[i].length; j++) {
                grid[i][j] = DARK;
            }
        }
        assertTrue(new Othello(grid).gameOver(DARK));
        assertTrue(new Othello(grid).gameOver(LIGHT));
        grid[0][7] = NO_DISK;
        grid[1][7] = NO_DISK;
        grid[2][7] = NO_DISK;
        grid[3][7] = NO_DISK;
        grid[4][7] = NO_DISK;
        grid[5][7] = NO_DISK;
        grid[6][7] = NO_DISK;
        grid[7][7] = NO_DISK;
        assertTrue(new Othello(grid).gameOver(DARK));
        assertTrue(new Othello(grid).gameOver(LIGHT));
        grid[0][0] = NO_DISK;
        grid[1][0] = NO_DISK;
        grid[2][0] = NO_DISK;
        grid[3][0] = NO_DISK;
        grid[4][0] = NO_DISK;
        grid[5][0] = NO_DISK;
        grid[6][0] = NO_DISK;
        grid[7][0] = NO_DISK;
        assertTrue(new Othello(grid).gameOver(DARK));
        assertTrue(new Othello(grid).gameOver(LIGHT));
        grid[0][1] = NO_DISK;
        grid[0][2] = NO_DISK;
        grid[0][3] = NO_DISK;
        grid[0][4] = NO_DISK;
        grid[0][5] = NO_DISK;
        grid[0][6] = NO_DISK;
        grid[0][7] = NO_DISK;
        assertTrue(new Othello(grid).gameOver(DARK));
        assertTrue(new Othello(grid).gameOver(LIGHT));
        grid[7][1] = NO_DISK;
        grid[7][2] = NO_DISK;
        grid[7][3] = NO_DISK;
        grid[7][4] = NO_DISK;
        grid[7][5] = NO_DISK;
        grid[7][6] = NO_DISK;
        grid[7][7] = NO_DISK;
        assertTrue(new Othello(grid).gameOver(DARK));
        assertTrue(new Othello(grid).gameOver(LIGHT));
        grid[6][7] = LIGHT;
        assertTrue(new Othello(grid).gameOver(DARK));
        assertTrue(new Othello(grid).gameOver(LIGHT));
        grid[4][5] = NO_DISK;
        assertTrue(new Othello(grid).gameOver(DARK));
        assertFalse(new Othello(grid).gameOver(LIGHT));
        grid[5][7] = DARK;
        assertFalse(new Othello(grid).gameOver(DARK));
        assertFalse(new Othello(grid).gameOver(LIGHT));
    }

    @Test
    public void validMoveTest() {
        Othello o1 = new Othello();
        o1.play("f5");
        char DARK = 'X';
        char LIGHT = 'O';
        Board b = new Board(8, 8);
        assertTrue(o1.validMove(LIGHT, b.getMove("6f").getRow(), b.getMove("6f").getCol()));

        Othello o2 = new Othello();
        o2.play("f5,6f");
        assertTrue(o2.validMove(DARK, b.getMove("f7").getRow(), b.getMove("f7").getCol()));

        Othello o3 = new Othello();
        o3.play("f5,6f,f7");
        assertTrue(o3.validMove(LIGHT, b.getMove("4f").getRow(), b.getMove("4f").getCol()));

        Othello o4 = new Othello();
        o4.play("f5,6f,f7,4f,f3,3e,d3");
        assertTrue(o4.validMove(LIGHT, b.getMove("c5").getRow(), b.getMove("c5").getCol()));
        assertFalse(o4.validMove(LIGHT, b.getMove("f7").getRow(), b.getMove("f7").getCol()));
        assertFalse(o4.validMove(LIGHT, b.getMove("d3").getRow(), b.getMove("d3").getCol()));
        assertFalse(o4.validMove(LIGHT, b.getMove("c1").getRow(), b.getMove("c1").getCol()));
        assertFalse(o4.validMove(LIGHT, b.getMove("g1").getRow(), b.getMove("g1").getCol()));
    }
}
