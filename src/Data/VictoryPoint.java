package Data;

import Data.Enums.Colors;

/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class VictoryPoint {
    Data.Coord coord;
    Colors colors;

    public VictoryPoint(Data.Coord coord , Colors colors) {
        this.coord=coord;
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
