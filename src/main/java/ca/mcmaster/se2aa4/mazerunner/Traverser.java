package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Traverser {
    public ArrayList<Integer> pos;
    public Direction direct;

    public Traverser(){
        this.pos = new ArrayList<Integer>();
        this.direct = Direction.RIGHT;
    }

    public ArrayList<Integer> startAtEntry(ArrayList<ArrayList<Character>> grid){
        ArrayList<Integer> entry = new ArrayList<Integer>();
        for (int i = 0; i < (grid.get(0)).size(); i++){
            ArrayList<Character> row = grid.get(i);
            if (row.get(0).equals(' ')){
                
                entry.add(i);
                entry.add(0);
                this.pos.set(0, i);
                this.pos.set(1, 0);
                return entry;
            }
        }
        //if no entry found, return empty position
        return entry;
    }

    protected boolean checkIfExit (ArrayList<ArrayList<Character>> grid){
        if (this.pos.get(1) == ((grid.get(0)).size() - 1)){
            if ((grid.get(this.pos.get(0)).get(this.pos.get(1))) == ' '){
                return true;
            }
        }

        return false;
    }

    protected boolean movePos(char character, ArrayList<ArrayList<Character>> grid){
        if (character == 'F'){
            if ((this.direct == Direction.UP) && 
                ((this.pos.get(0)  - 1) > 0) && 
                ((grid.get(this.pos.get(0) - 1).get(this.pos.get(1)) != '#'))) {
                int temp = this.pos.get(0);
                temp--;
                this.pos.set(0, temp);
            } else if ((this.direct == Direction.RIGHT) && 
                        (this.pos.get(1) + 1 < (grid.get(0)).size()) &&
                        (grid.get(this.pos.get(0)).get(this.pos.get(1) + 1) != '#')) {
                int temp = this.pos.get(1);
                temp++;
                this.pos.set(1, temp);
            } else if ((this.direct == Direction.DOWN) && 
                        (this.pos.get(0) + 1 > grid.size()) &&
                        (grid.get(this.pos.get(0) - 1).get(this.pos.get(1)) != '#')) {
                int temp = this.pos.get(0);
                temp++;
                this.pos.set(0, temp);
            } else if ((this.direct == Direction.LEFT) && 
                        (this.pos.get(1) - 1 > 0) &&
                        (grid.get(this.pos.get(0)).get(this.pos.get(1) - 1) != '#')) {
                int temp = this.pos.get(1);
                temp--;
                this.pos.set(1, temp);
            } else {
                return false;
            }
        } else if (character == 'R') {
            if ((this.direct == Direction.RIGHT)){
                this.direct = Direction.DOWN;
            } else if (this.direct == Direction.DOWN){
                this.direct = Direction.LEFT;
            } else if (this.direct == Direction.LEFT){
                this.direct = Direction.UP;
            } else {
                this.direct = Direction.RIGHT;
            }
        } else {
            if (this.direct == Direction.RIGHT){
                this.direct = Direction.UP;
            } else if (this.direct == Direction.DOWN){
                this.direct = Direction.RIGHT;
            } else if (this.direct == Direction.LEFT){
                this.direct = Direction.DOWN;
            } else {
                this.direct = Direction.LEFT;
            }
        }
        return true;
    }
}
