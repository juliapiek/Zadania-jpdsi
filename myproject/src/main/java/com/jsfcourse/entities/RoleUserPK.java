/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author pieka
 */
@Embeddable
public class RoleUserPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id")
    private long roleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private long userId;

    public RoleUserPK() {
    }

    public RoleUserPK(long roleId, long userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) roleId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleUserPK)) {
            return false;
        }
        RoleUserPK other = (RoleUserPK) object;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.RoleUserPK[ roleId=" + roleId + ", userId=" + userId + " ]";
    }
    
}
