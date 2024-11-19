/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pieka
 */
@Entity
@Table(name = "role_user")
@NamedQueries({
    @NamedQuery(name = "RoleUser.findAll", query = "SELECT r FROM RoleUser r"),
    @NamedQuery(name = "RoleUser.findByRoleId", query = "SELECT r FROM RoleUser r WHERE r.roleUserPK.roleId = :roleId"),
    @NamedQuery(name = "RoleUser.findByUserId", query = "SELECT r FROM RoleUser r WHERE r.roleUserPK.userId = :userId"),
    @NamedQuery(name = "RoleUser.findByCreatedAt", query = "SELECT r FROM RoleUser r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "RoleUser.findByUpdatedAt", query = "SELECT r FROM RoleUser r WHERE r.updatedAt = :updatedAt")})
public class RoleUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoleUserPK roleUserPK;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public RoleUser() {
    }

    public RoleUser(RoleUserPK roleUserPK) {
        this.roleUserPK = roleUserPK;
    }

    public RoleUser(long roleId, long userId) {
        this.roleUserPK = new RoleUserPK(roleId, userId);
    }

    public RoleUserPK getRoleUserPK() {
        return roleUserPK;
    }

    public void setRoleUserPK(RoleUserPK roleUserPK) {
        this.roleUserPK = roleUserPK;
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleUserPK != null ? roleUserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleUser)) {
            return false;
        }
        RoleUser other = (RoleUser) object;
        if ((this.roleUserPK == null && other.roleUserPK != null) || (this.roleUserPK != null && !this.roleUserPK.equals(other.roleUserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.RoleUser[ roleUserPK=" + roleUserPK + " ]";
    }
    
}
