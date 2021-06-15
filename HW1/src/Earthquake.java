
public class Earthquake implements Comparable, Copy {
	
	private int id;
	private int time;
	private String place;
	private String coordinates;
	private double magnitude;
	
	public Earthquake() {} // default constructor
	
	public Earthquake(int id, int time, String place, String coordinates, double magnitude) {
		this();
		this.id = id;
		this.time = time;
		this.place = place;
		this.coordinates = coordinates;
		this.magnitude = magnitude;
	}
	
	public Earthquake(Earthquake e) { // copy constructor
		this(e.getId(), e.getTime(), e.getPlace(), e.getCoordinates(), e.getMagnitude());
	}
	
	// beginning of getter and setter methods
	public int getId() {			
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
	// ends of getter and setter methods
	
	@Override
	public String toString() {
		return "Id: " + id + "\n" +
			   "Time: " + time + "\n" +
			   "Place: " + place + "\n" +
			   "Coordinates: " + coordinates + "\n" +
			   "Magnitude: " + magnitude + "\n";
	}

	@Override
	public int compareTo(Object o) {
		Earthquake e = (Earthquake) o;
		if(magnitude < e.getMagnitude())
			return -1;
		else if (magnitude > e.getMagnitude())
			return 1;
		else return 0;
	}

	@Override
	public Object copy() {
		return new Earthquake(this);
	}
}
