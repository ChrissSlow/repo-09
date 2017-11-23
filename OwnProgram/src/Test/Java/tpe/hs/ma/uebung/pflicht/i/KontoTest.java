package tpe.hs.ma.uebung.pflicht.i;

import static org.junit.Assert.*;

import org.junit.Test;

public class KontoTest {
    // Inhaber-Objekte
    Inhaber i1 = new Inhaber("Michael Trent", "Reznor");
    Inhaber i2 = new Inhaber("Anders", "Friden");
    Inhaber i3 = new Inhaber("Matthew", "Bellamy");
    Inhaber i4 = new Inhaber("Mike", "Patton");

    // Waehrungs-Objekte
    Waehrungen auswahl = new Waehrungen();

    // Konto-Objekte
    Konto k1 = new Konto(i1, auswahl.getUsDollar());
    Konto k2 = new Konto(i2, auswahl.getEuro());
    Konto k3 = new Konto(i3, auswahl.getRubel());
    Konto k4 = new Konto(i4, auswahl.getYen());

    // Betrag-Objekte
    Betrag b1 = new Betrag(3456.78, auswahl.getUsDollar());
    Betrag b2 = new Betrag(-385, auswahl.getEuro());
    Betrag b3 = new Betrag(183475.23, auswahl.getRubel());
    Betrag b4 = new Betrag(-412356790, auswahl.getYen());

    @Test
    public void getterTests() {
	assertEquals(auswahl.getUsDollar(), k1.getWaehrung());
	assertEquals("US-Dollar", k1.getWaehrung().getName());
	assertEquals("$", k1.getWaehrung().getKuerzel());
	assertEquals(10000, k1.getWaehrung().getKursZumDollar());
	assertEquals(i1, k1.getInhaber());
	assertEquals("Michael Trent", k1.getInhaber().getVorname());
	assertEquals("Reznor", k1.getInhaber().getNachname());
	assertEquals(0, k1.saldo());

	assertEquals(auswahl.getEuro(), k2.getWaehrung());
	assertEquals("Euro", k2.getWaehrung().getName());
	assertEquals("€", k2.getWaehrung().getKuerzel());
	assertEquals(12690, k2.getWaehrung().getKursZumDollar());
	assertEquals(i2, k2.getInhaber());
	assertEquals("Anders", k2.getInhaber().getVorname());
	assertEquals("Friden", k2.getInhaber().getNachname());
	assertEquals(0, k2.saldo());

	assertEquals(auswahl.getRubel(), k3.getWaehrung());
	assertEquals("Rubel", k3.getWaehrung().getName());
	assertEquals("RUB", k3.getWaehrung().getKuerzel());
	assertEquals(255, k3.getWaehrung().getKursZumDollar());
	assertEquals(i3, k3.getInhaber());
	assertEquals("Matthew", k3.getInhaber().getVorname());
	assertEquals("Bellamy", k3.getInhaber().getNachname());
	assertEquals(0, k3.saldo());

	assertEquals(auswahl.getYen(), k4.getWaehrung());
	assertEquals("Yen", k4.getWaehrung().getName());
	assertEquals("¥", k4.getWaehrung().getKuerzel());
	assertEquals(91, k4.getWaehrung().getKursZumDollar());
	assertEquals(i4, k4.getInhaber());
	assertEquals("Mike", k4.getInhaber().getVorname());
	assertEquals("Patton", k4.getInhaber().getNachname());
	assertEquals(0, k4.saldo());
    }

    @Test
    public void bucheUndToStringTests() {
	k1.buche(b1);
	assertEquals(345678, k1.saldo());
	k1.buche(b3);
	assertEquals(813539, k1.saldo());
	k2.buche(b2);
	assertEquals(-385, k2.saldo());
	k2.buche(b4);
	assertEquals(-2957395, k2.saldo());
	k3.buche(b1);
	assertEquals(13556000, k3.saldo());
	k3.buche(b3);
	assertEquals(31903523, k3.saldo());
	k4.buche(b2);
	assertEquals(-53688, k4.saldo());
	k4.buche(b4);
	assertEquals(-412410478, k4.saldo());
	
	assertEquals("Kontoinhaber: Michael Trent Reznor\nWaehrung: US-Dollar\n---------\n3456.78 $\n4678.61 $\n---------\nSaldo: 8135.39 $", k1.toString());
	assertEquals("Kontoinhaber: Anders Friden\nWaehrung: Euro\n---------\n-3.85 €\n-29570.10 €\n---------\nSaldo: -29573.95 €", k2.toString());
	assertEquals("Kontoinhaber: Matthew Bellamy\nWaehrung: Rubel\n---------\n135560.00 RUB\n183475.23 RUB\n---------\nSaldo: 319035.23 RUB", k3.toString());
	assertEquals("Kontoinhaber: Mike Patton\nWaehrung: Yen\n---------\n-536.88 ¥\n-4123567.90 ¥\n---------\nSaldo: -4124104.78 ¥", k4.toString());
    }

}
