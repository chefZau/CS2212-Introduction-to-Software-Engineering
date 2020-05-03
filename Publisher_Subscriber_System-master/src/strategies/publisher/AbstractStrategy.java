package strategies.publisher;

import events.AbstractEvent;
import events.EventFactory;

/**
 * @author Shulan Yang
 *  the abstract base class for the strategy functionality
 *  IStrategy class is implemented by AbstractStrategy class
 */
// TODO modify comments to hint at creation of ChannelFactory
public abstract class AbstractStrategy implements IStrategy{

	/**
	 * Whenever a publisher's {@link AbstractPublisher#publish()} is called, the call is forwarded to the particular Publisher's
	 * IStrategy implementation of this method 
	 * @param publisherId the hashCode or any other unique identifier of the publisher of an AbstractEvent
	 */
	public abstract void doPublish(long publisherId);
	
	/**
	 * 
	 * Whenever a publisher's {@link AbstractPublisher#publish(AbstractEvent)} is called, the call is forwarded to the particular Publisher's
	 * IStrategy implementation of this method
	 * 
	 * @param event the event to be published
	 * @param publisherId  the hashCode or any other unique identifier of the publisher of an AbstractEvent 
	 */
	public abstract void doPublish(AbstractEvent event, long publisherId);
}
