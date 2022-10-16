package Logic;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.GameConfig.Config;

import java.util.ArrayList;
import java.util.Random;

// Controls the order of events in the Game

public class Game {

    // Gamestate keeps track of the temporary data during a Game
    Gamestate state;
    Config config;



    //starts a new Game
    public Game(Config config){
        this.config=config;
        VictoryPoint newVic = createFirstVictoryPoint();
        //newVic = new VictoryPoint(new Coord(12,2), Colors.YELLOW);
        newVic = config.getStartVic();
        //testPoint vor DLDF search(reachable within 5 moves)
        Board board = new Board(config.getLength(),config.getHeight(),config.getObstacleList(),config.getRobotList()
        ,config.getVictorySpawns(),newVic);
        state = new Gamestate(board,0);

    }

    //Moves Robot, returns 0 if movement successful
    //returns -1 if already facing an obstacle
    //return 1 if robot has collected the victorypoint
    public int moveRobot(MoveCommand cmd){
        Colors colors = cmd.getColor();
        Direction dir = cmd.getDir();
        int result = 0;
        Robot currRobot = this.state.getBoard().getRobot(colors);
        Coord startPos= currRobot.getCoord().clone();
        Coord newPos = checkMovement(startPos.clone(),dir,this.state.getBoard());
        if(startPos.equals(newPos)){
           result = -1;
        }else {
            result = 0;
            Move move = new Move(colors,startPos,newPos);
            this.state.addMove(move);
            currRobot.setCoord(newPos);
            if(newPos.equals(this.state.getBoard().getVictoryPoint().getCoord())
                    && colors.equals(this.state.getBoard().getVictoryPoint().getColor())) {
                this.state.setScore(this.state.getScore()+1);
                VictoryPoint newVic = createNewVictoryPoint();
                this.state.getBoard().setVictoryPoint(newVic);
                this.state.setMoveList(new ArrayList<Move>());
                return 1;
            }
        }
        return result;
    }

    //produces the same output as moveRobot without changing Board or State
    public int checkMove(MoveCommand cmd){
        Colors colors = cmd.getColor();
        Direction dir = cmd.getDir();
        int result = 0;
        Robot currRobot = this.state.getBoard().getRobot(colors);
        Coord startPos= currRobot.getCoord().clone();
        Coord newPos = checkMovement(startPos.clone(),dir,this.state.getBoard());
        if(startPos.equals(newPos)){
            result = -1;
        }else {
            result = 0;
            if(newPos.equals(this.state.getBoard().getVictoryPoint().getCoord())
                    && colors.equals(this.state.getBoard().getVictoryPoint().getColor())) {
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

    //reverts all moves done till movelist is Empty
    public void resetGame(){
        while(!this.state.getMoveList().isEmpty()){
            Move lastMove = this.state.moveList.get(this.state.getMoveList().size()-1);
            this.state.getBoard().getRobot(lastMove.getColor()).setCoord(lastMove.getPrevPos());
            this.state.getMoveList().remove(this.state.getMoveList().size()-1);
        }

    }

    //uninformed random VP selection
    public VictoryPoint createFirstVictoryPoint(){
        Random rand = new Random();
        int newIndex= rand.nextInt(this.getConfig().getVictorySpawns().size());
        VictorySpawn spawn = this.getConfig().getVictorySpawns().get(newIndex);
        VictoryPoint nextVic = new VictoryPoint(spawn.getCoord(),spawn.getColor());
        return nextVic;
    }

    //does not create returns the same Point as before
    //does not create a VP where a Robot is already standing
    public VictoryPoint createNewVictoryPoint(){
        Random rand = new Random();
        boolean vpValid=false;
        VictoryPoint nextVic=null;
        Coord oldCoord=this.state.getBoard().getVictoryPoint().getCoord();
        while(!vpValid){
            int newIndex= rand.nextInt(this.getConfig().getVictorySpawns().size());
            VictorySpawn spawn = this.getConfig().getVictorySpawns().get(newIndex);
            Coord newCoord = spawn.getCoord();
            if(!oldCoord.equals(newCoord)){
                nextVic = new VictoryPoint(spawn.getCoord(),spawn.getColor());
                vpValid=true;
            }
            for(Robot curr :this.getState().getBoard().getRobots()){
                if(curr.getCoord().equals(newCoord)){
                    vpValid=false;
                }
            }
        }

        return nextVic;
    }

    public void forceNewVictoryPoint(){
        this.state.getBoard().setVictoryPoint(createNewVictoryPoint());
    }

    //returns a possibly new Position after an executed movement
    public Coord checkMovement(Coord pos, Direction dir, Board board){
        Coord newPos=new Coord(pos.getX(),pos.getY());
        Boolean stoped = false;
        while (!stoped){
            switch (dir){
               case UP:
                   newPos.setY(newPos.getY()-1);
                   break;
               case DOWN:
                   newPos.setY(newPos.getY()+1);
                   break;
               case LEFT:
                   newPos.setX(newPos.getX()-1);
                   break;
               case RIGHT:
                   newPos.setX(newPos.getX()+1);
                   break;
           }
           stoped=!checkPos(newPos,board,dir);
           if(!stoped){
               pos.setX(newPos.getX());
               pos.setY(newPos.getY());
           }
        }
        return pos;
    }

    //checks if a position is reachable from a specific direction
    public boolean checkPos(Coord pos, Board board, Direction dir){
        boolean result = true;
        ArrayList<Obstacle> obstacles= board.getObstacles();
        ArrayList<Robot> robots = board.getRobots();
        for(Robot robot :robots){
            if(robot.getCoord().equals(pos)){
                result=false;
            }
        }

        if(pos.getX()<0 || pos.getX()>board.getLength() || pos.getY()<0 || pos.getY()>board.getHeight()){
            result=false;
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
                case DOWN: case RIGHT:
                    if(pos.equals(obstacle.getCoord2())){
                        result=false;
                    }
                    break;
                case UP: case LEFT:
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

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }



}
