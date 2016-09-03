package sgcib.eliot.ewos.datalake.HBernate_orm.exceptions;

public class ParsingException extends Throwable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * error message
	 */
	private String message;

	public ParsingException() {
		super();
	}

	public ParsingException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
