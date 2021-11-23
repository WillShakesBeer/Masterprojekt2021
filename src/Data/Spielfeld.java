package Data;

import java.util.ArrayList;

/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class Spielfeld {
    int laenge;
    int breite;
    ArrayList<Obstacle> obstacles;
    ArrayList<Robot> robots;


    public Spielfeld(int laenge, int breite , ArrayList<Obstacle> obstacles , ArrayList<Robot> robots){
        this.laenge = laenge;
        this.breite = breite;
        this.obstacles = obstacles;
        this.robots = robots;
    }


    




    //getter
    public int getLaenge() {
        return laenge;
    }

    public int getBreite() {
        return breite;
    }


    //setter
    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }
}
