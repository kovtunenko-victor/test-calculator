package ru.test.testproj.error;

public class CalcAppError extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CalcAppError() {
        super();
    }

    public CalcAppError(String s) {
        super(s);
    }

    public CalcAppError(String message, Throwable cause) {
        super(message, cause);
    }

    public CalcAppError(Throwable cause) {
        super(cause);
    }
}
