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

    public boolean equals(MoveCommand mc){
       if(this.colors == mc.getColor() && this.dir == mc.getDir()){
           return true;
       }else{
           return false;
       }
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
