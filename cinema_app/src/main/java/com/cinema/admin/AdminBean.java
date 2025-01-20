package com.cinema.admin;

import com.cinema.dao.RoleDAO;
import com.cinema.dao.UserDAO;
import com.cinema.entities.Role;
import com.cinema.entities.User;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AdminBean implements Serializable {

    @Inject
    private UserDAO userDAO;
    @Inject
    private RoleDAO roleDAO;
    
     @PersistenceContext(unitName = "cinema-simplePU")
    private EntityManager em;

    private List<User> users;
    private List<Role> roles;

    public List<User> getUsers() {
        if (users == null) {
            users = userDAO.getFullList(); 
        }
        return users;
        
    }
     public List<Role> getRoles() {
        if (roles == null) {
            roles = roleDAO.findAll();
        }
        return roles;
    }
    
    public void loadUsers() {
        users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
     @Transactional
    
    public void assignRole(User user) {
        try {
     
        Role employeeRole = em.find(Role.class, 2L);
       
        user.setRoleId(employeeRole);
        em.merge(user);
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Rola pracownika została przypisana", null));
        loadUsers();
    } catch (Exception e) {
        System.err.println("Error w przypisaniu roli: " + e.getMessage());
        e.printStackTrace();
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystąpił błąd: " + e.getMessage(), null));
    }
    }
    @Transactional
    public void removeRole(Long userId) {
        User user = userDAO.find(userId);
        
        if (user != null && user.getRoleId() != null && user.getRoleId().getId() == 2) {
            Role userRole = em.find(Role.class, 3L);
            user.setRoleId(userRole);
            userDAO.merge(user); 
            loadUsers();
        }
    }
   
}
    
    