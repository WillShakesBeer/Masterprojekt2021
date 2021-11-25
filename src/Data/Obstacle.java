package Data;


import Data.Enums.ObsType;

public class Obstacle {
    //An obstacle is defined by the two adjacent positions
    //The type describes if the adjacent positions are horizontal or vertical to each other
    //For horizontal obstacles coord1 is the left one and coord2 is the right one
    //For vertical obstacles coord1 is the lower one and coord2 is the upper one
    ObsType type;
    Data.Coord coord1;
    Data.Coord coord2;
    public Obstacle(Data.Coord coord1, Data.Coord coord2, ObsType type){
        this.coord1=coord1;
        this.coord2=coord2;
        this.type=type;
    }

    public ObsType getType() {
        return type;
    }

    public void setType(ObsType type) {
        this.type = type;
    }

    public Coord getCoord1() {
        return coord1;
    }

    public void setCoord1(Coord coord1) {
        this.coord1 = coord1;
    }

    public Coord getCoord2() {
        return coord2;
    }

    public void setCoord2(Coord coord2) {
        this.coord2 = coord2;
    }
}
