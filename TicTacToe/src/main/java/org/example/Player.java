package org.example;

import static org.example.State.*;

public class Player {


    public static State getCurrentPlayer(Board currentBoard){

        int xCount = 0;
        int oCount = 0;

        State[][] board = currentBoard.getBoard();

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == O){
                    oCount += 1;
                } else if (board[i][j] == X){
                    xCount += 1;
                }
            }
        }
        // X starts first
        return xCount > oCount ? O : X;
    }

}
