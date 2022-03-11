package Logic;

import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.MoveCommand;
import Data.MoveNode;

import java.util.ArrayList;
import java.util.Random;

public class SetupSearch {

    Game game;
    //Running parameters
    int selectedSetupHeuristic;
    int setupLimit;

    //Expandable SetupNodes
    ArrayList<MoveNode> setupExplore;
    //Setupnode that will be used as root of main tree
    MoveNode setup;

    Utility utility;

    public SetupSearch(int selectedSetupHeuristic,int setupLimit,Game game){
        this.selectedSetupHeuristic=selectedSetupHeuristic;
        this.setupLimit=setupLimit;
        this.game=game;
        this.utility=new Utility(game);

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
            utility.loadCriticalPositions(3);
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
                //selects Node with highstHValue(Best Setup Score)
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
                //selects Node with highstHValue(Best Setup Score)
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
        if(currSetup.getMoveCommands().size()>setupLimit){
            setupExplore.remove(currSetup);
        }else{
            for (int i = 0; i < otherColors.size(); i++) {
                Colors currColor = otherColors.get(i);
                for (Direction direction : Direction.values()) {
                    currSetup.addChild(new MoveCommand(currColor, direction));
                    MoveNode recentC=currSetup.getChilds().get(currSetup.getChilds().size()-1);
                    int seqCheck = utility.isSeqSmart(recentC.getMoveCommands());
                    if(this.selectedSetupHeuristic==3){
                        float HValue=utility.isSeqSmartSetup(recentC.getMoveCommands());
                        recentC.setHValue(HValue);
                    }
                    if(this.selectedSetupHeuristic==4){
                        float HValue=utility.isSeqSmartSetup(recentC.getMoveCommands());
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
