package app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class AgentProgramm{
    
    private final Byte[][][] goal_cube = {
        {
            {0,0,0},
            {0,null,0}, //Cara superior
            {0,0,0}
        },
        {
            {1,1,1},
            {1,null,1}, //Cara de al frente
            {1,1,1}
        },
        {
            {2,2,2},
            {2,null,2}, //Cara derecha
            {2,2,2}
        },
        {
            {3,3,3},
            {3,null,3}, //Cara trasera
            {3,3,3}
        },
        {
            {4,4,4},
            {4,null,4}, //Cara izquierda
            {4,4,4}
        },
        {
            {5,5,5},
            {5,null,5}, //Cara inferior
            {5,5,5}
        }
    };

    private Heuristics heuristic = new Heuristics();
    HashMap<List<List<List<Byte>>>,Boolean> map;

    protected void doAction(){
        Cube cube = new Cube( goal_cube );

        cube.printCube();
        cube.randomizeCube();
        cube.printCube();

        depthSearch(cube);
        //cube.printCube();
        System.out.println("Finished");
        
    }

    private Cube breathSearch( Cube cube ){
        Stack<Cube> queue = new Stack<>();
        HashMap<Cube, Boolean> map2 = new HashMap<>();
        
        List<List<List<Byte>>> list_goal_cube = Cube.arrayToList( goal_cube );
        map = new HashMap<>();
        Cube[] childs;
        
        queue.add(cube);
        map2.put( cube, true );

        while( !queue.isEmpty() ){
            cube = queue.pop();
            if( cube.finished( list_goal_cube ) ) break;
            childs = cube.getChilds();
            for (Cube child : childs) {
                if( map2.containsKey( child ) ) {
                    System.out.println("entro");
                    continue;
                }
                //child.setPriority( heuristic.heuristicA(cube, goal_cube) );
                queue.add( child );
                map2.put( child, true );
            }
        }
        cube.printCube();
        return cube;
    }

    private void depthSearch( Cube cube ){
        Queue<Cube> queue = new LinkedList<>();
        map = new HashMap<>();
        HashMap<Cube, Boolean> map2 = new HashMap<>();
        List<List<List<Byte>>> list_goal_cube = Cube.arrayToList( goal_cube );
        Cube[] childs;
        
        queue.add(cube);
        map2.put( cube, null );

        while( !queue.isEmpty() ){
            cube = queue.poll();
            if( cube.finished( list_goal_cube ) ) break;
            childs = cube.getChilds();
            for (Cube child : cube.getChilds()) {
                if( map2.containsKey( child ) ) continue;
                //child.setPriority( heuristic.heuristicA(cube, goal_cube) );
                queue.add( child );
                map2.put( child, null );
            }
        }
        cube.printCube();
    }

    private void aStarSearch( Cube cube ){
        PriorityQueue<Cube> queue =  new PriorityQueue<>();
        map = new HashMap<>();
        HashMap<Cube, Boolean> map2 = new HashMap<>();
        List<List<List<Byte>>> list_goal_cube = Cube.arrayToList( goal_cube );
        Cube[] childs;
        
        cube.setPriority( heuristic.heuristicA(cube, goal_cube) );
        queue.add(cube);
        map2.put( cube, null );

        while( !queue.isEmpty() ){
            cube = queue.poll();
            // System.out.println(cube);
            if( cube.finished( list_goal_cube ) ) break;
            childs = cube.getChilds();
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
    }

    /* protected void doAction(){
        Cube cube = new Cube( goal_cube );

        cube.printCube();
        cube.randomizeCube();
        cube.printCube();

        breathSearch(cube);
        cube.printCube(goal_cube);
        cube.printCube();
        System.out.println("Finished");
        
    }

    private Cube breathSearch( Cube cube ){
        Stack<Cube> queue = new Stack<>();
        HashMap<Cube, Boolean> map2 = new HashMap<>();
        map = new HashMap<>();
        List<List<List<Byte>>> list = cube.getListCube();
        Cube[] childs;
        
        queue.add(cube);
        map.put( list, null );

        while( !queue.isEmpty() ){
            cube = queue.pop();
            if( cube.finished( goal_cube ) ) break;
            childs = cube.getChilds();
            for (Cube child : childs) {
                list = child.getListCube();
                if( map.containsKey( list ) ) continue;
                //child.setPriority( heuristic.heuristicA(cube, goal_cube) );
                queue.add( child );
                map.put( list, true );
            }
        }
        cube.printCube();
        return cube;
    }

    private void depthSearch( Cube cube ){
        Queue<Cube> queue = new LinkedList<>();
        map = new HashMap<>();
        List<List<List<Byte>>> list = cube.getListCube();
        Cube[] childs;
        
        queue.add(cube);
        map.put( list, null );

        while( !queue.isEmpty() ){
            cube = queue.poll();
            if( cube.finished( goal_cube ) ) break;
            childs = cube.getChilds();
            for (Cube child : childs) {
                list = child.getListCube();
                if( map.containsKey( list ) ) continue;
                //child.setPriority( heuristic.heuristicA(cube, goal_cube) );
                queue.add( child );
                map.put( list, true );
            }
        }
    }

    private void aStarSearch( Cube cube ){
        PriorityQueue<Cube> queue =  new PriorityQueue<>();
        map = new HashMap<>();
        List<List<List<Byte>>> list = cube.getListCube();
        Cube[] childs;
        
        queue.add(cube);
        map.put( list, null );

        while( !queue.isEmpty() ){
            cube = queue.poll();
            if( cube.finished( goal_cube ) ) break;
            childs = cube.getChilds();
            for (Cube child : childs) {
                list = child.getListCube();
                if( map.containsKey( list ) ) continue;
                child.setPriority( heuristic.heuristicA(cube, goal_cube) );
                queue.add( child );
                map.put( list, true );
            }
        }
        cube.printCube();
    } */

}