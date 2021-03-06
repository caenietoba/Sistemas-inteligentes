package app;

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
 * En esta clase se expande el arbol guardando los objetos de tipo Cube
 */
class AgentProgramm {

    Cube<Byte[]> cube, aux_cube;
    HashSet<Cube<Byte[]>> memory;
    int num_moves, pos_moves, num_alg;
    int used_memory_visited = 0, used_memory_queue = 0, used_process = 0;

    public AgentProgramm(int num_moves, int pos_moves, int num_alg) {
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
        Queue<Cube<Byte[]>> queue = new LinkedList<>();

        queue.add(cube); // Agrega el nodo inicial
        memory.add(cube); // Agrega el nodo inicial

        while (!queue.isEmpty()) { // Revisa que se hayan expandido más nodos
            cube = queue.poll(); // Saca el siguiente nodoa  visitar
            if(cube.isGoal()) return true;
            for (int i = 0; i < pos_moves; i++) { // Revisa los hijos
                aux_cube = Moves.moves[i].move(cube); // Genera cada hijo
                aux_cube.addMove((byte)i); // Agrega el movimiento hecho al hijo

                if(memory.contains(aux_cube)) continue; // Mira si el hijo ya ha sido expandido

                queue.offer(aux_cube); // Agrega el nodo 
                memory.add(aux_cube); // Agrega el nodo 

            }
            // Actualiza los indicadores de rendimiento
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
        // Bucle que recorrera cada nivel del arbol
        for (int depth = 0; depth < num_moves; depth++) { 
            if(dls(cube, depth)) // función dfs recursiva
                return true;
        }
        return false;
    }

    private boolean dls(Cube<Byte[]> node, int depth){
        //if(memory.contains(node)) return false;
        lookUsedProcess(); // Actualiza los indicadores
        if(node.isGoal()) return true;
        // En caso de que se haya llegado al nivel límite
        if(depth < 0) return false; 
        // Revisa los hijos
        for (int i = 0; i < pos_moves; i++) {
            // Genera cada hijo
            aux_cube = Moves.moves[i].move(node);
            aux_cube.addMove((byte)i);
            if(dls(aux_cube, depth - 1)) 
                return true;
        }
        return false;
    }

    private boolean aStar(int heuristic){
        PriorityQueue<Cube<Byte[]>> queue = new PriorityQueue<>();

        queue.add(cube); // Agrega el nodo inicial
        memory.add(cube); // Agrega el nodo inicial

        while (!queue.isEmpty()) { // Revisa que se hayan expandido más nodos
            cube = queue.poll(); // Saca el siguiente nodoa  visitar
            if(cube.isGoal()) return true;
            for (int i = 0; i < pos_moves; i++) { // Revisa los hijos
                aux_cube = Moves.moves[i].move(cube); // Genera cada hijo
                aux_cube.addMove((byte)i); // Agrega el movimiento hecho al hijo

                // Mira si el hijo ya ha sido expandido
                if(memory.contains(aux_cube)) continue;
                // Encuentra la prioridad de dicho nodo con una de las heuristicas
                aux_cube.setPriority(findHeuristic(aux_cube, heuristic));

                queue.offer(aux_cube); // Agrega el nodo 
                memory.add(aux_cube); // Agrega el nodo 
            }
            // Actualiza los indicadores de rendimiento
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

    private void lookUsedMemory(int memory_visited, int memory_queue){
        if(memory_visited > this.used_memory_visited) used_memory_visited = memory_visited;
        if(memory_queue > this.used_memory_queue) used_memory_queue = memory_queue;
    }

    private void lookUsedProcess(){
        used_process++;
    }
    
}