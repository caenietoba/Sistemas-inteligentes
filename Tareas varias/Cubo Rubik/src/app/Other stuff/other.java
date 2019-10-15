/* private void up( boolean save ){
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

private void getBack( String action ){
    if( action.equals( "up" ) ) down( false );
    else if( action.equals( "down" ) ) up( false );
    else if( action.equals( "rigth" ) ) left( false );
    else if( action.equals( "left" ) ) rigth( false );
    else if( action.equals( "leftTurn" ) ) rigthTurn( false );
    else if( action.equals( "rigthTurn" ) ) leftTurn( false );
    else System.out.println( action );
} */