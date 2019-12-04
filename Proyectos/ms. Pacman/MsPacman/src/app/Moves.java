package app;

import app.Board.BoardMarks;

public class Moves {

    private static final Movement<Board> left = board -> {
        return movePacman(board, -1, 0, 'l');
    };

    private static final Movement<Board> rigth = board -> {
        return movePacman(board, 1, 0, 'r');
    };

    private static final Movement<Board> up = board -> {
        return movePacman(board, 0, -1, 'u');
    };

    private static final Movement<Board> down = board -> {
        return movePacman(board, 0, 1, 'd');
    };

    private static final Board movePacman(Board board, int x_incr, int y_incr, Character last_movement){
        int x = board.getMsPacman().getPos_x();
        int y = board.getMsPacman().getPos_y();

        int new_x = x + x_incr;
        int new_y = y + y_incr;

        if(board.getBoard().get(new_x).get(new_y) == BoardMarks.WALL) return null;

        board.setLastMovement(last_movement);

        if(board.getBoard().get(new_x).get(new_y) == BoardMarks.NOT_VISITED) 
            board.setNumNotVisited(board.getNumNotVisited() - 1);

        board.getBoard().get(x).set(y, BoardMarks.VISITED);
        board.getBoard().get(new_x).set(new_y, BoardMarks.MS_PACMAN);

        board.getMsPacman().setPos_x(new_x);
        board.getMsPacman().setPos_y(new_y);

        return board;
    }

    @SuppressWarnings("unchecked")
    public static final Movement<Board>[] moves = new Movement[]{left, rigth, up, down};

}