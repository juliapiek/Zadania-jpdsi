/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.reservation;

import com.cinema.entities.Reservation;
import com.cinema.entities.ReservedSeat;
import com.cinema.entities.Screening;
import com.cinema.entities.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;


@Stateless
public class ReservationService {

    private final static String UNIT_NAME = "cinema-simplePU";


	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;


    public void saveReservedSeat(ReservedSeat reservedSeat) {
        em.persist(reservedSeat);
    }

   
    public void submitReservation(Reservation reservation) {
        reservation.setUpdatedAt(new Date());
        em.merge(reservation);
    }


    public Reservation createReservation(User user, Screening screening) {
        Reservation reservation = new Reservation();
        reservation.setUserId(user);
        reservation.setScreeningId(screening);
        reservation.setCreatedAt(new Date());
        reservation.setUpdatedAt(new Date());
        em.persist(reservation);
        return reservation;
    }

    
}

