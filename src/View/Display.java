package View;

import Data.Enums.Color;
import Data.Enums.ObsType;
import Data.Obstacle;
import Data.Robot;
import Data.VictorySpawn;
import Logic.Game;

import java.util.ArrayList;

public class Display {

    //The Game that is displayed
    Game game;

    int height;
    int length;

    //The Output String
    String[][] view;

    public Display(Game game){
        this.game = game;
    }

    //updates Visual if the game.GameState has changed
    public String[][] updateGame(){

        height = game.getState().getBoard().getHeight();
        length = game.getState().getBoard().getLength();
        view = new String[height][length];

        //intitalizes the board for the first time
        drawBoard(view);

        //draw victory points
        drawVictoryPoints(view);

        //draw obstacles
        drawObstacles(view);

        //draw robots adds robots onto the board
        drawRobots(view);

        return view;
    }




    public void drawBoard (String[][] view){

        for (int i = 0; i< height; i++){
            for (int j = 0; j< length; j++){
                view[i][j] = "■";
            }
        }

    }

    public void drawVictoryPoints (String[][] view){
        ArrayList<VictorySpawn> victorySpawns = game.getState().getBoard().getVictorySpawns();
         for (VictorySpawn victorySpawn : victorySpawns) {
             Color color = victorySpawn.getColor();
             // if E is printed a VP is missing its color type
             String colorString = "E";

             switch (color) {
                 case RED:
                     colorString = "r";
                     break;
                 case GREEN:
                     colorString = "g";
                     break;
                 case BLUE:
                     colorString = "b";
                     break;
                 case YELLOW:
                     colorString = "y";
                     break;
             }
             view[victorySpawn.getCoord().getX()][victorySpawn.getCoord().getY()] = colorString;
         }

    }

    public void drawObstacles (String[][] view){
        ArrayList<Obstacle> obstacles = game.getState().getBoard().getObstacles();
        for (Obstacle obstacle : obstacles) {
            ObsType type = obstacle.getType();
            // if E is printed an Obs is missing its  type
            String obsString = "E";


            switch (type) {
                case VERTICAL:
                    obsString = "‾";
                    break;
                case HORIZONTAL:
                    obsString = " |";
                    break;
            }

            String existingField = view[obstacle.getCoord1().getX()][obstacle.getCoord1().getY()];
            switch (existingField) {
                case "■":
                    break;
                case " |":
                    obsString = "‾|";
                    break;
                case "‾":
                    obsString = "‾|";
                    break;
            }
            view[obstacle.getCoord1().getX()][obstacle.getCoord1().getY()] = obsString;

        }

    }



    public void drawRobots (String[][] view){
        ArrayList<Robot> robots = game.getState().getBoard().getRobots();

        for(Robot robot : robots){
            Color color = robot.getColor();
            // if E is printed a robot is missing its color type""
            String colorString = "E";
            switch (color){
                case RED:
                    colorString = "R";
                    break;
                case GREEN:
                    colorString = "G";
                    break;
                case BLUE:
                    colorString = "B";
                    break;
                case YELLOW:
                    colorString = "Y";
                    break;
            }

            String field = view[robot.getCoord().getX()][robot.getCoord().getY()];
            if (field.equals("■")){
                view[robot.getCoord().getX()][robot.getCoord().getY()] = colorString;
            }else {
                view[robot.getCoord().getX()][robot.getCoord().getY()] = colorString + field;
            }

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
