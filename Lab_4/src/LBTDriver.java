import java.util.Scanner;

public class LBTDriver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();
		
		while(input != -1) {
			
			tree.addOrdered(input);
			input = scan.nextInt();
		}
		
    	System.out.println(tree.toString());
		scan.close();
	}

}
