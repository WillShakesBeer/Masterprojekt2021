package Logic;

import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.Move;
import Data.MoveCommand;
import Data.MoveNode;

import java.util.ArrayList;
import java.util.Random;

public class SetupSearch {

    Game game;
    //Running parameters

    //SetupHeuristic=0:
    //Depth first search
    //SetupHeuristic=1:
    //Random first search
    //SetupHeuristic=2:
    //Breadth first search
    //SetupHeuristic=3:
    //Best Position score first
    //SetupHeuristic=4:
    //Best Cross block score first

    int selectedSetupHeuristic;
    int setupLimit;

    boolean interrupt;

    //Expandable SetupNodes
    ArrayList<MoveNode> setupExplore;
    //Setupnode that will be used as root of main tree
    MoveNode setup;

    Utility utility;

    public SetupSearch(int selectedSetupHeuristic,int setupLimit,Game game,Utility utility){
        this.selectedSetupHeuristic=selectedSetupHeuristic;
        this.setupLimit=setupLimit;
        this.game=game;
        this.utility=utility;


        MoveNode root = new MoveNode();
        this.setup=root;
        this.setupExplore=new ArrayList<MoveNode>();
        this.setupExplore.add(root);

    }

    //created this function to solve multicolor problems
    //will only be called if the problem is not feasible with only one color in the respective depth limit
    //a setup is a move node with a Movecommand sequence enables the victorycolor to solve the problem
    public MoveNode createNewSetup(){
        if(this.selectedSetupHeuristic==3){
            utility.loadCriticalPositions(utility.getMaxDegree());
        }
        ArrayList<Colors> otherColors=new ArrayList<Colors>();
        Colors vicColor = this.game.getState().getBoard().getVictoryPoint().getColor();
        for(Colors currColor: Colors.values()){
            if(currColor!=vicColor){
                otherColors.add(currColor);
            }
        }

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
                //selects Node with highstHValue
                //position score heuristic
                float maxHValue3=0;
                currSetup = setupExplore.get(setupExplore.size() - 1);
                for(MoveNode currNode: setupExplore){
                    if(currNode.getHValue()>maxHValue3){
                        currSetup=currNode;
                        maxHValue3=currSetup.getHValue();
                    }
                }
                break;
            case 4:
                //selects Node with highstHValue
                //crossblock score heuristic
                float maxHValue4=0;
                currSetup = setupExplore.get(setupExplore.size() - 1);
                for(MoveNode currNode: setupExplore){
                    if(currNode.getHValue()>maxHValue4){
                        currSetup=currNode;
                        maxHValue4=currSetup.getHValue();
                    }
                }
                break;
            default:
                currSetup = setupExplore.get(0);
                break;
        }
        if(currSetup.getMoveCommands().size()>=setupLimit){
            setupExplore.remove(currSetup);
        }else{
            for (int i = 0; i < otherColors.size(); i++) {
                Colors currColor = otherColors.get(i);
                for (Direction direction : Direction.values()) {

                    ArrayList<MoveCommand> childCommands= (ArrayList<MoveCommand>) currSetup.getMoveCommands().clone();
                    childCommands.add(new MoveCommand(currColor,direction));
                    MoveNode recentC=new MoveNode(childCommands);
                    int seqCheck = utility.isSeqSmart(recentC.getMoveCommands());
                    float HValue;
                    switch (this.selectedSetupHeuristic){
                        case 3:
                            HValue=utility.isSeqSmartSetup(recentC.getMoveCommands());
                            recentC.setHValue(HValue);
                            break;
                        case 4:
                            HValue=utility.isSeqSmartSetupCrossBlock(recentC.getMoveCommands());
                            recentC.setHValue(HValue);
                            break;
                        default:
                            HValue=recentC.getMoveCommands().size();
                            recentC.setHValue(HValue);
                            break;
                    }
                    if (seqCheck >=0) {
                        setupExplore.add(0, recentC);
                    }
                }
            }
            setupExplore.remove(currSetup);
        }
        return currSetup;
    }

    public ArrayList<MoveNode> loadAllSetups(){
        ArrayList<MoveNode> setupList = new ArrayList<MoveNode>();
        while (!this.setupExplore.isEmpty()){
            if(interrupt){
                return new ArrayList<MoveNode>();
            }
            MoveNode newSetup = createNewSetup();
            switch (selectedSetupHeuristic){
                case 3: case 4:
                    if(newSetup.getHValue()>0 || newSetup.getRoot()){
                        newSetup.setHValue(newSetup.getMoveCommands().size());
                        utility.insertInOrder(newSetup,setupList);
                    }
                break;
                case 0:
                    setupList.add(newSetup);
                break;
                default:
                    utility.insertInOrder(newSetup,setupList);
                break;
            }
        }
        //setupListe.size()
        return setupList;
    }

    public MoveNode getSetup() {
        return setup;
    }

    public void setSetup(MoveNode setup) {
        this.setup = setup;
    }

    public ArrayList<MoveNode> getSetupExplore() {
        return setupExplore;
    }

    public void setSetupExplore(ArrayList<MoveNode> setupExplore) {
        this.setupExplore = setupExplore;
    }
}
