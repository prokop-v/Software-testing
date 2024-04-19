package oks05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OsobniCisloTest_PozitivniPolotovar {
    OsobniCislo oc;

    @BeforeEach
    void setUp() {
        this.oc = new OsobniCislo("Novák, Josef, fav, 2014, b, p");
    }

    @Test
    void compareTo_1() {
        OsobniCislo oc2 = new OsobniCislo("Prokop, Václav, fav, 2014, b, p");
        assertEquals(1, oc2.compareTo(oc));
    }

    @Test
    void compareTo_2() {
        OsobniCislo oc2 = new OsobniCislo("Novák, Alan, fav, 2014, b, p");
        assertEquals(-1, oc2.compareTo(oc));
    }

    @Test
    void testToString() {
        assertEquals("A14BxxxxP <= NOVÁK Josef", oc.toString(), "vypsaný text je nesprávný");
    }

    @Test
    void getOsobniCislo() {
        assertEquals("A14BxxxxP", oc.getOsobniCislo(), "Chyba: testují se pouze polotovary");
    }

    @Test
    void isPlatneOsobniCislo() {
        assertFalse(oc.isPlatneOsobniCislo(), "Zadané číslo není platné");
    }

    @Test
    void isPlatnyFormat() {
        assertTrue(oc.isPlatnyFormat(), "Zadané číslo je ve špatném formátu");
    }

    @Test
    void getTypStudia() {
        assertEquals(TypStudia.BAKALARSKY, oc.getTypStudia(), "Typ studia má být bakalářský");
    }

    @Test
    void getFakulta() {
        assertEquals("A", oc.getFakulta(), "Výsledkem byla jiná zkratka fakulty");
    }
}