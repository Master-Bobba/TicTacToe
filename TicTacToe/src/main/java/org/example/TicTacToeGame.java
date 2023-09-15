package org.example;

import java.io.IOException;
import java.util.Scanner;
import static org.example.State.*;

public class TicTacToeGame {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Welcome to Tic-Tac-Toe");
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        GameLogic gl = new GameLogic();
        StatsReader statsReader = new StatsReader();

        board.displayBoard();

        while(!gl.terminal(board)){
            System.out.print("Please select a cell for your next move 'ij' for [i,j]: ");
            int selection = scanner.nextInt();
            if (board.markUserSelection(selection)){
                board.displayBoard();
                // Check for a winner
                if (gl.terminal(board) && gl.winner(board) == X) {
                    System.out.println("Congratulations. You Win.");
                    statsReader.setLosses();
                    System.exit(0);
                }
                // Computer to Move
                System.out.println("Loading Computer's Move...");
                gl.performComputerMove(board);
                board.displayBoard();
                if (gl.terminal(board) && gl.winner(board) == O){
                    System.out.println("Unlucky. Computer wins.");
                    statsReader.setWins();
                    System.exit(0);
                }
            } else {
                System.out.print("Invalid selection. ");
            }
        }
        statsReader.setDraws();
        System.out.println("GAME OVER!");
    }
}