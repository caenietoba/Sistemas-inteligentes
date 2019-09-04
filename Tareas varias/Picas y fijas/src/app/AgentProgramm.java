package app;

import java.util.ArrayList;
import java.util.Iterator;

public class AgentProgramm{

    private Integer NUMS_GAME; 
    protected String last_number = "";
    protected boolean started = false;
    protected ArrayList<String> values = new ArrayList<String>();

    public AgentProgramm( int NUMS_GAME ){
        super();
        this.NUMS_GAME = NUMS_GAME;
    }

    /**
     * Principal function of the agent, it contains all the logic of how
     * the agent select the next number in order to ask if the number 
     * match
     * @param spades
     * @param fixed
     * @return
     */
    public String operation( int spades, int fixed ){
        
        if( !started )  {
            fillFirstArray();
            started = true;
        }else if( fixed == 4 ) return "finish";
        else reduceArray( spades, fixed );
        last_number = getSelection();
        return last_number;
    }

    /** 
     * Reduce the array of values depending of how many matchs exists 
     * in the last number selected and the amount of fixed and spades of that
     * number
     * @param spades
     * @param fixed
    */
    private void reduceArray( int spades, int fixed ){
        Iterator<String> it = values.iterator();
        while( it.hasNext() ){
            String value = it.next();
            int spades_value = 0;
            int fixed_value = 0;
            for( int i=0; i<NUMS_GAME; i++ ){
                if( last_number.charAt( i ) == ( value.charAt( i ) )) fixed_value++;
                else if( value.indexOf( last_number.charAt( i ) ) >= 0 ) spades_value++;
            }
            if( spades_value != spades || fixed_value != fixed ) it.remove();
        }
    }

    /**
     * Get the value in the middle of the array, it can be selected in other forms
     * to improve the perform if selection
     * @return The value in the middle of the array
     */
    private String getSelection(){
        return values.get( values.size() / 2 );
    }

    /**
     * Fill the value ArrayList with the 5040 posibilities of 4 numbers without 
     * repeating numbers
     */
    protected void fillFirstArray(){
        for( int i=0; i<10; i++ )
            for( int j=0; j<10; j++ )
                for( int k=0; k<10; k++ )
                    for( int l=0; l<10; l++ )
                        if( i != j && j != k && k != l && l != i && j != l && k != i)  
                            values.add( Integer.toString( i ) + Integer.toString( j ) + Integer.toString( k ) + Integer.toString( l ) );
    }

}