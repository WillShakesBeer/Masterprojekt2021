package Logic;

import java.util.ArrayList;

public class Gamestate {
    ArrayList<Data.Move> moveList;
    int move;
    int score;
    Data.Board board;
    public Gamestate(Data.Board board, int score, int move){
        this.board=board;
        this.score=score;
        this.move=move;
    }
}
