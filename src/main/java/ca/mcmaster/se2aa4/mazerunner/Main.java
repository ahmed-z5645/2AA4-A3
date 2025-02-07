package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    private static final void help() {
        System.out.println("Welcome to the Maze Runner:");
        System.out.println("Parameters: -i [path-to-maze.txt] [-p [PathToCheck]]");
    }

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options optionsobj = new Options();
        optionsobj.addOption("i", true, "Path to Maze");
        optionsobj.addOption("p", true, "Path provided");
        CommandLineParser cmdlparser = new DefaultParser();
        
        try {
            CommandLine cmd = cmdlparser.parse(optionsobj, args);

            if (cmd.hasOption("i") && cmd.hasOption("p")){
                String pathToFile = args[1];
                BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
                String line;

                Maze maze = new Maze();

                while ((line = reader.readLine()) != null) {
                    maze.incrHeight();
                    for (int i = 0; i < line.length(); i++){
                        maze.fill(line.charAt(i), i);
                    }
                }
                reader.close();

                //then test path here
                System.out.println("'" + maze.getChar(0, 0) + "'");
            } else if (cmd.hasOption("i")){
                String pathToFile = args[1];
                BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
                String line;
                int size = 0;

                Maze maze = new Maze();

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

                //then use algorithm here
                PathFinder rightHandSearch = new RightHandSearcher();
                rightHandSearch.searchAlgorithim(maze.getGrid(), maze.getHeight(), maze.getWidth());
                
                System.out.println("The Canonical Path is: " + rightHandSearch.getCanonicalPath());
                System.out.println("The Factorized Path is: " + rightHandSearch.getFactorizedPath());
            } else {
                help();
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
