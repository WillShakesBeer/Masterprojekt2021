package Logic;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;

import java.util.ArrayList;
import java.util.Random;



//Vicheuristic=0
//Uses Depth limited Depth first search
//Vicheurisitc=1
//Uses Depth limited random first search
//Vicheurisitc=2
//Uses Depth limited breadth first search
//Vicheurisitc=3
//Uses air distance to the VictoryPoint for PrimarySearch
//Vicheuristic=4
//Depth limited breadth first search VicColor
//preloads every Setup within the setupLimit
//Every heuristic != 4 loads setups on demand

//Depthlimit is the limit of Moves that can be used in total
//Setuplimit is the limit of the Moves that can be used on robots from colors other than the victorycolor
public class TreeSearch extends Thread{

    //input Parameters
    Game game;
    int selectedVicHeuristic;
    int selectedSetupHeuristic;
    int setupLimit;
    int depthLimit;
    int maxDegree;

    boolean interrupt=false;

    //output
    MoveNode result=null;
    ArrayList<ArrayList<MoveCommand>> resultList;

    //Expandable nodes of the Main tree search
    ArrayList<MoveNode> toExplore;

    //Expandable nodes of the Setup tree search
    SetupSearch setupSearch;
    ArrayList<MoveNode> setupExplore;
    //Setupnode that will be used as root of main tree
    MoveNode setup;

    //colors that are not victory colors
    ArrayList<Colors> otherColors=new ArrayList<Colors>();

    Utility utility;



    public TreeSearch(Game game,int depthLimit, int setupLimit, int selectedHeuristic,int selectedSetupHeuristic,int maxDegree){
        this.game=game;
        this.depthLimit=depthLimit;
        this.setupLimit=setupLimit;
        this.selectedVicHeuristic = selectedHeuristic;
        this.selectedSetupHeuristic= selectedSetupHeuristic;

        utility=new Utility(game,maxDegree);

        setupSearch = new SetupSearch(this.selectedSetupHeuristic,this.setupLimit,game,utility);
        setupExplore=setupSearch.getSetupExplore();
        setup=setupSearch.getSetup();

        this.maxDegree=maxDegree;
        resultList=new ArrayList<ArrayList<MoveCommand>>();
    }

    @Override
    public void run() {

        if (this.selectedVicHeuristic == 5){
            this.genericBreadthSearch();
            return;
        }
        if (this.selectedVicHeuristic == 6){
            this.genericDepthSearch();
            return;
        }
        if(this.selectedSetupHeuristic==4){
            utility.loadCriticalPath(utility.getMaxDegree());
        }
        MoveNode curr;
        Colors vicColor = this.game.getState().getBoard().getVictoryPoint().getColor();
        for(Colors currColor: Colors.values()){
            if(currColor!=vicColor){
                otherColors.add(currColor);
            }
        }
        while (!this.setupExplore.isEmpty() && !Thread.currentThread().isInterrupted() && !interrupt){
            if(interrupt){
                this.result=null;
                return;
            }
            if(selectedSetupHeuristic!=4) {
                if (this.selectedVicHeuristic == 4) {
                    ArrayList<MoveNode> setupList = setupSearch.loadAllSetups();
                    toExplore = new ArrayList<MoveNode>();
                    for (MoveNode currSetup : setupList) {
                        MoveNode currSetupCopy = new MoveNode((ArrayList<MoveCommand>) currSetup.getMoveCommands().clone(), currSetup.getHValue());
                        toExplore.add(currSetupCopy);
                    }
                } else {
                    toExplore = new ArrayList<MoveNode>();
                    MoveNode setupCopy = new MoveNode((ArrayList<MoveCommand>) setup.getMoveCommands().clone(), setup.getHValue());
                    toExplore.add(setupCopy);
                }
            }else{
                if(utility.crossBlockPositions.size()==0){
                    toExplore=new ArrayList<MoveNode>();
                    MoveNode root = new MoveNode();
                    toExplore.add(root);
                }else{
                    if (this.selectedVicHeuristic == 4) {
                        ArrayList<MoveNode> setupList = setupSearch.loadAllSetups();
                        toExplore = new ArrayList<MoveNode>();
                        for (MoveNode currSetup : setupList) {
                            MoveNode currSetupCopy = new MoveNode((ArrayList<MoveCommand>) currSetup.getMoveCommands().clone(), currSetup.getHValue());
                            toExplore.add(currSetupCopy);
                        }
                    } else {
                        toExplore = new ArrayList<MoveNode>();
                        MoveNode setupCopy = new MoveNode((ArrayList<MoveCommand>) setup.getMoveCommands().clone(), setup.getHValue());
                        toExplore.add(setupCopy);
                    }
                }
            }
            while (!toExplore.isEmpty()) {
                if(interrupt){
                    this.result=null;
                    return;
                }
                float minHValue;

                //selects the next expanded Node
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
                        minHValue=Integer.MAX_VALUE;
                        curr = toExplore.get(toExplore.size() - 1);
                        for(MoveNode currNode: toExplore){
                            if(currNode.getHValue()!=-1 && currNode.getHValue()<minHValue){
                                curr=currNode;
                                minHValue=curr.getHValue();
                            }
                        }
                        break;
                    default:
                        curr = toExplore.get(toExplore.size() - 1);
                        break;
                }
                //nodes expanded +1
                if (curr.getMoveCommands().size() > depthLimit) {
                    toExplore.remove(curr);
                } else {
                    for (Direction direction : Direction.values()) {
                        float newHValue=curr.getHValue();
                        ArrayList<MoveCommand> childCommands= (ArrayList<MoveCommand>) curr.getMoveCommands().clone();
                        childCommands.add(new MoveCommand(vicColor,direction));
                        MoveNode recentC=new MoveNode(childCommands,newHValue);
                        int HInc;
                        switch (this.selectedVicHeuristic){
                            case 3:
                                HInc=utility.isSeqSmartVicColor(recentC.getMoveCommands());
                                break;
                            default:
                                if(this.selectedSetupHeuristic==4){
                                    HInc= utility.isSeqSmartRememberCrossings(recentC.getMoveCommands());
                                }else{
                                    HInc=utility.isSeqSmart(recentC.getMoveCommands());
                                }
                                break;
                        }
                        recentC.setHValue(recentC.getHValue()+HInc);
                        if(HInc==0){
                            if(this.selectedVicHeuristic==4){
                                resultList.add(recentC.getMoveCommands());
                                if(!(this.selectedSetupHeuristic==4 && this.utility.crossBlockPositions.size()==0)){
                                    this.result=recentC;
                                    return;
                                }
                            }else{
                                this.result=recentC;
                                return;
                            }
                        }
                        if(HInc>0){
                            utility.insertInOrder(recentC,toExplore);
                            //toExplore.add(0, recentC);
                        }
                    }
                    toExplore.remove(curr);
                }

            }
            if(selectedVicHeuristic!=4) {
                System.out.println("new Setup needed");
                setup = setupSearch.createNewSetup();
            }
        }
        if(selectedVicHeuristic==4){
            int minMoves=-1;
            result=new MoveNode();
            for (ArrayList<MoveCommand> currRes : resultList){
                if(currRes.size()<minMoves || minMoves==-1){
                   result.setMoveCommands(currRes);
                   minMoves=currRes.size();
                }
            }
            if(minMoves==-1){
                this.result=null;
            }
        }else{
            this.result=null;
        }
        return;
    }


    public void genericBreadthSearch(){
        MoveNode curr;
        MoveNode root = new MoveNode();
        toExplore=new ArrayList<MoveNode>();
        toExplore.add(root);
        while (!toExplore.isEmpty()){
            if(interrupt){
                this.result=null;
                return;
            }
            curr = toExplore.get(toExplore.size()-1);
            if(curr.getMoveCommands().size() > depthLimit){
                toExplore.remove(curr);
            }else{
                for(Direction direction : Direction.values()){
                    for(Colors color : Colors.values()){
                        ArrayList<MoveCommand> childCommands= (ArrayList<MoveCommand>) curr.getMoveCommands().clone();
                        childCommands.add(new MoveCommand(color,direction));
                        MoveNode recentC=new MoveNode(childCommands);
                        int seqSmart = utility.isSeqSmart(recentC.getMoveCommands());
                        if(seqSmart ==0){
                            this.result=recentC;
                            return;
                        }
                        if(seqSmart == 1){
                            toExplore.add(0,recentC);
                        }
                    }
                }
            }
            toExplore.remove(curr);

        }
        this.result=null;

    }

    //todo: Solutions are either Maxlength or 1 fix
    public void genericDepthSearch(){
        int precomputedSetups = 1;
        MoveNode curr;
        MoveNode root = new MoveNode();
        toExplore=new ArrayList<MoveNode>();
        toExplore.add(root);
        if(precomputedSetups==1){
            ArrayList<MoveNode> setupList = setupSearch.loadAllSetups();
            toExplore = new ArrayList<MoveNode>();
            for (MoveNode currSetup : setupList) {
                MoveNode currSetupCopy = new MoveNode((ArrayList<MoveCommand>) currSetup.getMoveCommands().clone(), currSetup.getHValue());
                toExplore.add(currSetupCopy);
            }
        }
        while (!toExplore.isEmpty()){
            if(interrupt){
                this.result=null;
                return;
            }
            curr = toExplore.get(0);
            if(curr.getMoveCommands().size() > depthLimit){
                toExplore.remove(curr);
            }else{
                for(Direction direction : Direction.values()){
                    switch (precomputedSetups){
                        case 1:
                            Colors vicColor = this.game.getState().getBoard().getVictoryPoint().getColor();
                            ArrayList<MoveCommand> childCommands1= (ArrayList<MoveCommand>) curr.getMoveCommands().clone();
                            childCommands1.add(new MoveCommand(vicColor,direction));
                            MoveNode recentC1=new MoveNode(childCommands1);
                            int seqSmart1 = utility.isSeqSmart(recentC1.getMoveCommands());
                            if(seqSmart1 ==0){
                                this.result=recentC1;
                                return;
                            }
                            if(seqSmart1 == 1){
                                toExplore.add(0,recentC1);
                            }
                            break;
                        case 0:
                            for(Colors color : Colors.values()){
                                ArrayList<MoveCommand> childCommands= (ArrayList<MoveCommand>) curr.getMoveCommands().clone();
                                childCommands.add(new MoveCommand(color,direction));
                                MoveNode recentC=new MoveNode(childCommands);
                                int seqSmart = utility.isSeqSmart(recentC.getMoveCommands());
                                if(seqSmart ==0){
                                    this.result=recentC;
                                    return;
                                }
                                if(seqSmart == 1){
                                    toExplore.add(0,recentC);
                                }
                            }
                            break;
                    }
                }
            }
            toExplore.remove(curr);

        }
        this.result=null;

    }
    public void stopSearch(){
        this.interrupt=true;
        this.setupSearch.interrupt=true;
    }

    public MoveNode getResult() {
        return result;
    }

    public void setResult(MoveNode result) {
        this.result = result;
    }

    public boolean getInterrupt() {
        return interrupt;
    }

    public void setInterrupt(boolean interrupt) {
        this.interrupt = interrupt;
    }
}
