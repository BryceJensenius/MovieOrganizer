package userInteraction;

import java.io.FileNotFoundException;
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
				if(readCommandLine(command) == -1) {//-1 means exit
					scnr.close();
					return;
				}
			}catch(InvalidCommandException e) {
				System.out.println(e);
			}
		}
	}
	
	/*
	 * Reads one line of command
	 * @param command - string command to read from
	 * @return -1 for exit, other for succeed
	 */
	private static int readCommandLine(String command) throws InvalidCommandException{
		Scanner sscnr = new Scanner(command);//scanner to read one part of command at a time
		String input = sscnr.next();
		
		switch(input) {
			case "exit"://exit program
				sscnr.close();
				
				if(p != null) {//if they have signed in, save their history
					MediaSaver.saveHistory(p);
				}
				return -1;//to exit the program
			case "login"://log into account
				logIntoAccount(sscnr);//read log in info from the line
				break;
			case "add":
				addElementToUser(sscnr);
			default:
				return 1;
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
			String type = sscnr.next();
			
			switch(type) {
				case "MediaItem":
					promptMediaItemCreation();
			}
		}catch(NoSuchElementException e) {
			throw new InvalidCommandException("MediaItem Type to Add Not Provided.");
		}
	}
	
	private static void promptMediaItemCreation() {
		MediaItem m;
		System.out.print("Enter MediaItem Name: ");
		String name = scnr.next();
		System.out.print("(OPTIONAL) Date MM-DD-YYYY: ");
		//dm = new MediaItem()
		System.out.print("(OPTIONAL) Rating out of 10: ");
		System.out.print("Review? (y/n): ");
		
		String input = scnr.next();
		if(input.equals("y")) {
			
		}
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
