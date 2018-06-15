/**
 * Name: Sabrina Lee (A91066880) , Jamicko Tan (A14717976)
 * Log In: cs12fkh, cs12fny 
 * Email: SAL040@ucsd.edu , jjt025@ucsd.edu
 * Due Date: 
 *	MileStone- October 23, 2017
 *	Final Submission- October 27,2017
 * File: HashTableTester.jave
 * 
 * Description: Tests the HashTable functions
 *  using JUnit testing techniques
 *  Specifically checking the throws exception functions
 *  verifying that the methods return the correct values 
 * 
 */
package hw4;
import org.junit.*;
import static org.junit.Assert.*;

/** 
 * 
 * @author Sabrina Lee, Jamicko John Tan
 * @version 1.0
 *
 */
public class HashtableTester {
	private HashTable testHashTable1;
	
	/**
	 * Initialize the HashTable
	 */
	@Before
	public void setUp()
	{
		testHashTable1 = new HashTable(1);
	}
	
	/**
	 * Given Test
	 * Testing insert method if it adds the element to the table
	 */
	@Test
	public void testInsert()
	{
		assertEquals("checking insert",true,testHashTable1.insert("abc"));
		assertEquals("Checking contains after insert",true,testHashTable1.contains("abc"));
	}

	/**
	 * Given Test
	 * Testing delete method if it removes the element from table
	 */
	@Test
	public void testDelete()
	{
     	testHashTable1.insert("abc");
		assertEquals("Checking delete",true,testHashTable1.delete("abc"));
		assertEquals("Checking contains after delete",false,testHashTable1.contains("abc"));
	}
	
	/**
	 * Given Test
	 * Tests that getSize() would return the correct size of the table
	 */
	@Test
	public void testGetSize()
	{
		testHashTable1.insert("abc");
		testHashTable1.insert("pqr");
		testHashTable1.insert("xyz");
		testHashTable1.printTable();
		assertEquals("Checking getSize",new Integer(3),new Integer(testHashTable1.getSize()));
	}

	/**
	 * Asserts that the insert() would throw NullPointerException 
	 * When given a null value as a parameter
	 */
	@Test
	public void testInsertException(){
		try {
			testHashTable1.insert(null);
			fail("Should have thrown exception");
		}catch(NullPointerException e) {
			//good
		}
	}

	/**
	 * Asserts that the contains() would throw NullPointerException 
	 * When given a null value as a parameter
	 */	
	@Test
	public void testContainsException(){
		try {
			testHashTable1.contains(null);
			fail("Should have thrown exception");
		}catch(NullPointerException e) {
			//good
		}
	}

	/**
	 * Asserts that the delete() would throw NullPointerException 
	 * When given a null value as a parameter
	 */
	@Test
	public void testDeleteException(){
		try {
			testHashTable1.delete(null);
			fail("Should have thrown exception");
		}catch(NullPointerException e) {
			//good
		}
	}

	/**
	 * Asserts the table size is 0 when nothing has been inserted 
	 */
	@Test
	public void testInitializeSize() {
		assertEquals(0,testHashTable1.getSize());
	}

	/**
	 * Testing the contains() method returns true or false correctly
	 */
	@Test 
	public void testContains() {
		testHashTable1.insert("abc");
		assertTrue(testHashTable1.contains("abc"));
		assertFalse(testHashTable1.contains("bcd tofu"));
	}

	/**
	 * Asserts the size of the table is correct after inserting and
	 * deleting elements
	 */
	@Test
	public void testDeleteSizeUpdate() {
		testHashTable1.insert("abc");
		testHashTable1.insert("bcd");
		testHashTable1.insert("efg");
		testHashTable1.delete("abc");
		assertEquals(2, testHashTable1.getSize());
	}

	/**
	 * Asserts the delete() returns true or false 
	 * when trying to delete an element from the table
	 */
	@Test
	public void testDeleteReturn() {
		testHashTable1.insert("abc");
		testHashTable1.insert("bcd");
		testHashTable1.insert("efg");
		assertTrue(testHashTable1.delete("abc")); 
		assertFalse(testHashTable1.delete("bcd tofu"));
	}	

	/**
	 * Asserts the insert() method returns true or false
	 * when trying to insert an element into the table
	 * or when it already exists 
	 */
	@Test
	public void testInsertReturn() {
		assertTrue(testHashTable1.insert("abc")); 
		assertFalse(testHashTable1.insert("abc"));
	}	 

}