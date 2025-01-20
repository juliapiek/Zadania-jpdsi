/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.cinema.reservation;
//
//import com.cinema.entities.Reservation;
//import com.cinema.entities.ReservedSeat;
//import com.cinema.entities.Seat;
//import jakarta.ejb.EJB;
//import jakarta.enterprise.context.RequestScoped;
//import jakarta.inject.Named;
//import java.util.Date;
//
//@Named("reservationBean")
//@RequestScoped
//public class ReservationBean {
//
//    private Seat selectedSeat;
//    private Reservation reservation;
//
//    @EJB
//    private ReservationService reservationService;  
//
//
//    public String reserveSeat(Seat seat) {
//        this.selectedSeat = seat;
//        
//
//        reservation = new Reservation();
//
//        ReservedSeat reservedSeat = new ReservedSeat();
//        reservedSeat.setSeat(seat);
//        reservedSeat.setReservation(reservation);
//
//        reservationService.saveReservedSeat(reservedSeat); 
//
//        return "success";  
//    }
//
//
//    public String submitReservation() {
//        reservationService.submitReservation(reservation); 
//        return "confirmation"; 
//    }
//}

