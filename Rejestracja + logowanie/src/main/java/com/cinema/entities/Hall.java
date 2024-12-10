/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author pieka
 */
@Entity
@Table(name = "halls")
@NamedQueries({
    @NamedQuery(name = "Hall.findAll", query = "SELECT h FROM Hall h"),
    @NamedQuery(name = "Hall.findById", query = "SELECT h FROM Hall h WHERE h.id = :id"),
    @NamedQuery(name = "Hall.findByNumberOfHall", query = "SELECT h FROM Hall h WHERE h.numberOfHall = :numberOfHall"),
    @NamedQuery(name = "Hall.findByCapacity", query = "SELECT h FROM Hall h WHERE h.capacity = :capacity"),
    @NamedQuery(name = "Hall.findByCreatedAt", query = "SELECT h FROM Hall h WHERE h.createdAt = :createdAt"),
    @NamedQuery(name = "Hall.findByUpdatedAt", query = "SELECT h FROM Hall h WHERE h.updatedAt = :updatedAt")})
public class Hall implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_hall")
    private int numberOfHall;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacity")
    private int capacity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hallId")
    private Collection<Screening> screeningCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hallId")
    private Collection<Seat> seatCollection;

    public Hall() {
    }

    public Hall(Long id) {
        this.id = id;
    }

    public Hall(Long id, int numberOfHall, int capacity, Date createdAt, Date updatedAt) {
        this.id = id;
        this.numberOfHall = numberOfHall;
        this.capacity = capacity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfHall() {
        return numberOfHall;
    }

    public void setNumberOfHall(int numberOfHall) {
        this.numberOfHall = numberOfHall;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Collection<Screening> getScreeningCollection() {
        return screeningCollection;
    }

    public void setScreeningCollection(Collection<Screening> screeningCollection) {
        this.screeningCollection = screeningCollection;
    }

    public Collection<Seat> getSeatCollection() {
        return seatCollection;
    }

    public void setSeatCollection(Collection<Seat> seatCollection) {
        this.seatCollection = seatCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hall)) {
            return false;
        }
        Hall other = (Hall) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Hall[ id=" + id + " ]";
    }
    
}
