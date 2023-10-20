package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Luke Pavia
 *
 */
public class Town {

	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;


	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int width, int length) {

		//@param length and @param width to create variables
		this.length = width;
		this.width = length;

		//Using @param length and @param width to create a new grid
		grid = new TownCell[width][length];

	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {

		//Creates a new File with @param inputFileName
		File file = new File(inputFileName);
		//Try catch statement in case FileNotFoundException
		try {
			Scanner scan = new Scanner(file);
			this.width = scan.nextInt();
			this.length = scan.nextInt();
			grid = new TownCell[width][length];
			//while the file still has new lines
			while(scan.hasNextLine())
			{
				//Double for loop for the grid sets state on grid
				for(int i = 0; i < width; i++)
				{
					for(int j = 0; j < length; j++)
					{
						String s = scan.next();
						switch(s) {
							case "C":
								grid[i][j] = new Casual(this, i, j);
								break;

							case "S":
								grid[i][j] = new Streamer(this,i,j);
								break;

							case "R":
								grid[i][j] = new Reseller(this, i, j);
								break;

							case "E":
								grid[i][j] = new Empty(this,i,j);
								break;

							case "O":
								grid[i][j] = new Outage(this,i,j);
								break;
						}
					}
				}
			}

		}
		
		catch(FileNotFoundException e) {
			System.out.println("Invalid file path");
		}


	}

	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		//TODO: Write your code here.
		//Double for loop and if statements to place correct state on grid 
		for(int i = 0; i<width; i++) {
			for(int j = 0; j<length; j++) {
				int num = rand.nextInt(5);
				if(num == TownCell.RESELLER) {
					 grid[i][j] = new Reseller(this, i, j);
					 
				}
				if(num == TownCell.EMPTY) {
					grid[i][j] = new Empty(this, i, j);
					
				}
				if(num == TownCell.CASUAL) {
					grid[i][j] = new Casual(this, i, j);
					
				}
				if(num == TownCell.OUTAGE) {
					grid[i][j] = new Outage(this, i, j);
					
				}
				if(num == TownCell.STREAMER) {
					grid[i][j] = new Streamer(this, i, j);
					
				}
			}
		}


	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		//nested for loop to print the first character of each cell
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < length; y++)
			{
				State temp = grid[x][y].who();
				s += temp.toString().charAt(0) + " ";
			}
			System.out.println();

		}


		return s;
	}

}
