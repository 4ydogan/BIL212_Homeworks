
public class Queue<E> {
	private E[] data;
	
	int front = 0;
	int size = 0;
	int rare = 0;
	
	
	public Queue(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	public Queue(E[] array, int capacity) {			// constructor for given array 
		  this(capacity);
		  for(E element : array) {
			  if(element == null)
				  break;
			  enqueue(element);
		  }
	  }

	public void enqueue(E element) throws IllegalStateException  {
		if(size == data.length) {
			throw new IllegalStateException("Queue is full");
		}else {
			rare = (front + size) % data.length;
			data[rare] = element;
			size++;
		}
	}
	
	public E dequeue() {
		if(isEmpty()) {
			return null;
		}
		E answer = data[front];
		data[front] = null;
		front = (front + 1) % data.length;
		rare = (front + size) % data.length;
		size--;
		return answer;
	}
	
	public boolean isEmpty() {
		return (size == 0);		
	}
	
	public E first() {
		if(isEmpty()) 
			return null;
		return data[front];		
	}
	
	public int size() {
		return size;
	}
}
