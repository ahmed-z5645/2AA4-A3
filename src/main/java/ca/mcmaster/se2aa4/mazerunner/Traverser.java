package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public abstract class Traverser {
    public ArrayList<Integer> pos;
    public StringBuffer path;
    public Direction direct;

    
    public Traverser(){
        this.path = new StringBuffer("");
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

    protected void movePos(char character, ArrayList<ArrayList<Character>> grid){
        if (character == 'F'){
            if ((this.direct == Direction.UP) && (grid.get(this.pos.get(0) + 1).get(this.pos.get(1)) != '#')){
                int temp = this.pos.get(0);
                temp++;
                this.pos.set(0, temp);
            } else if ((this.direct == Direction.RIGHT) && (grid.get(this.pos.get(0)).get(this.pos.get(1) + 1) != '#')){
                int temp = this.pos.get(1);
                temp++;
                this.pos.set(1, temp);
            } else if ((this.direct == Direction.DOWN) && (grid.get(this.pos.get(0) - 1).get(this.pos.get(1)) != '#')){
                int temp = this.pos.get(0);
                temp--;
                this.pos.set(0, temp);
            } else {
                int temp = this.pos.get(1);
                temp--;
                this.pos.set(1, temp);
            }
        } else if (character == 'R') {
            if (this.direct == Direction.RIGHT){
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
    }

    protected void addToPath (char character){
        this.path.append(character);
    }

    public String getCanonicalPath(){
        return this.path.toString();
    }

    public String getFactorizedPath(){
        StringBuffer res = new StringBuffer("");
        String worker = (getCanonicalPath()).replaceAll("\\s", "");
        
        int l = 0;
        int r = 1;

        while (r < worker.length()){
            char left = worker.charAt(l);
            char right = worker.charAt(r);
            //first two conditionals catch the last char properly
            if ((r == worker.length() - 1) && (left != right)){
                if ((r - l) > 1) {
                    res.append((r - l));
                }
                res.append(left);
                res.append(" ");
                res.append(right);
            } else if ((r == worker.length() - 1) && (left == right)){
                res.append(r - l + 1);
                res.append(left);
            } else if (right != left) {
                if ((r - l) > 1) {
                    res.append((r - l));
                }
                res.append(left);
                res.append(" ");
                l = r;
            }
            r++;
        }
        return res.toString();
    }

    abstract public void searchAlgorithim(ArrayList<ArrayList<Character>> grid, int height, int width);
}
