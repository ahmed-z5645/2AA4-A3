package ca.mcmaster.se2aa4.mazerunner;

public class SearchCommand implements Command {
    private PathFinder searcher;
    private Maze maze;

    public SearchCommand(PathFinder searcher, Maze maze) {
        this.searcher = searcher;
        this.maze = maze;
    }

    @Override
    public void execute() {
        searcher.searchAlgorithm(maze.getGrid(), maze.getHeight(), maze.getWidth());
    }
}
