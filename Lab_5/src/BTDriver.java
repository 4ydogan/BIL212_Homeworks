
public class BTDriver {
	
	LinkedBinaryTree<Integer> tree;
	
	public BTDriver() {
		tree = new LinkedBinaryTree<Integer>();
	}
	
	public void addRoot(int i) {
		tree.addRoot(i);
	}
	
	public void addLeft(Position<Integer> pos, int i) {
		tree.addLeft(pos, i);	
	}
	
	public void addRight(Position<Integer> pos, int i) {
		tree.addRight(pos, i);	
	}
	
	public void attach(Position<Integer> pos, LinkedBinaryTree<Integer> t1, LinkedBinaryTree<Integer> t2) {
		tree.attach(pos, t1, t2);
	}
	
	public int minimum(LinkedBinaryTree<Integer> tree){
		return tree.minimum(tree.root());
	}	
	
	public int sum(LinkedBinaryTree<Integer> tree){
		return tree.sum(tree.root());
	}
}
