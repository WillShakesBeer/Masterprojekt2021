package View;

import Data.Enums.SetupAlgorithms;
import Data.Enums.VicAlgorithms;
import Data.RunConfig;

import java.util.ArrayList;

public class DisplayUtility {

    //Contains all used configs
    //Every used config contains all stats that have been collected while using that config
    ArrayList<RunConfig> statList = new ArrayList<RunConfig>();

    public ArrayList<String> getAverageList(){
        ArrayList<String> averageList = new ArrayList<String>();
        return averageList;
    }

    public void updateStats(RunStat runStat){
        boolean added = false;
        for(RunConfig stat : statList){
            if(runStat.getConfig().equals(stat)){
                stat.addRunstat(runStat);
                added=true;
            }
        }
        if(!added){
            RunConfig newConfig = runStat.getConfig();
            newConfig.addRunstat(runStat);
            statList.add(newConfig);
        }
    }





    public String intToVicAlgo(int algoInt){
        String algoString;
        switch (algoInt) {
            case 0:  algoString = "Depth First Search" + "\t";
                break;
            case 1:  algoString = "Random First Search";
                break;
            case 2:  algoString = "Breadth First Search" + "\t";
                break;
            case 3:  algoString = "Air First Search" + "\t";
                break;
            case 4:  algoString = "Breadth First Search preloaded Setups" + "\t";
                break;
            case 5:  algoString = "Generic Breadth" + "\t";
                break;
            case 6:  algoString = "Generic Depth" + "\t";
                break;
            default: algoString = "undefined";
        }
        return algoString;
    }
    public String intToSetupHeuristic(int algoInt){
        String algoString;
        switch (algoInt) {
            case 0:  algoString = "Depth First Search" + "\t";
                break;
            case 1:  algoString = "Random First Search";
                break;
            case 2:  algoString = "Breadth First Search" + "\t";
                break;
            case 3:  algoString = "Position Score" + "\t";
                break;
            case 4:  algoString = "Cross Block Score" + "\t";
                break;
            default: algoString = "none";
        }
        return algoString;
    }
    public String enumToVicAlgo(VicAlgorithms algoEnum){
        String algoString;
        switch (algoEnum) {
            case DFS:  algoString = "Depth First Search" + "\t";
                break;
            case RFS:  algoString = "Random First Search";
                break;
            case BFS:  algoString = "Breadth First Search" + "\t";
                break;
            case AIR_FS:  algoString = "Air First Search" + "\t";
                break;
            case BFS_PRELOAD:  algoString = "Breadth First Search preloaded Setups" + "\t";
                break;
            case BGENERIC:  algoString = "Generic Breadth" + "\t";
                break;
            case DGENERIC:  algoString = "Generic Depth" + "\t";
                break;
            default: algoString = "undefined";
        }
        return algoString;
    }
    public String enumToSetupHeuristic(SetupAlgorithms algoEnum){
        String algoString;
        switch (algoEnum) {
            case DFS:  algoString = "Depth First Search" + "\t";
                break;
            case RFS:  algoString = "Random First Search";
                break;
            case BFS:  algoString = "Breadth First Search" + "\t";
                break;
            case POS_SCORE:  algoString = "Position Score" + "\t";
                break;
            case CROSS_SCORE:  algoString = "Cross Block Score" + "\t";
                break;
            default: algoString = "none";
        }
        return algoString;
    }

    public ArrayList<RunConfig> getStatList() {
        return statList;
    }

    public void setStatList(ArrayList<RunConfig> statList) {
        this.statList = statList;
    }
}
