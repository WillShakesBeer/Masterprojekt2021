package Data;

import Data.Enums.Colors;
import Data.Enums.Direction;


//Used for Userinput
//MoveCommand can be input in Game.moveRobot and may not result in a sucessful Move
public class MoveCommand {
    Colors colors;
    Direction dir;


    public MoveCommand(Colors colors, Direction dir){
        this.colors = colors;
        this.dir = dir;
    }

    public Colors getColor() {
        return colors;
    }

    public void setColor(Colors colors) {
        this.colors = colors;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

}
