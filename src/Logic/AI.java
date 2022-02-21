package Logic;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

// An AI that play on a given Game object
public class AI {

    private Game game;
    private MoveNode root;

    public AI(Game game){
        this.game = game;
        root = new MoveNode();
    }

    //test TreeSearch
    //tryMore Colors has some bugs
    public MoveNode createSeq(){
        TreeSearch ts =new TreeSearch(game,11,4,3);
        Thread t = new Thread(ts);
        Timer timer = new Timer();
        System.out.println("Start Treesearch");
        //timer.schedule(new TimeOutTask(t, timer,this.game), 5*1000);
        t.start();
        MoveNode result;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result=ts.getResult();
        if (result == null) {
            return new MoveNode();
        } else {
            //result.setMoveCommands(clearCycles(result.getMoveCommands()));
            return result;
        }
    }

    //Input List of Movecommands with possible cycles
    //Output List of Movecommands that does not contain cycles
    //Cycle is defined as sequence of Movecommands that do not change the robots position if they were executed
    //Probably to Complex to be used inside of Treesearch
    //Can optimize some non-optimal Solutions
    //Does not guarantee an optimal Solution
    public ArrayList<MoveCommand> clearCycles(ArrayList<MoveCommand> moveCommands){
        ArrayList<ArrayList<Robot>> robotListList= new ArrayList<ArrayList<Robot>>();
        ArrayList<Robot> currRobots =this.game.getState().getBoard().getRobots();
        ArrayList<Robot> currRobotsCopy = new ArrayList<Robot>();
        for(Robot curr :currRobots){
           currRobotsCopy.add(new Robot(curr.getCoord(),curr.getColor()));
        }
        robotListList.add(currRobotsCopy);
        for(int x = 0;x<moveCommands.size();x++){
            MoveCommand cmd = moveCommands.get(x);
            if(game.checkMove(cmd)!=1){
                game.moveRobot(cmd);
            }else{
                //if the command collect VP exit
                game.resetGame();
                return moveCommands;
            }
            boolean hasCycle=false;
            boolean cycleHere=false;
            int startOfCycle=-1;
            //Search whether the current board positions were already visited
            for(int j =0;j<robotListList.size();j++){
                ArrayList<Robot> robots = robotListList.get(j);
                cycleHere=true;
                for(int i=0;i<robots.size();i++){
                    Robot robot = robots.get(i);
                    if(!robot.equals(currRobots.get(i))){
                        cycleHere=false;
                    }
                }
                if(cycleHere){
                    if(startOfCycle==-1 || startOfCycle > j){
                        startOfCycle=j;
                    }
                    hasCycle=true;
                }
            }

            //Delete Cycle
            if(hasCycle){
                for(int j=x;j>=startOfCycle;j--){
                    moveCommands.remove(j);
                    if(j>=1){
                        robotListList.remove(j);
                    }
                    x=startOfCycle-1;
                    game.resetGame();
                }
                hasCycle=false;
            }
            //No Cycle detected add new Board positions to the list
            else {
                currRobotsCopy = new ArrayList<Robot>();
                for(Robot curr :currRobots){
                    currRobotsCopy.add(new Robot(curr.getCoord(),curr.getColor()));
                }
                robotListList.add(currRobotsCopy);
            }
        }
        //will only be reached if the Movecommands do not collect the VP
        game.resetGame();
        return moveCommands;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
