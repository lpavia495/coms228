package edu.iastate.cs228.hw1;

/**
 * 
 * @author Luke Pavia
 *	Also provide appropriate comments for this class
 *	
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		//Sets all counts to be zero initially
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 

		//TODO: Write your code here.
		int plainWidth = plain.getWidth();
		int plainLength = plain.getLength();
		
		//Double for loop to iterate through neighboring cells and count amount of each cell type
		for (int i = row - 1; i < row + 2; i++) {
			for (int j = col - 1; j < col + 2; j++) {

				if ((j != col || i != row) && i > -1 && i < plainLength && j > -1 && j < plainWidth) {
					if(plain.grid[i][j].who() == State.CASUAL) {
						nCensus[CASUAL] +=1;
					}
					if(plain.grid[i][j].who() == State.EMPTY) {
						nCensus[EMPTY] +=1;
					}
					if(plain.grid[i][j].who() == State.RESELLER) {
						nCensus[RESELLER] +=1;
					}
					if(plain.grid[i][j].who() == State.STREAMER) {
						nCensus[STREAMER] +=1;
					}				
					if(plain.grid[i][j].who() == State.OUTAGE) {
						nCensus[OUTAGE] +=1;
					}
				}
			}

		}
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}
