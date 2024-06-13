package mediaClasses;

import java.util.ArrayList;

public class PersonHistory {
	
	/*
	 * This persons name
	 */
	private String name;
	
	/*
	 * Shows this person has watched
	 */
	private ArrayList<TVShow> shows;
	
	/*
	 * Movies this person has watched
	 */
	private ArrayList<Movie> movies;
	
	/*
	 * Generic Media Item Just incase I want to use it
	 */
	private ArrayList<MediaItem> media;
	
	/*
	 * @param name
	 */
	public PersonHistory(String name) {
		this.name = name;
		
		shows = new ArrayList<TVShow>();
		movies = new ArrayList<Movie>();
		media = new ArrayList<MediaItem>();
	}
	
	/*
	 * @return name - name of this person
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * @param m - movie to add to history
	 */
	public void addMovie(Movie m) {
		movies.add(m);
	}
	
	/*
	 * @param s - TVShow to add to history
	 */
	public void addShow(TVShow s) {
		//Hmm, why didn't I call this addTVShow?
		shows.add(s);
	}
	
	/*
	 * @param m - Generic media Item
	 */
	public void addMedia(MediaItem m) {
		media.add(m);
	}
	
	/*
	 * Sets the list of shows this person has watched
	 * @param shows
	 */
	public void setShows(ArrayList<TVShow> shows) {
		this.shows = shows;
	}
	
	/*
	 * Sets the list of movies this person has watched
	 * @param movies
	 */
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	
	/*
	 * gets the list of shows this person has watched
	 * @param shows
	 */
	public ArrayList<TVShow> getShows() {
		return shows;
	}
	
	/*
	 * gets the list of movies this person has watched
	 * @return movies
	 */
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	/*
	 * @return media - all generic media items
	 */
	public ArrayList<MediaItem> getMedia(){
		return media;
	}
	
	@Override
	public String toString() {
		return name + shows.size() + " shows, " + movies.size() + " movies, " + media.size() + " media.";
	}
}
