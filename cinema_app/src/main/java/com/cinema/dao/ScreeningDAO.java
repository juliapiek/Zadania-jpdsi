/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.dao;

import com.cinema.entities.Screening;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Stateless
public class ScreeningDAO {
    private final static String UNIT_NAME = "cinema-simplePU";


	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;





	public Screening find(Object id) {
		return em.find(Screening.class, id);
	}

	public List<Screening> getFullList() {
		List<Screening> list = null;

		Query query = em.createQuery("select s from Screening s");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
            
}
