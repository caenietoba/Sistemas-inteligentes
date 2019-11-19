package app;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import app.representations.array.Heuristics;
import app.representations.Cube;
import app.representations.array.ArrayCube;
import app.representations.array.Moves;

class AgentProgramm {

    Cube<Byte[]> cube, aux_cube;
    HashSet<Cube<Byte[]>> memory;
    int num_moves, pos_moves, num_alg;

    public AgentProgramm(int num_moves, int pos_moves, int num_alg) {
        this.num_moves = num_moves;
        this.pos_moves = pos_moves;
        this.num_alg = num_alg;
    }

    protected void solveCube() {
        memory = new HashSet<>();
        cube = new ArrayCube(num_moves, pos_moves);

        boolean solved = false;

        if(num_alg == 0)      solved = breathSearch();
        else if(num_alg == 1) solved = iterativeDeepeningSearch();
        else if(num_alg == 2) solved = aStar(0);
        else if(num_alg == 3) solved = aStar(1);
        
        if(!solved) System.out.print("not found");
    }

    private boolean breathSearch() {
        Queue<Cube<Byte[]>> queue = new LinkedList<>();

        queue.add(cube);
        memory.add(cube);

        while (!queue.isEmpty()) {
            cube = queue.poll();
            if(cube.isGoal()) return true;
            for (int i = 0; i < pos_moves; i++) {
                aux_cube = Moves.moves[i].move(cube);

                if(memory.contains(aux_cube)) continue;

                aux_cube.addMove((byte)i);
                queue.offer(aux_cube);
                memory.add(aux_cube);

            }
        }
        return false;
    }

    private boolean iterativeDeepeningSearch(){
        for (int depth = 0; depth < num_moves; depth++) {
            //memory = new HashSet<>();
            if(dls(cube, depth))
                return true;
        }
        return false;
    }

    private boolean dls(Cube<Byte[]> node, int depth){
        if(memory.contains(node)) return false;
        //memory.add(node);
        if(node.isGoal()) return true;
        if(depth < 0) return false;
        for (int i = 0; i < pos_moves; i++) {
            aux_cube = Moves.moves[i].move(node);
            aux_cube.addMove((byte)i);
            if(dls(aux_cube, depth - 1)) 
                return true;
        }
        return false;
    }

    private boolean aStar(int heuristic){
        PriorityQueue<Cube<Byte[]>> queue = new PriorityQueue<>();

        queue.add(cube);
        memory.add(cube);

        while (!queue.isEmpty()) {
            cube = queue.poll();
            if(cube.isGoal()) return true;
            for (int i = 0; i < pos_moves; i++) {
                aux_cube = Moves.moves[i].move(cube);

                if(memory.contains(aux_cube)) continue;
                
                aux_cube.setPriority(findHeuristic(aux_cube, heuristic));
                aux_cube.addMove((byte)i);

                queue.offer(aux_cube);
                memory.add(aux_cube);

            }
        }

        return false;
    }

    private Byte findHeuristic(Cube<Byte[]> cube, int heuristic){
        if(heuristic == 0)      return Heuristics.heuristic1(cube);
        else if(heuristic == 1) return Heuristics.heuristic2(cube);
        return 0;
    }
    
}