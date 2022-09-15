package Data;

import Data.Enums.SetupAlgorithms;
import Data.Enums.VicAlgorithms;
import View.DisplayUtility;
import View.RunStat;


//Contains Runparameter
//Can collect all stats that have been analysed using the same Parameters
public class RunConfig {
    VicAlgorithms vicAlgo;
    SetupAlgorithms setupAlgo;
    int depthLimit;
    int setupLimit;
    float timeUsed;
    int timesFailed;
    int amntTimeout;
    int iterations;
    int movesUsed;

    public RunConfig(VicAlgorithms vicAlgo, SetupAlgorithms setupAlgo, int depthLimit, int setupLimit){
        this.vicAlgo=vicAlgo;
        this.setupAlgo=setupAlgo;
        this.depthLimit=depthLimit;
        this.setupLimit=setupLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RunConfig config = (RunConfig) o;
        boolean result = vicAlgo == config.getVicAlgo() && setupAlgo == config.getSetupAlgo() && depthLimit == config.getDepthLimit() && setupLimit== config.getSetupLimit();
        return result;
    }

    public void addRunstat(RunStat stat){
        iterations = iterations+1;
        timesFailed = timesFailed+stat.getTimesFailed();
        amntTimeout = amntTimeout + stat.getAmntTimeout();
        if(stat.getTimesFailed()==0 && stat.getAmntTimeout() ==0){
            movesUsed = movesUsed+ stat.getMovesUsed();
            timeUsed = timeUsed + stat.getTimeNeeded();
        }
    }

    public String toString(){
        float avgTimeNeeded = (float) timeUsed / (float) (iterations-timesFailed-amntTimeout);
        float avgMovesNeeded = (float) movesUsed / (float) (iterations-timesFailed-amntTimeout);
        String result = "";
        DisplayUtility utility = new DisplayUtility();
        result = result + utility.enumToVicAlgo(this.vicAlgo) +"\t";
        result = result + utility.enumToSetupHeuristic(this.setupAlgo) +"\t";
        result = result + "Time needed " + avgTimeNeeded+"\t";
        result = result + "Moves used " + avgMovesNeeded +"\t";
        result = result + "Times failed " +timesFailed + "\t";
        result = result + "Timeouts " +amntTimeout + "\t";
        result = result + "Iterations " +iterations + "\n";
        return result;
    }

    public VicAlgorithms getVicAlgo() {
        return vicAlgo;
    }

    public void setVicAlgo(VicAlgorithms vicAlgo) {
        this.vicAlgo = vicAlgo;
    }

    public SetupAlgorithms getSetupAlgo() {
        return setupAlgo;
    }

    public void setSetupAlgo(SetupAlgorithms setupAlgo) {
        this.setupAlgo = setupAlgo;
    }

    public int getDepthLimit() {
        return depthLimit;
    }

    public void setDepthLimit(int depthLimit) {
        this.depthLimit = depthLimit;
    }

    public int getSetupLimit() {
        return setupLimit;
    }

    public void setSetupLimit(int setupLimit) {
        this.setupLimit = setupLimit;
    }
}
