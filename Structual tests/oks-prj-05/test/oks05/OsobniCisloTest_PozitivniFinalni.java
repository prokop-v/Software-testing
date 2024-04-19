package oks05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OsobniCisloTest_PozitivniFinalni {
    OsobniCislo oc;

    @BeforeEach
    void setUp() {
        this.oc = new OsobniCislo("Novák, Josef, fav, 2014, b, p");
        oc.generujOsobniCislo("0123");
    }

    @Test
    void testToString() {
        assertEquals("A14B0123P <= NOVÁK Josef", oc.toString(), "vypsaný text je nesprávný");
    }

    @Test
    void getOsobniCislo() {
        assertEquals("A14B0123P", oc.getOsobniCislo(), "Chyba: osobní číslo má být správně");
    }

    @Test
    void isPlatneOsobniCislo() {
        assertTrue(oc.isPlatneOsobniCislo(), "Zadané číslo je platné!");
    }

    @Test
    void isPlatnyFormat() {
        assertTrue(oc.isPlatnyFormat(), "Zadané číslo má být ve správném formátu");
    }
}
