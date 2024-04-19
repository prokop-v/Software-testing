package oks08.tests.ts_04.ts_04_02;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import oks08.support.*;
import oks08.support.Tagy;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

@ExtendWith(TestBase.class)
public class TS_04_02_06 {

  @BeforeAll
  public static void setUpBeforeAll() {
    Utils.getURLAndWait(Const.URL_GENEROVANI);
  }

  @Test
  @DisplayName("TC.04.02.06.02: Zahranicni student ANO")
  @Tag(Tagy.AKTIVNI)
  void tc_04_02_06() {
    Utils.setFakulta("FST");
    Utils.setRok("15");
    Utils.setTyp(Utils.TYP_B);
    Utils.setPoradi("0001");
    Utils.setForma(Id.GEN_RADBTN_FORM_PREZ);
    Utils.setZahranicni(true);
    Utils.generujCislo();
    String vysledek = Utils.getVysledek();
    assertEquals("S15B0001PI", vysledek);
  }
}
