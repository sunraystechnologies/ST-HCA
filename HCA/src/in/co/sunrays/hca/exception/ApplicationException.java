package in.co.sunrays.hca.exception;

/**
 * ApplicationException is propogated from Service classes when an business
 * logic exception occurered.
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
public class ApplicationException extends RuntimeException {

	/**
	 * @param msg
	 *            : Error message
	 */
	Exception rootException = null;

	public ApplicationException(String msg) {
		super(msg);
	}

	public ApplicationException(Exception e) {
		super(e.getMessage());
		rootException = e;
	}

}
