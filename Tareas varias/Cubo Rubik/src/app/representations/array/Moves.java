package app.representations.array;

import app.representations.Cube;
import app.representations.Movement;

public class Moves {

    public final static Movement<Cube<Byte[]>> up1 = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = 3 * i;
            aux.getCube()[j] = cube.getCube()[9 + j];
            aux.getCube()[35 - j] = cube.getCube()[j];
            aux.getCube()[45 + j] = cube.getCube()[35 - j];
            aux.getCube()[9 + j] = cube.getCube()[45 + j];
        }
        rotateMatrix(aux, cube, "rigth", 4);
        return aux;
    };

    public final static Movement<Cube<Byte[]>> up2 = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = 3 * i;
            aux.getCube()[2 + j] = cube.getCube()[11 + j];
            aux.getCube()[33 - j] = cube.getCube()[2 + j];
            aux.getCube()[47 + j] = cube.getCube()[33 - j];
            aux.getCube()[11 + j] = cube.getCube()[47 + j];
        }
        rotateMatrix(aux, cube, "rigth", 2);
        return aux;
    };

    public final static Movement<Cube<Byte[]>> down1 = cube -> {
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

    public final static Movement<Cube<Byte[]>> down2 = cube -> {
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

    public final static Movement<Cube<Byte[]>> left1 = cube -> {
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

    public final static Movement<Cube<Byte[]>> left2 = cube -> {
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

    public final static Movement<Cube<Byte[]>> rigth1 = cube -> {
        Cube<Byte[]> aux = cube.clone();
        for (int i = 0; i < 3; i++) {
            aux.getCube()[9 + i] = cube.getCube()[36 + i];
            aux.getCube()[18 + i] = cube.getCube()[9 + i];
            aux.getCube()[27 + i] = cube.getCube()[18 + i];
            aux.getCube()[36 + i] = cube.getCube()[27 + i];
        }
        rotateMatrix(aux, cube, "rigth", 0);
        return aux;
    };

    public final static Movement<Cube<Byte[]>> rigth2 = cube -> {
        Cube<Byte[]> aux = cube.clone();
        for (int i = 0; i < 3; i++) {
            aux.getCube()[15 + i] = cube.getCube()[42 + i];
            aux.getCube()[24 + i] = cube.getCube()[15 + i];
            aux.getCube()[33 + i] = cube.getCube()[24 + i];
            aux.getCube()[42 + i] = cube.getCube()[33 + i];
        }
        rotateMatrix(aux, cube, "rigth", 5);
        return aux;
    };

    public final static Movement<Cube<Byte[]>> leftTurn1 = cube -> { // Aca vamos
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

    public final static Movement<Cube<Byte[]>> leftTurn2 = cube -> {
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

    public final static Movement<Cube<Byte[]>> rigthTurn1 = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = i * 3;
            aux.getCube()[i] = cube.getCube()[42 - j];
            aux.getCube()[20 + j] = cube.getCube()[i];
            aux.getCube()[53 - i] = cube.getCube()[20 + j];
            aux.getCube()[42 - j] = cube.getCube()[53 - i];
        }
        rotateMatrix(aux, cube, "rigth", 3);
        return aux;
    };

    public final static Movement<Cube<Byte[]>> rigthTurn2 = cube -> {
        Cube<Byte[]> aux = cube.clone();
        int j;
        for (int i = 0; i < 3; i++) {
            j = i * 3;
            aux.getCube()[6 + i] = cube.getCube()[44 - j];
            aux.getCube()[18 + j] = cube.getCube()[6 + i];
            aux.getCube()[47 - i] = cube.getCube()[18 + j];
            aux.getCube()[44 - j] = cube.getCube()[47 - i];
        }
        rotateMatrix(aux, cube, "rigth", 1);
        return aux;
    };

    private final static void rotateMatrix(Cube<Byte[]> aux, Cube<Byte[]> cube, String direction, int face) {
        
        /* try {
            if (direction.equals("left") && direction.equals("rigth")) 
                throw new Exception("Direction must be \"left\" or \"rigth\"");
            if (face < 0 || face > 6) throw new Exception("Direction must be between 0 and 6");

            int index_face = (face*9);

            if(direction.equals("rigth")){
                for (int i = 0; i < 3; i++) aux.getCube()[i+index_face]       = cube.getCube()[(2-i)*3+index_face];
                for (int i = 0; i < 3; i++) aux.getCube()[(2-i)*3+index_face] = cube.getCube()[index_face+8-i];
                for (int i = 0; i < 3; i++) aux.getCube()[index_face+8-i]     = cube.getCube()[3*i+index_face];
                for (int i = 0; i < 3; i++) aux.getCube()[3*i+index_face]     = cube.getCube()[i+index_face];
            } else{
                for (int i = 0; i < 3; i++) aux.getCube()[(2-i)*3+index_face] = cube.getCube()[i+index_face];
                for (int i = 0; i < 3; i++) aux.getCube()[index_face+8-i]     = cube.getCube()[(2-i)*3+index_face];
                for (int i = 0; i < 3; i++) aux.getCube()[3*i+index_face]     = cube.getCube()[index_face+8-i];
                for (int i = 0; i < 3; i++) aux.getCube()[i+index_face]       = cube.getCube()[3*i+index_face];
            }

        } catch (Exception e) {
            e.printStackTrace();
        } */
    }

    @SuppressWarnings("unchecked")
    public static Movement<Cube<Byte[]>>[] moves = new Movement[]{
        up1, up2, rigth1, rigth2, rigthTurn1, rigthTurn2, down1, down2, left1, left2, leftTurn1, leftTurn2
    };

    public static String[] moves_names = new String[]{
        "up1", "up2", "rigth1", "rigth2", "rigthTurn1", "rigthTurn2", "down1", "down2", "left1", "left2", "leftTurn1", "leftTurn2"
    };

}