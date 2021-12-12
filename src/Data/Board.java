package Data;
import Data.Enums.Colors;
import Data.Enums.ObsType;

import java.util.ArrayList;

/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class Board {
    int length;
    int height;

    ArrayList<VictorySpawn> victorySpawns;
    VictoryPoint victoryPoint;

    ArrayList<Obstacle> obstacles;
    ArrayList<Robot> robots;


    public Board(int length, int height , ArrayList<Obstacle> obstacles ,
                 ArrayList<Robot> robots, ArrayList<VictorySpawn> victorySpawns, VictoryPoint victoryPoint){
        this.victorySpawns=victorySpawns;
        this.length = length;
        this.height = height;
        this.obstacles = obstacles;
        this.robots = robots;
        this.victoryPoint=victoryPoint;
    }

    public Robot getRobot(Colors colors){
       for(Robot robot : robots){
           if(robot.getColor().equals(colors)){
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
    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }


    //setter
    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public ArrayList<VictorySpawn> getVictorySpawns() {
        return victorySpawns;
    }

    public void setVictorySpawns(ArrayList<VictorySpawn> victorySpawns) {
        this.victorySpawns = victorySpawns;
    }

    public VictoryPoint getVictoryPoint() {
        return victoryPoint;
    }

    public void setVictoryPoint(VictoryPoint victoryPoint) {
        this.victoryPoint = victoryPoint;
    }
}
