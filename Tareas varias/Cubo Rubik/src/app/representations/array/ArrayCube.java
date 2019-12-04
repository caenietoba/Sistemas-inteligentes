package app.representations.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import app.representations.Cube;

public class ArrayCube implements Cube<Byte[]> {

    private Byte[] cube;
    private Byte priority;
    private ArrayList<Byte> moves = new ArrayList<>();

    // Arreglo que representa el estado final
    public final static Byte[] goal_cube = { 
         0,  1,  2,  3,  4,  5,  6,  7,  8, //up
         9, 10, 11, 12, 13, 14, 15, 16, 17, //front
        18, 19, 20, 21, 22, 23, 24, 25, 26, //right
        27, 28, 29, 30, 31, 32, 33, 34, 35, //back
        36, 37, 38, 39, 40, 41, 42, 43, 44, //left
        45, 46, 47, 48, 49, 50, 51, 52, 53  //down
    };

    // Arreglo que representa las letras de cada cara del rubik
   private final static char[] cube_letters = { 'W', 'G', 'R', 'B', 'O', 'Y'};

    public ArrayCube(int num_moves, int pos_moves) {
        cube = ArrayCube.goal_cube.clone();
        randomizeCube(num_moves, pos_moves);
        moves = new ArrayList<>();
    }

    public ArrayCube(Byte[] cube, ArrayList<Byte> moves) {
        this.cube = cube;
        this.moves = moves;
    }

    /**
     * Only used in case that you dont need to save the 54 byte array of the cube
     * @param moves
     */
    public ArrayCube(ArrayList<Byte> moves){
        this.cube = new Byte[0];
        this.moves = moves;
    }

    public void setCube(Byte[] cube) {
        this.cube = cube;
    }

    /**
     * Función utilizada para implementar una lista de movimientos a el arreglo dentro de un Cube
     */
    public static Cube<Byte[]> implementMoves(Cube<Byte[]> cube, ArrayList<Byte> moves){
        Cube<Byte[]> aux = cube.clone();
        for(Byte move: moves) aux = Moves.moves[move].move(aux);
        return aux;
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

    // Esto no debería imprimir 
    @Override
    public Boolean isGoal() {
        if (Arrays.equals(cube, ArrayCube.goal_cube)) {
            printSolution();
            return true;
        }
        return false;
    }

    @Override
    public void printSolution(){
        System.out.print("The solution is: ");
        for (Byte move : moves) System.out.print(Moves.moves_names[move] + ", ");
    }

    @Override
    public void printCube() {
        int pos, letter;
        System.out.println("...CUBE...");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print("|");
                for (int k = 0; k < 3; k++) {
                    pos = 3 * i + 9 * j + k;
                    letter = (int)Math.floor(cube[pos] / 9) % 6;
                    System.out.print(cube_letters[letter]);
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