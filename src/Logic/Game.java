package Logic;

import Data.*;
import Data.Enums.Color;
import Data.Enums.Direction;

import java.util.ArrayList;
import java.util.Random;

// Controls the order of events in the Game

public class Game {

    // Gamestate keeps track of the temporary data during a Game
    Gamestate state;
    ArrayList<Obstacle> obstacleList;
    ArrayList<Robot> robotList;

    //starts a new Game
    public Game(){
        //Standard board parameters for testing
        this.obstacleList = obstacleList;
        this.robotList = robotList;
        Data.Board board = new Board(16,16,obstacleList,robotList,victorySpawns,victoryPoint);
        state = new Gamestate(board,0);

    }

    //Moves Robot, returns 0 if movement successful
    //returns -1 if already facing an obstacle
    //return 1 if robot has collected the victorypoint
    public int moveRobot(Color color, Direction dir){
        int result =0;
        Robot currRobot = this.state.getBoard().getRobot(color);
        Coord currpos = currRobot.getCoord();
        Coord newPos = checkMovement(currpos,dir,this.state.getBoard());
        if(currpos.equals(newPos)){
           result = -1;
        }else {
            Move move = new Move(color,currpos,newPos);
            this.state.addMove(move);
            currRobot.setCoord(newPos);
            if(newPos.equals(this.state.getBoard().getVictorypoint().getCoord())
                    && color.equals(this.state.getBoard().getVictorypoint().getColor())) {
                this.state.setScore(this.state.getScore()+1);
                this.createNewVictoryPoint();
                this.state.setMoveList(new ArrayList<Move>());
                return 1;
            }
        }
        return result;
    }

    //Reverts the effect of the last move in Board.moveList
    //Also deletes the move from the list
    //returns 0 if successful
    //returns -1 if moveList is empty
    public int revertMove(){
        if(!this.state.getMoveList().isEmpty()){
            Move lastMove = this.state.moveList.get(this.state.getMoveList().size()-1);
            this.state.getBoard().getRobot(lastMove.getColor()).setCoord(lastMove.getPrevPos());
            this.state.getMoveList().remove(this.state.getMoveList().size()-1);
            return 0;
        }

        return -1;
    }

    public void createNewVictoryPoint(){
        Random rand = new Random();
        int newIndex= rand.nextInt(this.state.getBoard().getVictorySpawns().size());
        VictorySpawn spawn = this.state.getBoard().getVictorySpawns().get(newIndex);
        VictoryPoint nextVic = new VictoryPoint(spawn.getCoord(),spawn.getColor());
        this.state.board.setVictorypoint(nextVic);
    }

    //returns a possibly new Position after an executed movement
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

    //checks if a position is reachable from a specific direction
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
}
