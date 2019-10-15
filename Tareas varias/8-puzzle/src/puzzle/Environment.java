package puzzle;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Some info
 * http://w01fe.com/blog/2009/01/the-hardest-eight-puzzle-instances-take-31-moves-to-solve/
 * https://medium.com/@sairamankumar2/8-puzzle-problem-aa578104ae15
 * http://w01fe.com/blog/2009/01/the-hardest-eight-puzzle-instances-take-31-moves-to-solve/
 */

public class Environment {

    private Scanner sc;
    
    /**
     * 
     */
    public void run(){

        sc = new Scanner(System.in);

        AgentProgramm agent = initAgent();
        int[] results;

        Integer selection_menu = 0;
        
        while( selection_menu != 6 ){
            System.out.println("\n-------- MENU --------");
            System.out.println("1. Breath-first search");
            System.out.println("2. Depth-first search");
            System.out.println("3. A* search using heuristic A");
            System.out.println("4. A* search using heuristic B");
            System.out.println("5. Change puzzle");
            System.out.println("6. End");

            System.out.print("Selection: ");
            selection_menu = sc.nextInt();
            System.out.print("\n");

            switch( selection_menu ){
                case 1:
                    results = agent.solve( selection_menu );
                    printResult( "Breath-first search", results[0], results[1] );
                    break;
                case 2:
                    results = agent.solve( selection_menu );
                    printResult( "Depth-first search", results[0], results[1] );
                    break;
                case 3:
                    results = agent.solve( selection_menu );
                    printResult( "A* search ()", results[0], results[1] );
                    break;
                case 4:
                    results = agent.solve( selection_menu );
                    printResult( "A* search ()", results[0], results[1] );
                    break;
                case 5:
                    agent = initAgent();
                    break;
                case 6: 
                    System.out.println("Programm ended");
                    break;
                default:
                    System.out.println("Bad option selected, please digit one of the menu options.");
            }
        }

        sc.close();
    }

    /**
     * 
     */
    private AgentProgramm initAgent(){
        Boolean bad_number = true;
        Byte[] initial_puzzle = new Byte[9];
        Integer puzzle;
        while( bad_number ){
            System.out.print("Enter a puzzle in the form of n integer of 9 digits: ");
            puzzle = sc.nextInt();
            initial_puzzle = splitNumber( puzzle );
            if( !confirmNumber( initial_puzzle ) ) System.out.println("All digits must be different and can have only digits from [0...8]");
            else if( isUnsolvable(initial_puzzle) ) System.out.println("The puzzle is unsolvable.");
            else bad_number = false;
        }
        return new AgentProgramm( initial_puzzle );

    }

    /**
     * 
     */
    private Byte[] splitNumber( Integer number ){
        int[] initial_puzzle = new int[9];
        for (int i = 0; i < 9; i++) {
            initial_puzzle[8-i] = number%10;
            number = (number - number%10) / 10;
        }

        Byte[] byte_puzzle = new Byte[9];
        for (int i = 0; i < 9; i++) {
            byte_puzzle[i] = (byte) initial_puzzle[i];
        }
        return byte_puzzle;
    }

    /**
     * 
     * @param puzzle
     * @return
     */
    private Boolean confirmNumber( Byte[] puzzle ){
        HashSet<Byte> used = new HashSet<>();
        if( puzzle.length != 9 ) return true;
        for (int i = 0; i < 9; i++) {
            if( used.contains( puzzle[i] ) || puzzle[i] >= 9 )
                return false;
            else
                used.add( puzzle[i] );
        }
        return true;
    }

    /**
     * 
     * @param puzzle
     * @return
     */
    public Boolean isUnsolvable(Byte[] puzzle){
        int count = 0;
        for(int i = 0; i < puzzle.length - 1; i++){
            for(int j = i; j < puzzle.length; j++){
                if (puzzle[i] > puzzle[j] && puzzle[j] != 0) count++;
            }
        }
        if(count%2 == 1) return true;
        else return false;
    }
    //https://math.stackexchange.com/questions/293527/how-to-check-if-a-8-puzzle-is-solvable/1402737#1402737

    /**
     * 
     */
    private void printResult( String method, Integer processing, Integer memory ){
        System.out.println( "\n" + method + "-> processing: " + processing + ", memory: " + memory + ".\n");
    }

}