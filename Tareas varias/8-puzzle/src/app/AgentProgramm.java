package app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class AgentProgramm{

    private Byte[] initial_8puzzle = new Byte[9];
    private Byte[] goal_8puzzle = {1,2,3,4,5,6,7,8,0};

    private Stack<Byte[]> stack_puzzles; 
    private Queue<Byte[]> queue_puzzles;
    private PriorityQueue<PuzzleData> prior_queue_puzzles; 
    private HashMap<List, Boolean> looked_puzzles;

    private Integer nodes_visited;
    private Integer max_memory;

    private Integer method;

    private class PuzzleData implements Comparable<PuzzleData>{
        private Byte[] puzzle;
        private Integer priority;

        public PuzzleData( Byte[] puzzle, Integer priority ){
            this.puzzle = puzzle;
            this.priority = priority;
        }

        public Byte[] getPuzzle(){
            return this.puzzle;
        }

        public Integer getPriority(){
            return this.priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PuzzleData puzzle = (PuzzleData) o;
            return Integer.compare(puzzle.getPriority(), this.getPriority()) == 0 &&
                    Arrays.equals(this.getPuzzle(), puzzle.getPuzzle());
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(this.getPuzzle().toString(), this.getPriority());
        }

        @Override
        public int compareTo(PuzzleData puzzle) {
            if(this.getPriority() > puzzle.getPriority()) return 1;
            else if(this.getPriority() < puzzle.getPriority()) return -1;
            else return 0;
        }
    }

    public AgentProgramm( Byte[] puzzle ) {
        this.initial_8puzzle = puzzle;
    }

    public Integer[] solve( Integer method ){
        this.method = method;

        if( method == 1 ) solveBreadth();
        else if( method == 2 ) solveDepth();
        else if( method == 3 ) solveAStar();
        else if( method == 4 ) solveAStar();
        Integer[] result = {nodes_visited, max_memory};
        return result;
    }

    private void solveBreadth(){

        Byte[] aux;

        queue_puzzles = new LinkedList<Byte[]>();
        looked_puzzles = new HashMap<List, Boolean>();
        nodes_visited = 0;
        max_memory = 0;

        List<Byte> list = Arrays.asList( initial_8puzzle );
        queue_puzzles.add( initial_8puzzle );
        looked_puzzles.put( list, true );
        while( !queue_puzzles.isEmpty() ){

            if( queue_puzzles.size() > max_memory ) max_memory = queue_puzzles.size();

            aux = queue_puzzles.poll();
            nodes_visited++;

            if( Arrays.equals( goal_8puzzle, aux ) ) break;
             
            findChilds( aux );
        }
    }

    private void solveDepth(){
        Byte[] aux;

        stack_puzzles = new Stack<Byte[]>(); 
        looked_puzzles = new HashMap<List, Boolean>();
        nodes_visited = 0;
        max_memory = 0;

        stack_puzzles.add( initial_8puzzle );
        List<Byte> list = Arrays.asList( initial_8puzzle );
        looked_puzzles.put( list, true );
        while( !stack_puzzles.isEmpty() ){
            if( stack_puzzles.size() > max_memory ) max_memory = stack_puzzles.size();

            aux = stack_puzzles.pop();
            nodes_visited++;

            if( Arrays.equals( goal_8puzzle, aux ) ) break;

            findChilds( aux );
        }
    }

    private void solveAStar(){
        Byte[] aux;

        prior_queue_puzzles = new PriorityQueue<PuzzleData>();
        looked_puzzles = new HashMap<List, Boolean>();
        nodes_visited = 0;
        max_memory = 0;

        prior_queue_puzzles.add( new PuzzleData(initial_8puzzle, 100) );
        List<Byte> list = Arrays.asList( initial_8puzzle );
        looked_puzzles.put( list, true );
        while( !prior_queue_puzzles.isEmpty() ){

            if( prior_queue_puzzles.size() > max_memory ) max_memory = prior_queue_puzzles.size();

            aux = prior_queue_puzzles.poll().getPuzzle();
            nodes_visited++;

            if( Arrays.equals( goal_8puzzle, aux ) ) break;

            findChilds( aux );
        }
    }

    private Integer heuristicA( Byte[] array ){
        Integer f = 1;
        for (int i = 0; i < 9; i++) 
            if( array[i] != 0 && array[i] != goal_8puzzle[i] )
                f++;
        return f;
    }

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

    private void addChild( Byte[] array ){
        List<Byte> list = Arrays.asList( array );

        if( looked_puzzles.get( list ) == null ){
            if( method == 1 ) queue_puzzles.add( array );
            if( method == 2 ) stack_puzzles.add( array );
            if( method == 3 ) prior_queue_puzzles.add( new PuzzleData( array, heuristicA(array) ) );
            if( method == 4 ) prior_queue_puzzles.add( new PuzzleData( array, heuristicB(array) ) );
            looked_puzzles.put( list, true );
        }
    }

    private void findChilds( Byte[] array ){
        Integer p0 = getP0( array );

        if( p0 % 3 != 0 ) addChild( left( p0, array.clone() ) );
        if( p0 % 3 != 2 ) addChild( right( p0, array.clone() ) );
        if( p0 / 3 >= 1 ) addChild( up( p0, array.clone() ) );
        if( p0 / 3 < 2 )  addChild( down( p0, array.clone() ) );
    }

    private Integer getP0( Byte[] array ){
        for (int i = 0; i < 9; i++)
            if( array[i] == 0 )
                return i;
        return 0;
    }

    /** Actions */
    private Byte[] left( Integer p0, Byte[] copy ){
        Byte aux = copy[p0-1];
        copy[p0-1] = copy[p0];
        copy[p0] = aux;
        return copy;
    }

    private Byte[] right( Integer p0, Byte[] copy ){
        Byte aux = copy[p0+1];
        copy[p0+1] = copy[p0];
        copy[p0] = aux;
        return copy;
    }

    private Byte[] up( Integer p0, Byte[] copy ){
        Byte aux = copy[p0-3];
        copy[p0-3] = copy[p0];
        copy[p0] = aux;
        return copy;
    }

    private Byte[] down( Integer p0, Byte[] copy ){
        Byte aux = copy[p0+3];
        copy[p0+3] = copy[p0];
        copy[p0] = aux;
        return copy;
    }

}