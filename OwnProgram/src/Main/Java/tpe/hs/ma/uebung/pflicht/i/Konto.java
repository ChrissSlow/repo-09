package tpe.hs.ma.uebung.pflicht.i;

import java.io.*;

/**
 * Ein Konto
 * 
 * @author Dustin
 *
 */
public class Konto {
    /**
     * Inhaber des Kontos
     */
    private final Inhaber inhaber;

    /**
     * Waehrung des Kontos
     */
    private final Waehrung waehrung;

    /**
     * Saldo
     */
    private long saldo;

    /**
     * Alle Buchungen
     */
    private String buchungen = "";

    /**
     * Konstruktor zum Initialisieren eines Konto-Objekts
     * 
     * @param inhaber
     *            Inhaber des Kontos
     * @param waehrung
     *            Waehrung des Kontos
     */
    public Konto(Inhaber inhaber, Waehrung waehrung) {
	this.inhaber = inhaber;
	this.waehrung = waehrung;
	this.saldo = 0;
    }

    /**
     * Liefert den Inhaber des Kontos.
     * 
     * @return Inhaber des Kontos
     */
    public Inhaber getInhaber() {
	return this.inhaber;
    }

    /**
     * Liefert die Waehrung des Kontos.
     * 
     * @return Waehrung des Kontos
     */
    public Waehrung getWaehrung() {
	return this.waehrung;
    }
    
    /**
     * Liefert die Summe aller Buchungen.
     * 
     * @return Summe aller Buchungen
     */
    public long saldo() {
	return this.saldo;
    }
    
    /**
     * Liefert alle Buchungen.
     * 
     * @return Alle Buchungen
     */
    public String getBuchungen(){
	return this.buchungen;
    }

    /**
     * Bucht einen Betrag auf das Konto.
     * 
     * @param betrag Betrag, der auf das Konto gebucht werden soll
     */
    public void buche(Betrag betrag) {
	/**
	 * Betrag der letztendlich auf das Konto gebucht wird
	 */
	long buchbetrag;

	// Pruefen, ob zu buchender Betrag gleiche Waehrung wie Konto hat
	if (!((betrag.getWaehrung()).equals(this.getWaehrung()))) {
	    buchbetrag = betrag.getWaehrung().umrechnen(betrag.getBetrag(), this.getWaehrung());
	} else {
	    buchbetrag = betrag.getBetrag();
	}
	
	buchungSpeichern(buchbetrag);
	
	this.saldo += buchbetrag;
    }

    /**
     * Speichert eine Buchung.
     * 
     * @param buchbetrag Buchung die gespeichert wird
     */
    private void buchungSpeichern(long buchbetrag) {
	/**
	 * Buchbetrag als Kommazahl
	 */
	double alsKommazahl = (double) buchbetrag / 100.00;
	
	/**
	 * Buchbetrag als Zeichenkette
	 */
	String alsZeichenkette = Double.toString(alsKommazahl);
	
	//Falls Nachkommastellenanzahl < 2 0 anhaengen
	int indexNachkomma = alsZeichenkette.indexOf(".")+1;
	String sub = alsZeichenkette.substring(indexNachkomma);
	if(sub.length() < 2){
	     alsZeichenkette = alsZeichenkette + "0";
	}

	// Falls Buchungen leer sind, dann Buchung speichern, ansonsten an
	// vorhandene Buchungen anhaengen
	if(this.buchungen.isEmpty()){
		this.buchungen = alsZeichenkette + " " + getWaehrung().getKuerzel() + "\n";
	}else{
	    this.buchungen = this.buchungen + alsZeichenkette + " " + getWaehrung().getKuerzel() + "\n";
	}
    }
    
    /**
     * Liefert den Saldo als Fließkommazahl.
     * 
     * @return Saldo als Fließkommazahl
     */
    private double getAsDouble() {
	return (double) this.saldo() / 100.0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	/**
	 * Saldo als Zeichenkette
	 */
	String saldo = Double.toString(getAsDouble());
	
	//Falls Nachkommastellenanzahl < 2 0 anhaengen
	int indexNachkomma = saldo.indexOf(".")+1;
	String sub = saldo.substring(indexNachkomma);
	if(sub.length() < 2){
	     saldo = saldo + "0";
	}

	return "Kontoinhaber: " + getInhaber().toString() + "\n" + "Waehrung: " + getWaehrung().getName() + "\n" + "---------\n" + getBuchungen() + "---------\n" + "Saldo: " + saldo + " " + getWaehrung().getKuerzel();
    }

}
