package Logic;

import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.MoveCommand;
import Data.MoveNode;
import View.DisplayFx;

import java.util.ArrayList;

// An AI that play on a given Game object
public class AI {
    Game game;
    MoveNode root;
    DisplayFx view;
    public AI(Game game, DisplayFx view){
       this.game = game;
       this.view = view;
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
            if (game.moveRobot(curr.getChilds().get(0).getMoveCommand()) == 0) {
                displayMove(curr.getChilds().get(0).getMoveCommand().getColor(),curr.getChilds().get(0).getMoveCommand().getDir());
                buildNaiveTree(depthLimit-1,curr.getChilds().get(0));
            }
            if (game.moveRobot(curr.getChilds().get(0).getMoveCommand()) == -1) {
                curr.getChilds().remove(0);
                game.revertMove();
            }
        }
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


}
