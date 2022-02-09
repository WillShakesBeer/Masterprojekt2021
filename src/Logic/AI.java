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
    private ArrayList<Colors> onecolor;
    private ArrayList<Colors> allcolors;
    private ArrayList<Colors> testcolors;

    public AI(Game game){
        this.game = game;
        root = new MoveNode();
        onecolor=new ArrayList<Colors>();
        onecolor.add(Colors.BLUE);
        allcolors=new ArrayList<Colors>();
        allcolors.add(Colors.BLUE);
        allcolors.add(Colors.RED);
        allcolors.add(Colors.YELLOW);
        allcolors.add(Colors.GREEN);
        testcolors=new ArrayList<Colors>();
        testcolors.add(Colors.GREEN);
        testcolors.add(Colors.RED);
    }

    //test TreeSearch
    //tryMore Colors has some bugs
    public MoveNode createSeq(){
        ArrayList<MoveNode> toExplore = new ArrayList<MoveNode>();
        toExplore=new ArrayList<MoveNode>();
        toExplore.add(root);
        TreeSearch ts =new TreeSearch(game,20,toExplore,2,onecolor);
        Thread t = new Thread(ts);
        Timer timer = new Timer();
        timer.schedule(new TimeOutTask(t, timer,this.game), 5*1000);
        t.start();
        MoveNode result;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result=ts.getResult();
        if (result == null) {
            System.out.println("try more colors");
            ts =new TreeSearch(game,11,toExplore,1,allcolors);
            t = new Thread(ts);
             timer = new Timer();
            timer.schedule(new TimeOutTask(t, timer,this.game), 120*1000);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result=ts.getResult();
            if(result==null){

                return new MoveNode();
            }else{
                return result;
            }
        } else {
            //result.setMoveCommands(clearCycles(result.getMoveCommands()));
            return result;
        }
    }

    //deprecated
    //heuristic=0
    //Uses Depth limited Depth first search
    //heurisitc=1
    //Uses Depth limited random first search
    //heurisitc=2
    //Uses Depth limited breadth first search
    public MoveNode treeSearch(int depthLimit, ArrayList<MoveNode> toExplore, int heuristic, int colorsUsed){
        MoveNode curr = toExplore.get(0);
        Colors vicColor=game.getState().getBoard().getVictoryPoint().getColor();
        Colors color = vicColor;
        while(!toExplore.isEmpty()) {
            switch (heuristic){
                case 0:
                    curr=toExplore.get(0);
                    break;
                case 1:
                    Random rand = new Random();
                    int newIndex = rand.nextInt(toExplore.size());
                    curr=toExplore.get(newIndex);
                    break;
                case 2:
                    curr=toExplore.get(toExplore.size()-1);
                    break;
            }
            if(curr.getMoveCommands().size()>depthLimit){
                toExplore.remove(curr);
            }else {
                int seqCheck = isSeqSmart(curr.getMoveCommands());
                if (seqCheck==0 || curr.getRoot()) {
                    //System.out.println("Expand further");
                    if(colorsUsed==1) {
                        for (Direction direction : Direction.values()) {

                            curr.addChild(new MoveCommand(color, direction));
                            toExplore.add(0, curr.getChilds().get(curr.getChilds().size() - 1));
                        }
                    }else{
                        for(int i=0;i<colorsUsed;i++){
                            Colors currColor = Colors.values()[i];
                            for (Direction direction : Direction.values()) {

                                curr.addChild(new MoveCommand(currColor, direction));
                                toExplore.add(0, curr.getChilds().get(curr.getChilds().size() - 1));
                            }
                        }
                    }
                    toExplore.remove(curr);
                }
                //cut out useless moves
                //useless move = crash against wall
                if (seqCheck==-1) {
                    //System.out.println("Useless Move");
                    toExplore.remove(curr);
                    if(!toExplore.isEmpty()){
                        curr = toExplore.get(0);
                    }
                }
                if(seqCheck==1){
                    return curr;
                }
            }

        }
        return null;
    }

    //checks if a Seq contains moves that end up crashing a Wall
    //0 => no crash
    //-1 => crash
    //1 => Point Scored
    public int isSeqSmart(ArrayList<MoveCommand> moveCommands){
        for(int i =0;i<moveCommands.size();i++){
            if(game.checkMove(moveCommands.get(i))==1){
                game.resetGame();
                return 1;
            }
            int result = game.moveRobot(moveCommands.get(i));
            if(result==-1){
                game.resetGame();
                return -1;
            }
        }
        game.resetGame();
        return 0;
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


    /*
    //sorts out moves that doesnt move the robot, e.g if he would hit a wall
    public void doSmartStuff(){
        int counter = 100;
        while (counter<100){



            counter++;
        }

    }

    //checks if the move is ending up in a loop or if the robot is hitting a wall
    public boolean isMoveSmart (){
        boolean isSmart = true;

        //checks if we hit a wall
        view.isCrashWall();

        //checks if we run a loop
        view.getGame().getState().getMoveList();

        return isSmart;

    }*/

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
