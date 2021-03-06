package events;

import publishers.AbstractPublisher;


/**
 *
 * @author Shulan Yang
 * this class is for creating a event
 */
public class EventFactory {

	
	/**
	 * This is an implementation of the Factory Method design pattern
	 * Creates an instance of any of the subclasses derived from the top level class AbstractEvent
	 * 
	 * @param eventType a member of the {@link EventType} enumeration
	 * @param eventPublisherId a number generated by invoking the  {@link AbstractPublisher#hashCode()} on the {@link AbstractPublisher} instance issuing the event
	 * @param payload an object of type {@link EventMessage}
	 * @return an event created
	 */
	public static AbstractEvent createEvent(EventType eventType, long eventPublisherId, EventMessage payload) {
		
		long eventID = EventIDMaker.getNewEventID();
		switch(eventType) {
			case TypeA:
				return new EventTypeA(eventID, eventPublisherId, payload);
			case TypeB:
				return new EventTypeB(eventID, eventPublisherId, payload);
			default:
				return new EventTypeDefault(eventID, eventPublisherId, payload);
		}
		
	}
	
}
