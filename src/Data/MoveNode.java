package Data;

import java.util.ArrayList;

public class MoveNode {
    // Nodes for Treesearching algorithms
    // Root node does not contain a Movecommand
    Boolean isRoot;
    MoveCommand MoveCommand;
    ArrayList<MoveNode> childs;

    public MoveNode(){
        isRoot=true;
        childs=new ArrayList<MoveNode>();
    }

    public MoveNode(MoveCommand moveCommand){
        isRoot=false;
        this.MoveCommand=moveCommand;
        childs=new ArrayList<MoveNode>();
    }

    public void addChild(MoveNode mn){
        childs.add(mn);
    }

    public void addChild(MoveCommand mc){
        childs.add(new MoveNode(mc));
    }

    public void removeChild(MoveCommand mc){
        for(MoveNode child : childs){
           if(!child.isRoot){
              if(child.getMoveCommand().equals(mc)){
                  childs.remove(child);
              }
           }else{
               System.out.println("Cant Remove on Node on Root");
           }
        }
    }

    public Data.MoveCommand getMoveCommand() {
        return MoveCommand;
    }

    public void setMoveCommand(Data.MoveCommand moveCommand) {
        MoveCommand = moveCommand;
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
}
