import Data.*;
import Data.Enums.Color;
import Logic.Game;
import Logic.Gamestate;
import View.Display;

import java.util.ArrayList;


/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class RunDis {
    public static void main (String[] args){





        final StringBuilder wordSearch = new StringBuilder();
        for (int i = 0; i < display.getGame().getState().getBoard().getHeight(); i++){
            for (int j = 0; j < display.getGame().getState().getBoard().getLength(); j++){
                wordSearch.append(display.displayGame()[i][j]).append('\t');
            }
            wordSearch.append('\n');
        }
        wordSearch.toString();
        System.out.println(wordSearch);
    }



    public void startDefaultGame(){
        ArrayList<Robot> robotList = new ArrayList<Robot>();

        robotList.add(new Data.Robot(new Coord(0,0), Data.Enums.Color.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Data.Enums.Color.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Data.Enums.Color.YELLOW));
        robotList.add(new Robot(new Coord(15,15), Color.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
    }



}
