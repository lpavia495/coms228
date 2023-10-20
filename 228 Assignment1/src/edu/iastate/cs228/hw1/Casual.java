package edu.iastate.cs228.hw1;

import java.net.SocketOption;
import java.util.stream.Stream;

public class Casual extends TownCell{
    public Casual(Town p, int r, int c) {
        super(p, r, c);
    }

    //Returns the state of the cell
    @Override
    public State who() {
        return State.CASUAL;
    }

    //Returns new state based off rules
    @Override
    public TownCell next(Town tNew) {

        int[] nCensus = new int[5];
        census(nCensus);

        if(nCensus[OUTAGE] + nCensus[EMPTY] < 2){
            return new Reseller(tNew,row,col);
        }
        else if(nCensus[RESELLER] > 0)
        {
            return new Outage(tNew,row,col);
        }
        else if(nCensus[STREAMER] > 0 || nCensus[CASUAL] > 4)
        {
            return new Streamer(tNew,row,col);
        }
        else
        {
            return new Casual(tNew,row,col);
        }



    }
}
