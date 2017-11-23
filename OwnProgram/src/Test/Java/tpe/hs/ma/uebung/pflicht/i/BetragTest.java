package tpe.hs.ma.uebung.pflicht.i;

import static org.junit.Assert.*;

import org.junit.Test;

public class BetragTest {
    // Waehrungen
    Waehrungen auswahl = new Waehrungen();
    Waehrung w1 = new Waehrung("Fict", "FIC", 2.3451);
    Waehrung w2 = new Waehrung("Ands", "&", 1.9000);
    Waehrung w3 = new Waehrung("Question", "?", 0.0001);

    // Betraege
    Betrag b1 = new Betrag(3456.78, auswahl.getEuro());
    Betrag b2 = new Betrag(-385, auswahl.getUsDollar());
    Betrag b3 = new Betrag(183475.23, auswahl.getRubel());
    Betrag b4 = new Betrag(-412356790, auswahl.getYen());
    Betrag b5 = new Betrag(1.23, auswahl.getSchweizerFranken());
    Betrag b6 = new Betrag(-8700, w1);
    Betrag b7 = new Betrag(93000.86, w2);
    Betrag b8 = new Betrag(-99999999, w3);

    @Test
    public void GetterTests() {
	assertEquals(345678, b1.getBetrag());
	assertEquals(auswahl.getEuro(), b1.getWaehrung());
	assertEquals(1, b1.getVorzeichen());
	assertEquals(3456.78, b1.getAsDouble(), 0.5);
	assertEquals(3456, b1.getVorkomma());
	assertEquals(78, b1.getNachkomma());
	
	assertEquals(-385, b2.getBetrag());
	assertEquals(auswahl.getUsDollar(), b2.getWaehrung());
	assertEquals(-1, b2.getVorzeichen());
	assertEquals(-3.85, b2.getAsDouble(), 0.5);
	assertEquals(-3, b2.getVorkomma());
	assertEquals(85, b2.getNachkomma());
	
	assertEquals(18347523, b3.getBetrag());
	assertEquals(auswahl.getRubel(), b3.getWaehrung());
	assertEquals(1, b3.getVorzeichen());
	assertEquals(183475.23, b3.getAsDouble(), 0.5);
	assertEquals(183475, b3.getVorkomma());
	assertEquals(23, b3.getNachkomma());
	
	assertEquals(-412356790, b4.getBetrag());
	assertEquals(auswahl.getYen(), b4.getWaehrung());
	assertEquals(-1, b4.getVorzeichen());
	assertEquals(-4123567.90, b4.getAsDouble(), 0.05);
	assertEquals(-4123567, b4.getVorkomma());
	assertEquals(90, b4.getNachkomma());
	
	assertEquals(123, b5.getBetrag());
	assertEquals(auswahl.getSchweizerFranken(), b5.getWaehrung());
	assertEquals(1, b5.getVorzeichen());
	assertEquals(1.23, b5.getAsDouble(), 0.5);
	assertEquals(1, b5.getVorkomma());
	assertEquals(23, b5.getNachkomma());
	
	assertEquals(-8700, b6.getBetrag());
	assertEquals(w1, b6.getWaehrung());
	assertEquals(-1, b6.getVorzeichen());
	assertEquals(-87.00, b6.getAsDouble(), 0.5);
	assertEquals(-87, b6.getVorkomma());
	assertEquals(00, b6.getNachkomma());
	
	assertEquals(9300086, b7.getBetrag());
	assertEquals(w2, b7.getWaehrung());
	assertEquals(1, b7.getVorzeichen());
	assertEquals(93000.86, b7.getAsDouble(), 0.5);
	assertEquals(93000, b7.getVorkomma());
	assertEquals(86, b7.getNachkomma());
	
	assertEquals(-99999999, b8.getBetrag());
	assertEquals(w3, b8.getWaehrung());
	assertEquals(-1, b8.getVorzeichen());
	assertEquals(-999999.99, b8.getAsDouble(), 0.5);
	assertEquals(-999999, b8.getVorkomma());
	assertEquals(99, b8.getNachkomma());
    }
    
    @Test
    public void arithmetischeTests(){
	assertEquals(345375, b1.addiere(b2));
	assertEquals(345981, b1.subtrahiere(b2));
	assertEquals(795059, b1.multipliziere(2.3));
	assertEquals(2074068, b1.multipliziere(6));
	assertEquals(-770, b2.addiere(b2));
	assertEquals(0, b2.subtrahiere(b2));
	assertEquals(-6430, b2.multipliziere(16.7));
	assertEquals(-12705, b2.multipliziere(33));
	assertEquals(-128807253, b3.addiere(b4));
	assertEquals(165502299, b3.subtrahiere(b4));
	assertEquals(1638984230, b3.multipliziere(89.33));
	assertEquals(16732940976l, b3.multipliziere(912));
	assertEquals(-824713580, b4.addiere(b4));
	assertEquals(0, b4.subtrahiere(b4));
	assertEquals(-733995086, b4.multipliziere(1.78));
	assertEquals(-64327659240l, b4.multipliziere(156));
	assertEquals(-19291, b5.addiere(b6));
	assertEquals(19537, b5.subtrahiere(b6));
	assertEquals(36329, b5.multipliziere(295.36));
	assertEquals(7995, b5.multipliziere(65));
	assertEquals(-17400, b6.addiere(b6));
	assertEquals(0, b6.subtrahiere(b6));
	assertEquals(-3871413, b6.multipliziere(444.99));
	assertEquals(-26100, b6.multipliziere(3));
	assertEquals(9294823, b7.addiere(b8));
	assertEquals(9305349, b7.subtrahiere(b8));
	assertEquals(2791885817l, b7.multipliziere(300.2));
	assertEquals(678906278, b7.multipliziere(73));
	assertEquals(-199999998, b8.addiere(b8));
	assertEquals(0, b8.subtrahiere(b8));
	assertEquals(-1255999987, b8.multipliziere(12.56));
	assertEquals(-23399999766l, b8.multipliziere(234));
    }
    
    @Test
    public void prozentUndPromilleTest(){
	assertEquals(172839, b1.prozent(50.0));
	assertEquals(86420, b1.promille(250.0));
	assertEquals(-64, b2.prozent(16.7));
	assertEquals(-128, b2.promille(333.33));
	assertEquals(13889075, b3.prozent(75.7));
	assertEquals(14482067, b3.promille(789.32));
	assertEquals(-91831857, b4.prozent(22.27));
	assertEquals(-369319112, b4.promille(895.63));
	assertEquals(48, b5.prozent(39.1));
	assertEquals(61, b5.promille(496.1));
	assertEquals(-8699, b6.prozent(99.99));
	assertEquals(-8700, b6.promille(999.99));
	assertEquals(7802772, b7.prozent(83.9));
	assertEquals(5282263, b7.promille(567.98));
	assertEquals(-43200000, b8.prozent(43.2));
	assertEquals(-11245000, b8.promille(112.45));
    }
    
    @Test
    public void toStringTest(){
	assertEquals("3456.78 €", b1.toString());
	assertEquals("-3.85 $", b2.toString());
	assertEquals("183475.23 RUB", b3.toString());
	assertEquals("-4123567.90 ¥", b4.toString());
	assertEquals("1.23 CHF", b5.toString());
	assertEquals("-87.00 FIC", b6.toString());
	assertEquals("93000.86 &", b7.toString());
	assertEquals("-999999.99 ?", b8.toString());
    }

}
