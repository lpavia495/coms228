package edu.iastate.cs228.hw1;

public class Empty extends TownCell{
    public Empty(Town p, int r, int c) {
        super(p, r, c);
    }

    //Returns the state of the cell
    @Override
    public State who() {
        return State.EMPTY;
    }

  //Returns new state based off rules
    @Override
    public TownCell next(Town tNew) {

        int[] nCensus = new int[5];
        census(nCensus);

        if(nCensus[EMPTY] + nCensus[OUTAGE] < 2){
            return new Reseller(tNew,row,col);
        }
        return new Casual(tNew,row,col);
    }
}
