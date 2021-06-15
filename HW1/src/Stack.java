
public class Stack<E> {
	
	private E[] data;

	private int top = -1;
	
	public Stack(int capacity){		
		data = (E[]) new Object[capacity];		
	}
	
	public boolean isEmpty() {
		if(top < 0)
			return true;
		return false;
	}
	
	public int size() {
		return top+1;
	}
	
	public E pop() {
		if(isEmpty()) {
			return null;
		}
		E answer = data[top];
		data[top] = null;
		top--;
		return answer;
	}
	
	public void push(E element) throws IllegalStateException {
		if(top == data.length-1) {
			throw new IllegalStateException("Stack is full");
		}else {
			top++;
			data[top] = element;
		}
	}
	
	public E top() {
		return data[top];
	}
	
	
	
}
