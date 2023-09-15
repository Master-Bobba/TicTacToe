package org.example;

import static org.example.State.*;

public class GameLogic {

    public State winner(Board currentBoard){
        State[][] board = currentBoard.getBoard();
        // Rows
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2]){
            if (board[0][0] != EMPTY)
                return board[0][0];
        } else if (board[1][0] == board[1][1] && board[1][1] == board[1][2]){
            if (board[1][0] != EMPTY)
                return board[1][0];
        } else if (board[2][0] == board[2][1] && board[2][1] == board[2][2]){
            if (board[2][0] != EMPTY)
                return board[2][0];
        }
        // Columns
        else if (board[0][0] == board[1][0] && board[1][0] == board[2][0]){
            if (board[0][0] != EMPTY)
                return board[0][0];
        } else if (board[0][1] == board[1][1] && board[1][1] == board[2][1]){
            if (board[0][1] != EMPTY)
                return board[0][1];
        } else if (board[0][2] == board[1][2] && board[1][2] == board[2][2]){
            if (board[0][2] != EMPTY)
                return board[0][2];
        }
        // Diagonals
        else if (board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            if (board[0][0] != EMPTY)
                return board[0][0];
        } else if(board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            if (board[0][2] != EMPTY)
                return board[0][2];
        }
        return EMPTY;
    }

    public boolean terminal(Board currentBoard){
        State[][] board = currentBoard.getBoard();

        if (this.winner(currentBoard) != EMPTY ){
            return true;
        }
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] ==  EMPTY){
                    return false;
                }
            }
        }
        return true;
    }



}
