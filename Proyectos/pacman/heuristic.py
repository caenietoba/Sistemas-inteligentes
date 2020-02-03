from board import NOT_VISITED

class Heuristic:

    def heuristicA(self, board):

        (row, col) = board.ms_pacman
        op = board.last_movement

        priority = 0
        """ int i_i = 0, i_f = 0, j_i = 0, j_f = 0, i = 0, j = 0;

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

        priority = (priority == 0) ? -1000: priority; """
        
        priority = (30*self.distancia_punto_mas_cercano(board) + 500*board.num_not_visited)
        return priority 
    

    def distancia_punto_mas_cercano(self, board):
        (x, y) = board.ms_pacman
        w = len(board.board)
        h = len(board.board[0])

        min_dist = 1000
        frontera = 2
        cubrio_espacio_total = False
        hay_puntos = False
        while not cubrio_espacio_total:
            puntos = 0
            wi = max(0, x - frontera)
            hi = max(0, y - frontera)
            wf = min(w, x + frontera)
            hf = min(h, y + frontera)

            i = wi
            j = hi

            for i in range(wi, wf):
                for j in range(hi, hf):
                    if self.isInside(i, j, w, h) and board.board[i][j] == NOT_VISITED:
                        min_dist = min(min_dist, self.distancia_manhattan(x, i, y, j))

            cubrio_espacio_total = (x - frontera) <= 0 and (y - frontera) <= 0 and \
                                (x + frontera) >= w and (y + frontera) >= h
            frontera *= 2

        # System.out.println(min_dist);
        return 0 if min_dist == 1000 else min_dist
        

    def isInside(self, i, j, w, h):
        return i >= 0 and i < w and j >= 0 and j < h
        

    def distancia_manhattan(self, x1, x2, y1, y2):
        return abs(x1-x2) + abs(y1-y2)
    

    """ def distancia_euclidiana(x1, x2, y1, y2):
        return (int)sqrt(pow((double)(x2-x1), 2) + pow((double)(y2-y1), 2)) """
    