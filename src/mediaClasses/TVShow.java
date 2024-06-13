package mediaClasses;

import java.time.LocalDate;
import java.util.ArrayList;

public class TVShow extends MediaItem{
	/*
	 * Each Season of this show
	 */
	protected ArrayList<Season> seasons;
	
	/*
	 * Creates TVShow with given name and finish date as Current Date
	 * @param name - name of this media item
	 * @param seasons - number of seasons in this show
	 */
	public TVShow(String name) {
		super(name);
		seasons = new ArrayList<Season>();
	}
	
	/*
	 * Creates TVShow with given name and given finish date
	 * @param name - name of this media item
	 * @param date - date this media was finished
	 */
	public TVShow(String name, String date) {
		super(name, date);
		seasons = new ArrayList<Season>();
	}
	
	/*
	 * Creates TVShow with given name and given finish date object
	 * Used by MediaLoader for Creation
	 * @param name - name of this media item
	 * @param localDate - localDate for when this media was finished
	 */
	public TVShow(String name, LocalDate localDate) {
		super(name, localDate);
		seasons = new ArrayList<Season>();
	}
	
	/*
	 * @return number of seasons in this show
	 * 		Could be 0
	 */
	public int getNumSeasons() {
		return seasons.size();
	}
	
	/*
	 * @return seasons
	 */
	public ArrayList<Season> getSeasons() {
		return seasons;
	}
	
	/*
	 * Adds a season to this show
	 * @param seasons
	 */
	public void addSeason(Season season) {
		if(season != null) {
			this.seasons.add(season);
		}
	}
	
	@Override
	public String toString() {
		return this.name + ": " + getRating() + "/10 stars, " + seasons.size() + " seasons";
	}
}
