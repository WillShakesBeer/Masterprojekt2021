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
    private TreeSearch treeSearch;
    private int selectedVicHeuristic;
    private int selectedSetupHeuristic;
    private int depthLimit;
    private int setupLimit;
    private int maxDegree;
    private boolean interrupted;


    public AI(Game game){
        this.game = game;
        root = new MoveNode();
    }

    public void setAiDefaults() {
        setupLimit=6;
        depthLimit = 11;
        selectedVicHeuristic = 4;
        selectedSetupHeuristic = 4;
        maxDegree=10;
    }

    public MoveNode createSeq(){
        treeSearch =new TreeSearch(game,depthLimit,setupLimit,selectedVicHeuristic,selectedSetupHeuristic,maxDegree);
        Thread t = new Thread(treeSearch);
        Timer timer = new Timer();
        System.out.println("Start Treesearch");
        long startTime = System.currentTimeMillis();
        interrupted=false;
        //timer.schedule(new TimeOutTask(t, timer,this.game), 5*1000);
        t.start();
        MoveNode result;
        int timeLimit=30000;
        //20000
        try {
            while (System.currentTimeMillis()< (startTime+timeLimit) && t.isAlive()){
            }
            if(t.isAlive()){
                treeSearch.stopSearch();
            }
            if(treeSearch.getInterrupt()){
                interrupted=true;
                System.out.println("Timeout");
            }else{
                interrupted=false;
            }
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result=treeSearch.getResult();
        if (result == null) {
            return new MoveNode();
        } else {
            long stopTime = System.currentTimeMillis();
            long timeUsed=(stopTime-startTime)/(long)(1000);
            System.out.println("Time used "+(timeUsed)+" seconds");
            //result.setMoveCommands(clearCycles(result.getMoveCommands()));
            return result;
        }
    }

    //Input List of Move commands with possible cycles
    //Output List of Move commands that does not contain cycles
    //Cycle is defined as sequence of Move commands that do not change the robots position if they were executed
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

    public TreeSearch getTreeSearch() {
        return treeSearch;
    }

    public void setTreeSearch(TreeSearch treeSearch) {
        this.treeSearch = treeSearch;
    }


    public int getDepthLimit() {
        return depthLimit;
    }

    public void setDepthLimit(int depthLimit) {
        this.depthLimit = depthLimit;
    }

    public int getSetupLimit() {
        return setupLimit;
    }

    public int getSelectedVicAlgorithm() {
        return selectedVicHeuristic;
    }

    public void setSelectedVicAlgorithm(int selectedVicHeuristic) {
        this.selectedVicHeuristic = selectedVicHeuristic;
    }

    public int getSelectedSetupHeuristic() {
        return selectedSetupHeuristic;
    }

    public void setSelectedSetupHeuristic(int selectedSetupHeuristic) {
        this.selectedSetupHeuristic = selectedSetupHeuristic;
    }

    public void setSetupLimit(int setupLimit) {
        this.setupLimit = setupLimit;
    }

    public boolean isInterrupted() {
        return interrupted;
    }

    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }
}
