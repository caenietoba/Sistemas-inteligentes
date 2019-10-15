package app.moves;

import app.Constants;
import app.Cube2;
import java.util.List;

public class MoveRigth implements Movements {

    private Byte num_move;

    public MoveRigth(Byte num_move){
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
            _cube.get(1).get(k).set(i, cube.getCube().get(4).get(k).get(i));
            _cube.get(2).get(k).set(i, cube.getCube().get(1).get(k).get(i));
            _cube.get(3).get(k).set(i, cube.getCube().get(2).get(k).get(i));
            _cube.get(4).get(k).set(i, cube.getCube().get(3).get(k).get(i));
        }

        if( k == Constants.FIRST_MOVE ) _cube.set(0, rotateMatrix( _cube.get(0), true ));
        if( k == Constants.SECOND_MOVE ) _cube.set(5, rotateMatrix( _cube.get(5), true ));

        cube.setCube(_cube);
    }
    
}