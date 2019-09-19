package app;

class Cube {

    private final Character[][][] final_cube = {
        {
            {'B','B','B'},
            {'B','B','B'}, //Cara superior
            {'B','B','B'}
        },
        {
            {'W','W','W'},
            {'W','W','W'}, //Cara de al frente
            {'W','W','W'}
        },
        {
            {'R','R','R'},
            {'R','R','R'}, //Cara derecha
            {'R','R','R'}
        },
        {
            {'G','G','G'},
            {'G','G','G'}, //Cara trasera
            {'G','G','G'}
        },
        {
            {'Y','Y','Y'},
            {'Y','Y','Y'}, //Cara izquierda
            {'Y','Y','Y'}
        },
        {
            {'O','O','O'},
            {'O','O','O'}, //Cara inferior
            {'O','O','O'}
        }
    };

    private Character[][][][] array_cube;
    private Integer index_array;

    protected Character[][][][] actions(){
        array_cube = new Character[18][][][];
        index_array = 0;
        System.out.println( final_cube[0][0][0] );
        down( true );
        /* down( true );
        rigth( true );
        left( true );
        rigthTurn( true );
        leftTurn( true ); */

        System.out.println( array_cube[0][0][0][0] );
        System.out.println( final_cube[0][0][0] );

        return array_cube;
    }

    private void up( boolean save ){
        //Frontal arriba
        Character[][][] aux_cube = final_cube;
        for(int j=0; j<3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[0][i][j] = final_cube[1][i][j];
                aux_cube[1][i][j] = final_cube[5][i][j];
                aux_cube[5][i][j] = final_cube[3][i][j];
                aux_cube[3][i][j] = final_cube[0][i][j];
            }
            if( j == 0 ) rotarMatrix( aux_cube[4] );
            if( j == 2 ) rotarMatrix( aux_cube[2] );

            if( save ) {
                array_cube[index_array] = aux_cube;
                index_array++;
            }
        }
        if( save ) getBack( "up" );
    }

    private void down( boolean save ){
        Character[][][] aux_cube = final_cube;
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[0][i][j] = final_cube[3][i][j];
                aux_cube[1][i][j] = final_cube[0][i][j];
                aux_cube[5][i][j] = final_cube[1][i][j];
                aux_cube[3][i][j] = final_cube[5][i][j];
            }
            if( j == 0 ) rotarMatrix( aux_cube[4] );
            if( j == 2 ) rotarMatrix( aux_cube[2] );

            if( save ) {
                array_cube[index_array] = aux_cube;
                index_array++;
            }
        }
        if( save ) getBack( "down" );
    }

    private void rigth( boolean save ){
        //frontal derecha
        Character[][][] aux_cube = final_cube;
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[1][j][i] = final_cube[4][j][i];
                aux_cube[2][j][i] = final_cube[1][j][i];
                aux_cube[3][j][i] = final_cube[2][j][i];
                aux_cube[4][j][i] = final_cube[3][j][i];
            }

            if( j == 0 ) rotarMatrix( aux_cube[0] );
            if( j == 2 ) rotarMatrix( aux_cube[5] );

            if( save ) {
                array_cube[index_array] = aux_cube;
                index_array++;
            }
        }
        if( save ) getBack( "rigth" );
    }

    private void left( boolean save ){
        //Frontal izquierda
        Character[][][] aux_cube = final_cube;
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[1][j][i] = final_cube[2][j][i];
                aux_cube[2][j][i] = final_cube[3][j][i];
                aux_cube[3][j][i] = final_cube[4][j][i];
                aux_cube[4][j][i] = final_cube[1][j][i];
            }
            if( j == 0 ) rotarMatrix( aux_cube[0] );
            if( j == 2 ) rotarMatrix( aux_cube[5] );

            if( save ) {
                array_cube[index_array] = aux_cube;
                index_array++;
            }
        }
        if( save ) getBack( "left" );
    }

    private void rigthTurn( boolean save ){
        //Frontal girar derecha
        Character[][][] aux_cube = final_cube;
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[0][j][i] = final_cube[4][2-i][j];
                aux_cube[2][i][2-j] = final_cube[0][j][i];
                aux_cube[5][2-j][2-i] = final_cube[2][i][2-j];
                aux_cube[4][2-i][j] = final_cube[5][2-j][2-i];
            }
            
            if( j == 0 ) rotarMatrix( aux_cube[3] );
            if( j == 2 ) rotarMatrix( aux_cube[1] );

            if( save ) {
                array_cube[index_array] = aux_cube;
                index_array++;
            }
        }
        if( save ) getBack( "rigthTurn" );
    }

    private void leftTurn( boolean save ){
        //Frontal girar izquierda
        Character[][][] aux_cube = final_cube;
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[0][j][i] = final_cube[2][i][2-j];
                aux_cube[2][i][2-j] = final_cube[5][2-j][2-i];
                aux_cube[5][2-j][2-i] = final_cube[4][2-i][j];
                aux_cube[4][2-i][j] = final_cube[0][j][i];
            }
            
            if( j == 0 ) rotarMatrix( aux_cube[3] );
            if( j == 2 ) rotarMatrix( aux_cube[1] );

            if( save ) {
                array_cube[index_array] = aux_cube;
                index_array++;
            }
        }
        if( save ) getBack( "leftTurn" );
    }

    //Con dirección
    private void rotarMatrix(Character[][] matrix){

    }

    private void getBack( String action ){
        if( action.equals( "up" ) ) down( false );
        else if( action.equals( "down" ) ) up( false );
        else if( action.equals( "rigth" ) ) left( false );
        else if( action.equals( "left" ) ) rigth( false );
        else if( action.equals( "leftTurn" ) ) rigthTurn( false );
        else if( action.equals( "rigthTurn" ) ) leftTurn( false );
        else System.out.println( action );
    }

}



/*Character[][][] aux_cube = final_cube;

        int cont = 0;

        //Frontal arriba
        for(int j=0; j<3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[0][i][j] = final_cube[1][i][j];
                aux_cube[1][i][j] = final_cube[5][i][j];
                aux_cube[5][i][j] = final_cube[3][i][j];
                aux_cube[3][i][j] = final_cube[0][i][j];
            }
            for (int i = 0; i < 3; i++) 
                aux_cube[1][i][j] = final_cube[5][i][j];
            for (int i = 0; i < 3; i++) 
                aux_cube[5][i][j] = final_cube[3][i][j];
            for (int i = 0; i < 3; i++) 
                aux_cube[3][i][j] = final_cube[0][i][j];
            if( j == 0 )
                rotarMatrix( aux_cube[4] );
            if( j == 2 )
                rotarMatrix( aux_cube[2] );
            array_cube[cont] = aux_cube;
            cont++;
        }

        //Frontal abajo
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[0][i][j] = final_cube[3][i][j];
                aux_cube[1][i][j] = final_cube[0][i][j];
                aux_cube[5][i][j] = final_cube[1][i][j];
                aux_cube[3][i][j] = final_cube[5][i][j];
            }
            for (int i = 0; i < 3; i++) 
                aux_cube[1][i][j] = final_cube[0][i][j];
            for (int i = 0; i < 3; i++) 
                aux_cube[5][i][j] = final_cube[1][i][j];
            for (int i = 0; i < 3; i++) 
                aux_cube[3][i][j] = final_cube[5][i][j];
            if( j == 0 )
                rotarMatrix( aux_cube[4] );
            if( j == 2 )
                rotarMatrix( aux_cube[2] );
            array_cube[cont] = aux_cube;
            cont++;
        }

        //frontal derecha
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[1][j][i] = final_cube[4][j][i];
                aux_cube[2][j][i] = final_cube[1][j][i];
                aux_cube[3][j][i] = final_cube[2][j][i];
                aux_cube[4][j][i] = final_cube[3][j][i];
            }
            for (int i = 0; i < 3; i++) 
                aux_cube[2][j][i] = final_cube[1][j][i];
            for (int i = 0; i < 3; i++) 
                aux_cube[3][j][i] = final_cube[2][j][i];
            for (int i = 0; i < 3; i++) 
                aux_cube[4][j][i] = final_cube[3][j][i];
            if( j == 0 )
                rotarMatrix( aux_cube[0] );
            if( j == 2 )
                rotarMatrix( aux_cube[5] );
            array_cube[cont] = aux_cube;
            cont++;
        }

        //Frontal izquierda
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[1][j][i] = final_cube[2][j][i];
                aux_cube[2][j][i] = final_cube[3][j][i];
                aux_cube[3][j][i] = final_cube[4][j][i];
                aux_cube[4][j][i] = final_cube[1][j][i];
            }
            for (int i = 0; i < 3; i++) 
                aux_cube[2][j][i] = final_cube[3][j][i];
            for (int i = 0; i < 3; i++) 
                aux_cube[3][j][i] = final_cube[4][j][i];
            for (int i = 0; i < 3; i++) 
                aux_cube[4][j][i] = final_cube[1][j][i];
            if( j == 0 )
                rotarMatrix( aux_cube[0] );
            if( j == 2 )
                rotarMatrix( aux_cube[5] );
            array_cube[cont] = aux_cube;
            cont++;
        }

        //Frontal girar derecha
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[0][j][i] = final_cube[4][2-i][j];
                aux_cube[2][i][2-j] = final_cube[0][j][i];
                aux_cube[5][2-j][2-i] = final_cube[2][i][2-j];
                aux_cube[4][2-i][j] = final_cube[5][2-j][2-i];
            }
            for (int i = 0; i < 3; i++) 
                aux_cube[2][i][2-j] = final_cube[0][j][i];
            for (int i = 0; i < 3; i++) 
                aux_cube[5][2-j][2-i] = final_cube[2][i][2-j];
            for (int i = 0; i < 3; i++) 
                aux_cube[4][2-i][j] = final_cube[5][2-j][2-i];
            if( j == 0 )
                rotarMatrix( aux_cube[3] );
            if( j == 2 )
                rotarMatrix( aux_cube[1] );
            array_cube[cont] = aux_cube;
            cont++;
        }

        //Frontal girar izquierda
        for(int j = 0; j < 3; j++){
            aux_cube = final_cube;
            for (int i = 0; i < 3; i++) {
                aux_cube[0][j][i] = final_cube[2][i][2-j];
                aux_cube[2][i][2-j] = final_cube[5][2-j][2-i];
                aux_cube[5][2-j][2-i] = final_cube[4][2-i][j];
                aux_cube[4][2-i][j] = final_cube[0][j][i];
            }
            for (int i = 0; i < 3; i++) 
                aux_cube[2][i][2-j] = final_cube[5][2-j][2-i];
            for (int i = 0; i < 3; i++) 
                aux_cube[5][2-j][2-i] = final_cube[4][2-i][j];
            for (int i = 0; i < 3; i++) 
                aux_cube[4][2-i][j] = final_cube[0][j][i];
            if( j == 0 )
                rotarMatrix( aux_cube[3] );
            if( j == 2 )
                rotarMatrix( aux_cube[1] );
            array_cube[cont] = aux_cube;
            cont++;
        } */