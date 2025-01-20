package com.cinema.security;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import com.cinema.entities.Role;
import com.cinema.dao.RoleDAO;

import jakarta.inject.Inject;

@FacesConverter(value = "roleConverter", managed = true)
public class RoleConverter implements Converter<Role> {

    @Inject
    private RoleDAO roleDAO;

    @Override
    public Role getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("Converting String to Role: " + value);
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            Role role = roleDAO.findById(id);
            return role;
        } catch (NumberFormatException e) {
            System.err.println("Invalid role ID: " + value);
            throw new IllegalArgumentException("Invalid role ID: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Role role) {
        if (role == null) {
            return "";
        }
        System.out.println("Converting Role to String: " + role.getId());
        return String.valueOf(role.getId());
    }

}
