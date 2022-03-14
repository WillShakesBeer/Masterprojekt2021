package View;

import Data.Testing.SavedConfigs;
import Logic.AI;
import Logic.Game;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Created by Martin Eberle aka WillShakesBeer on 11.03.2022.
 */
public class AnalysisScene {
    Game game;
    AI ai;

    AnalysisScene(Game game){
        this.game = game;
    }

    public void generateAnalysisScene(){
        ai = new AI(game);
        ai.setAiDefaults();
    }
}
