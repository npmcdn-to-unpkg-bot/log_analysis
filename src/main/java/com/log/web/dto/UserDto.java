package com.log.web.dto;

import java.util.Set;


public class UserDto
{
    private long id;

    private String cin;

    private String firstname;

    private String lastName;

    private String address;

    private String tel;

    private String username;

    private String password;

    private boolean enabled;

    private Set<String> authorities;

    public String getCin()
    {
        return cin;
    }

    public void setCin(String cin)
    {
        this.cin = cin;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public Set<String> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities)
    {
        this.authorities = authorities;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
}
