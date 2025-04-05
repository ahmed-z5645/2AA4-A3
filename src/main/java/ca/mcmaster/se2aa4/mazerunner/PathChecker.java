package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class PathChecker extends Traverser{

    private void startAtExit(ArrayList<ArrayList<Character>> grid){
        for (int i = 0; i < grid.size(); i++){
            ArrayList<Character> row = grid.get(i);
            if (row.get(row.size() - 1).equals(' ')){
                if (this.pos.size() == 2){
                    this.pos.set(0, i);
                    this.pos.set(1, (row.size() -1));
                } else {
                    this.pos.add(i);
                    this.pos.add((row.size() - 1));
                }
            }
        }
        this.direct = Direction.LEFT;
    }

    public boolean checkPath(ArrayList<ArrayList<Character>> grid, String path){
        startAtEntry(grid);
        path = path.replaceAll("\\s", "");
        path = path.toUpperCase();
        for (int i = 0; i < path.length(); i++){
            char character = path.charAt(i);
            if (Character.isDigit(character)){
                if (i + 1 >= path.length()){
                    break;
                } else if (path.charAt(i + 1) != 'L' && path.charAt(i + 1) != 'R' && path.charAt(i + 1) != 'F'){
                    break;
                }

                for (int j = 0; j < Character.getNumericValue(character); j++){
                    boolean validMove = movePos(path.charAt(i + 1), grid);
                    if (validMove == false) {
                        break;
                    }
                }
                i++;
            } else if (character == 'R' || character == 'L' || character == 'F'){
                boolean validMove = movePos(character, grid);
                if (validMove == false) {
                    break;
                }
            } else {
                return false;
            }
        }
        
        //if forwards doesn't work, try going backwards from west to east
        if (checkIfExit(grid) == false) {
            ArrayList<Integer> entrance = startAtEntry(grid);
            startAtExit(grid);
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
                } else if (character == 'R' || character == 'L' || character == 'F'){
                    boolean validMove = movePos(character, grid);
                    if (validMove == false) {
                        return false;
                    }
                }
            }
            
            if (entrance.get(0) == this.pos.get(0) && (entrance.get(1) == this.pos.get(1))){
                return true;
            } else {
                return false;
            }

        } else {
            return true;
        }
    }
}
