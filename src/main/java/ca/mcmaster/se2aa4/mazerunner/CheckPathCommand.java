package ca.mcmaster.se2aa4.mazerunner;

public class CheckPathCommand implements Command {
    private PathChecker pathChecker;
    private Maze maze;

    public CheckPathCommand(PathChecker pathChecker, Maze maze) {
        this.pathChecker = pathChecker;
        this.maze = maze;
    }

    @Override
    public void execute() {
        execute("");
    }

    public void execute(String path) {
        this.pathChecker.checkPath(maze.getGrid(), path);
    }
    
}
