package app.multiplayer;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class that will manage the environment where our agent connects with the other
 */
public class Environment{

    private AgentProgramm ap; // The agent programm variable
    private Othello othello; // Mantain the othello game
    private Scanner sc;
    private int max_time; // Variable that contains the max time to do a move

    /**
     * Principal function, recover the data from the user and calls the 
     * agent functions
     */
    public void init(){

        int rows, cols; // Manage the rows and cols of the othello board
        Boolean player; // Contains what token will use the agent of the player
        Boolean next; // Contains if the enemy has put a valid entry

        // Initial data
        sc = new Scanner(System.in);
        System.out.print("Number of rows: ");               rows = sc.nextInt();
        System.out.print("Number of cols: ");               cols = sc.nextInt();
        System.out.print("What player am i?(0=W, 1=B): ");  player = sc.nextInt() == 0 ? true: false;
        System.out.print("Press any button to continue: "); sc.next();

        // Init othello, agent programm objects
        othello = new Othello(rows, cols, player); 
        ap = new AgentProgramm(othello);

        // principal loop that will finish when the othello board is full
        while(!othello.finished()){
            // Depending on wich tokens is the player it will have an order
            if(player){
                playPlayer(player);
                //This loop is done until the enemy put a valid data
                do {
                    next = playEnemy(rows, cols, player);
                } while (!next);
            } else{
                //This loop is done until the enemy put a valid data
                do {
                    next = playEnemy(rows, cols, player);
                } while (!next);
                playPlayer(player);
            }
        }
        othello.winner();
        sc.close();
    }

    /**
     * Function of how our agent will play
     * @param player Tokens that is using our agent, whites = 0 and blacks = 1
     */
    public void playPlayer(Boolean player){
        Timer timer = new Timer(); // Variable to manage the time of the player to play
        Board board;
        timer.schedule(new TimerTask(){
            /** 
             * TimerTask to manage the time, once it is trigger it will change the
             * variable time_finished in agent programm so the ap will return a new board
             */
            @Override
            public void run() {
                ap.setTime_finished(true);
            }
    
        }, max_time); // Has 9 seconds, not 10 because with 10 its already a lose
        othello.setTurn(player); // Set the othello player as actual player
        board = ap.play(othello.getBoard(), player); // Makes the play of the agent
        othello.setBoard(board.copy()); // Change the actual board for the best board

        othello.setTurn(!player); // Change the player to the enemy

        ap.setTime_finished(false); // Put again the time_finished var to false until next turn

        // Some prints 
        System.out.println(othello.getBoard().getLast_pos()[0] + " " + othello.getBoard().getLast_pos()[1]);
        othello.printBoard();  
    }

    /**
     * Function of the enemy turn
     * @param rows The rows of the board
     * @param cols The cols of the board
     * @param player Tokens that is using our agent, whites = 0 and blacks = 1
     * @return Return a boolean indicating if the enemy made a valid play
     */
    public Boolean playEnemy(int rows, int cols, Boolean player){

        // Collect the data of the enemy play (Row and col)
        System.out.print("Row of the enemy: ");
        int e_row = sc.nextInt();
        System.out.print("Col of the enemy: ");
        int e_col = sc.nextInt();
        // Validate the data
        if( invalidEntry(e_row, e_col, rows, cols) ) return false;

        // Add the token to the board
        othello.addToken(e_row, e_col, !player);
        othello.printBoard();
        return true;
    }

    /**
     * Validate the data digited by the enemy, if the enemy select a place
     * outside the board, our a place already taken or a place that has not
     * a near token already return false
     * @param e_row Enemy row selected
     * @param e_col Enemy col selected
     * @param rows Number of rows of the board
     * @param cols Number of cols of the board
     * @return Return a boolean indicating if the move is valid
     */
    private Boolean invalidEntry(int e_row, int e_col, int rows, int cols) {
        Byte[][] board = othello.getBoard().getBoard();       
        boolean near = false;
        
        // Looks if is inside the board
        if(e_row < 0 || e_row >= rows || e_col < 0 || e_col >= cols) return true;
        // Looks if is already taken
        if(board[e_row][e_col] != null) return true;

        // Looks if there is a near token already
        if(e_row > 0 && e_col > 0 && !near)           near = board[e_row-1][e_col-1] == null ? false: true;
        if(e_row > 0 && !near)                        near = board[e_row-1][e_col] == null ? false: true;
        if(e_row > 0 && e_col < cols-1 && !near)      near = board[e_row-1][e_col+1] == null ? false: true;
        if(e_row < rows-1 && e_col > 0 && !near)      near = board[e_row+1][e_col-1] == null ? false: true;
        if(e_row < rows-1 && !near)                   near = board[e_row+1][e_col] == null ? false: true;
        if(e_row < rows-1 && e_col < cols-1 && !near) near = board[e_row+1][e_col+1] == null ? false: true;
        if(e_col > 0 && !near)                        near = board[e_row][e_col-1] == null ? false: true;
        if(e_col < cols-1 && !near)                   near = board[e_row][e_col+1] == null ? false: true;
        
        if(!near) return true;
        else return false;
    }

}