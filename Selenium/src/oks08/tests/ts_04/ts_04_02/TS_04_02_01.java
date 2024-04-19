package oks08.tests.ts_04.ts_04_02;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import oks08.support.*;
import oks08.support.Tagy;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

@ExtendWith(TestBase.class)
public class TS_04_02_01 {

  @BeforeAll
  public static void setUpBeforeAll() {
    Utils.getURLAndWait(Const.URL_GENEROVANI);
  }

  @BeforeEach
  public void setUp() {
    Utils.setRok("15");
    Utils.setTyp(Utils.TYP_B);
    Utils.setPoradi("123");
    Utils.setForma(Id.GEN_RADBTN_FORM_PREZ);
  }

  @Test
  @DisplayName("TC.04.02.01.03: FEK")
  @Tag(Tagy.AKTIVNI)
  void tc_04_02_01_03() {
    Utils.setFakulta("FEK");
    Utils.generujCislo();
    String vysledek = Utils.getVysledek();
    assertEquals("K15B0123P", vysledek);
  }

  @Test
  @DisplayName("TC.04.02.01.07: FPR")
  @Tag(Tagy.AKTIVNI)
  void tc_04_02_01_07() {
    Utils.setFakulta("FPR");
    Utils.generujCislo();
    String vysledek = Utils.getVysledek();
    assertEquals("R15B0123P", vysledek);
  }
}
