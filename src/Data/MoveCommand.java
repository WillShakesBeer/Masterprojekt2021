package Data;

import Data.Enums.Color;
import Data.Enums.Direction;


//Used for Userinput
//MoveCommand can be input in Game.moveRobot and may not result in a sucessful Move
public class MoveCommand {
    Color color;
    Direction dir;


    public MoveCommand(Color color, Direction dir){
        this.color = color;
        this.dir = dir;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

}
