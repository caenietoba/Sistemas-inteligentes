package app.moves;

import java.util.ArrayList;
import java.util.List;

import app.Cube2;

public interface Movements{
    public void move(Cube2 cube);

    default List<List<List<Byte>>> copyCube(List<List<List<Byte>>> cube){
        List<List<List<Byte>>> aux_cube = new ArrayList<>();

        for( int i = 0; i < cube.size(); i++ ){
            aux_cube.add(new ArrayList<>());
            for( int j = 0; j < cube.get(i).size(); j++ ){
                aux_cube.get(i).add(new ArrayList<>());
                for( int k = 0; k < cube.get(i).get(j).size(); k++ )
                    aux_cube.get(i).get(j).add( cube.get(i).get(j).get(k) );
            }
        }
        return aux_cube;
    }

    default List<List<Byte>> rotateMatrix( List<List<Byte>> matrix, Boolean dir ){
        List<List<Byte>> aux_matrix = new ArrayList<>();
        for( int i = 0; i < 3; i++ ){
            aux_matrix.add(new ArrayList<>());
            for( int j = 0; j < 3; j++ )
                aux_matrix.get(i).add(matrix.get(i).get(j));
        }
        if( dir ){
            for(int i=0; i<3; i++) {
                aux_matrix.get(0).set(i, matrix.get(2-i).get(0));
                aux_matrix.get(i).set(2, matrix.get(0).get(i));
                aux_matrix.get(2).set(2-i, matrix.get(i).get(2));
                aux_matrix.get(2-i).set(0, matrix.get(2).get(2-i));
            }
        } else{
            for(int i=0; i<3; i++) {
                aux_matrix.get(2-i).set(0, matrix.get(0).get(i));
                aux_matrix.get(0).set(i, matrix.get(i).get(2));
                aux_matrix.get(i).set(2, matrix.get(2).get(2-i));
                aux_matrix.get(2).set(2-i, matrix.get(2-i).get(0));
            }
        }
        return aux_matrix;
    }
}