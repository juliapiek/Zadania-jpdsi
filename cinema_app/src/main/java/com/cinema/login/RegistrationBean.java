/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.login;

import com.cinema.dao.UserDAO;
import com.cinema.entities.User;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class RegistrationBean implements Serializable{
  private static final long serialVersionUID = 1L;

	private User user = new User();
	private User loaded = null;

	@Inject
	FacesContext context;

	@EJB
	UserDAO userDAO;
        
        private String confirmpass;
    
   
   public void register() {
      userDAO.register(user);
      
    } 
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
  
    
 
}
