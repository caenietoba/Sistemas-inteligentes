package app.moves;

import app.Constants;
import app.Cube2;
import java.util.List;

public class MoveDown implements Movements {

    private Byte num_move;

    public MoveDown(Byte num_move){
        this.num_move = num_move;
    }

    @Override
    public void move(Cube2 cube) {
        Byte k = 0;
        if( num_move == 0 ) k = Constants.FIRST_MOVE;
        else if( num_move == 1 ) k = Constants.SECOND_MOVE;
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        List<List<List<Byte>>> _cube = copyCube(cube.getCube());

        for( int i = 0; i < 3; i++){
            _cube.get(1).get(i).set(k, cube.getCube().get(0).get(i).get(k));
            _cube.get(5).get(i).set(k, cube.getCube().get(1).get(i).get(k));
            _cube.get(3).get(i).set(k, cube.getCube().get(5).get(2-i).get(k));
            _cube.get(0).get(2-i).set(k, cube.getCube().get(3).get(i).get(k));
        }

        if( k == Constants.FIRST_MOVE ) _cube.set(4, rotateMatrix( _cube.get(4), false ));
        if( k == Constants.SECOND_MOVE ) _cube.set(2, rotateMatrix( _cube.get(2), false ));

        cube.setCube(_cube);
    }
    
}