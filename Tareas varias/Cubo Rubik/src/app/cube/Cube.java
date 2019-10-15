package app.cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import app.Constants;

class Cube extends Throwable implements Comparable<Cube> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Byte[][][] cube;
    private Integer priority = 100;

    public Cube( Byte[][][] cube ){
        this.cube = cube;
    }

    public Cube test( Cube cube ){
        Cube aux = new Cube( cube.getCube() );
        aux.printCube();
        return aux;

    }

    public void randomizeCube(){
        Random rd = new Random();
        int rnd;
        for( int i=0; i<15; i++ ){
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

        Byte[][][] _cube = copyCube();

        _cube[0][0][k] = this.cube[1][0][k];
        _cube[0][1][k] = this.cube[1][1][k];
        _cube[0][2][k] = this.cube[1][2][k];

        _cube[1][0][k] = this.cube[5][0][k];
        _cube[1][1][k] = this.cube[5][1][k];
        _cube[1][2][k] = this.cube[5][2][k];

        _cube[5][0][k] = this.cube[3][2][k];
        _cube[5][1][k] = this.cube[3][1][k];
        _cube[5][2][k] = this.cube[3][0][k];

        _cube[3][2][k] = this.cube[0][0][k];
        _cube[3][1][k] = this.cube[0][1][k];
        _cube[3][0][k] = this.cube[0][2][k];

        if( k == Constants.FIRST_MOVE ) _cube[4] = rotateMatrix( _cube[4], true );
        if( k == Constants.SECOND_MOVE ) _cube[2] = rotateMatrix( _cube[2], true );

        this.cube = _cube;
    }
    
    private void down( int num_move ){
        Byte k = 0;
        if( num_move == 0 ) k = Constants.FIRST_MOVE;
        else if( num_move == 1 ) k = Constants.SECOND_MOVE;
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        Byte[][][] _cube = copyCube();

        _cube[0][0][k] = cube[1][0][k];
        _cube[0][1][k] = cube[1][1][k];
        _cube[0][2][k] = cube[1][2][k];

        _cube[1][0][k] = cube[5][0][k];
        _cube[1][1][k] = cube[5][1][k];
        _cube[1][2][k] = cube[5][2][k];

        _cube[5][0][k] = cube[3][2][k];
        _cube[5][1][k] = cube[3][1][k];
        _cube[5][2][k] = cube[3][0][k];

        _cube[3][2][k] = cube[0][0][k];
        _cube[3][1][k] = cube[0][1][k];
        _cube[3][0][k] = cube[0][2][k];

        if( k == Constants.FIRST_MOVE ) _cube[4] = rotateMatrix( _cube[4], true );
        if( k == Constants.SECOND_MOVE ) _cube[2] = rotateMatrix( _cube[2], true );

        this.cube = _cube;
    }
    
    private void rigth( int num_move ){
        Byte k = 0;
        if( num_move == 0 ) k = Constants.FIRST_MOVE;
        else if( num_move == 1 ) k = Constants.SECOND_MOVE;
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        Byte[][][] _cube = copyCube();

        _cube[1][k][0] = cube[4][k][0];
        _cube[1][k][1] = cube[4][k][1];
        _cube[1][k][2] = cube[4][k][2];

        _cube[2][k][0] = cube[1][k][0];
        _cube[2][k][1] = cube[1][k][1];
        _cube[2][k][2] = cube[1][k][2];

        _cube[3][k][0] = cube[2][k][0];
        _cube[3][k][1] = cube[2][k][1];
        _cube[3][k][2] = cube[2][k][2];

        _cube[4][k][0] = cube[3][k][0];
        _cube[4][k][1] = cube[3][k][1];
        _cube[4][k][2] = cube[3][k][2];

        if( k == Constants.FIRST_MOVE ) _cube[0] = rotateMatrix( _cube[0], true );
        if( k == Constants.SECOND_MOVE ) _cube[5] = rotateMatrix( _cube[5], true );

        this.cube = _cube;
    }
    
    private void left( int num_move ){
        Byte k = 0;
        if( num_move == 0 ) k = Constants.FIRST_MOVE;
        else if( num_move == 1 ) k = Constants.SECOND_MOVE;
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        Byte[][][] _cube = copyCube();

        _cube[4][k][0] = cube[1][k][0];
        _cube[4][k][1] = cube[1][k][1];
        _cube[4][k][2] = cube[1][k][2];

        _cube[1][k][0] = cube[2][k][0];
        _cube[1][k][1] = cube[2][k][1];
        _cube[1][k][2] = cube[2][k][2];

        _cube[2][k][0] = cube[3][k][0];
        _cube[2][k][1] = cube[3][k][1];
        _cube[2][k][2] = cube[3][k][2];

        _cube[3][k][0] = cube[4][k][0];
        _cube[3][k][1] = cube[4][k][1];
        _cube[3][k][2] = cube[4][k][2];

        if( k == Constants.FIRST_MOVE ) _cube[0] = rotateMatrix( _cube[0], true );
        if( k == Constants.SECOND_MOVE ) _cube[5] = rotateMatrix( _cube[5], true );

        this.cube = _cube;
    }
    
    private void rigthTurn( int num_move ){
        Byte k = 0, l = 0;
        if( num_move == 0 ) {k = Constants.FIRST_MOVE; l = Constants.SECOND_MOVE;}
        else if( num_move == 1 ) {k = Constants.SECOND_MOVE; l = Constants.FIRST_MOVE;}
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        Byte[][][] _cube = copyCube();

        _cube[0][k][0] = cube[4][0][k];
        _cube[0][k][1] = cube[4][1][k];
        _cube[0][k][2] = cube[4][2][k];

        _cube[2][0][l] = cube[0][k][0];
        _cube[2][1][l] = cube[0][k][1];
        _cube[2][2][l] = cube[0][k][2];

        _cube[5][l][0] = cube[2][0][l];
        _cube[5][l][1] = cube[2][1][l];
        _cube[5][l][2] = cube[2][2][l];

        _cube[4][0][k] = cube[5][l][0];
        _cube[4][1][k] = cube[5][l][1];
        _cube[4][2][k] = cube[5][l][2];

        if( k == Constants.FIRST_MOVE ) _cube[3] = rotateMatrix( _cube[3], true );
        if( k == Constants.SECOND_MOVE ) _cube[1] = rotateMatrix( _cube[1], true );

        this.cube = _cube;
    }
    
    private void leftTurn( int num_move ){
        Byte k = 0, l = 0;
        if( num_move == 0 ) {k = Constants.FIRST_MOVE; l = Constants.SECOND_MOVE;}
        else if( num_move == 1 ) {k = Constants.SECOND_MOVE; l = Constants.FIRST_MOVE;}
        else throw new ArithmeticException("Variable num_love must be 0 or 1");

        Byte[][][] _cube = copyCube();

        _cube[4][0][k] = cube[0][k][0];
        _cube[4][1][k] = cube[0][k][1];
        _cube[4][2][k] = cube[0][k][2];

        _cube[0][k][0] = cube[2][0][l];
        _cube[0][k][1] = cube[2][1][l];
        _cube[0][k][2] = cube[2][2][l];

        _cube[2][0][l] = cube[5][l][0];
        _cube[2][1][l] = cube[5][l][1];
        _cube[2][2][l] = cube[5][l][2];

        _cube[5][l][0] = cube[4][0][k];
        _cube[5][l][1] = cube[4][1][k];
        _cube[5][l][2] = cube[4][2][k];

        if( k == Constants.FIRST_MOVE ) _cube[3] = rotateMatrix( _cube[3], true );
        if( k == Constants.SECOND_MOVE ) _cube[1] = rotateMatrix( _cube[1], true );

        this.cube = _cube;
    }
    
    private Byte[][] rotateMatrix( Byte[][] matrix, Boolean dir ){
        Byte[][] aux_matrix = new Byte[3][3];
        if( dir ){
            for(int i=0; i<3; i++) {
                aux_matrix[0][i] = matrix[2-i][0];
                aux_matrix[i][2] = matrix[0][i];
                aux_matrix[2][2-i] = matrix[i][2];
                aux_matrix[2-i][0] = matrix[2][2-i];
            }
        } else{
            for(int i=0; i<3; i++) {
                aux_matrix[2-i][0] = matrix[0][i];
                aux_matrix[0][i] = matrix[i][2];
                aux_matrix[i][2] = matrix[2][2-i];
                aux_matrix[2][2-i] = matrix[2-i][0];
            }
        }
        aux_matrix[1][1] = matrix[1][1];
        return aux_matrix;
    }

    private Boolean compareCube( Byte[][][] cube ){
        for( int i=0; i<6; i++ )
            for( int j=0; j<3; j++ )
                for( int k=0; k<3; k++ )
                    if( this.cube[i][j][k] != cube[i][j][k] )
                        return false;
        return true;
    }

    private Byte[][][] copyCube(){
        Byte[][][] aux_cube = new Byte[6][3][3];

        for( int i = 0; i < 6; i++ )
            for( int j = 0; j < cube[i].length; j++ )
                for( int k = 0; k < cube[i][j].length; k++ )
                    aux_cube[i][j][k] = cube[i][j][k];

        return aux_cube;
    }
    
    public Boolean finished( Byte[][][] cube ){
        return compareCube(cube);
    }

    public void printCube(){
        System.out.println("\n------------CUBE------------");
        for( int i=0; i<6; i++ ){
            for( int j=0; j<3; j++ ){
                for( int k=0; k<3; k++ ){
                    System.out.print( this.cube[i][j][k] + " " );
                }
                System.out.print( "\n" );
            }
            System.out.print( "------\n" );
        }
    }

    public void printCube( Byte[][][] cube ){
        System.out.println("\n------------CUBE------------");
        for( int i=0; i<6; i++ ){
            for( int j=0; j<3; j++ ){
                for( int k=0; k<3; k++ ){
                    System.out.print( cube[i][j][k] + " " );
                }
                System.out.print( "\n" );
            }
            System.out.print( "------\n" );
        }
    }

    public List<List<List<Byte>>> getListCube(){
        List<List<List<Byte>>> list = new ArrayList<>();
        List<List<Byte>> aux2 = new ArrayList<>(); 
        for( int i = 0; i < 6; i++ ){
            aux2 = new ArrayList<>(); 
            for( int j = 0; j < 3; j++ ){
                aux2.add(Arrays.asList(this.cube[i][j]));
            }
            list.add(aux2);
        }
        return list;
    }
    
    public Cube[] getChilds(){
        Cube[] childs = new Cube[6];
        (childs[0] = new Cube( this.cube )).up(0);
        (childs[1] = new Cube( this.cube )).up(1);
        (childs[2] = new Cube( this.cube )).rigth(0);
        (childs[3] = new Cube( this.cube )).rigth(1);
        (childs[4] = new Cube( this.cube )).rigthTurn(0);
        (childs[5] = new Cube( this.cube )).rigthTurn(1);
        /* Cube[] childs = new Cube[12];
        childs[0] = new Cube( up1( true ), false );
        childs[1] = new Cube( up2( true ), false );
        //childs[2] = new Cube( down1( true ), false );
        //childs[3] = new Cube( down2( true ), false );
        childs[4] = new Cube( rigth1( true ), false );
        childs[5] = new Cube( rigth2( true ), false );
        // childs[6] = new Cube( left1( true ), false );
        // childs[7] = new Cube( left2( true ), false );
        childs[8] = new Cube( rigthTurn1( true ), false );
        childs[9] = new Cube( rigthTurn2( true ), false );
        // childs[10] = new Cube( leftTurn1( true ), false );
        // childs[11] = new Cube( leftTurn2( true ), false ); */
        
        return childs;
    }
    
    public Byte[][][] getCube() {
        return this.cube;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cube)) {
            return false;
        }
        Cube cube = (Cube) o;
        return compareCube( cube.getCube() );
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for (int i = 0, s = this.cube.length; i < s; i++) {
            for( int j = 0; j < this.cube[i].length; j++ )
                for( int k = 0; k < this.cube[j].length; k++ )
                    if( i != 1 && j != 1 && k != 1)
                    hash = 31 * hash + this.cube[i][j][k].hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "{" +
            " childs='" + getChilds() + "'" +
            ", cube='" + getCube() + "'" +
            ", priority='" + getPriority() + "'" +
            "}";
    }


    @Override
    public int compareTo(Cube o) {
        if( this.priority > o.getPriority() ) return 1;
        else if( this.priority < o.getPriority() ) return -1;
        else return 0;
    }
    
}
