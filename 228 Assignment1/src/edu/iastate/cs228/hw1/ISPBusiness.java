package edu.iastate.cs228.hw1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Luke Pavia
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		//TODO: Write your code here.
		//double for loop to loop through the old town and update it in a new town
		for(int i = 0; i < tOld.getWidth(); i++)
		{
			for(int j = 0; j < tOld.getLength(); j++)
			{
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		//TODO: Write/update your code here.
		int money = 0;
		//double for loop to find amount of casual cells

		for(int i = 0; i < town.getWidth(); i++) {
			for (int j = 0; j < town.getLength(); j++) {
					if(town.grid[i][j].who() == State.CASUAL) {
					
						money+=1;
					}
			}
		}


		return money;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		//TODO: Write your code here.
		Town t = null;
		File f;
		Scanner scan = new Scanner(System.in);
		double profit = 0.0;
		//Prompt user whether they want to load a file or generate a seed
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");
		int choice = scan.nextInt();
		
		if (choice == 1) {
			String filePath = "";
			try {

				//ask for file path and calls Town constructor with file updating
				System.out.println("Please enter file path:");
				scan.nextLine();
				filePath = scan.nextLine();

				f = new File(filePath);
				t = new Town(filePath);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Invalid file path");
			}
		}

		if(choice == 2)
		{
			//gets amount of rows cols and seed and also calls randomInit
			
			System.out.println("Enter Rows, Cols and the SEED! seperated by a space");
			int row = scan.nextInt();
			int col = scan.nextInt();
			int seed = scan.nextInt();
			
			t = new Town(row,col);
			t.randomInit(seed);

		}

		//For loop to get the profit and update monthly
		for(int k = 0; k < 12; k++) {
			profit += (getProfit(t) / ((double)t.getWidth() * (double)t.getLength())) * 100;
			t = updatePlain(t);
		}
		profit = profit / 12;
		String finalProfit = String.format("%.2f", profit);
		System.out.println(finalProfit+"%");;

	}
}
