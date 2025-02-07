package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class PathChecker extends Traverser{

    public boolean checkPath(ArrayList<ArrayList<Character>> grid, String path){
        startAtEntry(grid);
        path = path.replaceAll("\\s", "");
        path = path.toUpperCase();
        for (int i = 0; i < path.length(); i++){
            char character = path.charAt(i);
            boolean status = movePos(character, grid);
            if (status == false) {
                return false;
        }
        }
        return checkIfExit(grid);
    }
}
