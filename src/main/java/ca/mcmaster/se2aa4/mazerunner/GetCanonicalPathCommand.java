package ca.mcmaster.se2aa4.mazerunner;

public class GetCanonicalPathCommand implements Command {
    private PathFinder searcher;

    public GetCanonicalPathCommand(PathFinder searcher) {
        this.searcher = searcher;
    }

    @Override
    public void execute() {
        searcher.getCanonicalPath();
    }
}