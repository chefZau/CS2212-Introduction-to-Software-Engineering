package events;

/**
 * @author Shulan Yang
 * this class is for creating an object that contains the event message--header and body
 * EventMessage is the content within the Event object, and contains both the header and body 
 */
public class EventMessage {
	private String header = null;
	private String body = null;
	
	
	/**
	 * Constructor for creating a EventMessageObject
	 * @param header String value containing the header of the Event
	 * @param body String value containing the body of the Event
	 */
	public EventMessage(String header, String body) {
		this.header = header;
		this.body = body;
	}

	/**
	 * return the header of event message
	 * @return the String containing the Event.header data
	 */
	protected String getHeader() {
		return header;
	}

	/**
	 * return the body of event message
	 * @return the String containing the Event.body data
	 */
	protected String getBody() {
		return body;
	}
	
	
}
