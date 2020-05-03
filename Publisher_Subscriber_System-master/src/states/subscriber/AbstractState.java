package states.subscriber;

import events.AbstractEvent;

/**
 * @author MingCong,Zhou
 * Base Interface for the implementation of the State design pattern
 */
public abstract class AbstractState implements IState{

	/**
	 * the handleEvent method will behave appropriately depending on the implementation.
	 * In general it will do something, after an AbstractEvent is published on an
	 * AbstractChannel to which the ConcreteState's host Object is subscribed
	 * @param event
	 * @param channelName
	 */
	public abstract void handleEvent(AbstractEvent event, String channelName);

}
