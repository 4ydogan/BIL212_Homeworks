
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReceiveRequest {
	
	private Request[] data;
	private int index = -1;
	
	public ReceiveRequest() {}  // default constructor

	public ReceiveRequest(String fileName) {
		
		this();
		data = new Request[1000];
		Request request = new Request();
		 		
		File file = new File(fileName); // create file
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			// opening file
			String line = br.readLine(); // reading line
			
			while(line != null && !line.equals("")) { // loop to read every line
				String[] strArray = line.split(" ");  // split the line
				request = new Request();			  // create request object
				index++;
				updateRequest(request, strArray);	  // update request		
				updateData(request, index);			  // add request to the data array
				line = br.readLine();				  // reading line
			}
			br.close();								  // closing the file
		}catch (Exception e) {
			System.out.println("File I/O error!");
		}
	}
	
	// beginning of getter and setter methods
	public Request[] getData() {
		return data;
	}

	public void setData(Request[] data) {
		this.data = data;
	}
	
	public int size() {
		return index+1;
	}
	// end of getter and setter methods
		
	private void updateData(Request request, int index) { // update the array with given request
		Request newRequest = new Request(request);
		data[index] = newRequest;		
	}
	
	private void updateRequest(Request request, String[] data) { // set the data with given request 
		try {
			request.setTimestamp(Integer.parseInt(data[0]));    // set the id 
		}catch (Exception e) {}
		
		try {
			request.setRequest(data[1]);						// set the request
			if(data[1].equals("delete"))
				request.setWatchersName(data[2]);				// if the data[1] is delete set watchersName 
		}catch (Exception e) {}
		
		try {
			request.setLatitude(Double.parseDouble(data[2]));   // set the latitude
		}catch (Exception e) {}
		
		try {
			request.setLongitude(Double.parseDouble(data[3]));	// set the longitude
		}catch (Exception e) {}
		
		try {
			request.setWatchersName(data[4]);					// set the watchersName
		}catch (Exception e) {}
	}
	
}
