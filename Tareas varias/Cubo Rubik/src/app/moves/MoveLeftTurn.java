package app.moves;

import app.Constants;
import app.Cube2;
import java.util.List;

public class MoveLeftTurn implements Movements {

    private Byte num_move;

    public MoveLeftTurn(Byte num_move){
        this.num_move = num_move;
    }

    @Override
    public void move(Cube2 cube) {
        Byte k = 0, l = 0;
        if( num_move == 0 ) {k = Constants.FIRST_MOVE; l = Constants.SECOND_MOVE;}
        else if( num_move == 1 ) {k = Constants.SECOND_MOVE; l = Constants.FIRST_MOVE;}
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        List<List<List<Byte>>> _cube = copyCube(cube.getCube());

        for( int i = 0; i < 3; i++){
            _cube.get(4).get(i).set(k, cube.getCube().get(0).get(k).get(i));
            _cube.get(0).get(k).set(i, cube.getCube().get(2).get(i).get(l));
            _cube.get(2).get(i).set(l, cube.getCube().get(5).get(l).get(i));
            _cube.get(5).get(l).set(i, cube.getCube().get(4).get(i).get(k));
        }

        if( k == Constants.FIRST_MOVE ) _cube.set(3, rotateMatrix( _cube.get(3), false ));
        if( k == Constants.SECOND_MOVE ) _cube.set(1, rotateMatrix( _cube.get(1), false ));

        cube.setCube(_cube);
    }
    
}