 package edu.iastate.cs228.hw2;

/**
 *  
 * @author Luke Pavia
 *
 */

 public class Point implements Comparable<Point>
 {
 	private int x; 
 	private int y;
 	
 	public static boolean xORy;  // compare x coordinates if xORy == true and y coordinates otherwise 
 	                             // To set its value, use Point.xORy = true or false. 
 	
 	public Point()  // default constructor
 	{
 		// x and y get default value 0

 	}

 	public Point(int x, int y)
 	{
 		this.x = x;
 		this.y = y;
 	}

 	
 	public Point(Point p) { // copy constructor
 		x = p.getX();
 		y = p.getY();
 	}

 	public int getX()   
 	{
 		return x;
 	}
 	
 	public int getY()
 	{
 		return y;
 	}
 	
 	/** 
 	 * Set the value of the static instance variable xORy. 
 	 * @param xORy
 	 */
 	public static void setXorY(boolean xORy)
 	{
 		// TODO
 		// TEST
 		Point.xORy = xORy;
 	}
 	
 	
 	@Override
 	public boolean equals(Object obj)
 	{
 		if (obj == null || obj.getClass() != this.getClass())
 		{
 			return false;
 		}
     
 		Point otherPoint = (Point) obj;
 		return x == otherPoint.x && y == otherPoint.y;
 	}

 	/**
 	 * Compare this point with a second point q depending on the value of the static variable xORy 
 	 * @param 	q 
 	 * @return  -1  if (xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y))) 
 	 *                || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x)))
 	 * 		    0   if this.x == q.x && this.y == q.y)  
 	 * 			1	otherwise 
 	 */
 	public int compareTo(Point q)
 	{
 		// TODO
 		// TEST

 		if ((xORy == true && (this.x == q.x && this.y < q.y || (this.x < q.x))) || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x))))
 		{
 			return -1;
 		}
 		else if(this.x == q.x && this.y == q.y)
 		{
 			return 0;
 		}
 		else
 		{
 			return 1;
 		}


 	}
 	
 	
 	/**
 	 * Output a point in the standard form (x, y). 
 	 */
 	@Override
     public String toString() 
 	{
 		// TODO
 		// TEST
 		return "(" + x + ", " + y + ")";

 	}
 }
