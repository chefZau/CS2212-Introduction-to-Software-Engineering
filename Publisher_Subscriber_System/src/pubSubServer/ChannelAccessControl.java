package pubSubServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import subscribers.AbstractSubscriber;


/**

 * @author MingCong, Zhou

 * @author Shunxin Pang

 * MUST Implements the Singleton design pattern
 * Class that acts as an access control module that allows for the
 * blocking and unblocking of specific subscribers for specific channels
 */
public class ChannelAccessControl {
	private static ChannelAccessControl instance;
	private Map<String, List<AbstractSubscriber>> blackList;

	
	/**
	 * creates a black list by using hash map
	 */
	private ChannelAccessControl() {
		blackList = new HashMap<>();
	}
	
	/**
	 * return the instance of channel access control
	 * @return instance the instance of channel access control
	 */

	public static ChannelAccessControl getInstance() {
		if(instance==null) {
			instance = new ChannelAccessControl();
		}

		return instance;
	}





	
	/**
	 * blocks the provided subscriber from accessing the designated channel
	 * @param subscriber an instance of any implementation of {@link AbstractSubscriber}
	 * @param channelName a String value representing a valid channel name
	 */
	public void blockSubcriber(AbstractSubscriber subscriber, String channelName) {

		List<AbstractSubscriber> blockedSubscribers = blackList.getOrDefault(channelName, new ArrayList<AbstractSubscriber>());
		blockedSubscribers.add(subscriber);
		blackList.put(channelName, blockedSubscribers);
		System.out.format("Subscriber %d is blocked on Channel %s\n", 
				subscriber.getSubscriberID(),channelName);
	}

	/**
	 * unblocks the provided subscriber from accessing the designated channel
	 * @param subscriber an instance of any implementation of {@link AbstractSubscriber}
	 * @param channelName a String value representing a valid channel name
	 */
	public void unBlockSubscriber(AbstractSubscriber subscriber, String channelName) {

		List<AbstractSubscriber> blockedSubscribers;
		if((blockedSubscribers = blackList.get(channelName)) == null)
			return;
		blockedSubscribers.remove(subscriber);
		System.out.format("Subscriber %d is unblocked on Channel %s\n", 
				subscriber.getSubscriberID(),channelName);
	}


	/**
	 * checks if the provided subscriber is blocked from accessing the specified channel
	 * @param subscriber an instance of any implementation of {@link AbstractSubscriber}
	 * @param channelName a String value representing a valid channel name
	 * @return true if blocked false otherwise
	 */
	protected boolean checkIfBlocked(AbstractSubscriber subscriber, String channelName) {

		List<AbstractSubscriber> blockedSubscribers = blackList.get(channelName);
		if(blockedSubscribers== null) {
			return false;
		}else {
			return (blockedSubscribers.contains(subscriber));
		}
	}




}
