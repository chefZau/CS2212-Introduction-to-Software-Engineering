package events;


/**
 * @author Shulan Yang
 *  the abstract base class for the event functionality
 */
public abstract class AbstractEvent {
	private long eventId = -1;
	private long eventPublisherId;
	private EventMessage payload = null;
	
	/**
	 * Constructor for the abstract class AbstractEvent
	 * @param eventID must be a number of type long generated from the {@link EventIDMaker#getNewEventID()} method 
	 * @param eventPublisher must be a number (currently is the hashCode of the object) designating the object
	 *                          issuing the event
	 * @param payload is a {@link EventMessage} which contains a title and a message for the event. Normally there
	 *                   should be a rich hierarchy of EventMessage types.
	 */
	public AbstractEvent(long eventID, long eventPublisher, EventMessage payload) {
		this.eventId = eventID;
		this.eventPublisherId = eventPublisher;
		this.payload = payload;
	}
	
	
	/**
	 * return the ID of the event
	 * @return a long value designating the EventType
	 */
	public long getEventType() {
		return eventId;
	}
	
	
	/**
	 * return the ID of the publisher
	 * @return Event Publisher's hashcode
	 */
	public long getEventPublisher() {
		return eventPublisherId;
	}
	
	
	/**
	 * returns the object payload
	 * @return an object of the Class {@link EventMessage}
	 */
	public EventMessage getPayload() {
		return payload;
	}
	
	
	
	
}
