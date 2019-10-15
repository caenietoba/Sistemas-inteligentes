package app;

import java.util.LinkedList;

import app.moves.Movements;

public class Actions2 extends Actions implements Comparable<Actions2> {

    private Byte priority = 0;

    public Actions2(LinkedList<Movements> list){
        super(list);
    }

    public Actions2(LinkedList<Movements> list_to_copy, Movements child) {
        super(list_to_copy, child);
    }

    @Override
    public int compareTo(Actions2 o) {
        if( this.priority > o.getPriority() ) return 1;
        else if( this.priority < o.getPriority() ) return -1;
        else return 0;
    }

    private Byte getPriority() {
        return this.priority;
    }

}