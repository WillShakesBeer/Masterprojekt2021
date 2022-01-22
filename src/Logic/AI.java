package Logic;

import Data.Enums.Colors;
import Data.Enums.Direction;
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
         buildNaiveTree(3,root);
    }



    //Naive uninformed Tree search
    //Uses Depth first expansion
    public void buildNaiveTree(int depthLimit,MoveNode curr){
        //for(Colors color : Colors.values()){
        Colors color = Colors.RED;
            for(Direction direction : Direction.values()){
                //game.moveRobot(new MoveCommand(color,direction));
                curr.addChild(new MoveCommand(color, direction));
            }
        //}
        while(!curr.getChilds().isEmpty() && depthLimit>0) {
            //@Linus: hie rhab ich das geändert von 0/1/-1 auf nen boolean, da es nur 2 Zustände gibt: hittet wall/hittet keine -> a
            //-> a  if (game.moveRobot(curr.getChilds().get(0).getMoveCommand()) == 0) {
            if (game.moveRobot(curr.getChilds().get(0).getMoveCommand()) ) {
                displayMove(curr.getChilds().get(0).getMoveCommand().getColor(),curr.getChilds().get(0).getMoveCommand().getDir());
                buildNaiveTree(depthLimit-1,curr.getChilds().get(0));
            }
            //-> a  if (game.moveRobot(curr.getChilds().get(0).getMoveCommand()) == -1) {
            if (game.moveRobot(curr.getChilds().get(0).getMoveCommand()) ) {
                curr.getChilds().remove(0);
                game.revertMove();
            }
        }
    }


    //Naive uninformed Tree search
    //Uses Depth first expansion



    //Dirty Hack to interact with displayfx
    //1. use game.moveRobot to get return value
    //2. displayMove to repeat the moveCommand with a visualization
    public void displayMove(Colors color,Direction dir){
        game.revertMove();
        view.setSelectedColor(color);
        view.setSelectedDirection(dir);
        view.moveRobot(dir,color);
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
