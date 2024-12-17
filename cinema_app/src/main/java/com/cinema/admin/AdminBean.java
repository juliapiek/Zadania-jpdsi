package com.cinema.admin;

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
    
     @PersistenceContext(unitName = "cinema-simplePU")
    private EntityManager em;

    private List<User> users;

    public List<User> getUsers() {
        if (users == null) {
            users = userDAO.getFullList(); 
        }
        return users;
        
    }
    
    public void loadUsers() {
        users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
     @Transactional
    
    public void assignRole(User user) {
        try {
        System.out.println("Starting role assignment for user: " + user);
     
        Role employeeRole = em.find(Role.class, 2L);
       
        user.setRoleId(employeeRole);
        em.merge(user);
        System.out.println("Role successfully assigned to user: " + user.getId());
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Rola pracownika została przypisana", null));
        loadUsers();
    } catch (Exception e) {
        System.err.println("Error in assignRole: " + e.getMessage());
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
    
    