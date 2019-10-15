package app.moves;

import app.Constants;
import app.Cube2;
import java.util.List;

public class MoveLeft1 implements Movements {

    @Override
    public void move(Cube2 cube) {
        Byte k = Constants.FIRST_MOVE;

        List<List<List<Byte>>> _cube = copyCube(cube.getCube());

        for( int i = 0; i < 3; i++){
            _cube.get(4).get(k).set(i, cube.getCube().get(1).get(k).get(i));
            _cube.get(1).get(k).set(i, cube.getCube().get(2).get(k).get(i));
            _cube.get(2).get(k).set(i, cube.getCube().get(3).get(k).get(i));
            _cube.get(3).get(k).set(i, cube.getCube().get(4).get(k).get(i));
        }

        _cube.set(0, rotateMatrix( _cube.get(0), false ));

        cube.setCube(_cube);
    }
    
}