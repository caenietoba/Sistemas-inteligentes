package app.representations.array;

import java.util.Arrays;
import java.util.HashSet;

import app.representations.Cube;
import app.representations.array.ArrayCube;

public class Heuristics {

    private static int[] sides = {0, 2, 6, 8, 15, 17, 26, 35};
    private static int[] corners = {1, 3, 5, 7, 12, 14, 16, 23, 25, 32, 34, 43};
    private static HashSet<Integer> ud_faces = new HashSet<>(Arrays.asList(0, 5));
    private static HashSet<Integer> lateral_faces = new HashSet<>(Arrays.asList(1, 2, 3, 4));

    public static Byte heuristic1(Cube<Byte[]> cube){
        Byte priority = 0;

        for (int i : corners) {
            priority = (byte)(priority + count(cube, i));
        }

        for (int i : sides) {
            priority = (byte)(priority + count(cube, i));
        }

        return (byte)(priority/8 + 1);
    }

    public static Byte heuristic2(Cube<Byte[]> cube){
        int priority_corners = 0, priority_sides = 0;

        for (int i : corners) {
            priority_corners = priority_corners + count(cube, i);
        }

        for (int i : sides) {
            priority_sides = priority_sides + count(cube, i);
        }
        return (byte)(Math.max(priority_corners/4, priority_sides/4) + 1);
    }

    private static int count(Cube<Byte[]> cube, int index) {
        int w_index = findIndex(cube.getCube(), index);
        int moves_same_array = 0, moves_dif_array = 0;

        if((index % 9)%3 == 3 && (w_index % 9)%3 == 5 || (index % 9)%3 == 5 && (w_index % 9)%3 == 3) moves_same_array = 4;
        else moves_same_array = Math.abs((index % 9)%3 - (w_index % 9)%3) + Math.abs((int)((index % 9)/3) - (int)((w_index % 9)/3));

        if(ud_faces.contains((int)(w_index/9)) || ud_faces.contains((int)(index/9)))
            if(lateral_faces.contains((int)(w_index/9)) || lateral_faces.contains((int)(index/9))) moves_dif_array++;
            else moves_dif_array+=2;
        else
            moves_dif_array += (Math.abs((int)(w_index/9) - (int)(index/9)) > 2 ? 1 : Math.abs((int)(w_index/9) - (int)(index/9)));

        return moves_same_array + moves_dif_array;
    }

    private static int findIndex(Byte[] array, int value){
        for (int i = 0; i < array.length; i++) {
            if(array[i] == value) return i;
        }
        return 54;
    }

    public static Byte heuristic( Cube<Byte[]> cube ){
        Byte priority = 1;
        for (int i = 0; i < cube.getCube().length; i++) 
            if(cube.getCube()[i] != ArrayCube.goal_cube[i]) priority++;
        return (byte)(priority/8);
    }

}