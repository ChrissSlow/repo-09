package tpe.hs.ma.uebung.pflicht.i;

/**
 * Eine Reihe von vorgegebenen Waehrungen
 * 
 * @author Dustin
 *
 */
public class Waehrungen {
    /**
     * US-Dollar
     */
    private final Waehrung usDollar = new Waehrung("US-Dollar", "$", 1.0000);

    /**
     * Euro
     */
    private final Waehrung euro = new Waehrung("Euro", "€", 1.2690);

    /**
     * Yen
     */
    private final Waehrung yen = new Waehrung("Yen", "¥", 0.0091);

    /**
     * Rubel
     */
    private final Waehrung rubel = new Waehrung("Rubel", "RUB", 0.0255);

    /**
     * Schweizer Franken
     */
    private final Waehrung schweizerFranken = new Waehrung("Schweizer Franken", "CHF", 1.0509);

    /**
     * Liefert die Waehrung des US-Dollars.
     * 
     * @return Waehrung des US-Dollars
     */
    public Waehrung getUsDollar() {
	return this.usDollar;
    }
    
    /**
     * Liefert die Waehrung des Euros.
     * 
     * @return Waehrung des Euros
     */
    public Waehrung getEuro() {
	return this.euro;
    }
    
    /**
     * Liefert die Waehrung des Yens.
     * 
     * @return Waehrung des Yens
     */
    public Waehrung getYen() {
	return this.yen;
    }
    
    /**
     * Liefert die Waehrung des Rubels.
     * 
     * @return Waehrung des Rubels
     */
    public Waehrung getRubel() {
	return this.rubel;
    }
    
    /**
     * Liefert die Waehrung der schweizer Franken.
     * 
     * @return Waehrung der schweizer Franken
     */
    public Waehrung getSchweizerFranken() {
	return this.schweizerFranken;
    }
}
