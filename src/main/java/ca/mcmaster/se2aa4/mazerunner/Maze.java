package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Maze {
    public int height;
    public int width;
    public ArrayList<ArrayList<Character>> grid;

    public Maze() {
        this.height = 0;
        this.width = 0;
        this.grid = new ArrayList<ArrayList<Character>>();
    }

    public void incrHeight() {
        this.height++;
    }

    public void incrWidth(int currWidth) {
        if (this.width < currWidth) {
            this.width = currWidth;
        }
    }

    public Character getChar (int x, int y) {
        Character res = this.grid.get(x).get(y);
        return res;
    }
}
