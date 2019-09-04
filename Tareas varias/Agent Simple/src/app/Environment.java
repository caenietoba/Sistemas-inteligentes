package app;

import java.util.Scanner;

public class Environment{

    AgentProgramm agent = new AgentProgramm();

    public void run(){
        Scanner sc = new Scanner(System.in);
        String in = "no";
        String out = agent.func( in );

        while( !out.equals( "break" ) ){
            System.out.print( out + " es ese tu número (yes/no)?: " );
            in = sc.nextLine(); 
            out = agent.func( in );
            print( out );
        }
        sc.close();
    }

    protected void print( String in ){
        if( in.equals( "break" ) ) System.out.println( "\n\n ...FIN..." );
        //if( in.equals( "break" ) ) System.out.println( "El número " + in + " no es el que estoy pensando." );
        //else System.out.println( "El número " + in + " es el que estoy pensando. \n\n ...FIN..." );
    }

}