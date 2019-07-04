package pt.ipleiria.estg.dei.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class FileHandler {

	private String filename;
	
	public FileHandler(String filename) {
		this.filename = filename;
	}

	public String readFile(){
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			
			reader = new BufferedReader(new FileReader(new File(FileHandler.class.getResource(filename).toURI())));
			
			String line = "";
			while ((line = reader.readLine())!=null)
				sb.append(line).append("\n");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		return sb.toString();
		
	}
	
}

