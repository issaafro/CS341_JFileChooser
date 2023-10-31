package dabney;

/**
 * Represents a LinkedList of nodes.
 * 
 * @author Devon Dabney
 * @version 1.0
 * 
 */
public class LinkedList {
	
	private Node head;
	private Node tail;
	public int size; // KEEP TRACK OF SIZE OF LinkedList
	
	/**
	 * Creates an empty LinkedList of Nodes.
	 */
	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	// FUNCTIONALITY
	
	/**
	 * Checks to see if the LinkedList contains any nodes.
	 * 
	 * @return True if head is equal to null, false otherwise.
	 */
	public Boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * Adds a node to the LinkedList.
	 * 
	 * @param num	The integer value of the soon to be node.
	 */
	public void add(double num) {
		Node temp = new Node(num);
		
		// Scenario 1: empty LinkedList
		if(head == null) {
			head = temp;
			tail = temp;
			size++;
		}
		
		// Scenario 2: linkedList is not empty
		else {
			tail.next = temp;
			tail = temp;
			size++;
		}
	}
	
	/**
	 * Method to retrieve the value of a node in a LinkedList based on index.
	 * 
	 * @param index	Index for the desired element in LinkedList.
	 * @return	Double for the value of the node.
	 */
	public double getVal(int index) {
		if(index < 0)
			return 0;
		
		Node current = head;
		
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.n;
	}
}
