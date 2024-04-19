package oks05;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OsobniCisloTest_Negativni {

    @Test
    void testToString() {
        OsobniCislo oc = new OsobniCislo("Nový, Adam, fav, 2K14, b, p");
        assertEquals("A?BxxxxP <= NOVÝ Adam (chybně zadáno)", oc.toString(), "text má být chybný kvůli roku");
    }

    @Test
    void zpracujRokNastupu() {
        OsobniCislo oc = new OsobniCislo("Novák, Josef, fav, 20145, b, p, i");
        assertFalse(oc.isPlatnyFormat(), "rok nástupu má neplatné číslo!");
    }

    @Test
    void zpracujTypStudia() {
        OsobniCislo oc = new OsobniCislo("");
        assertEquals(TypStudia.NEPLATNY, oc.getTypStudia(), "Typ studia má být neplatný");
    }

    @Test
    void zpracujNepovinne() {
        OsobniCislo oc = new OsobniCislo("Novák, Josef, fav, 2014, b, p, i");
        assertEquals("I", oc.nepovinne, "osobní číslo obsahuje nepovinný údaj");
    }

    @Test
    void isPlatneOsobniCislo_1(){
        OsobniCislo oc = new OsobniCislo(null);
        assertEquals(false ,oc.isPlatneOsobniCislo(), "Přijmení je null");
    }

    @Test
    void isPlatneOsobniCislo_2(){
        OsobniCislo oc = new OsobniCislo("Novák, Josef, fav, 2014, b, p, i, b");
        assertEquals(true ,oc.isPlatnyFormat());
    }
}