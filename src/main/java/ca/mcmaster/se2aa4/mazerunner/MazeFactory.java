package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

public class MazeFactory {
    public Maze createMazeFromFile(String pathToFile) {
        Maze maze = new Maze();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            int size = 0;

            
            while ((line = reader.readLine()) != null) {
                if (size < line.length()) {
                    size = line.length();
                }
                maze.incrHeight();
                for (int i = 0; i < size; i++) {
                    if ((size > line.length()) && (i >= line.length())) {
                        maze.fill(' ', i);
                    } else {
                        maze.fill(line.charAt(i), i);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("/!\\ An error has occured /!\\");
        }
        return maze;
    }

}