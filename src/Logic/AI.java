package Logic;

import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.Move;
import Data.MoveCommand;
import Data.MoveNode;
import View.DisplayFx;

import java.util.ArrayList;

// An AI that play on a given Game object
public class AI {
    private Game game;
    private MoveNode root;
    private DisplayFx view;

    public AI(DisplayFx view){
        this.view = view;
        this.game = view.getGame();
        root = new MoveNode();
        ArrayList<MoveNode> toExplore = new ArrayList<MoveNode>();
        toExplore.add(root);
        buildNaiveTree(3,toExplore);
    }

    //@Linus: hier hab ich das geändert von 0/1/-1 auf nen boolean, da es nur 2 Zustände gibt: hittet wall/hittet keine -> a
    //@Maddin: Lässt sich drüber streiten

    //reworked now uses Nodes with list of Commands
    //todo Bugfix DepthLimitedDepthfirstSearch
    //(some incorrect Nodes come through)
    //todo visualize
    //Naive uninformed Tree search
    //Uses Depth limited Depth first search
    public MoveNode buildNaiveTree(int depthLimit, ArrayList<MoveNode> toExplore){
        MoveNode curr = toExplore.get(0);
        Colors color = Colors.RED;
        while(!toExplore.isEmpty()) {
            curr=toExplore.get(0);
            if(curr.getMoveCommands().size()>=depthLimit){
                toExplore.remove(curr);
            }else {
                if (isSeqSmart(curr.getMoveCommands()) || curr.getRoot()) {
                    System.out.println("Expand further");
                    for (Direction direction : Direction.values()) {

                        curr.addChild(new MoveCommand(color, direction));
                        toExplore.add(0, curr.getChilds().get(curr.getChilds().size() - 1));
                    }
                    toExplore.remove(curr);
                }
                //cut out usless moves
                if (!isSeqSmart(curr.getMoveCommands())) {
                    System.out.println("Useless Move");
                    toExplore.remove(curr);
                    if(!toExplore.isEmpty()){
                        curr = toExplore.get(0);
                    }
                }
            }

        }
        System.out.println("No more to explore");
        return curr;
    }

    //Dirty Hack to interact with displayfx
    //1. use game.moveRobot to get return value
    //2. displayMove to repeat the moveCommand with a visualization
    public void displayMove(Colors color,Direction dir){
        game.revertMove();
        view.setSelectedColor(color);
        view.setSelectedDirection(dir);
        view.moveRobot(dir,color);

    }

    //checks if a Seq contains moves that end up crashing a Wall
    //true => no crash
    //false => crash
    public boolean isSeqSmart(ArrayList<MoveCommand> moveCommands){
        for(int i =0;i<moveCommands.size();i++){
           if(game.moveRobot(moveCommands.get(i))){
               return false;
           }
        }
        for(int i =0;i<moveCommands.size();i++) {
            game.revertMove();
        }
        return true;
    }

    //todo
    public void visualizeSeq(ArrayList<MoveCommand> moveCommands){

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



}
