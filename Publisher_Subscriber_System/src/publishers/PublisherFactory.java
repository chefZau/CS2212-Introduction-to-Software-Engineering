package publishers;
/*
* import the class StrategyName in from the Strategies, 
* import the Istrategy from the Strategies publisher
* import the StrategyFactory 
*/
import strategies.publisher.StrategyFactory;
import strategies.publisher.StrategyName;


/**
 * @author yansheng xie
 * creates new {@link AbstractPublisher} objects
 * contributes to the Strategy design pattern
 * implements the FactoryMethod design pattern   
 */
public class PublisherFactory {

	
	/**
	 * This is an implementation of the Factory Method design pattern
	 * Creates an instance of any of the classes implementing the top level Interface IPublisher
	 * 
	 * note we have multiple entries that return instances of the same ConcretePublisher class
	 * 
	 * @param publisherType an entry from the {@link PublisherType} enumeration
	 * @param strategyName an entry from the {@link StrategyName} enumeration
	 * @return an instance of the specified IPublisher implementation with the specified strategyName attached to it
	 */
	public static AbstractPublisher createPublisher(PublisherType publisherType, StrategyName strategyName) {
		AbstractPublisher ip;
		long publisherID = PublisherIDMaker.getNewPublisherID();
		
		switch (publisherType) {
			case alphaPub : 
				ip = new AlphaPublisher(StrategyFactory.createStrategy(strategyName),publisherID);
				break;
			case betaPub : 
				ip = new BetaPublisher(StrategyFactory.createStrategy(strategyName),publisherID);
				break;
			case gammaPub : 
				ip = new GammaPublisher(StrategyFactory.createStrategy(strategyName),publisherID);
				break;
			case deltaPub : 
				ip = new DeltaPublisher(StrategyFactory.createStrategy(strategyName),publisherID);
				break;
			default:
				ip = new DefaultPublisher(StrategyFactory.createStrategy(strategyName),publisherID);
				break;
				
		}
		
		System.out.format("Publisher %d created \n",ip.getPublisherID());
		System.out.format("Publisher %d has strategy %s\n", ip.getPublisherID(), strategyName);
		return ip;	
	}
	
}
