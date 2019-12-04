package app;

import java.util.Scanner;

public class Environment{

    AgentProgramm ap;

    public Environment(){
        Scanner sc = new Scanner(System.in);

        int num_moves, pos_moves, num_alg;
        
        System.out.print("How many movements to be randomized: ");
        num_moves = sc.nextInt();
        System.out.print("How many possible moves: ");
        pos_moves = sc.nextInt();
        System.out.println("What algorithm want to use: ");
        System.out.println("1. BFS.");
        System.out.println("2. DFS.");
        System.out.println("3. IDDFS.");
        System.out.println("4. A* 1.");
        System.out.println("5. A* 2.");
        System.out.println("6. A* 3.");
        System.out.print("Select one of them: ");
        num_alg = sc.nextInt();

        if(num_alg < 1 && num_alg > 6)
            System.out.println("You must digit a number in the range 1...6");
        else
            ap = new AgentProgramm(num_moves, pos_moves, num_alg);

        sc.close();
    }

    public void run() {
        int used_memory_visited = 0, used_memory_queue = 0, used_process = 0, passed = 0;
        for(int i=0; i<10; i++){
            try{
                ap.solveCube();
                used_memory_queue += ap.used_memory_queue;
                used_memory_visited += ap.used_memory_visited;
                used_process += ap.used_process;
                passed++;
            } catch(Throwable e){

            }
        }
        System.out.println("\nThe media of process, memory of the visited nodes and memory of the expansion in 10 runs are " 
                            + used_process/passed + ", " + used_memory_visited/passed + ", " + used_memory_queue/passed + " respectivily.");
    }

}