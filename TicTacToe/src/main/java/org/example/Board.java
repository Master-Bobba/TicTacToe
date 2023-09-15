package org.example;

import java.awt.*;
import java.io.Serializable;

import static org.example.State.*;

public class Board implements Serializable {

    private State[][] board;

    public Board(){
        board = new State[3][3];
        this.initialiseBoard();
    }

    private void initialiseBoard(){
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[i].length; j++){
                board[i][j] = State.EMPTY;
            }
        }
    }

    public void displayBoard(){
        for (int i = 0; i < this.board.length; i++){
            System.out.print("|");
            for (int j = 0; j < this.board[i].length; j++){
                System.out.print(board[i][j].getSign()+ "|");
            }
            System.out.println();
        }
    }

    public State[][] getBoard(){
        return this.board;
    }

    private boolean isValidSelection(int i, int j){
        return board[i][j] != EMPTY ? false : true;
    }

    public boolean markUserSelection(int selection){

        int i = selection / 10;
        int j = selection % 10;

        if (isValidSelection(i,j)){
            board[i][j] = Player.getCurrentPlayer(this);
            return true;
        }
        return false;
    }


    /**
     * Just for testing purposes
     * @param args
     */
    public static void main (String[] args){
        Board board = new Board();
        board.displayBoard();
        GameLogic gl = new GameLogic();

        System.out.println(Player.getCurrentPlayer(board));
        System.out.println(gl.terminal(board));
        System.out.println(gl.winner(board));
    }

}
