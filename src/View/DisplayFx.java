package View;

import Data.*;
import Data.Enums.*;
import Data.GameConfig.Config;
import Data.Testing.SavedConfigs;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by Martin Eberle aka WillShakesBeer on 14.12.2021.
 */

public class DisplayFx {

    //Associated Classes
    private Game game;
    private Logic.AI ai;
    private DisplayUtility utility = new DisplayUtility();
    private AnalysisWindow anaWindow;

    //Ui Declaration
    private ScrollPane movelistScrollPane;
    private GridPane boardGrid;

    //Buttondeclaration
    private RadioButton red = new RadioButton("Red ");
    private RadioButton green = new RadioButton("Green ");
    private RadioButton blue = new RadioButton("Blue ");
    private RadioButton yellow = new RadioButton("Yellow ");
    private Button left = new Button("←");
    private Button up = new Button("↑");
    private Button down = new Button("↓");
    private Button right = new Button("→");
    private Button analysisButton = new Button("\uD83D\uDCC8");
    private Button revertLastMoveButton = new Button("Revert Last Move");

    //Labeldeclaraiton
    private Label analyisLabel = new Label("Analysis run: ");
    private Label averageLabel =new Label("Average:" + '\n');
    private Label score = new Label("Score: 0");
    private Label moveScore = new Label ("Moves: ");
    private Label movelist = new Label("Made moves: ");


    //Data

    //Current state variables
    private Colors selectedColor;
    private Direction selectedDirection;
    private boolean crashWall;
    private int selectedVicAlgo;
    private int setupVicAlgo;
    private ArrayList<MoveCommand> visSeq=new ArrayList<MoveCommand>();


    //Stat tracking data
    private int iterations = 1;
    private ArrayList<RunConfig> configList = new ArrayList<RunConfig>();
    private ArrayList<RunStat> runStats = new ArrayList<>();
    private ArrayList<String> moveListlist = new ArrayList<>();

    public DisplayFx (){
    }

    /*public void runGame(Stage startWindow ){

        startWindow.setTitle("Choose!");

        Button gameplay = new Button("just play");
        gameplay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                playGame(startWindow);
            }
        });
        Button analysis = new Button("analysis");
        analysis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                runAnalysis(startWindow);
            }
        });


        HBox hBoxAllButtons = new HBox(gameplay,analysis);
        hBoxAllButtons.setPadding(new Insets(10));
        hBoxAllButtons.setSpacing(110);

        Scene scene = new Scene(hBoxAllButtons, 250, 45);        startWindow.setScene(scene);
        startWindow.show();


        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        startWindow.setX((primScreenBounds.getWidth() - startWindow.getWidth()) / 2);
        startWindow.setY((primScreenBounds.getHeight() - startWindow.getHeight()) / 2);

    }*/

    //This Method is used for testing prupose
    //I dont want to deal with AnalysisScene just yet
    public void runGame(Stage startWindow){
        SavedConfigs testConfig= new SavedConfigs();
        this.game = new Game(testConfig.loadDefaultGameConfig());

        this.ai = new AI(game);
        ai.setAiDefaults();
        anaWindow = new AnalysisWindow(game,ai,this);
        playGame(startWindow);
    }


    //Analysis Area code pls refactor to another class
    //starts here
    public void runAnalysis(Stage analysisWindow){

        Scene scene = new Scene(generateAnalysisChooseButton(), 950, 900);
        generateKeyhandlers(scene);
        analysisWindow.setScene(scene);
        analysisWindow.setHeight(1000);
        analysisWindow.setWidth(1000);
        analysisWindow.setMaximized(true);
        analysisWindow.show();
    }

    public VBox generateAnalysisChooseButton(){

        SavedConfigs testConfig= new SavedConfigs();
        VBox buttonsAndField = new VBox();


        MenuButton algorithmChooseButton = new MenuButton("Choose Algorithm");
        for ( int i = 0 ; i <= 4 ; i++) {

            MenuItem menuItem = new MenuItem(""+ i);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedVicAlgo = Integer.valueOf(menuItem.getText());
                    ai.setSelectedVicAlgorithm(selectedVicAlgo);
                }
            });
            algorithmChooseButton.getItems().add(menuItem);

        }

        MenuButton analysisChooseButton = new MenuButton("Choose Area");
        for (Config currentAnalysisConfig : testConfig.loadAnalyseConfigs()) {

            MenuItem menuItem = new MenuItem(currentAnalysisConfig.toString());
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (buttonsAndField.getChildren().size()>2){
                        buttonsAndField.getChildren().remove(2);
                    }
                    buttonsAndField.getChildren().add(generateSingleAnalysisArea(new Game(currentAnalysisConfig)));
                }
            });
            analysisChooseButton.getItems().add(menuItem);

        }

        Button runAI = new Button("solve");
        runAI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("algo: " + selectedVicAlgo + "  Game: " + game + "  State "+ game.getState() + "   Vic: " + game.getState().getBoard().getVictoryPoint());
                executeNextSequence();

             }
        });

        HBox allButtons = new HBox(algorithmChooseButton, analysisChooseButton , runAI );
        buttonsAndField.getChildren().add(allButtons);
        buttonsAndField.getChildren().add(analysisChooseButton);
        return buttonsAndField;
    }

    public HBox generateSingleAnalysisArea(Game game){

        this.game =game;
        this.ai = new AI(game);

        ai.setSelectedVicAlgorithm(selectedVicAlgo);

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

        HBox hBoxAllButtons = drawButtons();
        HBox gridAndMoveList = new HBox(boardGrid , movelistScrollPane);
        VBox vBoxAll = new VBox(gridAndMoveList, hBoxAllButtons);

        return new HBox(vBoxAll);

    }
    //analysis code ends here


    //Main Game
    public void playGame(Stage gameplayWindow ) {

        //Basic UI Setup
        gameplayWindow.setTitle("Ricochet Robots");
        gameplayWindow.setResizable(true);

        drawEmptyBoard();
        drawObstacles();
        drawVP();
        drawRobots();

        movelistScrollPane = drawListOfMoves();
        HBox gridAndMoveList = new HBox(boardGrid , movelistScrollPane);
        HBox hBoxAllButtons = drawButtons();
        VBox vBoxAll = new VBox(gridAndMoveList, hBoxAllButtons);
        //Scene scene = new Scene(vbox,(int)(Screen.getPrimary().getBounds().getWidth()),(int)(Screen.getPrimary().getBounds().getHeight()));
        Scene scene = new Scene(vBoxAll, 950, 900);

        generateKeyhandlers(scene);

        gameplayWindow.setScene(scene);
        gameplayWindow.setX(970);
        gameplayWindow.setY(40);
        gameplayWindow.show();

        // analysisButton.fire();
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

    public void drawEmptyBoard(){

        boardGrid = new GridPane();
        boardGrid.setHgap(0);
        boardGrid.setVgap(0);
        boardGrid.setPadding(new Insets(10, 10, 0, 10));

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

        revertLastMoveButton = drawRevertButton();
        analysisButton = drawAnalysisButton();

        HBox hBoxColor = drawColorButtons();
        HBox hBoxDirection = drawDirectionButtons();

        moveScore.setText("Moves: "+game.getState().getMoveList().size() );



        HBox hBoxAllButtons = new HBox(analysisButton, score, revertLastMoveButton , hBoxColor, hBoxDirection , moveScore);
        hBoxAllButtons.setSpacing(50);
        hBoxAllButtons.setAlignment(Pos.BASELINE_CENTER);

        return hBoxAllButtons;
    }

    public Button drawAnalysisButton(){
        analysisButton.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                anaWindow.createWindow();
            }
        });

        return analysisButton;
    }


    //Searches for TreeSearch solution if successful visSeq will be refilled
    //otherwise visSeq is emptyList
    public void executeNextSequence(){
        int movesUsed = -1;
        long timerBegin = System.currentTimeMillis();

        while (!this.visSeq.isEmpty()){
            executeNextMove();
            redrawMovelist();
            redrawRobots();
        }
        this.visSeq=ai.createSeq().getMoveCommands();
        if (this.visSeq.isEmpty()){
            this.visSeq=new ArrayList<MoveCommand>();
            System.out.println("no result");
            if(ai.isInterrupted()){
                anaWindow.updateAnalysisLabel(10,0,0,1);
            }else{
                anaWindow.updateAnalysisLabel(10,0,1,0);
            }
            game.forceNewVictoryPoint();
            this.moveListlist = new ArrayList<>();
            redrawMovelist();
            redrawRobots();
            return;
        }
        else{
            redrawMovelist();
            redrawRobots();
            System.out.println("Solution found");
            movesUsed = this.visSeq.size();
            System.out.println("Moves: "+ movesUsed);

        }
        long timerEnd = System.currentTimeMillis();
        long timeDiff = timerEnd-timerBegin;
        float timeUsed = (float) timeDiff* (float) 0.001;
        anaWindow.updateAnalysisLabel(timeUsed, movesUsed,0,0);
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

    //Visually Moves the Robot, Calls certain redraw Methods
    public boolean moveRobot(Direction selectedDirection, Colors selectedColor){
        System.out.println("Selected: " + selectedColor + " " + selectedDirection);
        MoveCommand mCmd = new MoveCommand(selectedColor, selectedDirection);
        fillMovelist();

        // used to determine if the robot hit a wall  (then returns -1)
        //clears movelist if victorypoint is reached
        switch(game.moveRobot(mCmd)){
            case -1:
                crashWall =true;
                break;
            case 0:
                crashWall = false;
                break;
            case 1:
                crashWall=false;
                this.moveListlist = new ArrayList<>();
                redrawMovelist();
                break;
        }
        redrawRobots();
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



    //Getter and Setter madness
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

    public AI getAi() {
        return ai;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }
}