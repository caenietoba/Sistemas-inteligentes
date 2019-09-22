package app;

import java.util.ArrayList;
import java.util.Objects;

public class Othello {

    private Byte[][] initial_board;
    private Integer rows;
    private Integer cols;
    private Boolean maximizing_player = true;
    private Integer total_tokens;
    private Integer actual_tokens = 4;

    public Othello( Integer rows, Integer cols ){
        this.rows = rows;
        this.cols = cols;
        this.initial_board = new Byte[rows][cols];
        this.total_tokens = rows*cols;
        initBoard();
        printBoard(initial_board);
    }

    private void initBoard(){
        int middle_rows = this.rows/2;
        int middle_cols = this.cols/2;

        this.initial_board[middle_rows - 1][middle_cols - 1] = 0;
        this.initial_board[middle_rows - 1][middle_cols] = 1;
        this.initial_board[middle_rows][middle_cols - 1] = 1;
        this.initial_board[middle_rows][middle_cols] = 0;
    }

    public ArrayList<Byte[][]> getChilds( Byte[][] board ){
        return getChilds( board, true );
    }

    public void setMaximizingPlayer( Boolean maximizing_player){
        this.maximizing_player = maximizing_player;
    }

    public Boolean getMaximizingPlayer(){
        return this.maximizing_player;
    }

    public ArrayList<Byte[][]> getChilds( Byte[][] board, Boolean maximizing_player ){
        ArrayList<Byte[][]> childs = new ArrayList<>();
        for( int i=0; i<this.rows; i++ ){
            for( int j=0; j<this.cols; j++ ){

                if( board[i][j] == null )continue;
                if( i > 0 && j > 0 && board[i-1][j-1] == null ) childs.add( addChild( copy( board ), i-1, j-1, maximizing_player ) );
                if( i > 0 && j < cols-1 && board[i-1][j+1] == null ) childs.add( addChild( copy( board ), i-1, j+1, maximizing_player ) );
                if( i < rows-1 && j > 0 && board[i+1][j-1] == null ) childs.add( addChild( copy( board ), i+1, j-1, maximizing_player ) );
                if( i < rows-1 && j < cols-1 && board[i+1][j+1] == null ) childs.add( addChild( copy( board ), i+1, j+1, maximizing_player ) );
                if( i > 0 && board[i-1][j] == null ) childs.add( addChild( copy( board ), i-1, j, maximizing_player ) );
                if( i < rows-1 && board[i+1][j] == null ) childs.add( addChild( copy( board ), i+1, j, maximizing_player ) );
                if( j > 0 && board[i][j-1] == null ) childs.add( addChild( copy( board ), i, j-1, maximizing_player ) );
                if( j < cols-1 && board[i][j+1] == null ) childs.add( addChild( copy( board ), i, j+1, maximizing_player ) );
            }
        }
        return childs;
    }

    private Byte[][] addChild( Byte[][] board, int i, int j, Boolean maximizing_player ){

        if( this.maximizing_player ) board[i][j] = 0;
        else board[i][j] = 1;

        board = changeTokens(board, i, j, -1, -1, this.maximizing_player);
        board = changeTokens(board, i, j, -1, 0, this.maximizing_player);
        board = changeTokens(board, i, j, -1, 1, this.maximizing_player);
        board = changeTokens(board, i, j, 0, -1, this.maximizing_player);
        board = changeTokens(board, i, j, 0, 1, this.maximizing_player);
        board = changeTokens(board, i, j, 1, -1, this.maximizing_player);
        board = changeTokens(board, i, j, 1, 0, this.maximizing_player);
        board = changeTokens(board, i, j, 1, 1, this.maximizing_player);

        return board;
    }

    private Byte[][] changeTokens( Byte[][] board, int i, int j, int _i, int _j, Boolean maximizing_player ){
        return changeTokens(board, null, i+_i, j+_j, _i, _j, maximizing_player, false, false);
    }

    private Byte[][] changeTokens( 
                    Byte[][] board, Byte[][] aux_board, 
                    int i, int j, int _i, int _j, 
                    Boolean maximizing_player, Boolean created, Boolean change ){

        if( i < 0 || j < 0 || i >= this.rows || j >= this.cols || board[i][j] == null )
            return board;
        if( !created ){
            aux_board = copy(board);
            created = true;
        }
        if( maximizing_player && board[i][j] == 0 || !maximizing_player && board[i][j] == 1 )
            if( change ) return aux_board;
            else return board;

        if( maximizing_player ) aux_board[i][j] = 0;
        else aux_board[i][j] = 1;
        change = true;
        return changeTokens(board, aux_board, i+_i, j+_j, _i, _j, maximizing_player, created, change);
    }

    /* private Byte[][] changeTokens( Byte[][] board, int i, int j, int _i, int _j, Boolean maximizing_player ){
        if( board[i+_i][j+_j] == null ) return board;

        Byte[][] aux_board = copy(board);

        while( i+_i > 0 && j+_j > 0 && i+_i < this.rows-1 && j+_j < this.cols-1 && aux_board[i+_i][j+_j] != null ){
            if( maximizing_player ){
                if( board[i+_i][j+_j] == 1 )
                    aux_board[i+_i][j+_j] = 0;
                else
                    return aux_board;
            } else{
                if( board[i+_i][j+_j] == 0 )
                    aux_board[i+_i][j+_j] = 1;
                else
                    return aux_board;
            }
            _i += _i;
            _j += _j;
        }
        return board;
    } */

    private Byte[][] copy( Byte[][] board ){
        Byte[][] aux = new Byte[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) 
            aux[i] = board[i].clone();
        return aux;
    }

    public Boolean isFinished( Byte[][] board ){
        for (int i = 0; i < this.rows; i++) 
            for (int j = 0; j < this.cols; j++) 
                if( board[i][j] == null )
                    return false;
        return true;
                
    }

    public Integer winner( Byte[][] board ){
        Integer ceros = 0;
        for( int i=0; i<this.rows; i++ )
            for( int j=0; j<this.cols; j++ )
                if( board[i][j] == 0 )
                    ceros++;
        return ceros > this.total_tokens/2 ? 0 : 1;
    }

    public Boolean isTurn() {
        return this.maximizing_player;
    }

    public Boolean getTurn() {
        return this.maximizing_player;
    }

    public void setTurn(Boolean maximizing_player) {
        this.maximizing_player = maximizing_player;
    }

    public Othello maximizing_player(Boolean maximizing_player) {
        this.maximizing_player = maximizing_player;
        return this;
    }


    public Byte[][] getInitialBoard() {
        return this.initial_board;
    }

    public void setInitialBoard(Byte[][] board) {
        this.initial_board = board;
    }

    public Integer getRows() {
        return this.rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getCols() {
        return this.cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Othello initial_board(Byte[][] board) {
        this.initial_board = board;
        return this;
    }

    public Othello rows(Integer rows) {
        this.rows = rows;
        return this;
    }

    public Othello cols(Integer cols) {
        this.cols = cols;
        return this;
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