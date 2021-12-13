import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.Enums.ObsType;
import Data.GameConfig.Config;
import Data.Obstacle;
import Data.Robot;
import Data.VictorySpawn;
import Logic.Game;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class RunDis extends Application {

    public static Colors selectedColor;
    public static Direction selectedDirection;



    public static void main (String[] args){
        //idk men SwingUtilities?
        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });
        Game game = new Game(DefaultGame());
        */

        launch();

        //updateVisuals(game);
    }

    public void start(Stage primaryStage){
        primaryStage.setTitle("Ricochet Robots");
        primaryStage.setResizable(false);
        //Starting with Input UI
        //Color Selection with Radio Buttons
        //Direction with Buttons

        Game game = new Game(DefaultGame());

        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(0);
        boardGrid.setVgap(0);

        boardGrid.setPadding(new Insets(10, 10, 0, 10));

        Image floorSprite = new Image("file:Ressources/SpriteFloor.jpg");



        //draw empty board
        for (int i =0 ; i < game.getConfig().getHeight() ;i++){
            for (int j =0 ; j < game.getConfig().getLength() ;j++){
                boardGrid.add(new ImageView(floorSprite), i , j );
            }
        }
        //draw VPs
        ArrayList<VictorySpawn> victorySpawns = game.getState().getBoard().getVictorySpawns();
        for (VictorySpawn victorySpawn : victorySpawns) {
            Colors colors = victorySpawn.getColor();
            Image newVP = null;
            switch (colors) {
                 case RED:
                    newVP =  new Image("file:Ressources/VRed.png");
                    break;
                case GREEN:
                    newVP = new Image("file:Ressources/VGreen.png");
                    break;
                case BLUE:
                    newVP = new Image("file:Ressources/VBlue.png");
                    break;
                case YELLOW:
                    newVP = new Image("file:Ressources/VYellow.png");
                    break;
            }
            boardGrid.add(new ImageView(newVP), victorySpawn.getCoord().getX()  , victorySpawn.getCoord().getY() );
        }


        //draw Walls
        ArrayList<Obstacle> obstacles = game.getState().getBoard().getObstacles();
        for (Obstacle obstacle : obstacles) {
            ObsType type = obstacle.getType();
            // if E is printed an Obs is missing its  type
            Image newObs =null;
            switch (type) {
                case VERTICAL:
                    newObs = new Image("file:Ressources/SpriteWallVertical.png");
                    break;
                case HORIZONTAL:
                    newObs = new Image("file:Ressources/SpriteWallHorizontal.png");
                    break;
            }

            boardGrid.add(new ImageView(newObs), obstacle.getCoord1().getX()  , obstacle.getCoord1().getY() );
        }


        //draw Robots
        ArrayList<Robot> robots = game.getState().getBoard().getRobots();
        for(Robot robot : robots){
            Colors colors = robot.getColor();
            Image newRobot =null;
            switch (colors){
                case RED:
                    newRobot= new Image("file:Ressources/RobotRed.png" );
                    break;
                case GREEN:
                    newRobot= new Image("file:Ressources/RobotGreen.png");
                    break;
                case BLUE:
                    newRobot= new Image("file:Ressources/RobotBlue.png");
                    break;
                case YELLOW:
                    newRobot= new Image("file:Ressources/RobotYellow.png");
                    break;
            }
            boardGrid.add(new ImageView(newRobot), robot.getCoord().getX(), robot.getCoord().getY() );
        }



        RadioButton red = new RadioButton("Red");
        red.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                selectedColor = Colors.RED;
                System.out.println("Selected: " + selectedColor);
            }
        });
        RadioButton green = new RadioButton("Green");
        green.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                selectedColor = Colors.GREEN;
                System.out.println("Selected: " + selectedColor);
            }
        });
        RadioButton blue = new RadioButton("Blue");
        blue.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                selectedColor = Colors.BLUE;
                System.out.println("Selected: " + selectedColor);
            }
        });
        RadioButton yellow = new RadioButton("yellow");
        yellow.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                selectedColor = Colors.YELLOW;
                System.out.println("Selected: " + selectedColor);
            }
        });
        red.setSelected(true);
        ToggleGroup radioGroupColor = new ToggleGroup();


        red.setToggleGroup(radioGroupColor);
        green.setToggleGroup(radioGroupColor);
        blue.setToggleGroup(radioGroupColor);
        yellow.setToggleGroup(radioGroupColor);

        HBox hBoxColor = new HBox(red,green,blue,yellow);
        hBoxColor.setPadding(new Insets(0, 0, 0, 0));


        int childrenLength =
                (game.getConfig().getHeight()*game.getConfig().getLength() ) + //h*l +
                        game.getState().getBoard().getObstacles().size() -1 +     // all walls
                        game.getState().getBoard().getVictorySpawns().size() -1;  // all VPs


        Button left = new Button("left");
        left.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                selectedDirection = Direction.LEFT;
                System.out.println("Selected: " + selectedColor + " " +  selectedDirection);
                MoveCommand mCmd = new MoveCommand(selectedColor, selectedDirection);
                game.moveRobot(mCmd);

            }
        });
        Button up = new Button("up");
        up.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                selectedDirection = Direction.UP;
                System.out.println("Selected: " + selectedColor + " " +  selectedDirection);
                MoveCommand mCmd = new MoveCommand(selectedColor, selectedDirection);
                game.moveRobot(mCmd);
            }
        });
        Button right = new Button("right");
        right.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                selectedDirection = Direction.RIGHT;
                System.out.println("Selected: " + selectedColor + " " +  selectedDirection);
                MoveCommand mCmd = new MoveCommand(selectedColor, selectedDirection);
                game.moveRobot(mCmd);

            }
        });
        Button down = new Button("down");
        down.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                selectedDirection = Direction.DOWN;
                System.out.println("Selected: " + selectedColor + " " +  selectedDirection);
                MoveCommand mCmd = new MoveCommand(selectedColor, selectedDirection);
                game.moveRobot(mCmd);
            }
        });

        HBox hBoxDirection = new HBox(left,up,right,down);
        hBoxDirection.setPadding(new Insets(10, 0, 0, 0));

        HBox hBoxAllButtons = new HBox(hBoxColor,hBoxDirection);
        hBoxAllButtons.setSpacing(50);
        hBoxAllButtons.setAlignment(Pos.BASELINE_CENTER);

        VBox vBoxAll = new VBox(boardGrid,hBoxAllButtons);


        //Scene scene = new Scene(vbox,(int)(Screen.getPrimary().getBounds().getWidth()),(int)(Screen.getPrimary().getBounds().getHeight()));
        Scene scene = new Scene(vBoxAll,810,850);
        primaryStage.setScene(scene);

        primaryStage.show();




    }


    /*
    //deprecated
    public static void updateVisuals(Game game){
        Display display = new Display(game);
        game.moveRobot(new MoveCommand(Color.RED, Direction.RIGHT));
        game.moveRobot(new MoveCommand(Color.RED, Direction.UP));
        //updateVisuals(display);
        display.getMove();

        updateVisuals(display);
        while (true){
            MoveCommand mCmd=display.getMove();
            game.moveRobot(mCmd);
            updateVisuals(display);
        }

    }


    public static void updateVisuals(Display display){
        Game game =display.getGame();
        String[][] updatedGame = display.updateGame();

        for (int i = game.getState().getBoard().getHeight()-1; i >=0; i--){
            for (int j = 0; j < game.getState().getBoard().getLength(); j++){
                System.out.print(updatedGame[j][i] + '\t' );
            }
            System.out.println();
        }
    }
    */




    public static Config DefaultGame(){
        int length = 16;
        int height = 16;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Robot(new Coord(15,15), Colors.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        obstacleList.add(new Obstacle(new Coord(3,0),new Coord(4,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(9,0),new Coord(10,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(5,1),new Coord(6,1), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(12,2),new Coord(13,2), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(4,3),new Coord(5,3), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(11,3),new Coord(12,3), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(12,4),new Coord(13,4), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(1,5),new Coord(2,5), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(10,5),new Coord(11,5), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(4,6),new Coord(5,6), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(2,7),new Coord(3,7), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,7),new Coord(7,7), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(8,7),new Coord(9,7), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,8),new Coord(7,8), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(8,8),new Coord(9,8), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(3,9),new Coord(4,9), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(11,9),new Coord(12,9), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(4,10),new Coord(5,10), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(9,11),new Coord(10,11), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,12),new Coord(1,12), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(14,12),new Coord(15,12), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(4,14),new Coord(5,14), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(10,14),new Coord(11,14), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(1,15),new Coord(2,15), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(8,15),new Coord(9,15), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,2), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(0,9),new Coord(0,10), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(1,4),new Coord(1,5), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(1,11),new Coord(1,12), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(2,7),new Coord(2,8), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(3,8),new Coord(3,9), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(4,3),new Coord(4,4), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(4,14),new Coord(4,15), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(5,5),new Coord(5,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(5,9),new Coord(5,10), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(6,1),new Coord(6,2), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(7,6),new Coord(7,7), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(7,8),new Coord(7,9), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(8,6),new Coord(8,7), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(8,8),new Coord(8,9), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(9,10),new Coord(9,11), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(10,3),new Coord(10,4), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(11,5),new Coord(11,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(11,14),new Coord(11,15), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(12,1),new Coord(12,2), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(12,8),new Coord(12,9), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(13,3),new Coord(13,4), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(14,12),new Coord(14,13), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(15,3),new Coord(15,4), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(15,13),new Coord(15,14), ObsType.VERTICAL));

        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        victorySpawns.add(new VictorySpawn(new Coord(4,3), Colors.RED));
        victorySpawns.add(new VictorySpawn(new Coord(13,4), Colors.RED));
        victorySpawns.add(new VictorySpawn(new Coord(1,12), Colors.RED));
        victorySpawns.add(new VictorySpawn(new Coord(11,14), Colors.RED));
        victorySpawns.add(new VictorySpawn(new Coord(1,5), Colors.BLUE));
        victorySpawns.add(new VictorySpawn(new Coord(3,9), Colors.BLUE));
        victorySpawns.add(new VictorySpawn(new Coord(10,3), Colors.BLUE));
        victorySpawns.add(new VictorySpawn(new Coord(5,10), Colors.YELLOW));
        victorySpawns.add(new VictorySpawn(new Coord(12,2), Colors.YELLOW));
        victorySpawns.add(new VictorySpawn(new Coord(12,9), Colors.YELLOW));
        victorySpawns.add(new VictorySpawn(new Coord(4,14), Colors.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(5,6), Colors.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(11,5), Colors.GREEN));

        Config config = new Config(robotList,obstacleList,victorySpawns,length,height);
        return config;
    }




}
