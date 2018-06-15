
/**
 * Name: Sabrina Lee (A91066880) , Jamicko Tan (A14717976)
 * Log In: cs12fkh, cs12fny 
 * Email: SAL040@ucsd.edu , jjt025@ucsd.edu
 * Due Date: 
 *	MileStone- October 23, 2017
 *	Final Submission- October 27,2017
 * File: HashTable.jave
 * 
 * Description: HashTable class that implements the
 * IHashTable.java (we have to implement each method that is 
 * declared) HashTable includes, getting the hashvalue 
 * for each string being put into the hashtable
 * 
 */
package hw4;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable implements IHashTable {
	
	//You will need a HashTable of LinkedLists. 
	
	private int nelems;  //Number of element stored in the hash table
	private int expand;  //Number of times that the table has been expanded
	private int collision;  //Number of collisions since last expansion
	private String statsFileName;     //FilePath for the file to write statistics upon every rehash
	private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing
	
	private int size; // tracks the number of "buckets" or space
	private int loadFactor = 0; // nelems/size
	private LinkedList<String>[] buckets; 
	private int sizeLongestCollisionChain = 0;
	private LinkedList<String> insertedElements = new LinkedList<String>();
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 */
	public HashTable(int size) {
		if(size <= 0) {
			throw new IllegalArgumentException();
		}
		this.nelems = 0;
		this.expand = 0;
		this.collision = 0;
		this.statsFileName = "";
		this.printStats = false;
		this.size = size;
		this.loadFactor = 0;
		this.buckets = initializeBuckets(size);
	}
	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 * @param File path to write statistics
	 */
	public HashTable(int size, String fileName){
		if(size <= 0) {
			throw new IllegalArgumentException();
		}
		this.nelems = 0;
		this.expand = 0;
		this.collision = 0;
		this.statsFileName = fileName;
		this.printStats = true;
		this.size = size;
		this.loadFactor = 0;
		this.buckets = initializeBuckets(size);
	}	

	/**
	 * 
	 */
	@Override
	public boolean insert(String value) throws NullPointerException{
		if(value == null) 
			throw new NullPointerException();
		//get the linked list at index
		int hashValue = getHashValue(value);
		//System.out.println(hashValue + " "+ this.buckets.length);
		LinkedList<String> targetList = this.buckets[hashValue];
		//insert the string in that linkedlist 
		if(!targetList.contains(value)) {
			
			if(!targetList.isEmpty()) {
				//there is a collision
				this.collision++;
			}
			//adding the value to specified index in hashtable
			targetList.add(value);
			if(!insertedElements.contains(value)) {
				//updating our linkedlist of added elements
				this.insertedElements.add(value);
				//updating nelems
				this.nelems++;
			}
			
			//update stat longest collision chain
			if(targetList.size() > this.sizeLongestCollisionChain) {
				this.sizeLongestCollisionChain = targetList.size();
			}
			
			this.loadFactor = this.udpdateLoadFactor();
			return true;
		}else {
			//value already exists
			return false;
		}
	}

	/**
	 * 
	 */
	@Override
	public boolean delete(String value) throws NullPointerException {
		if(value == null) 
			throw new NullPointerException();
		int hashValue = getHashValue(value);
		//get the linked list at index
		LinkedList<String> targetList = this.buckets[hashValue];
		//insert the string in that linkedlist 
		if(targetList.remove(value)) {
			this.insertedElements.remove(value);
			this.nelems--;
			this.udpdateLoadFactor();
			return true;
		}else {
			return false;
		}
	}

	/**
	 * returns true if the linked list at the hash index
	 * contains the value
	 * returns false if the linked list at the hash index
	 * does not contain the value
	 */
	@Override
	public boolean contains(String value) throws NullPointerException {
		if(value == null) 
			throw new NullPointerException();
		int hashValue = getHashValue(value);
		//get the linked list at index
		LinkedList<String> targetList = this.buckets[hashValue];
		//using the linkedlist contains method to return true or false
		if (targetList.contains(value)){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 
	 */
	@Override
	public void printTable() {
		for(int i=0; i < this.size; i++) {
			
			//print index
			if(i!=0)
			System.out.print("\n" + i + ": ");
			else
			System.out.print(i + ": ");
			//getting the elements 
			for(int j=0; j< this.buckets[i].size(); j++) {
				System.out.print(this.buckets[i].get(j));
				if (j != this.buckets[i].size()-1)
				System.out.print(", ");
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public int getSize() {
		return this.nelems;
	}
	
	/* TODO - Helper methods can make your code more efficient and look neater.
     * We expect AT LEAST the following helper methods in your code
     * rehash() to expand and rehash the items into the table when load factor goes
     * over the threshold.
	 * printStatistics() to print the statistics after each expansion. This method 
     * will be called from insert/rehash only if printStats=true
     */
	
	//Constructor helper method to initialize an arrayList with
	//empty linkedlist
	private static LinkedList<String>[] initializeBuckets( int size) {
 
		LinkedList<String>[] list = new LinkedList[size];
		for(int i = 0; i < size; i++) {
			list[i] = new LinkedList<String>();
		}
		return list;
	}
	
	/**
	 *From ZyBooks Figure 4.2.3 Multiplicative String Hash 
	 * @param key
	 * @return hashvalue
	 */
	private int getHashValue(String key) {
		
		int hash = 9; // "LARGE" number
		final int HASH_MULTIPLIER = 3;  
		for(int i = 0; i < key.length();i++) {
			hash = (hash * HASH_MULTIPLIER) + key.charAt(i);
		}
		return hash % this.size;
	 
		//return key.hashCode()%this.size;
	}
	/**
	 *helper function that updates the load factor
	 */
	private int udpdateLoadFactor() {
		 //check if load factor has exceeded 2/3
		final int THRESHOLD = 67;
		this.loadFactor = roundLoadFactor(this.nelems,this.size);
		if(this.loadFactor > THRESHOLD) {
			loadFactor = this.rehash();
		}
		return loadFactor;
	}
	/**
	 * 
	 * @param nelems
	 * @param size
	 * @return
	 */
	private int roundLoadFactor(int nelems, int size) {
		double factor = (nelems/(double)size);
		factor = Math.round(factor * 100);
		return (int)factor;
	}
	
	/**
	 * 
	 * @return
	 */
	private int rehash(){
		
		int newSize = this.size * 2;
		this.size = newSize;
		//update stats
		this.expand++;
		this.collision = 0;
		
		this.loadFactor = roundLoadFactor(this.nelems,this.size);
		this.buckets = initializeBuckets(this.size);
		 
		for(int i = 0 ; i < this.insertedElements.size(); i++) {
			this.insert(this.insertedElements.get(i));
		}
		return loadFactor;
		
	}
	
	
}