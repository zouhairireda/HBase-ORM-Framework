package sgcib.eliot.ewos.datalake.HBernate_orm.exceptions;

public class TableNotFoundException extends Throwable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * error message
	 */
	private String message;

	public TableNotFoundException() {
		super();
	}

	public TableNotFoundException(String message) {
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
