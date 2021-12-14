package View;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.Enums.ObsType;
import Logic.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by Martin Eberle aka WillShakesBeer on 14.12.2021.
 */

public class DisplayFx {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    private Colors selectedColor;
    private Direction selectedDirection;


    public DisplayFx (){

    }

    public void diplayVisuals(Stage primaryStage , Game game) {
        primaryStage.setTitle("Ricochet Robots");
        primaryStage.setResizable(true);
        //Starting with Input UI
        //Color Selection with Radio Buttons
        //Direction with Buttons

        System.out.println("Spielfeld: " + game.getConfig().getLength() + " x " + game.getConfig().getHeight() );


        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(0);
        boardGrid.setVgap(0);
        boardGrid.setPadding(new Insets(10, 10, 0, 10));

        //draw empty board
        drawEmptyBoard(game, boardGrid);

        //draw VPs
        drawVP(game, boardGrid);

        //draw Walls
        drawObstacles(game,boardGrid);


        //draw Robots
        drawRobots(game,boardGrid);

        //generateButtons
        HBox hBoxAllButtons = drawButtons(game,boardGrid);

        //fullBoard with Buttons
        VBox vBoxAll = new VBox(boardGrid, hBoxAllButtons);


        //Scene scene = new Scene(vbox,(int)(Screen.getPrimary().getBounds().getWidth()),(int)(Screen.getPrimary().getBounds().getHeight()));
        Scene scene = new Scene(vBoxAll, 820, 900);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void drawEmptyBoard(Game game , GridPane boardGrid){
        for (int i = 0; i <= game.getConfig().getHeight(); i++) {
            for (int j = 0; j <= game.getConfig().getLength(); j++) {
                boardGrid.add(new ImageView(new Image("file:Ressources/SpriteFloor.jpg" )), i, j);
            }
        }
    }

    public void drawVP(Game game, GridPane boardGrid){
        ArrayList<VictorySpawn> victorySpawns = game.getState().getBoard().getVictorySpawns();
        for (VictorySpawn victorySpawn : victorySpawns) {
            Colors colors = victorySpawn.getColor();
            Image newVP = null;
            switch (colors) {
                case RED:
                    newVP = new Image("file:Ressources/VRed.png");
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
            boardGrid.add(new ImageView(newVP), victorySpawn.getCoord().getX(), victorySpawn.getCoord().getY());
        }
    }

    public void drawObstacles(Game game, GridPane boardGrid){
        ArrayList<Obstacle> obstacles = game.getState().getBoard().getObstacles();
        for (Obstacle obstacle : obstacles) {
            ObsType type = obstacle.getType();
            // if E is printed an Obs is missing its  type
            Image newObs = null;
            switch (type) {
                case VERTICAL:
                    newObs = new Image("file:Ressources/SpriteWallVertical.png");
                    break;
                case HORIZONTAL:
                    newObs = new Image("file:Ressources/SpriteWallHorizontal.png");
                    break;
            }

            boardGrid.add(new ImageView(newObs), obstacle.getCoord1().getX(), obstacle.getCoord1().getY());
        }
    }
    public void drawRobots(Game game, GridPane boardGrid){
        ArrayList<Robot> robots = game.getState().getBoard().getRobots();
        for (Robot robot : robots) {
            Colors colors = robot.getColor();
            Image newRobot = null;
            switch (colors) {
                case RED:
                    newRobot = new Image("file:Ressources/RobotRed.png");
                    break;
                case GREEN:
                    newRobot = new Image("file:Ressources/RobotGreen.png");
                    break;
                case BLUE:
                    newRobot = new Image("file:Ressources/RobotBlue.png");
                    break;
                case YELLOW:
                    newRobot = new Image("file:Ressources/RobotYellow.png");
                    break;
            }
            boardGrid.add(new ImageView(newRobot), robot.getCoord().getX(), robot.getCoord().getY());
        }
    }
    public HBox drawButtons(Game game, GridPane boardGrid){
        HBox hBoxColor = drawColorButtons(game,boardGrid);
        HBox hBoxDirection = drawDirectionButtons(game,boardGrid);

        HBox hBoxAllButtons = new HBox(hBoxColor, hBoxDirection);
        hBoxAllButtons.setSpacing(50);
        hBoxAllButtons.setAlignment(Pos.BASELINE_CENTER);

        return hBoxAllButtons;
    }

    public HBox drawColorButtons(Game game, GridPane boardGrid){

        RadioButton red = new RadioButton("Red ");
        red.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedColor = Colors.RED;
                System.out.println("Selected: " + selectedColor);
            }
        });

        RadioButton green = new RadioButton("Green ");
        green.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedColor = Colors.GREEN;
                System.out.println("Selected: " + selectedColor);
            }
        });

        RadioButton blue = new RadioButton("Blue ");
        blue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedColor = Colors.BLUE;
                System.out.println("Selected: " + selectedColor);
            }
        });

        RadioButton yellow = new RadioButton("Yellow ");
        yellow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedColor = Colors.YELLOW;
                System.out.println("Selected: " + selectedColor);
            }
        });

        selectedColor = Colors.RED;
        red.setSelected(true);

        ToggleGroup radioGroupColor = new ToggleGroup();
        red.setToggleGroup(radioGroupColor);
        green.setToggleGroup(radioGroupColor);
        blue.setToggleGroup(radioGroupColor);
        yellow.setToggleGroup(radioGroupColor);

        HBox hBoxColor = new HBox(red, green, blue, yellow);
        hBoxColor.setPadding(new Insets(0, 0, 0, 0));
        return hBoxColor;
    }

    public HBox drawDirectionButtons(Game game, GridPane boardGrid){

        Button left = new Button("←");
        left.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDirection = Direction.LEFT;
                moveRobot(selectedDirection , selectedColor , game , boardGrid);
            }
        });
        Button up = new Button("↑");
        up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDirection = Direction.UP;
                moveRobot(selectedDirection , selectedColor , game , boardGrid);

            }
        });
        Button right = new Button("→");
        right.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDirection = Direction.RIGHT;
                moveRobot(selectedDirection , selectedColor , game , boardGrid);

            }
        });
        Button down = new Button("↓");
        down.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDirection = Direction.DOWN;
                moveRobot(selectedDirection , selectedColor , game , boardGrid);

            }
        });

        VBox upAndDown = new VBox(up,down);
        HBox hBoxDirection = new HBox(left, upAndDown , right );
        hBoxDirection.setPadding(new Insets(10, 0, 0, 0));
        return hBoxDirection;

    }

    public void moveRobot(Direction selectedDirection, Colors selectedColor, Game game, GridPane boardGrid){
        System.out.println("Selected: " + selectedColor + " " + selectedDirection);
        MoveCommand mCmd = new MoveCommand(selectedColor, selectedDirection);
        game.moveRobot(mCmd);
        redrawRobots (game, boardGrid);
    }

    public void redrawRobots(Game game, GridPane boardGrid){
        int distanceToRobots = (game.getConfig().getHeight()+1) * (game.getConfig().getLength()+1) //all fields
                + game.getConfig().getVictorySpawns().size()                                       //all vp
                + game.getConfig().getObstacleList().size();                                       //all obs

        //we delete all robots and draw them new (less hazzle than to identify which Robot moved this turn)
        boardGrid.getChildren().remove(distanceToRobots);
        boardGrid.getChildren().remove(distanceToRobots);
        boardGrid.getChildren().remove(distanceToRobots);
        boardGrid.getChildren().remove(distanceToRobots);
        drawRobots(game,boardGrid);
    }


}


