import java.util.Scanner;
import java.util.Stack;

public class Question1 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);			// Scanner open
		System.out.println("Please enter a path: ");	// request a path
		String path = scan.nextLine();					// input
		scan.close();									// Scanner close
		
		System.out.println(absolutePath(path));			// output
	}
	
	public static String absolutePath(String path) {	// method
		
		Stack<String> stack = new Stack<String>();		// Stack creating
		
		String result = "";								// creating result string
		
		String[] elements = path.split("/");			// separating elements 
		
		for (String s : elements) {						// adding elements to stack
			
			if(s.equals(".") || s.equals("")) {}		// if the element is "." or "", don't anything
												
			else if(s.equals("..")) {					// if the element is "..", pop the last element
				stack.pop();
			}else {										// else push the element into stack
				stack.push(s);
			}
		}
		
		for(int i = stack.size(); i>0; i--) {			// pop from the stack
			result = "/" + stack.pop() + result;		// adding to result string
		}
		
		return result;									// return
	}
}
