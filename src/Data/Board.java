package Data;
import java.util.ArrayList;

/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class Board {
    int laenge;
    int breite;

    ArrayList<Obstacle> obstacles;
    ArrayList<Robot> robots;


    public Board(int laenge, int breite , ArrayList<Obstacle> obstacles , ArrayList<Robot> robots){
        this.laenge = laenge;
        this.breite = breite;
        this.obstacles = obstacles;
        this.robots = robots;
    }

    public Robot getRobot(Color color){
       for(Robot robot : robots){
           if(robot.getColor().equals(color)){
               return robot;
           }
       }
       return null;
    }

    public ArrayList<Obstacle> getHorizontalObstacles(){
        ArrayList<Obstacle> horizontals = new ArrayList<Obstacle>();
        for(Obstacle obstacle : obstacles){
            if(obstacle.getType().equals(ObsType.HORIZONTAL)){
               horizontals.add(obstacle);
            }
        }
        return horizontals;
    }

    public ArrayList<Obstacle> getVerticalObstacles(){
        ArrayList<Obstacle> verticals = new ArrayList<Obstacle>();
        for(Obstacle obstacle : obstacles){
            if(obstacle.getType().equals(ObsType.VERTICAL)){
                verticals.add(obstacle);
            }
        }
        return verticals;
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

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

}
