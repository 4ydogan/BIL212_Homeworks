public class PalindromeDriver {

	public static void main(String[] args) {
		final int NUMBER = 80000;
		
		DoublyLinkedList<String> list = new DoublyLinkedList<>();
		
		for(int i=0; i<NUMBER; i++) {
			list.addFirst((char)(111) + "");
		}
		
		long start = System.currentTimeMillis();
		
		System.out.println(isPalindrome1(list));
		
		long end = System.currentTimeMillis();
	       
		System.out.println(end-start);

		
	}
	
	public static <E extends Comparable<E>> boolean isPalindrome1(DoublyLinkedList<E> list) {
		long time = 0;
		
		int size = list.size()+1;
		
		for (int i = 1; i < Math.floor(size/2.0); i++) {
			E e = list.get(i);
			E m = list.get(size-i);
			
			time++;
			if(e.compareTo(m) != 0) {
				System.out.println(time);
				return false;
			}
		}
		System.out.println(time);
		return true;
	}
}
