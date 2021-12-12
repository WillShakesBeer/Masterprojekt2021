import Data.*;
import Data.Enums.Colors;
import Data.Enums.ObsType;
import Data.GameConfig.Config;
import Data.Obstacle;
import Data.Robot;
import Data.VictorySpawn;
import Logic.Game;
import View.Display;
import java.awt.*;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class RunDis extends Application {


    public static void main (String[] args){
        //idk men SwingUtilities?
        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });*/

        launch();
        Game game = new Game(DefaultGame());
        //updateVisuals(game);
    }

    public void start(Stage primaryStage){
        primaryStage.setTitle("Ricochet Robots");

        //Starting with Input UI
        //Color Selection with Radio Buttons
        //Direction with Buttons

        RadioButton red = new RadioButton("Red");
        RadioButton green = new RadioButton("Green");
        RadioButton blue = new RadioButton("Blue");
        RadioButton yellow = new RadioButton("yellow");

        VBox vbox = new VBox(red,green,blue,yellow);

        ToggleGroup radioGroup = new ToggleGroup();

        red.setToggleGroup(radioGroup);
        green.setToggleGroup(radioGroup);
        blue.setToggleGroup(radioGroup);
        yellow.setToggleGroup(radioGroup);

        Scene scene = new Scene(vbox,600,400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    //JFrame = dirty Swing shit ?!
    //lass mal kleine Size nehmen zum debuggen
    //Idk men lass doch einfach starten wie ne normale FX App siehe Rundis.start(Stage)
    private static void initAndShowGUI() {
        JFrame frame = new JFrame("Ricochet Robots");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width,screenSize.height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Stage stage = new Stage();
        stage.show();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(stage);
            }
        });
    }

    private static void initFX(Stage stage) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        stage.setScene(scene);
    }

    private static Scene createScene() {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
        Text  text  =  new  Text();

        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Welcome JavaFX!");

        root.getChildren().add(text);

        return (scene);
    }




    //deprecated
    public static void updateVisuals(Game game){
        Display display = new Display(game);
        /*game.moveRobot(new MoveCommand(Color.RED, Direction.RIGHT));
        game.moveRobot(new MoveCommand(Color.RED, Direction.UP));
        //updateVisuals(display);
        display.getMove();*/

        updateVisuals(display);
        while (true){
            MoveCommand mCmd=display.getMove();
            game.moveRobot(mCmd);
            updateVisuals(display);
        }

    }

    //deprecated
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
