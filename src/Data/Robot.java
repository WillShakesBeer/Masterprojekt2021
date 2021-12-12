package Data;

import Data.Enums.Colors;

public class Robot {
    Data.Coord coord;
    Colors colors;

    public Robot(Data.Coord coord, Colors colors){
       this.coord = coord;
       this.colors = colors;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Colors getColor() {
        return colors;
    }

    public void setColor(Colors colors) {
        this.colors = colors;
    }


}
