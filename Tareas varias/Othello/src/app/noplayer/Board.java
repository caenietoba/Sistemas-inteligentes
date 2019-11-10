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

package app.noplayer;

import java.util.Random;

public class Board implements Comparable<Board> {
    private Byte[][] board;
    private Integer priority;

    private Random rd = new Random();

    /**
     * 
     * @param board
     * @param priority
     */
    public Board( Byte[][] board, Integer priority ){
        this.board = board;
        this.priority = priority;
    }

    /**
     * 
     * @param board
     */
    public Board( Byte[][] board ){
        this.board = board;
        this.priority = 100;
    }

    /**
     * 
     * @return
     */
    public Byte[][] getBoard(){
        return this.board;
    }

    @Override
    public int compareTo(Board o) {
        if( o.priority > this.priority ) return 1;
        if( o.priority < this.priority ) return -1;
        return rd.nextInt( 2 ) == 0 ? -1 : 1;
    }

}