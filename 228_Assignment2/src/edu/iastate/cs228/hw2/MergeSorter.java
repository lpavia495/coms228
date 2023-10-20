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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		// TODO
		super(pts);
		super.algorithm = "mergesort";

	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		// TODO
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int len = pts.length;
		int m = len / 2;
		if(len <= 1)
		{
			return;
		}
		
		Point[] leftPoints = new Point[m];
		Point[] rightPoints = new Point[len - m];
		int j = 0;

		for(int i = 0; i < leftPoints.length; i++)
		{
			leftPoints[i] = pts[j];
			j++;
		}

		for(int i = 0; i < rightPoints.length; i++)
		{
			rightPoints[i] = pts[j];
			j++;
		}

		mergeSortRec(leftPoints);
		mergeSortRec(rightPoints);
		merge(pts, leftPoints, rightPoints);
	}

	
	// Other private methods if needed ...

	/**
	 *
	 * @param pts
	 * @param leftPoints
	 * @param rightPoints
	 *
	 * merges arrays together
	 */

	private void merge(Point[] pts, Point[] leftPoints, Point[] rightPoints)
	{
		int left = leftPoints.length;
		int right = rightPoints.length;
		int i = 0; 
		int j = 0; 
		int k = 0; 
		//Ends when i and j go through each element in either the left and right.
		while(i < left && j < right)
		{
			//If left point is smaller than right point
			if(leftPoints[i].compareTo(rightPoints[j]) < 0)
			{
				pts[k] = leftPoints[i];
				i++;
				k++;
			}
			//Else when right point is bigger than left point
			else
			{
				pts[k] = rightPoints[j];
				j++;
				k++;
			}
		}

		while(j < right)
		{
			pts[k] = rightPoints[j];
			j++;
			k++;
		}

		while(i < left)
		{
			pts[k] = leftPoints[i];
			i++;
			k++;
		}
	}
}
