package app;

import java.util.Scanner;

public class Environment{

    private Othello othello;

    public void run(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print( "Number of rows: " );
        int rows = sc.nextInt();
        System.out.print( "Number of cols: " );
        int cols = sc.nextInt();

        sc.close();
        
        othello = new Othello(rows, cols);
        AgentProgramm agent = new AgentProgramm( othello );
        Byte[][] board = othello.getInitialBoard();

        while( !finish( board ) ){
            board = agent.alphabeta(othello.getChilds( board ), true);
            othello.printBoard(board);
        }

        othello.printBoard(board);

        System.out.println( othello.winner( board ) );
    }

    private Boolean finish( Byte[][] board ){
        if( !othello.isFinished( board ) )
            return false;
        return true;
    }
    
}