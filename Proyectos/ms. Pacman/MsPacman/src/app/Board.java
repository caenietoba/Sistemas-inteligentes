package app;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final Byte VISITED = '1';
    private final Byte NOT_VISITED = '0';
    private final Byte MS_PACMAN = '2';
    private final Byte WALL = '3';

    public static class Movements{
        public static final Byte LEFT = 0;
        public static final Byte RIGTH = 1;
        public static final Byte UP = 2;
        public static final Byte DOWN = 3;
    }

    private List<List<Byte>> board;
    private MsPacman ms_pacman;

    public Board( Byte[][] board, int pos_x, int pos_y ){
        this.board = arraToList(board);
        this.ms_pacman = new MsPacman(pos_x, pos_y);
    }

    private List<List<Byte>> arraToList(Byte[][] board){
        List<List<Byte>> aux_board = new ArrayList<>();
        List<Byte> aux;
        for (Byte[] list : board) {
            aux = new ArrayList<>();
            for (Byte element : list) {
                aux.add(element);
            }
            aux_board.add(aux);
        }
        return aux_board;
    }

    public List<List<Byte>> getBoard(){
        return this.board;
    }

    public void move( Byte move ){
        int x = ms_pacman.getPos_x();
        int y = ms_pacman.getPos_y();
        
        changeBoard(x, y, VISITED);

        if( move.equals(Movements.LEFT) ){
            x--;
        } else if( move.equals(Movements.RIGTH) ){
            x++;
        } else if( move.equals(Movements.UP) ){
            y--;
        } else if( move.equals(Movements.DOWN) ){
            y++;
        }
        
        changeBoard(x, y, MS_PACMAN);
    }

    private void changeBoard(int x, int y, Byte value){
        this.board.get(x).set(y, value);
    }
}