package events;


/**
 * @author Shulan Yang
 * the enumeration of available options for the event type
 * an Event using the {@link EventFactory#createEvent(EventType, int, EventMessage)} 
 * method
 * 
 * for every new @link {@link AbstractEvent} subclass created, a new entry should be added here
 *  
 */
public enum EventType {
	TypeA, TypeB, Default
}
