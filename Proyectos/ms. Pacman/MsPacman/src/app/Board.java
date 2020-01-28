package app;

import java.util.ArrayList;
import java.util.List;

public class Board implements Comparable<Board>{

    public static class BoardMarks{
        public static final Byte VISITED = 1;
        public static final Byte NOT_VISITED = 0;
        public static final Byte MS_PACMAN = 2;
        public static final Byte WALL = 3;
    }

    private List<List<Byte>> board;
    private MsPacman ms_pacman;
    private int num_not_visited;
    private double priority = 0.0;
    private Character last_movement = ' ';

    public Board( Byte[][] board ){
        this.board = arrayToList(board);
        findData();
    }

    public Board(List<List<Byte>> board, MsPacman ms_pacman, int num_not_visited, Character last_movement){
        this.board = board;
        this.ms_pacman = ms_pacman;
        this.num_not_visited = num_not_visited;
        this.last_movement = last_movement;
    }

    public Board clone(){
        return new Board(copyBoard(), ms_pacman.clone(), num_not_visited, last_movement);
    }

    public void findData(){
        int pos_x = 0, pos_y = 0, num_not_visited = 0;
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if(board.get(i).get(j) == BoardMarks.NOT_VISITED) num_not_visited++;
                if(board.get(i).get(j) == BoardMarks.MS_PACMAN){
                    pos_x = i;
                    pos_y = j;
                }
            }
        }

        this.num_not_visited = num_not_visited;
        this.ms_pacman = new MsPacman(pos_x, pos_y);
        System.out.println(this.num_not_visited);
    }

    private List<List<Byte>> arrayToList(Byte[][] board){
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

    public List<List<Byte>> copyBoard(){
        List<List<Byte>> aux_board = new ArrayList<>();
        List<Byte> aux;
        for (List<Byte> list : board) {
            aux = new ArrayList<>();
            for (Byte element : list) {
                aux.add(element);
            }
            aux_board.add(aux);
        }
        return aux_board;
    }

    public void printBoard(){
        System.out.println();
        System.out.println("The movement was: " + last_movement);
        for (List<Byte> list : board) {
            System.out.print("|");
            for (Byte value : list) {
                System.out.print(value + " ");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    @Override
    public int compareTo(Board o) {
        if(this.getPriority() > o.getPriority())      return 1;
        else if(this.getPriority() < o.getPriority()) return -1;
        else return 0;
    }

    public List<List<Byte>> getBoard(){
        return this.board;
    }

    public MsPacman getMsPacman(){
        return this.ms_pacman;
    }

    public void setPriority(double priority){
        this.priority = priority;
    }

    public double getPriority(){
        return this.priority;
    }

    public boolean finished(){
        return num_not_visited == 0;
    }

    public Character getLastMovement(){
        return this.last_movement;
    }

    public void setLastMovement(Character last_movement){
        this.last_movement = last_movement;
    }

    public int getNumNotVisited(){
        return this.num_not_visited;
    }

    public void setNumNotVisited(int num_not_visited){
        this.num_not_visited = num_not_visited;
    }
}