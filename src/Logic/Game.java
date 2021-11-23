package Logic;

import Data.*;

import java.util.ArrayList;

public class Game {
    Gamestate state;
    public Game(){
        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Robot(new Coord(0,0),Color.RED));
        robotList.add(new Robot(new Coord(0,16),Color.BLUE));
        robotList.add(new Robot(new Coord(16,0),Color.YELLOW));
        robotList.add(new Robot(new Coord(16,16),Color.GREEN));
        Data.Board board = new Board(16,16,obstacleList,robotList);
        state = new Gamestate(board,0,0);
    }

}
