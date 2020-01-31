package app;

import java.util.List;

import app.Board.BoardMarks;

public class Heuristic{

    public static double heuristicA(Board board){

        int x = board.getMsPacman().getPos_x();
        int y = board.getMsPacman().getPos_y();
        Character op = board.getLastMovement();
        /* System.out.println(x);
        System.out.println(y);
        System.out.println(op); */
        List<List<Byte>> byte_board = board.getBoard();
        double priority = 0.0;
        int i_i = 0, i_f = 0, j_i = 0, j_f = 0, i = 0, j = 0;

        if(op == 'u'){
            i_i = 0; 
            i_f = x; 
            j_i = 0;
            j_f = byte_board.get(i).size(); 
            /* j_i = y-1; 
            j_f = y+1; */
        }
        if(op == 'd'){
            i_i = x; 
            i_f = byte_board.size();
            j_i = 0;
            j_f = byte_board.get(i).size();  
            /* j_i = y-i-x; 
            j_f = y+i-x; */
        }
        if(op == 'l'){
            i_i = 0;
            i_f = byte_board.size(); 
            /* i_i = x-j; 
            i_f = x+j;  */
            j_i = 0; 
            j_f = y;
        }
        if(op == 'r'){
            i_i = 0;
            i_f = byte_board.size(); 
            /* i_i = x-j-y; 
            i_f = x+j-y;  */
            j_i = y; 
            j_f = byte_board.get(i).size();
        }

        /* System.out.println(i_i);
        System.out.println(i_f);
        System.out.println(j_i);
        System.out.println(j_f); */
        

        for (i = i_i; i < i_f; i++) 
            for (j = j_i; j < j_f; j++) {
                if(byte_board.get(i).get(j) == BoardMarks.NOT_VISITED) priority = priority - 1.0;
                if(byte_board.get(i).get(j) == BoardMarks.VISITED) priority = priority - 10.0;
                //*  Math.abs(i_i - x) * Math.abs(i_f - x) * Math.abs(j_i - y) * Math.abs(j_f - y)
                //System.out.println("entro");
            }
        //System.out.println(priority);

        priority = (priority == 0) ? -1000: priority;

        priority = 0;

        for(i = 0; i < byte_board.size(); i++){
            for (j = 0; j < byte_board.get(i).size(); j++) {
                if(byte_board.get(i).get(j) == BoardMarks.NOT_VISITED) priority = priority + 1.0;
            }
        }
        
        priority = (30*distancia_punto_mas_cercano(board) + 10*board.getNumNotVisited());
        return priority;
    }

    private static double distancia_punto_mas_cercano(Board board){
            int x = board.getMsPacman().getPos_x();
            int y = board.getMsPacman().getPos_y();
            int w = board.getBoard().size();
            int h = board.getBoard().get(0).size();

            int min_dist = 1000;
            int frontera = 2;
            boolean cubrio_espacio_total = false;
            boolean hay_puntos = false;
            do {
                int puntos = 0;
                int wi = Math.max(0, x - frontera);
                int hi = Math.max(0, y - frontera);
                int wf = Math.min(w, x + frontera);
                int hf = Math.min(h, y + frontera);

                //gs.getObjects(Rectangle(wi, hi, wf - wi, hf - hi), DOT, puntos);
                for (int i = wi; i < wf; i++) {
                    for (int j = hi; j < hf; j++) {
                        if(isInside(i, j, w, h) && board.getBoard().get(i).get(j) == BoardMarks.NOT_VISITED){
                            min_dist = Math.min(min_dist, distancia_manhattan(x, i, y, j));
                            //System.out.println(distancia_manhattan(x, i, y, j));
                        }
                    }
                }
                cubrio_espacio_total = (x - frontera) <= 0 && (y - frontera) <= 0 &&
                                    (x + frontera) >= w && (y + frontera) >= h;
                frontera *= 2;
            } while (min_dist == 1000);
            //System.out.println(min_dist);
            return min_dist;
        }

        static boolean isInside(int i, int j, int w, int h){
            boolean f = i >= 0 && i < w && j >= 0 && j < h; 
            return f;
        }

        static int distancia_manhattan(int x1, int x2, int y1, int y2)
        {
            return Math.abs(x1-x2) + Math.abs(y1-y2);
        }

        static int distancia_euclidiana(int x1, int x2, int y1, int y2)
        {
            return (int)sqrt(pow((double)(x2-x1), 2) + pow((double)(y2-y1), 2));
        }

}