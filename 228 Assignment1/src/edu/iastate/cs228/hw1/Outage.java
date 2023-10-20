package edu.iastate.cs228.hw1;

public class Outage extends TownCell {
    public Outage(Town p, int r, int c) {
        super(p, r, c);
    }

  //Returns the state of the cell
    @Override
    public State who() {
        return State.OUTAGE;
    }

  //Returns new state based off rules
    @Override
    public TownCell next(Town tNew) {

        return new Empty(tNew,row,col);
    }
}
