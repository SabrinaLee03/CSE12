/* Name: Sabrina Lee (A91066880) and Jamicko Tan (A14717976)
 * Log In: SAL040@ucsd.edu and  jjt025@ucsd.edu
 * Due Date: 
 *	MileStone- October 9, 2017
 *	Final Submission- October 13,2017
 * File: LinkedListTester.java
 * 
 * Description: LinkedList Test cases for the methods that
 * are implemented in the MyLinkedList class
 * 
 */

package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;


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
public class LinkedListTester
{
  private LinkedList<Integer> empty ;
  private LinkedList<Integer> one ;
  private LinkedList<Integer> several ;
  private LinkedList<String>  slist ;
  static final int DIM = 5;
  static final int FIBMAX = 30;
  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  @Before
  public void setUp()
  {
    empty = new LinkedList<Integer>();
    one = new LinkedList<Integer>();
    one.add(0,new Integer(0));
    several = new LinkedList<Integer>() ;
    // List: 1,2,3,...,Dim
    for (int i = DIM; i > 0; i--)
      several.add(0,new Integer(i));
    
    // List: "First","Last"
    slist = new LinkedList<String>();
    slist.add(0,"First");
    slist.add(1,"Last");
  }
  /** Test if heads of the lists are correct */
  @Test
  public void testGetHead()
  {
    assertEquals("Check 0",new Integer(0),one.get(0)) ;
    assertEquals("Check 0",new Integer(1),several.get(0)) ;
  }
  
  /** Test if size of lists are correct */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size",0,empty.size()) ;
    assertEquals("Check One Size",1,one.size()) ;
    assertEquals("Check Several Size",DIM,several.size()) ;
  }
  
  /** Test setting a specific entry */
  @Test
  public void testSet()
  {
    slist.set(1,"Final");
    assertEquals("Setting specific value", "Final",slist.get(1));
  }
  
  /** Test isEmpty */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()) ;
    assertTrue("one is not empty",!one.isEmpty()) ;
    assertTrue("several is not empty",!several.isEmpty()) ;
  }

  /** Test out of bounds exception on get */
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

  
  /** Test iterator on empty list and several list */
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
  
  
  /** test Iterator Fibonacci.
    * This is a more holistic test for the iterator.  You should add
    * several unit tests that do more targeted testing of the individual
    * iterator methods.  */
  @Test
  public void testIteratorFibonacci()
  {
    
    LinkedList<Integer> fib  = new LinkedList<Integer>();
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
    for (int j = 1; j < FIBMAX/2; j++)
      sum = iter.next();
    iter.previous();
    assertEquals(iter.previous() + iter.previous(),sum);
    // Go forward with the list iterator
    assertEquals(iter.next() + iter.next(),sum);
  }
  /* Add your own tests here */
  
  /*tests if add functiono throws desired exceptions*/
  
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
   * Tests if add function would thrown illegal argument exception
   */
  @Test 
  public void testAddEceptionNull() {
	  try {
		  one.add(1,null);
		  fail("should have thrown exception");
	  }catch(IllegalArgumentException e) {
		  
		  
	  }
	  
  }
  @Test
  public void testSingleAddEceptionNull() {
	  try {
		  one.add(null);
		  fail("should have thrown exception");
	  }catch(IllegalArgumentException e) {
		  
		  
	  }
	  
  }
  
  @Test
  public void TestSingleAdded() {
	  
	  one.add(new Integer(5));
	  assertEquals(one.getLast(), new Integer(5));
	  
  }
  
  @Test
  public void testSingleAddReturn() {
	  Boolean flag = one.add(new Integer(5));
	  assertTrue(flag);
	  
  }
 // @Test
  /*
  public void testAddHeadTailUpdate() {
	  
	  //case 0 when we add a node the and empty list
	  //case1 When we add a node to the head
	  
	  //case 2 when add a ndoe to the tail
	  
	  //case when a node is added at inside the list in general
	  several.add(3, new Integer(99));
	  assertEquals(99, several.get);
	  
  }
  */
  @Test
  public void testGetTail() {
	  one.add(1,new Integer(2));
	  assertEquals(one.getLast(), new Integer( 2 ));
  }
  
  @Test
  public void testGetExpected() {
	  several.add(3, new Integer(8));
	  assertEquals(several.get(3), new Integer(8));
	  
  }
  @Test
  public void testSetExceptionNull() {
	  try {
		  one.set(1, null);
		  fail("Should have thrown exception");
		  
	  }catch(IllegalArgumentException e) {
		  
		  
	  }
	  
  }
  @Test
  public void  testSetExceptionIndex(){
	  try {
		  one.set(3, new Integer(3));
		  
		  fail(" Index have thrown index out of bounds exception");
	  }
	  catch(IndexOutOfBoundsException e){
		  
		  
	  }
  }

  @Test
  public void testRemoved() {
	  //case for size 1
	  Integer value = 	one.remove(0);
	  	assertEquals(0, one.size());
	  	assertEquals( new Integer(0) , value);
	  
  
  }
  
  @Test
  public void testRemoveSizeUpdated() {
	  //case for size 1
	  Integer value = 	one.remove(0);
	  	assertEquals(0, one.size());
	
  }
  
  @Test
  public void  testRemoveExceptionIndex(){
	  try {
		  one.remove(3);
		  
		  fail(" Index have thrown index out of bounds exception");
	  }
	  catch(IndexOutOfBoundsException e){
		  
		  
	  }
  }
  
  
  @Test
  public void testClear() {
	  one.clear();
	  assertTrue(one.isEmpty());
	  
  }
  
  
}
