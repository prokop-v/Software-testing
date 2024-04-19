package oks09.tests;

/**
 * Parametrizovany test
 *
 * @author Pavel Herout
 * @version 2020-03-28
 */

import oks09.support.basic.Const;
import oks09.support.pageobjects.GenerovaniPage;
import oks09.support.testutils.TestBase;
import oks09.support.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestBase.class)
public class OsobniCisloTest {

  private GenerovaniPage generovaniPage;

  @BeforeAll
  public static void setUpBeforeAll() {
    Utils.openUrlAndWait(Const.URL_GENEROVANI);
  }

  @BeforeEach
  public void setUp() {
    generovaniPage = new GenerovaniPage();
  }

  @ParameterizedTest(name = "{0}: {1}")
  @MethodSource("oks09.tests.PripravaDatovehoZdroje#listPrepravka")
  void platnyFormat(String tc, Prepravka prepravka) {
    generovaniPage.setFakulta(prepravka.fakulta);
    generovaniPage.setRokNastupu(prepravka.rok);
    generovaniPage.setTyp(prepravka.typ);
    generovaniPage.setPoradi(prepravka.poradi);
    generovaniPage.setForma(prepravka.forma);
    generovaniPage.setZahranicni(prepravka.zahranicni);
    generovaniPage = generovaniPage.clickGenerovani();
    String osobniCislo = generovaniPage.getGenerovaneCislo();
    String expected = prepravka.osobniCislo;
    System.out.println(osobniCislo);
    assertEquals(expected, osobniCislo);
  }

}