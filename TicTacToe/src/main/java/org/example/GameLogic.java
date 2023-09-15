package org.example;

import java.io.*;
import java.util.HashSet;

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

    private HashSet<String> actions(Board currentBoard){
        State[][] board = currentBoard.getBoard();

        HashSet<String> possibleActions = new HashSet<>();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == EMPTY){
                    possibleActions.add("" + i + j);
                }
            }
        }
        return possibleActions;
    }

    private Value<Integer, String> utility(Board board){
        if (terminal(board)){
            if (winner(board) == X) {
                return new Value<Integer, String>(1, null);
            } else if (winner(board) == O) {
                return new Value<Integer, String>(-1, null);
            }else {
                return new Value<Integer, String>(0, null);
            }
        }
        return new Value<Integer, String>(0, null);
    }

    public String minimax(Board board) throws IOException, ClassNotFoundException {

        if(terminal(board)){
            return null;
        }
        if (Player.getCurrentPlayer(board) == X){
            Value<Integer, String> value = maxValue(board);
            return value.act;
        } else {
            Value<Integer, String> value = minValue(board);
            return value.act;
        }
    }

    private Value<Integer, String> maxValue(Board board) throws IOException, ClassNotFoundException {

        if (terminal(board)){
            return utility(board);
        }
        int v = Integer.MIN_VALUE;
        String move = null;

        for (String action : actions(board)){
            Value<Integer, String> value = minValue(result(board, action));
            if (value.value > v){
                v = value.value;
                move = action;
                if (v == 1) {
                    return new Value<Integer,String>(v, move);
                }
            }
        }
        return new Value<Integer, String>(v, move);
    }

    private Value<Integer, String> minValue(Board board) throws IOException, ClassNotFoundException {

        if (terminal(board)){
            return utility(board);
        }

        int v = Integer.MAX_VALUE;
        String move = null;

        for (String action : actions(board)){
            Value<Integer, String> value = maxValue(result(board, action));
            if (value.value < v){
                v = value.value;
                move = action;
                if (v == -1) {
                    return new Value<Integer, String>(v, move);
                }
            }
        }
        return new Value<Integer, String>(v, move);
    }

    /**
     * source: https://stackoverflow.com/questions/64036/how-do-you-make-a-deep-copy-of-an-object
     */

    private Board result(Board board, String action) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(board);
        oos.flush();
        oos.close();
        bos.close();
        byte[] byteData = bos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
        Board tempBoard = (Board) new ObjectInputStream(bais).readObject();

        int selection = Integer.parseInt(action);
        int i = selection / 10;
        int j = selection % 10;
        tempBoard.getBoard()[i][j] = Player.getCurrentPlayer(board);

        return tempBoard;
    }

    public void performComputerMove(Board board) throws IOException, ClassNotFoundException {
        String computerMove = minimax(board);
        if (computerMove != null) {
            int move = Integer.parseInt(computerMove);
            int i = move / 10;
            int j = move % 10;
            board.getBoard()[i][j] = Player.getCurrentPlayer(board);
        }
    }

}
