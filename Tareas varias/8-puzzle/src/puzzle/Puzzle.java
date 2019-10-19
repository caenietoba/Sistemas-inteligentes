package puzzle;

import java.util.Arrays;


public class Puzzle implements Comparable<Puzzle> {

    private Byte[] puzzle;
    private int priority;

    public static Byte[] goal_puzzle = {
        1,2,3,
        4,5,6,
        7,8,0
    };

    public Puzzle(Byte[] puzzle){
        this.puzzle = puzzle;
    }

    /**
     * 
     */
    public static final Movement<Puzzle> left = puzzle -> {
        Byte p0 = puzzle.findBlank();
        int size = (int)Math.sqrt(puzzle.getSize());

        if( p0 % size != 0 ) {
            puzzle.changePositions(p0, -1);
            return puzzle;
        }
        return null;
    };

    /**
     * 
     */
    public static final Movement<Puzzle> rigth = puzzle -> {
        Byte p0 = puzzle.findBlank();
        int size = (int)Math.sqrt(puzzle.getSize());

        if( p0 % size != size - 1 ) {
            puzzle.changePositions(p0, 1);
            return puzzle;
        }
        return null;
    };

    /**
     * 
     */
    public static final Movement<Puzzle> up = puzzle -> {
        Byte p0 = puzzle.findBlank();
        int size = (int)Math.sqrt(puzzle.getSize());

        if( p0 / size >= 1 ) {
            puzzle.changePositions(p0, -size);
            return puzzle;
        }
        return null;
    };

    /**
     * 
     */
    public static final Movement<Puzzle> down = puzzle -> {
        Byte p0 = puzzle.findBlank();
        int size = (int)Math.sqrt(puzzle.getSize());

        if( p0 / size < size - 1 ) {
            puzzle.changePositions(p0, size);
            return puzzle;
        }
        return null;
    };

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public static final Movement<Puzzle>[] moves = new Movement[]{
        up,down,left,rigth
    };

    /**
     * 
     */
    public void changePositions( int p0, int pos ){
        Byte aux = puzzle[p0+pos];
        puzzle[p0+pos] = puzzle[p0];
        puzzle[p0] = aux;
    }

    /**
     * 
     */
    public Byte findBlank(){
        for (int i = 0; i < this.puzzle.length; i++)
            if( this.puzzle[i] == 0 ) return (byte)i;
        return -1;
    }

    /**
     * 
     */
    public void printPuzzle(){
        System.out.println("Puzzle: ");
        for (int i = 0; i < this.puzzle.length; i++) {
            if( i % 3 == 0 && i != 0 ) System.out.println("\n");
            System.out.print(this.puzzle[i]);
        }
    }

    /**
     * 
     */
    public Byte[] clonePuzzle(){
        return this.puzzle.clone();
    }

    /**
     * 
     * @return
     */
    public Boolean isGoal(){
       return Arrays.equals(this.puzzle, Puzzle.goal_puzzle) ? true: false;
    }

    public Byte[] getPuzzle() {
        return this.puzzle;
    }

    public Byte getSize() {
        return (byte)this.puzzle.length;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Puzzle)) {
            return false;
        }
        Puzzle puzzle = (Puzzle) o;
        return Arrays.equals(this.puzzle, puzzle.puzzle); //
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(puzzle); //
    }

    @Override
    public String toString() {
        return "{" +
            " puzzle='" + getPuzzle() + "'" +
            ", priority='" + getPriority() + "'" +
            "}";
    }

    @Override
    public int compareTo(Puzzle puzzle) {
        if(this.getPriority() > puzzle.getPriority()) return 1;
        else if(this.getPriority() < puzzle.getPriority()) return -1;
        else return 0;
    }
    
}