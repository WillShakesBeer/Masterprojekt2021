package Data;

import java.util.ArrayList;

public class MoveNode {
    // Nodes for Treesearching algorithms
    // Root node does contain an empty MoveCommandList
    // Every node represents a Sequence of Moves
    Boolean isRoot;
    ArrayList<MoveCommand> moveCommands;
    ArrayList<MoveNode> childs;
    float HValue;

    public MoveNode(){
        isRoot=true;
        this.moveCommands=new ArrayList<MoveCommand>();
        childs=new ArrayList<MoveNode>();
        HValue=0;
    }

    public MoveNode(ArrayList<MoveCommand> moveCommands){
        isRoot=false;
        this.moveCommands=moveCommands;
        childs=new ArrayList<MoveNode>();
        this.HValue=-1;
    }

    public MoveNode(ArrayList<MoveCommand> moveCommands,float HValue){
        isRoot=false;
        this.moveCommands=moveCommands;
        childs=new ArrayList<MoveNode>();
        this.HValue=HValue;
    }
    public void addChild(MoveNode mn){
        childs.add(mn);
    }

    public void addChild(MoveCommand mc){
        ArrayList<MoveCommand> childCommands= (ArrayList<MoveCommand>) this.moveCommands.clone();
        childCommands.add(mc);
        childs.add(new MoveNode(childCommands));
    }

    public void addChild(MoveCommand mc,float HValue){
        ArrayList<MoveCommand> childCommands= (ArrayList<MoveCommand>) this.moveCommands.clone();
        childCommands.add(mc);
        childs.add(new MoveNode(childCommands,HValue));
    }
    public void addChild(ArrayList<MoveCommand> mcs){
        childs.add(new MoveNode(mcs));
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

    public ArrayList<MoveNode> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<MoveNode> childs) {
        this.childs = childs;
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
