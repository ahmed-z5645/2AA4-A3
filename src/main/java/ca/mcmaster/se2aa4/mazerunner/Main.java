package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    
    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options optionsobj = new Options();
        optionsobj.addOption("i", true, "Path to Maze");

        CommandLineParser cmdlparser = new DefaultParser();
        
        try {
            CommandLine cmd = cmdlparser.parse(optionsobj, args);

            if (cmd.hasOption("i")){
                String pathToFile = args[1];
                logger.info("**** Reading the maze from file " + pathToFile);
                BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.trace("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger.trace("PASS ");
                        }
                    }
                    logger.trace(System.lineSeparator());
            }
            
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
