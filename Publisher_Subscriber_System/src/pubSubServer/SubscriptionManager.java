package pubSubServer;

import subscribers.AbstractSubscriber;


/**
<<<<<<< HEAD
 * @author MingCong, Zhou
 * @author Shunxin Pang
 * MUST IMPLEMENT the Singleton Design Pattern and 
 * already implements the Proxy Design Pattern
 *  exposes the subscribe, and unsubscribe methods to the clients
 */
public class SubscriptionManager {

	/* data member initialization */
	private ChannelPoolManager cpManager;
	private static SubscriptionManager instance;


	/**
	 *	channel pool manager holds the collection of AbstractChannel type entities
	 *	this method would create a new record in ChannelPoolManager
	 * creates an instance for subscription manager
	 */
	private SubscriptionManager() {
		cpManager = ChannelPoolManager.getInstance();
	}


	/**
	 *	this method is use to get SubscriptionManager
	 *	if the instance does noe exist a new SubscriptionManager
	 * 	will be created
	 * return the instance of subscription manager
	 * @return instance the instance of subscription manager
	 */
	public static SubscriptionManager getInstance() {
		if(instance == null) {
			instance = new SubscriptionManager();
		}
		return instance;
	}


	/**
	 * Completes the subscription of the provided ISubscriber to the appropriate AbstractChannel specified by the channelName
	 * @param channelName the name of the AbstractChannel to which the ISubscriber wants to subscribe
	 * @param subscriber the reference to an ISubscriber object
	 */
	public void subscribe(String channelName, AbstractSubscriber subscriber) {
		System.out.format("Subscriber %d subscribes to channel %s\n",
				subscriber.getSubscriberID(),channelName);
		AbstractChannel channel = cpManager.findChannel(channelName);
		channel.subscribe(subscriber);

	}

	/**
	 * Completes the unsubscription of the provided ISubscriber from the specified, by the channelName, AbstractChannel
	 * @param channelName the name of the AbstractChannel from which the ISubscriber wants to unsubscribe
	 * @param subscriber the reference to an ISubscriber object
	 */
	public void unSubscribe(String channelName, AbstractSubscriber subscriber) {
		System.out.format("Subscriber %d unsubscribes channel %s\n",
				subscriber.getSubscriberID(),channelName);
		AbstractChannel channel = cpManager.findChannel(channelName);
		channel.unsubscribe(subscriber);

	}

}
