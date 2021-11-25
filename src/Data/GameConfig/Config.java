package Data.GameConfig;

import Data.Board;
import Data.Obstacle;
import Data.Robot;
import Data.VictorySpawn;
import Logic.Gamestate;

import java.util.ArrayList;

/**
 * Created by Martin Eberle aka WillShakesBeer on 25.11.2021.
 */
public class Config {
    Gamestate state;
    ArrayList<Obstacle> obstacleList;
    ArrayList<Robot> robotList;
    Board board;

    public Config(ArrayList<Robot> robotList, ArrayList<Obstacle> obstacleList,
                  ArrayList<VictorySpawn> victorySpawns, Board board) {
        this.obstacleList = obstacleList;
        this.robotList = robotList;
        this.board = board;
        state = new Gamestate(board,0);
    }

    public Gamestate getState() {
        return state;
    }

    public void setState(Gamestate state) {
        this.state = state;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
