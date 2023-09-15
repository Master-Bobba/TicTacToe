package org.example;

import java.util.Scanner;
import static org.example.State.*;

public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe");
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        GameLogic gl = new GameLogic();

        board.displayBoard();

        while(!gl.terminal(board)){
            System.out.println("Please select a cell for your next move 'ij' for [i,j]: ");
            int selection = scanner.nextInt();
            if (board.markUserSelection(selection)){
                board.displayBoard();
                // Check for a winner
                if (gl.terminal(board) && gl.winner(board) == X) {
                    System.out.println("Congratulations. You Win");
                    System.exit(0);
                }
            
            } else {
                System.out.print("Invalid selection. ");
            }

        }

    }
}