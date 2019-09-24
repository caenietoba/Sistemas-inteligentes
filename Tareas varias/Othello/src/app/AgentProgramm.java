/*
 * #### requires ps-version 3.0 ####
 * <#
 *    Version:        0.1
 *    Author:         Camilo Nieto
 *    Creation Date:  Thursday, September 19th 2019, 12:49:07 pm
 *    File: AgentProgramm.java
 *    Copyright (c) 2019 Your Company
 * 
 * .LICENSE
 * Free software created by Camilo Esteban Nieto Barrera
 *  
 */

package app;

import java.util.ArrayList;

import java.util.PriorityQueue;

public class AgentProgramm{

    private PriorityQueue<Board> p_queue = new PriorityQueue<>();
    Othello othello;
    Heuristics heuristics;

    /**
     * 
     * @param othello
     */
    public AgentProgramm( Othello othello ) {
        this.othello = othello;
        heuristics = new Heuristics(this.othello);
    } 

    /**
     * 
     * @param childs_board
     * @return
     */
    public Byte[][] alphabeta( ArrayList<Byte[][]> childs_board ){
        int depth = 3;
        int alpha = -1000000000;
        int betha = 1000000000;
        Boolean maximizing_player = true;
        p_queue = new PriorityQueue<>();
        for (Byte[][] board : childs_board) {
            p_queue.add( new Board( board, alphabeta(board, depth, alpha, betha, maximizing_player) ) );
        }
        othello.setPlayer(!othello.getPlayer());
        return p_queue.peek().getBoard();
    }

    /**
     * 
     * @param board
     * @param depth
     * @param alpha
     * @param betha
     * @param maximizing_player
     * @return
     */
    private Integer alphabeta( Byte[][] board, int depth, int alpha, int betha, Boolean maximizing_player ){
        int value;
        ArrayList<Byte[][]> childs_board;
        
        if( depth == 0 || othello.isFinished( board ) ) return heuristics.heuristicA( board );
        if( maximizing_player ){    
            value = -1000000000;
            childs_board = othello.getChilds( board, maximizing_player );
            for (Byte[][] child : childs_board) {
                value = Math.max( value, alphabeta( child, depth - 1, alpha, betha, false ) );
                alpha = Math.max( alpha, value );
                if( alpha >= betha ) break;
            }
            return value;
        } else {
            value = 1000000000;
            childs_board = othello.getChilds( board, maximizing_player );
            for (Byte[][] child : childs_board) {
                value = Math.min( value, alphabeta( child, depth - 1, alpha, betha, true ) );
                betha = Math.min( betha, value );
                if( alpha >= betha ) break;
            }
            return value;
        }
    }

    

}