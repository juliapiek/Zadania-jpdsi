/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.screening;


import com.cinema.dao.HallDAO;
import com.cinema.dao.MovieDAO;
import com.cinema.dao.ScreeningDAO;
import com.cinema.entities.Hall;
import com.cinema.entities.Movie;
import com.cinema.entities.ReservedSeat;
import com.cinema.entities.Screening;
import com.cinema.entities.Seat;
import com.cinema.reservation.ReservedSeatService;
import com.cinema.reservation.SeatService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import java.util.List;

@Named("screeningBean")
@RequestScoped

public class ScreeningBean {
    
 
    @Inject
	FacesContext context;
        @Inject
    private HallDAO hallDAO;
        @Inject
    private MovieDAO movieDAO;
        

	@EJB
	private ScreeningDAO screeningDAO;
        
         @EJB
        private SeatService seatService;  
                
        @EJB
        private ReservedSeatService reservedSeatService;
        
        private String confirmpass;
        private List<Screening> screenings;
            
        private Screening selectedScreening;  
        private List<Seat> availableSeats;    
        private List<ReservedSeat> reservedSeats;

    @PostConstruct
    public void init() {
   
        this.screenings = screeningDAO.getFullList();
    }

    public List<Screening> getScreenings() {
        return screenings;
    }
    
     public void loadAvailableSeats(Screening screening) {
        availableSeats = seatService.getAvailableSeats(screening);
        reservedSeats = reservedSeatService.getReservedSeats(screening);
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }


    public Screening getScreeningById(int id) {
        return screeningDAO.find(id);
    }
    
    public String navigateToReservation(){
        return "/public/reservation?faces-redirect=true";
    }
    

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public Screening getSelectedScreening() {
        return selectedScreening;
    }

    public void setSelectedScreening(Screening selectedScreening) {
        this.selectedScreening = selectedScreening;
        loadAvailableSeats(selectedScreening);
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public List<ReservedSeat> getReservedSeats() {
        return reservedSeats;
    }
    
    
}



    

