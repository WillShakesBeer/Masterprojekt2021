package Logic;

import Data.Board;
import Data.Move;

import java.util.ArrayList;

// Keeps track of temporary data during a game
public class Gamestate {
    //Lists all moves of the current Turn
    ArrayList<Data.Move> moveList;
    //Amount of gained victorypoints
    int score;
    Data.Board board;
    public Gamestate(Data.Board board, int score){
        this.moveList=new ArrayList<Data.Move>();
        this.board=board;
        this.score=score;
    }

    public void addMove(Move move){
        this.moveList.add(move);
    }

    public ArrayList<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(ArrayList<Move> moveList) {
        this.moveList = moveList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
