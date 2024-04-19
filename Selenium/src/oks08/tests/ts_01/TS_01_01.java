package oks08.tests.ts_01;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import oks08.support.*;
import oks08.support.Tagy;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

@ExtendWith(TestBase.class)
public class TS_01_01 {

  @BeforeAll
  public static void setUpBeforeAll() {

    Utils.getURLAndWait(Const.URL_GENEROVANI);
  }

  @Test
  @DisplayName("TC.01.01.01: Happy day scenario")
  @Tag(Tagy.AKTIVNI)
  void tc_01_01_01() {
    Utils.setFakulta("FAV");
    Utils.setRok("15");
    Utils.setTyp(Utils.TYP_B);
    Utils.setPoradi("123");
    Utils.setForma(Id.GEN_RADBTN_FORM_PREZ);
    Utils.generujCislo();
    String vysledek = Utils.getVysledek();
    assertEquals("A15B0123P", vysledek);
  }

  @Test
  @DisplayName("TC.01.01.02: Vymazani formulare")
  @Tag(Tagy.AKTIVNI)
  void tc_01_01_02() {
    Utils.vymazFormular();
    boolean vysledek = Utils.isVysledekZobrazen();
    assertFalse(vysledek);
  }
}
