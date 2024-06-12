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
import mediaClasses.Season;
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
			line = br.readLine();
			while(line != null) {
				MediaItem m;
				sscnr = new Scanner(line);
				String type = sscnr.next();
				
				switch(type) {
				case "MI"://MediaItem
					m = readMediaLine(sscnr.nextLine().trim());//trim type off the start of line
					line = br.readLine();//read next line to check for review
					sscnr.close();//close and open scanner for new line
					sscnr = new Scanner(line);
					if(sscnr.hasNextInt()) {//number means a rating
						readRating(m, line);//adds line of rating to mediaitem
						line = br.readLine();//read line for next iteration of loop
					}
					p.addMedia(m);//add item to person
					break;
				case "MO"://Movie
					m = readMovieLine(sscnr.nextLine().trim());
					line = br.readLine();
					sscnr.close();
					sscnr = new Scanner(line);
					if(sscnr.hasNextInt()) {//number means a rating
						readRating(m, line);//adds line of rating to mediaitem
						line = br.readLine();
					}
					break;
				case "TV"://TVShow, I like how clean this looks, until looking at the method
					line = readShowLine(br, sscnr.nextLine().trim(), p);//returns the next line to be read
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
		Scanner sscnr = new Scanner(line);
		String name = sscnr.next();
		
		//get date
		int year = sscnr.nextInt();//add try here and throws an exception, if so tell them it failed and check the file
		int month = sscnr.nextInt();
		int day = sscnr.nextInt();
		int length = sscnr.nextInt();
		
		//make movie with this name, finish date, and length
		m = new Movie(name, makeDate(year, month, day), length);
		
		sscnr.close();
		return m;
	}
	
	/*
	 * Input line is a stored TVShow Item
	 * puts info into movie object
	 * Seasons of this show will be numbers so they can be identified
	 * 
	 * @param br - used to read input
	 * @param line
	 * @param p - person to add to
	 * @return line - next line for MediaLoader to read from
	 */
	private static String readShowLine(BufferedReader br, String line, PersonHistory p) throws IOException {
		TVShow m;
		Scanner sscnr = new Scanner(line);
		String name = sscnr.next();
		
		//get date
		int year = sscnr.nextInt();
		int month = sscnr.nextInt();
		int day = sscnr.nextInt();
		
		//make TVShow item with this name and finish date
		m = new TVShow(name, makeDate(year, month, day));
		
		int numSeasons = sscnr.nextInt();
		
		//all other iterations
		line = br.readLine();//check for review or season
		sscnr.close();
		sscnr = new Scanner(line);
		if(sscnr.hasNextInt()) {//number means a rating
			readRating(m, line);//adds line of rating to mediaitem
			line = br.readLine();//grab line for next season
		}
		for(int i = 0; i < numSeasons; i++) {//read a season line for numSeasons
			Season s;
			sscnr.close();//close scanner to be safe
			sscnr = new Scanner(line);//open scanner on new line read
			name = sscnr.next();
			
			//get date
			year = sscnr.nextInt();
			month = sscnr.nextInt();
			day = sscnr.nextInt();
			
			//make TVShow item with this name and finish date
			s = new Season(name, makeDate(year, month, day));
			
			if(sscnr.hasNextInt()) {//check if numEpisodes is at end, add if so
				s.setEpisodes(sscnr.nextInt());
			}
			
			line = br.readLine();//read next line to check for review
			sscnr.close();//close and open scanner for new line
			sscnr = new Scanner(line);
			if(sscnr.hasNextInt()) {//number means a rating
				readRating(m, line);//adds line of rating on season
				line = br.readLine();//read line for next iteration of loop OR to be returned if it is next media item
			}
			
			sscnr.close();
			m.addSeason(s);//add the season to the show
		}
		
		p.addShow(m);//add show to person
		return line;//this line was read at the end of the last iteration of loop and is next medai item
	}
	
	
	private static LocalDate makeDate(int year, int month, int day) {
		return LocalDate.of(year, month, day);
	}
}
