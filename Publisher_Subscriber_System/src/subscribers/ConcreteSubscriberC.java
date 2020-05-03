package subscribers;

import events.AbstractEvent;
import pubSubServer.SubscriptionManager;
import states.subscriber.IState;
import states.subscriber.StateFactory;
import states.subscriber.StateName;



/**
 * @author yansheng Xie
 * concrate the subscriber C ,we also extend from the sbstractSubscriber 
 * the subscriber have it own state 
 */
class ConcreteSubscriberC extends AbstractSubscriber {

	
	protected ConcreteSubscriberC(long subscriberID) {
		state = StateFactory.createState(StateName.defaultState);
		this.subscriberID = subscriberID;
	}
	
	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#setState(states.subscriber.StateName)
	 */
	public void setState(StateName stateName) {
		state = StateFactory.createState(stateName);
	}
	
	
	/* (non-Javadoc)
	 * @see the following 3 class all depend the input channelName to choose which channel i need to subscribe
	 * and at the end print out the alert message to make sure if the subscriber really want to subscriber the channel.
	 */ 
	@Override
	public void alert(AbstractEvent event, String channelName) {
		
		System.out.format("Subscriber %d receives event %d and ", 
				this.getSubscriberID(),event.getEventType());
		state.handleEvent(event, channelName);
	}

	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#subscribe(java.lang.String)
	 */
	@Override
	public void subscribe(String channelName) {
		SubscriptionManager.getInstance().subscribe(channelName, this);		
	}

	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#unsubscribe(java.lang.String)
	 */
	@Override
	public void unsubscribe(String channelName) {
		SubscriptionManager.getInstance().subscribe(channelName, this);
		
	}
	
	
}
