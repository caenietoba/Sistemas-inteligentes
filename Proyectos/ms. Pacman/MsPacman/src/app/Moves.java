package app;

import app.Board.BoardMarks;

public class Moves {

    private static final Movement<Board> left = board -> {
        return movePacman(board, 0, -1, 'l', board.getLastMovement());
    };

    private static final Movement<Board> rigth = board -> {
        return movePacman(board, 0, 1, 'r', board.getLastMovement());
    };

    private static final Movement<Board> up = board -> {
        return movePacman(board, -1, 0, 'u', board.getLastMovement());
    };

    private static final Movement<Board> down = board -> {
        return movePacman(board, 1, 0, 'd', board.getLastMovement());
    };

    private static final Board movePacman(Board board, int x_incr, int y_incr, Character movement, Character last_movement){
        int x = board.getMsPacman().getPos_x();
        int y = board.getMsPacman().getPos_y();

        int new_x = x + x_incr;
        int new_y = y + y_incr;

        if(board.getBoard().get(new_x).get(new_y) == BoardMarks.WALL) return null;
        if(last_movement == 'u' && movement == 'd') return null;
        if(last_movement == 'd' && movement == 'u') return null;
        if(last_movement == 'r' && movement == 'l') return null;
        if(last_movement == 'l' && movement == 'r') return null;
        
        board.setLastMovement(movement);

        if(board.getBoard().get(new_x).get(new_y) == BoardMarks.NOT_VISITED) 
            board.setNumNotVisited(board.getNumNotVisited() - 1);

        board.getBoard().get(x).set(y, BoardMarks.VISITED);
        board.getBoard().get(new_x).set(new_y, BoardMarks.MS_PACMAN);

        board.getMsPacman().setPos_x(new_x);
        board.getMsPacman().setPos_y(new_y);

        if(movement == 'u' || movement == 'd')
            if(!isWall(board, new_x, new_y -1 ) || !isWall(board, new_x, new_y + 1))
                return board;
        if(movement == 'l' || movement == 'r')
            if(!isWall(board, new_x - 1 , new_y) || !isWall(board, new_x + 1, new_y))
                return board;

        return movePacman(board, x_incr, y_incr, movement, last_movement);
    }

    private static final Boolean isWall(Board board, int x, int y){
        if(board.getBoard().get(x).get(y) == BoardMarks.WALL)
            return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    public static final Movement<Board>[] moves = new Movement[]{left, rigth, up, down};

}


/* package app;

import app.Board.BoardMarks;

public class Moves {

    private static final Movement<Board> left = board -> {
        return movePacman(board, 0, -1, 'l');
    };

    private static final Movement<Board> rigth = board -> {
        return movePacman(board, 0, 1, 'r');
    };

    private static final Movement<Board> up = board -> {
        return movePacman(board, -1, 0, 'u');
    };

    private static final Movement<Board> down = board -> {
        return movePacman(board, 1, 0, 'd');
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

        if(last_movement == 'u' || last_movement == 'd')
            if(!isWall(board, new_x, new_y -1 ) || !isWall(board, new_x, new_y + 1))
                return board;
        if(last_movement == 'l' || last_movement == 'r')
            if(!isWall(board, new_x - 1 , new_y) || !isWall(board, new_x + 1, new_y))
                return board;

        return movePacman(board, x_incr, y_incr, last_movement);
    }

    private static final Boolean isWall(Board board, int x, int y){
        if(board.getBoard().get(x).get(y) == BoardMarks.WALL)
            return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    public static final Movement<Board>[] moves = new Movement[]{left, rigth, up, down};

} */