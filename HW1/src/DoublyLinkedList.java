public class DoublyLinkedList<E extends Comparable> {

  private static class Node<E> {

    private E element;               // reference to the element stored at this node

    private Node<E> prev;            // reference to the previous node in the list

    private Node<E> next;            // reference to the subsequent node in the list

    public Node(E e, Node<E> p, Node<E> n) {
      element = e;
      prev = p;
      next = n;
    }

    // public accessor methods
    public E getElement() { 
    	return element; 
    }
    
    public Node<E> getPrev() { return prev; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Update methods
    public void setPrev(Node<E> p) { prev = p; }

    public void setNext(Node<E> n) { next = n; }
  } 

  // instance variables of the DoublyLinkedList
 
  private Node<E> header;                    // header sentinel

  private Node<E> trailer;                   // trailer sentinel

  private int size = 0;                      // number of elements in the list

  public DoublyLinkedList() {
    header = new Node<>(null, null, null);      // create header
    trailer = new Node<>(null, header, null);   // trailer is preceded by header
    header.setNext(trailer);                    // header is followed by trailer
  }
  
  public DoublyLinkedList(E[] array) {			// constructor for given array 
	  this();
	  for(E element : array) {
		  if(element == null)
			  break;
		  addLast(element);
	  }
  }

  // public accessor methods
  public int size() { return size; }

  public boolean isEmpty() { return size == 0; }

  public E first() {
    if (isEmpty()) return null;
    return header.getNext().getElement();   // first element is beyond header
  }

  public E last() {
    if (isEmpty()) return null;
    return trailer.getPrev().getElement();    // last element is before trailer
  }

  // public update methods
  public void addFirst(E e) {
    addBetween(e, header, header.getNext());    // place just after the header
  }

  public void addLast(E e) {
    addBetween(e, trailer.getPrev(), trailer);  // place just before the trailer
  }

  public E removeFirst() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(header.getNext());             // first element is beyond header
  }

  public E removeLast() {
    if (isEmpty()) return null;                  // nothing to remove
    return remove(trailer.getPrev());            // last element is before trailer
  }

  // private update methods
  
  // Adds an element to the linked list in between the given nodes.
  private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
    // create and link a new node
    Node<E> newest = new Node<>(e, predecessor, successor);
    predecessor.setNext(newest);
    successor.setPrev(newest);
    size++;
  }

  // Removes the given node from the list and returns its element. 
  private E remove(Node<E> node) {
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    return node.getElement();
  }
  
  // adds newData immediately before the given node.
  private void addBefore(Node<E> node, E newData) {
	 Node<E> temp_a = node.getPrev();
	  
	 Node<E> newNode = new Node<>(newData, node.getPrev(), node);
	 temp_a.setNext(newNode);
	 node.setPrev(newNode);
	 size++;	
	 
  }
  
  // returns the address of the i-th node.
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
  
  // returns the data at the i-th position (in the i-th node).
  public E get(int i) {
	  if (isEmpty()) 
		  return null;
	  
	  Node<E> node = getNode(i);
	  	  
	  return node.getElement(); 
  }
  
  // adds newData at the i-th position.
  public void add(int i, E newData) {
	  Node<E> node = getNode(i);
	  
	  addBefore(node, newData);
  }
  
  //  removes the i-th node, and returns the data that is removed.
  public E remove(int i) {
	  Node<E> node = getNode(i);
	  
	  if(node == null ) {
		  return null;
	  }else {
		 Node<E> removed = node;
		 return remove(removed);
	  }
  }
  
  //finds and returns the index of node that contains the given data, returns -1 if no node contains it.
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
  
  //swaps two given nodes in a doubly linked list.
  private void swap(Node<E> node1, Node<E> node2) {
  	Node<E> walk = node1;
	try {
		while(walk.getNext() != node2) {
			walk = walk.getNext();
		}				
	}catch (Exception e) {
		Node<E> temp = node1;
		node1 = node2;
		node2 = temp;
	}
	
	E temp1 = node1.getElement();
	E temp2 = node2.getElement();
	addBetween(temp2, node1, node1.getNext());
	addBefore(node2, temp1);
	
	remove(node1);
	remove(node2);
  }

  // sorts the doubly linked list in increasing order using the bubble sort algorithm and the swap method you just wrote.
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
  
  // Produces a string representation of the contents of the list.
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
}

