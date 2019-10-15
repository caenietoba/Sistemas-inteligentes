package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import app.moves.*;

public class Cube2{
    private List<List<List<Byte>>> cube;

    public Cube2( Byte[][][] cube, int rand_moves, int num_moves ){
        this.cube = arrayToList(cube);
        if( num_moves == 6 ) randomizeCube6(rand_moves);
        else randomizeCube12(rand_moves);
        // printCube();
    }

    public Cube2( Cube2 cube ){
        this.cube = cube.copyCube();
    }

    protected static List<List<List<Byte>>> arrayToList( Byte[][][] cube ){
        List<List<List<Byte>>> list_cube = new ArrayList<>();
        for( int i = 0; i < cube.length; i++){
            list_cube.add(new ArrayList<>());
            for( int j = 0; j < cube[i].length; j++){
                list_cube.get(i).add(new ArrayList<>());
                for( int k = 0; k < cube[i][j].length; k++){
                    list_cube.get(i).get(j).add( cube[i][j][k] );
                }
            }
        }
        return list_cube;
    }

    /* private void randomizeCube12(int rand_moves){
        Random rd = new Random();
        Movements move = null;
        int rnd;
        for( int i=0; i<rand_moves; i++ ){
            rnd = rd.nextInt(12);
            switch( rnd ){
                case 0:
                    move = new MoveUp((byte)0);
                    break;
                case 1:
                    move = new MoveUp((byte)1);
                    break;
                case 2:
                    move = new MoveDown((byte)0);
                    break;
                case 3:
                    move = new MoveDown((byte)1);
                    break;
                case 4:
                    move = new MoveRigth((byte)0);
                    break;
                case 5:
                    move = new MoveRigth((byte)1);
                    break;
                case 6:
                    move = new MoveLeft((byte)0);
                    break;
                case 7:
                    move = new MoveLeft((byte)1);
                    break;
                case 8:
                    move = new MoveRigthTurn((byte)0);
                    break;
                case 9:
                    move = new MoveRigthTurn((byte)1);
                    break;
                case 10:
                    move = new MoveLeftTurn((byte)0);
                    break;
                case 11:
                    move = new MoveLeftTurn((byte)1);
                    break;
            }
            move.move(this);
        }
    } */

    private void randomizeCube12(int rand_moves){
        Random rd = new Random();
        Movements move = null;
        int rnd;
        for( int i=0; i<rand_moves; i++ ){
            rnd = rd.nextInt(12);
            switch( rnd ){
                case 0:
                    move = new MoveUp1();
                    break;
                case 1:
                    move = new MoveUp2();
                    break;
                case 2:
                    move = new MoveDown1();
                    break;
                case 3:
                    move = new MoveDown2();
                    break;
                case 4:
                    move = new MoveRigth1();
                    break;
                case 5:
                    move = new MoveRigth2();
                    break;
                case 6:
                    move = new MoveLeft1();
                    break;
                case 7:
                    move = new MoveLeft2();
                    break;
                case 8:
                    move = new MoveRigthTurn1();
                    break;
                case 9:
                    move = new MoveRigthTurn2();
                    break;
                case 10:
                    move = new MoveLeftTurn1();
                    break;
                case 11:
                    move = new MoveLeftTurn2();
                    break;
            }
            move.move(this);
        }
    }

    private void randomizeCube6(int rand_moves){
        Random rd = new Random();
        Movements move = null;
        int rnd;
        for( int i=0; i<15; i++ ){
            rnd = rd.nextInt(6);
            switch( rnd ){
                case 0:
                    move = new MoveDown((byte)0);
                    break;
                case 1:
                    move = new MoveDown((byte)1);
                    break;
                case 2:
                    move = new MoveLeft((byte)0);
                    break;
                case 3:
                    move = new MoveLeft((byte)1);
                    break;
                case 4:
                    move = new MoveLeftTurn((byte)0);
                    break;
                case 5:
                    move = new MoveLeftTurn((byte)1);
                    break;
            }
            move.move(this);
        }
    }

    protected List<List<List<Byte>>> copyCube(){
        List<List<List<Byte>>> aux_cube = new ArrayList<>();

        for( int i = 0; i < this.cube.size(); i++ ){
            aux_cube.add(new ArrayList<>());
            for( int j = 0; j < this.cube.get(i).size(); j++ ){
                aux_cube.get(i).add(new ArrayList<>());
                for( int k = 0; k < this.cube.get(i).get(j).size(); k++ )
                    aux_cube.get(i).get(j).add( this.cube.get(i).get(j).get(k) );
            }
        }
        return aux_cube;
    }
    
    private Boolean compareCube( List<List<List<Byte>>> cube ){
        return this.cube.equals(cube);
    }

    public Boolean finished( List<List<List<Byte>>> cube ){
        return compareCube(cube);
    }

    public void printCube(){
        System.out.println("\n------------CUBE------------");
        for (int i = 0; i < cube.get(i).size(); i++) {
            for(int j = 0; j < cube.size(); j++){
                System.out.print("|");
                for(int k = 0; k < cube.get(i).get(i).size(); k++){
                    System.out.print(" " + cube.get(j).get(i).get(k));
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    public static void printCube( List<List<List<Byte>>> cube ){
        System.out.println("\n------------CUBE------------");
        for (int i = 0; i < cube.get(i).size(); i++) {
            for(int j = 0; j < cube.size(); j++){
                System.out.print("|");
                for(int k = 0; k < cube.get(i).get(i).size(); k++){
                    System.out.print(" " + cube.get(j).get(i).get(k));
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    /* public Movements[] getChilds12(){
        Movements[] childs = new Movements[12];

        childs[0] = new MoveUp((byte)0);
        childs[1] = new MoveUp((byte)1);
        childs[2] = new MoveDown((byte)0);
        childs[3] = new MoveDown((byte)1);
        childs[4] = new MoveRigth((byte)0);
        childs[5] = new MoveRigth((byte)1);
        childs[6] = new MoveLeft((byte)0);
        childs[7] = new MoveLeft((byte)1);
        childs[8] = new MoveRigthTurn((byte)0);
        childs[9] = new MoveRigthTurn((byte)1);
        childs[10] = new MoveLeftTurn((byte)0);
        childs[11] = new MoveLeftTurn((byte)1);

        return childs;
    } */

    public Movements[] getChilds12(){
        Movements[] childs = new Movements[12];

        childs[0] = new MoveUp1();
        childs[1] = new MoveUp2();
        childs[2] = new MoveDown1();
        childs[3] = new MoveDown2();
        childs[4] = new MoveRigth1();
        childs[5] = new MoveRigth2();
        childs[6] = new MoveLeft1();
        childs[7] = new MoveLeft2();
        childs[8] = new MoveRigthTurn1();
        childs[9] = new MoveRigthTurn2();
        childs[10] = new MoveLeftTurn1();
        childs[11] = new MoveLeftTurn2();

        return childs;
    }

    public Movements[] getChilds6(){
        Movements[] childs = new Movements[6];

        childs[0] = new MoveUp((byte)0);
        childs[1] = new MoveUp((byte)1);
        childs[2] = new MoveRigth((byte)0);
        childs[3] = new MoveRigth((byte)1);
        childs[4] = new MoveRigthTurn((byte)0);
        childs[5] = new MoveRigthTurn((byte)1);

        return childs;
    }
    
    public List<List<List<Byte>>> getCube() {
        return this.cube;
    }

    public void setCube(List<List<List<Byte>>> cube) {
        this.cube = cube;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cube2)) {
            return false;
        }
        Cube2 cube2 = (Cube2) o;
        return Objects.equals(cube, cube2.cube);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cube);
    }

    @Override
    public String toString() {
        return "{" +
            " cube='" + getCube() + "'" +
            "}";
    }

    
}
