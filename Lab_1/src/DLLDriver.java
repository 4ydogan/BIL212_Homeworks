public class DLLDriver {
	public static void main(String[] args) {
		DoublyLinkedList<String> dll = new DoublyLinkedList<>();
	
		System.out.println(dll);
		
		dll.addFirst("A");
		dll.addFirst("B");
		dll.addLast("C");
		dll.addFirst("D");
		
		System.out.println(dll);
		System.out.println("--------------------");
		
		System.out.println("Sorted Linked List:");
		dll.sort();
		System.out.println(dll);
		System.out.println("--------------------");

		System.out.println(dll);
		dll.removeLast();
		System.out.println(dll);
		dll.removeLast();
		System.out.println(dll);
		dll.removeLast();
		System.out.println(dll);
		dll.removeLast();
		System.out.println(dll);
		
		System.out.println("--------------------");

		dll.addLast("Z");
		System.out.println(dll);
		
		dll.addLast("Y");
		System.out.println(dll);
		
		dll.addLast("X");
		System.out.println(dll);
		
		dll.addLast("T");
		System.out.println(dll);
		
		dll.addLast("S");
		System.out.println(dll);
		
		dll.addLast("Q");
		System.out.println(dll);
		
		dll.addLast("R");		
		System.out.println(dll);
		System.out.println("--------------------");

		System.out.println("Sorted Linked List:");
		dll.sort();
		System.out.println(dll);
		System.out.println("--------------------");

		System.out.println("4th element: " + dll.get(4));
		System.out.println("3th element: " + dll.get(3));
		System.out.println("2th element: " + dll.get(2));
		System.out.println("1th element: " + dll.get(1));
		
		System.out.println("--------------------");

		System.out.println("\"R\" index: " + dll.find("R"));
		System.out.println("\"T\" index: " + dll.find("T"));
		System.out.println("\"X\" index: " + dll.find("X"));
		System.out.println("\"A\" index: " + dll.find("A"));
		System.out.println("\"V\" index: " + dll.find("V"));
	}
}
