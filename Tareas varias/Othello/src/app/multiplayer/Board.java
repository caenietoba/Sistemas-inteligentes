package app.multiplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Class that will manage the logic of the boards, implements the interface
 * Comparable so it can be used in the priority queue
 */
public class Board implements Comparable<Board>{

    private Byte[][] board; // Struct used to manage the NxM board
    private Byte priority = 0; // Priority asigned to this board based on the heuristic
    private Byte[] last_pos = {0,0}; // The position of the last move added to this board

    /**
     * Constructor that inits the Board with the nums of rows and cols that 
     * the Byte[][] will have
     * @param rows Numbers of rows of the matrix
     * @param cols Numbers of cols of the matrix
     */
    public Board(int rows, int cols){
        board = new Byte[rows][cols];
    }

    /**
     * Constructor to init the Board with a matrix, a priority and a last position
     * this constructior is used when is required to obtain a copy of the actual Board
     * @param board Matrix already constructed
     * @param priority Priority of the matrix
     * @param last_pos Last move done in the matrix
     */
    public Board(Byte[][] board, Byte priority, Byte[] last_pos){
        this.board = board;
        this.priority = priority;
        this.last_pos = last_pos;
    }

    /**
     * Init the Byte[][] object of the Board to the initial state
     * ----
     * -10-
     * -01-
     * ----
     */
	public void startBoard(){
        int middle_rows = this.board.length/2;
        int middle_cols = this.board[0].length/2;

        this.board[middle_rows - 1][middle_cols - 1] = 0;
        this.board[middle_rows - 1][middle_cols]     = 1;
        this.board[middle_rows][middle_cols - 1]     = 1;
        this.board[middle_rows][middle_cols]         = 0;
    }

    /**
     * Obtain the childs (moves) that can the agent do on the actual board
     * @param turn What player is actually moving
     * @return Returns a list of boards
     */
    public ArrayList<Board> getMoves(boolean turn){
        ArrayList<Board> childs = new ArrayList<>();
        Byte[][] matrix = board;
        int rows = this.board.length;
        int cols = this.board[0].length;
        HashSet<Integer> looked_pos = new HashSet<>();
        // Iterate over the matrix to find what moves can do
        for( int i=0; i<rows; i++ ){
            for( int j=0; j<cols; j++ ){
                /* 
                 * It consider a child like a space null but with a neighbor not null space 
                 * Exists 8 possible moves that can be done in a central position
                 * maked the respective restrictions in the limits of the matrix.
                 * Looks too if the space is already been looked for, so the programm
                 * dont repeat childs.
                 */
                if( matrix[i][j] == null ) continue;
                if(!looked_pos.contains((i-1)*rows + (j-1))){
                    if( i > 0 && j > 0 && matrix[i-1][j-1] == null )           childs.add( makeMove( this.copy(), i-1, j-1, turn ) );
                    looked_pos.add((i-1)*rows + (j-1));
                }
                if(!looked_pos.contains((i-1)*rows + (j+1))){
                    if( i > 0 && j < cols-1 && matrix[i-1][j+1] == null )      childs.add( makeMove( this.copy(), i-1, j+1, turn ) );
                    looked_pos.add((i-1)*rows + (j+1));
                }
                if(!looked_pos.contains((i+1)*rows + (j-1))){
                    if( i < rows-1 && j > 0 && matrix[i+1][j-1] == null )      childs.add( makeMove( this.copy(), i+1, j-1, turn ) );
                    looked_pos.add((i+1)*rows + (j-1));
                }
                if(!looked_pos.contains((i+1)*rows + (j+1))){
                    if( i < rows-1 && j < cols-1 && matrix[i+1][j+1] == null ) childs.add( makeMove( this.copy(), i+1, j+1, turn ) );
                    looked_pos.add((i+1)*rows + (j+1));
                }
                if(!looked_pos.contains((i-1)*rows + (j))){
                    if( i > 0 && matrix[i-1][j] == null )                      childs.add( makeMove( this.copy(), i-1, j, turn ) );
                    looked_pos.add((i-1)*rows + (j));
                }
                if(!looked_pos.contains((i+1)*rows + (j))){
                    if( i < rows-1 && matrix[i+1][j] == null )                 childs.add( makeMove( this.copy(), i+1, j, turn ) );
                    looked_pos.add((i+1)*rows + (j));
                }
                if(!looked_pos.contains((i)*rows + (j-1))){
                    if( j > 0 && matrix[i][j-1] == null )                      childs.add( makeMove( this.copy(), i, j-1, turn ) );
                    looked_pos.add((i)*rows + (j-1));
                }
                if(!looked_pos.contains((i)*rows + (j+1))){
                    if( j < cols-1 && matrix[i][j+1] == null )                 childs.add( makeMove( this.copy(), i, j+1, turn ) );
                    looked_pos.add((i)*rows + (j+1));     
                } 
            }
        }
        return childs;
    }

    /**
     * Put a token in a respective cell of the matrix, and realize the respective move.
     * Move means change the tokens int the 8 lines that are trapped between 2 tokens of
     * the same color
     * @param board Board that will be used to do the move, must be a copy so the original board will not be change
     * @param i Row in wich the token will be put
     * @param j Col in wich the token will be put
     * @param turn Player that ios actually playing, 0 for WHITES and 1 for BLACKS
     * @return Return the board with the move done
     */
    private Board makeMove( Board board, int i, int j, Boolean turn ){

        // Put the token in the matrix
        if( turn ) board.getBoard()[i][j] = 0;
        else board.getBoard()[i][j] = 1;

        board.setLast_pos((byte)i, (byte)j); // Set the last pos variable with i and j

        // Makes the 8 possible moves
        board = changeTokens(board, i, j, -1, -1, turn);
        board = changeTokens(board, i, j, -1, 0, turn);
        board = changeTokens(board, i, j, -1, 1, turn);
        board = changeTokens(board, i, j, 0, -1, turn);
        board = changeTokens(board, i, j, 0, 1, turn);
        board = changeTokens(board, i, j, 1, -1, turn);
        board = changeTokens(board, i, j, 1, 0, turn);
        board = changeTokens(board, i, j, 1, 1, turn);

        return board;
    }

    /**
     * Middle Function that call changeTokens with all the parameters needed
     * @param board Board to be used to do the changes
     * @param i Row that will be changed
     * @param j Col that will be changed
     * @param _i How the row will increase or deacrease (0 or 1)
     * @param _j How the col will increase or deacrease (0 or 1)
     * @param turn Player that ios actually playing, 0 for WHITES and 1 for BLACKS
     * @return Return the board with the move done
     */
    private Board changeTokens( Board board, int i, int j, int _i, int _j, Boolean turn ){
        return changeTokens(board, null, i+_i, j+_j, _i, _j, false, false, turn);
    }

    /**
     * Function that changes the tokens depending on the move done for the player.
     * 
     * Puts all the tokens in the line of the same color in case that there are between
     * two tokens of the same color.
     * @param original The original board on wich the move is realized, in case changes
     * are not done this is the board returned
     * @param board A copy of the original, in case some changes are done this will be
     * returned
     * @param i Row that will be changed
     * @param j Col that will be changed
     * @param _i How the row will increase or deacrease (0 or 1)
     * @param _j How the col will increase or deacrease (0 or 1)
     * @param created Boolean that will contains if board copy is already created
     * @param changed Boolean that co ntains if the board was changed or not, 
     * in case it has nothing to be changed it will mantain in false
     * @param turn Player that ios actually playing, 0 for WHITES and 1 for BLACKS
     * @return Return the board with the move done
     */
    private Board changeTokens(Board original, Board board, int i, int j, int _i, int _j, Boolean created, Boolean changed, Boolean turn){
        int rows = this.board.length;
        int cols = this.board[0].length;
        // In case the indixes go out the matrix means that changes are not done into the board
        if( i < 0 || j < 0 || i >= rows || j >= cols || original.getBoard()[i][j] == null )
            return original;
        // If the board has not been copied it return a copy
        if( !created ) return changeTokens(original, original.copy(), i, j, _i, _j, true, changed, turn);
        // In case that it return to a token of the initial color it looks if a change has been done
        if( turn && original.getBoard()[i][j] == 0 || !turn && original.getBoard()[i][j] == 1 )
            if( changed ) return board;
            else return original;

        // Change the token
        if( turn ) board.getBoard()[i][j] = 0;
        else board.getBoard()[i][j] = 1;

        // Recursive call
        return changeTokens(original, board, i+_i, j+_j, _i, _j, created, true, turn);
    }

    /**
     * Makes a copy of the matrix board
     * @return Return new matrix Byte[][] with the data of board
     */
    private Byte[][] copyBoard(){
        Byte[][] board = new Byte[this.board.length][this.board[0].length];
        for (int i=0; i<this.board.length; i++) {
            board[i] = this.board[i].clone();
        }
        return board;
    }

    /**
     * Return a copy of this object Board
     * @return Return a copy of this object Board
     * @return
     */
    public Board copy(){
        return new Board(copyBoard(), priority, last_pos.clone());
    }

    /**
     * Clone last_pos array, in case its initiate it
     * @return Return a new array of Byte of two positions
     */
    /* private Byte[] cloneLastPos(){
        if(last_pos == null){
            System.out.println("Is null");
            Byte[] arr = {0,0};
            return arr;
        }
        return last_pos.clone();
    } */

    /**
     * Look if the board is complete, complete means that all the matrix is fill
     * @return True in case that is complete, false in other case
     */
    public Boolean isComplete(){
        for (Byte[] bytes : board) {
            for (Byte _byte : bytes) {
                if(_byte == null) return false;
            }
        }
        return true;
    }

    /**
     * Print the board witha formatted form
     * an example will be for a 5x5 board
     * ---------------
     *   0 1 2 3 4
     * 0 - - - - -
     * 1 - - - - -
     * 2 - - - - -
     * 3 - - - - -
     * 4 - - - - -
     * ---------------
     */
    public void printBoard() { // Agregar indices del lado
        int rows = this.board.length;
        int cols = this.board[0].length;
        System.out.println();
        for (int i = 0; i < cols*2; i++) System.out.print("-");
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < cols; i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < cols; j++) 
                System.out.print((board[i][j] == null ? "'" : board[i][j]) + " ");
            System.out.println();
        }
        for (int i = 0; i < cols*2; i++) System.out.print("-");
        System.out.println();
    }
    
    /**
     * Add a token to the board. This function is used when the enemy 
     * player makes a move
     * @param row Row for the move
     * @param col Col for the move
     * @param player Player that ios actually playing, 0 for WHITES and 1 for BLACKS
     */
	public void addToken(int row, int col, Boolean player) {
        Board board = makeMove(this, row, col, player); // Makes the moves
        // Change the values of this objects to the values of the child object obtained
        this.board = board.getBoard();
        this.priority = board.getPriority();
        this.last_pos = board.getLast_pos();
	}

    /**
     * Getter for the matrix
     * @return Return a matrix Byte[][]
     */
    public Byte[][] getBoard() {
        return this.board;
    }

    /**
     * Setter for the matrix variable
     * @param board matrix Byte[][]
     */
    public void setBoard(Byte[][] board) {
        this.board = board;
    }

    /**
     * Getter priority
     * @return An integer representing the priority of the board
     */
    public Byte getPriority() {
        return this.priority;
    }

    /**
     * Setter of the priority
     * @param priority Priority to be assigned
     */
    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    /**
     * Getter of the last position
     * @return Return the position of the last move done on this board
     */
    public Byte[] getLast_pos() {
        return this.last_pos;
    }

    /**
     * Setter if the last_position variable
     * @param row Row of the last move done
     * @param col Col of the last move done
     */
    public void setLast_pos(Byte row, Byte col) {
        this.last_pos[0] = row;
        this.last_pos[1] = col;
    }

    /**
     * Equals for the Board object, it will only compare the Byte[][] matrix
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Board)) {
            return false;
        }
        Board board = (Board) o;
        return Arrays.deepEquals(board.board, this.board);
    }

    /**
     * Does a deepHashCode of the Byte[][]matrix, doesnt have into account 
     * the rest of params
     */
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    /**
     * Comparable for the priority queue
     */
    @Override
    public int compareTo(Board o) {
        if(o.priority > this.priority) return 1;
        else if(o.priority < this.priority) return -1;
        else return (int) (Math.random() * 1);
    }

}