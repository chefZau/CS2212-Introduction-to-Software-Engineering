package subscribers;
/*
* import the class abstractEvent in from the event, 
* import the Istate from the State
* import the stateFactory from the state
* import the StateName from the state
*/
import baseEntities.IEntity;
import events.AbstractEvent;
import states.subscriber.IState;
import states.subscriber.StateFactory;
import states.subscriber.StateName;


/**
 * @author yansheng xie
 * the base Interface for the Subscriber hierarchy
 */
public abstract class AbstractSubscriber implements IEntity {
	
	protected long subscriberID;
	protected IState state;
	
	public long getSubscriberID() {
		return this.subscriberID;
	}

	/**
	 * set's the {@link IState} for this ISubscriber implementation using the {@link StateFactory}
	 * @param stateName the entry from the {@link StateName} enumeration that we want the new IState of the ISubscriber to be 
	 */
	public void setState(StateName stateName) {};

	
	/**
	 * is the function called each time an event is published to one of the channels that the 
	 * ISubscriber is sbuscribed to
	 * @param event the AbstractEvent that's received
	 * @param channelName the name of the channel that sent the AbstractEvent to the ISubscriber
	 */
	public void alert(AbstractEvent event, String channelName) {};
	
	
	/**
	 * subscribes to the channel whose name is provided by the parameter channelName 
	 * @param channelName type String
	 */
	public void subscribe(String channelName) {};
	
	
	/**
	 * unsubscribes from the channel whose name is provided by the parameter channelName
	 * @param channelName type String
	 */
	public void unsubscribe(String channelName) {};
	
}
