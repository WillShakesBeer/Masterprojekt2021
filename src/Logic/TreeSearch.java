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
    SetupSearch setupSearch;
    ArrayList<MoveNode> setupExplore;
    //Setupnode that will be used as root of main tree
    MoveNode setup;

    //colors that are not victory colors
    ArrayList<Colors> otherColors=new ArrayList<Colors>();

    Utility utility;



    public TreeSearch(Game game,int depthLimit, int setupLimit, int selectedHeuristic,int selectedSetupHeuristic){
        this.game=game;
        this.depthLimit=depthLimit;
        this.setupLimit=setupLimit;
        this.selectedVicHeuristic = selectedHeuristic;
        this.selectedSetupHeuristic= selectedSetupHeuristic;

        setupSearch = new SetupSearch(this.selectedSetupHeuristic,this.setupLimit,game);
        setupExplore=setupSearch.getSetupExplore();
        setup=setupSearch.getSetup();

        utility=new Utility(game);
    }

    @Override
    public void run() {

        if(this.selectedSetupHeuristic==4){
            utility.loadCriticalPath(3);
        }
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
            setup=setupSearch.createNewSetup();
        }
        this.result=null;
        return;
    }


    public MoveNode getResult() {
        return result;
    }

    public void setResult(MoveNode result) {
        this.result = result;
    }

}
