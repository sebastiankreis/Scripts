/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dan
 */
@ManagedBean(name="user")
@ApplicationScoped
public class User {
    private String username;
    private String passsword;

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String validUser(){
        if(username.toLowerCase().equals("dan")){
            return "valid-user";
        }else{
            System.out.println("NOPE");
            return "invalid-user";
        }
    }

    public boolean isLoggedIn(){
        FacesContext context = FacesContext.getCurrentInstance();
        Cookie cookie[] = ((HttpServletRequest)context.getExternalContext().getRequest()).getCookies();
        
        if(cookie == null){
            return false;
        }

        for(Cookie c : cookie){
            if(!c.getName().equals("logged_in"))
                continue;

            if(c.getValue().equals("true"))
                return true;
        }

        return false;
    }
}
