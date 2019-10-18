package com.lambdaschool.starthere.models;

import com.lambdaschool.starthere.logging.Loggable;

@Loggable // for custom swagger documentation - see SwaggerManualApiPlugin, ln 54
public class UserLogin
{
    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
