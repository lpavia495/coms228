package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Luke Pavia
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		// TODO 
		super(pts);
		super.algorithm = "selection sort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		for(int i = 0; i < points.length; i++)
		{
			//Smallest value by default at i[0]
			int minNum = i;
			//Double for loop comparing the two values to see if the current index is less than the min.
			for(int j = i + 1; j < points.length; j++)
			{
				//Compares current smallest point to the Point at points[j]
				if(points[j].compareTo(points[minNum]) < 0)
				{
					minNum = j;
				}
			}
			//swaps old smallest point with the new one
			swap(i, minNum);
		}
	}	
	}
