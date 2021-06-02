
public class DLLDriver {
	public static void main(String[] args) {
		DoublyLinkedList<String> dll = new DoublyLinkedList<>();
		dll.addFirst("A");
		dll.addFirst("B");
		dll.addLast("C");
		dll.addFirst("D");
		System.out.println(dll);
		dll.removeFirst();
		System.out.println("After removal of first:\n"+dll);
		
		dll.removeLast();
		System.out.println("After removal of last:\n"+dll);
		
	}
}
