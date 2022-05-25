package View;

import Data.Enums.Algorithms;

/**
 * Created by Martin Eberle aka WillShakesBeer on 02.03.2022.
 */

//Should probably be located in Data
public class RunStat {
    Algorithms heuristic;
    int depthLimit;
    int setupLimit;
    long timeNeeded;
    int movesUsed;

    public RunStat(Algorithms heuristic, int depthLimit, int setupLimit, long timeNeeded, int movesUsed){
        this.heuristic=heuristic;
        this.depthLimit=depthLimit;
        this.setupLimit=setupLimit;
        this.timeNeeded=timeNeeded;
        this.movesUsed=movesUsed;

    }

    public Algorithms getAlgorithm() {
        return heuristic;
    }

    public void setHeuristic(Algorithms heuristic) {
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

    public long getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(long timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public int getMovesUsed() {
        return movesUsed;
    }

    public void setMovesUsed(int movesUsed) {
        this.movesUsed = movesUsed;
    }
}