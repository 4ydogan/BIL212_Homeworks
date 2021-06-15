
public class PrepareWatcher {
	 private Watcher[] data;
	 private DoublyLinkedList<Request> listRequest;
	 private int index = 0;
	 
	 public Watcher[] getData() {
		return data;
	}
	 
	 public void setData(Watcher[] data) {
		this.data = data;
	}
	 
	public PrepareWatcher(DoublyLinkedList<Request> listRequest) {
		data = new Watcher[1000];
		for(int i=0; i<listRequest.size(); i++) {
			Request request = listRequest.get(i);
			String method = request.getRequest();
			if(method.equals("add")) {
				data[index] = new Watcher(request.getLatitude(), request.getLongitude(), request.getWatchersName());
				index++;
			}
			else if(method.equals("delete")) {
				boolean hasDone = false;
				for(int k=0; k<data.length; k++) {
					if(data[k].equals(request.getWatchersName())) {
						data[k] = null;
						hasDone = true;
					}
					if(hasDone && k < data.length-1) {
						data[k] = data[k+1];
					}
					if(hasDone && k == data.length-1) {
						data[k] = null;
					}
				}
			}
			else if(method.equals("query-largest")) {
				
			}
		}
	}
}
