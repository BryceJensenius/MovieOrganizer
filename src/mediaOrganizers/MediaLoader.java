package mediaOrganizers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import mediaClasses.PersonHistory;

/*
 * Loads a persons media from their file
 */
public class MediaLoader {
	
	/*
	 * Given a persons name, it loads their file and creates
	 * 
	 * If this person is not yet on file, it returns null
	 * a PersonHistory object holding all the loaded information
	 */
	public static PersonHistory loadHistory(String name) {
		PersonHistory p = new PersonHistory(name);
		
		String fileName = name + "MediaFile.txt";//get file name from their name
		File fp = new File(fileName);//open file
	
		try {
			FileReader fr = new FileReader(fp);//used to read from file
			BufferedReader br = new BufferedReader(fr);//supposedly makes file reading quicker
			
			LoadFromFile(br, p);
			
			br.close();
		} catch(FileNotFoundException e) {//they don't yet have a file
			return null;
		}catch (IOException e) {//required catch block for br.close()
			e.printStackTrace();
		}
		
		return p;
	}
	
	/*
	 * Does the actual reading from file and inputing
	 * into the persons history
	 * @param br - reader pointing to correct file
	 * @param p - person history to be filled
	 */
	private static void LoadFromFile(BufferedReader br, PersonHistory p) {
		String line;
		
		try {
			while((line = br.readLine()) != null) {
				
			}
		} catch (IOException e) {}
	}
}
