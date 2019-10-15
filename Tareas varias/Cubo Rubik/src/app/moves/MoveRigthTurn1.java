package app.moves;

import app.Constants;
import app.Cube2;
import java.util.List;

public class MoveRigthTurn1 implements Movements {

    @Override
    public void move(Cube2 cube) {
        Byte k = Constants.FIRST_MOVE, l = Constants.SECOND_MOVE;

        List<List<List<Byte>>> _cube = copyCube(cube.getCube());

        for( int i = 0; i < 3; i++){
            _cube.get(0).get(k).set(i, cube.getCube().get(4).get(i).get(k));
            _cube.get(2).get(i).set(l, cube.getCube().get(0).get(k).get(i));
            _cube.get(5).get(l).set(i, cube.getCube().get(2).get(i).get(l));
            _cube.get(4).get(i).set(k, cube.getCube().get(5).get(l).get(i));
        }

        _cube.set(3, rotateMatrix( _cube.get(3), true ));

        cube.setCube(_cube);
    }
    
}