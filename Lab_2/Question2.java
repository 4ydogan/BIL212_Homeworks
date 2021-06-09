import java.util.Scanner;
import java.util.Stack;

public class Question2 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);						// Scanner open
		System.out.println("Please enter an encoding message: ");	// request a path
		String message = scan.nextLine();							// input
		scan.close();												// Scanner close
		
		System.out.println("Decoding message: ");
		System.out.println(decode(message));						// output
	}
	
	public static String decode(String message) {					// method
		
		Stack<String> stack = new Stack<String>();					// Stack creating
		
		String result = "";											// creating result string
		
		for(int i=0; i<message.length(); i++) {						// adding elements to stack
			String str = message.substring(i,i+1);
			if(str.equals("]")) {									// if the element is "]"
				String temp = "";
				while(!stack.peek().equals("[")) {					// pop the element while the element is not "["
					temp = stack.pop() + temp;
				}
				stack.pop();										// pop the "["
				
				int time = Integer.parseInt(stack.pop());			// count of again
				
				for(int j=0; j<time; j++) {							// add to stack
					for(int k=0; k<temp.length(); k++) {
						stack.push(temp.substring(k, k+1));
					}
				}
			}else {													// add to stack
				stack.push(str);
			}
		}
		
		for(int i = stack.size(); i>0; i--) {						// pop from the stack
			result = stack.pop() + result;							// adding to result string
		}
		
		return result;												// return
	}

}
