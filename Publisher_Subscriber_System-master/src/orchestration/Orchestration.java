package orchestration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import events.AbstractEvent;
import events.EventFactory;
import events.EventMessage;
import events.EventType;
import pubSubServer.AbstractChannel;
import pubSubServer.ChannelAccessControl;
import pubSubServer.ChannelDiscovery;
import pubSubServer.SubscriptionManager;
import publishers.AbstractPublisher;
import publishers.PublisherFactory;
import publishers.PublisherType;
import states.subscriber.StateName;
import strategies.publisher.StrategyName;
import subscribers.AbstractSubscriber;
import subscribers.SubscriberFactory;
import subscribers.SubscriberType;
/**
 * This class is the core implementation of the publisher/subscriber system. 
 * It first reads in the 3 configuration files, and then reads in the file 
 * that contains all the commands to the system. After loading all the files
 * from the system, this class will interpret each command separately and then
 * implement the command with all the other classes in the project.
 * @author Shawn
 *
 */
public class Orchestration {
	/* Instance Variables */
	//the corresponding publishers and their id
	private Map<Long, AbstractPublisher> publishersMap;   
	//the corresponding subscribers and their id
	private Map<Long, AbstractSubscriber> subscribersMap;
	
	/**
	 * The main implementation of the Pub/Sub system
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		//check if the command file is given as a class parameter
		if(args.length==1) {
			
			Orchestration admin = new Orchestration();
			try {
				//Read all the configuration files and 
				//create all the publishers with their corresponding strategies,
				//all the subscribers with their states, and all the channels.
				admin.createPublishers();
				admin.createSubscribers();
				ChannelDiscovery.getInstance().listChannels();
				
				//Read all commands in the command file given as a class parameter
				try {
					BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
					ArrayList<String[]> commandSet = new ArrayList<String[]>();
					String command[] = new String[5];
					String line;
					int numOfCommands = 0;
					//read each line in the command file, 
					//and split each line into different components of a command
					while((line = reader.readLine()) != null ) {
						if(line.split(" ")[0].equals("PUB")) {
							if(line.split(" ").length!=2) {
								
								command[0]=line.split(" ")[0];
								command[1]=line.split(" ")[1];
								command[2]=line.split(" ")[2];
								command[3]=line.split("'")[1];
								command[4]=line.split("'")[3];
							}else {
								command=line.split(" ");
							}
							commandSet.add(command);
							numOfCommands++;
						}else {
							commandSet.add(line.split(" "));
							numOfCommands++;
						}
					}
					
					// Interpret each command read from the file
					// and implement the specified action in the command 
					// through using corresponding methods and passing in the parameters.	
					int count = 0;
					while(count!=numOfCommands) {
						if(commandSet.get(count)[0].equals("PUB")) {
							if(commandSet.get(count).length==2) {
								long publisherId = Integer.parseInt(commandSet.get(count)[1]);
								admin.pubEvent(publisherId);
							}else {
								int publisherId = Integer.parseInt(commandSet.get(count)[1]);
								int event = Integer.parseInt(commandSet.get(count)[2]);
								EventType eventT = EventType.values()[event];
								String head = commandSet.get(count)[3];
								String body = commandSet.get(count)[4];
								admin.pubEvent(publisherId,eventT,head,body);
							}
						}else if(commandSet.get(count)[0].equals("SUB")) {
							long subscriberId = Integer.parseInt(commandSet.get(count)[1]);
							String channelName = commandSet.get(count)[2];
							admin.subChannel(subscriberId,channelName);
						}else if(commandSet.get(count)[0].equals("BLOCK")) {
							long subscriberId = Integer.parseInt(commandSet.get(count)[1]);
							String channelName =commandSet.get(count)[2];
							admin.block(subscriberId,channelName);
						}else if(commandSet.get(count)[0].equals("UNBLOCK")) {
							long subscriberId = Integer.parseInt(commandSet.get(count)[1]);
							String channelName =commandSet.get(count)[2];
							admin.unblock(subscriberId,channelName);
						}else {
							
						}
						count++;
					}
					
					reader.close();
				}catch(IOException e) {
					System.out.println("Loading the configuration files or commands failed");
				}
				
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
				System.out.println("Will now terminate");
				return;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Please enter the name of the command file as class argument!");
		}
	}

	
	/**
	 * create all the publishers and connect them with their corresponding strategy 
	 * given in the configuration file
	 * @return a map of all publishers and their IPs
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void createPublishers() throws IOException, InterruptedException {
		 Map<Long,AbstractPublisher> listOfPublishers = new HashMap<Long,AbstractPublisher>();
		AbstractPublisher newPub;
		BufferedReader StrategyBufferedReader = new BufferedReader(new FileReader(new File("Strategies.str")));
		while(StrategyBufferedReader.ready()) {
			String PublisherConfigLine = StrategyBufferedReader.readLine();
			String[] PublisherConfigArray = PublisherConfigLine.split("\t");
			int[] PublisherConfigIntArray = new int[2];
			for(int i = 0; i < PublisherConfigArray.length; i++)
				PublisherConfigIntArray[i] = Integer.parseInt(PublisherConfigArray[i]);
			newPub = PublisherFactory.createPublisher(
					PublisherType.values()[PublisherConfigIntArray[0]],
					StrategyName.values()[PublisherConfigIntArray[1]]);
			listOfPublishers.put(newPub.getPublisherID(), newPub);
		}
		StrategyBufferedReader.close();
		this.publishersMap = listOfPublishers;
	}
	
	/**
	 * create all the subscribers and connect them with their corresponding strategy 
	 * given in the configuration file
	 * @return a map of all subscribers and their IPs
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void createSubscribers() throws IOException, InterruptedException {
		 Map<Long,AbstractSubscriber>listOfSubscribers = new HashMap<>();
		AbstractSubscriber newSub;
		BufferedReader StateBufferedReader = new BufferedReader(new FileReader(new File("States.sts")));
		while(StateBufferedReader.ready()) {
			String StateConfigLine = StateBufferedReader.readLine();
			String[] StateConfigArray = StateConfigLine.split("\t");
			int[] StateConfigIntArray = new int[2];
			for(int i = 0; i < StateConfigArray.length; i++)
				StateConfigIntArray[i] = Integer.parseInt(StateConfigArray[i]);
			newSub = SubscriberFactory.createSubscriber(
					SubscriberType.values()[StateConfigIntArray[0]], 
					StateName.values()[StateConfigIntArray[1]]);
			listOfSubscribers.put(newSub.getSubscriberID(), newSub);
		}
		StateBufferedReader.close();
		this.subscribersMap=listOfSubscribers;
	}
	
	/**
	 * Publish an given event.
	 * @param publishId
	 * @param eventType
	 * @param header
	 * @param body
	 * @throws InterruptedException
	 */
	private void pubEvent(long publishId,EventType eventType, String header, String body) throws InterruptedException {
		EventMessage msg = new EventMessage(header,body);
		AbstractEvent e = EventFactory.createEvent(eventType, publishId, msg);
		AbstractPublisher publisher = publishersMap.get(publishId);
		if(publisher!=null) {
			publisher.publish(e);
		}
	}
	
	/**
	 * Publish an event without indicating the content of the event to be published.
	 * @param publishId
	 * @throws InterruptedException
	 */
	private void pubEvent(long publishId) throws InterruptedException {
		
		AbstractPublisher publisher = publishersMap.get(publishId);
		if(publisher!=null) {
			publisher.publish();
		}
	}
	
	/**
	 * set a subscriber to subscribe a specific channel by the channel's name
	 * @param subscriberId
	 * @param channelName
	 */
	private void subChannel(long subscriberId, String channelName) {
		AbstractSubscriber subscriber = subscribersMap.get(subscriberId);
		if(subscriber!=null) {
			SubscriptionManager.getInstance().subscribe(channelName, subscriber);
		}
	}
	
	/**
	 * block a subscriber from a channel
	 * @param subscriberId
	 * @param channelName
	 */
	private void block(long subscriberId, String channelName) {
		AbstractSubscriber subscriber = subscribersMap.get(subscriberId);
		if(subscriber!=null) {
			ChannelAccessControl.getInstance().blockSubcriber(subscriber, channelName);
		}
	}
	
	/**
	 * unblock a subscriber from a channel
	 * @param subscriberId
	 * @param channelName
	 */
	private void unblock(long subscriberId, String channelName) {
		AbstractSubscriber subscriber = subscribersMap.get(subscriberId);
		if(subscriber!=null) {
			ChannelAccessControl.getInstance().unBlockSubscriber(subscriber, channelName);
		}
	}
}
