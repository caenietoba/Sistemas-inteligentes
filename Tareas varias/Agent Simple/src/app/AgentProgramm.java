package app;

import java.util.Random;

public class AgentProgramm {

    protected Random r = new Random();

    protected boolean started = false;
    protected boolean history[] = new boolean[10];

    public String func( String in ){
        
        String out;
        
        if( !started ){
            out = Integer.toString( genRandom() );
            started = true;
        }else if( in.equals("yes") )
            out = "break";
        else
            out = Integer.toString( genRandom() );

        return out;
    }

    protected Integer genRandom(){
        int rnd = r.nextInt(10);
        while( history[rnd] )
            rnd = r.nextInt(10);
        history[rnd] = true;
        return rnd;
    }
}