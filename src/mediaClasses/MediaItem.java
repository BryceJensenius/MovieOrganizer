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
	 * -1 means no review
	 */
	protected int rating = -1;
	
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
	 * @param date - date this media was finished, MM-DD-YYYY
	 */
	public MediaItem(String name, String date) {
		if(name == null || date == null) {
			throw new NullPointerException("Name or Date are Null.");
		}
		this.name = name;
		setFinishDate(date);
	}
	
	/*
	 * @return name - name of this MediaItem
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Creates Media Item with given name and given finish date
	 * Used when created form MediaLoader
	 * @param name - name of this media item
	 * @param localDate - date this media was finished
	 */
	public MediaItem(String name, LocalDate localDate) {
		if(name == null || localDate == null) {
			throw new NullPointerException("Name or Date are Null.");
		}
		this.name = name;
		this.finishDate = localDate;
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
	 * no review provided so default none is given
	 * @param rating
	 */
	public void giveRating(int rating) {//***Potentially Not needed, Kept Just in case future Use***
		this.rating = Math.max(0, Math.min(rating, 10));
		giveReview("None");
	}
	
	/*
	 * sets rating in range of 0 to 10
	 * @param rating
	 * @param review - review to give with rating
	 */
	public void giveRating(int rating, String review) {
		
		if(review == null) {
			giveReview("None");
		}
		
		this.rating = Math.max(0, Math.min(rating, 10));
		giveReview(review);
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
