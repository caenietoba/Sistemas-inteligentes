package app;

import java.io.IOException;

class Environment2 {

    protected static final Byte[][][] goal_cube = { 
            { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }, //Cara superior
            { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, // Cara de al frente
            { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } }, // Cara derecha
            { { 3, 3, 3 }, { 3, 3, 3 }, { 3, 3, 3 } }, // Cara trasera
            { { 4, 4, 4 }, { 4, 4, 4 }, { 4, 4, 4 } }, // Cara izquierda
            { { 5, 5, 5 }, { 5, 5, 5 }, { 5, 5, 5 } } }; // Cara inferior

    protected void run() {
        AgentProgramm2 agent = new AgentProgramm2(goal_cube, 0);
        try {
            agent.solveCube();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}