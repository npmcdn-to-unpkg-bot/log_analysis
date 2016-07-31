package com.log.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "log_file")
public class LogFile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "logFile")
    private Set<Alert> alerts = new HashSet<>();

    public LogFile()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Alert> getAlerts()
    {
        return alerts;
    }

    public void setAlerts(Set<Alert> alerts)
    {
        this.alerts = alerts;
    }
}
