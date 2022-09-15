package Logic;

import Data.*;
import Data.Enums.Colors;
import Data.Enums.Direction;

import java.util.ArrayList;

public class Utility {

    Coord currVicCoord;
    Game game;

    //A critical path consists of positions that can reach the VictoryPoint with a set number of Moves
    //The degree of the path is equivalent to the amount of moves needed to reach the VictoryPoint
    //The ArrayList is ordered by the degree of the paths
    //Element 0 => Critical Path degree 0
    //Element 1 => Critical Path degree 1
    //usw
    //For calculation efficiency the Array is Split in horizontal and vertical paths
    ArrayList<ArrayList<Coord>> critHorizontalPath;
    ArrayList<ArrayList<Coord>> critVerticalPath;
    //Critical positions are coordinates adjacent to critical paths
    //They are ordered by degree just like the critical paths
    ArrayList<ArrayList<Coord>> critPositions;
    //CrossBlockPositions are positions that if blocked enable the vicColor to enter a critical path
    ArrayList<CrossBlockPos> crossBlockPositions;
    int maxDegree=20;

    public Utility(Game game,int maxDegree){
        this.game=game;
        currVicCoord=this.game.getState().getBoard().getVictoryPoint().getCoord();
        critPositions=null;
        crossBlockPositions=new ArrayList<CrossBlockPos>();
        this.maxDegree=maxDegree;
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
    //-2 => Last move reverted
    public int isSeqSmart(ArrayList<MoveCommand> moveCommands){
        for(int i =0;i<moveCommands.size();i++){

            if(i==moveCommands.size()-1 && moveCommands.size()>=2){
                Direction currDir = moveCommands.get(i).getDir();
                Colors currColor= moveCommands.get(i).getColor();
                Colors prevColor = moveCommands.get(i-1).getColor();
                //check if last move just negates the move before that
                if(currColor.equals(prevColor)){
                    switch (moveCommands.get(moveCommands.size()-2).getDir()){
                        case UP:
                            if(currDir==Direction.DOWN){
                                game.resetGame();
                                return -2;
                            }
                            break;
                        case DOWN:
                            if(currDir==Direction.UP){
                                game.resetGame();
                                return -2;
                            }
                            break;
                        case LEFT:
                            if(currDir==Direction.RIGHT){
                                game.resetGame();
                                return -2;
                            }
                            break;
                        case RIGHT:
                            if(currDir==Direction.LEFT){
                                game.resetGame();
                                return -2;
                            }
                            break;
                    }
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
    public float isSeqSmartSetup(ArrayList<MoveCommand> moveCommands){

        if(critPositions==null){
            loadCriticalPositions(maxDegree);
        }
        float posScore=0;
        float setupScore;
        if(moveCommands.size()==0){
            return -1;
        }
        //Colors color=moveCommands.get(moveCommands.size()-1).getColor();
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
            for(int i=1;i<this.maxDegree;i++){
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
                posScore=posScore+(1/(float)lowestDegree);
            }
        }
        game.resetGame();
        setupScore=posScore/moveCommands.size();
        return setupScore;
    }

    // Gives feedback if Seq leads to a meaningful Setup
    //-1 => No Movement
    //-2 => Crash Wall
    //postive float the best HValue of an CrossBlockPos that is taken
    //HValue of CrossBlockPos movesNeededVicColor+Degree+movesNeededSet^up
    public float isSeqSmartSetupCrossBlock(ArrayList<MoveCommand> moveCommands){
        float hValue=0;
        if(moveCommands.size()==0){
            return -1;
        }
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
           for(CrossBlockPos currCrossBlock : this.crossBlockPositions){
               if(currCoord.equals(currCrossBlock.getCoord())){
                   float newHValue = moveCommands.size()+currCrossBlock.getDegree()+currCrossBlock.getMovesNeeded();
                   if(newHValue>hValue){
                       hValue=newHValue;
                   }
               }
           }
        }
        game.resetGame();
        return hValue;
    }
    //checks if there is already a crossBlockPos with the same coord and same Degree
    //if there is the corrBlockPos with lesser Moves needed takes precedence
    public void insertCrossBlockPos(CrossBlockPos crossBlockPos){
        for(CrossBlockPos currPos:crossBlockPositions){
           if(currPos.equalsCoord(crossBlockPos) && currPos.getDegree()==crossBlockPos.getDegree()){
               if(currPos.getMovesNeeded()>crossBlockPos.getMovesNeeded()){
                   currPos=crossBlockPos;
               }else{
                   return;
               }
           }
        }
        crossBlockPositions.add(crossBlockPos);
    }

    //checks if a Seq contains moves that end up crashing a Wall
    //1 => no crash
    //-1 => crash
    //0 => Point Scored
    //will RememberCrossings with an Critical Path for Setupheuristic
    public int isSeqSmartRememberCrossings(ArrayList<MoveCommand> moveCommands){
        Board board = this.game.state.getBoard();
        for(int i =0;i<moveCommands.size();i++){
            if(i==moveCommands.size()-1){
                Direction currDir = moveCommands.get(i).getDir();
                //check if last move just negates the move before that
                Colors currColor= moveCommands.get(i).getColor();
                if(moveCommands.size()>=2){
                    Colors prevColor = moveCommands.get(i-1).getColor();
                    if(prevColor.equals(currColor)){
                        switch (moveCommands.get(moveCommands.size() - 2).getDir()) {
                            case UP:
                                if (currDir == Direction.DOWN) {
                                    game.resetGame();
                                    return -2;
                                }
                                break;
                            case DOWN:
                                if (currDir == Direction.UP) {
                                    game.resetGame();
                                    return -2;
                                }
                                break;
                            case LEFT:
                                if (currDir == Direction.RIGHT) {
                                    game.resetGame();
                                    return -2;
                                }
                                break;
                            case RIGHT:
                                if (currDir == Direction.LEFT) {
                                    game.resetGame();
                                    return -2;
                                }
                                break;
                        }
                    }
                }
                Colors vicColor =moveCommands.get(i).getColor();
                Coord startPos = game.getState().getBoard().getRobot(vicColor).getCoord();
                Coord collisionPos = game.checkMovement(startPos.clone(), moveCommands.get(i).getDir(), game.getState().getBoard());
                switch (currDir){
                    case UP:
                        for (int y = startPos.getY()-1; y >= collisionPos.getY(); y--) {
                            Coord currCoord=new Coord(startPos.getX(),y);
                            for(int j=1;j<maxDegree;j++){
                                for(Coord critPos: critHorizontalPath.get(j)){
                                    Coord newCoord = new Coord(currCoord.getX(),currCoord.getY()-1);
                                    if(currCoord.equals(critPos) && critPos.getY()>0 && game.checkPos(newCoord,board,currDir)){
                                        CrossBlockPos newPos= new CrossBlockPos(newCoord,j,moveCommands.size());
                                        insertCrossBlockPos(newPos);
                                    }
                                }
                            }
                        }
                        break;
                    case DOWN:
                        for (int y = startPos.getY()+1; y <= collisionPos.getY(); y++) {
                            Coord currCoord=new Coord(startPos.getX(),y);
                            for(int j=1;j<maxDegree;j++){
                                for(Coord critPos: critHorizontalPath.get(j)){

                                    Coord newCoord = new Coord(currCoord.getX(),currCoord.getY()+1);
                                    if(currCoord.equals(critPos) && critPos.getY()<15&& game.checkPos(newCoord,board,currDir)){
                                        CrossBlockPos newPos= new CrossBlockPos(newCoord,j,moveCommands.size());
                                        insertCrossBlockPos(newPos);
                                    }
                                }
                            }
                        }
                        break;
                    case RIGHT:
                        for (int x = startPos.getX()+1; x <= collisionPos.getX(); x++) {
                            Coord currCoord=new Coord(x,startPos.getY());
                            for(int j=1;j<maxDegree;j++){
                                for(Coord critPos: critVerticalPath.get(j)){
                                    Coord newCoord = new Coord(currCoord.getX()+1,currCoord.getY());
                                    if(currCoord.equals(critPos) && critPos.getX()<15 &&game.checkPos(newCoord,board,currDir)){
                                        CrossBlockPos newPos= new CrossBlockPos(newCoord,j,moveCommands.size());
                                        insertCrossBlockPos(newPos);
                                    }
                                }
                            }
                        }
                        break;
                    case LEFT:
                        for (int x = startPos.getX()-1; x >= collisionPos.getX(); x--) {
                            Coord currCoord=new Coord(x,startPos.getY());
                            for(int j=1;j<maxDegree;j++){
                                for(Coord critPos: critVerticalPath.get(j)){
                                    Coord newCoord = new Coord(currCoord.getX()-1,currCoord.getY());
                                    if(currCoord.equals(critPos)&&critPos.getX()>0&&game.checkPos(newCoord,board,currDir)){
                                        CrossBlockPos newPos= new CrossBlockPos(newCoord,j,moveCommands.size());
                                        insertCrossBlockPos(newPos);
                                    }
                                }
                            }
                        }
                        break;
                }
            }

            //Test if game is won
            if(game.checkMove(moveCommands.get(i))==1){
                game.resetGame();
                return 0;
            }
            //Move Robot and continue the sequence
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
                    for (Coord checkPos : oldStartPos) {
                        if (collisionPos.equals(checkPos)) {
                            collisionPosSmart = false;
                        }
                    }
                    //test if the movement will work reversly
                    //there has to be an obstacle on the other side
                    //so critical paths of lower degrees can be accessed
                    //if not the roboter will slip through the lower degree path
                    if(collisionPosSmart==true){
                        Board board = this.game.getState().getBoard();
                        Coord reversePos;
                        Direction reverseDir;
                        switch (dir){
                            case UP:
                                reversePos=new Coord(currPos.getX(),currPos.getY()+1);
                                reverseDir=Direction.DOWN;
                                if(game.checkPos(reversePos,board,reverseDir)){
                                    collisionPosSmart=false;
                                }
                                break;
                            case DOWN:
                                reversePos=new Coord(currPos.getX(),currPos.getY()-1);
                                reverseDir=Direction.UP;
                                if(game.checkPos(reversePos,board,reverseDir)){
                                    collisionPosSmart=false;
                                }
                                break;
                            case LEFT:
                                reversePos=new Coord(currPos.getX()+1,currPos.getY());
                                reverseDir=Direction.RIGHT;
                                if(game.checkPos(reversePos,board,reverseDir)){
                                    collisionPosSmart=false;
                                }
                                break;
                            case RIGHT:
                                reversePos=new Coord(currPos.getX()-1,currPos.getY());
                                reverseDir=Direction.LEFT;
                                if(game.checkPos(reversePos,board,reverseDir)){
                                    collisionPosSmart=false;
                                }
                                break;
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
                    //draw paths from endpoints of critical paths of lower degree
                    Coord collisionPos = game.checkMovement(currPos.clone(), dir, game.getState().getBoard());
                    boolean collisionPosSmart = true;
                    //Test if we move at all
                    //Or if we move to a point we already were
                    for (Coord checkPos : oldStartPos) {
                        if (collisionPos.equals(checkPos)) {
                            collisionPosSmart = false;
                        }
                    }
                //test if the movement will work reversly
                //there has to be an obstacle on the other side
                //so critical paths of lower degrees can be accessed
                //if not the roboter will slip through the lower degree path
                if(collisionPosSmart==true){
                    Board board = this.game.getState().getBoard();
                    Coord reversePos;
                    Direction reverseDir;
                    switch (dir){
                        case UP:
                            reversePos=new Coord(currPos.getX(),currPos.getY()+1);
                            reverseDir=Direction.DOWN;
                            if(game.checkPos(reversePos,board,reverseDir)){
                               collisionPosSmart=false;
                            }
                            break;
                        case DOWN:
                            reversePos=new Coord(currPos.getX(),currPos.getY()-1);
                            reverseDir=Direction.UP;
                            if(game.checkPos(reversePos,board,reverseDir)){
                                collisionPosSmart=false;
                            }
                            break;
                        case LEFT:
                            reversePos=new Coord(currPos.getX()+1,currPos.getY());
                            reverseDir=Direction.RIGHT;
                            if(game.checkPos(reversePos,board,reverseDir)){
                                collisionPosSmart=false;
                            }
                            break;
                        case RIGHT:
                            reversePos=new Coord(currPos.getX()-1,currPos.getY());
                            reverseDir=Direction.LEFT;
                            if(game.checkPos(reversePos,board,reverseDir)){
                                collisionPosSmart=false;
                            }
                            break;
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

    public void insertInOrder(MoveNode mn,ArrayList<MoveNode> mnList){
        if(mnList.size()==0){
            mnList.add(mn);
            return;
        }
        float hValue = mn.getHValue();
        boolean inserted=false;
        for(int i=0;i<mnList.size();i++){
            if(hValue>=mnList.get(i).getHValue() && !inserted){
                mnList.add(i,mn);
                inserted=true;
            }
        }
        if(!inserted){
            mnList.add(mnList.size()-1,mn);
        }
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(int maxDegree) {
        this.maxDegree = maxDegree;
    }
}
