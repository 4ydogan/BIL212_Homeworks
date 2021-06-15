
public class ArrayList<E> {
	
	public static final int CAPACITY = 16; 	// default array capacity
	private E[] data;						// storage	
	private int size = 0;					// current number of elements
	
	public ArrayList() {					// default constructor
		this(CAPACITY);
	}
	
	public ArrayList(int capacity) {		// constructor with given capacity
		data = (E[]) new Object[capacity];
	}
	
	public int size() {						// returns number of elements in the arraylist
		return size;
	}
	
	public boolean isEmpty() {				// returns whether the arraylist is empty
		return size == 0;
	}
	
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);			
		return data[i];						// returns the element at index i
	}
	
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];					
		data[i] = e;						// replaces the element at index i with e
		return temp;						// returns the replaced element
	}

	public E remove(int i) throws IndexOutOfBoundsException {// removes and returns the element at index i
		checkIndex(i, size);				
		E temp = data[i];
		for (int k=i; k<size-1; k++) {		// shift elements to fill hole
			data[k] = data[k+1];
		}
		data[size-1] = null; 				// help garbage collector
		size--;
		return temp;
	}
	
	public void add(int i, E e) throws IndexOutOfBoundsException { // inserts element e to be at index i
		checkIndex(i, size);				
		if(size == data.length) {			// not enough capacity
			resize(2*data.length);
		}
		for (int k=size-1; k>=i; k--) {		// shift elements to right
			data[k+1] = data[k];
		}
		data[i] = e; 						// ready to place the new element
		size++;
	}
	
	private void resize(int i) {
		E[] newData = (E[]) new Object[i];
		for (int k=0; k<size; k++) {		// shift elements to fill hole
			newData[k] = data[k];
		}
		data = newData;
	}

	private void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if(i < 0 || i >= n) {
			throw new IndexOutOfBoundsException(" Illegal index: " + i);
		}
		
	}
}
