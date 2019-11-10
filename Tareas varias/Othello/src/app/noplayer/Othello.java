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

import java.util.ArrayList;
import java.util.Objects;

public class Othello{

    private Byte[][] initial_board;
    private Integer rows;
    private Integer cols;
    private Boolean player = true;
    private Integer total_tokens;

    /**
     * 
     * @param rows
     * @param cols
     */
    public Othello( Integer rows, Integer cols ){
        this.rows = rows;
        this.cols = cols;
        this.initial_board = new Byte[rows][cols];
        this.total_tokens = rows*cols;
        initBoard();
    }

    /** */
    private void initBoard(){
        int middle_rows = this.rows/2;
        int middle_cols = this.cols/2;

        this.initial_board[middle_rows - 1][middle_cols - 1] = 0;
        this.initial_board[middle_rows - 1][middle_cols] = 1;
        this.initial_board[middle_rows][middle_cols - 1] = 1;
        this.initial_board[middle_rows][middle_cols] = 0;
    }

    /**
     * 
     * @param board
     * @param player
     * @return
     */
    public ArrayList<Byte[][]> getChilds( Byte[][] board, Boolean player ){
        ArrayList<Byte[][]> childs = new ArrayList<>();
        for( int i=0; i<this.rows; i++ ){
            for( int j=0; j<this.cols; j++ ){

                if( board[i][j] == null )continue;
                if( i > 0 && j > 0 && board[i-1][j-1] == null ) childs.add( addChild( copy( board ), i-1, j-1 ) );
                if( i > 0 && j < cols-1 && board[i-1][j+1] == null ) childs.add( addChild( copy( board ), i-1, j+1 ) );
                if( i < rows-1 && j > 0 && board[i+1][j-1] == null ) childs.add( addChild( copy( board ), i+1, j-1 ) );
                if( i < rows-1 && j < cols-1 && board[i+1][j+1] == null ) childs.add( addChild( copy( board ), i+1, j+1 ) );
                if( i > 0 && board[i-1][j] == null ) childs.add( addChild( copy( board ), i-1, j ) );
                if( i < rows-1 && board[i+1][j] == null ) childs.add( addChild( copy( board ), i+1, j ) );
                if( j > 0 && board[i][j-1] == null ) childs.add( addChild( copy( board ), i, j-1 ) );
                if( j < cols-1 && board[i][j+1] == null ) childs.add( addChild( copy( board ), i, j+1 ) );
            }
        }
        return childs;
    }

    /**
     * 
     * @param board
     * @param i
     * @param j
     * @return
     */
    private Byte[][] addChild( Byte[][] board, int i, int j ){

        if( this.player ) board[i][j] = 0;
        else board[i][j] = 1;

        board = changeTokens(board, i, j, -1, -1);
        board = changeTokens(board, i, j, -1, 0);
        board = changeTokens(board, i, j, -1, 1);
        board = changeTokens(board, i, j, 0, -1);
        board = changeTokens(board, i, j, 0, 1);
        board = changeTokens(board, i, j, 1, -1);
        board = changeTokens(board, i, j, 1, 0);
        board = changeTokens(board, i, j, 1, 1);

        return board;
    }

    /**
     * 
     * @param board
     * @param i
     * @param j
     * @param _i
     * @param _j
     * @return
     */
    private Byte[][] changeTokens( Byte[][] board, int i, int j, int _i, int _j ){
        return changeTokens(board, null, i+_i, j+_j, _i, _j, false, false);
    }

    /**
     * 
     * @param board
     * @param aux_board
     * @param i
     * @param j
     * @param _i
     * @param _j
     * @param created
     * @param change
     * @return
     */
    private Byte[][] changeTokens( 
                    Byte[][] board, Byte[][] aux_board, 
                    int i, int j, int _i, int _j,
                    Boolean created, Boolean change ){

        if( i < 0 || j < 0 || i >= this.rows || j >= this.cols || board[i][j] == null )
            return board;
        if( !created ){
            aux_board = copy(board);
            created = true;
        }
        if( this.player && board[i][j] == 0 || !this.player && board[i][j] == 1 )
            if( change ) return aux_board;
            else return board;

        if( this.player ) aux_board[i][j] = 0;
        else aux_board[i][j] = 1;
        change = true;
        return changeTokens(board, aux_board, i+_i, j+_j, _i, _j, created, change);
    }

    /**
     * 
     * @param board
     * @return
     */
    private Byte[][] copy( Byte[][] board ){
        Byte[][] aux = new Byte[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) 
            aux[i] = board[i].clone();
        return aux;
    }

    /**
     * 
     * @param board
     * @return
     */
    public Boolean isFinished( Byte[][] board ){
        for (int i = 0; i < this.rows; i++) 
            for (int j = 0; j < this.cols; j++) 
                if( board[i][j] == null )
                    return false;
        return true;
                
    }

    /**
     * 
     * @param board
     * @return
     */
    public Integer winner( Byte[][] board ){
        Integer ceros = 0;
        for( int i=0; i<this.rows; i++ )
            for( int j=0; j<this.cols; j++ )
                if( board[i][j] == 0 )
                    ceros++;
        return ceros > this.total_tokens/2 ? 0 : 1;
    }

    /**
     * 
     * @return
     */
    public Boolean getTurn() {
        return this.player;
    }

    /**
     * 
     * @param player
     */
    public void setTurn(Boolean player) {
        this.player = player;
    }

    /**
     * 
     * @return
     */
    public Byte[][] getInitialBoard() {
        return this.initial_board;
    }

    /**
     * 
     * @return
     */
    public Integer getRows() {
        return this.rows;
    }

    /**
     * 
     * @param rows
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * 
     * @return
     */
    public Integer getCols() {
        return this.cols;
    }

    /**
     * 
     * @param board
     * @return
     */
    public ArrayList<Byte[][]> getChilds( Byte[][] board ){
        return getChilds( board, true );
    }

    /**
     * 
     * @param player
     */
    public void setPlayer( Boolean player){
        this.player = player;
    }

    /**
     * 
     * @return
     */
    public Boolean getPlayer(){
        return this.player;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Othello)) {
            return false;
        }
        Othello othello = (Othello) o;
        return Objects.equals(initial_board, othello.initial_board) && Objects.equals(rows, othello.rows) && Objects.equals(cols, othello.cols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initial_board, rows, cols);
    }

    @Override
    public String toString() {
        return "{" +
            " board='" + getInitialBoard() + "'" +
            ", rows='" + getRows() + "'" +
            ", cols='" + getCols() + "'" +
            "}";
    }

    /**
     * 
     * @param board
     */
    public void printBoard( Byte[][] board ){
        for( int i=0; i<this.cols; i++ )
            System.out.print("-");
        System.out.println("");
        for( int i=0; i<this.rows; i++ ){
            for( int j=0; j<this.cols; j++ ){
                System.out.print( (board[i][j] == null ? "'": board[i][j]) + " " );
            }
            System.out.println();
        }
        for( int i=0; i<this.cols; i++ )
            System.out.print("-");
        System.out.println("");
    }

}