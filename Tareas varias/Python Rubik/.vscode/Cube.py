import random
from copy import deepcopy

goal_cube = [
    0,  1,  2,  3,  4,  5,  6,  7,  8, 
    9, 10, 11, 12, 13, 14, 15, 16, 17, 
    18, 19, 20, 21, 22, 23, 24, 25, 26, 
    27, 28, 29, 30, 31, 32, 33, 34, 35, 
    36, 37, 38, 39, 40, 41, 42, 43, 44, 
    45, 46, 47, 48, 49, 50, 51, 52, 53  
]

def Li(cube):
    aux = deepcopy(cube)

    for i in range(3):
        j = 3 * i
        aux.cube[j] = cube.cube[9 + j]
        aux.cube[35 - j] = cube.cube[j]
        aux.cube[45 + j] = cube.cube[35 - j]
        aux.cube[9 + j] = cube.cube[45 + j]

    rotateMatrix(aux, cube, "right", 4)

    return aux

def R(cube):
    aux = deepcopy(cube)
    for i in range(3):
        j = 3 * i
        aux.cube[2 + j] = cube.cube[11 + j]
        aux.cube[33 - j] = cube.cube[2 + j]
        aux.cube[47 + j] = cube.cube[33 - j]
        aux.cube[11 + j] = cube.cube[47 + j]

    rotateMatrix(aux, cube, "right", 2)
    return aux

def L(cube):
    aux = deepcopy(cube)
    for i in range(3):
        j = 3 * i
        aux.cube[9 + j] = cube.cube[j]
        aux.cube[j] = cube.cube[35 - j]
        aux.cube[35 - j] = cube.cube[45 + j]
        aux.cube[45 + j] = cube.cube[9 + j]

    rotateMatrix(aux, cube, "left", 4)
    return aux

def Ri(cube):
    aux = deepcopy(cube)
    for i in range(3):
        j = 3 * i
        aux.cube[11 + j] = cube.cube[2 + j]
        aux.cube[2 + j] = cube.cube[33 - j]
        aux.cube[33 - j] = cube.cube[47 + j]
        aux.cube[47 + j] = cube.cube[11 + j]

    rotateMatrix(aux, cube, "left", 2)
    return aux

def U(cube):
    aux = deepcopy(cube)
    for i in range(3):
        j = 3 * i
        aux.cube[36 + i] = cube.cube[9 + i]
        aux.cube[9 + i] = cube.cube[18 + i]
        aux.cube[18 + i] = cube.cube[27 + i]
        aux.cube[27 + i] = cube.cube[36 + i]

    rotateMatrix(aux, cube, "left", 0
    return aux

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

class cube:
    
    def __init__(self, cube, moves):
        self.cube = cube
        self.moves = moves

    def __init__(self, num_moves):
        self.cube = deepcopy(cube)
        self.randomizeCube(num_moves)
        self.moves = []

    def setCube(self, cube):
        self.cube = cube

    def addMove(self, move):
        self.moves.append(move)

    def randomizeCube(self, num_moves):
        for i in range(num_moves):
            Moves.moves[random.randint(12)].move(self)
