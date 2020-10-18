package org.example;

/** Class Othello
 * represents a two person game of Othello (also known as Reversi or Dark and Light Chess).
 * The game starts on a 2D board with two dark and two light disks on diagonals in the
 * centre of the board. The players alternate turns starting with dark.
 * The player puts a disk on one of the squares and where the new disk forms a line
 * that surrounds the opponent's disk in any direction, the disk in that line switch colours.
 * The game ends when the board is filled with pegs or no valid step available.
 */
public class Othello
{
    /** A constant representing an empty place on the board. */
    private static final char NO_DISK = '-';
    /** A constant representing a dark disk on the board. */
    private static final char DARK = 'X';
    /** A constant representing a light disk on the board. */
    private static final char LIGHT = 'O';
    /** A constant indicating the size of the game board. */
    private static final int BOARD_SIZE = 8;

    /** This array keeps track of the logical state of the game. */
    private char[][] grid = new char[BOARD_SIZE][BOARD_SIZE];
    /** This board contains the physical state of the game. */
    private final Board gameBoard = new Board(BOARD_SIZE, BOARD_SIZE);

    public Othello() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = NO_DISK;
            }
        }
        grid[3][3]=LIGHT;
        grid[3][4]=DARK;
        grid[4][3]=DARK;
        grid[4][4]=LIGHT;

        this.updateView();
    }

    public Othello(char[][] grid) {
       this.grid = grid;
       this.updateView();
    }

    public void play (String moves) {
        String[] playerMoves = moves.split(",");
        char turn = DARK;
        for (String playerMove : playerMoves) {
            Coordinate c = this.gameBoard.getMove(playerMove);
            int row = c.getRow();
            int col = c.getCol();
            if (!this.gameOver(turn) && this.validMove(turn, row, col)) {
                this.takeTurn(turn, row, col);
                // After the turn, switch the players
                if (turn == DARK) {
                    turn = LIGHT;
                } else {
                    turn = DARK;
                }
            } else {
                System.out.println("Game is over or there is invalid move.");
            }
        }
    }

    public void play()
    {
        char turn = DARK;
        while (!this.gameOver(turn)) {
            int row = 0;
            int col = 0;
            boolean valid = false;

            while (!valid) {
                Coordinate c = this.gameBoard.getMove(turn);
                row = c.getRow();
                col = c.getCol();
                valid = this.validMove(turn, row, col);
                if (!valid) {
                    System.out.println("Invalid move. Please try again.");
                }
            }

            this.takeTurn(turn, row, col);
            if (turn == DARK)
            {
                turn = LIGHT;
            } else
            {
                turn = DARK;
            }
        }
        this.endGame();
    }

    /**
     * This method will handle the logic of a single turn in the Othello game.
     * It should "flip" the opponents disks wherever they are surrounded in a line
     * by the latest disk played
     */
    private void takeTurn(char turn, int row, int col) {
        grid[row][col]=turn;
        //check above & below
        flipDisk(row, col, turn, 0, -1);
        flipDisk(row, col, turn, 0, 1);
        //check right & left
        flipDisk(row, col, turn, 1,0);
        flipDisk(row, col, turn, -1, 0);
        //check corners
        flipDisk(row, col, turn, 1,1);
        flipDisk(row, col, turn, 1,-1);
        flipDisk(row, col, turn, -1,1);
        flipDisk(row, col, turn, -1,-1);

        this.updateView();
    }

    private void flipDisk(int row, int column, char colour, int colDir, int rowDir) {
        int currentRow= row + rowDir;
        int currentCol = column + colDir;
        if (currentRow == BOARD_SIZE || currentRow < 0 || currentCol == BOARD_SIZE || currentCol < 0) {
            return;
        }
        while (grid[currentRow][currentCol] == DARK || grid[currentRow][currentCol] == LIGHT) {
            //To check where the other surrounding one placed earlier is
            if (grid[currentRow][currentCol] == colour) {
                while(!(row == currentRow && column == currentCol)) {
                    grid[currentRow][currentCol] = colour;
                    //Reverse direction in order to flip the opponent disk
                    currentRow = currentRow - rowDir;
                    currentCol = currentCol - colDir;
                }
                break;
            } else { //go further to find the other surrounding one placed earlier
                currentRow = currentRow + rowDir;
                currentCol = currentCol + colDir;
            }
            if (currentRow < 0 || currentCol < 0 || currentRow == BOARD_SIZE || currentCol == BOARD_SIZE) {
                break;
            }
        }
    }

    public boolean validMove(char turn, int row, int col) {
        boolean result = false;
        char oppCol = DARK;
        if (turn == DARK) {
            oppCol = LIGHT;
        }

        if (row < 0 || row > BOARD_SIZE - 1 || col < 0 || col > BOARD_SIZE -1) {
            return result;
        }
        //current
        if (grid[row][col] == NO_DISK) {

            if (row + 2 < BOARD_SIZE && col + 2 < BOARD_SIZE &&
                    grid[row + 2][col + 2] == turn && grid[row + 1][col + 1] == oppCol) { //place on the left top of diagonal
                result = true;

            } else if (row - 2 > -1 && col -2 > -1 &&
                    grid[row - 2][col - 2] == turn && grid[row - 1][col - 1] == oppCol) { //place on the right bottom of diagonal
                result = true;

            } else if (row-2 > -1 && grid[row - 2][col] == turn && grid[row - 1][col] == oppCol) { //place on the right of horizontal
                result = true;

            } else if(row + 2 < BOARD_SIZE && grid[row + 2][col] == turn && grid[row + 1][col] == oppCol) {  //place on the left of horizontal
                result = true;

            } else if(col + 2 < BOARD_SIZE && grid[row][col + 2] == turn && grid[row][col + 1] == oppCol) { //place on the top of vertical
                result = true;

            } else if (col -2 > -1 && grid[row][col - 2] == turn && grid[row][col - 1] == oppCol) { //place on the bottom of vertical
                result = true;

            } else if(row-2 > -1 && col + 2 < BOARD_SIZE &&
                    grid[row-2][col+2] == turn && grid[row-1][col+1] == oppCol) { //place on the left bottom of diagonal
                result = true;

            } else if (row + 2 < BOARD_SIZE && col - 2 > -1 &&
                    grid[row + 2][col - 2] == turn && grid[row+1][col-1] == oppCol) { //place on the right top of diagonal
                result = true;
            }
        }
        return result;
    }

    private void displayStatus(char turn) {
        int countX=0;
        int countO=0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (grid[i][j] == DARK) {
                    countX++;
                } else if(grid[i][j] == LIGHT) {
                    countO++;
                }
            }
        }
        System.out.println("**Dark** has " +countX+ " disks up --- Light has " +countO+ " disks up");

    }

    /**
     * This method will determine when the game is over.
     * Returns true when there are no valid moves left, false otherwise.
     */
    public boolean gameOver(char turn) {
        boolean isOver = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (grid[i][j] == NO_DISK) {
                    if (validMove(turn, i, j)) {
                        isOver = false;
                        break;
                    }
                }
            }
        }
        return isOver;
    }

    private void endGame() {
        int countO=0;
        int countX=0;
        for (int i=0; i<grid.length; i++)
        {
            for (int j=0; j<grid[i].length; j++) {
                if (grid[i][j]==DARK) {
                    countX++;
                } else if (grid[i][j]==LIGHT) {
                    countO++;
                }
            }
        }
        if (countX > countO) {
            System.out.println("Player " + DARK + "wins (" + countX + "vs" + countO + ")");
        } else if (countO > countX) {
            System.out.println("Player " + LIGHT + "wins (" + countO + "vs" + countX + ")");
        } else {
            System.out.println("It is a tie game");
        }

    }

    private void updateView() {
        for (int i = 0; i < grid.length; i++) {
            for (int j=0; j < grid[i].length; j++) {
                gameBoard.putDisk(grid[i][j], i, j);
            }
        }
        gameBoard.displayView();
    }

    public static void main(String[] args) {
        Othello game = new Othello();
        if (args != null && args.length > 0) {
            game.play(args[0]);
        } else {
            game.play();
        }
    }

}
