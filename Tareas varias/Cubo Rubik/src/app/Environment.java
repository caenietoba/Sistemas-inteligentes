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
        System.out.println("2. IDDFS.");
        System.out.println("3. A* 1.");
        System.out.println("4. A* 2.");
        System.out.print("Select one of them: ");
        num_alg = sc.nextInt();

        ap = new AgentProgramm(num_moves, pos_moves, num_alg);

        sc.close();
    }

    public void run() {
        ap.solveCube();
    }

}