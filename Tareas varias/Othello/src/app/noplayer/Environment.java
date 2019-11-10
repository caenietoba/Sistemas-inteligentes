/*
 * #### requires ps-version 3.0 ####
 * <#
 *    Version:        0.1
 *    Author:         Camilo Nieto
 *    Creation Date:  Thursday, September 19th 2019, 12:48:51 pm
 *    File: Environment.java
 *    Copyright (c) 2019 Your Company
 * 
 * .LICENSE
 * Free software created by Camilo Esteban Nieto Barrera
 *  
 */
package app.noplayer;

import java.util.Scanner;

public class Environment{

    private Othello othello;

    /**
     * The environment principal function
     */
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
            board = agent.alphabeta(othello.getChilds( board ));
            othello.printBoard(board);
        }

        othello.printBoard(board);

        printWinner( othello.winner( board ) );
    }

    /**
     * Looks if the game is over 
     * @param board Board that will be analized to see if the game is over
     * @return Return true if game is over, otherwise return false
     */
    private Boolean finish( Byte[][] board ){
        if( !othello.isFinished( board ) )
            return false;
        return true;
    }

    /**
     * Print the winner player
     * @param winner Integer that represents the player
     */
    private void printWinner( Integer winner ){
        System.out.println( "The winner is player: " + winner );
    }
    
}