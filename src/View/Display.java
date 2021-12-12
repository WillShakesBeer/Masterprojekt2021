package View;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.Enums.ObsType;
import Logic.Game;

import java.util.ArrayList;
import java.util.Scanner;


public class Display {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    //The Game that is displayed
    Game game;

    int height;
    int length;

    //The Output String
    String[][] view;

    public Display(Game game){
        this.game = game;
    }

    //gets MoveCommand from user
    //returns newMoveCommand(null,null) for invalid input
    public MoveCommand getMove(){
        MoveCommand mCmd=null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Color (r=red,g=green,b=blue,y=yellow)");
        String colorStr = sc.nextLine();
        Colors colors =null;
        switch (colorStr){
            case "r":
                colors = Colors.RED;
                break;
            case "g":
                colors = Colors.GREEN;
                break;
            case "b":
                colors = Colors.BLUE;
                break;
            case "y":
                colors = Colors.YELLOW;
                break;
            default:
                System.out.println("Cant identify Color");
        }
        System.out.println("Enter Direction (u=up,r=right,l=left,d=down)");
        String dirStr = sc.nextLine();
        Direction dir=null;
        switch (dirStr){
            case "u":
                dir = Direction.UP;
                break;
            case "r":
                dir = Direction.RIGHT;
                break;
            case "l":
                dir = Direction.LEFT;
                break;
            case "d":
                dir = Direction.DOWN;
                break;
            default:
                System.out.println("Cant identify Direction");
        }
        mCmd = new MoveCommand(colors,dir);
        return mCmd;
    }

    //updates Visual if the game.GameState has changed
    public String[][] updateGame(){

        height = game.getState().getBoard().getHeight();
        length = game.getState().getBoard().getLength();
        view = new String[height][length];


        //intitalizes the board for the first time
        drawBoard(view);

        //draw victory points
        drawVictorySpawn(view);

        //draw the current victory point
        drawVictoryPoint(view);

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

    //replaces a victoryspawn with the current VictoryPoint
    public void drawVictoryPoint(String[][] view){
        VictoryPoint vp = game.getState().getBoard().getVictoryPoint();
        Colors colors = vp.getColor();
        String colorString = "E";
        switch (colors) {
            case RED:
                colorString = ANSI_RED + "X" + ANSI_RESET;
                break;
            case GREEN:
                colorString = ANSI_GREEN + "X" + ANSI_RESET;
                break;
            case BLUE:
                colorString = ANSI_BLUE + "X" + ANSI_RESET;
                break;
            case YELLOW:
                colorString = ANSI_YELLOW + "X" + ANSI_RESET;
                break;
        }
        view[vp.getCoord().getX()][vp.getCoord().getY()] = colorString;

    }

    public void drawVictorySpawn(String[][] view){
        ArrayList<VictorySpawn> victorySpawns = game.getState().getBoard().getVictorySpawns();
         for (VictorySpawn victorySpawn : victorySpawns) {
             Colors colors = victorySpawn.getColor();
             // if E is printed a VP is missing its color type
             String colorString = "E";
             switch (colors) {
                 case RED:
                     colorString = ANSI_RED + "O" + ANSI_RESET;
                     break;
                 case GREEN:
                     colorString = ANSI_GREEN + "O" + ANSI_RESET;
                     break;
                 case BLUE:
                     colorString = ANSI_BLUE + "O" + ANSI_RESET;
                     break;
                 case YELLOW:
                     colorString = ANSI_YELLOW + "O" + ANSI_RESET;
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



    public void drawRobots(String[][] view){
        ArrayList<Robot> robots = game.getState().getBoard().getRobots();

        for(Robot robot : robots){
            Colors colors = robot.getColor();
            // if E is printed a robot is missing its color type""
            String colorString = "E";
            switch (colors){
                case RED:
                    colorString = ANSI_RED+"R"+ANSI_RESET;
                    break;
                case GREEN:
                    colorString = ANSI_GREEN+"G"+ANSI_RESET;
                    break;
                case BLUE:
                    colorString = ANSI_BLUE+"B"+ANSI_RESET;
                    break;
                case YELLOW:
                    colorString = ANSI_YELLOW+"Y"+ANSI_RESET;
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
