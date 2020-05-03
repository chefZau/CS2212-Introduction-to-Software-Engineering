package strategies.publisher;

import java.util.ArrayList;
import java.util.List;

import events.AbstractEvent;
import events.EventFactory;
import events.EventMessage;
import events.EventType;
import pubSubServer.AbstractChannel;
import pubSubServer.ChannelDiscovery;
import pubSubServer.ChannelEventDispatcher;

/**
 * @author Shulan Yang
 * Class represents the strategy with default strategy by the fllowing method
 * this class is responsible for creating a strategy for the publisher
 * and also, this class is reponsible for publishing an event by the publisher's ID.
 */
public class DefaultStrategy extends AbstractStrategy {
	private List<String> listOfChannels; //TODO channels on which to publish
	private EventType defaultEventType; //TODO
	private String defaultHeader, defaultBody; //TODO
	
	/**
	 * creates a strategy with default strategy for the publisher
	 */
	protected DefaultStrategy() {
		listOfChannels = new ArrayList<String>();
		defaultEventType = EventType.TypeA;
		defaultHeader = "Default | Default Message Header";
		defaultBody = "Default | Default Message Body";
		
		List<AbstractChannel> channels = ChannelDiscovery.getInstance().listChannels();
		for(int i = 0;i<channels.size();i++) {
			listOfChannels.add(channels.get(i).getChannelTopic());
		}
		
		
	}
	
	/**
	 * publish an event by using the publiher's Id
	 * @param publisherId The identity for the specified publisher
	 * 
	 */
	public void doPublish(long publisherId) {
		EventMessage message = new EventMessage(defaultHeader, defaultBody);
		AbstractEvent event = EventFactory.createEvent(defaultEventType, publisherId, message);
		doPublish(event,publisherId);
	}

	/**
	 * publish an event by using the publisher's Id and the event already existed. 
	 * @param event Event that need to be published
	 * @param publisherId The identity for specified publisher 
	 */
	public void doPublish(AbstractEvent event, long publisherId) {
		System.out.format("Publisher %d publishes event %d\n", publisherId,event.getEventType());
		ChannelEventDispatcher.getInstance().postEvent(event, listOfChannels);
	}

}
