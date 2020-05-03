package events;


/**
 * @author Shulan Yang
 *
 * this class is for creating the event type A
 *
 * {@link AbstractEvent#AbstractEvent(long, int, EventMessage)}   
 */
class EventTypeA extends AbstractEvent {

	
	/**
	 * shouldn't be used outside the EventFactory
	 * for information on how to call it and what the parameters are please look 
	 * at the documentation {@link EventFactory#createEvent(EventType, int, EventMessage)}
	 *
	 *
	 * Description: use the super() method to create an event
	 * 
	 * @param eventID The identity of event
	 * @param eventPublisherId The identity of event that is publishered
	 * @param payload  The payload information for the event 
	 */
	protected EventTypeA(long eventID, long eventPublisherId, EventMessage payload) {
		super(eventID, eventPublisherId, payload);
	}

}
