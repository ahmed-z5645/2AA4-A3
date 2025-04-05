package ca.mcmaster.se2aa4.mazerunner;

public class GetFactorizedPathCommand implements Command {
    private PathFinder searcher;

    public GetFactorizedPathCommand(PathFinder searcher) {
        this.searcher = searcher;
    }

    @Override
    public void execute() {
        searcher.getFactorizedPath();
    }
}