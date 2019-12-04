package app.representations.array;

import app.representations.Cube;
import app.representations.Movement;

public class Moves {

    /**
     * up1 - L'
     */
    public final static Movement<Cube<Byte[]>> Li = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = 3 * i;
            aux.getCube()[j] = cube.getCube()[9 + j];
            aux.getCube()[35 - j] = cube.getCube()[j];
            aux.getCube()[45 + j] = cube.getCube()[35 - j];
            aux.getCube()[9 + j] = cube.getCube()[45 + j];
        }
        rotateMatrix(aux, cube, "right", 4);
        return aux;
    };

    /**
     * up2 - R
     */
    public final static Movement<Cube<Byte[]>> R = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = 3 * i;
            aux.getCube()[2 + j] = cube.getCube()[11 + j];
            aux.getCube()[33 - j] = cube.getCube()[2 + j];
            aux.getCube()[47 + j] = cube.getCube()[33 - j];
            aux.getCube()[11 + j] = cube.getCube()[47 + j];
        }
        rotateMatrix(aux, cube, "right", 2);
        return aux;
    };

    /**
     * down1 - L
     */
    public final static Movement<Cube<Byte[]>> L = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = 3 * i;
            aux.getCube()[9 + j] = cube.getCube()[j];
            aux.getCube()[j] = cube.getCube()[35 - j];
            aux.getCube()[35 - j] = cube.getCube()[45 + j];
            aux.getCube()[45 + j] = cube.getCube()[9 + j];
        }
        rotateMatrix(aux, cube, "left", 4);
        return aux;
    };

    /**
     * down2 - R'
     */
    public final static Movement<Cube<Byte[]>> Ri = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = 3 * i;
            aux.getCube()[11 + j] = cube.getCube()[2 + j];
            aux.getCube()[2 + j] = cube.getCube()[33 - j];
            aux.getCube()[33 - j] = cube.getCube()[47 + j];
            aux.getCube()[47 + j] = cube.getCube()[11 + j];
        }
        rotateMatrix(aux, cube, "left", 2);
        return aux;
    };

    /**
     * left1 - U
     */
    public final static Movement<Cube<Byte[]>> U = cube -> {
        Cube<Byte[]> aux = cube.clone();
        for (int i = 0; i < 3; i++) {
            aux.getCube()[36 + i] = cube.getCube()[9 + i];
            aux.getCube()[9 + i] = cube.getCube()[18 + i];
            aux.getCube()[18 + i] = cube.getCube()[27 + i];
            aux.getCube()[27 + i] = cube.getCube()[36 + i];
        }
        rotateMatrix(aux, cube, "left", 0);
        return aux;
    };

    /**
     * left2 - D'
     */
    public final static Movement<Cube<Byte[]>> Di = cube -> {
        Cube<Byte[]> aux = cube.clone();
        for (int i = 0; i < 3; i++) {
            aux.getCube()[42 + i] = cube.getCube()[15 + i];
            aux.getCube()[15 + i] = cube.getCube()[24 + i];
            aux.getCube()[24 + i] = cube.getCube()[33 + i];
            aux.getCube()[33 + i] = cube.getCube()[42 + i];
        }
        rotateMatrix(aux, cube, "left", 5);
        return aux;
    };

    /**
     * right1 - U'
     */
    public final static Movement<Cube<Byte[]>> Ui = cube -> {
        Cube<Byte[]> aux = cube.clone();
        for (int i = 0; i < 3; i++) {
            aux.getCube()[9 + i] = cube.getCube()[36 + i];
            aux.getCube()[18 + i] = cube.getCube()[9 + i];
            aux.getCube()[27 + i] = cube.getCube()[18 + i];
            aux.getCube()[36 + i] = cube.getCube()[27 + i];
        }
        rotateMatrix(aux, cube, "right", 0);
        return aux;
    };

    /**
     * right2 - D
     */
    public final static Movement<Cube<Byte[]>> D = cube -> {
        Cube<Byte[]> aux = cube.clone();
        for (int i = 0; i < 3; i++) {
            aux.getCube()[15 + i] = cube.getCube()[42 + i];
            aux.getCube()[24 + i] = cube.getCube()[15 + i];
            aux.getCube()[33 + i] = cube.getCube()[24 + i];
            aux.getCube()[42 + i] = cube.getCube()[33 + i];
        }
        rotateMatrix(aux, cube, "right", 5);
        return aux;
    };

    /**
     * leftTurn1 - F'
     */
    public final static Movement<Cube<Byte[]>> Fi = cube -> { // Aca vamos
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = i * 3;
            aux.getCube()[42 - j] = cube.getCube()[i];
            aux.getCube()[i] = cube.getCube()[20 + j];
            aux.getCube()[20 + j] = cube.getCube()[53 - i];
            aux.getCube()[53 - i] = cube.getCube()[42 - j];
        }
        rotateMatrix(aux, cube, "left", 3);
        return aux;
    };

    /**
     * leftTurn2 - B
     */
    public final static Movement<Cube<Byte[]>> B = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = i * 3;
            aux.getCube()[44 - j] = cube.getCube()[6 + i];
            aux.getCube()[6 + i] = cube.getCube()[18 + j];
            aux.getCube()[18 + j] = cube.getCube()[47 - i];
            aux.getCube()[47 - i] = cube.getCube()[44 - j];
        }
        rotateMatrix(aux, cube, "left", 1);
        return aux;
    };

    /**
     * rightTurn1 - F
     */
    public final static Movement<Cube<Byte[]>> F = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = i * 3;
            aux.getCube()[i] = cube.getCube()[42 - j];
            aux.getCube()[20 + j] = cube.getCube()[i];
            aux.getCube()[53 - i] = cube.getCube()[20 + j];
            aux.getCube()[42 - j] = cube.getCube()[53 - i];
        }
        rotateMatrix(aux, cube, "right", 3);
        return aux;
    };

    /**
     * rightTurn2 - B'
     */
    public final static Movement<Cube<Byte[]>> Bi = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = i * 3;
            aux.getCube()[6 + i] = cube.getCube()[44 - j];
            aux.getCube()[18 + j] = cube.getCube()[6 + i];
            aux.getCube()[47 - i] = cube.getCube()[18 + j];
            aux.getCube()[44 - j] = cube.getCube()[47 - i];
        }
        rotateMatrix(aux, cube, "right", 1);
        return aux;
    };

    private final static void rotateMatrix(Cube<Byte[]> aux, Cube<Byte[]> cube, String direction, int face) {
        
        try {
            if (!direction.equals("left") && !direction.equals("right")) 
                throw new Exception("Direction must be \"left\" or \"right\"");
            if (face < 0 || face > 6) throw new Exception("Direction must be between 0 and 6");

            int index_face = (face*9);

            if(direction.equals("right")){
                for (int i = 0; i < 3; i++) aux.getCube()[i+index_face]         = cube.getCube()[(2-i)*3+index_face];
                for (int i = 0; i < 3; i++) aux.getCube()[(2-i)*3+index_face]   = cube.getCube()[index_face+8-i];
                for (int i = 0; i < 3; i++) aux.getCube()[index_face+8-i]       = cube.getCube()[3*(i+1)-1+index_face];
                for (int i = 0; i < 3; i++) aux.getCube()[3*(i+1)-1+index_face] = cube.getCube()[i+index_face];
            } else{
                for (int i = 0; i < 3; i++) aux.getCube()[(2-i)*3+index_face]   = cube.getCube()[i+index_face];
                for (int i = 0; i < 3; i++) aux.getCube()[index_face+8-i]       = cube.getCube()[(2-i)*3+index_face];
                for (int i = 0; i < 3; i++) aux.getCube()[3*(i+1)-1+index_face] = cube.getCube()[index_face+8-i];
                for (int i = 0; i < 3; i++) aux.getCube()[i+index_face]         = cube.getCube()[3*(i+1)-1+index_face];
            }
            aux.getCube()[9/2+index_face] = cube.getCube()[9/2+index_face];

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* @SuppressWarnings("unchecked")
    public static Movement<Cube<Byte[]>>[] moves = new Movement[]{
        up1, up2, right1, right2, rightTurn1, rightTurn2, down1, down2, left1, left2, leftTurn1, leftTurn2
    };

    public static String[] moves_names = new String[]{
        "up1", "up2", "right1", "right2", "rightTurn1", "rightTurn2", "down1", "down2", "left1", "left2", "leftTurn1", "leftTurn2"
    }; */

    @SuppressWarnings("unchecked")
    public static Movement<Cube<Byte[]>>[] moves = new Movement[]{
        Li, R, Ui, D, F, Bi, L, Ri, U, Di, Fi, B
    };

    public static String[] moves_names = new String[]{
        "L'", "R", "U'", "D", "F", "B'", "L", "R'", "U", "D'", "F'", "B"
    };

}