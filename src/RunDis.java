import Data.*;
import Data.Enums.Color;
import Data.Enums.ObsType;
import Data.Obstacle;
import Data.Robot;
import Data.VictorySpawn;
import Logic.Game;
import Logic.Gamestate;
import View.Display;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Martin Eberle aka WillShakesBeer on 23.11.2021.
 */
public class RunDis {

    public static void main (String[] args){

        Game game = startDefaultGame();


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



    public static game startDefaultGame(){
        ArrayList<Robot> robotList = new ArrayList<Robot>();

        robotList.add(new Data.Robot(new Coord(0,0), Data.Enums.Color.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Data.Enums.Color.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Data.Enums.Color.YELLOW));
        robotList.add(new Robot(new Coord(15,15), Color.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        obstacleList.add(new Obstacle(new Coord(0,0),new Coord(3,4), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,0),new Coord(0,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,0),new Coord(0,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,0),new Coord(0,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,0),new Coord(0,0), ObsType.HORIZONTAL));

        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        victorySpawns.add(new VictorySpawn(new Coord(4,3),Color.RED));
        victorySpawns.add(new VictorySpawn(new Coord(13,4),Color.RED));
        victorySpawns.add(new VictorySpawn(new Coord(1,12),Color.RED));
        victorySpawns.add(new VictorySpawn(new Coord(11,14),Color.RED));
        victorySpawns.add(new VictorySpawn(new Coord(1,5),Color.BLUE));
        victorySpawns.add(new VictorySpawn(new Coord(3,9),Color.BLUE));
        victorySpawns.add(new VictorySpawn(new Coord(10,3),Color.BLUE));
        victorySpawns.add(new VictorySpawn(new Coord(5,10),Color.YELLOW));
        victorySpawns.add(new VictorySpawn(new Coord(12,2),Color.YELLOW));
        victorySpawns.add(new VictorySpawn(new Coord(12,9),Color.YELLOW));
        victorySpawns.add(new VictorySpawn(new Coord(4,14),Color.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(5,6),Color.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(11,5),Color.GREEN));


    }




}
