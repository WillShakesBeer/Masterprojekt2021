import Data.*;
import Logic.AI;
import View.*;
import Data.Enums.Colors;
import Data.Enums.ObsType;
import Data.GameConfig.Config;
import Data.Obstacle;
import Data.Robot;
import Data.VictorySpawn;
import Logic.Game;

import java.util.ArrayList;
import javafx.application.Application;

import javafx.stage.Stage;


/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class RunDis extends Application {

    public static void main (String[] args){
        launch(args);
    }


    public void start(final Stage startWindow){
        DisplayFx displayFx = new DisplayFx();
        displayFx.runGame(startWindow);
    }

}
