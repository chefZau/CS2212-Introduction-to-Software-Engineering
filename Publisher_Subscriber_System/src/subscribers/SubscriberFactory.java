package subscribers;

import states.subscriber.StateName;


/**
 * @author yansheng xie
 *  
 */
/**
 * @author yansheng xie
 * creates new {@link AbstractSubscriber} objects
 * contributes to the State design pattern
 * implements the FactoryMethod design pattern   
 */
public class SubscriberFactory {

	
	/**
	 * creates a new {@link AbstractSubscriber} using an entry from the {@link SubscriberType} enumeration
	 * @param subscriberType a value from the {@link SubscriberType} enumeration specifying the type of Subscriber to be created 
	 * @return the newly created {@link AbstractSubscriber} instance 
	 * at first we initial the CSA the an abstract subscriber , then we depend on the state we input to crate an correspond subscriber
	 */
	public static AbstractSubscriber createSubscriber(SubscriberType subscriberType, StateName stateName) {
		AbstractSubscriber CSA = null;
		long subscriberID = SubscriberIDMaker.getNewSubscriberID();
		switch (subscriberType) {
			case alpha : 
				CSA = new ConcreteSubscriberA(subscriberID);
				break;
			case beta:
				CSA = new ConcreteSubscriberB(subscriberID);
				break;
			default: 
				CSA = new ConcreteSubscriberC(subscriberID);
				break;
		}
		CSA.setState(stateName);
		System.out.format("Subscriber %d created\n", CSA.getSubscriberID());
		System.out.format("Subscriber %d is on state %s\n", CSA.getSubscriberID(),stateName);
		return CSA;
	}
	
}
