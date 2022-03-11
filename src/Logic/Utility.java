package Logic;

import Data.Coord;
import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.MoveCommand;
import Data.Robot;
import Data.VictoryPoint;

import java.util.ArrayList;

public class Utility {

    Coord currVicCoord;
    Game game;

    ArrayList<ArrayList<Coord>> critPositions;
    ArrayList<ArrayList<Coord>> critHorizontalPath;
    ArrayList<ArrayList<Coord>> critVerticalPath;
    ArrayList<ArrayList<Coord>> crossBlockPostitions;
    int maxDegree=3;

    public Utility(Game game){
        this.game=game;
        currVicCoord=this.game.getState().getBoard().getVictoryPoint().getCoord();
        critPositions=null;
        crossBlockPostitions=new ArrayList<ArrayList<Coord>>();
        for(int i=0;i<maxDegree;i++){
            crossBlockPostitions.add(new ArrayList<Coord>());
        }
    }

    public void loadCriticalPositions(int degree){
        this.critPositions=getCriticalPositions(degree);
    }

    public void loadCriticalPath(int degree){
        this.critHorizontalPath = getCriticalPath(degree).get(1);
        this.critVerticalPath = getCriticalPath(degree).get(0);
    }


    //checks if a Seq contains moves that end up crashing a Wall
    //1 => no crash
    //-1 => crash
    //0 => Point Scored
    public int isSeqSmart(ArrayList<MoveCommand> moveCommands){
        for(int i =0;i<moveCommands.size();i++){
            if(game.checkMove(moveCommands.get(i))==1){
                game.resetGame();
                return 0;
            }
            int result = game.moveRobot(moveCommands.get(i));
            if(result==-1){
                game.resetGame();
                return -1;
            }
        }
        game.resetGame();
        return 1;
    }


    // Gives feedback if Seq leads to achieving a Victorypoint
    //0=> Victory achieved
    //-1 => No Movement
    //-2 => Crash Wall
    //positive Int: AmountOfMoves*100+DistToVictoryPoint
    public int isSeqSmartVicColor(ArrayList<MoveCommand> moveCommands){
        if(moveCommands.size()==0){
            return -1;
        }
        Colors color=moveCommands.get(moveCommands.size()-1).getColor();
        for(int i =0;i<moveCommands.size();i++){
            if(game.checkMove(moveCommands.get(i))==1){
                game.resetGame();
                return 0;
            }
            int result = game.moveRobot(moveCommands.get(i));
            if(result==-1){
                game.resetGame();
                return -2;
            }

        }
        double vicDist=-1;
        ArrayList<Robot> robots = game.getState().getBoard().getRobots();
        Robot vicRobot=null;
        for(Robot robot: robots){
            if(robot.getColor()==color){
                vicRobot = robot;
            }
        }
        VictoryPoint vicPoint = this.game.getState().getBoard().getVictoryPoint();
        if(vicRobot!=null){
            double xDist= Math.pow((double) (vicRobot.getCoord().getX()-vicPoint.getCoord().getX()),2);
            double yDist= Math.pow((double) (vicRobot.getCoord().getY()-vicPoint.getCoord().getY()),2);
            vicDist = Math.sqrt(xDist+yDist);
        }
        game.resetGame();
        vicDist=vicDist+100;
        return (int)(vicDist);
    }


    // Gives feedback if Seq leads to a meaningful Setup
    //-1 => No Movement
    //-2 => Crash Wall
    //positive Float: PositionScore/AmountOfMoves
    //PositionScore := CritcalPostionsTaken/CriticalDegree
    //Does only Consider cirticalDegree of a maximum of 3
    public float isSeqSmartSetup(ArrayList<MoveCommand> moveCommands){

        if(critPositions==null){
            loadCriticalPositions(maxDegree);
        }
        float posScore=0;
        float setupScore;
        int maxDegree=3;
        if(moveCommands.size()==0){
            return -1;
        }
        Colors color=moveCommands.get(moveCommands.size()-1).getColor();
        for(int i =0;i<moveCommands.size();i++){
            int result = game.moveRobot(moveCommands.get(i));
            if(result==-1){
                game.resetGame();
                return -2;
            }

        }
        ArrayList<Robot> robots=this.game.getState().getBoard().getRobots();
        for(Robot currRob:robots){
            Coord currCoord=currRob.getCoord();
            int lowestDegree=-1;
            for(int i=1;i<maxDegree;i++){
                ArrayList<Coord> currCritPosList=critPositions.get(i);
                for(Coord currCritPos: currCritPosList){
                    if(currCritPos.equals(currCoord)){
                        if(lowestDegree==-1 || lowestDegree>i){
                            lowestDegree=i;
                        }
                    }
                }
            }
            if(lowestDegree!=-1){
                posScore=posScore+(1/lowestDegree);
            }
        }
        game.resetGame();
        setupScore=posScore/moveCommands.size();
        return setupScore;
    }


    //checks if a Seq contains moves that end up crashing a Wall
    //1 => no crash
    //-1 => crash
    //0 => Point Scored
    //will RememberCrossings with an Critical Path for Setupheuristic
    public int isSeqSmartRememberCrossings(ArrayList<MoveCommand> moveCommands){
        for(int i =0;i<moveCommands.size();i++){
            if(i==moveCommands.size()-1){
                Colors vicColor =moveCommands.get(i).getColor();
                Coord startPos = game.getState().getBoard().getRobot(vicColor).getCoord();
                Coord collisionPos = game.checkMovement(startPos.clone(), moveCommands.get(i).getDir(), game.getState().getBoard());
                switch (moveCommands.get(i).getDir()){
                    case UP:
                        for (int y = startPos.getY(); y >= collisionPos.getY(); y--) {
                            Coord currCoord=new Coord(startPos.getX(),y);
                            for(int j=0;j<maxDegree;j++){
                                for(Coord critPos: critHorizontalPath.get(j)){
                                    if(currCoord.equals(critPos) && critPos.getY()>0){
                                        this.crossBlockPostitions.get(j).add(new Coord(currCoord.getX(),currCoord.getY()-1));
                                    }
                                }
                            }
                        }
                        break;
                    case DOWN:
                        for (int y = startPos.getY(); y <= collisionPos.getY(); y++) {
                            Coord currCoord=new Coord(startPos.getX(),y);
                            for(int j=0;j<maxDegree;j++){
                                for(Coord critPos: critHorizontalPath.get(j)){
                                    if(currCoord.equals(critPos) && critPos.getY()<15){
                                        this.crossBlockPostitions.get(j).add(new Coord(currCoord.getX(),currCoord.getY()+1));
                                    }
                                }
                            }
                        }
                        break;
                    case RIGHT:
                        for (int x = startPos.getX(); x <= collisionPos.getX(); x++) {
                            Coord currCoord=new Coord(x,startPos.getY());
                            for(int j=0;j<maxDegree;j++){
                                for(Coord critPos: critVerticalPath.get(j)){
                                    if(currCoord.equals(critPos) && critPos.getX()<15){
                                        this.crossBlockPostitions.get(j).add(new Coord(currCoord.getX()+1,currCoord.getY()));
                                    }
                                }
                            }
                        }
                        break;
                    case LEFT:
                        for (int x = startPos.getX(); x >= collisionPos.getX(); x--) {
                            Coord currCoord=new Coord(x,startPos.getY());
                            for(int j=0;j<maxDegree;j++){
                                for(Coord critPos: critVerticalPath.get(j)){
                                    if(currCoord.equals(critPos)&&critPos.getX()>0){
                                        this.crossBlockPostitions.get(j).add(new Coord(currCoord.getX()-1,currCoord.getY()));
                                    }
                                }
                            }
                        }
                        break;
                }
            }
            if(game.checkMove(moveCommands.get(i))==1){
                game.resetGame();
                return 0;
            }
            int result = game.moveRobot(moveCommands.get(i));
            if(result==-1){
                game.resetGame();
                return -1;
            }
        }
        game.resetGame();
        return 1;
    }


    //returns the surrounding Positions of the Paths to the current VictoryPoint
    //The Degree of a critical Position is defined by the amount of turns the Path
    //Degree0 being the VictoryPoint only
    //to the VictoryPoint has
    //returns a list of list
    //Element 0 => Critical Positions of Degree 0
    //Element 1 => Critical Positions of Degree 1
    //...
    public ArrayList<ArrayList<Coord>> getCriticalPositions(int degree){
        ArrayList<Coord> startPos = new ArrayList<Coord>();
        ArrayList<ArrayList<Coord>> criticalPositions = new ArrayList<ArrayList<Coord>>();
        ArrayList<Coord> degree0 = new ArrayList<Coord>();
        degree0.add(currVicCoord);
        criticalPositions.add(degree0);
        ArrayList<Coord> oldStartPos = new ArrayList<Coord>();
        ArrayList<Coord> newStartPos = new ArrayList<Coord>();
        newStartPos.add(currVicCoord);
        while (degree > 0){
            startPos=(ArrayList<Coord>)newStartPos.clone();
            newStartPos = new ArrayList<Coord>();
            ArrayList<Coord> newCritPos = new ArrayList<Coord>();
            criticalPositions.add(newCritPos);
            while(!startPos.isEmpty()) {
                Coord currPos = startPos.get(startPos.size() - 1);
                oldStartPos.add(currPos);
                startPos.remove(currPos);
                for (Direction dir : Direction.values()) {
                    Coord collisionPos = game.checkMovement(currPos.clone(), dir, game.getState().getBoard());
                    boolean collisionPosSmart = true;
                    for (Coord checkPos : startPos) {
                        if (collisionPos.equals(checkPos)) {
                            collisionPosSmart = false;
                        }
                    }
                    for (Coord checkPos : oldStartPos) {
                        if (collisionPos.equals(checkPos)) {
                            collisionPosSmart = false;
                        }
                    }
                    if (collisionPosSmart) {
                        newStartPos.add(collisionPos);
                        switch (dir) {
                            case DOWN:
                                for (int y = currPos.getY(); y <= collisionPos.getY(); y++) {
                                    if (currPos.getX() + 1 < 15) {
                                        newCritPos.add(new Coord(currPos.getX() + 1, y));
                                    }
                                    if (currPos.getX() - 1 > 0) {
                                        newCritPos.add(new Coord(currPos.getX() - 1, y));
                                    }
                                }
                                break;
                            case UP:
                                for (int y = currPos.getY(); y >= collisionPos.getY(); y--) {
                                    if (currPos.getX() + 1 < 15) {
                                        newCritPos.add(new Coord(currPos.getX() + 1, y));
                                    }
                                    if (currPos.getX() - 1 >0) {
                                        newCritPos.add(new Coord(currPos.getX() - 1, y));
                                    }
                                }
                                break;
                            case RIGHT:
                                for (int x = currPos.getX(); x <= collisionPos.getX(); x++) {
                                    if (currPos.getY() + 1 < 15) {
                                        newCritPos.add(new Coord(x, currPos.getY() + 1));
                                    }
                                    if (currPos.getY() - 1 > 0) {
                                        newCritPos.add(new Coord(x, currPos.getY() - 1));
                                    }
                                }
                                break;
                            case LEFT:
                                for (int x = currPos.getX(); x >= collisionPos.getX(); x--) {
                                    if (currPos.getY() + 1 < 15) {
                                        newCritPos.add(new Coord(x, currPos.getY() + 1));
                                    }
                                    if (currPos.getY() - 1 > 0) {
                                        newCritPos.add(new Coord(x, currPos.getY() - 1));
                                    }
                                }
                                break;
                        }
                    }
                }
            }
            degree--;
        }
        return criticalPositions;
    }

    public ArrayList<ArrayList<ArrayList<Coord>>> getCriticalPath(int degree){
        ArrayList<ArrayList<ArrayList<Coord>>> criticalPath = new ArrayList<ArrayList<ArrayList<Coord>>>();
        ArrayList<ArrayList<Coord>> critVertPath = new ArrayList<ArrayList<Coord>>();
        ArrayList<ArrayList<Coord>> critHorizPath = new ArrayList<ArrayList<Coord>>();
        criticalPath.add(critVertPath);
        criticalPath.add(critHorizPath);
        ArrayList<Coord> startPos = new ArrayList<Coord>();
        ArrayList<Coord> degree0 = new ArrayList<Coord>();
        degree0.add(currVicCoord);
        critHorizPath.add(degree0);
        critVertPath.add(degree0);
        ArrayList<Coord> oldStartPos = new ArrayList<Coord>();
        ArrayList<Coord> newStartPos = new ArrayList<Coord>();
        newStartPos.add(currVicCoord);
        while (degree > 0){
            startPos=(ArrayList<Coord>)newStartPos.clone();
            newStartPos = new ArrayList<Coord>();
            ArrayList<Coord> newVertCritPos = new ArrayList<Coord>();
            ArrayList<Coord> newHorizCritPos = new ArrayList<Coord>();
            critHorizPath.add(newHorizCritPos);
            critVertPath.add(newVertCritPos);
            while(!startPos.isEmpty()) {
                Coord currPos = startPos.get(startPos.size() - 1);
                oldStartPos.add(currPos);
                startPos.remove(currPos);
                for (Direction dir : Direction.values()) {
                    Coord collisionPos = game.checkMovement(currPos.clone(), dir, game.getState().getBoard());
                    boolean collisionPosSmart = true;
                    for (Coord checkPos : startPos) {
                        if (collisionPos.equals(checkPos)) {
                            collisionPosSmart = false;
                        }
                    }
                    for (Coord checkPos : oldStartPos) {
                        if (collisionPos.equals(checkPos)) {
                            collisionPosSmart = false;
                        }
                    }
                    if (collisionPosSmart) {
                        newStartPos.add(collisionPos);
                        switch (dir) {
                            case DOWN:
                                for (int y = currPos.getY(); y <= collisionPos.getY(); y++) {
                                    newVertCritPos.add(new Coord(currPos.getX(),y));
                                }
                                break;
                            case UP:
                                for (int y = currPos.getY(); y >= collisionPos.getY(); y--) {
                                    newVertCritPos.add(new Coord(currPos.getX(),y));
                                }
                                break;
                            case RIGHT:
                                for (int x = currPos.getX(); x <= collisionPos.getX(); x++) {
                                    newHorizCritPos.add(new Coord(x,currPos.getY()));
                                }
                                break;
                            case LEFT:
                                for (int x = currPos.getX(); x >= collisionPos.getX(); x--) {
                                    newHorizCritPos.add(new Coord(x,currPos.getY()));
                                }
                                break;
                        }
                    }
                }
            }
            degree--;
        }
        return criticalPath;
    }


}
