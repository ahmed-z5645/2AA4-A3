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

    public void print() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width + 1; j++) {
                System.out.print(this.grid.get(i).get(j));
            }
            System.out.println();
        }
    }

    private void incrWidth(int currWidth) {
        if (this.width < currWidth) {
            this.width = currWidth;
        }
    }

    public Character getChar (int x, int y) {
        Character res = this.grid.get(x).get(y);
        return res;
    }

    public void fill (Character input, int x) {
        incrWidth(x);
        if ((this.height) > this.grid.size()) {
            this.grid.add(new ArrayList<Character>());
        }
        this.grid.get(this.height - 1).add(input);
    }
}
