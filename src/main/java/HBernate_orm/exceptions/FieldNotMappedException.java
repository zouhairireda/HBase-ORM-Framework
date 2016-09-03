package sgcib.eliot.ewos.datalake.HBernate_orm.exceptions;

public class FieldNotMappedException extends Throwable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * error message
	 */
	private String message;

	public FieldNotMappedException() {
		super();
	}

	public FieldNotMappedException(String message) {
		super(message);
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
