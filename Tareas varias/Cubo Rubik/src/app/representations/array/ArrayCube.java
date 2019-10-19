package app.representations.array;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import app.representations.Cube;

public class ArrayCube implements Cube<Byte[]>{

    private Byte[] cube;
    private final static Byte[] goal_cube = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 
        2, 2, 2, 2, 2, 2, 2, 2, 2, 
        3, 3, 3, 3, 3, 3, 3, 3, 3, 
        4, 4, 4, 4, 4, 4, 4, 4, 4, 
        5, 5, 5, 5, 5, 5, 5, 5, 5,
    };

    public ArrayCube( int num_moves ){
        cube = ArrayCube.goal_cube.clone();
        randomizeCube(num_moves);
    }

    public ArrayCube(Byte[] cube) {
        this.cube = cube;
    }
    
    public void setCube(Byte[] cube) {
        this.cube = cube;
    }

    public ArrayCube cube(Byte[] cube) {
        this.cube = cube;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ArrayCube)) {
            return false;
        }
        ArrayCube arrayCube = (ArrayCube) o;
        return Arrays.equals(arrayCube.getCube(), cube);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cube);
    }

    @Override
    public String toString() {
        return "{" +
            " cube='" + getCube() + "'" +
            "}";
    }

    @Override
    public int compareTo(Cube<Byte[]> o) {
        return 0;
    }

    @Override
    public void randomizeCube(int num_moves) {
        for (int i = 0; i < num_moves; i++) {
            Random rd = new Random();
            setCube(Moves.moves[rd.nextInt(12)].move(this).getCube());
        }
    }

    @Override
    public Boolean isGoal() {
        return Arrays.equals(cube, ArrayCube.goal_cube);
    }

    @Override
    public void printCube() {
        System.out.println("...CUBE...");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++){
                System.out.print("|");
                for (int k = 0; k < 3; k++){
                    System.out.print(cube[3*i+6*j+k]);
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }

    }

    @Override
    public Byte[] getCube() {
        return this.cube;
    }

    @Override
    public Cube<Byte[]> clone() {
        return new ArrayCube( cube.clone() );
    }
    
}