package sjsu.Zhao.cs146.project3;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class RBTTester {

	@Test
	public void test() {
		RedBlackTree<String> rbt = new RedBlackTree<String>();
		rbt.insert("D");
		rbt.insert("B");
		rbt.insert("A");
		rbt.insert("C");
		rbt.insert("F");
		rbt.insert("E");
		rbt.insert("H");
		rbt.insert("G");
		rbt.insert("I");
		rbt.insert("J");
		assertEquals("DBACFEHGIJ", makeString(rbt));
		String str = "Color: 1, Key:D Parent: null\n" + "Color: 1, Key:B Parent: D\n" + "Color: 1, Key:A Parent: B\n"
				+ "Color: 1, Key:C Parent: B\n" + "Color: 1, Key:F Parent: D\n" + "Color: 1, Key:E Parent: F\n"
				+ "Color: 0, Key:H Parent: F\n" + "Color: 1, Key:G Parent: H\n" + "Color: 1, Key:I Parent: H\n"
				+ "Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
	}

	public static String makeString(RedBlackTree<String> t) {
		class MyVisitor implements RedBlackTree.Visitor<String> {
			String result = "";

			public void visit(RedBlackTree.Node<String> n) {
				result = result + n.key;
			}
		}
		;
		MyVisitor v = new MyVisitor();
		t.preOrderVisit(v);
		return v.result;
	}

	public static String makeStringDetails(RedBlackTree<String> t) {
		{
			class MyVisitor implements RedBlackTree.Visitor<String> {
				String result = "";

				public void visit(RedBlackTree.Node<String> n) {
					if (!(n.key).equals(""))
						result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: " + n.parent.key + "\n";

				}
			}
			;
			MyVisitor v = new MyVisitor();
			t.preOrderVisit(v);
			return v.result;
		}
	}

	@Test
	public void testTiming() throws IOException {
		RedBlackTree<String> rbt = new RedBlackTree<String>();
		String dictionary = "D:/Java/cs146/src/sjsu/Zhao/cs146/project3/dic.txt";
		String searchFile = "D:/Java/cs146/src/sjsu/Zhao/cs146/project3/simple.txt";
		String line = "";
		boolean search;

		FileReader fileReader = new FileReader(dictionary);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		long start = System.currentTimeMillis();

		while ((line = bufferedReader.readLine()) != null)
			rbt.insert(line);

		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("In order to insert the dictionary, we use: " + elapsed + " ms.");
		System.out.println("So, there exist " + rbt.nodeNum() + " words in our dictionary.");

		bufferedReader.close();

		fileReader = new FileReader(searchFile);
		bufferedReader = new BufferedReader(fileReader);

		start = System.currentTimeMillis();

		while ((line = bufferedReader.readLine()) != null) {
			search = rbt.searchExisting(line);
		}

		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("In order to search these words, we use: " + elapsed + " ms.");

		bufferedReader.close();
	}
}
