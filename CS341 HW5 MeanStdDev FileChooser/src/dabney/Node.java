package dabney;

/**
 * Represents a node.
 * 
 * @author Devon Dabney
 * @version 1.0
 * 
 */
public class Node {
	
	public double n;
	public Node next;
	
	/**
	 * Creates a node with a specific real number as the value.
	 * 
	 * @param num	The real number value of the node.
	 */
	public Node(double num) {
		next = null;
		n = num;
	}

	/**
	 * Getter method to extract value from node.
	 * 
	 * @return Real number value of the node.
	 */
	public double getN() {
		return n;
	}

}
