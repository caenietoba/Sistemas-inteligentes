package app.representations.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import app.representations.Cube;

public class ArrayCube implements Cube<Byte[]> {

    private Byte[] cube;
    private Byte priority;
    private ArrayList<Byte> moves = new ArrayList<>();

    public final static Byte[] goal_cube = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49, 50, 51, 52, 53, };

    public ArrayCube(int num_moves, int pos_moves) {
        cube = ArrayCube.goal_cube.clone();
        randomizeCube(num_moves, pos_moves);
        moves = new ArrayList<>();
    }

    public ArrayCube(Byte[] cube, ArrayList<Byte> moves) {
        this.cube = cube;
        this.moves = moves;
    }

    public void setCube(Byte[] cube) {
        this.cube = cube;
    }

    public ArrayCube cube(Byte[] cube) {
        this.cube = cube;
        return this;
    }

    @Override
    public void addMove(Byte move) {
        this.moves.add(move);
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
        return Arrays.hashCode(cube);
    }

    @Override
    public int compareTo(Cube<Byte[]> o) {
        if (o.getPriority() > this.priority)
            return 1;
        else
            return 0;
    }

    @Override
    public void randomizeCube(int num_moves, int pos_moves) {
        Random rd = new Random();
        for (int i = 0; i < num_moves; i++) {
            if (pos_moves == 6)
                setCube(Moves.moves[rd.nextInt(6) + 6].move(this).getCube());
            else
                setCube(Moves.moves[rd.nextInt(12)].move(this).getCube());
        }
    }

    // Esto no debería imprimir pero no quiero cambiar el codigo ahorita
    @Override
    public Boolean isGoal() {
        if (Arrays.equals(cube, ArrayCube.goal_cube)) {
            System.out.println("The solution is: ");
            for (Byte move : moves) 
                System.out.print(Moves.moves_names[move] + ", ");
            return true;
        }
        return false;
    }

    @Override
    public void printCube() {
        System.out.println("...CUBE...");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print("|");
                for (int k = 0; k < 3; k++) {
                    System.out.print(cube[3 * i + 9 * j + k]);
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
        ArrayList<Byte> aux = new ArrayList<>();
        for (Byte byte1 : moves) {
            aux.add(byte1);
        }
        return new ArrayCube(cube.clone(), aux);
    }

    @Override
    public Byte getPriority() {
        return this.priority;
    }

    @Override
    public void setPriority(Byte priority) {
        this.priority = priority;
    }
    
}