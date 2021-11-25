package View;

import Data.Robot;
import Logic.Game;

import java.util.ArrayList;

public class Display {
    Game game;
    int viewHeight;
    int viewLength;
    char[][] view;

    public Display(Game game){
        this.game = game;
    }

    public char[][] displayGame(){

        viewHeight = game.getState().getBoard().getHeight();
        viewLength = game.getState().getBoard().getLength();
        view = new char[viewHeight][viewLength];

        //intitalizes the board for the first time
        drawBoard(view);

        //draw robots adds robots onto the board
        drawRobots(view);

        return view;
    }

    public void drawBoard (char[][] view){

        for (int i=0; i<viewHeight ; i++){
            for (int j=0; j<viewLength ; j++){
                view[i][j] = 'o';
            }
        }
    }

    public void drawRobots (char[][] view){
        ArrayList<Robot> robots = game.getState().getBoard().getRobots();
        for(Robot robot : robots){
            view [robot.getCoord().getX()][robot.getCoord().getY()] = 'R';
        }
    }


    public int upDateView(){
        int movesNeeded =0;

        return movesNeeded;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
