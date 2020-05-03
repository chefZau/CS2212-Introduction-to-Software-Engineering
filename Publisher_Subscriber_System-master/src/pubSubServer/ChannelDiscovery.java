package pubSubServer;

import java.util.List;

import subscribers.AbstractSubscriber;


/**
 * @author Shunxin Pang
 * MUST IMPLEMENT the Singleton design pattern
 * Allows for the discovery of available channels for subscription from {@link AbstractSubscriber} that want to subscribe to them
 */
public class ChannelDiscovery {
	
	private static ChannelDiscovery instance;
	
	/**
	 * find the specified channel
	 */
	private ChannelDiscovery() {}
	
	/**
	 * return the instance of channel discovery
	 * @return instance the instance of channel discovery
	 */
	public static ChannelDiscovery getInstance() {
		if(instance == null) {
			instance = new ChannelDiscovery();
		}
		return instance;
	}
	
	/**
	 * return a list of channels objects
	 * @return a list of {@link AbstractChannel} type elements containing all available channels 
	 * currently in the PubSubServer 
	 */
	public List<AbstractChannel> listChannels() {
		return ChannelPoolManager.getInstance().listChannels();
	}

	/**
	 * return the channel object that is found
	 * @param channelName the topic/name of the looked-up channel
	 * @return a {@link AbstractChannel} type object corresponding the the channelName provided as input
	 */
	protected AbstractChannel findChannel(String channelName) {
		return ChannelPoolManager.getInstance().getChannelsMap().get(channelName);
	}
	
	
	
}
