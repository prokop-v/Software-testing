package oks08.tests.ts_04.ts_04_03;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import oks08.support.*;
import oks08.support.Tagy;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

@ExtendWith(TestBase.class)
public class TS_04_03_01 {

  @BeforeAll
  public static void setUpBeforeAll() {
    Utils.getURLAndWait(Const.URL_GENEROVANI);
  }

  @Test
  @DisplayName("TC.04.03.01.01: Fakulta nevybrana - chybove hlaseni")
  @Tag(Tagy.AKTIVNI)
  void tc_04_03_01_01() {
    Utils.setRok("15");
    Utils.setTyp(Utils.TYP_B);
    Utils.setPoradi("123");
    Utils.setForma(Id.GEN_RADBTN_FORM_PREZ);
    Utils.generujCislo();
    assertEquals(true, Utils.isZobrazenaChyba("chyba_fakulta_nevybrana"));
  }
}
