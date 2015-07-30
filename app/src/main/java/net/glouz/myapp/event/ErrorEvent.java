package net.glouz.myapp.event;

/**
 * @author glouzonf
 */
public class ErrorEvent {

    public Exception mException;
    private int mErrorCode;
    private String mErrorMessage;

    public ErrorEvent(Exception exception) {
        super();
        this.mException = exception;
    }

    public ErrorEvent(int errorCode) {
        super();
        this.mErrorCode = errorCode;
    }

    public ErrorEvent(String errorMessage) {
        super();
        this.mErrorMessage = errorMessage;
    }

    public ErrorEvent(int errorCode, String errorMessage) {
        super();
        this.mErrorCode = errorCode;
        this.mErrorMessage = errorMessage;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(int errorCode) {
        this.mErrorCode = errorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }

}
