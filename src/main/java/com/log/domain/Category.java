package com.log.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private LogLevel criticite;

    private String description ;

    private String message;

    private String note ;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
    private Set<Alert> alerts = new HashSet<>();

    public Category()
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

    public LogLevel getCriticite()
    {
        return criticite;
    }

    public void setCriticite(LogLevel criticite)
    {
        this.criticite = criticite;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
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
