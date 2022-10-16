package Data.Testing;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.ObsType;
import Data.GameConfig.Config;

import java.util.ArrayList;

/**
 * Created by Martin Eberle aka WillShakesBeer on 11.03.2022.
 */
public class SavedConfigs {

    ArrayList<Config> analyseConfigs = new ArrayList<>();
    Config defaultGameConfig;

    public SavedConfigs(){
        generateTestConfigs();
    }
    public void generateTestConfigs(){
        generateDefaultGame();
        generateAnalysisConfigs();
    }

    public void generateDefaultGame(){
        int length = 15;
        int height = 15;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,8), Colors.RED));
        robotList.add(new Data.Robot(new Coord(5,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(8,15), Colors.GREEN));

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
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,3), ObsType.VERTICAL));
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

        VictoryPoint testVic = new VictoryPoint(new Coord(3, 9),Colors.BLUE);
        //VictoryPoint testVic1 = new VictoryPoint(new Coord(12, 9),Colors.YELLOW);
        //VictoryPoint testVic2 = new VictoryPoint(new Coord(11, 5),Colors.GREEN);
        //VictoryPoint testVic3 = new VictoryPoint(new Coord(1, 12),Colors.RED);
        defaultGameConfig = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);

    }

    public void generateAnalysisConfigs(){
        analyseConfigs.add(generateTestConfig00());
        analyseConfigs.add(generateTestConfig01());
        analyseConfigs.add(generateTestConfig02());
        analyseConfigs.add(generateTestConfig03());
        analyseConfigs.add(generateTestConfig04());
        analyseConfigs.add(generateTestConfig05());
        analyseConfigs.add(generateTestConfig06());
        analyseConfigs.add(generateTestConfig07());
        analyseConfigs.add(generateTestConfig08());
        analyseConfigs.add(generateTestConfig09());
        analyseConfigs.add(generateTestConfig10());
        analyseConfigs.add(generateTestConfig11());
        analyseConfigs.add(generateTestConfig12());
        analyseConfigs.add(generateTestConfig13());
        analyseConfigs.add(generateTestConfig14());
        analyseConfigs.add(generateTestConfig15());
        /*
        analyseConfigs.add(generateTestConfig16());
        */


    }
    public Config generateTestConfig00(){
        int length = 2;
        int height = 2;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,2), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(2,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(2,2), Colors.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();

        obstacleList.add(new Obstacle(new Coord(0,0),new Coord(1,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(1,0),new Coord(2,0), ObsType.HORIZONTAL));

        obstacleList.add(new Obstacle(new Coord(0,1),new Coord(0,2), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(2,1),new Coord(2,2), ObsType.VERTICAL));

        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        VictorySpawn victorySpawn = new VictorySpawn(new Coord(1,1), Colors.GREEN);
        victorySpawns.add(victorySpawn);

        VictoryPoint testVic = new VictoryPoint(new Coord(1 , 1),Colors.GREEN);

        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig01(){
        int length = 15;
        int height = 2;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(1,1), Colors.RED));
        robotList.add(new Data.Robot(new Coord(2,1), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(3,1), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(0,1), Colors.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();

        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        VictorySpawn victorySpawn = new VictorySpawn(new Coord(14 , 1),Colors.GREEN);
        victorySpawns.add(victorySpawn);

        VictoryPoint testVic = new VictoryPoint(new Coord(14 , 1),Colors.GREEN);

        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig02(){
        int length = 15;
        int height = 2;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(1,1), Colors.RED));
        robotList.add(new Data.Robot(new Coord(2,1), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(3,1), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(0,1), Colors.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        obstacleList.add(new Obstacle(new Coord(14,0),new Coord(15,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(14,2),new Coord(15,2), ObsType.HORIZONTAL));


        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        VictorySpawn victorySpawn = new VictorySpawn(new Coord(14 , 1),Colors.GREEN);
        victorySpawns.add(victorySpawn);

        VictoryPoint testVic = new VictoryPoint(new Coord(14 , 1),Colors.GREEN);

        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig03(){
        int length = 15;
        int height = 2;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(1,1), Colors.RED));
        robotList.add(new Data.Robot(new Coord(2,1), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(3,1), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(0,1), Colors.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        obstacleList.add(new Obstacle(new Coord(13,1),new Coord(14,1), ObsType.HORIZONTAL));


        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        VictorySpawn victorySpawn = new VictorySpawn(new Coord(14 , 1),Colors.GREEN);
        victorySpawns.add(victorySpawn);

        VictoryPoint testVic = new VictoryPoint(new Coord(14 , 1),Colors.GREEN);

        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig04(){
        int length = 15;
        int height = 2;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(1,1), Colors.RED));
        robotList.add(new Data.Robot(new Coord(2,1), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(3,1), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(0,1), Colors.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        obstacleList.add(new Obstacle(new Coord(10,0),new Coord(11,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(11,1),new Coord(12,1), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(12,2),new Coord(13,2), ObsType.HORIZONTAL));


        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        VictorySpawn victorySpawn = new VictorySpawn(new Coord(14 , 1),Colors.GREEN);
        victorySpawns.add(victorySpawn);

        VictoryPoint testVic = new VictoryPoint(new Coord(14 , 1),Colors.GREEN);

        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig05(){
        int length = 15;
        int height = 2;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(1,1), Colors.RED));
        robotList.add(new Data.Robot(new Coord(2,1), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(3,1), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(0,1), Colors.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        obstacleList.add(new Obstacle(new Coord(7,0),new Coord(8,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(7,1),new Coord(8,1), ObsType.HORIZONTAL));

        obstacleList.add(new Obstacle(new Coord(8,1),new Coord(9,1), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(8,2),new Coord(9,2), ObsType.HORIZONTAL));

        obstacleList.add(new Obstacle(new Coord(9,0),new Coord(10,0), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(9,1),new Coord(10,1), ObsType.HORIZONTAL));

        obstacleList.add(new Obstacle(new Coord(10,1),new Coord(11,1), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(10,2),new Coord(11,2), ObsType.HORIZONTAL));




        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        VictorySpawn victorySpawn = new VictorySpawn(new Coord(14 , 1),Colors.GREEN);
        victorySpawns.add(victorySpawn);

        VictoryPoint testVic = new VictoryPoint(new Coord(14 , 1),Colors.GREEN);

        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig06(){
        int length = 7;
        int height = 7;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,7), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(7,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(7,7), Colors.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        obstacleList.add(new Obstacle(new Coord(1,0),new Coord(1,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(2,0),new Coord(2,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(3,0),new Coord(3,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(4,0),new Coord(4,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(5,0),new Coord(5,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(6,0),new Coord(6,1), ObsType.VERTICAL));

        obstacleList.add(new Obstacle(new Coord(1,6),new Coord(1,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(2,6),new Coord(2,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(3,6),new Coord(3,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(4,6),new Coord(4,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(5,6),new Coord(5,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(6,6),new Coord(6,6), ObsType.VERTICAL));

        obstacleList.add(new Obstacle(new Coord(0,1),new Coord(1,1), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(1,2), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,3),new Coord(1,3), ObsType.HORIZONTAL));

        obstacleList.add(new Obstacle(new Coord(0,5),new Coord(1,5), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,6),new Coord(1,6), ObsType.HORIZONTAL));


        obstacleList.add(new Obstacle(new Coord(6,1),new Coord(7,1), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,2),new Coord(7,2), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,3),new Coord(7,3), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,4),new Coord(7,4), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,5),new Coord(7,5), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,6),new Coord(7,6), ObsType.HORIZONTAL));


        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        victorySpawns.add(new VictorySpawn(new Coord(1,1), Colors.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(1,6), Colors.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(6,1), Colors.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(6,6), Colors.GREEN));

        VictoryPoint testVic = new VictoryPoint(new Coord(1 , 1),Colors.GREEN);

        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig07(){
        int length = 15;
        int height = 15;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(15,15), Colors.GREEN));

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
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,3), ObsType.VERTICAL));
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

        VictoryPoint testVic = new VictoryPoint(new Coord(11, 5),Colors.GREEN);
        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig08(){
        int length = 15;
        int height = 15;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(15,15), Colors.GREEN));

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
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,3), ObsType.VERTICAL));
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


        VictoryPoint testVic = new VictoryPoint(new Coord(12, 9),Colors.YELLOW);
        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig09(){
        int length = 15;
        int height = 15;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(15,15), Colors.GREEN));

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
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,3), ObsType.VERTICAL));
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


        VictoryPoint testVic = new VictoryPoint(new Coord(11, 5),Colors.GREEN);
        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig10(){
        int length = 15;
        int height = 15;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(15,15), Colors.GREEN));

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
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,3), ObsType.VERTICAL));
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

        VictoryPoint testVic = new VictoryPoint(new Coord(1, 12),Colors.RED);
        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    //rep
    public Config generateTestConfig11(){
        int length = 7;
        int height = 7;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,7), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(7,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(7,7), Colors.GREEN));

        ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
        obstacleList.add(new Obstacle(new Coord(1,0),new Coord(1,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(2,0),new Coord(2,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(3,0),new Coord(3,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(4,0),new Coord(4,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(5,0),new Coord(5,1), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(6,0),new Coord(6,1), ObsType.VERTICAL));

        obstacleList.add(new Obstacle(new Coord(1,6),new Coord(1,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(2,6),new Coord(2,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(3,6),new Coord(3,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(4,6),new Coord(4,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(5,6),new Coord(5,6), ObsType.VERTICAL));
        obstacleList.add(new Obstacle(new Coord(6,6),new Coord(6,6), ObsType.VERTICAL));

        obstacleList.add(new Obstacle(new Coord(0,1),new Coord(1,1), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(1,2), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,3),new Coord(1,3), ObsType.HORIZONTAL));

        obstacleList.add(new Obstacle(new Coord(0,5),new Coord(1,5), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(0,6),new Coord(1,6), ObsType.HORIZONTAL));


        obstacleList.add(new Obstacle(new Coord(6,1),new Coord(7,1), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,2),new Coord(7,2), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,3),new Coord(7,3), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,4),new Coord(7,4), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,5),new Coord(7,5), ObsType.HORIZONTAL));
        obstacleList.add(new Obstacle(new Coord(6,6),new Coord(7,6), ObsType.HORIZONTAL));


        ArrayList<VictorySpawn> victorySpawns = new ArrayList<VictorySpawn>();
        victorySpawns.add(new VictorySpawn(new Coord(1,1), Colors.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(1,6), Colors.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(6,1), Colors.GREEN));
        victorySpawns.add(new VictorySpawn(new Coord(6,6), Colors.GREEN));

        VictoryPoint testVic = new VictoryPoint(new Coord(1 , 1),Colors.GREEN);

        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig12(){
        int length = 15;
        int height = 15;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(15,15), Colors.GREEN));

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
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,3), ObsType.VERTICAL));
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

        VictoryPoint testVic = new VictoryPoint(new Coord(11, 5),Colors.GREEN);
        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig13(){
        int length = 15;
        int height = 15;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(15,15), Colors.GREEN));

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
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,3), ObsType.VERTICAL));
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


        VictoryPoint testVic = new VictoryPoint(new Coord(12, 9),Colors.YELLOW);
        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig14(){
        int length = 15;
        int height = 15;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(15,15), Colors.GREEN));

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
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,3), ObsType.VERTICAL));
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


        VictoryPoint testVic = new VictoryPoint(new Coord(11, 5),Colors.GREEN);
        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }
    public Config generateTestConfig15(){
        int length = 15;
        int height = 15;
        ArrayList<Robot> robotList = new ArrayList<Robot>();
        robotList.add(new Data.Robot(new Coord(0,0), Colors.RED));
        robotList.add(new Data.Robot(new Coord(0,15), Colors.BLUE));
        robotList.add(new Data.Robot(new Coord(15,0), Colors.YELLOW));
        robotList.add(new Data.Robot(new Coord(15,15), Colors.GREEN));

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
        obstacleList.add(new Obstacle(new Coord(0,2),new Coord(0,3), ObsType.VERTICAL));
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

        VictoryPoint testVic = new VictoryPoint(new Coord(1, 12),Colors.RED);
        Config config = new Config(robotList,obstacleList,victorySpawns,length,height,testVic);
        return config;
    }




    public ArrayList<Config> loadAnalyseConfigs() {
        return analyseConfigs;
    }

    public void setAnalyseConfigs(ArrayList<Config> analyseConfigs) {
        this.analyseConfigs = analyseConfigs;
    }

    public Config loadDefaultGameConfig() {
        return defaultGameConfig;
    }

    public void setDefaultGameConfig(Config defaultGameConfig) {
        this.defaultGameConfig = defaultGameConfig;
    }
}
