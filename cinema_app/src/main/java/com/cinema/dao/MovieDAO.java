/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.dao;

import com.cinema.entities.Movie;
import com.cinema.entities.Screening;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

public class MovieDAO {
     private final static String UNIT_NAME = "cinema-simplePU";


	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
        
	public Movie find(Object id) {
		return em.find(Movie.class, id);
	}

	public List<Movie> getFullList() {
		List<Movie> list = null;

		Query query = em.createQuery("select m from Movie m");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
