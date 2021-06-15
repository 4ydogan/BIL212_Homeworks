
public class ListDriver {
	
	DoublyLinkedList<Watcher> listWatcher;
	Queue<Request> listRequest;
	DoublyLinkedList<Earthquake> listEarthquake;
	Queue<Earthquake> listInfo;
	
	public ListDriver() {}  // default constructor

	public ListDriver(DoublyLinkedList<Watcher> listWatcher,  Queue<Request> listRequest, DoublyLinkedList<Earthquake> listEarthquake, Queue<Earthquake> listInfo) {
		this();
		this.listWatcher = listWatcher;
		this.listRequest = listRequest;
		this.listEarthquake = listEarthquake;
		this.listInfo = listInfo;
	}
		
	public void add(Request request) { // add the watcher to listWatcher
		listWatcher.addLast(new Watcher(request.getLatitude(), request.getLongitude(), request.getWatchersName()));
		System.out.println(request.getWatchersName() + " is added to the watcher-list\n");
		
	}
	
	public void delete(String name) { // remove the watcher from listWatcher
		
		boolean hasDone = false;
		for(int i=1; i<=listWatcher.size(); i++) {
			if(listWatcher.get(i).getWatchersName().equals(name)) {
				listWatcher.remove(i);
				System.out.println(name + " is removed from the watcher-list\n");
				hasDone = true;
			}
		}
		
		if(!hasDone) { 	// if the watcher couldn't remove from listWatcher 
			System.out.println(name + " could not remove from the watcher-list\n");
		}
	}
	
	public void queryLargest() { // prints the largest earthquake in past 6 hours
		DoublyLinkedList<Earthquake> orderedList = copyList(listEarthquake);
		orderedList.sort();
		if(orderedList.isEmpty()) {
			System.out.println("No record on list\n");
		}else {
			System.out.println("Largest earthquake in the past 6 hours:\n"
					+ "Magnitude " + orderedList.last().getMagnitude() + " at " + orderedList.last().getPlace());
			System.out.println();
		}
	}
	
	// copy the list and returns
	public static <E extends Comparable & Copy> DoublyLinkedList<E> copyList(DoublyLinkedList<E> list) {
		DoublyLinkedList<E> newList = new DoublyLinkedList<E>();
		for(int i=1; i<=list.size(); i++) {
			newList.addLast((E)(list.get(i).copy()));
		}
		return newList;
	}

	// the movements are occurs here
	public void next(int time, boolean type) {

		updateListEarthquake(listEarthquake, time); // removes old earthquakes
		
		while(!listRequest.isEmpty() && listRequest.first().getTimestamp() == time) { // loads requests
			Request request = listRequest.dequeue();
			process(request);
		}
		while(!listInfo.isEmpty() && listInfo.first().getTime() == time) { // loads earthquakes
			Earthquake earthquake = listInfo.dequeue();
			listEarthquake.addLast(earthquake);
			if(type)
				System.out.println("Earthquake " + earthquake.getPlace()+ " is inserted into the magnitude-ordered-list");
			for(int i=1; i<=listWatcher.size(); i++) {
				Watcher watcher = listWatcher.get(i);
				if(isClose(earthquake, watcher)) {
					System.out.println("Earthquake " + earthquake.getPlace() + " is close to " + watcher.getWatchersName());
				}
			}
			System.out.println();
		}
	}
	
	// adds, deletes or queryLargest
	public void process(Request request) {
		String method = request.getRequest();
		if(method.equals("add")) {
			add(request);
		}
		else if(method.equals("delete")) {
			delete(request.getWatchersName());
		}
		else if(method.equals("query-largest")) {
			queryLargest();
		}
	}
	
	// to control simulations is finished
	public boolean isFinished() {
		return (listInfo.first() == null && listRequest.first() == null);
	}
	
	// updates the listEarthquake to removes older than 6 hours
	public static void updateListEarthquake(DoublyLinkedList<Earthquake> listEarthquake, int time) {
		while(!listEarthquake.isEmpty() && listEarthquake.first().getTime() < time -6) {
			if(listEarthquake.first().getTime() < time -6) {
				listEarthquake.removeFirst();
			}
		}
	}
	
	// to control that the watcher is close the earthquake
	public static boolean isClose(Earthquake e, Watcher w) {
		String[] coordinates = e.getCoordinates().split(", ");
		double latitude = Double.parseDouble(coordinates[0]);
		double longitude = Double.parseDouble(coordinates[1]);
		double distance_lat = Math.abs(latitude - w.getLatitude());
		double distance_long = Math.abs(longitude - w.getLongitude());
		
		double distance = Math.sqrt(distance_lat*distance_lat + distance_long*distance_long);
		if(distance < Math.pow(e.getMagnitude(),3)*2)
			return true;
		return false;
	}
}
