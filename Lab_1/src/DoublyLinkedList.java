/**
 * A basic doubly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class DoublyLinkedList<E extends Comparable<E>> {

  //---------------- nested Node class ----------------
  /**
   * Node of a doubly linked list, which stores a reference to its
   * element and to both the previous and next node in the list.
   */
  private static class Node<E> {

    /** The element stored at this node */
    private E element;               // reference to the element stored at this node

    /** A reference to the preceding node in the list */
    private Node<E> prev;            // reference to the previous node in the list

    /** A reference to the subsequent node in the list */
    private Node<E> next;            // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param p  reference to a node that should precede the new node
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> p, Node<E> n) {
      element = e;
      prev = p;
      next = n;
    }

    // public accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { 
    	return element; 
    }

    /**
     * Returns the node that precedes this one (or null if no such node).
     * @return the preceding node
     */
    public Node<E> getPrev() { return prev; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Update methods
    /**
     * Sets the node's previous reference to point to Node n.
     * @param p    the node that should precede this one
     */
    public void setPrev(Node<E> p) { prev = p; }

    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the DoublyLinkedList
  /** Sentinel node at the beginning of the list */
  private Node<E> header;                    // header sentinel

  /** Sentinel node at the end of the list */
  private Node<E> trailer;                   // trailer sentinel

  /** Number of elements in the list (not including sentinels) */
  private int size = 0;                      // number of elements in the list

  /** Constructs a new empty list. */
  public DoublyLinkedList() {
    header = new Node<>(null, null, null);      // create header
    trailer = new Node<>(null, header, null);   // trailer is preceded by header
    header.setNext(trailer);                    // header is followed by trailer
  }

  // public accessor methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list.
   * @return element at the front of the list (or null if empty)
   */
  public E first() {
    if (isEmpty()) return null;
    return header.getNext().getElement();   // first element is beyond header
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last() {
    if (isEmpty()) return null;
    return trailer.getPrev().getElement();    // last element is before trailer
  }

  // public update methods
  /**
   * Adds an element to the front of the list.
   * @param e   the new element to add
   */
  public void addFirst(E e) {
    addBetween(e, header, header.getNext());    // place just after the header
  }

  /**
   * Adds an element to the end of the list.
   * @param e   the new element to add
   */
  public void addLast(E e) {
    addBetween(e, trailer.getPrev(), trailer);  // place just before the trailer
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(header.getNext());             // first element is beyond header
  }

  /**
   * Removes and returns the last element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeLast() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(trailer.getPrev());            // last element is before trailer
  }

  // private update methods
  /**
   * Adds an element to the linked list in between the given nodes.
   * The given predecessor and successor should be neighboring each
   * other prior to the call.
   *
   * @param predecessor   node just before the location where the new element is inserted
   * @param successor     node just after the location where the new element is inserted
   */
  private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
    // create and link a new node
    Node<E> newest = new Node<>(e, predecessor, successor);
    predecessor.setNext(newest);
    successor.setPrev(newest);
    size++;
  }

  /**
   * Removes the given node from the list and returns its element.
   * @param node    the node to be removed (must not be a sentinel)
   */
  private E remove(Node<E> node) {
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    return node.getElement();
  }
  
  /**
   * adds newData immediately before the given node.
   * @param node     the node is a reference.
   * @param newData  the E is a object will be added.
   */
  private void addBefore(Node<E> node, E newData) {
	 Node<E> temp_a = node.getPrev();
	  
	 Node<E> newNode = new Node<>(newData, node.getPrev(), node);
	 temp_a.setNext(newNode);
	 node.setPrev(newNode);
	 size++;	
	 
  }

  /**
   * removes the node that comes immediately before the given node.
   * @param node    the node is a reference.
   */
  private void removeBefore(Node<E> node) {
	  if(node.getPrev() == header ) {
		  
	  }else {
		 Node<E> removed = node.getPrev();
		 remove(removed);
	  }
  }
  
  /**
   * returns the address of the i-th node.
   * @param i    the i is like a index.
   */
  private Node<E> getNode(int i) {
	  int size = size();
	  
	  if( i > size/2 ) {
		  Node<E> node = trailer.getPrev();
		  int k = 0;
		  while(size-k != i) {
			  k++;
			  node = node.getPrev();
		  }
		  return node;
	  }
	  else {
		  Node<E> node = header;
		  int k = 0;
		  while(k != i) {
			  k++;
			  node = node.getNext();
		  }
		  return node;
	  }
  }
  
  /**
   * returns the data at the i-th position (in the i-th node).
   * @param i    the i is like an index.
   */
  public E get(int i) {
	  if (isEmpty()) 
		  return null;
	  
	  Node<E> node = getNode(i);
	  	  
	  return node.getElement(); 
  }
  
  /**
   * adds newData at the i-th position.
   * @param i        the i is like an index.
   * @param newData  the E is a object will be added.
   */
  public void add(int i, E newData) {
	  Node<E> node = getNode(i);
	  
	  addBefore(node, newData);
  }
  
  /**
   *  removes the i-th node, and returns the data that is removed.
   * @param i    the i is like an index.
   */
  public E remove(int i) {
	  Node<E> node = getNode(i);
	  
	  if(node == null ) {
		  return null;
	  }else {
		 Node<E> removed = node;
		 return remove(removed);
	  }
  }
  
  /**
   * finds and returns the index of node that contains the given data, returns -1 if no node contains it.
   * @param data    the data is an object finding
   */
  public int find(E data) {
	  Node<E> node = header.getNext();
	  int i = 1;
	  
	  while(node.getElement().compareTo(data) != 0 && i<size) {
		  node = node.getNext();
		  i++;
	  }
	  
	  if(i < size)
		  return i;
	  
	  return -1;
  }
  
  /**
   * swaps two given nodes in a doubly linked list.
   * @param node1  the node will be swapped
   * @param node2  the node will be swapped
   */
  private void swap(Node<E> node1, Node<E> node2) {
	  if(node1.getNext().equals(node2) == false) {
		  Node<E> temp = node1;
		  node1 = node2;
		  node2 = temp;		  
	  }
	
	  Node<E> node_a = node1.getPrev();
	  Node<E> node_d = node2.getNext();
	  
	  node_a.setNext(node2);
	  node_d.setPrev(node1);
	  node1.setNext(node_d);
	  node1.setPrev(node2);
	  node2.setNext(node1);
	  node2.setPrev(node_a);
	  
  }

  /**
   * sorts the doubly linked list in increasing order using the bubble sort algorithm and the swap method you just wrote.
   * 
   * 
   */
  public void sort() {
	 for(int i=size(); i>0; i--) {
		 for(int j=1; j<i; j++) {
			 Node<E> node1 = getNode(j);
			 Node<E> node2 = node1.getNext();
			 if(node1.getElement().compareTo(node2.getElement()) > 0) {
				 swap(node1,node2);
			 }
		 }
	 }
  }
  
  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = header.getNext();
    while (walk != trailer) {
      sb.append(walk.getElement());
      walk = walk.getNext();
      if (walk != trailer)
        sb.append(", ");
    }
    sb.append(")");
    return sb.toString();
  }

} //----------- end of DoublyLinkedList class -----------


