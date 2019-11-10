package app.multiplayer;

/**
 * Class that will contains the logic of the Othello
 */
public class Othello {

    private Boolean player; // Boolean that contains what player is our agent
    private Boolean turn; // Boolean that conatins with pakyer is actually playing
    private Board board; // The actual board used on the Othello

    /**
     * Constructor that inits the Othello with the size of the board and what 
     * player is our agent
     * @param rows Numbers of rows of the board
     * @param cols Numbers of rows of the board
     * @param player Tokens that is using our agent, whites = 0 and blacks = 1
     */
    public Othello(int rows, int cols, Boolean player){
        board = new Board(rows, cols);
        board.startBoard();
        this.player = player;
    }

    /**
     * Look who is the winner of the game comparing with has more tokens in the 
     * final board
     */
    public void winner(){
        int whites = 0, blacks = 0;
        for (Byte[] element : this.board.getBoard()) 
            for (Byte byte1 : element) 
                if( byte1 == 0 ) whites++;
                else blacks++;
        if( whites > blacks ) System.out.println("The winner are whites");
        else System.out.println("The winner are blacks");
    }

    /**
     * Getter for the player 
     * @return Return what player is our agent
     */
	public Boolean getPlayer() {
		return player;
	}

    /**
     * Getter for the Board
     * @return Return a Board object 
     */
	public Board getBoard() {
		return board;
    }

    /**
     * Setter for the turn
     * @param turn Turn assigned
     */
    public void setTurn(Boolean turn){
        this.turn = turn;
    }

    /**
     * Getter for the turn
     * @return Return a boolean representing the turn
     */
    public Boolean getTurn(){
        return turn;
    }
    
    /**
     * Setter for the Board
     * @param board Board that will be asigned
     */
    public void setBoard(Board board) {
        this.board = board;
	}

    /**
     * Fucntion taht looks if the game is finished. Call isComplete of the Board object
     * @return
     */
	public boolean finished() {
		return this.board.isComplete();
    }

    /**
     * Print the board. Call printBoard of the board object
     */
	public void printBoard() {
        this.board.printBoard();
    }

    /**
     * Add a new token in the board in the row/col position. Call addToken 
     * of the board object
     * @param row Row in wich the token will be added
     * @param col Col in wich the token will be added
     * @param player Boolean that contains what player is our agent
     */
	public void addToken(int row, int col, Boolean player) {
        this.board.addToken(row, col, player);
	}
    

}