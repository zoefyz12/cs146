package sjsu.Zhao.cs146.project3;

public class RBTreeMain {

	public static void main(String[] args) {
		
		RedBlackTree<String> tree = new RedBlackTree<String>();
		
		String result = "B A E C B D H G I ";

		tree.insert("B");
		tree.insert("C");
		tree.insert("A");
		tree.insert("H");
		tree.insert("E");
		tree.insert("D");
		tree.insert("B");
		tree.insert("I");
		tree.insert("G");
		
		tree.printTree();
		System.out.println();
		System.out.println("Correct Tree: " + result);
	}
}
