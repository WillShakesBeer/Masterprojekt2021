package Data;

import Data.Enums.Color;

/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class VictoryPoint {
    Data.Coord coord;
    Color color;

    public VictoryPoint(Data.Coord coord , Color color) {
        this.coord=coord;
        this.color=color;

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
