/*
 * Program title: WOMstory.java
 * Author: CEH Emma Civello, CA Alicia Chen
 * Description: A program that reads a save file from a user.
 */

package wrathOfManasses;

import java.io.File;	// import statements
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WOMfilereader {	// class header

	/*public static void main(String[] args) {	// main method header
		WOMfilereader fr = new WOMfilereader();
		fr.read();
	}*/
	
	public ArrayList<Object> read() {	// reads savefile
		
		Scanner carl = new Scanner(System.in);	// new Scanner object
		Scanner fileReader = null;
		ArrayList<Object> fileData = new ArrayList<>();
		
		String choice = "";
		String username = "";
		int count = 0;
		System.out.print("Do you have a preexisting save file? ");
		choice = carl.nextLine();
		
		if(choice.toLowerCase().equals("yes")) {
			
			System.out.print("What is your username (case sensitive)? ");
			username = carl.nextLine().replace(" ", "");
			
			try {
				String filename = username + "Savefile.txt";
				fileReader = new Scanner(new File(filename));	// read file
			}
			catch (FileNotFoundException ex) {System.out.print(ex);}
			
			while ( fileReader.hasNext() ){	//check if end of file
				String temp = fileReader.nextLine(); 
				if(count % 2 == 0)
					fileData.add(temp);
				count++;
			}
			
			fileData.set(2, listSplitter((String)(fileData.get(2))));
			fileData.set(3, listSplitter((String)(fileData.get(3))));

			//System.out.println(fileData);
			
		}
		System.out.println();
		
		return fileData;
		
	}
	
	private ArrayList<String> listSplitter (String dataIn){	// converts strings to list
		
		String[] partynames = dataIn.split("\t");
		return new ArrayList<>(Arrays.asList(partynames));
		
	}
	
}
