package oks08.tests.ts_04.ts_04_03;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import oks08.support.*;
import oks08.support.Tagy;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

@ExtendWith(TestBase.class)
public class TS_04_03_03 {

  @BeforeAll
  public static void setUpBeforeAll() {
    Utils.getURLAndWait(Const.URL_GENEROVANI);
  }

  @BeforeEach
  public void setUp() {
    Utils.setFakulta("FAV");
    Utils.setRok("15");
    Utils.setTyp(Utils.TYP_B);
    Utils.setForma(Id.GEN_RADBTN_FORM_PREZ);
  }

  @AfterEach
  public void tearDown() {
    Utils.generujCislo();
    assertAll("Chyba neni spravne zobrazena",
            () -> assertTrue(Utils.isZobrazenaChyba(Id.GEN_CHYBA_PORADI), "Chyba poradi"),
            () -> assertTrue(Utils.isZobrazenaChyba(Id.GEN_CHYBA_CELKOVA), "Chyba celkova"),
            () -> assertFalse(Utils.isVysledekZobrazen(), "Vysledek")
    );
  }

  @Test
  @DisplayName("TC.04.03.03.03: Poradove cislo 0")
  @Tag(Tagy.AKTIVNI)
  void tc_04_03_03_03() {
    Utils.setPoradi("0");
    Utils.generujCislo();
  }

  @Test
  @DisplayName("TC.04.03.03.04: Poradove cislo 00")
  @Tag(Tagy.AKTIVNI)
  void tc_04_03_03_04() {
    Utils.setPoradi("00");
    Utils.generujCislo();
  }

  @Test
  @DisplayName("TC.04.03.03.05: Poradove cislo 000")
  @Tag(Tagy.AKTIVNI)
  void tc_04_03_03_05() {
    Utils.setPoradi("000");
    Utils.generujCislo();
  }

  @Test
  @DisplayName("TC.04.03.03.06: Poradove cislo 0000")
  @Tag(Tagy.AKTIVNI)
  void tc_04_03_03_06() {
    Utils.setPoradi("0000");
    Utils.generujCislo();
  }
}
