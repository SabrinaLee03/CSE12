/**
 * Name: Sabrina Lee (A91066880) , Jamicko Tan (A14717976)
 * Log In: cs12fkh, cs12fny 
 * Email: SAL040@ucsd.edu , jjt025@ucsd.edu
 * Due Date: October 18, 2017
 * File: StackWorklist.java
 * 
 * Description: implements the SearchWorkList interface
 *works specifically with Squares from a Maze (not
 * generic type)
 *Works as a Stack List
 *LIFO Last in first out 
 * 
 */


import java.util.LinkedList;

public class StackWorklist implements SearchWorklist{
	
	private LinkedList<Square> stackList; 
	
	StackWorklist(){
		//using a linkedlist
		stackList = new LinkedList<Square>();
	}

	@Override
	public void add(Square s) {
		// TODO Auto-generated method stub
		//adding the square to the end
		this.stackList.add(s);
	}

	@Override
	public Square getNext() {
		// TODO Auto-generated method stub
		//returning the last square and 
		//removing it from the stacklist
		return (this.stackList.removeLast());
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		//checking if the stacklist has
		//no squares inside
		return (this.stackList.size()==0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		//getting the size of the stacklist
		return this.stackList.size();
	}






}