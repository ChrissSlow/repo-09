package tpe.hs.ma.uebung.pflicht.i;

import static org.junit.Assert.*;

import org.junit.Test;

public class InhaberTest {
    //Inhaber-Objekte
    Inhaber i1 = new Inhaber("Hans", "Wurst");
    Inhaber i2 = new Inhaber("Trent", "Reznor");

    @Test
    public void getterTests() {
	assertEquals("Hans", i1.getVorname());
	assertEquals("Wurst", i1.getNachname());
	assertEquals("Trent", i2.getVorname());
	assertEquals("Reznor", i2.getNachname());
    }
    
    @Test
    public void toStringTests(){
	assertEquals("Hans Wurst", i1.toString());
	assertEquals("Trent Reznor", i2.toString());
    }

}
