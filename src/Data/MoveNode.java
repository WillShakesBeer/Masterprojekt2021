package Data;

import java.util.ArrayList;

public class MoveNode {
    // Nodes for Treesearching algorithms
    // Root node does contain an empty MoveCommandList
    // Every node represents a Sequence of Moves
    Boolean isRoot;
    ArrayList<MoveCommand> moveCommands;
    float HValue;

    public MoveNode(){
        isRoot=true;
        this.moveCommands=new ArrayList<MoveCommand>();
        HValue=0;
    }

    public MoveNode(ArrayList<MoveCommand> moveCommands){
        isRoot=false;
        this.moveCommands=moveCommands;
        this.HValue=-1;
    }

    public MoveNode(ArrayList<MoveCommand> moveCommands,float HValue){
        isRoot=false;
        this.moveCommands=moveCommands;
        this.HValue=HValue;
    }

    public ArrayList<MoveCommand> getMoveCommands() {
        return moveCommands;
    }

    public void setMoveCommands(ArrayList<MoveCommand> moveCommands) {
        this.moveCommands = moveCommands;
    }

    public Boolean getRoot() {
        return isRoot;
    }



    public void setRoot(Boolean root) {
        isRoot = root;
    }

    public float getHValue() {
        return HValue;
    }

    public void setHValue(float HValue) {
        this.HValue = HValue;
    }
}
