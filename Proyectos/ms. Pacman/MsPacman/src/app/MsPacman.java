package app;

import java.util.Objects;

public class MsPacman {
    private int pos_x;
    private int pos_y;

    public MsPacman(int pos_x, int pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public int getPos_x() {
        return this.pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return this.pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MsPacman)) {
            return false;
        }
        MsPacman msPacman = (MsPacman) o;
        return pos_x == msPacman.pos_x && pos_y == msPacman.pos_y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos_x, pos_y);
    }

    @Override
    public String toString() {
        return "{" +
            " pos_x='" + getPos_x() + "'" +
            ", pos_y='" + getPos_y() + "'" +
            "}";
    }

}