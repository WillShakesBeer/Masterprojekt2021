package Data;

public class CrossBlockPos {

    Coord coord;
    int degree;
    int movesNeeded;

    public CrossBlockPos(Coord coord, int degree, int movesNeeded){
       this.coord=coord;
       this.degree=degree;
       this.movesNeeded=movesNeeded;
    }

    public Boolean equalsCoord(CrossBlockPos compare){
       return this.coord.equals(compare.getCoord());
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getMovesNeeded() {
        return movesNeeded;
    }

    public void setMovesNeeded(int movesNeeded) {
        this.movesNeeded = movesNeeded;
    }
}
