package answer;

/**
 * Thrown by the Bag class to indicate incorrect use of a bag.
 * 
 * @author Colin Fidge
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class BagException extends Exception {
	
	/**
	 * Constructs a BagException with null as its error message string.
	 */
	public BagException() {
		super();
	}
	
	/**
	 * Constructs a BagException with a particular error message string.
	 * 
	 * @param message a description of the exception's cause
	 */
	public BagException(String message) {
		super(message);
	}

}

