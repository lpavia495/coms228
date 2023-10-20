package edu.iastate.cs228.hw2;

/**
 *  
 * @author Luke Pavia
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.iastate.cs228.hw2.Algorithm;
import edu.iastate.cs228.hw2.Point;
import edu.iastate.cs228.hw2.PointScanner;

import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// TODO 
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	



		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		
		int choice = 0;
		int trial = 1;

		PointScanner[] scanners = new PointScanner[4];
		while(choice != 3)
		{
			System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");

			choice = scan.nextInt();

			if(choice == 1)
			{
				System.out.println("Trial " + trial + ": " + choice);
				System.out.println("Enter number of random points: ");
				int randNumPoints = scan.nextInt();
				
				scanners[0] = new PointScanner(generateRandomPoints(randNumPoints,rand), Algorithm.SelectionSort);
				scanners[1] = new PointScanner(generateRandomPoints(randNumPoints,rand), Algorithm.InsertionSort);
				scanners[2] = new PointScanner(generateRandomPoints(randNumPoints,rand), Algorithm.MergeSort);		
				scanners[3] = new PointScanner(generateRandomPoints(randNumPoints,rand), Algorithm.QuickSort);



				System.out.println("algorithm    size  time (ns)");
				System.out.println("-----------------------------------");
				for(int i = 0; i < scanners.length; i++)
				{
					scanners[i].scan();
					System.out.println(scanners[i].stats());
				}
				System.out.println("-----------------------------------");
				
				trial++;
			}
			if(choice == 2)
			{

				System.out.println("Trial " + trial + ": " + choice);
				System.out.println("Points From a File");
				System.out.println("File Name: ");
				String fileString = scan.next();
				
				scanners[0] = new PointScanner(fileString, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileString, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileString, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileString, Algorithm.QuickSort);
				
				
				



				System.out.println("algorithm    size  time (ns)");
				System.out.println("-----------------------------------");
				for(int i = 0; i < scanners.length; i++)
				{
					scanners[i].scan();
					System.out.println(scanners[i].stats());
				}
				System.out.println("-----------------------------------");
			
				
				scanners[2].writeMCPToFile();

				trial++;

			}

		}

		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description.



		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] x [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{

		// TODO
		// TEST

		if(numPts < 1) {
			throw new IllegalArgumentException();
		}

		Point[] randPoints = new Point[numPts];
		int x;
		int y;

		for(int i = 0; i < numPts; i++)
		{
			x = rand.nextInt(101) - 50;
			y = rand.nextInt(101) - 50;
			
			Point p = new Point(x, y);
			randPoints[i] = p;
		}
		return randPoints;

	}
}


