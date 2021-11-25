import Data.*;
import Data.Enums.Color;
import Data.Enums.ObsType;
import Data.GameConfig.Config;
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



    public static Config DefaultGame(){
        ArrayList<Robot> robotList = new ArrayList<Robot>();

        robotList.add(new Data.Robot(new Coord(0,0), Data.Enums.Color.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Data.Enums.Color.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Data.Enums.Color.YELLOW));
        robotList.add(new Robot(new Coord(15,15), Color.GREEN));

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

        Config config = new Config(robotList,obstacleList,victorySpawns,0,15);

        return config;
    }




}
