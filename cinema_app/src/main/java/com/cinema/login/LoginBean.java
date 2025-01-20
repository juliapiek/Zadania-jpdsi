/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.login;
import com.cinema.dao.UserDAO;
import com.cinema.entities.User;
import com.cinema.security.RemoteClient;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import javax.security.auth.login.LoginException;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable{
  private static final long serialVersionUID = 1L;


    private String email;
    private String password;
    private User loggedIn;


	@Inject
	FacesContext context;
        
         @PersistenceContext(unitName = "cinema-simplePU")
         protected EntityManager em;

	@EJB
	private UserDAO userDAO;
        
        private String confirmpass;
    
   
public String login() {
    try {
       
        loggedIn = userDAO.auth(email, password);

        if (loggedIn != null) {
          
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSession(true);
            session.setAttribute("user", loggedIn);

         
            RemoteClient<User> client = new RemoteClient<>();
            client.setDetails(loggedIn);

    
            List<String> roles = userDAO.getUserRolesFromDatabase(loggedIn);
            if (roles != null && !roles.isEmpty()) {
                client.getRoles().addAll(roles);
            }

 
            session.setAttribute("remoteClient", client);


            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                            "Zalogowano pomyślnie", null));

 
            return "index.xhtml";
        } else {
  
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Niepoprawne dane logowania", null));
            return null;
        }
    } catch (LoginException e) {
   
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Nie zalogowano", e.getMessage()));
        return null;
    } catch (Exception e) {

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Wystąpił nieoczekiwany błąd", e.getMessage()));
        return null;
    }
}
   public boolean isLoggedIn() {
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    loggedIn = (User) session.getAttribute("user");
  
    return loggedIn != null;
    }
   
   public boolean isAdmin() {
       return loggedIn != null && "admin".equals(loggedIn.getRoleId().getName());
}
   
   public String logout() {
     HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    if (session != null) {
        session.invalidate();
    }

    loggedIn = null;

    return "login.xhtml?faces-redirect=true"; 
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(User loggedIn) {
        this.loggedIn = loggedIn;
    }

  
    
 
}