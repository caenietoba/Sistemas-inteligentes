/*
 * #### requires ps-version 3.0 ####
 * <#
 *    Version:        0.1
 *    Author:         Camilo Nieto
 *    Creation Date:  Monday, September 23rd 2019, 8:09:17 pm
 *    File: Heuristics.java
 *    Copyright (c) 2019 Your Company
 * 
 * .LICENSE
 * Free software created by Camilo Esteban Nieto Barrera
 *  
 */

package app.multiplayer;

public class Heuristics {

    private Othello othello;

    /**
     * 
     * @param othello
     */
    public Heuristics(Othello othello) {
        this.othello = othello;
    }

    /**
     * 
     * @param board
     * @return
     */
    public Integer heuristicA(Byte[][] board) {

        // https://github.com/kartikkukreja/blog-codes/blob/master/src/Heuristic%20Function%20for%20Reversi%20(Othello).cpp

        Integer priority = 0;
        int rows = board.length;
        int cols = board[0].length;
        int actual_player = 0;
        Boolean player = othello.getPlayer();
        if (player)
            actual_player = 0;
        else
            actual_player = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != null && board[i][j] == actual_player) {
                    if (i == 0 && j == 0 || i == 0 && j == cols - 1 || i == rows - 1 && j == 0
                            || i == rows - 1 && j == cols - 1)
                        priority += 10;
                    else if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1)
                        priority += 5;
                    else
                        priority += 3;
                }
            }
        }

        return priority;

    }

    /**
     * Heuristica tomada de https://kartikkukreja.wordpress.com/2013/03/30/heuristic-function-for-reversiothello/
     */
    Boolean canmove(char self, char opp, char str[]) {
        if (str[0] != opp)
            return false;
        for (int ctr = 1; ctr < str.length-2; ctr++) {
            if (str[ctr] == '-')
                return false;
            if (str[ctr] == self)
                return true;
        }
        return false;
    }

    Boolean isLegalMove(char self, char opp, char grid[][], int startx, int starty) {
        if (grid[startx][starty] != '-')
            return false;
        int rows = grid.length;
        int cols = grid[0].length;
        char str[] = new char[rows+2];
        int x, y, dx, dy, ctr;
        for (dy = -1; dy <= 1; dy++)
            for (dx = -1; dx <= 1; dx++) {
                // keep going if both velocities are zero
                if (dy == 0 && dx == 0)
                    continue;
                str[0] = '\0';
                for (ctr = 1; ctr < rows; ctr++) {
                    x = startx + ctr * dx;
                    y = starty + ctr * dy;
                    if (x >= 0 && y >= 0 && x < rows && y < cols)
                        str[ctr - 1] = grid[x][y];
                    else
                        str[ctr - 1] = 0;
                }
                if (canmove(self, opp, str))
                    return true;
            }
        return false;
    }

    int num_valid_moves(char self, char opp, char grid[][]) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0, i, j;
        for (i = 0; i < rows; i++)
            for (j = 0; j < cols; j++)
                if (isLegalMove(self, opp, grid, i, j))
                    count++;
        return count;
    }

    /*
     * Assuming my_color stores your color and opp_color stores opponent's color '-'
     * indicates an empty square on the board 'b' indicates a black tile and 'w'
     * indicates a white tile on the board
     */
    public double dynamic_heuristic_evaluation_function(Byte[][] grid_, boolean player) {
        int rows = grid_.length;
        int cols = grid_[0].length;
        char grid[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid_[i][j] == null) grid[i][j] = '-';
                else if(grid_[i][j] == 0) grid[i][j] = 'w';
                else if(grid_[i][j] == 1) grid[i][j] = 'b';
            }
        }
        int my_tiles = 0, opp_tiles = 0, i, j, k, my_front_tiles = 0, opp_front_tiles = 0, x, y;
        double p = 0, c = 0, l = 0, m = 0, f = 0, d = 0;
        char my_color = (player ? 'w': 'b'), opp_color = (player ? 'b': 'w');
        //char my_color = (player ? 'b': 'w'), opp_color = (player ? 'w': 'b');

        int X1[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
        int Y1[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
        for (int n = 0; n < rows; n++) {
            for (int n2 = 0; n2 < cols; n2++) {
                
            }
        }
        int V[][] = { 
            { 20, -3, 11, 8, 8, 11, -3, 20 }, 
            { -3, -7, -4, 1, 1, -4, -7, -3 },
            { 11, -4, 2, 2, 2, 2, -4, 11 }, 
            { 8, 1, 2, -3, -3, 2, 1, 8 }, 
            { 8, 1, 2, -3, -3, 2, 1, 8 },
            { 11, -4, 2, 2, 2, 2, -4, 11 }, 
            { -3, -7, -4, 1, 1, -4, -7, -3 }, 
            { 20, -3, 11, 8, 8, 11, -3, 20 } 
        };

        // Piece difference, frontier disks and disk squares
        for (i = 0; i < rows; i++)
            for (j = 0; j < cols; j++) {
                if (grid[i][j] == my_color) {
                    //d += V[i][j];
                    my_tiles++;
                } else if (grid[i][j] == opp_color) {
                   // d -= V[i][j];
                    opp_tiles++;
                }
                if (grid[i][j] != '-') {
                    for (k = 0; k < 8; k++) {
                        x = i + X1[k];
                        y = j + Y1[k];
                        if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '-') {
                            if (grid[i][j] == my_color)
                                my_front_tiles++;
                            else
                                opp_front_tiles++;
                            break;
                        }
                    }
                }
            }
        if (my_tiles > opp_tiles)
            p = (100.0 * my_tiles) / (my_tiles + opp_tiles);
        else if (my_tiles < opp_tiles)
            p = -(100.0 * opp_tiles) / (my_tiles + opp_tiles);
        else
            p = 0;

        if (my_front_tiles > opp_front_tiles)
            f = -(100.0 * my_front_tiles) / (my_front_tiles + opp_front_tiles);
        else if (my_front_tiles < opp_front_tiles)
            f = (100.0 * opp_front_tiles) / (my_front_tiles + opp_front_tiles);
        else
            f = 0;

        // Corner occupancy
        my_tiles = opp_tiles = 0;
        if (grid[0][0] == my_color)                 my_tiles++;
        else if (grid[0][0] == opp_color)           opp_tiles++;
        if (grid[0][cols-1] == my_color)            my_tiles++;
        else if (grid[0][cols-1] == opp_color)      opp_tiles++;
        if (grid[rows-1][0] == my_color)            my_tiles++;
        else if (grid[rows-1][0] == opp_color)      opp_tiles++;
        if (grid[rows-1][cols-1] == my_color)       my_tiles++;
        else if (grid[rows-1][cols-1] == opp_color) opp_tiles++;
        c = 25 * (my_tiles - opp_tiles);

        // Corner closeness
        my_tiles = opp_tiles = 0;
        if (grid[0][0] == '-') {
            if (grid[0][1] == my_color)       my_tiles++;
            else if (grid[0][1] == opp_color) opp_tiles++;
            if (grid[1][1] == my_color)       my_tiles++;
            else if (grid[1][1] == opp_color) opp_tiles++;
            if (grid[1][0] == my_color)       my_tiles++;
            else if (grid[1][0] == opp_color) opp_tiles++;
        }
        if (grid[0][cols-1] == '-') {
            if (grid[0][cols-2] == my_color)       my_tiles++;
            else if (grid[0][cols-2] == opp_color) opp_tiles++;
            if (grid[1][cols-2] == my_color)       my_tiles++;
            else if (grid[1][cols-2] == opp_color) opp_tiles++;
            if (grid[1][cols-2] == my_color)       my_tiles++;
            else if (grid[1][cols-2] == opp_color) opp_tiles++;
        }
        if (grid[rows-1][0] == '-') {
            if (grid[rows-1][1] == my_color)       my_tiles++;
            else if (grid[rows-1][1] == opp_color) opp_tiles++;
            if (grid[rows-2][1] == my_color)       my_tiles++;
            else if (grid[rows-2][1] == opp_color) opp_tiles++;
            if (grid[rows-2][0] == my_color)       my_tiles++;
            else if (grid[rows-2][0] == opp_color) opp_tiles++;
        }
        if (grid[rows-1][cols-1] == '-') {
            if (grid[rows-2][cols-1] == my_color)       my_tiles++;
            else if (grid[rows-2][cols-1] == opp_color) opp_tiles++;
            if (grid[rows-2][cols-2] == my_color)       my_tiles++;
            else if (grid[rows-2][cols-2] == opp_color) opp_tiles++;
            if (grid[rows-1][cols-2] == my_color)       my_tiles++;
            else if (grid[rows-1][cols-2] == opp_color) opp_tiles++;
        }
        l = -12.5 * (my_tiles - opp_tiles);

        // Mobility
        my_tiles = num_valid_moves(my_color, opp_color, grid);
        opp_tiles = num_valid_moves(opp_color, my_color, grid);
        if (my_tiles > opp_tiles)
            m = (100.0 * my_tiles) / (my_tiles + opp_tiles);
        else if (my_tiles < opp_tiles)
            m = -(100.0 * opp_tiles) / (my_tiles + opp_tiles);
        else
            m = 0;

        // final weighted score
        double score = (10 * p) + (801.724 * c) + (382.026 * l) + (78.922 * m) + (74.396 * f) + (10 * d);
        return score;
    }
}