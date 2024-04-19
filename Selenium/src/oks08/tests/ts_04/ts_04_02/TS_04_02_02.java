package oks08.tests.ts_04.ts_04_02;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import oks08.support.*;
import oks08.support.Tagy;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

@ExtendWith(TestBase.class)
public class TS_04_02_02 {

  @BeforeAll
  public static void setUpBeforeAll() {
    Utils.getURLAndWait(Const.URL_GENEROVANI);
  }

  @Test
  @DisplayName("TC.04.02.02.03: rok 99")
  @Tag(Tagy.AKTIVNI)
  void tc_04_02_02_03() {
    Utils.setFakulta("FAV");
    Utils.setRok("99");
    Utils.setTyp(Utils.TYP_N);
    Utils.setPoradi("9876");
    Utils.setForma(Id.GEN_RADBTN_FORM_KOMB);
    Utils.generujCislo();
    String vysledek = Utils.getVysledek();
    assertEquals("A99N9876K", vysledek);
  }
}
