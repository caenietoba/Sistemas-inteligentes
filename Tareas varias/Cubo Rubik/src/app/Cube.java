package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import app.Constants;

public class Cube implements Comparable<Cube> {
    
    private Byte priority = 0;
    private List<List<List<Byte>>> cube;

    public Cube( Byte[][][] cube ){
        this.cube = arrayToList(cube);
    }

    private Cube( Cube cube ){
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

    public void randomizeCube(){
        Random rd = new Random();
        int rnd;
        for( int i=0; i<12; i++ ){
            /* rnd = rd.nextInt(12);
            switch( rnd ){
                case 0:
                    up(0);
                    break;
                case 1:
                    up(1);
                    break;
                case 2:
                    down(0);
                    break;
                case 3:
                    down(1);
                    break;
                case 4:
                    rigth(0);
                    break;
                case 5:
                    rigth(1);
                    break;
                case 6:
                    left(0);
                    break;
                case 7:
                    left(1);
                    break;
                case 8:
                    rigthTurn(0);
                    break;
                case 9:
                    rigthTurn(1);
                    break;
                case 10:
                    leftTurn(0);
                    break;
                case 11:
                    leftTurn(1);
                    break;
            } */
            rnd = rd.nextInt(6);
            switch( rnd ){
                case 0:
                    down(0);
                    break;
                case 1:
                    down(1);
                    break;
                case 2:
                    left(0);
                    break;
                case 3:
                    left(1);
                    break;
                case 4:
                    leftTurn(0);
                    break;
                case 5:
                    leftTurn(1);
                    break;
            }
        }
    }
    
    private void up( int num_move ){
        Byte k = 0;
        if( num_move == 0 ) k = Constants.FIRST_MOVE;
        else if( num_move == 1 ) k = Constants.SECOND_MOVE;
        else throw new ArithmeticException("Variable num_love must be 0 or 1");;

        List<List<List<Byte>>> _cube = copyCube();

        for( int i = 0; i < 3; i++){
            _cube.get(0).get(i).set(k, this.cube.get(1).get(i).get(k));
            _cube.get(1).get(i).set(k, this.cube.get(5).get(i).get(k));
            _cube.get(5).get(i).set(k, this.cube.get(3).get(2-i).get(k));
            _cube.get(3).get(2-i).set(k, this.cube.get(0).get(i).get(k));
        }

        if( k == Constants.FIRST_MOVE ) _cube.set(4, rotateMatrix( _cube.get(4), true ));
        if( k == Constants.SECOND_MOVE ) _cube.set(2, rotateMatrix( _cube.get(2), true ));

        this.cube = _cube;
    }
    
    private void down( int num_move ){
        Byte k = 0;
        if( num_move == 0 ) k = Constants.FIRST_MOVE;
        else if( num_move == 1 ) k = Constants.SECOND_MOVE;
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        List<List<List<Byte>>> _cube = copyCube();

        for( int i = 0; i < 3; i++){
            _cube.get(1).get(i).set(k, this.cube.get(0).get(i).get(k));
            _cube.get(5).get(i).set(k, this.cube.get(1).get(i).get(k));
            _cube.get(3).get(i).set(k, this.cube.get(5).get(2-i).get(k));
            _cube.get(0).get(2-i).set(k, this.cube.get(3).get(i).get(k));
        }

        if( k == Constants.FIRST_MOVE ) _cube.set(4, rotateMatrix( _cube.get(4), false ));
        if( k == Constants.SECOND_MOVE ) _cube.set(2, rotateMatrix( _cube.get(2), false ));

        this.cube = _cube;
    }
    
    private void rigth( int num_move ){
        Byte k = 0;
        if( num_move == 0 ) k = Constants.FIRST_MOVE;
        else if( num_move == 1 ) k = Constants.SECOND_MOVE;
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        List<List<List<Byte>>> _cube = copyCube();

        for( int i = 0; i < 3; i++){
            _cube.get(1).get(k).set(i, this.cube.get(4).get(k).get(i));
            _cube.get(2).get(k).set(i, this.cube.get(1).get(k).get(i));
            _cube.get(3).get(k).set(i, this.cube.get(2).get(k).get(i));
            _cube.get(4).get(k).set(i, this.cube.get(3).get(k).get(i));
        }

        if( k == Constants.FIRST_MOVE ) _cube.set(0, rotateMatrix( _cube.get(0), true ));
        if( k == Constants.SECOND_MOVE ) _cube.set(5, rotateMatrix( _cube.get(5), true ));

        this.cube = _cube;
    }
    
    private void left( int num_move ){
        Byte k = 0;
        if( num_move == 0 ) k = Constants.FIRST_MOVE;
        else if( num_move == 1 ) k = Constants.SECOND_MOVE;
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        List<List<List<Byte>>> _cube = copyCube();

        for( int i = 0; i < 3; i++){
            _cube.get(4).get(k).set(i, this.cube.get(1).get(k).get(i));
            _cube.get(1).get(k).set(i, this.cube.get(2).get(k).get(i));
            _cube.get(2).get(k).set(i, this.cube.get(3).get(k).get(i));
            _cube.get(3).get(k).set(i, this.cube.get(4).get(k).get(i));
        }

        if( k == Constants.FIRST_MOVE ) _cube.set(0, rotateMatrix( _cube.get(0), false ));
        if( k == Constants.SECOND_MOVE ) _cube.set(5, rotateMatrix( _cube.get(5), false ));

        this.cube = _cube;
    }
    
    private void rigthTurn( int num_move ){
        Byte k = 0, l = 0;
        if( num_move == 0 ) {k = Constants.FIRST_MOVE; l = Constants.SECOND_MOVE;}
        else if( num_move == 1 ) {k = Constants.SECOND_MOVE; l = Constants.FIRST_MOVE;}
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        List<List<List<Byte>>> _cube = copyCube();

        for( int i = 0; i < 3; i++){
            _cube.get(0).get(k).set(i, this.cube.get(4).get(i).get(k));
            _cube.get(2).get(i).set(l, this.cube.get(0).get(k).get(i));
            _cube.get(5).get(l).set(i, this.cube.get(2).get(i).get(l));
            _cube.get(4).get(i).set(k, this.cube.get(5).get(l).get(i));
        }

        if( k == Constants.FIRST_MOVE ) _cube.set(3, rotateMatrix( _cube.get(3), true ));
        if( k == Constants.SECOND_MOVE ) _cube.set(1, rotateMatrix( _cube.get(1), true ));

        this.cube = _cube;
    }
    
    private void leftTurn( int num_move ){
        Byte k = 0, l = 0;
        if( num_move == 0 ) {k = Constants.FIRST_MOVE; l = Constants.SECOND_MOVE;}
        else if( num_move == 1 ) {k = Constants.SECOND_MOVE; l = Constants.FIRST_MOVE;}
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        List<List<List<Byte>>> _cube = copyCube();

        for( int i = 0; i < 3; i++){
            _cube.get(4).get(i).set(k, this.cube.get(0).get(k).get(i));
            _cube.get(0).get(k).set(i, this.cube.get(2).get(i).get(l));
            _cube.get(2).get(i).set(l, this.cube.get(5).get(l).get(i));
            _cube.get(5).get(l).set(i, this.cube.get(4).get(i).get(k));
        }

        if( k == Constants.FIRST_MOVE ) _cube.set(3, rotateMatrix( _cube.get(3), false ));
        if( k == Constants.SECOND_MOVE ) _cube.set(1, rotateMatrix( _cube.get(1), false ));

        this.cube = _cube;
    }
    
    private List<List<Byte>> rotateMatrix( List<List<Byte>> matrix, Boolean dir ){
        List<List<Byte>> aux_matrix = new ArrayList<>();
        for( int i = 0; i < 3; i++ ){
            aux_matrix.add(new ArrayList<>());
            for( int j = 0; j < 3; j++ )
                aux_matrix.get(i).add(matrix.get(i).get(j));
        }
        if( dir ){
            for(int i=0; i<3; i++) {
                aux_matrix.get(0).set(i, matrix.get(2-i).get(0));
                aux_matrix.get(i).set(2, matrix.get(0).get(i));
                aux_matrix.get(2).set(2-i, matrix.get(i).get(2));
                aux_matrix.get(2-i).set(0, matrix.get(2).get(2-i));
            }
        } else{
            for(int i=0; i<3; i++) {
                aux_matrix.get(2-i).set(0, matrix.get(0).get(i));
                aux_matrix.get(0).set(i, matrix.get(i).get(2));
                aux_matrix.get(i).set(2, matrix.get(2).get(2-i));
                aux_matrix.get(2).set(2-i, matrix.get(2-i).get(0));
            }
        }
        return aux_matrix;
    }

    private Boolean compareCube( List<List<List<Byte>>> cube ){
        return this.cube.equals(cube);
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
    
    public Boolean finished( List<List<List<Byte>>> cube ){
        return compareCube(cube);
    }

    public void printCube(){
        System.out.println("\n------------CUBE------------");
        for( int i=0; i<6; i++ ){
            for( int j=0; j<3; j++ ){
                for( int k=0; k<3; k++ ){
                    System.out.print( this.cube.get(i).get(j).get(k) + " " );
                }
                System.out.print( "\n" );
            }
            System.out.print( "------\n" );
        }
    }

    public void printCube( List<List<List<Byte>>> cube ){
        System.out.println("\n------------CUBE------------");
        for( int i=0; i<6; i++ ){
            for( int j=0; j<3; j++ ){
                for( int k=0; k<3; k++ ){
                    System.out.print( cube.get(i).get(j).get(k) + " " );
                }
                System.out.print( "\n" );
            }
            System.out.print( "------\n" );
        }
    }
    
    public Cube[] getChilds(){
        Cube[] childs = new Cube[6];
        (childs[0] = new Cube( this )).up(0);
        (childs[1] = new Cube( this )).up(1);
        (childs[2] = new Cube( this )).rigth(0);
        (childs[3] = new Cube( this )).rigth(1);
        (childs[4] = new Cube( this )).rigthTurn(0);
        (childs[5] = new Cube( this )).rigthTurn(1);
        /* Cube[] childs = new Cube[12];
        (childs[0] = new Cube( this )).up(0);
        (childs[1] = new Cube( this )).up(1);
        (childs[2] = new Cube( this )).rigth(0);
        (childs[3] = new Cube( this )).rigth(1);
        (childs[4] = new Cube( this )).rigthTurn(0);
        (childs[5] = new Cube( this )).rigthTurn(1);
        (childs[6] = new Cube( this )).down(0);
        (childs[7] = new Cube( this )).down(1);
        (childs[8] = new Cube( this )).left(0);
        (childs[9] = new Cube( this )).left(1);
        (childs[10] = new Cube( this )).leftTurn(0);
        (childs[11] = new Cube( this )).leftTurn(1); */
        
        return childs;
    }

    public Byte getPriority() {
        return this.priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public List<List<List<Byte>>> getCube() {
        return this.cube;
    }

    public void setCube(List<List<List<Byte>>> cube) {
        this.cube = cube;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cube cube = (Cube) o;
            return Integer.compare(cube.getPriority(), this.getPriority()) == 0 &&
                    this.cube.equals(cube.getCube());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cube, priority);
    }

    @Override
    public String toString() {
        return "{" +
            " priority='" + getPriority() + "'" +
            ", cube='" + getCube() + "'" +
            "}";
    }

    @Override
    public int compareTo(Cube o) {
        if( this.priority > o.getPriority() ) return 1;
        else if( this.priority < o.getPriority() ) return -1;
        else return 0;
    }
    
}
