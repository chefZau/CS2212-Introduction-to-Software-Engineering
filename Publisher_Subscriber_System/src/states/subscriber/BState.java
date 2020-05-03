package states.subscriber;

import events.AbstractEvent;

/**
 * @author MingCong,Zhou
 * This class is for the subscriber that has a state that is Bstate
 * Astate extends the AbstractState class
 * it is an implementation of the State design pattern
 */
public class BState extends AbstractState{

	/**
	 * A state for the subscriber
	 * an subscriber attruibute that is used after publishing event
	 */
	protected BState() {

	}

	/**
	 * the handleEvent method will behave appropriately depending on the implementation.
	 * In general it will do something, after an AbstractEvent is published on an
	 * AbstractChannel to which the ConcreteState's host Object is subscribed
	 * @param event
	 * @param channelName
	 */
	public void handleEvent(AbstractEvent event, String channelName) {
		System.out.println("handles it at state B");
	}

}
