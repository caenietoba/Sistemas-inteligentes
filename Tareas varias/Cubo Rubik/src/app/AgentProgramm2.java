package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import app.moves.Movements;

class AgentProgramm2 {

    private final int num_moves = 6;

    protected List<List<List<Byte>>> goal_cube;
    private Cube2 cube;
    private int method;

    private Movements[] childs;

    private Heuristics heuristic = new Heuristics();
    HashSet<Cube2> memory;

    public AgentProgramm2(Byte[][][] goal_cube, int method) {
        this.goal_cube = Cube.arrayToList(goal_cube);
        this.method = method;
        cube = new Cube2(goal_cube, 8, num_moves);
    }

    protected void solveCube() throws IOException {
        if( num_moves == 6 ) childs = cube.getChilds6();
        else childs = cube.getChilds12();

        memory = new HashSet<>();

        switch (this.method) {
            case 0:
                breathSearch();
                break;
            case 1:
                depthSearch();
                break;
            case 2:
                // aStarSearch();
                break;
        }

    }

    private void depthSearch() {
        Stack<Actions> queue = new Stack<>();
        Actions actions = null;
        Cube2 aux_cube;
        int i = 1;

        queue.add(new Actions(new LinkedList<>()));
        memory.add(cube);

        while (!queue.isEmpty()) {
            actions = queue.pop();
            aux_cube = new Cube2(this.cube);

            for (Movements child : childs) {
                performActions(actions, child, aux_cube);
                if (aux_cube.finished(goal_cube)) {
                    System.out.println("paso");
                    break;
                }
                if (i == 1)
                    aux_cube.printCube();

                if (memory.contains(aux_cube)) {
                    /* System.out.println("Paso"); */continue;
                }

                queue.add(new Actions(actions.getList(), child));
                memory.add(aux_cube);
                i++;
            }
        }
        cube.printCube();
    }

    private void breathSearch() throws IOException {
        Queue<Actions> queue = new LinkedList<>();
        HashSet<List<List<List<Byte>>>> memory2 = new HashSet<>();
        Actions actions = null;
        Cube2 aux_cube;
        Boolean finished = false;

        Random rd = new Random();
        
        queue.add(new Actions(new LinkedList<>()));
        //memory.add( cube );
        memory2.add( get3Matrix(cube) );

        while( !queue.isEmpty() && !finished ){
            actions = queue.remove();

            for (Movements child : childs) {
                aux_cube = new Cube2(this.cube);
                performActions(actions, child, aux_cube);
                /* aux_cube.printCube(); */

                if( aux_cube.finished( goal_cube ) ) {
                    finished = true;
                    actions = new Actions(actions.getList(), child);
                    break;
                }

                if( memory.contains(aux_cube)) continue;
                if( memory2.contains(get3Matrix(aux_cube))) continue;
                
                queue.add( new Actions(actions.getList(), child) );
                memory2.add( get3Matrix(aux_cube) );
                /* if(rd.nextInt(200) % 5 == 0)
                    memory.add( aux_cube ); */
            }
        }
        printSolution(actions);
    }
    

    /* private void aStarSearch(){
        PriorityQueue<Cube> queue =  new PriorityQueue<>();
        // map = new HashSet<>();
        HashSet<Cube> map2 = new HashSet<>();
        
        cube.setPriority( heuristic.heuristicA(cube, goal_cube) );
        queue.add(cube);
        map2.put( cube, null );

        while( !queue.isEmpty() ){
            cube = queue.poll();
            // System.out.println(cube);
            if( cube.finished( list_goal_cube ) ) break;
            for (Cube child : childs) {
                child.setPriority( heuristic.heuristicA(child, goal_cube) );
                if( map2.containsKey( child ) ) {
                    System.out.println("entro");
                    continue;
                }
                queue.add( child );
                map2.put( child, null );
            }
        }
        cube.printCube();
    }  */

    private void printSolution(Actions actions) {
        System.out.println("\nThe messy cube is:\n");
        Cube2.printCube(this.cube.getCube());

        System.out.println("\nThe goal cube is:\n");
        Cube2.printCube(this.goal_cube);

        System.out.println("\nThe actions to solve it are:\n");
        for (Movements move : actions.getList()) {
            System.out.println(move.getClass().getName());
        }
    }

    private void performActions(Actions actions, Movements move, Cube2 cube) {
        for (Movements var : actions.getList()) {
            var.move(cube);
        }
        move.move(cube);
    }

    private List<List<List<Byte>>> get3Matrix(Cube2 cube){
        List<List<List<Byte>>> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            list.add(cube.getCube().get(i));
        }

        return list;
    }

}