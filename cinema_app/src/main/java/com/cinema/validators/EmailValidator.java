/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
        String email = (String) t;
        
        if(email==null || email.isEmpty()){
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email jest wymagany!", null));
        
        }
        String emailPattern = "^[^@\\\\s]+@[^@\\\\s]+\\\\.[^@\\\\s]+$";
        if(!email.matches(emailPattern)){
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie poprawny email!", null));
        }
    }
    
}
