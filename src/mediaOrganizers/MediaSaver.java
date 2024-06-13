package mediaOrganizers;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import mediaClasses.MediaItem;
import mediaClasses.Movie;
import mediaClasses.PersonHistory;
import mediaClasses.Reviewable;
import mediaClasses.Season;
import mediaClasses.TVShow;

/*
 * Saves a persons media to a file to be loaded when the 
 * program is run again
 */
public class MediaSaver {

	/*
	 * Throws null exception if person is null
	 * @param p - person who's history is being saved
	 */
	public static void saveHistory(PersonHistory p){
		if(p == null) {
			throw new NullPointerException();
		}
		//printwriter allows formatted printing
		//filewriter handles the actual printing of data to the file, the outpot stream to file
		
		/*Try with resources statement
		//more concise than having a finally statement
		//putting PrintWriter Creation in the Try statement defines it as a resource contained in the 
		//try catch, these resources are automatically closed when try block exits
		//this being useful when exceptions quit unexpectedly
		 */
		try(PrintWriter pr = new PrintWriter(new FileWriter(p.getName() + "MediaFile.txt"), false)) {
			ArrayList<MediaItem> media = p.getMedia();
			saveMediaItems(pr, media);
			ArrayList<Movie> movies = p.getMovies();
			saveMovieItems(pr, movies);
			ArrayList<TVShow> shows = p.getShows();
			saveTVShowItems(pr, shows);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Given printwriter to users file, and list of their media, it prints them
	 * in the right format:
	    MI name year month day
		rating review
	 * @param pr - allows formatted printing to file stream
	 * @param media - list of MediaItems
	 */
	private static void saveMediaItems(PrintWriter pr, ArrayList<MediaItem> media) {
		for(MediaItem m : media) {
			pr.println("MI " + m.getName() + " " + getDateString(m.getFinishDate()));
			
			printReview(pr, m);
		}
	}
	
	/*
	 * Given printwriter to users file, and list of their Movies, it prints them
	 * in the right format:
	    	MO name year month day movieLength
			Rating review
	 * @param pr - allows formatted printing to file stream
	 * @param movies - list of Movie
	 */
	private static void saveMovieItems(PrintWriter pr, ArrayList<Movie> movies) {
		for(Movie m : movies) {
			pr.println("MO " + m.getName() + " " + getDateString(m.getFinishDate()) + " " + m.getLength());
			printReview(pr, m);
		}
	}
	
	/*
	 * Given printwriter to users file, and list of their TVShows, it prints them
	 * in the right format:
	    MI name year month day
		rating review
	 * @param pr - allows formatted printing to file stream
	 * @param media - list of TVshow
	 */
	private static void saveTVShowItems(PrintWriter pr, ArrayList<TVShow> shows) {
		for(TVShow tv : shows) {
			ArrayList<Season> seasons = new ArrayList<Season>();
			pr.println("TV " + tv.getName() + " " + getDateString(tv.getFinishDate()) + " " + seasons.size());
			printReview(pr, tv);
			for(Season s : seasons) {
				saveSeasonItem(pr, s);
			}
		}
	}
	
	/*
	 * saves specific season to the file
	 * @param pr
	 * @param s - season to add
	 */
	private static void saveSeasonItem(PrintWriter pr, Season s) {
		int numEpisodes = s.getEpisodes();
		String output = s.getName() + " " + getDateString(s.getFinishDate());
		if(numEpisodes != -1) {//-1 means episodes not set
			output += " " + numEpisodes;
		}
		pr.println(output);
		printReview(pr, s);
	}
	
	/*
	 * @param date
	 * @return year month day
	 */
	private static String getDateString(LocalDate date) {
		return date.getYear() + " " + date.getMonthValue() + " " + date.getDayOfMonth();
	}
	
	/*
	 * Prints review on MediaItem to Pr's stream
	 * @param pr
	 * @param m - any reviewable object
	 */
	private static void printReview(PrintWriter pr, Reviewable m) {
		if(m.getRating() != -1) {//-1 means no rating
			pr.println(m.getRating() + " " + m.getReview());
		}
	}
}
