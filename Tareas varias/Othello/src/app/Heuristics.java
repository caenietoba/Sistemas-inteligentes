/*
 * #### requires ps-version 3.0 ####
 * <#
 *    Version:        0.1
 *    Author:         Camilo Nieto
 *    Creation Date:  Monday, September 23rd 2019, 8:09:17 pm
 *    File: Heuristics.java
 *    Copyright (c) 2019 Your Company
 * 
 * .LICENSE
 * Free software created by Camilo Esteban Nieto Barrera
 *  
 */

package app;

public class Heuristics{

    private Othello othello;

    /**
     * 
     * @param othello
     */
    public Heuristics( Othello othello ){
        this.othello = othello;
    }

    /**
     * 
     * @param board
     * @return
     */
    public Integer heuristicA( Byte[][] board ){

        //https://github.com/kartikkukreja/blog-codes/blob/master/src/Heuristic%20Function%20for%20Reversi%20(Othello).cpp

        Integer priority = 0;
        int rows = board.length;
        int cols = board[0].length;
        Boolean player = othello.getPlayer();
        for (int i = 0; i < rows; i++) {
            for( int j = 0; j < cols; j++ ){
                if( player ){
                    if( board[i][j] != null && board[i][j] == 0 ){
                        if( i == 0 && j == 0 
                            || i == 0 && j == cols-1 
                            || i == rows-1 && j == 0 
                            || i == rows-1 && j == cols-1 
                        )
                            priority += 10;
                        else if( i == 0 || j == 0 || i == rows-1 || j == cols-1 ) 
                            priority += 5;
                        else priority += 3;
                    }
                }else{
                    if( board[i][j] != null && board[i][j] == 1 ){
                        if( i == 0 && j == 0 
                            || i == 0 && j == cols-1 
                            || i == rows-1 && j == 0 
                            || i == rows-1 && j == cols-1 
                        )
                            priority += 10;
                        else if( i == 0 || j == 0 || i == rows-1 || j == cols-1 ) 
                            priority += 5;
                        else priority += 3;
                    }
                }
            }
        }

        return priority;

    }
}