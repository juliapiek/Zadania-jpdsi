/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.reservation;

import com.cinema.entities.Reservation;
import com.cinema.entities.ReservedSeat;
import com.cinema.entities.Screening;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author pieka
 */
@Stateless
public class ReservedSeatService {

    @PersistenceContext
    private EntityManager em;

   
    public List<ReservedSeat> getReservedSeats(Screening screening) {
        String query = "SELECT rs FROM ReservedSeat rs WHERE rs.seatId.screeningId = :screening";
        return em.createQuery(query, ReservedSeat.class)
                 .setParameter("screening", screening)
                 .getResultList();
    }


    public void saveReservedSeat(ReservedSeat reservedSeat) {
        em.persist(reservedSeat);
    }

    public void submitReservation(Reservation reservation) {
        em.persist(reservation);
    }
}
