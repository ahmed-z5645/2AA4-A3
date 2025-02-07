package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public abstract class PathFinder extends Traverser{
    public StringBuffer path;

    public PathFinder(){
        super();
        this.path = new StringBuffer("");
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
