package tpe.hs.ma.uebung.pflicht.i;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Eine Waehrung.
 * 
 * @author Dustin
 *
 */
public class Waehrung {
    /**
     * Waehrungsname
     */
    private final String name;

    /**
     * Kuerzel fuer die Waehrung
     */
    private final String kuerzel;

    /**
     * Kurs zum Dollar
     */
    private final long kursZumDollar;

    /**
     * Konstruktor zum Initialisieren eines Waehrungs-Objekts
     * 
     * @param name
     *            Name der Waehrung
     * @param kuerzel
     *            Kuerzel der Waeherung
     * @param kursZumDollar
     *            Kurs der Waehrung zum Dollar
     */
    public Waehrung(String name, String kuerzel, double kursZumDollar) {
	this.name = name;
	this.kuerzel = kuerzel.toUpperCase();
	this.kursZumDollar = Math.round(kursZumDollar * 10000);
    }

    /**
     * Liefert den Name der Waehrung
     * 
     * @return Name der Waehrung
     */
    public String getName() {
	return this.name;
    }

    /**
     * Liefert den Kuerzel der Waehrung
     * 
     * @return Kuerzel der Waehrung
     */
    public String getKuerzel() {
	return this.kuerzel;
    }

    /**
     * Liefert den Kurs zum Dollar der Waehrung
     * 
     * @return Kurs zum Dollar der Waehrung
     */
    public long getKursZumDollar() {
	return this.kursZumDollar;
    }

    /**
     * Rechnet den Betrag einer Waehrung in eine Zielwaehung um.
     * 
     * @param betrag
     *            Betrag, der umgerechnet werden soll
     * @param zielWaehrung
     *            Waehrung in die der Betrag umgerechnet werden soll
     * @return Der Betrag in der Zielwaehrung
     */
    public long umrechnen(long betrag, Waehrung zielWaehrung) {
	/**
	 * Betrag in Dollar
	 */
	long inDollar = betrag * this.getKursZumDollar();

	/**
	 * Betrag in der Zielwaehrung
	 */
	long inZielWaehrung = inDollar / zielWaehrung.getKursZumDollar();

	return inZielWaehrung;
    }

    /**
     * Wandelt den Kurs in eine Gleitkommazahl um.
     * 
     * @return Kurs in Form eine Gleitkommazahl
     */
    private String kursAlsDezimalzahl() {
	/**
	 * Zeichenkette fuer den Betrag
	 */
	String s;
	
	//Um arithmetische Operationen zu vermeiden
	if(this.getKursZumDollar() < 10){
	    s = "0.000" + this.getKursZumDollar();
	}else if(this.getKursZumDollar() < 100){
	    s = "0.00" + this.getKursZumDollar();
	}else if(this.getKursZumDollar() < 1000){
	    s = "0.0" + this.getKursZumDollar();
	}else if(this.getKursZumDollar() < 10000){
	    s = "0." + this.getKursZumDollar();
	    //Falls die Zahl groesser oder gleich 1 ist
	}else{
	    s = Double.toString(this.getKursZumDollar()/10000.0);
	}

	/**
	 * Index bei dem die Nachkommazahl beginnt
	 */
	int indexNachkomma = (s.indexOf(".")) + 1;

	/**
	 * Zeichenkette, die die Nachkommazahl enthaelt
	 */
	String sub = s.substring(indexNachkomma);

	//Ist die Laenge der Nachkommazahl kuerzer wie 4, so muessen 0en angehaengt werden 
	if (sub.length() < 4) {
	    //Schleife zum Anhaengen der 0en
	    for (int i = sub.length(); i < 4; i++) {
		s = s + "0";
	    }
	}
	
	return s;
    }

    /**
     * Liefert die Eigenschaften einer Waehrung als String.
     * 
     * @return Eigenschaften einer Waehrung als String
     */
    @Override
    public String toString() {
	return this.getName() + " [" + this.getKuerzel() + "] 1 $ = " + this.kursAlsDezimalzahl() + " "
		+ this.getKuerzel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((kuerzel == null) ? 0 : kuerzel.hashCode());
	result = prime * result + (int) (kursZumDollar ^ (kursZumDollar >>> 32));
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Waehrung other = (Waehrung) obj;
	if (kuerzel == null) {
	    if (other.kuerzel != null) {
		return false;
	    }
	} else if (!kuerzel.equals(other.kuerzel)) {
	    return false;
	}
	if (kursZumDollar != other.kursZumDollar) {
	    return false;
	}
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	return true;
    }

}
