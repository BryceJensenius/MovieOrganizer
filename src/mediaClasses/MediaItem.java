package mediaClasses;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MediaItem implements Reviewable{
	
	/*
	 * Formatter for returning dates in media items
	 */
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	
	/*
	 * name of this media Item
	 */
	protected String name;
	
	/*
	 * date this media item was finished
	 */
	protected LocalDate finishDate;
	
	/*
	 * Media Items rating out of 10
	 */
	protected int rating;
	
	/*
	 * Optional Review of Media Item
	 */
	protected String review;
	
	/*
	 * Creates media Item with given name and finishdate as Current Date
	 * @param name - name of this media item
	 */
	public MediaItem(String name) {
		if(name == null) {
			throw new NullPointerException("Name is Null");
		}
		this.name = name;
		finishDate = LocalDate.now();
	}
	
	/*
	 * Creates Media Item with given name and given finish date
	 * @param name - name of this media item
	 * @param date - date this media was finished
	 */
	public MediaItem(String name, String date) {
		if(name == null || date == null) {
			throw new NullPointerException("Name or Date are Null.");
		}
		this.name = name;
		setFinishDate(date);
	}
	
	/*
	 * Sets the finish date to MM-DD-YYYY
	 * @param date - date the media was finished
	 */
	public void setFinishDate(String date) {
		if(date == null || date.length() != 10) {
			throw new NullPointerException("Date is Null.");
		}
		
		Scanner scnrS = new Scanner(date);
		scnrS.useDelimiter("-");
		int month = scnrS.nextInt();
		int day = scnrS.nextInt();
		int year = scnrS.nextInt();
		
		finishDate = LocalDate.of(year, month, day);//makes a new date with these values
		scnrS.close();
	}
	
	/*
	 * Returns finish date in a string based on dateFormatter
	 */
	public String getFinishDateString() {
        return finishDate.format(dateFormatter);
	}
	
	/*
	 * Returns finish date object reference
	 * @return finishDate
	 */
	public LocalDate getFinishDate() {
		return finishDate;
	}
	
	/*
	 * sets rating in range of 0 to 10
	 * @param rating
	 */
	public void giveRating(int rating) {
		this.rating = Math.max(0, Math.min(rating, 10));
	}
	
	/*
	 * Sets this media items review
	 * @param review
	 */
	public void giveReview(String review) {
		this.review = review;
	}
	
	/*
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}
	
	/*
	 * @return review
	 */
	public String getReview() {
		return review;
	}
	
	@Override
	public String toString() {
		return this.name + ": " + getRating() + "/10 " + getFinishDateString();
	}
	
	/*
	 * Set the date formatter that is used for all media Items
	 * yyyy for year
	 * MM for month
	 * dd for day
	 * @param format - String format dates are returned
	 */
	public static void setDateFormatter(String format) {
		dateFormatter = DateTimeFormatter.ofPattern(format);
	}
}
