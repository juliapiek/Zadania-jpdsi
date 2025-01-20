/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.reservation;

import com.cinema.entities.Screening;
import com.cinema.entities.Seat;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class SeatService {

    @PersistenceContext
    private EntityManager em;

    public List<Seat> getAvailableSeats(Screening screening) {
        String query = "SELECT s FROM Seat s WHERE s.screeningId = :screening";
        return em.createQuery(query, Seat.class)
                 .setParameter("screening", screening)
                 .getResultList();
    }
}
