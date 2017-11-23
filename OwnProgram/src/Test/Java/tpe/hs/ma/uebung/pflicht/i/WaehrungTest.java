package tpe.hs.ma.uebung.pflicht.i;

import static org.junit.Assert.*;

import org.junit.Test;

public class WaehrungTest {
    
    //Waehrungen
    Waehrungen auswahl = new Waehrungen();
    Waehrung w1 = new Waehrung("Fict", "FIC", 2.3451);
    Waehrung w2 = new Waehrung("Ands", "&", 1.9000);
    Waehrung w3 = new Waehrung("Question", "?", 0.0001);
    
    //Betraege
    long b1 = 345697;
    long b2 = 412356790;
    long b3 = 385;

    @Test
    public void GetterTests() {
	assertEquals("US-Dollar", auswahl.getUsDollar().getName());
	assertEquals("$", auswahl.getUsDollar().getKuerzel());
	assertEquals(10000, auswahl.getUsDollar().getKursZumDollar());
	
	assertEquals("Euro", auswahl.getEuro().getName());
	assertEquals("€", auswahl.getEuro().getKuerzel());
	assertEquals(12690, auswahl.getEuro().getKursZumDollar());
	
	assertEquals("Yen", auswahl.getYen().getName());
	assertEquals("¥", auswahl.getYen().getKuerzel());
	assertEquals(91, auswahl.getYen().getKursZumDollar());
	
	assertEquals("Rubel", auswahl.getRubel().getName());
	assertEquals("RUB", auswahl.getRubel().getKuerzel());
	assertEquals(255, auswahl.getRubel().getKursZumDollar());
	
	assertEquals("Schweizer Franken", auswahl.getSchweizerFranken().getName());
	assertEquals("CHF", auswahl.getSchweizerFranken().getKuerzel());
	assertEquals(10509, auswahl.getSchweizerFranken().getKursZumDollar());
	
	assertEquals("Fict", w1.getName());
	assertEquals("FIC", w1.getKuerzel());
	assertEquals(23451, w1.getKursZumDollar());
	
	assertEquals("Ands", w2.getName());
	assertEquals("&", w2.getKuerzel());
	assertEquals(19000, w2.getKursZumDollar());
	
	assertEquals("Question", w3.getName());
	assertEquals("?", w3.getKuerzel());
	assertEquals(1, w3.getKursZumDollar());
    }
    
    @Test
    public void umrechnenTests(){
	assertEquals(303, auswahl.getUsDollar().umrechnen(b3, auswahl.getEuro()));
	assertEquals(6946, auswahl.getRubel().umrechnen(b1, auswahl.getEuro()));
	assertEquals(47620412155l, auswahl.getSchweizerFranken().umrechnen(b2, auswahl.getYen()));
	assertEquals(426681, w1.umrechnen(b1, w2));
	assertEquals(7834779010000l, w2.umrechnen(b2, w3));
	assertEquals(000, w3.umrechnen(b3, w1));
    }
    
    @Test
    public void toStringTests(){
	assertEquals("US-Dollar [$] 1 $ = 1.0000 $", auswahl.getUsDollar().toString());
	assertEquals("Euro [€] 1 $ = 1.2690 €", auswahl.getEuro().toString());
	assertEquals("Yen [¥] 1 $ = 0.0091 ¥", auswahl.getYen().toString());
	assertEquals("Rubel [RUB] 1 $ = 0.0255 RUB", auswahl.getRubel().toString());
	assertEquals("Schweizer Franken [CHF] 1 $ = 1.0509 CHF", auswahl.getSchweizerFranken().toString());
	assertEquals("Fict [FIC] 1 $ = 2.3451 FIC", w1.toString());
	assertEquals("Ands [&] 1 $ = 1.9000 &", w2.toString());
	assertEquals("Question [?] 1 $ = 0.0001 ?", w3.toString());
    }

}
