package app;

import java.util.LinkedList;
import app.moves.Movements;

public class Actions {

    private LinkedList<Movements> list;

    public Actions(LinkedList<Movements> list){
        merge(list);
    }

    public Actions(LinkedList<Movements> list_to_copy, Movements child) {
        merge(list_to_copy);
        addMove(child);
	}

	private void merge(LinkedList<Movements> list_to_copy){
        list = new LinkedList<>();
        for (Movements var : list_to_copy) {
            this.list.add(var);
        }
    }

    private void addMove(Movements move){
        this.list.add(move);
    }

    public LinkedList<Movements> getList(){
        return this.list;
    }
}