package hu.zforgo.resteasy;

public class InvalidInputException extends Exception {

	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidInputException(Throwable e) {

	}
}
