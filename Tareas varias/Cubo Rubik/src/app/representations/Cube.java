package app.representations;

public interface Cube<T> extends Comparable<Cube<T>>{
    void randomizeCube(int num_moves, int pos_moves);

    Boolean isGoal();

    void printCube();

    T getCube();

    Byte getPriority();

    void setPriority(Byte priority);

    void addMove(Byte move);

    Cube<T> clone();

    boolean equals(Object cube);
}