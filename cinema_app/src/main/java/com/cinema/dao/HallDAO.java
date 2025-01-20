/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.dao;

import com.cinema.entities.Hall;
import com.cinema.entities.Screening;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

public class HallDAO {
     private final static String UNIT_NAME = "cinema-simplePU";


	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
        
	public Hall find(Object id) {
		return em.find(Hall.class, id);
	}

	public List<Hall> getFullList() {
		List<Hall> list = null;

		Query query = em.createQuery("select h from Hall h");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
    
}
