import Data.Coord;
import Data.Enums.Color;
import Data.Obstacle;
import Data.Robot;
import Data.VictorySpawn;
import Logic.Game;
import View.Display;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class RunDis {
    public static void main (String[] args){

        ArrayList<Robot> robotList = new ArrayList<Robot>();

        robotList.add(new Data.Robot(new Coord(0,0), Data.Enums.Color.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Data.Enums.Color.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Data.Enums.Color.YELLOW));
        robotList.add(new Robot(new Coord(15,15), Color.GREEN));

        //obstacle list null for error reasons
        Game game = new Game(robotList,null,null);
        Display display = new Display(game);



        final StringBuilder wordSearch = new StringBuilder();
        for (int i = 0; i < game.getState().getBoard().getHeight(); i++){
            for (int j = 0; j < game.getState().getBoard().getLength(); j++){
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
