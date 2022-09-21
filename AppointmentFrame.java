import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.TitledBorder;
//CHRISTIAN THOMAS 500761706
/**
 * Class Appointment Frame
 * 
 * @author Christian Thomas 500761706
 */
public class AppointmentFrame extends JFrame {
	// final variables
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 820;
	private static final int YEAR_WIDTH = 4;
	private static final int MONTH_WIDTH = 2;
	private static final int DAY_WIDTH = 2;
	private static final int HOUR_WIDTH = 2;
	private static final int MINUTE_WIDTH = 2;
	private static final int TEXTBOX_HEIGHT = 1;
	private static final int DES_HEIGHT = 4;
	private static final int DES_WIDTH = 25;
	private static final int APP_WIDTH = 28;
	private static final int APP_HEIGHT = 20;

	// instance variables
	private JPanel datePanel, viewPanel, controlPanel, CP1, CP2, CP3, arrows, input, show, input2, create;
	private JLabel date, inDay, inMonth, inYear, inHour, inMinute;
	private Calendar today;
	private SimpleDateFormat sdf;
	private TitledBorder title1, title2, title3;
	private JButton left, right, showButton, createButton, cancelButton;
	private JTextArea dayIn, monthIn, yearIn, hourIn, minuteIn, description, appointmentsText;
	private JScrollPane appointmentsSP;
	private ArrayList<Appointment> appointments;
	private ActionListener nextDay = new nextDay();
	private ActionListener previousDay = new previousDay();
	private ActionListener selectDay = new selectDay();
	private ActionListener createApp = new createApp();
	private ActionListener removeApp = new removeApp();

	/**
	 * 
	 * Constructor method that builds the appointment frame
	 */
	// constructor creates a calendar at todays time and an empty appointments
	// list and runs methods that creates the 3 main panels
	public AppointmentFrame() {
		today = Calendar.getInstance();
		// used to format the date
		sdf = new SimpleDateFormat("E, MMM dd, YYYY");
		date = new JLabel(sdf.format(today.getTime()));
		// arraylist that hold all appointment objects
		appointments = new ArrayList<Appointment>();
		mkDatePanel();
		mkViewPanel();
		mkControlPanel();
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * 
	 * Creates a Panel that displays the date of the calendar and adds it to the
	 * frame
	 */
	// Creates a Panel for the date and displays the date of the calendar (set
	// to the top)
	public void mkDatePanel() {
		datePanel = new JPanel();
		datePanel.add(date);
		this.add(datePanel, BorderLayout.NORTH);

	}

	/**
	 * 
	 * Creates a text area to display the appointments and adds it to the frame
	 */
	// creates a Panel for appointments and displays the appointments found in
	// the arraylist on the date (set to the center)
	public void mkViewPanel() {
		appointmentsText = new JTextArea(APP_HEIGHT, APP_WIDTH);
		appointmentsText.setEditable(false);
		appointmentsSP = new JScrollPane(appointmentsText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		viewPanel = new JPanel();
		viewPanel.add(appointmentsSP);
		this.add(viewPanel, BorderLayout.CENTER);
	}

	/**
	 * 
	 * Creates a panel containing all the controls for the appointment frame
	 */
	// creates a Control Panel that
	public void mkControlPanel() {
		controlPanel = new JPanel(new GridLayout(3, 1));
		CP1 = new JPanel(new GridLayout(3, 1));
		title1 = BorderFactory.createTitledBorder("Date");
		CP1.setBorder(title1);
		// code for day selection arrows
		arrows = new JPanel(new GridLayout(1, 2));
		left = new JButton("<");
		left.addActionListener(previousDay);
		right = new JButton(">");
		right.addActionListener(nextDay);
		arrows.add(left);
		arrows.add(right);
		CP1.add(arrows);
		// code for skip to day
		input = new JPanel();
		inDay = new JLabel("Day:");
		input.add(inDay);
		dayIn = new JTextArea(TEXTBOX_HEIGHT, DAY_WIDTH);
		input.add(dayIn);
		inMonth = new JLabel("Month:");
		input.add(inMonth);
		monthIn = new JTextArea(TEXTBOX_HEIGHT, MONTH_WIDTH);
		input.add(monthIn);
		inYear = new JLabel("year:");
		input.add(inYear);
		yearIn = new JTextArea(TEXTBOX_HEIGHT, YEAR_WIDTH);
		input.add(yearIn);
		CP1.add(input);
		// code for show button
		show = new JPanel();
		showButton = new JButton("Show");
		showButton.addActionListener(selectDay);
		show.add(showButton);
		CP1.add(show);
		// code for create and cancel buttons/input
		CP2 = new JPanel(new GridLayout(2, 1));
		title2 = BorderFactory.createTitledBorder("Action");
		CP2.setBorder(title2);
		input2 = new JPanel();
		inHour = new JLabel("Hour:");
		input2.add(inHour);
		hourIn = new JTextArea(TEXTBOX_HEIGHT, HOUR_WIDTH);
		input2.add(hourIn);
		inMinute = new JLabel("Minute:");
		input2.add(inMinute);
		minuteIn = new JTextArea(TEXTBOX_HEIGHT, MINUTE_WIDTH);
		input2.add(minuteIn);
		CP2.add(input2);
		create = new JPanel();
		createButton = new JButton("Create");
		createButton.addActionListener(createApp);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(removeApp);
		create.add(createButton);
		create.add(cancelButton);
		CP2.add(create);
		// code for descrpition panel
		CP3 = new JPanel();
		title3 = BorderFactory.createTitledBorder("Description");
		CP3.setBorder(title3);
		description = new JTextArea(DES_HEIGHT, DES_WIDTH);
		CP3.add(description);
		// add 3 sub panels to control panel
		controlPanel.add(CP1);
		controlPanel.add(CP2);
		controlPanel.add(CP3);
		// add subpanel to frame
		this.add(controlPanel, BorderLayout.SOUTH);

	}

	/**
	 * 
	 * Sorts and prints the appointments on the given day to the textArea
	 * specified for appointments
	 */
	// sorts arraylist of appointments and prints all matching the current date
	// to screen
	public void printAppointments() {
		appointmentsText.setText("");
		// sort the appointments arraylist based on the compareTo method
		Collections.sort(appointments);
		// checks each appointment if it is on the current day and displays it
		// to screen if true
		for (int x = 0; x < appointments.size(); x++) {
			if (appointments.get(x).getDate().get(Calendar.YEAR) == today.get(Calendar.YEAR)
					&& (appointments.get(x).getDate().get(Calendar.MONTH) == today.get(Calendar.MONTH))
					&& (appointments.get(x).getDate().get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH))) {
				appointmentsText.append(appointments.get(x).toString() + "\n");
			}
		}
	}

	/**
	 * 
	 * Takes in a time and checks to see if it matches any appointments in the
	 * array of appointments
	 * 
	 * @param year
	 *            int: used to compare against the year of the appointment
	 * @param month
	 *            int: used to compare against the month of the appointment
	 * @param day
	 *            int: used to compare against the day of the appointment
	 * @param hour
	 *            int: used to compare against the hour of the appointment
	 * @param minute
	 *            int: used to compare against the minute of the appointment
	 * @return appointment that matches time given, otherwise returns null
	 */
	// checks to see if the time given matches any appointments in the array of
	// appointments, returns the matching appointment
	public Appointment findAppointment(int year, int month, int day, int hour, int minute) {
		for (int x = 0; x < appointments.size(); x++) {
			if (appointments.get(x).occurs0n(year, month, day, hour, minute) == true) {
				return appointments.get(x);
			}
		}
		return null;
	}

	/**
	 * 
	 * Checks to see if the time inputed in the control panel matches an
	 * appointment in the arraylist, if yes remove it
	 */
	// checks too see if an appointment occurs on the time interval inputed into
	// the control panel, if yes it removes the appointment from the array
	public void cancelAppointment() {
		// check if minute text field has input, if false default minute to 00
		int min;
		if (minuteIn.getText().equals("")) {
			min = 0;
		} else {
			min = Integer.parseInt(minuteIn.getText());
			System.out.println(min);
		}
		// if an appointment matches given time remove that appointment
		if (findAppointment(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),
				Integer.parseInt(hourIn.getText()), min) != null) {
			appointments.remove((findAppointment(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
					today.get(Calendar.DAY_OF_MONTH), Integer.parseInt(hourIn.getText()), min)));
			System.out.println("test");
		}
		// reset input fields
		description.setText("");
		hourIn.setText("");
		minuteIn.setText("");
	}

	/**
	 * 
	 * Checks to see if the time inputed in the control panel matches an
	 * appointment in the arraylist, is not create the appointment
	 */
	public void createAppointment() {
		// Check if the minute field text field has input,if false default
		// minute to 00
		int min;
		if (minuteIn.getText().equals("")) {
			min = 0;
		} else {
			min = Integer.parseInt(minuteIn.getText());
			System.out.println(min);
		}
		// if appointment matching time inputed is not found create an
		// appointment, else change description field to CONFLICT!
		if (findAppointment(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),
				Integer.parseInt(hourIn.getText()), min) == null) {
			appointments.add(new Appointment(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
					today.get(Calendar.DAY_OF_MONTH), Integer.parseInt(hourIn.getText()), min, description.getText()));
			// reset input field
			description.setText("");
			hourIn.setText("");
			minuteIn.setText("");
		} else {
			description.setText("CONFLICT!");
		}
	}

	/**
	 * 
	 * ActionListener that increments the calendar date by 1 and prints the
	 * appointments on the new day
	 * 
	 * @author Christian
	 */
	class nextDay implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// increment date by 1
			today.add(Calendar.DATE, 1);
			date.setText(sdf.format(today.getTime()));
			description.setText("");
			hourIn.setText("");
			minuteIn.setText("");
			// print appointments on new date
			printAppointments();
		}
	}

	/**
	 * 
	 * ActionListener that decrements the calendar date by 1 and prints the
	 * appointments on the new day
	 * 
	 * @author Christian
	 */
	class previousDay implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// decrement date by 1
			today.add(Calendar.DATE, -1);
			date.setText(sdf.format(today.getTime()));
			description.setText("");
			hourIn.setText("");
			minuteIn.setText("");
			// print appointments on new date
			printAppointments();
		}
	}

	/**
	 * 
	 * ActionListener that retrieves the new date input and sets it to the new
	 * date and displays the corresponding appointments
	 * 
	 * @author Christian
	 */
	class selectDay implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			today.set(Integer.parseInt(yearIn.getText()), Integer.parseInt(monthIn.getText()) - 1,
					Integer.parseInt(dayIn.getText()));
			date.setText(sdf.format(today.getTime()));
			description.setText("");
			hourIn.setText("");
			minuteIn.setText("");
			printAppointments();
		}
	}

	/**
	 * 
	 * ActionListener that runs the create appointment and print appointment
	 * methods
	 * 
	 * @author Christian
	 */
	class createApp implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// runs create and print appointment methods
			createAppointment();
			printAppointments();
		}
	}

	/**
	 * 
	 * ActionListener that runs the remove appointment and print appointment
	 * methods
	 * 
	 * @author Christian
	 */
	class removeApp implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// runs remove and print appointment methods
			cancelAppointment();
			printAppointments();
		}
	}
}
