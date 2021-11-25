package Data;

import Data.Enums.Color;

public class Robot {
    Data.Coord coord;
    Color color;

    public Robot(Data.Coord coord, Color color){
       this.coord = coord;
       this.color = color;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
