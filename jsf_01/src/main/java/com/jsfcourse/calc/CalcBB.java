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
	private String kwota;
	private String oprocentowanie;
        private String lata;
	private Double result;

	@Inject
	FacesContext ctx;

    public String getKwota() {
        return kwota;
    }

    public void setKwota(String kwota) {
        this.kwota = kwota;
    }

    public String getOprocentowanie() {
        return oprocentowanie;
    }

    public void setOprocentowanie(String oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }

    public String getLata() {
        return lata;
    }

    public void setLata(String lata) {
        this.lata = lata;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

	

	public boolean doTheMath() {
		try {
			double kwota = Double.parseDouble(this.kwota);
			double oprocentowanie = Double.parseDouble(this.oprocentowanie);
                        int lata = Integer.parseInt(this.lata);
                       
                        

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
