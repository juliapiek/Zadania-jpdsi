/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.login;

import com.cinema.dao.RoleDAO;
import com.cinema.dao.UserDAO;
import com.cinema.entities.Role;
import com.cinema.entities.User;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable{
  private static final long serialVersionUID = 1L;

	private User user = new User();
	private User loaded = null;

	@Inject
	FacesContext context;
        @Inject
    private RoleDAO roleDAO;

	@EJB
	private UserDAO userDAO;
        
        private String confirmpass;
    
   
   public String register() {
       System.out.println("rejestrac");

      try {
            userDAO.register(user); 
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful!", null));
            return "login?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed. Please try again.", null));
            e.printStackTrace();
            return null; 
        }
      
    } 
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
  private Role getRoleByName(String name) {
        Role role = roleDAO.findByName(name);
        if (role == null) {
            throw new RuntimeException("Role not found: " + name);
        }
    return role;
}
    
 
}
