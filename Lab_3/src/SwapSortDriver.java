public class SwapSortDriver {

	public static void main(String[] args) {
		LinkedPositionalList<Double> list = new LinkedPositionalList<>();
		

		
		for (int i = 10; i > 0; i--) {
			list.addLast(i+0.0);
		}
		
		System.out.println("------------------------------------------");
		System.out.println("My list:");
		System.out.println(list);
		
		System.out.println("------------------------------------------");
		System.out.println("Testing for the swap method");
		swap(list, list.after(list.first()), list.before(list.last()));
		System.out.println(list);
		
		System.out.println("------------------------------------------");
		System.out.println("Testing for the sort method");
		sort(list);
		System.out.println(list);
	}
	
	public static <E> void swap(LinkedPositionalList<E> list, Position<E> pos1, Position<E> pos2) {
		
		Position<E> walk = pos1;
		try {
			while(list.after(walk) != pos2) {
			walk = list.after(walk);
			}				
		}catch (Exception e) {
			Position<E> temp = pos1;
			pos1 = pos2;
			pos2 = temp;
		}
		
		E temp1 = pos1.getElement();
		E temp2 = pos2.getElement();
		list.addAfter(pos1, temp2);
		list.addBefore(pos2, temp1);
		
		list.remove(pos1);
		list.remove(pos2);
	}
	
	 public static <E extends Number> void sort(LinkedPositionalList<E> list) {

		 try{
			 Position<E> pos1 = list.first();
			 Position<E> pos2 = list.after(pos1);
			 			 
			 while(!isSort(list)) {
				try{
					if(pos1.getElement().doubleValue() - pos2.getElement().doubleValue() > 0) {
						swap(list,pos1,pos2);
						pos1 = list.first();
						pos2 = list.after(pos1);
					}else {
						pos1 = list.after(pos1);
						pos2 = list.after(pos1);
					}
				}catch (Exception e) {}
			 }
		 }catch (Exception e) {}
	  }
	 
	 public static <E extends Number> boolean isSort(LinkedPositionalList<E> list) {
			
		 try {
			 Position<E> pos1 = list.first();
			 Position<E> pos2 = list.after(pos1);
			 
			for (int i = 0; i < list.size()-1; i++) {
				if(pos1.getElement().doubleValue() > pos2.getElement().doubleValue())
					return false;
				pos1 = list.after(pos1);
				pos2 = list.after(pos1);
			}
		 }catch (Exception e) {}
		return true;
	 }
}
