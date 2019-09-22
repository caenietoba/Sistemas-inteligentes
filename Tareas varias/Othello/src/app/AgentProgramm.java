package app;

import java.util.ArrayList;

import java.util.PriorityQueue;
import java.util.Random;

public class AgentProgramm{

    private class dataBoard implements Comparable<dataBoard>{
        private Byte[][] board;
        private Integer priority;

        private Random rd = new Random();

        public dataBoard( Byte[][] board, Integer priority ){
            this.board = board;
            this.priority = priority;
        }

        public Byte[][] getBoard(){
            return this.board;
        }

        @Override
        public int compareTo(dataBoard o) {
            if( o.priority > this.priority ) return 1;
            if( o.priority < this.priority ) return -1;
            return rd.nextInt( 2 ) == 0 ? -1 : 1;
        }

    }

    private PriorityQueue<dataBoard> p_queue = new PriorityQueue<>();
    Othello othello;

    public AgentProgramm(  Othello othello ) {
        this.othello = othello;
    }    

    /* public Integer fun(){

        othello = new Othello(this.rows, this.cols);
        p_queue.add( new dataBoard( othello.getInitialBoard(), 100 ) );
        Byte[][] board = othello.getInitialBoard();
        ArrayList<Byte[][]> childs_board;

        while( !othello.finished() ){
            board = p_queue.poll().getBoard();
            childs_board = othello.getChilds( board, true );
            board = alphabeta(othello.getChilds( board ), true);
        }
        return othello.winner( board );
    } */

    public Byte[][] alphabeta( ArrayList<Byte[][]> childs_board, Boolean maximizing_player ){
        int depth = 3;
        int alpha = -1000000000;
        int betha = 1000000000;
        p_queue = new PriorityQueue<>();
        for (Byte[][] board : childs_board) {
            //othello.printBoard(board);
            p_queue.add( new dataBoard( board, alphabeta(board, depth, alpha, betha, maximizing_player) ) );
            //System.out.println("Hola2");
        }
        othello.setMaximizingPlayer(!othello.getMaximizingPlayer());
        return p_queue.peek().getBoard();
    }

    private Integer alphabeta( Byte[][] board, int depth, int alpha, int betha, Boolean maximizing_player ){
        int value;
        ArrayList<Byte[][]> childs_board;
        
        if( depth == 0 || othello.isFinished( board ) ) return heuristicA( board );
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
            childs_board = othello.getChilds( board,maximizing_player );
            for (Byte[][] child : childs_board) {
                value = Math.min( value, alphabeta( child, depth - 1, alpha, betha, true ) );
                betha = Math.min( betha, value );
                if( alpha >= betha ) break;
            }
            return value;
        }
    }

    private Integer heuristicA( Byte[][] board ){
        Integer priority = 0;

        return priority;

    }

}