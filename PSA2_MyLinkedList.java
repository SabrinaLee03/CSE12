/**
 * Name: Sabrina Lee (A91066880) , Jamicko Tan (A14717976)
 * Log In: cs12fkh, cs12fny 
 * Email: SAL040@ucsd.edu , jjt025@ucsd.edu
 * Due Date: 
 *	MileStone- October 9, 2017
 *	Final Submission- October 13,2017
 * File: MyLinkedList.java
 * 
 * Description: MyLinkedList class that implements the List
 * interface and add methods to this doubly linked list
 */

package hw2;

import java.util.*;


/**
 * MYLINKEDLIST class
 * 
 */
public class MyLinkedList<E> extends AbstractList<E> {
  
  private int nelems;
  private Node head;
  private Node tail;
  
  /**
   * NODE class
   * 
   */
  protected class Node {
    E data;
    Node next;
    Node prev;
    
    /**
     * Constructor to create single Node 
     * @param element
     */
    public Node(E element)
    {
		/* Add your implementation here */
    	this.data = element;
    	this.next = null;
    	this.prev = null;
    }
    /** 
      * Constructor to create singleton link it between previous and next 
      *   @param element Element to add, can be null
      *   @param prevNode predecessor Node, can be null
      *   @param nextNode successor Node, can be null 
      */
    public Node(E element, Node prevNode, Node nextNode)
    {
		/* Add your implementation here */
    	this.data = element;
    	this.next = nextNode;
    	this.prev = prevNode;
    	//prevNode.next = this;
    	//nextNode.prev = this;
    
    }
    /** Remove this node from the list
     *  Update previous and next nodes
     */
    public void remove()
    {
		/* Add your implementation here */
    	//case1 1 element
    	if (this.prev == head  && this.next == tail ) {
    		head.setNext(tail);
    		tail.setPrev(head);
    	}
    	//case2 removing index 0
    	if(this.prev == head) {
    		head.setNext(this.getNext());
    		this.next.setPrev(head);
    	}
    	//case3 removing index last
    	if(this.next == tail) {
    		tail.setPrev(this.getPrev());
    		this.prev.setNext(tail);	
    	}
    	//general case
    	this.next.setPrev(this.getPrev());
    	this.prev.setNext(this.getNext());
    	
    }
    
    /** Set the previous node in the list
      *  @param p new previous node
      */
    public void setPrev(Node p)
    {
		/* Add your implementation here */
    	this.prev = p;
    }
	
    /** Set the next node in the list
      *  @param n new next node
      */
    public void setNext(Node n)
    {
		/* Add your implementation here */
    	this.next = n;
    }
    
    /** Set the element 
      *  @param e new element 
      */
    public void setElement(E e)
    {
		/* Add your implementation here */
    	this.data = e;
    }
    
    /** Accessor to get the next Node in the list */
    public Node getNext()
    {
      return this.next; // XXX-CHANGE-XXX
    }
    
    /** Accessor to get the prev Node in the list */
    public Node getPrev()
    {
      return this.prev; // XXX-CHANGE-XXX
    } 
    
    /** Accessor to get the Nodes Element */
    public E getElement()
    {
      return this.data; // XXX-CHANGE-XXX
    } 
  }
  
  /**
   * LISTITERATOR implementation 
   * 
   */
  protected class MyListIterator implements ListIterator<E> {
    
    private boolean forward ;
    private boolean canRemove;
    private Node left,right; // Cursor sits between these two nodes
    private int idx;  // Tracks current position. what next() would return 
    
    /**
     * Constructor that initializes the iterator
     */
    public MyListIterator()
    {
		/* Add your implementation here */
    	this.forward = true;
    	this.canRemove = false;
    	this.left = head;
    	//empty
    	if(nelems == 0) {
    		this.right = tail;
    	}else {
    	//for non empty
    	this.right = head.next;
    	}
    }
    
   
    /**
    *Add function that adds a new node with data in param
    * @param e
    */
    @Override
    public void add(E e) throws  IllegalArgumentException
    {
		/* Add your implementation here */
   	 if (e == null) {
		 throw new IllegalArgumentException();
	 }
	 Node nodeData = new Node(e);
	//size 0
	 if (nelems == 0) {
		 head.setNext(nodeData);
		 tail.setPrev(nodeData);
		 nodeData.setPrev(head);
		 nodeData.setNext(tail); 
	 }else {
		 /*
		 Node temp = tail.prev;
		 temp.next = nodeData;
		 nodeData.prev = temp;
		 tail.prev = nodeData;
		 nodeData.next = tail;
		  */
		//adds to the left of the current iterator
		
		nodeData.setPrev(this.left);
		nodeData.setNext(this.right);
		this.right.setPrev(nodeData);
		this.left.setNext(nodeData);
	 }
	 this.left = nodeData;
	 this.idx++;
	 nelems++;
	 this.canRemove = false;
	  // XXX-CHANGE-XXX
  }
    
    
  /**
  * Checks if there is a next node
  * @return true when there is a next node
  */
    @Override
    public boolean hasNext()
    {
      return (this.right.getNext() != null); // XXX-CHANGE-XXX
    }
    
    /**
     * Checks if there is a previous node
     * @return true when there is a previous node
     */
    @Override
    public boolean hasPrevious()
    {
      return (this.left.getPrev() != null); // XXX-CHANGE-XXX
    }

    /**
     * Goes to the next node (right) and returns
     * the element of that node
     * @throws NoSuchElementException
     * @return E
     */
    @Override
    public E next() throws NoSuchElementException
    {	E element = null;
    	//check the exception
    	if(!this.hasNext()) {
    		throw new NoSuchElementException();
    	}else {
    		element = this.right.getElement();
    		this.left = this.left.getNext();
    		this.right = this.right.getNext();
    		this.idx++;
    		this.canRemove = true;
    		this.forward = true;
    	}
    	return  element;  // XXX-CHANGE-XXX
    }
    
    
    /** 
    *Returns the index(index of the right node)
     * @return the index of the next node
     */
    @Override
    public int nextIndex()
    {
      return this.idx; // XXX-CHANGE-XXX
    }
    

    /**
    *Returns the element of the node to the left
    *@throws NoSuchElementException
    *@return element of left node
    */
    @Override
    public E previous() throws NoSuchElementException
    {
    	E element = null;	
    	if(!this.hasPrevious()) {
    		throw new NoSuchElementException();
    	}
    	else {
    		element = this.left.getElement();
    		this.idx--;
    		this.canRemove = true;
    		this.left = this.left.getPrev();
    		this.right = this.right.getPrev();
    		this.forward =  false;
    	}	
      return element; // XXX-CHANGE-XXX
    }


    /**
    *Gets the index of the node to the left
    *@return index of the left node
    */
    @Override
    public int previousIndex()
    {
      return this.idx-1;  // XXX-CHANGE-XXX
    }
    

    /**
    *removes the desired node (depending on if)
    *next or previous was called
    *@throws IllegalStateException
    */
    @Override
    public void remove() throws IllegalStateException
    {
     Node temp = null; 
		/* Add your implementation here */
    	if(!canRemove) {
    		throw new IllegalStateException();
    	}else if(this.forward) {
    		//update left
        temp = this.left.getPrev();
    	this.left.remove();
        this.left = temp;
    	}else {
    		//update right 
        temp = this.right.getNext();
        this.right.remove();
        this.right = temp;
    		}
    	//we removed somthing so
		this.canRemove = false;
    	nelems--;
    }

    /**
    *Sets the element data to the param
    *@throws IllegalArgumentException
    *@throws IllegalStateException
    */
    @Override
    public void set(E e) throws IllegalArgumentException,IllegalStateException
    {
		/* Add your implementation here */
    	if(e == null) {
    		throw new IllegalArgumentException();
    	}else if(!canRemove) {
    		throw new IllegalStateException();
    	}else if(this.forward) {
    		this.left.setElement(e);
    	}else {
    		this.right.setElement(e);
    	}
    }    
  }

  //  Implementation of the MyLinkedList Class
  /** Only 0-argument constructor is define 
   * 
   * */
  public MyLinkedList()
  {
	  this.nelems = 0;
	  this.head = new Node(null,null,this.tail);
	  this.tail = new Node(null,this.head,null);  
  }
  
  /**
   * Returns the number of elements being stored
   * @return number of elements
   */
  @Override
  public int size()
  {
    // need to implement the size method
    return this.nelems; // XXX-CHA NGE-XXX 
  }
  

  /**
   * Gets the element of the index in the parameter
   * @throws IndexOutOfBoundsException
   * @return element at the desired index
   */
  @Override
  public E get(int index) throws IndexOutOfBoundsException
  {
	if( index > this.size()-1 || index < 0) {
		IndexOutOfBoundsException e = new IndexOutOfBoundsException();
		throw e;
	}
	Node targetNode = this.getNth(index);
	
    return targetNode.getElement();  // XXX-CHANGE-XXX

  }
  

  /** Add an element to the list 
    * @param index  where in the list to add
    * @param data data to add
    * @throws IndexOutOfBoundsException
    * @throws IllegalArgumentException
    */ 
  @Override
    public void add(int index, E data) 
    throws IndexOutOfBoundsException,IllegalArgumentException
  { 
	  /* Add your implementation here */
	  if(data == null) {
		  throw new IllegalArgumentException();
	  }
	  if (index < 0 || index > this.size()) {
		  throw new IndexOutOfBoundsException();
	  }
	  Node nodeData = new Node(data);
	  Node temp = new Node(null);
	  //add to empty
	  if (this.size() == 0 ) {
		  nodeData.setPrev(this.head);
		  nodeData.setNext(this.tail);
		  this.tail.setPrev(nodeData);
		  this.head.setNext(nodeData);  
	  }
	  //add to head
	  else if(index == 0 ) {
		  nodeData.setNext(this.head.getNext());
		  nodeData.setPrev(this.head);
		  this.head.getNext().setPrev(nodeData);
		  this.head.setNext(nodeData);
	  }
	  //add to tail
	  else if(index == this.size()-1){
		  nodeData.setNext(this.tail);
		  nodeData.setPrev(this.tail.getPrev());
		  this.tail.getPrev().setNext(nodeData);
		  this.tail.setPrev(nodeData);
	  }else{
		  //add in general
		  Node targetNode = this.getNth(index);
		  nodeData.setNext(targetNode);
		  nodeData.setPrev(targetNode.getPrev());
		  targetNode.getPrev().setNext(nodeData);
		  targetNode.setPrev(nodeData);
		  temp = targetNode.prev;
	  }
	  this.nelems++;
  }
  
  /** Add an element to the end of the list 
    * @param data data to add
    * @throws IllegalArgumentException
    * @return true if able to add
    */ 
  public boolean add(E data) throws IllegalArgumentException
  {	
	 if (data == null) {
		 throw new IllegalArgumentException();
	 }
	 Node nodeData = new Node(data);
	//size 0
	 if (this.size() == 0) {
		 this.head.setNext(nodeData); 
		 this.tail.setPrev(nodeData); 
		 nodeData.setPrev(this.head);
		 nodeData.setNext(this.tail);
	 }else {
		 nodeData.setNext(this.tail);
		 nodeData.setPrev(this.tail.getPrev());
		 this.tail.getPrev().setNext(nodeData);
		 this.tail.setPrev(nodeData);
	/*
		 Node temp = this.tail.prev;
		 temp.next = nodeData;
		 nodeData.prev = temp;
		 this.tail.prev = nodeData;
	*/
	 }
	this.nelems++;
	return true; // XXX-CHANGE-XXX
  }
  
  /** Set the element at an index in the list 
    * @param index  where in the list to add
    * @param data data to add
    * @return element that was previously at this index.
    * @throws IndexOutOfBoundsException
    * @throws IllegalArgumentException
    * @throws IllegalStateExceptoin
    */ 
  public E set(int index, E data) 
    throws IndexOutOfBoundsException,IllegalArgumentException
  {		E targetValue = null;
	  if (index < 0 || index > this.size()-1) {
		  throw new IndexOutOfBoundsException();
	  }
	  if(data == null) {
		  throw new IllegalArgumentException();
	  }else{
		  Node targetNode = this.getNth(index);
		  targetValue = targetNode.getElement() ;
		  targetNode.setElement(data);	  
		}	  
    return targetValue; 
  
  }
  
  /** Remove the element at an index in the list 
    * @param index  where in the list to add
    * @return element the data found
    * @throws IndexOutOfBoundsException
    */ 
  public E remove(int index) throws IndexOutOfBoundsException
  {
	 if(index > this.size()-1 || index < 0) {
		 throw new IndexOutOfBoundsException();
	 } 	
	 //set targetNode to the Nth node from the LinkedList
	 Node targetNode = this.getNth(index);
	 E removedValue = null;
	 //getting the element from that removed node
	 removedValue = targetNode.getElement();
	 //removing the Node from the LinkedList
	 targetNode.remove();
	 //update size
	 this.nelems--;
    return removedValue; // XXX-CHANGE-XXX
  }
  
  /** Clear the linked list, remove all elements */
  public void clear()
  {
	  /* Add your implementation here */
	  //head points to tail
	  this.head.setNext(this.tail);
	  //tail points to head
	  this.tail.setPrev(this.head);
	  //update the number of elements in the list
	  this.nelems = 0;
  }
  
  /** Determine if the list empty 
    *  @return true if empty, false otherwise */
  public boolean isEmpty()
  {
    return (this.size() == 0);  // XXX-CHANGE-XXX
  }
  
  
  public Iterator<E> QQQiterator()
  {
    return new MyListIterator();
  }
  public ListIterator<E> QQQlistIterator()
  {
    return new MyListIterator();
  }
  
  /**
   * Helper method to get the Node at the Nth index
   * @param index
   * @return targetNode object
   */
  private Node getNth(int index) 
  {
	Node targetNode = this.head;
	//goes through the list to the target index
	for(int i = 0; i<=index; i++) {
		targetNode = targetNode.next;
	}
	//returns the target node
    return targetNode;  // XXX-CHANGE-XXX
  }
  //helper to see the list
  public  void printList() {
	  System.out.print(" \n Printing Forward : head <=> ");
	  for(int i = 0 ; i<this.size(); i++) {
		  System.out.print(this.getNth(i).getElement() + " <=> ");
	  }
	  System.out.print(" tail ");	  
	  
	  System.out.print("\n Printing backwards : tail  <=>  ");
	  
	  for(int i = this.size()-1 ; i>=0; i--) {
		  System.out.print(this.getNth(i).getElement() + " <=> ");
	  }
	  System.out.print("head ");
	  
  }
  
  
  
   // UNCOMMENT the following when you believe your MyListIterator class is
   //functioning correctly
   
   public Iterator<E> iterator()
   {
   return new MyListIterator();
   }
   public ListIterator<E> listIterator()
   {
   return new MyListIterator();
   }
   
}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4
