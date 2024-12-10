/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.dao;

import com.cinema.entities.Role;
import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.cinema.entities.User;
import java.util.Date;
import javax.security.auth.login.LoginException;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "cinema-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void register(User user) {
            if (user.getRoleId() == null) {
          
            Role defaultRole = em.find(Role.class, 3L);  
            if (defaultRole != null) {
                user.setRoleId(defaultRole); 
            } 
        }

        Date now = new Date();
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(now);
        }
        if (user.getUpdatedAt() == null) {
            user.setUpdatedAt(now);
        }
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}

	public List<User> getFullList() {
		List<User> list = null;

		Query query = em.createQuery("select u from User u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
        
        public User auth(String email, String password) throws LoginException {
        try {
            User user = em.createNamedQuery("User.findByEmail", User.class)
                          .setParameter("email", email)
                          .getSingleResult();

               if (user != null && user.getPassword().equals(password)) {
                return user; 
            } else {
                throw new LoginException("Nie poprawne dane logowania");
            }
        } catch (LoginException e) {
            throw new LoginException("Nie znaleziono użytkownika lub podano złe dane logowania");
        }
    }


}
