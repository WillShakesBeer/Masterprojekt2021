package Data;
import Data.Enums.*;


public class Move {
    //color of the moved Robot
    Colors colors;
    //pos of the Robot before movement
    Coord prevPos;
    //pos of the Robot after movement
    Coord nextPos;
    public Move(Colors colors, Coord prevPos, Coord nextPos){
        this.colors = colors;
        this.prevPos=prevPos;
        this.nextPos=nextPos;
    }

    public Colors getColor() {
        return colors;
    }

    public void setColor(Colors colors) {
        this.colors = colors;
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
