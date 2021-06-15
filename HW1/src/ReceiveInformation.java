import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReceiveInformation {
	
	private Earthquake[] data;
	private int index = -1; 
	
	public Earthquake[] getData() {
		return data;
	}

	public void setData(Earthquake[] data) {
		this.data = data;
	}
	
	public int size() {
		return index+1;
	}
	
	public ReceiveInformation() {}  // default constructor
	
	public ReceiveInformation(String fileName) {
		
		this();
		data = new Earthquake[1000];
		Earthquake earthquake = new Earthquake();
		
		File file = new File(fileName); 							// create file
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			// opening file
			Stack<String> stack = new Stack<>(30000);
			String line = br.readLine(); 							// reading line
			
			while(line != null) {									// loop to read every line
				line = line.replaceAll("\t", "");					// edit line
				String[] strArray = line.split(" ");				// split the line
				updateData(strArray, stack, earthquake);			// update data
				
				line = br.readLine();								// reading line
			}
			br.close();												// closing the file
		}catch (Exception e) {
			System.out.println("File I/O error!");
		}
	}
	
	private static void update(Earthquake earthquake, String element, String type, int index, Earthquake[] data) {
	
		if (type.equals("<earthquake>")) {							// if the type earthquake
			Earthquake newEarthquake = new Earthquake(earthquake);	// copy earthquake
			data[index] = newEarthquake;							// add the earthquake to data array 
		}
		else if(type.equals("<id>")){								// if the type id
			earthquake.setId(Integer.parseInt(element));			// set id
		}
		else if (type.equals("<time>")){							// if the type time
			earthquake.setTime(Integer.parseInt(element));			// set time
		}
		else if (type.equals("<place>")){							// if the type place
			earthquake.setPlace(element);							// set place
		}
		else if (type.equals("<coordinates>")){						// if the type coordinates
			earthquake.setCoordinates(element);						// set coordinates
		}
		else if (type.equals("<magnitude>")){						// if the type magnitude
			earthquake.setMagnitude(Double.parseDouble(element));	// set magnitude
		}
	}

	private static boolean isAvailable(String[] array, String e) {	// controls whether the array has e
		for(String s : array) {
			if(s.equals(e)) {
				return true;
			}
		}
		return false;
	}
	
	private static int indexElement(String[] array, String e) {		// returns index of e element in array
		int index = 0;
		
		if(isAvailable(array, e)) {
			for(String s : array) {
				if(s.equals(e)) 
					return index;
				index++;
			}
		}
		
		return -1;
	}

	// update data
	public void updateData(String[] strArray, Stack<String> stack, Earthquake earthquake) {
		
		String[] open = new String[] {"<earthquake>", "<id>", "<time>", "<place>", "<coordinates>" , "<magnitude>"};
		String[] close = new String[] {"</earthquake>", "</id>", "</time>", "</place>", "</coordinates>" , "</magnitude>"};
		
		for(String s : strArray) {					
			if(isAvailable(open, s)) {								// if s is in open array  
				stack.push(s);										// push the s to stack
				if(s.equals(open[0])){								// if is an open earthquake
					earthquake = new Earthquake();					// create new Earthquake
					index++;
				}
			}
			else if(isAvailable(close, s)) {						// if s is in close array  
				String opening = open[indexElement(close, s)];		// opening element
				String element = "";								
				while(!stack.top().equals(opening)) {				// stack poping
					element = stack.pop() + " " + element;
				}
				
				// editing string
				if(!element.equals("") && element.charAt(element.length()-1) == ' ') {
					element = element.substring(0, element.length()-1);
				}
				
				// updating of data
				update(earthquake, element, stack.pop(), index, data);
			}
			else {
				stack.push(s);
			}
		}
	}
}
