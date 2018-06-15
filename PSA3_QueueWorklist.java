/**
 * Name: Sabrina Lee (A91066880) , Jamicko Tan (A14717976)
 * Log In: cs12fkh, cs12fny 
 * Email: SAL040@ucsd.edu , jjt025@ucsd.edu
 * Due Date: October 18, 2017
 * File: QueueWorklist.java
 * 
 * Description: implements the SearchWorkList interface
 * works specifically with Squares from a Maze (not
 * generic type)
 * Works as a Queue List
 *FIFO First in first out
 * 
 * 
 */

import java.util.LinkedList;

public class QueueWorklist implements SearchWorklist{
	
	private LinkedList<Square> queuesList;
	
	QueueWorklist(){
		//using a linked list
		queuesList = new LinkedList<Square>();
	}
	
	@Override
	public void add(Square s) {
		// TODO Auto-generated method stub
		//adding the square at the end
		this.queuesList.add(s);
	}

	@Override
	public Square getNext() {
		// TODO Auto-generated method stub
		//getting the first square of 
		//queue list and removing the square
		return this.queuesList.removeFirst();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		//checking the queuelist is empty
		return (this.queuesList.size()==0) ;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		//getting the size of the queue list
		return this.queuesList.size();
	}




}

