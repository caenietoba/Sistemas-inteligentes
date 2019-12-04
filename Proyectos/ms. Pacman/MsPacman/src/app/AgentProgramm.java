package app;

import java.util.PriorityQueue;

public class AgentProgramm {

    private Board board;

    public AgentProgramm(Byte[][] board) {
        this.board = new Board(board);
    }

    public void aStar(){

        PriorityQueue<Board> queue = new PriorityQueue<Board>();
        Board aux_board;

        while(!board.finished()){
            for (int i = 0; i < Moves.moves.length; i++) {
                aux_board = Moves.moves[i].move(board.clone());
                if(aux_board == null) continue;

                aux_board.setPriority(Heuristic.heuristicA(aux_board));

                queue.offer(aux_board);
            }

            board = queue.poll();
            board.printBoard();
            queue.clear();
        }

    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}