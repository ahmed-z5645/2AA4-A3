import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.PathChecker;
import ca.mcmaster.se2aa4.mazerunner.PathFinder;
import ca.mcmaster.se2aa4.mazerunner.RightHandSearcher;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class MazeTests {

    @Test
    void testFillMaze() {

        Maze maze = new Maze();

        maze.incrHeight();
        maze.fill('#', 0);
        maze.fill('#', 1);
        maze.fill('#', 2);

        maze.incrHeight();
        maze.fill(' ', 0);
        maze.fill(' ', 1);
        maze.fill(' ', 2);

        maze.incrHeight();
        maze.fill('#', 0);
        maze.fill('#', 1);
        maze.fill('#', 2);

        ArrayList<ArrayList<Character>> grid = maze.getGrid();

        // Verify the grid contents
        assertEquals(' ', grid.get(1).get(0), "The middle cell should be empty");
        assertEquals(' ', grid.get(1).get(1), "The middle cell should be empty");
        assertEquals(' ', grid.get(1).get(2), "The middle cell should be empty");

        assertEquals('#', grid.get(0).get(0), "Top-left cell should be '#'");
        assertEquals('#', grid.get(0).get(2), "Top-right cell should be '#'");

        assertEquals('#', grid.get(2).get(0), "Bottom-left cell should be '#'");
        assertEquals('#', grid.get(2).get(2), "Bottom-right cell should be '#'");
    }

    @Test
    void testMazeDimensions() {
        Maze maze = new Maze();

        maze.incrHeight();
        maze.fill('#', 0);
        maze.fill('#', 1);
        maze.fill('#', 2);

        maze.incrHeight();
        maze.fill(' ', 0);
        maze.fill(' ', 1);
        maze.fill(' ', 2);

        maze.incrHeight();
        maze.fill('#', 0);
        maze.fill('#', 1);
        maze.fill('#', 2);

        assertEquals(3, maze.getHeight(), "Maze height should be 3");
        assertEquals(2, maze.getWidth(), "Maze width should be 2");
    }

    @Test
    void testStartAtEntry(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("straight.maz.txt");
        assertNotNull(inputStream, "Input stream should not be null");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int size = 0;

        Maze maze = new Maze();
        try {
            while ((line = reader.readLine()) != null) {
                if (size < line.length()){
                    size = line.length();
                };
    
                maze.incrHeight();
                for (int i = 0; i < size; i++){
                    if ((size > line.length()) && (i >= line.length())){
                        maze.fill(' ', i);
                    } else {
                        maze.fill(line.charAt(i), i);
                    }
                }
            }
            reader.close();
    
            
            PathChecker pathChecker = new PathChecker();
            ArrayList<Integer> result = pathChecker.startAtEntry(maze.getGrid());
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(2);
            expected.add(0);
            assertEquals(result, expected, "The path should be valid");
        } catch (Exception e) {
            System.out.println("An Error occurred!");
        }
    }

    @Test
    void testFactorizedPath() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("straight.maz.txt");
        assertNotNull(inputStream, "Input stream should not be null");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int size = 0;

        Maze maze = new Maze();
        try {
            while ((line = reader.readLine()) != null) {
                if (size < line.length()){
                    size = line.length();
                };
    
                maze.incrHeight();
                for (int i = 0; i < size; i++){
                    if ((size > line.length()) && (i >= line.length())){
                        maze.fill(' ', i);
                    } else {
                        maze.fill(line.charAt(i), i);
                    }
                }
            }
            reader.close();
    
            String path = "4F";

            PathChecker pathChecker = new PathChecker();
            boolean result = pathChecker.checkPath(maze.getGrid(), path);
            assertTrue(result, "The path should be valid");
        } catch (Exception e) {
            System.out.println("An Error occurred!");
        }
        

    }

    @Test
    void testCanonicalPath() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("straight.maz.txt");
        assertNotNull(inputStream, "Input stream should not be null");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int size = 0;

        Maze maze = new Maze();
        try {
            while ((line = reader.readLine()) != null) {
                if (size < line.length()){
                    size = line.length();
                };
    
                maze.incrHeight();
                for (int i = 0; i < size; i++){
                    if ((size > line.length()) && (i >= line.length())){
                        maze.fill(' ', i);
                    } else {
                        maze.fill(line.charAt(i), i);
                    }
                }
            }
            reader.close();
    
            String path = "FFFF";

            PathChecker pathChecker = new PathChecker();
            boolean result = pathChecker.checkPath(maze.getGrid(), path);
            assertTrue(result, "The path should be valid");
        } catch (Exception e) {
            System.out.println("An Error occurred!");
        }
    }

    @Test
    void testFalsePath() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("straight.maz.txt");
        assertNotNull(inputStream, "Input stream should not be null");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int size = 0;

        Maze maze = new Maze();
        try {
            while ((line = reader.readLine()) != null) {
                if (size < line.length()){
                    size = line.length();
                };
    
                maze.incrHeight();
                for (int i = 0; i < size; i++){
                    if ((size > line.length()) && (i >= line.length())){
                        maze.fill(' ', i);
                    } else {
                        maze.fill(line.charAt(i), i);
                    }
                }
            }
            reader.close();
    
            String path = "FFF";

            PathChecker pathChecker = new PathChecker();
            boolean result = pathChecker.checkPath(maze.getGrid(), path);
            assertTrue((!result), "The path should not be valid");
        } catch (Exception e) {
            System.out.println("An Error occurred!");
        }
    }

    @Test
    void testInvalidDigit(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("straight.maz.txt");
        assertNotNull(inputStream, "Input stream should not be null");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int size = 0;

        Maze maze = new Maze();
        try {
            while ((line = reader.readLine()) != null) {
                if (size < line.length()){
                    size = line.length();
                };
    
                maze.incrHeight();
                for (int i = 0; i < size; i++){
                    if ((size > line.length()) && (i >= line.length())){
                        maze.fill(' ', i);
                    } else {
                        maze.fill(line.charAt(i), i);
                    }
                }
            }
            reader.close();
    
            String path = "4FXF";

            PathChecker pathChecker = new PathChecker();
            boolean result = pathChecker.checkPath(maze.getGrid(), path);
            assertTrue((!result), "The path should not be valid");
        } catch (Exception e) {
            System.out.println("An Error occurred!");
        }
    }

    @Test
    void testGetPaths() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("small.maz.txt");
        assertNotNull(inputStream, "Input stream should not be null");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int size = 0;

        Maze maze = new Maze();
        try {
            while ((line = reader.readLine()) != null) {
                if (size < line.length()){
                    size = line.length();
                };
    
                maze.incrHeight();
                for (int i = 0; i < size; i++){
                    if ((size > line.length()) && (i >= line.length())){
                        maze.fill(' ', i);
                    } else {
                        maze.fill(line.charAt(i), i);
                    }
                }
            }
            reader.close();

            PathFinder rightHandSearch = new RightHandSearcher();
            rightHandSearch.searchAlgorithm(maze.getGrid(), maze.getHeight(), maze.getWidth());
            assertEquals(rightHandSearch.getCanonicalPath(),
            "FRFLLFFRFFRFFLLFFFFRFFRFFFFLLFFRFFFFRFFRFFLLFFLFFLFFFFRFFRFFLLFFFFRFFRFFLLFFRFFRFFFFRFFLFFRFFLF",
              "The path should be valid");
              assertEquals(rightHandSearch.getFactorizedPath(),
              "F R F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F",
                "The path should be valid"); 
        } catch (Exception e) {
            System.out.println("An Error occurred!");
        }
    }

    @Test
    void testFalseGetPaths() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("small.maz.txt");
        assertNotNull(inputStream, "Input stream should not be null");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int size = 0;

        Maze maze = new Maze();
        try {
            while ((line = reader.readLine()) != null) {
                if (size < line.length()){
                    size = line.length();
                };
    
                maze.incrHeight();
                for (int i = 0; i < size; i++){
                    if ((size > line.length()) && (i >= line.length())){
                        maze.fill(' ', i);
                    } else {
                        maze.fill(line.charAt(i), i);
                    }
                }
            }
            reader.close();

            PathFinder rightHandSearch = new RightHandSearcher();
            rightHandSearch.searchAlgorithm(maze.getGrid(), maze.getHeight(), maze.getWidth());
            assertNotEquals(rightHandSearch.getCanonicalPath(),
            "FRFLLFFRFFRFLLFFFFRFFRFFFFLLFFRFFFFRFFRFFLLFFLFFLFFFFRFFRFFLLFFFFRFFRFFLLFFRFFRFFFFRFFLFFRFFLF",
              "The path should not be valid");
            assertNotEquals(rightHandSearch.getFactorizedPath(),
            "F R F 2L 2F R 2F R 2F 2L F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F",
            "The path should not be valid"); 
        } catch (Exception e) {
            System.out.println("An Error occurred!");
        }
    }

    @Test
    void testBackwardsPath(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("tiny.maz.txt");
        assertNotNull(inputStream, "Input stream should not be null");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int size = 0;

        Maze maze = new Maze();
        try {
            while ((line = reader.readLine()) != null) {
                if (size < line.length()){
                    size = line.length();
                };
    
                maze.incrHeight();
                for (int i = 0; i < size; i++){
                    if ((size > line.length()) && (i >= line.length())){
                        maze.fill(' ', i);
                    } else {
                        maze.fill(line.charAt(i), i);
                    }
                }
            }
            reader.close();
    
            String path = "FFFLFFFFRFFF";

            PathChecker pathChecker = new PathChecker();
            boolean result = pathChecker.checkPath(maze.getGrid(), path);
            assertTrue(result, "The path should be valid");
        } catch (Exception e) {
            System.out.println("An Error occurred!");
        }
    }
}
