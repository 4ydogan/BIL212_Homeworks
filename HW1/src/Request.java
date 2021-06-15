
public class Request implements Comparable {
	
	private int timestamp;
	private String request;
	private double latitude;
	private double longitude;
	private String watchersName;
	
	public Request() {} // default constructor
	
	public Request(int timestamp, String request, double latitude, double longitude, String watchersName) {
		this();
		this.timestamp = timestamp;
		this.request = request;
		this.latitude = latitude;
		this.longitude = longitude;
		this.watchersName = watchersName;
	}
	
	public Request(Request w) { // copy constructor
		this(w.getTimestamp(), w.getRequest(), w.getLatitude(), w.getLongitude(), w.getWatchersName());
	}

	// beginning of getter and setter methods
	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getWatchersName() {
		return watchersName;
	}

	public void setWatchersName(String watchersName) {
		this.watchersName = watchersName;
	}
	// end of getter and setter methods

	@Override
	public int compareTo(Object o) {
		Request w = (Request) o;
		if(timestamp < w.getTimestamp())
			return -1;
		else if (timestamp > w.getTimestamp())
			return 1;
		else return 0;
	}
	
	@Override
	public String toString() {
		return timestamp + " " + request + " " + latitude + " " + longitude + " " + watchersName + "\n";
	}
}
