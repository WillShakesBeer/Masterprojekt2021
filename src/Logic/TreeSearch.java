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
//heurisitc=3
//Uses air distance to the VictoryPoint for PrimarySearch
//PosScore Heuristic for SetupSearch
//heuristic=4
//Depth limited breadth first search VicColor
//PosScore Heuristic for SetupSearch

//Depthlimit is the limit of Moves that can be used in total
//Setuplimit is the limit of the Moves that can be used on robots from colors other than the victorycolor
public class TreeSearch extends Thread{

    //input Parameters
    Game game;
    int selectedVicHeuristic;
    int selectedSetupHeuristic;
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

    Coord currVicCoord;

    ArrayList<ArrayList<Coord>> critPositions;

    int maxDegree=3;


    public TreeSearch(Game game,int depthLimit, int setupLimit, int selectedHeuristic,int selectedSetupHeuristic){
        this.game=game;
        this.depthLimit=depthLimit;
        this.setupLimit=setupLimit;
        this.selectedVicHeuristic = selectedHeuristic;
        this.selectedSetupHeuristic= selectedSetupHeuristic;

        MoveNode root = new MoveNode();
        this.setup=root;
        this.setupExplore=new ArrayList<MoveNode>();
        this.setupExplore.add(root);
        currVicCoord=this.game.getState().getBoard().getVictoryPoint().getCoord();
        if(this.selectedSetupHeuristic==3 || this.selectedSetupHeuristic==4){
             this.critPositions=getCriticalPositions(maxDegree);
        }

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
                switch (this.selectedVicHeuristic) {
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
                        //selects Node with minHValue(shortest distance to VP)
                        float minHValue=Integer.MAX_VALUE;
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
                    for (Direction direction : Direction.values()) {
                        float newHValue=curr.getHValue();
                        curr.addChild(new MoveCommand(vicColor, direction),newHValue);
                        MoveNode recentC=curr.getChilds().get(curr.getChilds().size()-1);
                        int HInc;
                        switch (this.selectedVicHeuristic){
                            case 3:
                                HInc=isSeqSmartVicColor(recentC.getMoveCommands());
                                break;
                            default:
                                HInc=isSeqSmart(recentC.getMoveCommands());
                                break;
                        }
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
            System.out.println("new Setup needed");
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
        switch (this.selectedSetupHeuristic) {
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
                //selects Node with highstHValue(Best Setup Score)
                float maxHValue=0;
                currSetup = setupExplore.get(setupExplore.size() - 1);
                for(MoveNode currNode: setupExplore){
                    if(currNode.getHValue()>maxHValue){
                        currSetup=currNode;
                        maxHValue=currSetup.getHValue();
                    }
                }
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
                    MoveNode recentC=currSetup.getChilds().get(currSetup.getChilds().size()-1);
                    int seqCheck = isSeqSmart(recentC.getMoveCommands());
                    if(this.selectedSetupHeuristic==3){
                        float HValue=isSeqSmartSetup(recentC.getMoveCommands());
                        recentC.setHValue(HValue);
                    }
                    if (seqCheck >=0) {
                        setupExplore.add(0, currSetup.getChilds().get(currSetup.getChilds().size() - 1));
                    }
                }
            }
            setupExplore.remove(currSetup);
        }
        return currSetup;
    }

    //checks if a Seq contains moves that end up crashing a Wall
    //1 => no crash
    //-1 => crash
    //0 => Point Scored
    public int isSeqSmart(ArrayList<MoveCommand> moveCommands){
        for(int i =0;i<moveCommands.size();i++){
            if(game.checkMove(moveCommands.get(i))==1){
                game.resetGame();
                return 0;
            }
            int result = game.moveRobot(moveCommands.get(i));
            if(result==-1){
                game.resetGame();
                return -1;
            }
        }
        game.resetGame();
        return 1;
    }

    // Gives feedback if Seq leads to achieving a Victorypoint
    //0=> Victory achieved
    //-1 => No Movement
    //-2 => Crash Wall
    //positive Int: AmountOfMoves*100+DistToVictoryPoint
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


    // Gives feedback if Seq leads to a meaningful Setup
    //-1 => No Movement
    //-2 => Crash Wall
    //positive Float: PositionScore/AmountOfMoves
    //PositionScore := CritcalPostionsTaken/CriticalDegree
    //Does only Consider cirticalDegree of a maximum of 3
    public float isSeqSmartSetup(ArrayList<MoveCommand> moveCommands){
        float posScore=0;
        float setupScore;
        int maxDegree=3;
        if(moveCommands.size()==0){
            return -1;
        }
        Colors color=moveCommands.get(moveCommands.size()-1).getColor();
        for(int i =0;i<moveCommands.size();i++){
            int result = game.moveRobot(moveCommands.get(i));
            if(result==-1){
                game.resetGame();
                return -2;
            }

        }
        ArrayList<Robot> robots=this.game.getState().getBoard().getRobots();
        for(Robot currRob:robots){
            Coord currCoord=currRob.getCoord();
            int lowestDegree=-1;
            for(int i=1;i<maxDegree;i++){
                ArrayList<Coord> currCritPosList=critPositions.get(i);
                for(Coord currCritPos: currCritPosList){
                    if(currCritPos.equals(currCoord)){
                       if(lowestDegree==-1 || lowestDegree>i){
                           lowestDegree=i;
                       }
                    }
                }
            }
            if(lowestDegree!=-1){
                posScore=posScore+(1/lowestDegree);
            }
        }
        game.resetGame();
        setupScore=posScore/moveCommands.size();
        return setupScore;
    }

    //returns the surrounding Positions of the Paths to the current VictoryPoint
    //The Degree of a critical Position is defined by the amount of turns the Path
    //Degree0 being the VictoryPoint only
    //to the VictoryPoint has
    //returns a list of list
    //Element 0 => Critical Positions of Degree 0
    //Element 1 => Critical Positions of Degree 1
    //...
    public ArrayList<ArrayList<Coord>> getCriticalPositions(int degree){
        ArrayList<Coord> startPos = new ArrayList<Coord>();
        ArrayList<ArrayList<Coord>> criticalPositions = new ArrayList<ArrayList<Coord>>();
        ArrayList<Coord> degree0 = new ArrayList<Coord>();
        degree0.add(currVicCoord);
        criticalPositions.add(degree0);
        ArrayList<Coord> oldStartPos = new ArrayList<Coord>();
        ArrayList<Coord> newStartPos = new ArrayList<Coord>();
        newStartPos.add(currVicCoord);
        while (degree > 0){
            startPos=(ArrayList<Coord>)newStartPos.clone();
            newStartPos = new ArrayList<Coord>();
            ArrayList<Coord> newCritPos = new ArrayList<Coord>();
            criticalPositions.add(newCritPos);
            while(!startPos.isEmpty()) {
                Coord currPos = startPos.get(startPos.size() - 1);
                oldStartPos.add(currPos);
                startPos.remove(currPos);
                for (Direction dir : Direction.values()) {
                    Coord collisionPos = game.checkMovement(currPos.clone(), dir, game.getState().getBoard());
                    boolean collisionPosSmart = true;
                    for (Coord checkPos : startPos) {
                        if (collisionPos.equals(checkPos)) {
                            collisionPosSmart = false;
                        }
                    }
                    for (Coord checkPos : oldStartPos) {
                        if (collisionPos.equals(checkPos)) {
                            collisionPosSmart = false;
                        }
                    }
                    if (collisionPosSmart) {
                        newStartPos.add(collisionPos);
                        switch (dir) {
                            case DOWN:
                                for (int y = currPos.getY(); y <= collisionPos.getY(); y++) {
                                    if (currPos.getX() + 1 < 15) {
                                        newCritPos.add(new Coord(currPos.getX() + 1, y));
                                    }
                                    if (currPos.getX() - 1 > 0) {
                                        newCritPos.add(new Coord(currPos.getX() - 1, y));
                                    }
                                }
                            break;
                            case UP:
                                for (int y = currPos.getY(); y >= collisionPos.getY(); y--) {
                                    if (currPos.getX() + 1 < 15) {
                                        newCritPos.add(new Coord(currPos.getX() + 1, y));
                                    }
                                    if (currPos.getX() - 1 >0) {
                                        newCritPos.add(new Coord(currPos.getX() - 1, y));
                                    }
                                }
                            break;
                            case RIGHT:
                                for (int x = currPos.getX(); x <= collisionPos.getX(); x++) {
                                    if (currPos.getY() + 1 < 15) {
                                        newCritPos.add(new Coord(x, currPos.getY() + 1));
                                    }
                                    if (currPos.getY() - 1 > 0) {
                                        newCritPos.add(new Coord(x, currPos.getY() - 1));
                                    }
                                }
                            break;
                            case LEFT:
                                for (int x = currPos.getX(); x >= collisionPos.getX(); x--) {
                                    if (currPos.getY() + 1 < 15) {
                                        newCritPos.add(new Coord(x, currPos.getY() + 1));
                                    }
                                    if (currPos.getY() - 1 > 0) {
                                        newCritPos.add(new Coord(x, currPos.getY() - 1));
                                    }
                                }
                            break;
                        }
                    }
                }
            }
            degree--;
        }
        return criticalPositions;
    }

    public MoveNode getResult() {
        return result;
    }

    public void setResult(MoveNode result) {
        this.result = result;
    }

}
