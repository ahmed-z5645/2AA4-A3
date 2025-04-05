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
                String pathToFile = cmd.getOptionValue("i");
                
                MazeFactory mazeFactory = new MazeFactory();
                Maze maze = mazeFactory.createMazeFromFile(pathToFile);

                //then test path here
                String inputPath = cmd.getOptionValue("p");

                PathChecker pathChecker = new PathChecker();

                CheckPathCommand checkPathCommand = new CheckPathCommand(pathChecker, maze);
                checkPathCommand.execute(inputPath);
                if (pathChecker.checkPath(maze.getGrid(), inputPath)){
                    System.out.println("This path is valid");
                } else {
                    System.out.println("This path is invalid");
                }

            } else if (cmd.hasOption("i")){
                String pathToFile = cmd.getOptionValue("i");

                MazeFactory mazeFactory = new MazeFactory();
                Maze maze = mazeFactory.createMazeFromFile(pathToFile);
                
                //then use algorithm here
                PathFinder rightHandSearch = new RightHandSearcher();
                SearchCommand searchCommand = new SearchCommand(rightHandSearch, maze);
                searchCommand.execute();
                
                System.out.println("The Canonical Path is: " + rightHandSearch.getCanonicalPath());
                System.out.println("The Factorized Path is: " + rightHandSearch.getFactorizedPath());
            } else {
                help();
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }
}
