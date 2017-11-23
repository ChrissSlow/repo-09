package tpe.hs.ma.uebung.pflicht.i;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Ein Betrag.
 * 
 * @author Dustin
 *
 */
public class Betrag {

    /**
     * Ein Betrag
     */
    private final long betrag;

    /**
     * Eine Waehrung
     */
    private final Waehrung waehrung;

    /**
     * Vorzeichen des Betrags
     */
    private final byte vorzeichen;

    /**
     * Wert vor dem Komma
     */
    private final long vorKomma;

    /**
     * Wert nach dem Komma
     */
    private final long nachKomma;

    /**
     * Konstruktor eines Betrags, in dem ein double als Betrag uebergeben wird.
     * 
     * @param betrag
     *            Betrag
     * @param waehrung
     *            Waehrung des Betrags
     */
    public Betrag(double betrag, Waehrung waehrung) {
	// Ermittelt das Vorzeichen
	if (betrag < 0) {
	    this.vorzeichen = -1;
	} else {
	    this.vorzeichen = 1;
	}
	double betragGerundet = rundenAufZweiNachkommastellen(betrag);
	this.vorKomma = this.vorKommaErmitteln(betragGerundet);
	this.nachKomma = this.nachKommaErmitteln(betragGerundet);
	this.betrag = (long) Math.round(betragGerundet * 100);
	this.waehrung = waehrung;
    }

    /**
     * Konstruktor eines Betrags, in dem ein long als Betrag uebergeben wird.
     * 
     * @param betrag
     *            Betrag
     * @param waehrung
     *            Waehrung des Betrags
     */
    public Betrag(long betrag, Waehrung waehrung) {
	// Ermittelt das Vorzeichen
	if (betrag < 0) {
	    this.vorzeichen = -1;
	} else {
	    this.vorzeichen = 1;
	}
	this.betrag = betrag;
	this.waehrung = waehrung;

	/**
	 * Betrag als Fließkommazahl
	 */
	double betragAlsDezimalZahl = this.getAsDouble();

	this.vorKomma = this.vorKommaErmitteln(betragAlsDezimalZahl);
	this.nachKomma = this.nachKommaErmitteln(betragAlsDezimalZahl);
    }

    /**
     * Rundet ein Betrag auf zwei Nachkommastellen.
     * 
     * @param betrag
     *            Betrag der gerundet werden soll
     * @return Gerundeter Betrag
     */
    protected double rundenAufZweiNachkommastellen(double betrag) {
	BigDecimal bd = new BigDecimal(betrag);
	bd = bd.setScale(2, RoundingMode.HALF_UP);
	System.out.println(bd);
	return bd.doubleValue();
    }

    /**
     * Liefert den Betrag.
     * 
     * @return Der Betrag, der geliefert wird
     */
    public long getBetrag() {
	return this.betrag;
    }

    /**
     * Liefert die Waehrung des Betrags.
     * 
     * @return Waehrung des Betrags
     */
    public Waehrung getWaehrung() {
	return this.waehrung;
    }

    /**
     * Liefert das Vorzeichen des Betrags.
     * 
     * @return Vorzeichen des Betrags
     */
    public byte getVorzeichen() {
	return this.vorzeichen;
    }

    /**
     * Liefert die Zahl, die vor dem Komma des Betrags steht.
     * 
     * @return Zahl, die vor dem Komma des Betrags steht
     */
    public long getVorkomma() {
	return this.vorKomma;
    }

    /**
     * Liefert die Zahl, die nach dem Komma des Betrags steht.
     * 
     * @return Zahl, die nach dem Komma des Betrags steht
     */
    public long getNachkomma() {
	return this.nachKomma;
    }

    /**
     * Addiert zwei Betraege.
     * 
     * @param summand
     *            Zu addierender Betrag
     * @return Summe
     */
    public long addiere(Betrag summand) {
	/**
	 * Der zu addierende Betrag
	 */
	long zuAddierenderBetrag;

	// Ermittelt, ob der zu summierende Betrag die gleiche Waehrung hat
	if (!((summand.getWaehrung()).equals(this.getWaehrung()))) {
	    zuAddierenderBetrag = summand.getWaehrung().umrechnen(summand.getBetrag(), this.getWaehrung());
	} else {
	    zuAddierenderBetrag = summand.getBetrag();
	}

	return this.getBetrag() + zuAddierenderBetrag;
    }

    /**
     * Subtrahiert zwei Betraege.
     * 
     * @param summand
     *            Zu subtrahierender Betrag
     * @return Differenz
     */
    public long subtrahiere(Betrag summand) {
	/**
	 * Der zu subtrahierende Betrag
	 */
	long zuSubtrahierenderBetrag;

	// Ermittelt, ob der zu subtrahierende Betrag die gleiche Waehrung hat
	if (!((summand.getWaehrung()).equals(this.getWaehrung()))) {
	    zuSubtrahierenderBetrag = summand.getWaehrung().umrechnen(summand.getBetrag(), this.getWaehrung());
	} else {
	    zuSubtrahierenderBetrag = summand.getBetrag();
	}

	return this.getBetrag() - zuSubtrahierenderBetrag;
    }

    /**
     * Multipliziert den Betrag mit einem Dezimalwert
     * 
     * @param kommaZahl
     *            Zahl mit der multipliziert wird
     * @return Produkt
     */
    public long multipliziere(double kommaZahl) {
	/**
	 * Ergebnis der Multiplikation
	 */
	double produkt = this.getAsDouble() * kommaZahl;

	return (long) Math.round(this.rundenAufZweiNachkommastellen(produkt) * 100);
    }

    /**
     * Multipliziert einen Betrag mit einer Ganzzahl.
     * 
     * @param ganzZahl
     *            Zahl mit der multipliziert wird
     * @return Produkt
     */
    public long multipliziere(int ganzZahl) {
	return this.getBetrag() * ganzZahl;
    }

    /**
     * Berechnet einen prozentualen Teil des Betrags.
     * 
     * @param prozent
     *            Prozentualer Teil der berechnet werden soll
     * @return Prozentualer Teil
     */
    public long prozent(double prozent) {
	/**
	 * Ein Prozent des Betrags
	 */
	double einProzent = this.getAsDouble() / 100.00;

	/**
	 * Endergebnis
	 */
	double ergebnis = einProzent * prozent;

	return Math.round(this.rundenAufZweiNachkommastellen(ergebnis) * 100);
    }

    /**
     * Berechnet den promillialen Teil eines Betrags.
     * 
     * @param promille
     *            Promillialer Teil der berechnet werden soll
     * @return Promillialer Teil
     */
    public long promille(double promille) {
	/**
	 * Ein Promille des Betrags
	 */
	double einPromille = this.getAsDouble() / 1000.00;

	/**
	 * Endergebnis
	 */
	double ergebnis = einPromille * promille;

	return Math.round(this.rundenAufZweiNachkommastellen(ergebnis) * 100);
    }

    /**
     * Liefert den Wert vor dem Komma des Betrags.
     * 
     * @param betrag
     *            Betrag.
     * @return Wert vor dem Komma des Betrags
     */
    private long vorKommaErmitteln(double betrag) {
	/**
	 * Wert der vor dem Komma steht
	 */
	long vorKomma = (long) betrag;

	return vorKomma;
    }

    /**
     * Leifert den Wert, der hinter dem Komma steht.
     * 
     * @param betrag
     *            Betrag
     * @return Wert, der hinter dem Komma steht
     */
    private long nachKommaErmitteln(double betrag) {
	/**
	 * Betrag als Zeichenkette
	 */
	String s = Double.toString(betrag);

	/**
	 * Index bei dem die Nachkommazahl beginnt
	 */
	int indexNachkomma = (s.indexOf(".")) + 1;

	/**
	 * Zeichenkette, die die Nachkommazahl enthaelt
	 */
	String sub = s.substring(indexNachkomma);

	// Falls die Nachkommazahl zu kurz ist eine 0 dranhaengen
	if (sub.length() == 1) {
	    sub = sub + "0";
	}

	/**
	 * Wert, das nach dem Komma steht als Ganzzahl
	 */
	long nachkomma = Long.parseLong(sub);

	return nachkomma;
    }

    /**
     * Liefert den Betrag als Fließkommazahl.
     * 
     * @return Betrag als Fließkommazahl
     */
    public double getAsDouble() {
	/**
	 * Betrag als Fließkommazahl
	 */
	double betragAlsDezimalzahl = (double) this.getBetrag() / 100.0;

	double zweiKommastellen = this.rundenAufZweiNachkommastellen(betragAlsDezimalzahl);

	System.out.println(zweiKommastellen);

	return this.rundenAufZweiNachkommastellen(betragAlsDezimalzahl);
    }

    /**
     * Liefert die Eigenschaften eines Betrags als String.
     * 
     * @return Eigenschaften eines Betrags als String
     */
    @Override
    public String toString() {
	/**
	 * Betrag als Zeichenkette
	 */
	String alsString = Double.toString(this.getAsDouble());

	/**
	 * Index fuer die Nachkommastellen
	 */
	int indexNachkomma = alsString.indexOf(".") + 1;

	/**
	 * Nachkommastellen als Zeichenkette
	 */
	String sub = alsString.substring(indexNachkomma);

	// Ist die Anzahl der Ziffern nach dem Komma < 2, dann 0 anhaengen
	if (sub.length() < 2) {
	    alsString = alsString + "0";
	}

	return alsString + " " + this.getWaehrung().getKuerzel();
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
	result = prime * result + (int) (betrag ^ (betrag >>> 32));
	result = prime * result + (int) (nachKomma ^ (nachKomma >>> 32));
	result = prime * result + (int) (vorKomma ^ (vorKomma >>> 32));
	result = prime * result + vorzeichen;
	result = prime * result + ((waehrung == null) ? 0 : waehrung.hashCode());
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
	Betrag other = (Betrag) obj;
	if (betrag != other.betrag) {
	    return false;
	}
	if (nachKomma != other.nachKomma) {
	    return false;
	}
	if (vorKomma != other.vorKomma) {
	    return false;
	}
	if (vorzeichen != other.vorzeichen) {
	    return false;
	}
	if (waehrung == null) {
	    if (other.waehrung != null) {
		return false;
	    }
	} else if (!waehrung.equals(other.waehrung)) {
	    return false;
	}
	return true;
    }

}
