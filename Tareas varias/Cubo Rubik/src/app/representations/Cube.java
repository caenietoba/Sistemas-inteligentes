package app.representations;

public interface Cube<T> extends Comparable<Cube<T>>{
    void randomizeCube(int num_moves);

    Boolean isGoal();

    void printCube();

    T getCube();

    Cube<T> clone();
}