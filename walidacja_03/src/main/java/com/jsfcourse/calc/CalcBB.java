package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private Double kwota = null;
	private Double oprocentowanie = null;
        private Integer lata = null;
	private Double result;

	@Inject
	FacesContext ctx;

    public Double getKwota() {
        return kwota;
    }

    public void setKwota(Double kwota) {
        this.kwota = kwota;
    }

    public Double getOprocentowanie() {
        return oprocentowanie;
    }

    public void setOprocentowanie(Double oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }

    public Integer getLata() {
        return lata;
    }

    public void setLata(Integer lata) {
        this.lata = lata;
    }



	public boolean doTheMath() {
		try {
			

			result = (kwota * (oprocentowanie/100/12)) / 
                          (1 - Math.pow(1 + (oprocentowanie/100/12), -(lata*12)));
                       

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}