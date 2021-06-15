
public class EarthquakeNotification  {

	public static void main(String[] args) {
		
		int time = 0;									// time 
		boolean type = false;							// whether --all is given or not
		String fileOfEarthquakes = null;				// file of earthquake records
		String fileOfWatchers = null;					// file of user request
	
		if(args.length == 5 && args[2].equals("--all")){
			type = true;								// if --all is given, type is true 
			fileOfWatchers = args[3];
			fileOfEarthquakes = args[4];
		}else {
			fileOfWatchers = args[2];
			fileOfEarthquakes = args[3];
		}
				
		ReceiveInformation receiveEarthquakes = new ReceiveInformation(fileOfEarthquakes);	// to read file of earthquake records
		ReceiveRequest receiveRequest = new ReceiveRequest(fileOfWatchers);			// to read file of user request
		
		Queue<Earthquake> listInformation = new Queue<>(receiveEarthquakes.getData(), receiveEarthquakes.size()); // the queue of earthquake records
		Queue<Request> listRequest = new Queue<>(receiveRequest.getData(), receiveRequest.size());  // the queue of user request
		
		DoublyLinkedList<Earthquake> listEarthquke = new DoublyLinkedList<>();	// the list of earthquake records
		DoublyLinkedList<Watcher> listWatcher = new DoublyLinkedList<>();		// the list of user request
		
		ListDriver listDriver = new ListDriver(listWatcher, listRequest, listEarthquke, listInformation); // to drive all lists and queues
				
		while(true && !listDriver.isFinished()) {		// each loop lasts 1 hour
			listDriver.next(time, type);				// moves here
			time++;
		}
	}
}
