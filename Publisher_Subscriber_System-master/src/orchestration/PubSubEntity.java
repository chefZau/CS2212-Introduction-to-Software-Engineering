package orchestration;

import publishers.AbstractPublisher;
import subscribers.AbstractSubscriber;
@SuppressWarnings("all")

/**
 * an abstract public class that represents the entity of publisher/subscriber system
 * @author ShunxinPang
 */
abstract public class PubSubEntity {

	private AbstractPublisher publisherRole = null;
	private AbstractSubscriber subscriberRole = null; 
	
}
