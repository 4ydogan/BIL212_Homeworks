
public class Main {

	public static void main(String[] args) {

	BTDriver btdriver = new BTDriver();
	
	btdriver.addRoot(8);
	
	btdriver.addRight(btdriver.tree.root(), 0);
	btdriver.addRight(btdriver.tree.right(btdriver.tree.root()), 1);
	btdriver.addLeft(btdriver.tree.root(), 2);
	btdriver.addLeft(btdriver.tree.left(btdriver.tree.root()), 3);
	
	
	int sum = btdriver.sum(btdriver.tree);
	
	System.out.println(sum);
	}

}
