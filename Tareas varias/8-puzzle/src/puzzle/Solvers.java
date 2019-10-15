package puzzle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solvers {

    private LinkedList<Puzzle> queue_puzzles;
    private Stack<Puzzle> stack_puzzles;
    private PriorityQueue<Puzzle> prior_puzzles;
    private HashSet<Puzzle> looked_puzzles;

    private int nodes_visited;
    private int max_memory;

    private Puzzle puzzle;
    private Puzzle aux_puzzle;

    public Solvers(Puzzle initial_puzzle){
        this.puzzle = initial_puzzle;
    }

    /**
     * 
     * @return
     */
    public int[] solution(){
        return new int[]{nodes_visited, max_memory};
    }

    /**
     * 
     */
    public void solveBreadth(){

        Puzzle other;

        queue_puzzles = new LinkedList<Puzzle>();
        looked_puzzles = new HashSet<Puzzle>();
        nodes_visited = 0;
        max_memory = 0;

        queue_puzzles.add( puzzle );
        looked_puzzles.add( puzzle );

        while( !queue_puzzles.isEmpty() ){

            if( queue_puzzles.size() > max_memory ) max_memory = queue_puzzles.size();
            nodes_visited++;

            aux_puzzle = queue_puzzles.poll();

            if( aux_puzzle.isGoal() ) break;
             
            for (Movement<Puzzle> movement : Puzzle.moves) {
                other = new Puzzle(aux_puzzle.clonePuzzle());
                other = movement.move(other);

                if( other == null || looked_puzzles.contains(other) ) continue;

                looked_puzzles.add(other);
                queue_puzzles.add(other);
            }
        }
    }

    /**
     * 
     */
    public void solveDepth(){
        Puzzle other;

        stack_puzzles = new Stack<Puzzle>(); 
        looked_puzzles = new HashSet<>();
        nodes_visited = 0;
        max_memory = 0;

        stack_puzzles.add( puzzle );
        looked_puzzles.add( puzzle );
        
        while( !stack_puzzles.isEmpty() ){
            if( stack_puzzles.size() > max_memory ) max_memory = stack_puzzles.size();
            nodes_visited++;

            aux_puzzle = stack_puzzles.pop();

            if( aux_puzzle.isGoal() ) break;
             
            for (Movement<Puzzle> movement : Puzzle.moves) {
                other = new Puzzle(aux_puzzle.clonePuzzle());
                other = movement.move(other);

                if( other == null || looked_puzzles.contains(other) ) continue;
                stack_puzzles.add(other);
                looked_puzzles.add(other);
            }
        }
    }

    /**
     * 
     * @param heuristic
     */
    public void solveAStar( Character heuristic ){
        Puzzle other;

        prior_puzzles = new PriorityQueue<>();
        looked_puzzles = new HashSet<>();
        nodes_visited = 0;
        max_memory = 0;

        prior_puzzles.add( puzzle );
        looked_puzzles.add( puzzle );

        while( !prior_puzzles.isEmpty() ){

            if( prior_puzzles.size() > max_memory ) max_memory = prior_puzzles.size();

            nodes_visited++;

            aux_puzzle = prior_puzzles.poll();

            if( aux_puzzle.isGoal() ) break;
             
            for (Movement<Puzzle> movement : Puzzle.moves) {
                other = new Puzzle(aux_puzzle.clonePuzzle());
                other = movement.move(other);

                if( other == null || looked_puzzles.contains(other) ) continue;

                if(heuristic == 'A') other.setPriority( heuristicA(other.getPuzzle()) );
                else other.setPriority( heuristicB(other.getPuzzle()) );

                prior_puzzles.add(other);
                looked_puzzles.add(other);
            }
        }
    }

    /**
     * 
     * @param array
     * @return
     */
    private Integer heuristicA( Byte[] array ){
        Integer f = 1;
        for (int i = 0; i < 9; i++) 
            if( array[i] != 0 && array[i] != Puzzle.goal_puzzle[i] )
                f++;
        return f;
    }

    /**
     * 
     * @param array
     * @return
     */
    private Integer heuristicB( Byte[] array ){
        Integer f = 1;
        for (int i = 0; i < 9; i++) {
            if( array[i] != 0 ){
                f += Math.abs( ((array[i]-1) % 3) - (i % 3) );
                f += Math.abs( (byte)((array[i]-1) / 3) - (byte)(i / 3) );
            }
        }
        return f;
    }

}