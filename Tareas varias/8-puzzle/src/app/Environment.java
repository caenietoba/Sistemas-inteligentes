package app;

import java.util.HashMap;
import java.util.Scanner;

class Environment {

    Scanner sc;
    
    public void run(){

        sc = new Scanner(System.in);

        AgentProgramm agent = initAgent();
        Integer[] results;

        Integer selection_menu = 0;
        
        while( selection_menu != 6 ){
            System.out.println("-------- MENU --------");
            System.out.println("1. Breath-first search");
            System.out.println("2. Depth-first search");
            System.out.println("3. A* search using heuristic A");
            System.out.println("4. A* search using heuristic B");
            System.out.println("5. Change puzzle");
            System.out.println("6. End");

            System.out.print("Selection: ");
            selection_menu = sc.nextInt();

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
                    printResult( "A* search", results[0], results[1] );
                    break;
                case 4:
                    results = agent.solve( selection_menu );
                    printResult( "A* search", results[0], results[1] );
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

    private AgentProgramm initAgent(){
        Boolean bad_number = true;
        Byte[] initial_8puzzle = new Byte[9];
        Integer puzzle;
        while( bad_number ){
            System.out.print("Enter a puzzle in the form of n integer of 9 digits: ");
            puzzle = sc.nextInt();
            initial_8puzzle = splitNumber( puzzle );
            bad_number = confirmNumber( initial_8puzzle );
            if( bad_number ) System.out.println("All digits must be different and can have only digits from [0...8]");
        }
        return new AgentProgramm( initial_8puzzle );

    }

    private Byte[] splitNumber( Integer number ){
        int[] initial_8puzzle = new int[9];
        for (int i = 0; i < 9; i++) {
            initial_8puzzle[8-i] = number%10;
            number = (number - number%10) / 10;
        }

        Byte[] byte_puzzle = new Byte[9];
        for (int i = 0; i < 9; i++) {
            byte_puzzle[i] = (byte) initial_8puzzle[i];
        }
        return byte_puzzle;
    }

    private Boolean confirmNumber( Byte[] puzzle ){
        HashMap<Byte, Boolean> used = new HashMap<>();
        if( puzzle.length != 9 ) return true;
        for (int i = 0; i < 9; i++) {
            if( used.get( puzzle[i] ) != null || puzzle[i] >= 9 )
                return true;
            else
                used.put( puzzle[i], true );
        }
        return false;
    }

    private void printResult( String method, Integer processing, Integer memory ){
        System.out.println( "\n" + method + "-> processing: " + processing + ", memory: " + memory + ".\n");
    }

}