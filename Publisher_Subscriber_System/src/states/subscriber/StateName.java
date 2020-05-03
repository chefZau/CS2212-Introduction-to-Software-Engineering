package states.subscriber;


/**
 * @author MingCong,Zhou
 * This class is for initializing the state name
 * an enumeration of all the available IState names that can be created using the {@link StateFactory#createState(StateName)}
 * note that normally, for each new implementation of the {@link IState} interface, a new entry must be added here
 */
public enum StateName {
	astate, bstate, defaultState
}
