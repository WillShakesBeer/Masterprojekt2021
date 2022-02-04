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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) ((Robot) o).getCoord();
        Colors color = (Colors) ((Robot) o).getColor();
        return this.colors.equals(color) && this.coord.equals(coord);
    }


}
