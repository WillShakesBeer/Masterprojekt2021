package Data;

import Data.Enums.Colors;

public class VictorySpawn {
    Colors colors;
    Coord coord;

    public VictorySpawn(Coord coord, Colors colors){
        this.colors = colors;
        this.coord = coord;
    }

    public Colors getColor() {
        return colors;
    }

    public void setColor(Colors colors) {
        this.colors = colors;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
}
