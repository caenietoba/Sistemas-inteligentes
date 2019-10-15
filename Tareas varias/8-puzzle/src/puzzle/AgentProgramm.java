package puzzle;

public class AgentProgramm{

    private Solvers solvers;

    public AgentProgramm(Byte[] puzzle){
        solvers = new Solvers(new Puzzle(puzzle));
    }

    /**
     * 
     * @param method
     * @return
     */
    public int[] solve( Integer method ){
        if( method == 1 ) solvers.solveBreadth();
        else if( method == 2 ) solvers.solveDepth();
        else if( method == 3 ) solvers.solveAStar('A');
        else if( method == 4 ) solvers.solveAStar('B');

        return solvers.solution();
    }

}