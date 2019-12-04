package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import app.representations.array.Heuristics;
import app.representations.Cube;
import app.representations.array.ArrayCube;
import app.representations.array.Moves;

/**
 * En esta clase se expande el arbol guardando los movimientos realizados hasta
 * llegar a ese estado y implementando los movimientos en orden.
 * En teoría esta forma debería ocupar menos memoría que la otra usando más
 * procesamiento, la dificultad de esta implementación esta en como guardar los 
 * visitados, para esta se implemento guardando el objeto Cube pero se debe 
 * buscar una forma más eficiente de guardar el estado en memoría.
 */
class AgentProgramm2 {

    Cube<Byte[]> cube, aux_cube;
    HashSet<Cube<Byte[]>> memory;
    int num_moves, pos_moves, num_alg;
    ArrayList<Byte> moves, aux_moves;
    int used_memory_visited = 0, used_memory_queue = 0, used_process = 0;

    private class ExpandMoves implements Comparable<ExpandMoves>{
        ArrayList<Byte> moves;
        Byte priority;

        public ExpandMoves(ArrayList<Byte> moves, Byte priority){
            this.moves = moves;
            this.priority = priority;
        }

        @Override
        public int compareTo(ExpandMoves o) {
        if (o.priority > this.priority)
            return 1;
        else
            return 0;
        }
    }

    public AgentProgramm2(int num_moves, int pos_moves, int num_alg) {
        this.num_moves = num_moves;
        this.pos_moves = pos_moves;
        this.num_alg = num_alg;
    }

    protected void solveCube() {
        memory = new HashSet<>();
        cube = new ArrayCube(num_moves, pos_moves);
        used_memory_visited = 0;
        used_memory_queue = 0;
        used_process = 0;

        boolean solved = false;

        if(num_alg == 1)      solved = breathSearch();
        else if(num_alg == 2) solved = depthSearch();
        else if(num_alg == 3) solved = iterativeDeepeningSearch();
        else if(num_alg == 4) solved = aStar(0);
        else if(num_alg == 5) solved = aStar(1);
        else if(num_alg == 6) solved = aStar(2);
        
        if(!solved) System.out.print("not found");
        // Si encuentra esta imprimiendo en la función isGoal() de la clase Cube
        System.out.println("\nThe amount of process, memory of the visited nodes and memory of the expansion are " 
                            + used_process + ", " + used_memory_visited + ", " + used_memory_queue + " respectivily.");
    }

    private boolean breathSearch() {
        Queue<ArrayList<Byte>> queue = new LinkedList<>();

        queue.add(new ArrayList<Byte>());
        memory.add(cube);

        while (!queue.isEmpty()) {
            moves = queue.poll();
            if(ArrayCube.implementMoves(cube, moves).isGoal()) {
                for (Byte move : moves) System.out.print(Moves.moves_names[move] + ", ");
                return true;
            }
            for (int i = 0; i < pos_moves; i++) {
                aux_moves = copyMoves(moves);
                aux_moves.add((byte) i);

                aux_cube = ArrayCube.implementMoves(cube, aux_moves);

                if(memory.contains(aux_cube)) continue;

                queue.offer(aux_moves);
                memory.add(aux_cube);
            }

            lookUsedMemory(memory.size(), queue.size());
            lookUsedProcess();
        }
        return false;
    }

    private boolean depthSearch(){
        Stack<Cube<Byte[]>> queue = new Stack<>();

        queue.add(cube); // Agrega el nodo inicial
        memory.add(cube); // Agrega el nodo inicial

        while (!queue.isEmpty()) { // Revisa que se hayan expandido más nodos
            cube = queue.pop(); // Saca el siguiente nodoa  visitar
            if(cube.isGoal()) return true;
            for (int i = 0; i < pos_moves; i++) { // Revisa los hijos
                aux_cube = Moves.moves[i].move(cube); // Genera cada hijo
                aux_cube.addMove((byte)i); // Agrega el movimiento hecho al hijo

                if(memory.contains(aux_cube)) continue; // Mira si el hijo ya ha sido expandido

                queue.push(aux_cube); // Agrega el nodo 
                memory.add(aux_cube); // Agrega el nodo 

            }
            // Actualiza los indicadores de rendimiento
            lookUsedMemory(memory.size(), queue.size());
            lookUsedProcess();
        }
        return false;
    }

    private boolean iterativeDeepeningSearch(){
        for (int depth = 0; depth < num_moves; depth++) {
            if(dls(cube, depth, new ArrayList<>()))
                return true;
        }
        return false;
    }

    private boolean dls(Cube<Byte[]> node, int depth, ArrayList<Byte> moves){
        if(memory.contains(node)) return false;
        if(ArrayCube.implementMoves(cube, moves).isGoal()) {
            for (Byte move : moves) System.out.print(Moves.moves_names[move] + ", ");
            return true;
        }
        if(depth < 0) return false;
        for (int i = 0; i < pos_moves; i++) {
            aux_moves = copyMoves(moves);
            aux_moves.add((byte) i);
            if(dls(node, depth - 1, aux_moves)) 
                return true;
        }
        return false;
    }

    private boolean aStar(int heuristic){
        PriorityQueue<ExpandMoves> queue = new PriorityQueue<>();
        ExpandMoves em;

        queue.add(new ExpandMoves(new ArrayList<>(), (byte) 0));
        memory.add(cube);

        while (!queue.isEmpty()) {
            em = queue.poll();
            if(ArrayCube.implementMoves(cube, em.moves).isGoal()) {
                for (Byte move : em.moves) System.out.print(Moves.moves_names[move] + ", ");
                return true;
            }
            for (int i = 0; i < pos_moves; i++) {
                aux_moves = copyMoves(em.moves);
                aux_moves.add((byte) i);

                aux_cube = ArrayCube.implementMoves(cube, aux_moves);

                if(memory.contains(aux_cube)) continue;

                queue.offer(new ExpandMoves(aux_moves, findHeuristic(aux_cube, heuristic)));
                memory.add(aux_cube);

            }

            lookUsedMemory(memory.size(), queue.size());
            lookUsedProcess();
        }

        return false;
    }

    private Byte findHeuristic(Cube<Byte[]> cube, int heuristic){
        if(heuristic == 0)      return Heuristics.heuristic1(cube);
        else if(heuristic == 1) return Heuristics.heuristic2(cube);
        else if(heuristic == 2) return Heuristics.heuristic(cube);
        else return 0;
    }

    private ArrayList<Byte> copyMoves(ArrayList<Byte> moves){
        ArrayList<Byte> aux = new ArrayList<Byte>();
        for(Byte i: moves) aux.add(i);
        return aux;
    }

    private void lookUsedMemory(int memory_visited, int memory_queue){
        if(memory_visited > this.used_memory_visited) used_memory_visited = memory_visited;
        if(memory_queue > this.used_memory_queue) used_memory_queue = memory_queue;
    }

    private void lookUsedProcess(){
        used_process++;
    }
    
}