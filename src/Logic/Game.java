package Logic;

import Data.*;
import Data.Enums.Color;
import Data.Enums.Direction;

import java.util.ArrayList;

// Controls the order of events in the Game

public class Game {

    // Gamestate keeps track of the temporary data during a Game
    Gamestate state;


    //starts a new Game
    public Game(){
        //Standard board parameters for testing
        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Robot(new Coord(0,0), Color.RED));
        robotList.add(new Robot(new Coord(0,16),Color.BLUE));
        robotList.add(new Robot(new Coord(16,0),Color.YELLOW));
        robotList.add(new Robot(new Coord(16,16),Color.GREEN));
        Data.Board board = new Board(16,16,obstacleList,robotList);
        state = new Gamestate(board,0);

    }

    //Moves Robot, returns 0 if movement successful
    //returns -1 if already facing an obstacle
    public int moveRobot(Color color, Direction dir){
        int result =0;
        Robot currRobot = this.state.getBoard().getRobot(color);
        Coord currpos = currRobot.getCoord();
        Coord newPos = checkMovement(currpos,dir,this.state.getBoard());
        if(currpos.equals(newPos)){
           result = -1;
        }else {
            //oldBoard=this.state.getBoard()
            currRobot.setCoord(newPos);
        }
        return result;
    }

    public Coord checkMovement(Coord pos, Direction dir, Board board){
        Coord newPos=pos;
        Boolean stoped = false;
        while (!stoped){
           switch (dir){
               case UP:
                   newPos.setY((newPos.getY())+1);
                   break;
               case DOWN:
                   newPos.setY((newPos.getY())-1);
                   break;
               case LEFT:
                   newPos.setX((newPos.getX())-1);
                   break;
               case RIGHT:
                   newPos.setX((newPos.getX())+1);
                   break;
           }
           stoped=checkPos(newPos,board,dir);
           pos=newPos;
        }
        return pos;
    }

    public boolean checkPos(Coord pos, Board board, Direction dir){
        boolean result = true;
        ArrayList<Obstacle> obstacles=null;
        ArrayList<Robot> robots = board.getRobots();
        for(Robot robot :robots){
            if(robot.getCoord().equals(pos)){
                result=false;
            }
        }
        switch (dir){
            case UP: case DOWN:
                obstacles= board.getVerticalObstacles();
                break;
            case LEFT: case RIGHT:
                obstacles= board.getHorizontalObstacles();
                break;
        }
        for(Obstacle obstacle:obstacles){
            switch (dir){
                case UP: case RIGHT:
                    if(pos.equals(obstacle.getCoord2())){
                        result=false;
                    }
                    break;
                case DOWN: case LEFT:
                    if(pos.equals(obstacle.getCoord1())){
                        result=false;
                    }
                    break;
            }

        }
        return result;
    }

}
