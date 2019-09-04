package app;

import java.util.Scanner;

public class Environment {

    private final Integer NUMS_GAME = 4;
    private int spades = 0, fixed = 0;

    public void run(){
        AgentProgramm agent = new AgentProgramm( NUMS_GAME );

        String in = "", out = "";

        in = getInput();

        while( !out.equals( "finish" ) ){
            out = agent.operation( spades, fixed );
            findMacths( in, out );
            print( out, spades, fixed );
        }
    }

    private String getInput(){
        Scanner sc = new Scanner( System.in );
        String in = "";
        boolean isDifferent = true;

        do{
            System.out.print( "Digite un número de 4 digitos, todos deben ser diferentes: " );
            in = sc.nextLine();
            isDifferent = true;
            for( int i=0; i<NUMS_GAME; i++ )
                for( int j=i+1; j<NUMS_GAME; j++ )
                    if( in.charAt( i ) == in.charAt( j ) )  isDifferent = false;
        } while( !isDifferent );

        sc.close();
        return in;
    }

    private void findMacths( String in, String out ){
        fixed = 0;
        spades = 0;
        for( int i=0; i<NUMS_GAME; i++ ){
            if( in.charAt( i ) == ( out.charAt( i ) )) fixed++;
            else if( out.indexOf( in.charAt( i ) ) >= 0 ) spades++;
        }
    }

    private void print( String out, int spades, int fixed ){
        if( out.equals( "finish" ) ) System.out.println( "...ENDED...");
        else System.out.println( "The number " + out + " has " + spades + " spades and " + fixed + " fixed.");
    }
    
}