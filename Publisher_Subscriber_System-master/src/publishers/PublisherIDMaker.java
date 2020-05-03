package publishers;

public class PublisherIDMaker {
	private static long publisherUIDs = 0L;
	
	/**
	 * return the event ID
	 * @return the next number of type {@link long}in the series of UID for the AbstractEvent derived classes
	 */
	protected static long getNewPublisherID() {
		return publisherUIDs++;
	}
}
