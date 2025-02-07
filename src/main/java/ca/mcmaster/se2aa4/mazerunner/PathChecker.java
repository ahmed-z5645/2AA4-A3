package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class PathChecker extends Traverser{

    public boolean checkPath(ArrayList<ArrayList<Character>> grid, String path){
        startAtEntry(grid);
        path = path.replaceAll("\\s", "");
        path = path.toUpperCase();
        for (int i = 0; i < path.length(); i++){
            char character = path.charAt(i);
            if (Character.isDigit(character)){
                if (i + 1 >= path.length()){
                    return false;
                } else if (path.charAt(i + 1) != 'L' && path.charAt(i + 1) != 'R' && path.charAt(i + 1) != 'F'){
                    return false;
                }

                for (int j = 0; j < Character.getNumericValue(character); j++){
                    boolean validMove = movePos(path.charAt(i + 1), grid);
                    if (validMove == false) {
                        return false;
                    }
                }
                i++;
            } else if (character == 'R' || character != 'L' || character != 'F'){
                boolean validMove = movePos(character, grid);
                if (validMove == false) {
                    return false;
            }
            }
        }
        return checkIfExit(grid);
    }
}
