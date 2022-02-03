package View;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.Enums.ObsType;
import Logic.AI;
import Logic.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Martin Eberle aka WillShakesBeer on 14.12.2021.
 */

public class DisplayFx {

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_BLACK = "\u001B[30m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_CYAN = "\u001B[36m";
    private final String ANSI_WHITE = "\u001B[37m";

    private Colors selectedColor;
    private Direction selectedDirection;

    private RadioButton red = new RadioButton("Red ");
    private RadioButton green = new RadioButton("Green ");
    private RadioButton blue = new RadioButton("Blue ");
    private RadioButton yellow = new RadioButton("Yellow ");

    private Button left = new Button("←");
    private Button up = new Button("↑");
    private Button down = new Button("↓");
    private Button right = new Button("→");

    private Button revertLastMoveButton = new Button("Revert Last Move");

    private Label score = new Label("Score: 0");
    private Label moveScore = new Label ("Moves: ");
    private Label movelist = new Label("Made moves: ");
    private ArrayList<String> moveListlist = new ArrayList<>();
    private ScrollPane movelistScrollPane;

    //to determine if a move is usefull, or the robot just runs in a wall
    private boolean crashWall;
    private ArrayList<MoveCommand> visSeq=new ArrayList<MoveCommand>();

    private Game game;
    private GridPane boardGrid;

    private Logic.AI ai;


    public DisplayFx (){

    }

    public void diplayVisuals(Stage primaryStage , Game game) {
        this.ai = ai;
        primaryStage.setTitle("Ricochet Robots");
        primaryStage.setResizable(true);
        //Starting with Input UI
        //Color Selection with Radio Buttons
        //Direction with Buttons

        System.out.println("Spielfeld: " + game.getConfig().getLength() + " x " + game.getConfig().getHeight() );

        this.game = game;
        boardGrid = new GridPane();
        boardGrid.setHgap(0);
        boardGrid.setVgap(0);
        boardGrid.setPadding(new Insets(10, 10, 0, 10));

        //draw empty board
        drawEmptyBoard();

        //draw Walls
        drawObstacles();

        //draw VPs
        drawVP();

        //draw Robots
        drawRobots();

        //Grid and listofMoves

        movelistScrollPane = drawListOfMoves();
        HBox gridAndMoveList = new HBox(boardGrid , movelistScrollPane);

        //generateButtons
        HBox hBoxAllButtons = drawButtons();

        //fullBoard with Buttons + move score
        VBox vBoxAll = new VBox(gridAndMoveList, hBoxAllButtons);

        //Scene scene = new Scene(vbox,(int)(Screen.getPrimary().getBounds().getWidth()),(int)(Screen.getPrimary().getBounds().getHeight()));
        Scene scene = new Scene(vBoxAll, 950, 900);

        generateKeyhandlers(scene);

        primaryStage.setScene(scene);

        primaryStage.show();

        this.ai = new AI(game);
    }

    public void generateKeyhandlers (Scene scene){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W: up.fire(); break;
                    case A: left.fire(); break;
                    case S: down.fire(); break;
                    case D: right.fire(); break;
                    case DIGIT1: red.fire(); break;
                    case DIGIT2: green.fire(); break;
                    case DIGIT3: blue.fire(); break;
                    case DIGIT4: yellow.fire(); break;
                    case BACK_SPACE: revertLastMoveButton.fire(); break;
                    case N: executeNextMove(); break;
                    case ENTER: executeNextSequence(); break;
                }
            }
        });

    }

    //todo
    //movelist does not empty if vp has reached properly
    //otherwise does properly iterate random vp with treesearch
    public void executeNextSequence(){
        while (!this.visSeq.isEmpty()){
            executeNextMove();
        }
        this.visSeq=ai.createSeq().getMoveCommands();
        if (this.visSeq.isEmpty()){
            this.visSeq=new ArrayList<MoveCommand>();
            System.out.println("no result");
            game.forceNewVictoryPoint();
            this.moveListlist = new ArrayList<>();
            redrawMovelist();
            redrawRobots();
        }
        else{

            redrawMovelist();
            redrawRobots();
            System.out.println("Solution found");
        }
    }

    public void visualizeSeq(ArrayList<MoveCommand> moveCommands){
        this.visSeq=moveCommands;
    }

    public void executeNextMove(){
        if(!visSeq.isEmpty()){
            MoveCommand curr = visSeq.get(0);
            this.setSelectedColor(curr.getColor());
            this.setSelectedDirection(curr.getDir());
            this.moveRobot(curr.getDir(), curr.getColor());
            visSeq.remove(curr);
        }

    }


    public void drawEmptyBoard(){
        for (int i = 0; i <= game.getConfig().getLength(); i++) {
            for (int j = 0; j <= game.getConfig().getHeight(); j++) {
                Random random = new Random();
                int randomNumber =random.nextInt(3);
                if (randomNumber == 0){
                    boardGrid.add(new ImageView(new Image("file:Ressources/SpriteFloor.jpg" )), i, j);
                }if (randomNumber == 1){
                    boardGrid.add(new ImageView(new Image("file:Ressources/SpriteFloor2.jpg" )), i, j);
                }if (randomNumber == 2){
                    boardGrid.add(new ImageView(new Image("file:Ressources/SpriteFloor3.jpg" )), i, j);
                }

            }
        }
    }

    public void drawVP(){
        ArrayList<VictorySpawn> victorySpawns = game.getState().getBoard().getVictorySpawns();
        for (VictorySpawn victorySpawn : victorySpawns) {
            Colors colors = victorySpawn.getColor();
            Image newVPSpawn = null;
            switch (colors) {
                case RED:
                    newVPSpawn = new Image("file:Ressources/VRedSpawn.png");
                    break;
                case GREEN:
                    newVPSpawn = new Image("file:Ressources/VGreenSpawn.png");
                    break;
                case BLUE:
                    newVPSpawn = new Image("file:Ressources/VBlueSpawn.png");
                    break;
                case YELLOW:
                    newVPSpawn = new Image("file:Ressources/VYellowSpawn.png");
                    break;
            }
            boardGrid.add(new ImageView(newVPSpawn), victorySpawn.getCoord().getX(), victorySpawn.getCoord().getY());
        }

        Colors colors = game.getState().getBoard().getVictoryPoint().getColor();
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
        boardGrid.add(new ImageView(newVP), game.getState().getBoard().getVictoryPoint().getCoord().getX(), game.getState().getBoard().getVictoryPoint().getCoord().getY());
    }

    public void drawObstacles(){
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
    public void drawRobots(){
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

    public ScrollPane drawListOfMoves (){
        ScrollPane movelistScrollPane = new ScrollPane();
        movelist.setPadding(new Insets(5, 0, 0,0 ));
        movelistScrollPane.setContent(movelist);
        movelistScrollPane.setPrefViewportHeight(game.getConfig().getHeight()*50);
        movelistScrollPane.setPrefViewportWidth(120);
        //so we stay at the bottom
        movelistScrollPane.setVvalue(movelistScrollPane.getVmax());

        return movelistScrollPane;
    }


    public HBox drawButtons(){

        Button revertLastMoveButton = drawRevertButton();

        HBox hBoxColor = drawColorButtons();
        HBox hBoxDirection = drawDirectionButtons();

        moveScore.setText("Moves: "+game.getState().getMoveList().size() );

        HBox hBoxAllButtons = new HBox(score, revertLastMoveButton , hBoxColor, hBoxDirection , moveScore);
        hBoxAllButtons.setSpacing(50);
        hBoxAllButtons.setAlignment(Pos.BASELINE_CENTER);

        return hBoxAllButtons;
    }

    public Button drawRevertButton (){
        revertLastMoveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                game.revertMove();
                redrawRobots();
                if (moveListlist.size() > 0){
                    moveListlist.remove(moveListlist.size()-1);
                }
                redrawMovelist();
                redrawRobots();
            }
        });

        return revertLastMoveButton;
    }

    //same functionality as the Button but accessible for the AI
    public void revertMove(){
        game.revertMove();
        redrawRobots();
        if (moveListlist.size() > 0){
            moveListlist.remove(moveListlist.size()-1);
        }
        redrawMovelist();
        redrawRobots();
    }

    public HBox drawColorButtons(){
        red.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedColor = Colors.RED;
                System.out.println("Selected: " + selectedColor);
            }
        });


        green.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedColor = Colors.GREEN;
                System.out.println("Selected: " + selectedColor);
            }
        });

        blue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedColor = Colors.BLUE;
                System.out.println("Selected: " + selectedColor);
            }
        });

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



    public HBox drawDirectionButtons(){
        left.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDirection = Direction.LEFT;
                moveRobot(selectedDirection , selectedColor );
            }
        });

        up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDirection = Direction.UP;
                moveRobot(selectedDirection , selectedColor );

            }
        });

        down.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDirection = Direction.DOWN;
                moveRobot(selectedDirection , selectedColor );

            }
        });

        right.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDirection = Direction.RIGHT;
                moveRobot(selectedDirection , selectedColor );

            }
        });

        VBox upAndDown = new VBox(up,down);
        HBox hBoxDirection = new HBox(left, upAndDown , right );
        hBoxDirection.setPadding(new Insets(10, 0, 0, 0));
        return hBoxDirection;

    }

    public boolean moveRobot(Direction selectedDirection, Colors selectedColor){
        System.out.println("Selected: " + selectedColor + " " + selectedDirection);
        MoveCommand mCmd = new MoveCommand(selectedColor, selectedDirection);
        fillMovelist();

        // used to determine if the robot hit a wall  (then returns -1)
        if(game.moveRobot(mCmd)==-1){
            crashWall = true;
        }else {
            crashWall = false;
        }

        redrawRobots ();
        score.setText("Score: " + game.getState().getScore());
        return crashWall;
    }

    public void redrawRobots(){
        int distanceToVps = (game.getConfig().getHeight()+1) * (game.getConfig().getLength()+1) //all fields
                + game.getConfig().getObstacleList().size() ;                                      //all obs

        //we delete all robots and VPs redraw them new (less hazzle than to identify which Robot moved this turn)
        for (int i = 0 ; i <= (game.getState().getBoard().getRobots().size() + game.getState().getBoard().getVictorySpawns().size()  ); i++){
            boardGrid.getChildren().remove(distanceToVps);
        }
        drawRobots();
        drawVP();
        moveScore.setText("Moves: "+game.getState().getMoveList().size() );

    }

    public void fillMovelist(){
        moveListlist.add('\n' + selectedColor.toString() + "  "+ "\t" + selectedDirection.toString() );
        redrawMovelist();
    }

    public void redrawMovelist (){
        String movelistString = "Made moves: ";
        for (String move : moveListlist){
            movelistString = movelistString + move;
        }
        movelist.setText(movelistString);
        //scrolls to the bottom
        movelistScrollPane.setVvalue(movelistScrollPane.getVmax());

    }

    public Colors getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Colors selectedColor) {
        this.selectedColor = selectedColor;
    }

    public Direction getSelectedDirection() {
        return selectedDirection;
    }

    public void setSelectedDirection(Direction selectedDirection) {
        this.selectedDirection = selectedDirection;
    }

    public RadioButton getRed() {
        return red;
    }

    public void setRed(RadioButton red) {
        this.red = red;
    }

    public RadioButton getGreen() {
        return green;
    }

    public void setGreen(RadioButton green) {
        this.green = green;
    }

    public RadioButton getBlue() {
        return blue;
    }

    public void setBlue(RadioButton blue) {
        this.blue = blue;
    }

    public RadioButton getYellow() {
        return yellow;
    }

    public void setYellow(RadioButton yellow) {
        this.yellow = yellow;
    }

    public Button getLeft() {
        return left;
    }

    public void setLeft(Button left) {
        this.left = left;
    }

    public Button getUp() {
        return up;
    }

    public void setUp(Button up) {
        this.up = up;
    }

    public Button getDown() {
        return down;
    }

    public void setDown(Button down) {
        this.down = down;
    }

    public Button getRight() {
        return right;
    }

    public void setRight(Button right) {
        this.right = right;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isCrashWall() {
        return crashWall;
    }

    public void setCrashWall(boolean crashWall) {
        this.crashWall = crashWall;
    }


}