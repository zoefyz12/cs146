package sjsu.Zhao.cs146.project3;

public class RedBlackTree<Key extends Comparable<Key>> {

	public static class Node<Key extends Comparable<Key>> {
		public static final int RED = 0;
		public static final int BLACK = 1;

		Node<Key> left, right, parent;
		Key key;
		int color;
		int nR = 0;
		int nL = 0;

		public Node() {
			color = BLACK;
			nL = 0;
			nR = 0;
			parent = null;
			right = null;
			left = null;
		}

		public Node(Key key) {
			this.key = key;
		}

		public int compareTo(Node<Key> n) { // this < that <0
			return key.compareTo(n.key); // this > that >0
		}
	}

	public interface Visitor<Key extends Comparable<Key>> {
		/**
		 * This method is called at each node.
		 * @param n the visited node
		 */
		void visit(Node<Key> n);
	}
	
	public void visit(Node<Key> n){
		System.out.println(n.key);
	}

	private RedBlackTree.Node<String> nil = new Node<String>();
	private RedBlackTree.Node<String> root = nil;

	public RedBlackTree() {
		root.left = nil;
		root.right = nil;
		root.parent = nil;
	}

	private void leftRotate(RedBlackTree.Node<String> node) {
		leftRotateFixup(node);

		RedBlackTree.Node<String> newNode;
		newNode = node.right;
		node.right = newNode.left;

		if (!isEnd(newNode.left))
			newNode.left.parent = node;
		newNode.parent = node.parent;

		if (isEnd(node.parent))
			root = newNode;
		else if (node.parent.left == node)
			node.parent.left = newNode;
		else
			node.parent.right = newNode;

		newNode.left = node;
		node.parent = newNode;
	}

	private void leftRotateFixup(RedBlackTree.Node<String> node) {
		if (isEnd(node.left) && isEnd(node.right.left)) {
			node.nL = 0;
			node.nR = 0;
			node.right.nL = 1;
		} else if (isEnd(node.left) && !isEnd(node.right.left)) {
			node.nL = 0;
			node.nR = 1 + node.right.left.nL + node.right.left.nR;
			node.right.nL = 2 + node.right.left.nL + node.right.left.nR;
		} else if (!isEnd(node.left) && isEnd(node.right.left)) {
			node.nR = 0;
			node.right.nL = 2 + node.left.nL + node.left.nR;
		} else {
			node.nR = 1 + node.right.left.nL + node.right.left.nR;
			node.right.nL = 3 + node.left.nL + node.left.nR + node.right.left.nL
					+ node.right.left.nR;
		}
	}

	private void rightRotate(RedBlackTree.Node<String> node) {
		rightRotateFixup(node);

		RedBlackTree.Node<String> newNode = node.left;
		node.left = newNode.right;

		if (!isEnd(newNode.right))
			newNode.right.parent = node;
		newNode.parent = node.parent;

		if (isEnd(node.parent))
			root = newNode;
		else if (node.parent.left == node)
			node.parent.left = newNode;
		else
			node.parent.right = newNode;

		newNode.right = node;
		node.parent = newNode;
	}

	private void rightRotateFixup(RedBlackTree.Node<String> node) {
		if (isEnd(node.right) && isEnd(node.left.right)) {
			node.nR = 0;
			node.nL = 0;
			node.left.nR = 1;
		} else if (isEnd(node.right) && !isEnd(node.left.right)) {
			node.nR = 0;
			node.nL = 1 + node.left.right.nR + node.left.right.nL;
			node.left.nR = 2 + node.left.right.nR + node.left.right.nL;
		} else if (!isEnd(node.right) && isEnd(node.left.right)) {
			node.nL = 0;
			node.left.nR = 2 + node.right.nR + node.right.nL;
		} else {
			node.nL = 1 + node.left.right.nR + node.left.right.nL;
			node.left.nR = 3 + node.right.nR + node.right.nL + node.left.right.nR
					+ node.left.right.nL;
		}
	}

	public void insert(String ele) {
		addNode(new RedBlackTree.Node<String>(ele));
	}

	private void addNode(RedBlackTree.Node<String> node) {
		RedBlackTree.Node<String> newNode = root;
		RedBlackTree.Node<String> nodePtr = nil;

		while (!isEnd(newNode)) {
			nodePtr = newNode;

			if (node.compareTo(newNode) < 0) {
				newNode.nL++;
				newNode = newNode.left;
			} else {
				newNode.nR++;
				newNode = newNode.right;
			}
		}

		node.parent = nodePtr;

		if (isEnd(nodePtr))
			root = node;
		else if (node.compareTo(nodePtr) < 0)
			nodePtr.left = node;
		else
			nodePtr.right = node;

		node.left = nil;
		node.right = nil;
		node.color = RedBlackTree.Node.RED;

		fixTree(node);
	}

	private void fixTree(RedBlackTree.Node<String> node) {
		RedBlackTree.Node<String> newNode = nil;

		while (node.parent.color == RedBlackTree.Node.RED) {
			if (node.parent == getGrandparent(node).left) {
				newNode = getGrandparent(node).right;

				if (newNode.color ==RedBlackTree.Node.RED) {
					node.parent.color = RedBlackTree.Node.BLACK;
					newNode.color = RedBlackTree.Node.BLACK;
					getGrandparent(node).color = RedBlackTree.Node.RED;
					node = getGrandparent(node);
				} else if (node == node.parent.right) {
					node = node.parent;
					leftRotate(node);
				} else {
					node.parent.color = RedBlackTree.Node.BLACK;
					getGrandparent(node).color = RedBlackTree.Node.RED;
					rightRotate(getGrandparent(node));
				}
			} else {
				newNode = getGrandparent(node).left;

				if (newNode.color == RedBlackTree.Node.RED) {
					node.parent.color = RedBlackTree.Node.BLACK;
					newNode.color = RedBlackTree.Node.BLACK;
					getGrandparent(node).color = RedBlackTree.Node.RED;
					node = getGrandparent(node);
				} else if (node == node.parent.left) {
					node = node.parent;
					rightRotate(node);
				} else {
					node.parent.color = RedBlackTree.Node.BLACK;
					getGrandparent(node).color = RedBlackTree.Node.RED;
					leftRotate(getGrandparent(node));
				}
			}
		}

		root.color = RedBlackTree.Node.BLACK;
	}

	public RedBlackTree.Node<String> lookup(String key) {
		RedBlackTree.Node<String> node = root;

		while (!isEnd(node)) {
			if (node.key.equals(key))
				return node;
			else if (node.key.compareTo(key) < 0)
				node = node.right;
			else
				node = node.left;
		}

		return null;
	}

	public boolean searchExisting(String ele) {
		RedBlackTree.Node<String> node = root;

		while (!isEnd(node)) {
			if (node.key.equals(ele))
				return true;
			else if (node.key.compareTo(ele) < 0)
				node = node.right;
			else
				node = node.left;
		}

		return false;
	}

	public void printTree() {
		RedBlackTree.Node<String> currentNode = root;
		printTreePreorder(currentNode);
	}

	private void printTreePreorder(RedBlackTree.Node<String> node) {
		if (!isEnd(node)) {
			System.out.print(node.key + " ");
			printTreePreorder(node.left);
			printTreePreorder(node.right);
		}
	}

	public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> node) {
		if (isEnd(node.parent))
			return null;
		else if (node == node.parent.left)
			return node.parent.right;
		else
			return node.parent.left;
	}

	public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> node) {
		if (!isEnd(node.parent)) {
			if (!isEnd(node.parent.parent))
				return node.parent.parent;
			else
				return null;
		} else
			return null;
	}

	public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> node) {
		if (!isEnd(getGrandparent(node))) {
			if (node.parent == getGrandparent(node).left)
				return getGrandparent(node).right;
			else
				return getGrandparent(node).left;
		} else
			return null;
	}

	private boolean isEnd(RedBlackTree.Node<String> node) {
		return node == nil;
	}

	public int nodeNum() {
		return root.nL + root.nR + 1;
	}

	public void preOrderVisit(Visitor<String> v) {
		preOrderVisit(root, v);
	}
	
	public boolean isEmpty(RedBlackTree.Node<String> n){
		if (isEnd(n)){
			return true;
		}
		return false;
	}

	public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child)
	{
		if (child.compareTo(parent) < 0 ) {//child is less than parent
			return true;
		}
		return false;
	}
	
	private void preOrderVisit(RedBlackTree.Node<String> node, Visitor<String> v) {
		if (!isEnd(node)) {
			v.visit(node);
			preOrderVisit(node.left, v);
			preOrderVisit(node.right, v);
		}
	}
}
