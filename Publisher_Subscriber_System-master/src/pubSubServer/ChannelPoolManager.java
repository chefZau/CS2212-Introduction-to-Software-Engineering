package pubSubServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author MingCong, Zhou
 *
 *  implements the Singleton Design Pattern
 *
 * @author Jiachen Luo
 *  implements the Singleton Design Pattern
 *  holds the collection of AbstractChannel type entities and provides the methods for manipulating the collections
 */
public class ChannelPoolManager {

	/**
	 * Declare Attributes
	 */
	private static ChannelPoolManager instance = null;
	private Map<String, AbstractChannel> channelsMap = new HashMap<String, AbstractChannel>();
	private List<AbstractChannel> channelList = new ArrayList<AbstractChannel>();

	/**
	 *	This method is for reading channels.chl file
	 *  
	 */
	private ChannelPoolManager() {
		try {
		BufferedReader channelListReader = new BufferedReader(new FileReader(new File("Channels.chl")));
		while(channelListReader.ready())
			addChannel(channelListReader.readLine());
		channelListReader.close();
		}catch(IOException ioe) {
			System.out.println("Error with loading from file, creating one no_theme_channel");
			addChannel("no_theme");
		}
	}
	
	/**
	 * return the instance of channel pool manager
	 * @return instance the instance of channel pool manager
	 */
	protected static ChannelPoolManager getInstance() {

		if (instance == null)
			instance = new ChannelPoolManager();
		return instance;
	}


	/**
	 * creates a new channel, adds it to the channel list and returns it to the caller
	 * @param channelName the name of the new AbstractChannel
	 * @return the new channel that added to the channel list
	 */
	protected AbstractChannel addChannel(String channelName) {
		System.out.format("Channel %s created\n", channelName);
		Channel ch = new Channel(channelName);
		channelsMap.put(channelName, ch);
		channelList.add(ch);
		return ch;
	}


	/**
	 * removes a channel from the channel list if the channel is exist
	 * @param channelName the name of the channel that need to be remove
	 */
	protected void deleteChannel(String channelName) {
		System.out.format("Channel %s deleted\n", channelName);
		channelList.remove(channelsMap.get(channelName));
		channelsMap.remove(channelName);
	}


	/**
	 * return the list of the channel
	 * @return a list of type {@link AbstractChannel}
	 */
	protected List<AbstractChannel> listChannels() {
		return channelList;
	}


	/**
	 * return the channel that is wish to find,
	 * @param channelName the name of the channel
	 * @return the channel that is found or return null
	 */
	protected AbstractChannel findChannel(String channelName) {
		return channelsMap.get(channelName);
	}


	/**
	 * accessor for the ChannelMap use with great caution
	 * @return a Map from String to {@link AbstractChannel}
	 */
	protected Map<String, AbstractChannel> getChannelsMap() {
		return channelsMap;
	}

}
