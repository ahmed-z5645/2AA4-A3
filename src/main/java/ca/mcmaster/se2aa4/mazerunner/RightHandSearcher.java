package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class RightHandSearcher extends PathFinder {
    
    public void searchAlgorithim(ArrayList<ArrayList<Character>> grid, int height, int width){
        startAtEntry(grid);

        while (checkIfExit(grid) == false) {
            int posX = this.pos.get(1);
            int posY = this.pos.get(0);

            switch (this.direct){
                case RIGHT:
                    if (grid.get(posY + 1).get(posX) != '#'){
                        movePos('R', grid);
                        this.path.append('R');
                    } else if (grid.get(posY).get(posX + 1) == '#'){
                        movePos('L', grid);
                        this.path.append('L');
                    }
                    break;
                case DOWN:
                    if (grid.get(posY).get(posX - 1) != '#'){
                        movePos('R', grid);
                        this.path.append('R');
                    } else if (grid.get(posY + 1).get(posX) == '#') {
                        movePos('L', grid);
                        this.path.append('L');
                    }
                    break;
                case LEFT:
                    if (grid.get(posY - 1).get(posX) != '#'){
                        movePos('R', grid);
                        this.path.append('R');
                    } else if (grid.get(posY).get(posX - 1) == '#'){
                        movePos('L', grid);
                        this.path.append('L');
                    }
                    break;
                case UP:
                    if (grid.get(posY).get(posX + 1) != '#'){
                        movePos('R', grid);
                        this.path.append('R');
                    } else if (grid.get(posY - 1).get(posX) == '#'){
                        movePos('L', grid);
                        this.path.append('L');
                    }
                    break;
            }

            movePos('F', grid);
            this.path.append('F');
        }
    }
}
