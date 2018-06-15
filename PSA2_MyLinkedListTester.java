 /* Name: Sabrina Lee (A91066880) , Jamicko Tan (A14717976)
 * Log In: cs12fkh, cs12fny 
 * Email: SAL040@ucsd.edu , jjt025@ucsd.edu
 * Due Date: 
 *	MileStone- October 9, 2017
 *	Final Submission- October 13,2017
 * File: MyLinkedListTester.java
 * 
 * Description: MyLinkedList Test cases for the methods that
 * are implemented in the MyLinkedList class
 * 
 */
 

package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 *  Title: class LinkedListTester
 *  Description: JUnit test class for LinkedList class
 *  @author Sabrina Lee , Jamicko Tan
 *  @version 3.0 
 * */

/*
 * You should modify the information above to add your names and CSE12 accounts.
 * 
 * We have provided 7 tests to help you get familiar. 
 *
 * As a part of the Milestone submission, you have to add 10 new tests.
 * These tests will ONLY be graded against the correct implementation of MyLinkedList.
 * However, you will also be given feedback on how your tests behave on the 
 * buggy implementation of MyLinkedList. 
 *
 * After the Milestone submission, you will be able to view each other's tests which you
 * can use to add or improve your tests for the final submission. At the final submission,
 * if you are using someone else's tests, we expect you to give the required attribution.
 * We also expect you to write a README file that explains in greater detail why you used
 * the test. 
 * 
 * For the Final Submission, your tests will be graded against both the correct and
 * buggy implementation of MyLinkedList. At the time of final submission, you will be
 * given feedback on how your tests behave against few but not all, buggy implementations.
 *
 * Finally, when your tester is complete, you will rename it MyLinkedListTester.java 
 * (renaming LinkedList to MyLinkedList everywhere in the file) so that you can
 * use it to test your MyLinkedList and MyListIterator classes.
 */
public class MyLinkedListTester
{
  private MyLinkedList<Integer> empty ;
  private MyLinkedList<Integer> one ;
  private MyLinkedList<Integer> several ;
  private MyLinkedList<String>  slist ;
  static final int DIM = 5;
  static final int FIBMAX = 30;
  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  @Before
  public void setUp()
  {
    empty = new MyLinkedList<Integer>();
    one = new MyLinkedList<Integer>();
    one.add(0,new Integer(0));
    several = new MyLinkedList<Integer>() ;
    // List: 1,2,3,...,Dim
    for (int i = DIM; i > 0; i--)
      several.add(0,new Integer(i));
    
    // List: "First","Last"
    slist = new MyLinkedList<String>();
    slist.add(0,"First");
    slist.add(1,"Last");
  }
     
  

  /* Add your own tests here */
  //***** TESTING MyLinkedList ******//
  
  //TESTING boolean add (T data)//
/*add an element into this list (either at end or by index)
*throw an IllegalArgumentException if the user tries to add a null pointer
*throw IndexOutOfBoundsException as needed (same rules as MyArrayList)
* the boolean add method will presumably always return true; it is a 
*boolean function due to the method definition in AbstractList
*/
  /**
   * Check if add() throws illegal  argument exception when given null
   */
  @Test
  public void testSingleAddEceptionNull() {
	  try {
		  one.add(null);
		  fail("should have thrown exception");
	  }catch(IllegalArgumentException e) {
		  //this is good
	  }
  }
  /**
   * checks if the element is indeed added at the end of the list 
   */
  @Test
  public void TestSingleAdded() {
	  one.add(new Integer(5));
	  int listSize = one.size();
	  assertEquals(one.get(listSize - 1), new Integer(5));
	  assertEquals(one.size(),2);
  }
  /**
   *checks that add() returns true when something was successfully added 
   */
  @Test
  public void testSingleAddReturn() {
	  Boolean flag = one.add(new Integer(5));
	  assertTrue(flag);
  }
  
  /**
   * tests that the list is what we expect it to be when add() is called
   */
  
  @Test
  public void testSingleAddExpected() {
	  several.add(6);
	  //several.printList();
	  for (int i = 0; i < several.size(); i++ ) {
		  int num = i + 1;
		  assertTrue( (int)several.get(i) == num);
	  } 
	  
  }

 
  //TESTING void add( int index, T data)//
  /*add an element into this list (either at end or by index)
*throw an IllegalArgumentException if the user tries to add a null pointer
*throw IndexOutOfBoundsException as needed (same rules as MyArrayList)
*/  
  /**
   * Tests if add function throws desired exceptions
   * */
  @Test
  public void  testAddExceptionIndex(){
	  try {
		  one.add(3, new Integer(3));
		  
		  fail(" Index have thrown index out of bounds exception");
	  }
	  catch(IndexOutOfBoundsException e){
	  }
  }
  
  /**
   * Tests if add function would throw illegal argument exception
   */
  @Test 
  public void testAddEceptionNull() {
	  try {
		  one.add(1,null);
		  fail("should have thrown exception");
	  }catch(IllegalArgumentException e) {
		//this is good  
	  }
  }
  /**
   * Checking the integers were added in on the correct index
   */
  @Test 
  public void testAddedAtIndex() {
	 several.add(3,9);
	 assertEquals(several.get(3),new Integer(9));
	 several.add(0,7);
	 assertEquals(several.get(0),new Integer(7));
	 several.add(7,7);
	 assertEquals(several.get(0),new Integer(7)); 
  }
  /**
   * Verifying the size was updated
   */
  @Test 
  public void testAddedAtSizeUpdated() {
	 several.add(3,9);
	 assertEquals(several.size(),6);
	 several.add(0,7);
	 assertEquals(several.size(),7);
	 several.add(7,7);
	 assertEquals(several.size(),8);
	 
	 empty.add(0,1);
	 assertEquals(empty.size(),1);
  }


//TESTING GET()//
  /*Get contents at position i
  *Throw IndexOutOfBoudsExcpetion as needed
  */
  /**
   * verifies that get(int index) would return the expected tail value
   */
  @Test
  public void testGetTail() {
	  one.add(1,new Integer(2));
	  int listSize = one.size();
	  assertEquals(one.get(listSize-1), new Integer( 2 ));
  }
  /**
   * verifies that get(int index) would return the expected value 
   */
  @Test
  public void testGetExpected() {
	  several.add(3, new Integer(8));
	  assertEquals(several.get(3), new Integer(8));  
  }
  
  /** GIVEN TEST:Test if heads of the lists are correct */
  @Test
  public void testGetHead()
  {
    assertEquals("Check 0",new Integer(0),one.get(0)) ;
    assertEquals("Check 0",new Integer(1),several.get(0)) ;
  }
  
  /** GIVEN TEST:Test out of bounds exception on get */
  @Test
  public void testGetException()
  {
    try 
    {
      empty.get(0);
      // This is how you can test when an exception is supposed 
      // to be thrown
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }

  	//TESTING Set(int index, E data)//
    /*set the value at index i to data 
    *throw illegalArgumentException if data is null
	*throw IndexOutOfBoundsException as needed
    **/
    /** GIVEN TEST: Test setting a specific entry */
    @Test
    public void testSet()
    {
      slist.set(1,"Final");
      assertEquals("Setting specific value", "Final",slist.get(1));
      several.set(0,new Integer(4));
      assertEquals(several.get(0), new Integer(4));
      several.set(4,new Integer(9));
      assertEquals(several.get(4), new Integer(9));
    }
    /**
     * verifies that set would return an IllegalArgumentException when null is given as a value
     */
    
    @Test
    public void testSetExceptionNull() {
  	  try {
  		  one.set(0, null);
  		  fail("Should have thrown exception");
  		  
  	  }catch(IllegalArgumentException e) {
  		  //this is good  
  	  }
    }
    /**
     * verifies that set would throw an IndexOutOfBoundsException for invalid index 
     */
    @Test
    public void  testSetExceptionIndex(){
  	  try {
  		  one.set(3, new Integer(3));
  		  fail(" Index have thrown index out of bounds exception");
  	  }
  	  catch(IndexOutOfBoundsException e){
  		  //this is good
  	  }
    }
    /**
     * verifies that set would throw an IndexOutOfBoundsException for invalid index 
     */
    @Test
    public void  testSetExceptionIndexOnEmpty(){
  	  try {
  		  empty.set(3, new Integer(3));
  		  fail(" Index have thrown index out of bounds exception");
  	  }
  	  catch(IndexOutOfBoundsException e){
  		  //this is good
  	  }
    }
    /**
     * 
     */
    @Test
    public void testSetSize() {
    	several.set(4, new Integer(5));
    	assertEquals(several.size(), 5);
    }
    
    //TESTING Remove()//
    /*Remove the element from position i in this list
    *Throw IndexOutOfBoundsException as needed
    */
  	/**
  	* verifies that the remove function returns the expected value
   	* that was removed from the list
   	*/
    @Test
    public void testRemoved() {
  	  //case for size 1
  	  Integer value = 	one.remove(0);
  	  assertEquals( new Integer(0) , value);    
    }
    
    /**
     * verifies that the remove function adjusts the size
     * to the correct size after removing one node
     */
    @Test
    public void testRemoveSizeUpdated() {
  	  //case for size 1
  	  	one.remove(0);
  	  	assertEquals(0, one.size());
    }
    
    /**
     * Verifies that remove throws an IndexOutOfBoundsException 
     * when trying to remove an index outside of the list size
     */
    @Test
    public void  testRemoveExceptionIndex(){
  	  try {
  		  one.remove(3);
  		  fail(" Index have thrown index out of bounds exception");
  	  }
  	  catch(IndexOutOfBoundsException e){ 
  	  }
    }
  
 	// TESTING Clear()//
 	/*Remove all elements from the list */
  	/**
   * verifies that clear removes all list items and 
   * the resulting list is empty
   */
  @Test
  public void testClear() {
	  one.clear();
	  assertTrue(one.isEmpty());
	  several.clear();
	  assertTrue(several.isEmpty());
	  empty.clear();
	  assertTrue(empty.isEmpty());
  } 
 
  //TESTING Empty()//
  /* Determine if the list is empty*/
  /** GIVEN TEST: Test isEmpty */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()) ;
    assertTrue("one is not empty",!one.isEmpty()) ;
    assertTrue("several is not empty",!several.isEmpty()) ;
  }
   
  //TESTING Size()//
  /* Return the number of elements being stored */
   /** GIVEN TEST: Test if size of lists are correct */
    @Test
    public void testListSize()
    {
      assertEquals("Check Empty Size",0,empty.size()) ;
      assertEquals("Check One Size",1,one.size()) ;
      assertEquals("Check Several Size",DIM,several.size()) ;
    }
    
     


//***** TESTING MyListIterator ******//

  
  	/** GIVEN TEST: test Iterator Fibonacci.
    * This is a more holistic test for the iterator.  You should add
    * several unit tests that do more targeted testing of the individual
    * iterator methods.  */
  	@Test
  	public void testIteratorFibonacci()
  	{
    
	  MyLinkedList<Integer> fib  = new MyLinkedList<Integer>();
	  ListIterator<Integer> iter;
    // List: 0 1 1 2 3 5 8 13 ... 
    // Build the list with integers 1 .. FIBMAX
    int t, p = 0, q = 1;
    fib.add(0,p);
    fib.add(1,q);
    for (int k = 2; k <= FIBMAX; k++)
    {
      t = p+q;
      fib.add(k,t);
      p = q; q = t; 
    }
    // Now iterate through the list to near the middle, read the
    // previous two entries and verify the sum.
    iter = fib.listIterator();
    int sum = 0;
    for (int j = 1; j < FIBMAX/2; j++) {
      sum = iter.next();
      
    }
    iter.previous();
    assertEquals(iter.previous() + iter.previous() ,sum);
    // Go forward with the list iterator
    assertEquals(iter.next() + iter.next(),sum);
  	}


  	//TESTING hasNext()//
  	/* Return true if there are more elements when going
  	* in the forward direction.*/ 

    /**
     * Testing iterator on empty and several linkedList
     *	to get the correct true or false
     */
    @Test
    public void testIteratorHasNext() {
    	ListIterator<Integer> iterSeveral = several.listIterator();
    	ListIterator<Integer> iterEmpty = empty.listIterator();	
    	assertTrue(!iterEmpty.hasNext());
    	assertTrue(iterSeveral.hasNext());	
    }

    /** GIVEN TEST:
	*Test iterator on empty list and several list */
  	@Test
  	public void testIterator()
  	{
    int counter = 0 ;
    ListIterator<Integer> iter;
    for (iter = empty.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterator several count", counter, DIM);
  	}

  	//TESTING next()//
  	/*Return the next element in the list when going forward.
	*Throw NoSuchElementException if there is no such element
	*/
    /**
     * Test to verify it throws an exception when the list is empty
     */
    @Test
    public void testIteratorNextExceptionNullEmpty() {
    ListIterator<Integer> iterEmpty = empty.listIterator();	
    try {
    	iterEmpty.next();
       	fail("Should have failed");
   		}
   	catch( NoSuchElementException e){
   		//good
	   	}
    }
    /**
     * Test to verify it throws an exception when several list items
     */
    @Test
    public void testIteratorNextExceptionNullSeveral() {
   	ListIterator<Integer> iterSeveral = several.listIterator();	
   	try {
   		for(int i=0; i < several.size() + 2; i++) {
   		iterSeveral.next();
     	}
       	fail("Should have failed");
    	}
    	catch( NoSuchElementException e){
    		//good
    	}
    }
    /**
     *Verify the next returns the element next in the list 
     */
    @Test
    public void testIteratorNextReturn() {
    	ListIterator<Integer> iterSeveral = several.listIterator();	
    	for(int i=1; i <= several.size(); i++) {
    		assertEquals(iterSeveral.next(),new Integer(i));
		}
    }

    //TESTING nextIndex()//
    /*Return the index of the element that would be 
    *returned by a call to next()
	*/
    /**
    * Verifying that the next index(to the right) is 
    *0 in an empty list 
    */
    @Test
    public void testIteratorNextIndexEmpty() {
   	ListIterator<Integer> iterEmpty = empty.listIterator();	
  	 	assertEquals(iterEmpty.nextIndex(), 0);
    } 
    /**
    * Verifying that it returns the correct index in a list
    *with elements 
    */
    @Test
    public void testIteratorNextIndexSeveral() {
   	ListIterator<Integer> iterSeveral = several.listIterator();	
    	for(int i=0; i < several.size(); i++) {
    	assertEquals(iterSeveral.nextIndex(), i);
    	iterSeveral.next();
		}
    }
    /**
    * Verifying that it returns the size of the list when at the
    *tail of the list 
    */
    @Test
    public void testIteratorNextIndexReturnsSize() {
  	ListIterator<Integer> iterSeveral = several.listIterator();	
      //put the iterator at the end
    	int value=0;
    	for(int i=1; i <= several.size(); i++) {
    		value = iterSeveral.next();
		}
    	assertEquals(several.size(), value);
    }

    //TESTING hasPrevious()//
    /*Return true if there are more elements when going in the reverse direction.*/ 
    /**
     * Verifying that it returns true or false when there is a previous
     *element in relation to where the iterator is
     */
    @Test
    public void testIteratorHasPrevious() {
    	ListIterator<Integer> iterSeveral = several.listIterator();
    	ListIterator<Integer> iterEmpty = empty.listIterator();	
    	assertTrue(!iterEmpty.hasPrevious());
    	iterSeveral.next();
    	assertTrue(iterSeveral.hasPrevious());	
    }
    
    //TESTING previous()//
    /*Return the next element in the list when going backwards
    *Throw NoSuchElementException if there is no such element
    */
    /**
     * Verifying that it throws an exception when there is 
     *no element when going backwards using an empty list
     */
    @Test
    public void testIteratorPreviousExceptionNullEmpty() {
    	ListIterator<Integer> iterEmpty = empty.listIterator();	
    	try {
    		iterEmpty.previous();
        	fail("Should have failed");
    	}catch( NoSuchElementException e){
    		//good
    	}
    }
    /**
     * Verifying ithat it throws an exception when at the
     *head of the list but there is no element going backward
     */
    @Test
    public void testIteratorPreviousExceptionNullSeveral() {
    	ListIterator<Integer> iterSeveral = several.listIterator();	
    	try {
    		iterSeveral.previous();
        	fail("Should have failed");
    	}catch( NoSuchElementException e){
    		//good
    	}
    }
    /**
     * Verifying that it returns the element of the previous Node in
     * the list 
     */
    @Test
    public void testIteratorPreviousReturn() {
    	ListIterator<Integer> iterSeveral = several.listIterator();	
    	//put the iterator at the end
    	for(int i=1; i <= several.size(); i++) {
    		iterSeveral.next();
		}
    	for(int i=several.size(); i >= 1; i--) {
    		assertEquals( iterSeveral.previous() ,new Integer(i));
		}
    }
    
    //TESTING previousIndex()//
    /* Return the index of the element that would be returned 
    *by a call to previous()
	*Return -1 if at the start of the list
	*/
    /**
     * Verifying that it returns -1 when there is no node 
     *going backwards (in an empty list) 
     */
     @Test
     public void testIteratorPreviousIndexEmpty() {
     	ListIterator<Integer> iterEmpty = empty.listIterator();	
     	assertEquals(iterEmpty.previousIndex(), -1);
     } 
     /**
      * Verifying that it returns the correct previous index
      * in the list 
      */
      @Test
      public void testIteratorPreviousIndexSeveral() {
    	ListIterator<Integer> iterSeveral = several.listIterator();	
    	for(int i=several.size(); i <= 0; i++) {
    		assertEquals(iterSeveral.previousIndex(), i);
    		iterSeveral.previous();
		}
      }
    
    //TESTING void add(T x)//
    /* Insert the given item into the list immediately before 
    * whatever would have been returned by a call to next()
    *Throw an IllegalArgumentException if x is null.
    */
    /**
    * Test that it throws an exception when trying to add a null
    */
    @Test
    public void testIteratorAddExceptionNull() {
    	ListIterator<Integer> iter = several.listIterator();
    	try {
    		iter.add(null);
    		fail("Should have thrown IllegalArgumentException");
    	}catch( IllegalArgumentException e){
    		//good
    	}
    }
    /**
     *Verifying that the added elements are in the correct
     *index 
     */
    @Test
    public void testIteratorAddIdx() {
    	ListIterator<Integer> iter = several.listIterator();
    	iter.add(3);
    	assertEquals(iter.previousIndex(), 0);
    	assertEquals(iter.nextIndex(), 1);
    	iter.next();	
    	iter.next();
    	// right now the list is 
    	//head->3->1->2 (iterator is here)->3->4-> 5->tail
    	iter.add(6);
    	//several.printList();
    	//head->3->1->2->6 (iterator is here)->3->4-> 5->tail
    	assertEquals(iter.previousIndex(), 3);
    	assertEquals(iter.nextIndex(), 4);
    }
        
	//TESTING void set(T x)//
	/*Change the value in the node returned by the most recent 
	*next/previous with the new value.
	*Throw a IllegalArgumentException if x is null.
	*Throw an IllegalStateException if neither next nor 
	*previous were called
	*Throw an IllegalStateException if add or remove have been
	* called since the most recent next/previous
	*/
    /**
     * Should thrown Illegal argument exception when the parameter is null
     */
    @Test
    public void testIteratorSetNull() {
    	ListIterator<Integer> iter = several.listIterator();
    	try {
    		iter.next();
    		iter.set(null);
    		fail("should have thrown exception");
    	}catch(IllegalArgumentException e) {
    		//good
    	}
    	
    	//should be the same when prev was called
    	try {
    		iter.previous();
    		iter.set(null);
    		fail("should have thrown exception");
    	}catch(IllegalArgumentException e) {
    		//good
    	}
    }
    
    /**
     *Should throws IllegalStateException if neither
     *next or previous was called 
     */
    @Test
    public void testIteratorSetIllegalStateBeginning() {
    	ListIterator<Integer> iter = several.listIterator();
    	try {
    		iter.set(4);
    		fail("Should have thrown an exception");
    	} catch(IllegalStateException e){
    		//good
    	}
    }
    /**
     *Should throws IllegalStateException if
     *add or remove has already been called
     */
    @Test
    public void testIteratorSetIllegalStateRemove() {
    	ListIterator<Integer> iter = several.listIterator();
    	try {
    		iter.next();
    		iter.remove();
    		iter.set(5);
    		fail("Should have thrown an exception");
    	} catch(IllegalStateException e){
    		//good
    	}
    	try {
    		iter.next();
    		iter.next();
    		iter.previous();
    		iter.remove();
    		iter.set(5);
    		fail("Should have thrown an exception");
    	} catch(IllegalStateException e){
    		//good
    	}
    }
    /**
     *Should throws IllegalStateException if
     *add or remove has already been called
     */
    @Test
    public void testIteratorSetIllegalStateAdd() {
    	ListIterator<Integer> iter = several.listIterator();
    	try {
    		iter.next();
    		iter.add(5);
    		iter.set(6);
    		fail("Should have thrown an exception");
    	} catch(IllegalStateException e){
    		//good
    	}
    	try {
    		iter.next();
    		iter.next();
    		iter.previous();
    		iter.add(5);
    		iter.set(6);
    		fail("Should have thrown an exception");
    	} catch(IllegalStateException e){
    		//good
    	}
    }
    /**
     * Test if set changes the value of the target node
     * and if it modifies the correct node
     */
    @Test
    public void testIteratorSet() {
    	ListIterator<Integer> iter = several.listIterator();
    	iter.next();
    	iter.next();
    	//should set index 1
    	iter.set(5);
    	assertEquals(several.get(1),new Integer(5));
    	iter.previous();
    	iter.set(9);
    	assertEquals(several.get(1),new Integer(9));
    	iter.add(10);
    	iter.next();
    	//since we called add and then called next, the 
    	//target node to be changed should be index 2
    	iter.set(23);
    	assertEquals(several.get(2),new Integer(23));
    	
    	//this checks that the whole list is what we expect 
    	//it to be 
    	//current list : head <=> 1 <=> 10 <=> 23 <=> 3 <=> 4 <=> 5 <=>  tail 
    	Integer[] severalArray = {new Integer(1),new Integer(10),new Integer(23),
    								new Integer(3),new Integer(4),new Integer(5)};
    	for(int i = 0; i< several.size(); i++) {
    		assertTrue(several.get(i).equals(severalArray[i]));
    	}    	
    }
    
	//TESTING void remove()//
	/*Remove the last element returned by the most recent call
	* to either next/previous 
	*Throw an IllegalStateException if neither next nor previous 
	*were called
	*Throw an IllegalStateException if add or remove has been 
	*called since the most recent next/previous
	*/ 
    
    /**
     *Should thrown IllegalStateException when neither next nor
     *previous was called yet
     */
    @Test
    public void testIteratorRemoveExceptionBeginning() {
    	ListIterator<Integer> iter = several.listIterator();
    	try {
    		iter.remove();
    		fail("Should have thrown an exception");
    	}catch(IllegalStateException e) {
    		//good
    	}
    }
    /**
     *Should thrown IllegalStateException when either 
     *remove or add has been called
     */
    @Test
    public void testIteratorRemoveExceptionRemoveAdd() {
    	ListIterator<Integer> iter = several.listIterator();
    	try {
    		iter.next();
    		iter.remove();
    		//should throw here
    		iter.remove();
    		fail("Should have thrown an exception");
    	}catch(IllegalStateException e) {
    		//good
    	}
    	try {
    		iter.next();
    		iter.add(3);
    		//should throw here
    		iter.remove();
    		fail("Should have thrown an exception");
    	}catch(IllegalStateException e) {
    		//good
    	}
    }
    /**
     * verifies that the iterator remove the node at its
     * left, after calling next
     */
    @Test
    public void testIteratorRemoveNext() {
    	ListIterator<Integer> iter = several.listIterator();
    	iter.next();
    	iter.remove();
    	for (int i = 0; i<several.size(); i++) {
    		assertEquals(several.get(i), new Integer(i+2));	
    	}
    }
    
    
    /**
     * verifies that the iterator remove the node at its
     * right, after calling prev
     */
    @Test
    public void testIteratorRemovePrev() {
    	ListIterator<Integer> iter = several.listIterator();
    	iter.next();
    	iter.previous();
    	iter.remove();
    	for (int i = 0; i<several.size(); i++) {
    		assertEquals(several.get(i), new Integer(i+2));	
    	}
    }
    
    /**
     * Verifies that the size is decremented b y 1
     * after remove is called
     */
    @Test
    public void testIteratorRemoveSizeUpdate() {
    	ListIterator<Integer> iter = several.listIterator();
    	int initialSize = several.size();
    	iter.next();
    	iter.remove();
    	assertEquals(initialSize - 1, several.size());
    }
    

}
