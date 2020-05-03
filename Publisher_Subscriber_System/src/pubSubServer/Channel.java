package pubSubServer;

import events.AbstractEvent;
import subscribers.AbstractSubscriber;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;


/**
 * @author Jiachen Luo
 * this class is for creating a channel. Meanwile, this class also adding or removing the subscribers
 * in or from the subscribers's list. when there is a new event have published on a chennel, this
 * class would notify the subscribers.
 *  
 */
class Channel extends AbstractChannel {


	/**
	 * declare attributes for this class
	 */
	private Set<AbstractSubscriber> subscribers = new HashSet<AbstractSubscriber>();
	private String channelTopic;
	private Queue<AbstractEvent> events = new ArrayDeque<AbstractEvent>();
	
	/**
	 * constructer for creating a channel
	 */
	public Channel(String channelTopic) {
		this.channelTopic = channelTopic;
	}
	
	
	/* (non-Javadoc)
	 * @see pubSubServer.AbstractChannel#publishEvent(events.AbstractEvent)
	 * publish an event
	 */
	protected void publishEvent(AbstractEvent event) {
		events.add(event);
		notifySubscribers(event);

	}

	
	/* (non-Javadoc)
	 * @see pubSubServer.AbstractChannel#subscribe(subscribers.ISubscriber)
	 * add the subscriber into subscriber's list
	 */
	protected void subscribe(AbstractSubscriber subscriber) {
		subscribers.add(subscriber);
	}

	
	/* (non-Javadoc)
	 * @see pubSubServer.AbstractChannel#unsubscribe(subscribers.ISubscriber)
	 * remove the subscriber from subscriber's list
	 */
	protected void unsubscribe(AbstractSubscriber subscriber) {
		subscribers.remove(subscriber);
	}

	/**
	 * notify the subscribers when there is a new event publish on the channel
	 * @param event the event that's to be disseminated to the subscribers
	 */
	private void notifySubscribers(AbstractEvent event) {
		AbstractEvent currentEvent; 
		currentEvent = event;
		for(AbstractSubscriber subscriber : subscribers) {
			if(!ChannelAccessControl.getInstance().checkIfBlocked(subscriber, channelTopic)) {
				subscriber.alert(currentEvent, this.channelTopic);
			}
		}
	}

	
	/* (non-Javadoc)
	 * @see pubSubServer.AbstractChannel#getChannelTopic()
	 * @return return topic of the channel
	 */
	public String getChannelTopic() {
		return channelTopic;
	}

}
