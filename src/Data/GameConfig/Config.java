package Data.GameConfig;

import Data.*;
import Logic.Gamestate;

import java.util.ArrayList;

/**
 * Created by Martin Eberle aka WillShakesBeer on 25.11.2021.
 */
public class Config {
    ArrayList<Obstacle> obstacleList;
    ArrayList<Robot> robotList;
    ArrayList<VictorySpawn> victorySpawns;
    VictoryPoint startVic;
    int length;
    int height;

    public Config(ArrayList<Robot> robotList, ArrayList<Obstacle> obstacleList,
                  ArrayList<VictorySpawn> victorySpawns, int length,int height,VictoryPoint startVic) {
        this.victorySpawns = victorySpawns;
        this.obstacleList = obstacleList;
        this.robotList = robotList;
        this.length=length;
        this.height=height;
        this.startVic=startVic;
    }

    public ArrayList<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public void setObstacleList(ArrayList<Obstacle> obstacleList) {
        this.obstacleList = obstacleList;
    }

    public ArrayList<Robot> getRobotList() {
        return robotList;
    }

    public void setRobotList(ArrayList<Robot> robotList) {
        this.robotList = robotList;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<VictorySpawn> getVictorySpawns() {
        return victorySpawns;
    }

    public void setVictorySpawns(ArrayList<VictorySpawn> victorySpawns) {
        this.victorySpawns = victorySpawns;
    }

    public VictoryPoint getStartVic() {
        return startVic;
    }

    public void setStartVic(VictoryPoint startVic) {
        this.startVic = startVic;
    }
}
