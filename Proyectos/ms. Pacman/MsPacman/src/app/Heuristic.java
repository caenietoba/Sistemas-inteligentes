package app;

import java.util.List;

import app.Board.BoardMarks;

public class Heuristic{

    public static double heuristicA(Board board){

        int x = board.getMsPacman().getPos_x();
        int y = board.getMsPacman().getPos_y();
        Character op = board.getLastMovement();
        List<List<Byte>> byte_board = board.getBoard();
        double priority = 0.0;
        int i_i = 0, i_f = 0, j_i = 0, j_f = 0, i = 0, j = 0;

        if(op == 'u'){
            i_i = 0; 
            i_f = x; 
            j_i = y-1; 
            j_f = y+1;
        }
        if(op == 'd'){
            i_i = x; 
            i_f = byte_board.size(); 
            j_i = y-i-x; 
            j_f = y+i-x;
        }
        if(op == 'l'){
            i_i = x-j; 
            i_f = x+j; 
            j_i = 0; 
            j_f = y;
        }
        if(op == 'r'){
            i_i = x-j-y; 
            i_f = x+j-y; 
            j_i = y; 
            j_f = byte_board.get(i).size();
        }

        for (i = i_i; i < i_f; i++) 
            for (j = j_i; j < j_f; j++) 
                if(byte_board.get(i).get(j) == BoardMarks.NOT_VISITED) priority = priority - 1.0;

        return priority;
    }

}