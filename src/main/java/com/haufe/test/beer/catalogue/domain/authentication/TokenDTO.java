package com.haufe.test.beer.catalogue.domain.authentication;

public class TokenDTO
{
    private String token;
    private String type;

    public TokenDTO(String token, String bearer)
    {
        this.token = token;
        this.type = bearer;
    }


    public String getToken()
    {
        return token;
    }


    public void setToken(String token)
    {
        this.token = token;
    }


    public String getType()
    {
        return type;
    }


    public void setType(String type)
    {
        this.type = type;
    }
}
