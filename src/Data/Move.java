package Data;

public class Move {
    Data.Board prevBoard;
    Data.Board nextBoard;
    public Move(Data.Board prevBoard, Data.Board nextBoard){
        this.prevBoard=prevBoard;
        this.nextBoard=nextBoard;
    }
}
