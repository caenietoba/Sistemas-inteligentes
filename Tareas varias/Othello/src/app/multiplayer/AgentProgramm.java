package app.multiplayer;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Class that will manage our agent logic
 */
public class AgentProgramm{

    // Will mantain the childs visited of the actual game play 
    // on the search and order them with the priority
    private PriorityQueue<Board> p_queue;
    // Mantain the othello game
    private Othello othello;
    // Obtain the heuristics of the othello game
    private Heuristics heuristics;
    // Variable that will be change when the 10 seconds pass
    private Boolean time_finished = false;

	/**
     * Constrcutor that init the othello
     * @param othello Initial Othello, should be a singleton
     */
    public AgentProgramm( Othello othello ) {
        this.othello = othello;
        heuristics = new Heuristics(this.othello);
    } 

    /**
     * Functions with a nice name that call the alphabeta algorithm
     * @param board Board that will be the root of the algorithm
     * @param player Tokens that is using our agent, whites = 0 and blacks = 1
     * @return Return a board with the move done by the agent
     */
	public Board play(Board board, Boolean player) {
        //System.out.println(board.getMoves( player ).size());
		return alphabeta(board.getMoves( player ));
    }

    /**
     * Calls the alphabeta algorithm over each child inthe ArrayList of boards, 
     * return in two cases, the first when all the childs are looked and it select 
     * the best and the second one when the 10 seconds pass
     * @param childs_board Childs of the board wich will be used for the alorithm
     * @return Return a board with the move done by the agent 
     */
    public Board alphabeta( ArrayList<Board> childs_board ){
        int depth = 3; // Max depth of the algorithm
        int alpha = -1000000000; 
        int betha = 1000000000;
        Boolean maximizing_player = true; 
        p_queue = new PriorityQueue<>(); // Init the queue
        for (Board board : childs_board) {
            if( time_finished ) break; // Manage of the time, in case that the 10 seconds pass it return
            board.setPriority((byte)(int)alphabeta(board, depth, alpha, betha, maximizing_player));
            //System.out.print(board.getPriority() + " ");
            p_queue.add(board);
        }
        return p_queue.peek();
    }

    /**
     * Alphabeta algorithm used to find the best move of the agent, 
     * is a recursive algorithm
     * @param board The board wich the algorithm will iterate
     * @param depth The max depth reached by the algorithm
     * @param alpha The alpha value
     * @param betha The betha value
     * @param maximizing_player The player that will maximize or minimize
     * @return Return the value based on the heuristic of the board passed
     */
    private Integer alphabeta( Board board, int depth, int alpha, int betha, Boolean maximizing_player ){
        int value; // Value to be returned
        ArrayList<Board> childs_board; // Childs of the actual board
        
        // If the depth has reduced to 0 or if the board is totally full then it returns
        if( depth == 0 || board.isComplete() ) return heuristic( board );
        // Depending on the maximizing player it will pass one or another to the next alphabeta
        if( maximizing_player ){ 
            value = -1000000000;
            childs_board = board.getMoves( maximizing_player ); // Get the childs of the board
            for (Board child : childs_board) {
                // Recursively call
                value = Math.max( value, alphabeta( child, depth - 1, alpha, betha, false ) );
                alpha = Math.max( alpha, value );
                if( alpha >= betha ) break;
                // Manage of the time, in case that the 10 seconds pass it return
                if(time_finished) return heuristic( board );
            }
            return value;
        } else {
            value = 1000000000;
            childs_board = board.getMoves( !maximizing_player ); // Get the childs of the board
            for (Board child : childs_board) {
                // Recursively call
                value = Math.min( value, alphabeta( child, depth - 1, alpha, betha, true ) );
                betha = Math.min( betha, value );
                if( alpha >= betha ) break;
                // Manage of the time, in case that the 10 seconds pass it return
                if(time_finished) return heuristic( board ); // Manejo del tiempo
                //(int)heuristics.dynamic_heuristic_evaluation_function( board.getBoard() );
            }
            return value;
        }
    }

    private int heuristic(Board board){
        //return heuristics.heuristicA( board.getBoard() );
        return (int)heuristics.dynamic_heuristic_evaluation_function( board.getBoard(), othello.getPlayer() );
    }

    /**
     * Getter of time_finsihed
     * @return Return time_finished
     */
    public Boolean getTime_finished() {
        return this.time_finished;
    }

    /**
     * Setter of time_finsihed
     * @param time_finished Boolean that will be asigned to time_finished
     */
    public void setTime_finished(Boolean time_finished) {
        this.time_finished = time_finished;
    }
}