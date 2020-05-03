package pubSubServer;

import events.AbstractEvent;
import publishers.AbstractPublisher;

import java.util.List;


/**
 * @author Jiachen Luo
 * MUST IMPLEMENT the Singleton design pattern
 * Class providing an interface for {@link AbstractPublisher} objects to cover their publishing needs 
 */
public class ChannelEventDispatcher {

	/**
	 * Declare attributes
	 */
	private ChannelPoolManager cpManager;
	private static ChannelEventDispatcher instance;
	
	/**
	 * creates an instance for channel pool manager
	 */
	private ChannelEventDispatcher() {
		cpManager = ChannelPoolManager.getInstance();
	}
	
	/**
	 * return the instance of channel pool manager
	 * @return instance the instance of channel pool manager
	 */
	public static ChannelEventDispatcher getInstance() {
		if(instance == null) {
			instance = new ChannelEventDispatcher();
		}
		
		return instance;
	}

	/**
	 * post an event that need to be published to a list of channel
	 * @param event event to be published
	 * @param listOfChannels list of channel names to which the event must be published to 
	 */
	public void postEvent(AbstractEvent event, List<String> listOfChannels) {
		
		for(String channelName : listOfChannels) {
			AbstractChannel channel = cpManager.findChannel(channelName);
			if(channel == null) {
				channel = ChannelCreator.getInstance().addChannel(channelName);
			} 
			System.out.format("Channel %s has event %d from publisher %d\n", 
					channelName,event.getEventType(),event.getEventPublisher());
			channel.publishEvent(event);
		}
	}
	
	
}
