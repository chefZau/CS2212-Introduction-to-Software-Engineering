package pubSubServer;


/**
 * @author MingCong,Zhou
 * @author Shunxin Pang
 * MUST IMPLEMENT the Singleton design pattern
 * this class is responsible for creating and deleting channels
 * it's also the only class that can do so
 */
public class ChannelCreator {

	/* data member initialization */
	private static ChannelCreator instance;
	private ChannelPoolManager cpm;

	/**
	 *	channel pool manager holds the collection of AbstractChannel type entities
	 *	this method would create a new record in ChannelPoolManager
	 *  create a constructor of channel
	 */
	private ChannelCreator() {
		cpm = ChannelPoolManager.getInstance();
	}


	/**
	 * Detect a channel is exist or nor if the channel does not exist
	 * create a new channel
	 * @return instance a new channel is creted

	
	/**
	 * return the instance of channel creator
	 * @return instance the instance of channel creator
	 */
	protected static ChannelCreator getInstance() {
		if(instance==null) {
			instance = new ChannelCreator();
		}
		return instance;
	}

	/**
	 * creates a new Channel and adds it to the list of Channels so that it can be discovered using the
	 * {@link ChannelDiscovery} methods
	 * @param channelName name of the Channel to be created
	 * @return the new channel (of any type that extends the {@link AbstractChannel} that has been created
	 */
	protected AbstractChannel addChannel(String channelName) {

		return cpm.addChannel(channelName);
	}

	/**
	 * deletes a channel and removes it from all channels stores so that no one can access it anymore
	 * @param channelName name of the channel to be deleted
	 */
	protected void deleteChannel(String channelName) {

		cpm.deleteChannel(channelName);
	}

}
