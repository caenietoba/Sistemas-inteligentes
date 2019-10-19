package app;

import java.util.List;

public class Heuristics {

    
    public Byte heuristicA( Cube2 cube, List<List<List<Byte>>> goal ){
        List<List<List<Byte>>> _cube = cube.getCube();
        Byte priority = 1;

        for( int i=0; i<6; i++)
            for( int j=0; j<3; j++)
                for( int k=0; k<3; k++)
                    if( _cube.get(i).get(j).get(k) != goal.get(i).get(j).get(k) ) priority++;
        return priority;
    }

}