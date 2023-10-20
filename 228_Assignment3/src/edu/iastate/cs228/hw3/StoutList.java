package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;







/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  public StoutList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public StoutList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  public StoutList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  @Override
  public int size()
  {
    // TODO Auto-generated method stub
    return size;
  }
  
  @Override
  public boolean add(E item)
  {
    // TODO Auto-generated method stub
	  //If item null throws NullPointerException
	  if (item == null) {
			throw new NullPointerException();
		}
	  Node node = new Node();
		node = tail;
		if(head.next == tail)
		{
			Node x = new Node();
			x.addItem(item);
			tail.previous.next = x;
			x.next = tail;
			x.previous = tail.previous;
			tail.previous = x;
		}
		else if(node.previous.count < nodeSize && node.previous != head)
		{
			node.previous.addItem(item);
		}
		else if(node == tail && node.previous.count == nodeSize)
		{
			Node x = new Node();
			x.addItem(item);
			tail.previous.next = x;
			x.next = tail;
			x.previous = tail.previous;
			tail.previous = x;
		}
		else if(node.count < nodeSize)
		{
			node.addItem(item);
		}
		size++;
		return true;

  }

  @Override
  public void add(int pos, E item)
  {
    // TODO Auto-generated method stub
	// if pos is not in bounds then throw IndexOutOfBoundsException
				if (!(pos > 0 || pos < size)) {
					throw new IndexOutOfBoundsException();
				}
				// if the list is empty, create a new node and put X at offset 0
				if (head.next == tail) {
					add(item);
				}

				NodeInfo info = find(pos);
				Node temp = info.node;
				int offset = info.offset;

				// otherwise if off = 0 and one of the following two cases occurs, 
				if (offset == 0) {
					//if n has a predecessor which has fewer than M elements (and is not the head), put X in n’s
					//predecessor
					if (temp.previous.count < nodeSize && temp.previous != head) {
						temp.previous.addItem(item);
						size++;
						
					}
					//if n is the tail node and n’s predecessor has M elements, create a new node and put X at offset 0
					
					else if (temp == tail) {
						add(item);
						size++;
						
					}
					return;
				}
				//Otherwise if there is space in node n, put X in node n at offset off, 
				//shifting array elements as necessary
				if (temp.count < nodeSize) {
					temp.addItem(offset, item);
				}
				//otherwise, perform a split operation: move the last M/2 elements of node n
				//into a new successor node n', and then
				else {
					int count = 0;
					
					Node newSuc = new Node();
					int halfPoint = nodeSize / 2;
					
					while (count < halfPoint) {
						newSuc.addItem(temp.data[halfPoint]);
						temp.removeItem(halfPoint);
						count++;
					}
					Node oldSuc = temp.next;

					temp.next = newSuc;
					newSuc.previous = temp;
					newSuc.next = oldSuc;
					oldSuc.previous = newSuc;

					// if off <= M/2 , put X in node n' at offset off
					if (offset <= nodeSize / 2) {
						temp.addItem(offset, item);
					}
					// if off > M/2, put X in node n' at offset (off - M/2)
					if (offset > nodeSize / 2) {
						newSuc.addItem((offset - nodeSize / 2), item);
					}

				}
				//New item has been added to list so we need to increase size
				size++;
  }

  @Override
  public E remove(int pos)
  {
    // TODO Auto-generated method stub
	  NodeInfo nodeInfo = find(pos);
	  Node temp = nodeInfo.node;
	  int offset = nodeInfo.offset;
	  E nodeValue = temp.data[offset];
	  Node predecessor;
	  Node suc;
		
	  if (!(pos > 0 || pos < size)) {
		throw new IndexOutOfBoundsException();
	  }

		// if the node n containing X is the last node and has only one element, delete it;
		if (temp.next == tail && temp.count == 1) {
			predecessor = temp.previous;
			predecessor.next = temp.next;
			temp.next.previous = predecessor;
			temp = null;
		}
		// otherwise, if n is the last node (thus with two or more elements) , or if n has more than M/2
		// elements, remove X from n, shifting elements as necessary;
		else if (temp.next == tail || temp.count > (nodeSize / 2)) {
			temp.removeItem(offset);
		}
		//otherwise (the node n must have at most M/2 elements), look at its successor n' (note that we don’t
		//look at the predecessor of n) and perform a merge operation as follows:
		else {
			temp.removeItem(offset);
			suc = temp.next;
			
			//if the successor node n' has more than   elements, move the first element from n' to n.
			//(mini-merge)
			if (suc.count > (nodeSize / 2)) {
				temp.addItem(suc.data[0]);
				suc.removeItem(0);
			}
			//if the successor node n' has M/2 or fewer elements, then move all elements from n' to n and
			//delete n' (full merge) 
			else if (suc.count <= nodeSize / 2) {
				for (int i = 0; i < suc.count; i++) {
					temp.addItem(suc.data[i]);
				}
				temp.next = suc.next;
				suc.next.previous = temp;
				suc = null;
			}
		}
		//Item has been removed from the list so we need to decrease size
		size--;
		return nodeValue;

  }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort()
  {
	  // TODO 
	  class GenericComp<E extends Comparable<? super E>> implements Comparator<E> 
		{
			public int compare(E a, E b) 
			{
				return a.compareTo(b);
			}
		}
	  
	  
	  E[] list = (E[]) new Comparable[size];
		Node node = new Node();
		node = head.next;
		int eLeft = size;
		int count = 0;
		
		//Traverse the list and copy its elements into an array.
		//And Destroy all storage nodes in the list during the traversal.
		for(int i = 0; i < eLeft; i++)
		{
			if(i == nodeSize)
			{
				eLeft -= node.count;
				size -= node.count;
				node = node.next;
				node.previous.previous.next = node;
				node.previous = node.previous.previous;
				i = 0;
			}
			if(node.data[i] == null)
			{
				eLeft -= node.count;
				size -= node.count;
				node = node.next;
				node.previous.previous.next = node;
				node.previous = node.previous.previous;
				i = 0;
			}
			list[count] = node.data[i];
			count++;

		}
		size -= node.count;
		node = node.next;
		node.previous.previous.next = node;
		node.previous = node.previous.previous;

		
		GenericComp<E> comp = new GenericComp();
		
		//Sorts the array
		insertionSort(list, comp);
		node = head.next;
		//Add elements back from the sorted array.
		for(int i = 0; i < list.length; i++)
		{
			this.add(list[i]);
		}

  }
  
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() 
  {
	  // TODO 
	  E[] list = (E[]) new Comparable[size];
		int eLeft = size;
		
		int count = 0;
		
		Node node = new Node();
		node = head.next;
		
		for(int i = 0; i < eLeft; i++)
		{
			if(i == nodeSize)
			{
				eLeft -= node.count;
				size -= node.count;
				node = node.next;
				node.previous.previous.next = node;
				node.previous = node.previous.previous;
				i = 0;
			}
			if(node.data[i] == null)
			{
				eLeft -= node.count;
				size -= node.count;
				node = node.next;
				node.previous.previous.next = node;
				node.previous = node.previous.previous;
				i = 0;
			}
			list[count] = node.data[i];
			count++;

		}
		size -= node.count;
		node = node.next;
		node.previous.previous.next = node;
		node.previous = node.previous.previous;

		bubbleSort(list);
		
		node = head.next;
		for(int i = 0; i < list.length; i++)
		{
			this.add(list[i]);
		}

  }
  
  @Override
  public Iterator<E> iterator()
  {
    // TODO Auto-generated method stub
	  return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    // TODO Auto-generated method stub
	  return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index)
  {
    // TODO Auto-generated method stub
	  return new StoutListIterator(index);
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter) 
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }


  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node
  {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item)
    {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
      //useful for debugging
      //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item)
    {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      ++count;
      data[offset] = item;
      //useful for debugging 
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset)
    {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      --count;
    }    
  }
  private class NodeInfo {
		public Node node;
		public int offset;

		public NodeInfo(Node node, int offset) {
			this.node = node;
			this.offset = offset;
		}
	}

	/**
	 * Helper method to locate item
	 * 
	 * @param pos position of item to get info from
	 * @return NodeInfo of point on list
	 */
	private NodeInfo find(int pos) {
		Node temp = head.next;
		int currPos = 0;
		while (temp != tail) {
			if (currPos + temp.count <= pos) {
				currPos += temp.count;
				temp = temp.next;
				
			}else {
				NodeInfo nodeInfo = new NodeInfo(temp, pos - currPos);
				return nodeInfo;
			}
		}
		return null;
	}
 
  private class StoutListIterator implements ListIterator<E>
  {
	// constants you possibly use ...   
	  
	// instance variables ... 
	  
	 	private static final int BEHIND = -1; 
		private static final int FORWARD = 1; 
		private static final int NONE = 0; 

		private Node cursor; 
		private int index; 
		private int direction; 
		private int arrIndex;
		
		
		
	  
    /**
     * Default constructor 
     */
    public StoutListIterator()
    {
    	// TODO 
    	this(0);
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    public StoutListIterator(int pos)
    {
    	// TODO 
    	if (pos > size || pos < 0) 
		{
			throw new IndexOutOfBoundsException ("" + pos); 
		}
    	direction = NONE;
		cursor = NodeFromPos(pos); 
		index = pos; 
		
    }

    @Override
    public boolean hasNext()
    {
    	// TODO 
    	return index < size;
    }

    @Override
    public E next()
    {
    	// TODO 
    	if (!hasNext())  
			throw new NoSuchElementException(); 
		cursor = NodeFromPos(index);
		if (arrIndex + 1 >= nodeSize)
		{
			cursor = cursor.next;
			arrIndex = 0;
		}
		else
		{
			arrIndex += 1;
		}
		++index; 
		direction = BEHIND; 
		return cursor.data[arrIndex];

    }

    @Override
    public void remove()
    {
    	if(direction == FORWARD)
		{
			if(arrIndex + 1 >= nodeSize)
			{
				arrIndex = 0;
				cursor = cursor.next;
				
			}
			else
			{
				arrIndex += 1;
			}
		}
		else if(direction == BEHIND)
		{
			if(arrIndex - 1 < 0)
			{
				arrIndex = nodeSize - 1;
				cursor = cursor.previous;
			}
			else
			{
				arrIndex -= 1;
			}
		}
		else
		{
			throw new IllegalStateException(); 
		}
		if (cursor.next == tail && cursor.count <= 1)
		{
			cursor.previous.next = tail;
			tail.previous = cursor.previous;
			cursor = cursor.previous;
		}
		else if (cursor.next == tail || cursor.count > nodeSize / 2)
		{
			cursor.removeItem(arrIndex);
		}
		else if (cursor.count <= nodeSize / 2)
		{
			if(cursor.next.count > nodeSize / 2)
			{
				cursor.removeItem(arrIndex);
				cursor.addItem(cursor.next.data[0]);
				cursor.next.removeItem(0);
			}
			else if(cursor.next.count <= nodeSize / 2)
			{
				cursor.removeItem(arrIndex);
				for(int i = nodeSize / 2; i < nodeSize; i++)
				{
					cursor.addItem(cursor.next.data[i]);
				}
				cursor.previous.next = tail;
				tail.previous = cursor.previous;
				cursor = cursor.previous;
			}
		}
		if(direction == FORWARD)
		{
			arrIndex = arrIndex - 1;
		}
		size--;
		index--;
		direction = NONE; 

    }
    
    // Other methods you may want to add or override that could possibly facilitate 
    // other operations, for instance, addition, access to the previous element, etc.
    // 
    // ...
    // 
    
    /**
     * Method to find a node by its index 
     * @param pos
     */
    public Node NodeFromPos(int pos)
	{
		Node node = new Node();
		node = head.next;
		arrIndex = pos;
		while(arrIndex >= node.count)
		{
			arrIndex -= node.count;
			node = node.next;	
		}
		for(int i = 0; i <= arrIndex; i++)
		{
			if (i == arrIndex)
			{
				return node;
			}
		}
		return node;
	}
	@Override
	public boolean hasPrevious() {
		return index > 0;
	}

	@Override
	public E previous() {
		if (!hasPrevious())  
			throw new NoSuchElementException(); 
		cursor = NodeFromPos(index);
		if(arrIndex - 1 < 0)
		{
			cursor = cursor.previous;
			arrIndex = nodeSize - 1;
		}
		else
		{
			arrIndex -= 1;
		}
		--index; 
		direction = FORWARD; 
		return cursor.data[arrIndex];
	}

	@Override
	public int nextIndex() {
		return index;
	}

	@Override
	public int previousIndex() {
		return index - 1;
	}

	@Override
	public void set(E e) {
		if (direction == NONE) 
		{ 
			throw new IllegalStateException(); 
		} 

		if (direction == FORWARD) 
		{ 
			if(arrIndex == nodeSize - 1)
			{
				arrIndex = 0;
				cursor = cursor.next;
				cursor.data[arrIndex] = e;
			}
			else
			{
				cursor.data[arrIndex + 1] = e;
			}
		} 
		else 
		{ 
			if(arrIndex == 0)
			{
				arrIndex = nodeSize - 1;
				cursor = cursor.previous;
				cursor.data[arrIndex] = e;
			}
			else
			{
				cursor.data[arrIndex - 1] = e;
			}
		} 

	}

	@Override
	public void add(E e) {
		if(head.next == tail)
		{
			Node node = new Node();
			node.addItem(e);
			tail.previous.next = node;
			node.next = tail;
			node.previous = tail.previous;
			tail.previous = node;
			cursor = node;
		}
		else if(arrIndex == 0)
		{
			if(cursor.previous.count < nodeSize && cursor.previous != head)
			{
				cursor.previous.addItem(e);
			}
			if(cursor == tail && cursor.previous.count == nodeSize)
			{
				Node node = new Node();
				node.addItem(e);
				tail.previous.next = node;
				node.next = tail;
				node.previous = tail.previous;
				tail.previous = node;
				cursor = node;
			}
		}

		else if(cursor.count < nodeSize)
		{
			cursor.addItem(arrIndex, e);
		}
		else
		{
			Node node = new Node();
			for(int i = nodeSize / 2; i < nodeSize; i++)
			{
				node.addItem(cursor.data[i]);
			}
			for(int i = nodeSize / 2; i < nodeSize; i++)
			{
				cursor.removeItem(i);
			}
			cursor.next.previous = node;
			node.next = cursor.next;
			node.previous = cursor;
			cursor.next = node;
			if(arrIndex <= nodeSize / 2)
			{
				cursor.addItem(arrIndex, e);
			}
			else if (arrIndex > nodeSize / 2)
			{
				node.addItem(arrIndex - (nodeSize / 2), e);
			}
		}
		index++;
		direction = NONE;
	}

  }
  
  
  

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp)
  {
	  // TODO
	  for (int i = 0; i < arr.length; i++) 
		{
			E e = arr[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				if (comp.compare(arr[j], e) <= 0) 
				{
					break;
				}
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = e; 
		}
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr)
  {
	  // TODO
	  int n = arr.length;
		E temp;

		for(int i=0; i < n; i++)
		{
			for(int j=1; j < (n-i); j++)
			{
				if(arr[j-1].compareTo(arr[j]) < 0)
				{
					temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}

			}
		}
  }
 

}