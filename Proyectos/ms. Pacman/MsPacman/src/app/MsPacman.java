package app;

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

    public MsPacman clone(){
        return new MsPacman(pos_x, pos_y);
    }

}