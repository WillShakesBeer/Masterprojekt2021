package Logic;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;
import View.DisplayFx;

import java.util.ArrayList;
import java.util.Scanner;

// An AI that play on a given Game object
public class AI {
    private Game game;
    private MoveNode root;

    public AI(Game game){
        this.game = game;
        root = new MoveNode();
    }

    //test TreeSearch
    public MoveNode createSeq(){
        ArrayList<MoveNode> toExplore = new ArrayList<MoveNode>();
        toExplore=new ArrayList<MoveNode>();
        toExplore.add(root);
        MoveNode result= buildNaiveTree(5,toExplore);
        if (result == null) {
            return new MoveNode();
        } else {
            return result;
        }
    }

    //reworked now uses Nodes with list of Commands
    //Uses Depth limited Depth first search
    public MoveNode buildNaiveTree(int depthLimit, ArrayList<MoveNode> toExplore){
        MoveNode curr = toExplore.get(0);
        Colors vicColor=game.getState().getBoard().getVictoryPoint().getColor();
        Colors color = vicColor;
        while(!toExplore.isEmpty()) {
            curr=toExplore.get(0);
            if(curr.getMoveCommands().size()>depthLimit){
                toExplore.remove(curr);
            }else {
                int seqCheck = isSeqSmart(curr.getMoveCommands());
                if (seqCheck==0 || curr.getRoot()) {
                    //System.out.println("Expand further");

                    for (Direction direction : Direction.values()) {

                        curr.addChild(new MoveCommand(color, direction));
                        toExplore.add(0, curr.getChilds().get(curr.getChilds().size() - 1));
                    }
                    toExplore.remove(curr);
                }
                //cut out usless moves
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

    /*public void visualizeSeq(ArrayList<MoveCommand> moveCommands){
        for(int i=0;i<moveCommands.size();i++){
            MoveCommand curr = moveCommands.get(i);
            view.setSelectedColor(curr.getColor());
            view.setSelectedDirection(curr.getDir());
            view.moveRobot(curr.getDir(), curr.getColor());
        }

    }*/

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



}
