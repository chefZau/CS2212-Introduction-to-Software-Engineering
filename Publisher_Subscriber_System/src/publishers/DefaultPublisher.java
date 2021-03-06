package publishers;
/*
* import the class abstractEvent in from the event, 
* import the Istrategy from the Strategies publisher
* import the StrategyFactory 
*/
import events.AbstractEvent;
import strategies.publisher.IStrategy;
import strategies.publisher.StrategyFactory;


/**
 * @author yansheng xie
 * 
 * the WeatherPublisher class is an example of a ConcretePublisher 
 * implementing the IPublisher interface. Of course the publish 
 * methods could have far more interesting logics
 */
public class DefaultPublisher extends AbstractPublisher {

	
	
	
	
	/**
	 * @param concreteStrategy attaches a concreteStrategy generated from the {@link StrategyFactory#createStrategy(strategies.publisher.StrategyName)}
	 * method
	 */
	protected DefaultPublisher(IStrategy concreteStrategy, long publisherID) {
		this.publishingStrategy = concreteStrategy;
		this.publisherID = publisherID;
	}

	/* (non-Javadoc)
	 * @see publishers.IPublisher#publish(events.AbstractEvent)
	 */
	@Override
	public void publish(AbstractEvent event) {
		publishingStrategy.doPublish(event, this.getPublisherID());
	}

	/* (non-Javadoc)
	 * @see publishers.IPublisher#publish()
	 */
	@Override
	public void publish() {
		publishingStrategy.doPublish(this.getPublisherID());
	}

}
