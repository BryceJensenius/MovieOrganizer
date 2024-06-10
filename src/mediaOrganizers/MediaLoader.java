package mediaOrganizers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import mediaClasses.MediaItem;
import mediaClasses.Movie;
import mediaClasses.PersonHistory;
import mediaClasses.TVShow;

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
		Scanner sscnr = null;
		
		try {
			while((line = br.readLine()) != null) {
				sscnr = new Scanner(line);
				String type = sscnr.next();
				
				switch(type) {
				case "MI"://MediaItem
					MediaItem m = readMediaLine(line);
					
					if(sscnr.hasNextInt()) {//number means a rating
						readRating(m, sscnr.nextLine());//adds line of rating to mediaitem
					}
					p.addMedia(readMediaLine(line));//add item to person
					break;
				case "MO"://Movie
					p.addMovie(readMovieLine(line));
					break;
				case "TV"://TVShow
					p.addShow(readShowLine(br, line));
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			sscnr.close();
		}
	}
	
	/*
	 * Line has a rating and possible review
	 * Adds the review to the mediaItem
	 * @param m - media item to add it to
	 * @param line
	 */
	public static void readRating(MediaItem m, String line) {
		Scanner sscnr = new Scanner(line);
		m.giveRating(sscnr.nextInt());
		
		if(sscnr.hasNext()) {//check if their is a review with the rating
			//nextLine gets everything until the end, trim removes whitespace
			m.giveReview(sscnr.nextLine().trim());
		}
		
		sscnr.close();
	}
	
	/*
	 * Input line of Text is a stored media item
	 * puts info into mediaItem object
	 * @param line
	 * @return m - media item to add
	 */
	private static MediaItem readMediaLine(String line) {
		MediaItem m;
		Scanner sscnr = new Scanner(line);
		String name = sscnr.next();
		
		//get date
		int year = sscnr.nextInt();
		int month = sscnr.nextInt();
		int day = sscnr.nextInt();
		
		//make media item with this name and finish date
		m = new MediaItem(name, makeDate(year, month, day));
		
		sscnr.close();
		return m;
	}
	
	/*
	 * Input line is a stored Movie Item
	 * puts info into movie object
	 * 
	 * @param line
	 * @return m - movie Item to add
	 */
	private static Movie readMovieLine(String line) {
		Movie m;
		
		return m;
	}
	
	/*
	 * Input line is a stored TVShow Item
	 * puts info into movie object
	 * Seasons of this show will be numbers so they can be identified
	 * 
	 * @param br - used to read input
	 * @param line
	 * @return m - TVShow Item to add
	 */
	private static TVShow readShowLine(BufferedReader br, String line) {
		TVShow t;
		
		return t;
	}
	
	private static LocalDate makeDate(int year, int month, int day) {
		return LocalDate.of(year, month, day);
	}
}
