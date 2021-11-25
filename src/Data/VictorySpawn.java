package Data;

import Data.Enums.Color;

public class VictorySpawn {
    Color color;
    Coord coord;

    public VictorySpawn(Coord coord, Color color){
        this.color = color;
        this.coord = coord;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
}
