package View;

import Data.Enums.SetupAlgorithms;
import Data.Enums.VicAlgorithms;
import Data.RunConfig;

/**
 * Created by Martin Eberle aka WillShakesBeer on 02.03.2022.
 */

//Should probably be located in Data
//contains data from one analysis run (one iteration)
public class RunStat {

    RunConfig config;
    VicAlgorithms heuristic;
    SetupAlgorithms setupHeuristic;
    int depthLimit;
    int setupLimit;
    float timeNeeded;
    int movesUsed;

    int timesFailed;
    int amntTimeout;

    public RunStat(RunConfig config, float timeNeeded, int movesUsed, int timesFailed,int amntTimeout){
        this.config= config;
        this.timeNeeded=timeNeeded;
        this.movesUsed=movesUsed;
        this.heuristic=config.getVicAlgo();
        this.setupHeuristic=config.getSetupAlgo();
        this.depthLimit=config.getDepthLimit();
        this.setupLimit=config.getSetupLimit();
        this.timesFailed=timesFailed;
        this.amntTimeout=amntTimeout;
    }

    public VicAlgorithms getAlgorithm() {
        return heuristic;
    }

    public void setHeuristic(VicAlgorithms heuristic) {
        this.heuristic = heuristic;
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

    public float getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(float timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public int getMovesUsed() {
        return movesUsed;
    }

    public void setMovesUsed(int movesUsed) {
        this.movesUsed = movesUsed;
    }

    public VicAlgorithms getHeuristic() {
        return heuristic;
    }

    public int getTimesFailed() {
        return timesFailed;
    }

    public void setTimesFailed(int timesFailed) {
        this.timesFailed = timesFailed;
    }

    public SetupAlgorithms getSetupHeuristic() {
        return setupHeuristic;
    }

    public void setSetupHeuristic(SetupAlgorithms setupHeuristic) {
        this.setupHeuristic = setupHeuristic;
    }

    public RunConfig getConfig() {
        return config;
    }

    public void setConfig(RunConfig config) {
        this.config = config;
    }

    public int getAmntTimeout() {
        return amntTimeout;
    }

    public void setAmntTimeout(int amntTimeout) {
        this.amntTimeout = amntTimeout;
    }
}
