This project is a game of Othello implementation in Java.
The game is a two person game of Othello (also known as Reversi or Dark and Light Chess).
The game starts on a 2D board with two dark and two light disks on diagonals in the
centre of the board. The players alternate turns starting with dark.
The player puts a disk on one of the squares and where the new disk forms a line
that surrounds the opponent's disk in any direction, the disk in that line switch colours.
The game ends when the board is filled with disks or no valid step available.

To compile the project source code, please run "mvn compile" at the project root directory

To run the project program with predefined moves, please run mvn exec:java -Dexec.mainClass="org.example.Othello" -Dexec.args="f5,6f,f7,4f,f3,3e,d3,c5"

To run the project program with interactive way, please run mvn exec:java -Dexec.mainClass="org.example.Othello"