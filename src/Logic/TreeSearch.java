package Logic;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;

import java.util.ArrayList;
import java.util.Random;



//heuristic=0
//Uses Depth limited Depth first search
//heurisitc=1
//Uses Depth limited random first search
//heurisitc=2
//Uses Depth limited breadth first search

//Depthlimit is the limit of Moves that can be used in total
//Setuplimit is the limit of the Moves that can be used on robots from colors other than the victorycolor
public class TreeSearch extends Thread{

    //input Parameters
    Game game;
    int heuristic;
    int setupLimit;
    int depthLimit;

    //output
    MoveNode result=null;

    //Expandable nodes of the Main tree search
    ArrayList<MoveNode> toExplore;

    //Expandable nodes of the Setup tree search
    ArrayList<MoveNode> setupExplore;
    //Setupnode that will be used as root of main tree
    MoveNode setup;

    //colors that are not victory colors
    ArrayList<Colors> otherColors=new ArrayList<Colors>();


    public TreeSearch(Game game,int depthLimit, int setupLimit, int heuristic){
        this.game=game;
        this.depthLimit=depthLimit;
        this.setupLimit=setupLimit;
        this.heuristic=heuristic;

        MoveNode root = new MoveNode();
        this.setup=root;
        this.setupExplore=new ArrayList<MoveNode>();
        this.setupExplore.add(root);

    }

    @Override
    public void run() {
        MoveNode curr;
        Colors vicColor = this.game.getState().getBoard().getVictoryPoint().getColor();
        for(Colors currColor: Colors.values()){
            if(currColor!=vicColor){
                otherColors.add(currColor);
            }
        }
        while (!this.setupExplore.isEmpty() && !Thread.currentThread().isInterrupted()){
            toExplore=new ArrayList<MoveNode>();
            MoveNode setupCopy = new MoveNode();
            setupCopy.setMoveCommands(setup.getMoveCommands());
            toExplore.add(setupCopy);
            while (!toExplore.isEmpty()) {
                switch (this.heuristic) {
                    case 0:
                        curr = toExplore.get(0);
                        break;
                    case 1:
                        Random rand = new Random();
                        int newIndex = rand.nextInt(toExplore.size());
                        curr = toExplore.get(newIndex);
                        break;
                    case 2:
                        curr = toExplore.get(toExplore.size() - 1);
                        break;
                    case 3:
                        int minHValue=Integer.MAX_VALUE;
                        curr = toExplore.get(toExplore.size() - 1);
                        for(MoveNode currNode: toExplore){
                            if(currNode.getHValue()!=-1 && currNode.getHValue()<minHValue){
                                curr=currNode;
                                minHValue=curr.getHValue();
                            }
                        }
                        break;
                    default:
                        curr = toExplore.get(0);
                        break;
                }
                if (curr.getMoveCommands().size() > depthLimit) {
                    toExplore.remove(curr);
                } else {
                    int seqCheck = isSeqSmartVicColor(curr.getMoveCommands());
                    //cut out useless moves
                    //useless move = crash against wall
                    /*if (seqCheck == -2) {
                        //System.out.println("Useless Move");
                        toExplore.remove(curr);
                        if (!toExplore.isEmpty()) {
                            curr = toExplore.get(0);
                        }
                    }
                    if (seqCheck == 0) {
                        this.result = curr;
                        return;
                    }*/

                    if (seqCheck >= -1 || curr.getRoot()) {
                        for (Direction direction : Direction.values()) {
                            int newHValue=curr.getHValue();
                            curr.addChild(new MoveCommand(vicColor, direction),newHValue);
                            MoveNode recentC=curr.getChilds().get(curr.getChilds().size()-1);
                            int HInc=isSeqSmartVicColor(recentC.getMoveCommands());
                            recentC.setHValue(recentC.getHValue()+HInc);
                            if(HInc==0){
                                this.result=recentC;
                                return;
                            }
                            if(HInc>0){
                                toExplore.add(0, recentC);
                            }
                        }
                        toExplore.remove(curr);
                    }
                }

            }
            setup=createNewSetup();
        }
        this.result=null;
        return;
    }


    //created this function to solve multicolor problems
    //will only be called if the problem is not feasible with only one color in the respective depth limit
    //a setup is a move node with a Movecommand sequence enables the victorycolor to solve the problem
    public MoveNode createNewSetup(){
        MoveNode currSetup;
        switch (this.heuristic) {
            case 0:
                currSetup = setupExplore.get(0);
                break;
            case 1:
                Random rand = new Random();
                int newIndex = rand.nextInt(setupExplore.size());
                currSetup = setupExplore.get(newIndex);
                break;
            case 2:
                currSetup = setupExplore.get(setupExplore.size() - 1);
                break;
            case 3:
                currSetup = setupExplore.get(setupExplore.size() - 1);
                break;
            default:
                currSetup = setupExplore.get(0);
                break;
        }
        if(currSetup.getMoveCommands().size()>setupLimit){
            setupExplore.remove(currSetup);
        }else{
            for (int i = 0; i < otherColors.size(); i++) {
                Colors currColor = otherColors.get(i);
                for (Direction direction : Direction.values()) {
                    currSetup.addChild(new MoveCommand(currColor, direction));
                    int seqCheck = isSeqSmart(currSetup.getChilds().get(currSetup.getChilds().size() -1).getMoveCommands());
                    if (seqCheck == 0) {
                        setupExplore.add(0, currSetup.getChilds().get(currSetup.getChilds().size() - 1));
                    }
                }
            }
            setupExplore.remove(currSetup);
        }
        return currSetup;
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

    public int isSeqSmartVicColor(ArrayList<MoveCommand> moveCommands){
        if(moveCommands.size()==0){
            return -1;
        }
        Colors color=moveCommands.get(moveCommands.size()-1).getColor();
        for(int i =0;i<moveCommands.size();i++){
            if(game.checkMove(moveCommands.get(i))==1){
                game.resetGame();
                return 0;
            }
            int result = game.moveRobot(moveCommands.get(i));
            if(result==-1){
                game.resetGame();
                return -2;
            }

        }
        double vicDist=-1;
        ArrayList<Robot> robots = game.getState().getBoard().getRobots();
        Robot vicRobot=null;
        for(Robot robot: robots){
            if(robot.getColor()==color){
                vicRobot = robot;
            }
        }
        VictoryPoint vicPoint = this.game.getState().getBoard().getVictoryPoint();
        if(vicRobot!=null){
           double xDist= Math.pow((double) (vicRobot.getCoord().getX()-vicPoint.getCoord().getX()),2);
           double yDist= Math.pow((double) (vicRobot.getCoord().getY()-vicPoint.getCoord().getY()),2);
           vicDist = Math.sqrt(xDist+yDist);
        }
        game.resetGame();
        vicDist=vicDist+100;
        return (int)(vicDist);
    }
    public int distDiff(MoveNode node){
        ArrayList<MoveCommand> cmds = node.getMoveCommands();
        Colors color=cmds.get(cmds.size()-1).getColor();

        return 0;
    }

    public MoveNode getResult() {
        return result;
    }

    public void setResult(MoveNode result) {
        this.result = result;
    }

}
