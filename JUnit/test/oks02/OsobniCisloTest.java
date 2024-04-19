package oks02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OsobniCisloTest {
    OsobniCislo osobniCislo;

    @BeforeEach
    public void setUp() {
        this.osobniCislo = new OsobniCislo("Novák, Josef, fav, 2014, b, 0123, p, i");
    }

    @Test
    void getOsobniCislo() {
        osobniCislo.vysledek = "A14B0123P";
        assertEquals("A14B0123P", osobniCislo.getOsobniCislo());
    }

    @Test
    void isPlatneOsobniCislo_1() {
        osobniCislo.platnyFormat = true;
        assertEquals(true, osobniCislo.isPlatneOsobniCislo());
    }

    @Test
    void isPlatneOsobniCislo_2() {
        osobniCislo.platnyFormat = false;
        assertEquals(false, osobniCislo.isPlatneOsobniCislo());
    }

    @Test
    void isPlatnyFormat_1() {
        osobniCislo.platnyFormat = false;
        assertEquals(false, osobniCislo.isPlatnyFormat());
    }

    @Test
    void isPlatnyFormat_2() {
        osobniCislo.platnyFormat = true;
        assertEquals(true, osobniCislo.isPlatnyFormat());
    }

    @Test
    void getTypStudia() {
        osobniCislo.typStudia = TypStudia.BAKALARSKY;
        assertEquals("B", osobniCislo.getTypStudia());
    }

    @Test
    void getFakulta() {
        assertEquals("A", osobniCislo.getFakulta());
    }

    @Test
    void zpracujPrijmeni_1() {
        osobniCislo.zpracujPrijmeni("Novák");
        assertEquals("NOVÁK", osobniCislo.prijmeni);
    }

    @Test
    void zpracujPrijmeni_2() {
        osobniCislo.zpracujPrijmeni(null);
        assertEquals(Konstanty.ZNAK_CHYBY, osobniCislo.prijmeni);
    }

    @Test
    void zpracujJmeno_1() {
        osobniCislo.zpracujJmeno("Josef");
        assertEquals("Josef", osobniCislo.jmeno);
    }

    @Test
    void zpracujJmeno_2() {
        osobniCislo.zpracujJmeno(null);
        assertEquals(Konstanty.PRAZDNY, osobniCislo.jmeno);
    }

    @Test
    void zpracujRokNastupu_1() {
        osobniCislo.zpracujRokNastupu("2014");
        assertEquals("2014", osobniCislo.rokNastupu);
    }

    @Test
    void zpracujRokNastupu_2() {
        osobniCislo.zpracujRokNastupu(null);
        assertEquals(Konstanty.PRAZDNY, osobniCislo.rokNastupu);
    }

    @Test
    void zpracujRokNastupu_3() {
        osobniCislo.zpracujRokNastupu("S");
        assertThrows(NumberFormatException.class, () -> osobniCislo.zpracujRokNastupu("S144"));
    }
    @Test
    void chybnyRokNastupu_1() {
        osobniCislo.chybnyRokNastupu();
        assertEquals(false, osobniCislo.platnyFormat);
    }

    @Test
    void chybnyRokNastupu_2() {
        osobniCislo.chybnyRokNastupu();
        assertEquals(Konstanty.ZNAK_CHYBY, osobniCislo.rokNastupu);
    }

    @Test
    void zpracujFakulta_() {
        osobniCislo.fakulta = "FAV";
        assertEquals("A", osobniCislo.getFakulta());
    }

    @Test
    void zpracujTypStudia_1() {
        osobniCislo.typStudia = TypStudia.BAKALARSKY;
        assertEquals(TypStudia.BAKALARSKY, osobniCislo.getTypStudia());
    }

    @Test
    void zpracujTypStudia_2() {
        osobniCislo.typStudia = null;
        assertEquals(Konstanty.PRAZDNY, osobniCislo.getTypStudia());
    }

    @Test
    void zpracujFormaStudia_1() {
        osobniCislo.zpracujFormaStudia(null);
        assertEquals(Konstanty.ZNAK_CHYBY, osobniCislo.formaStudia);
    }

    @Test
    void zpracujFormaStudia_2() {
        osobniCislo.zpracujFormaStudia("P");
        assertEquals("P", osobniCislo.formaStudia);
    }

    @Test
    void zpracujNepovinne_1() {
        osobniCislo.zpracujNepovinne(null);
        assertEquals(Konstanty.PRAZDNY, osobniCislo.nepovinne);
    }

    @Test
    void zpracujNepovinne_2() {
        osobniCislo.zpracujNepovinne("I");
        assertEquals("I", osobniCislo.nepovinne);
    }

    @Test
    void osobniCislo(){
        OsobniCislo osobniCislo2 = new OsobniCislo ("Novák, Josef, fav, 2014, b, 0123, p, i");
        assertEquals("A14B0123P", osobniCislo2.vysledek);
    }
}