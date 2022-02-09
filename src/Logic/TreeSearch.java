package Logic;

import Data.Enums.Colors;
import Data.Enums.Direction;
import Data.MoveCommand;
import Data.MoveNode;

import java.util.ArrayList;
import java.util.Random;



//heuristic=0
//Uses Depth limited Depth first search
//heurisitc=1
//Uses Depth limited random first search
//heurisitc=2
//Uses Depth limited breadth first search
public class TreeSearch extends Thread{

    Game game;
    MoveNode result=null;
    int depthLimit;
    ArrayList<MoveNode> toExplore;
    int heuristic;
    int colorsUsed;
    ArrayList<Colors> colorsInUse;
    public TreeSearch(Game game,int depthLimit, ArrayList<MoveNode> toExplore, int heuristic, ArrayList<Colors> colorsInUse){
        this.game=game;
        this.depthLimit=depthLimit;
        this.toExplore=toExplore;
        this.heuristic=heuristic;
        this.colorsInUse=colorsInUse;
    }

    public MoveNode getResult() {
        return result;
    }

    public void setResult(MoveNode result) {
        this.result = result;
    }

    @Override
    public void run() {
        MoveNode curr = toExplore.get(0);
        Colors vicColor=this.game.getState().getBoard().getVictoryPoint().getColor();
        Colors color = vicColor;
        while(!toExplore.isEmpty() && !Thread.currentThread().isInterrupted()) {
            switch (this.heuristic){
                case 0:
                    curr=toExplore.get(0);
                    break;
                case 1:
                    Random rand = new Random();
                    int newIndex = rand.nextInt(toExplore.size());
                    curr=toExplore.get(newIndex);
                    break;
                case 2:
                    curr=toExplore.get(toExplore.size()-1);
                    break;
            }
            if(curr.getMoveCommands().size()>depthLimit){
                toExplore.remove(curr);
            }else {
                int seqCheck = isSeqSmart(curr.getMoveCommands());
                if (seqCheck==0 || curr.getRoot()) {
                    //System.out.println("Expand further");
                    if(colorsInUse.size()==1) {
                        for (Direction direction : Direction.values()) {

                            curr.addChild(new MoveCommand(color, direction));
                            toExplore.add(0, curr.getChilds().get(curr.getChilds().size() - 1));
                        }
                    }else{
                        for(int i=0;i<colorsInUse.size();i++){
                            Colors currColor = colorsInUse.get(i);
                            for (Direction direction : Direction.values()) {

                                curr.addChild(new MoveCommand(currColor, direction));
                                toExplore.add(0, curr.getChilds().get(curr.getChilds().size() - 1));
                            }
                        }
                    }
                    toExplore.remove(curr);
                }
                //cut out useless moves
                //useless move = crash against wall
                if (seqCheck==-1) {
                    //System.out.println("Useless Move");
                    toExplore.remove(curr);
                    if(!toExplore.isEmpty()){
                        curr = toExplore.get(0);
                    }
                }
                if(seqCheck==1){
                    this.result=curr;
                    return;
                }
            }

        }
        this.result=null;
        return;
    }

    //checks if a Seq contains moves that end up crashing a Wall
    //0 => no crash
    //-1 => crash
    //1 => Point Scored
    public int isSeqSmart(ArrayList<MoveCommand> moveCommands){
        for(int i =0;i<moveCommands.size();i++){
            if(game.checkMove(moveCommands.get(i))==1){
                game.resetGame();
                return 1;
            }
            int result = game.moveRobot(moveCommands.get(i));
            if(result==-1){
                game.resetGame();
                return -1;
            }
        }
        game.resetGame();
        return 0;
    }
}
