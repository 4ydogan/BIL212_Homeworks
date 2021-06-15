
public class Watcher implements Comparable, Copy {
	private double latitude;
	private double longitude;
	private String watchersName;
	
	public Watcher() {} // default constructor
	
	public Watcher(double latitude, double longitude, String watchersName) {
		this();
		this.latitude = latitude;
		this.longitude = longitude;
		this.watchersName = watchersName;
	}
	
	public Watcher(Watcher w) { // copy constructor
		this(w.getLatitude(), w.getLongitude(), w.getWatchersName());
	}

	// beginning of getter and setter methods
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

	@Override
	public int compareTo(Object o) {
		return 0;
	}
	// end of getter and setter methods

	@Override
	public Object copy() {
		return new Watcher(this);
	}
}
