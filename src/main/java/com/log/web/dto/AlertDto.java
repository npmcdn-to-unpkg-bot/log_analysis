package com.log.web.dto;

import com.log.domain.LogLevel;

import java.time.LocalDate;

public class AlertDto
{

    private long id;

    private String machine ;

    private LocalDate date;

    private String hour ;

    private int level ;

    private String message ;

    private LogLevel criticite ;

    private String logFileName ;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getMachine()
    {
        return machine;
    }

    public void setMachine(String machine)
    {
        this.machine = machine;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getHour()
    {
        return hour;
    }

    public void setHour(String hour)
    {
        this.hour = hour;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public LogLevel getCriticite()
    {
        return criticite;
    }

    public void setCriticite(LogLevel criticite)
    {
        this.criticite = criticite;
    }

    public String getLogFileName()
    {
        return logFileName;
    }

    public void setLogFileName(String logFileName)
    {
        this.logFileName = logFileName;
    }
}
