package edu.iastate.cs228.hw1;

public class Streamer extends TownCell {
    public Streamer(Town p, int r, int c) {
        super(p, r, c);
    }

  //Returns the state of the cell
    @Override
    public State who() {
        return State.STREAMER;
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
        else if(nCensus[OUTAGE] > 0)
        {
            return new Empty(tNew,row,col);
        }

        return new Streamer(tNew,row,col);
    }
}
