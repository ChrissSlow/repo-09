package tpe.hs.ma.uebung.pflicht.i;

/**
 * Inhaber eines Kontos
 * 
 * @author Dustin
 *
 */
public class Inhaber {
    /**
     * Vorname eines Kontoinhabers
     */
    private final String vorname;

    /**
     * Nachname eines Kontoinhabers
     */
    private final String nachname;

    /**
     * Konstruktor zum Initialisieren eines Inhaber-Objekts
     * 
     * @param vorname
     *            Vorname eines Inhabers
     * @param nachname
     *            Nachname eines Inhabers
     */
    public Inhaber(String vorname, String nachname) {
	this.vorname = vorname;
	this.nachname = nachname;
    }

    /**
     * Liefert den Vornamen eines Mitarbeiters.
     * 
     * @return Vorname eines Mitarbeiters
     */
    public String getVorname() {
	return this.vorname;
    }

    /**
     * Liefert den Nachnamen eines Mitarbeiters.
     * 
     * @return Nachname eines Mitarbeiters
     */
    public String getNachname() {
	return this.nachname;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return vorname + " " + nachname;
    }
}
