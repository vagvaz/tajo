/**
 * 
 */
package nta.engine.query.exception;

import nta.catalog.proto.TableProtos.DataType;

/**
 * @author Hyunsik Choi
 *
 */
public class InvalidCastException extends InvalidQueryException {
	private static final long serialVersionUID = -5090530469575858320L;

	/**
	 * @param message
	 */
	public InvalidCastException(DataType src, DataType target) {
		super("Error: cannot cast " + src + " to " + target);
	}
}
