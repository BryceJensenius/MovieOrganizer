package userInteraction;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exceptions.InvalidCommandException;
import mediaClasses.MediaItem;
import mediaClasses.PersonHistory;
import mediaOrganizers.MediaLoader;
import mediaOrganizers.MediaSaver;

/*
 * Repeatedly reads used commands and does specific actions
 * when exit is entered, it saves and exits
 */
public class CommandReader {
	
	public static PersonHistory p = null;//bad practice? Who knows!
	public static Scanner scnr = new Scanner(System.in);
	
	public static void main(String[] args) {
		PersonHistory p = null;//person that is currently logged in
		
		System.out.println("Please Remember to Sign in.");
		
		while(true) {
			String command = scnr.nextLine();//get command from terminal
			try {
				int status = readCommandLine(command);
				if(status == -1) {//-1 means exit
					scnr.close();
					return;
				}else if(status == 2) {//unknown command inputed
					System.out.println("Invalid Command.\n");
				}
			}catch(InvalidCommandException e) {
				System.out.println(e);
			}
		}
	}
	
	/*
	 * Reads one line of command
	 * @param command - string command to read from
	 * @return -1 for exit, 2 for invalid input, other for succeed
	 */
	private static int readCommandLine(String command) throws InvalidCommandException{
		if(command.length() < 2 || command.contains("  ")) {//user entered an empty string
			return 2;
		}
		
		Scanner sscnr = new Scanner(command);//scanner to read one part of command at a time
		String input = sscnr.next();
		
		if(input.equals("login")) {
			logIntoAccount(sscnr);//read log in info from the line
			return 1;
		}
		
		if(p == null) {//cannot run other command if not logged in
			sscnr.close();
			return 2;
		}
		
		switch(input) {
			case "exit"://exit program
				sscnr.close();
				
				if(p != null) {//if they have signed in, save their history
					MediaSaver.saveHistory(p);
				}
				return -1;//to exit the program
			case "add":
				addElementToUser(sscnr);
				return 1;
			case "list":
				readListPrompt(sscnr);
				return 1;
			default:
				sscnr.close();
				return 2;
		}
	}
	
	/*
	 * Reads commands starting with list
	 * Lists elements of type that they specify after list -> 
	 * "list MI" for list media Items
	 * Not specifying
	 * @param sscnr - scans from list command input
	 */
	private static void readListPrompt(Scanner sscnr) {
		String type = null;
		
		if(sscnr.hasNext()) {//see if they specified a type to list
			type = sscnr.next().toLowerCase();
		}else {//must do this here or null input to switch will throw exception
			//listAllMediaItems();
			return;
		}
		
		switch(type) {
			case "mi":
				printMediaItems();
				break;
			case "mo":
				//printMovieItems();
				break;
			case "tv":
				//promptTVShowItems();
				break;
		}
	}
	
	/*
	 * Prints all users media Items toString()
	 */
	private static void printMediaItems() {
		for(MediaItem m : p.getMedia()) {
			System.out.println(m);
		}
	}
	
	/*
	 * Command: add ElementClass
	 * 
	 * Constructs an element specified in the command and adds to user
	 * @param sscnr - scanner reading from command string
	 */
	private static void addElementToUser(Scanner sscnr) {
		try {
			String type = sscnr.next().toLowerCase();
			
			switch(type) {
				case "mi":
					promptMediaItemCreation();
					break;
				case "mo":
					promptMovieItemCreation();
					break;
				case "tv":
					promptTVShowCreation();
					break;
			}
		}catch(NoSuchElementException e) {
			throw new InvalidCommandException("MediaItem Type to Add Not Provided.");
		}
	}
	
	/*
	 * prints out prompts for constructing a media Item
	 * 
	 * Review Must come with a rating, no date is current date
	 * 
	 * Input for creation should be name *MM-DD-YYYY* *rating* *review*
	 * with ** elements optional
	 */
	private static void promptMediaItemCreation() {
		System.out.println("Enter Information, follow *Optional Info*\n(NOTE) No Date Uses Current Date, "
				+ "review must come with rating\n");
		System.out.println("name *MM-DD-YYYY* *rating* *review*");
		String input = scnr.nextLine();//get creation information
		Scanner sscnr = new Scanner(input);//point scanner to media Item Input Line
		
		addMediaItemFromInput(sscnr);
	}
	
	/*
	 * reads input line with information for media Item
	 * 
	 * Review Must come with a rating, no date is current date
	 * 
	 * Input for creation should be name *MM-DD-YYYY* *rating* *review*
	 * with ** elements optional
	 * 
	 * @param sscnr - scanner pointing to string with media item information
	 */
	private static void addMediaItemFromInput(Scanner sscnr) {
		MediaItem m = null;
		try {
			String name = sscnr.next();
			
			//Check Date
			if(sscnr.hasNext() && !(sscnr.hasNextInt())) {//has date input? It input is integer, it is rating, not date
				m = new MediaItem(name, sscnr.next());
			}else {//no date input
				m = new MediaItem(name);
			}
			
			//Check Review
			if(sscnr.hasNextInt()) {//has a rating?
				String review;
				int rating = sscnr.nextInt();
				m.giveRating(rating);
				if(sscnr.hasNext()) {//has a Review?
					review = sscnr.nextLine().trim();//get review at the end of the input line, trim removing space at beginning/end
					m.giveReview(review);
				}
			}
			
			p.addMedia(m);//add the media to user history
		}catch(InputMismatchException e) {
			System.out.println("Creation Failed, Ensure input format is followed.\n");
		}catch(Exception e) {
			System.out.println("Creation Failed.");
		}
	}
	
	/*
	 * prints out prompts for constructing a movie Item
	 */
	private static void promptMovieItemCreation() {
		
	}
	
	/*
	 * prints out prompts for constructing a TVShow
	 */
	private static void promptTVShowCreation() {
		
	}
	
	/*
	 * Command: login FirstName LastName
	 * 
	 * Loads a persons history of makes a new person from scratch if they have no file
	 * @return PersonHistory Object with loaded history
	 * @param sscnr - scanner pointing to line with login information
	 */
	private static void logIntoAccount(Scanner sscnr) {
		if(p != null) {//if someone is logged in, save their history first
			MediaSaver.saveHistory(p);
		}
		
		String name = null;
		try {
			name = sscnr.next() + sscnr.next();//name used for file is firstLast
			p = MediaLoader.loadHistory(name);
		}catch(NoSuchElementException e) {
			throw new InvalidCommandException("First and Last Name not Provided for Log In.");
		}catch(FileNotFoundException e) {//thrown if user doesn't have any history so new user is made
			p = new PersonHistory(name);
		}
	}
}
