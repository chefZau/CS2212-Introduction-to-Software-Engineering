package subscribers;

public class SubscriberIDMaker {
	private static long subscriberUIDs = 0L;
	
	/**
	 * return the event ID
	 * @return the next number of type {@link long}in the series of UID for the AbstractEvent derived classes
	 */
	protected static long getNewSubscriberID() {
		return subscriberUIDs++;
	}
}
