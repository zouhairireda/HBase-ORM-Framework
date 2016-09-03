package sgcib.eliot.ewos.datalake.HBernate_orm.exceptions;

public class ColumnNotFoundException extends Throwable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * error message
	 */
	private String message;

	public ColumnNotFoundException() {
		super();
	}

	public ColumnNotFoundException(String message) {
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
