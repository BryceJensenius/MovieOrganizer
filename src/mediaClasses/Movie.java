package mediaClasses;

public class Movie extends MediaItem{
	protected int lengthMin;
	
	/*
	 * Creates Movie with given name and finish date as Current Date
	 * @param name - name of this media item
	 * @param movieLength - length of this movie in minutes
	 */
	public Movie(String name, int movieLength) {
		super(name);
		lengthMin = movieLength;
	}
	
	/*
	 * Creates Movie with given name and given finish date
	 * @param name - name of this media item
	 * @param date - date this media was finished
	 * @param movieLength - length of this movie in minutes
	 */
	public Movie(String name, String date, int movieLength) {
		super(name, date);
		lengthMin = movieLength;
	}
	
	/*
	 * @return lengthMin - length of this movie in minutes
	 */
	public int getLength() {
		return lengthMin;
	}
	
	/*
	 * Sets this movies Length
	 * @param length - minute length
	 */
	public void setLength(int length) {
		this.lengthMin = length;
	}
	
	@Override
	public String toString() {
		return this.name + ": " + getRating() + "/10 stars, " + getLength() + " minutes";
	}
}
