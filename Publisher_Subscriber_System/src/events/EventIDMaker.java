package events;


/**
 *
 * @author Shulan Yang
 * this class is for creating an event ID
 */
public class EventIDMaker {

	private static long eventUIDs = 0L;
	
	/**
	 * return the event ID
	 * @return the next number of type {@link long}in the series of UID for the AbstractEvent derived classes
	 */
	protected static long getNewEventID() {
		return eventUIDs++;
	}
	
}
