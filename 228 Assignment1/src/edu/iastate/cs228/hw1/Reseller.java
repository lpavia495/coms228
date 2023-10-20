package edu.iastate.cs228.hw1;

public class Reseller extends TownCell{

    public Reseller(Town p, int r, int c) {
        super(p, r, c);
    }
  //Returns the state of the cell
    @Override
    public State who() {
        return State.RESELLER;
    }

  //Returns new state based off rules
    @Override
    public TownCell next(Town tNew) {
        int[] nCensus = new int[5];
        census(nCensus);
        
		if( nCensus[EMPTY] > 2 || nCensus[CASUAL] < 4) {
			return new Empty(tNew, row, col);
		}
		if(nCensus[CASUAL] > 4)
	      {
	            return new Streamer(tNew,row,col);
	      }
	      

	    return new Reseller(tNew,row,col);
	}
}
