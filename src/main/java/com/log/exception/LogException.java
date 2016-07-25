package com.log.exception;


public class LogException extends Exception
{

    private String message;
    private Throwable cause;


    public LogException()
    {
    }


    public LogException(Throwable cause)
    {
        super(cause);
        this.cause = cause;
    }

    public LogException(String message)
    {
        super(message);
        this.message = message;
    }

    public LogException(String message, Throwable cause)
    {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public Throwable getCause()
    {
        return cause;
    }

    public void setCause(Throwable cause)
    {
        this.cause = cause;
    }
}
