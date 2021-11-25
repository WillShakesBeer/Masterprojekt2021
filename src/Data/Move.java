package Data;
import Data.Enums.*;


public class Move {
    //color of the moved Robot
    Color color;
    //pos of the Robot before movement
    Coord prevPos;
    //pos of the Robot after movement
    Coord nextPos;
    public Move(Color color, Coord prevPos, Coord nextPos){
        this.color=color;
        this.prevPos=prevPos;
        this.nextPos=nextPos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Coord getPrevPos() {
        return prevPos;
    }

    public void setPrevPos(Coord prevPos) {
        this.prevPos = prevPos;
    }

    public Coord getNextPos() {
        return nextPos;
    }

    public void setNextPos(Coord nextPos) {
        this.nextPos = nextPos;
    }
}
