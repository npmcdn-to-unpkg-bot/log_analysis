package com.log.domain;


public enum LogLevel
{
    SEVERE,
    INFO,
    WARN,
    TRACE,
    ERROR,
    DEBUG ;

    public static LogLevel fromString(final String level)
    {
        switch (level)
        {
            case "SEVERE":
                return SEVERE;
            case "INFO":
                return INFO;
            case "WARN ":
                return WARN ;
            case "TRACE":
                return TRACE;
            case "ERROR":
                return ERROR;
            case "DEBUG":
                return DEBUG;
            default:
                return INFO;
        }
    }
}
