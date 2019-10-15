package app.moves;

import app.Constants;
import app.Cube2;
import java.util.List;

public class MoveUp1 implements Movements {

    @Override
    public void move(Cube2 cube) {
        Byte k = Constants.FIRST_MOVE;

        List<List<List<Byte>>> _cube = copyCube(cube.getCube());

        for( int i = 0; i < 3; i++){
            _cube.get(0).get(i).set(k, cube.getCube().get(1).get(i).get(k));
            _cube.get(1).get(i).set(k, cube.getCube().get(5).get(i).get(k));
            _cube.get(5).get(i).set(k, cube.getCube().get(3).get(2-i).get(k));
            _cube.get(3).get(2-i).set(k, cube.getCube().get(0).get(i).get(k));
        }

        _cube.set(4, rotateMatrix( _cube.get(4), true ));

        cube.setCube(_cube);
    }
    
}