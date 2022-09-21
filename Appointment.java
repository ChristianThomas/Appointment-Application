import java.util.Calendar;
import java.util.GregorianCalendar;

//CHRISTIAN THOMAS 500761706
/**
 * Class Appointment
 * 
 * @author Christian Thomas 500761706
 */
public class Appointment implements Comparable<Appointment> {

	private Calendar date;
	private String description;

	/**
	 * Constructor method creates an appointment
	 *
	 * @param y
	 *            int: used to set year of the appointments date
	 * @param m
	 *            int: used to set month of the appointments date
	 * @param d
	 *            int: used to set day of the appointments date
	 * @param h
	 *            int used to set hour of the appointments date
	 * @param min
	 *            int: used to set minute of the appointments date
	 * @param des
	 *            String: used to set description of the appointment
	 */
	// Constructor
	public Appointment(int y, int m, int d, int h, int min, String des) {
		date = new GregorianCalendar(y, m, d, h, min);
		description = des;
	}
	// Getters and Setters

	/**
	 * Returns date of this appointment instance
	 * 
	 * @return date Calendar
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Set the date of the appointment instance
	 * 
	 * @param date
	 *            Calendar: New date
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Returns the description of the appointment instance
	 * 
	 * @return description String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of the appointment instance
	 * 
	 * @param description
	 *            String: New Description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the Hour, minute and description on the appointment instance
	 * 
	 * @return String in format HOUR:MINUTE DESCRIPTION
	 */
	// Print Appointment time and Description
	public String toString() {
		String min;
		// if input < 10 add a 0 in front of the input else return the number
		if (date.get(Calendar.MINUTE) < 10) {
			min = "0" + date.get(Calendar.MINUTE);
			return date.get(Calendar.HOUR_OF_DAY) + ":" + min + " " + description;
		} else {
			return date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + " " + description;
		}
	}

	/**
	 * Checks to see if the time given matches the time of the appointment
	 * instance
	 * 
	 * @param year
	 *            int: compares to the year of the appointment instance
	 * @param month
	 *            int: compares to the month of the appointment instance
	 * @param day
	 *            int: compares to the day of the appointment instance
	 * @param hour
	 *            int: compares to the hour of the appointment instance
	 * @param minute
	 *            int: compares to the minute of the appointment instance
	 * @return Boolean true if given time matches instance time
	 * 
	 */
	// Check to see if time given conflicts with this instance appointment
	public Boolean occurs0n(int year, int month, int day, int hour, int minute) {
		Calendar testCalendar;
		testCalendar = new GregorianCalendar(year, month, day, hour, minute);
		if (this.date.compareTo(testCalendar) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Comparing a given appointment to the instance appointment
	 * 
	 * @return int positive if given comes after, negative if given comes
	 *         before, zero if they match
	 */
	// Compare Appointments by seeing which of the two times comes first
	public int compareTo(Appointment other) {
		if (this.date.compareTo(other.date) < 0)
			return -1;
		else if (this.date.compareTo(other.date) > 0)
			return 1;
		else
			return 0;
	}

}