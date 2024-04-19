package oks04;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GeneratorTest {

  private Generator generator;
  private List<List<OsobniCislo>> seznamyTypuStudia;
  private List<OsobniCislo> testovaciData;

  public ICteniDat cteniDat;
  
  @BeforeEach
  public void setUp() {
    // příprava dat pro mock objekt
    testovaciData = TestovaciData.getTestovaciData();

    // příprava mock objektu
    cteniDat = mock(ICteniDat.class);
    when(cteniDat.nactiVsechnaOsobniCisla("")).thenReturn(testovaciData);
    generator = new Generator(cteniDat);
    seznamyTypuStudia = generator.zpracovani("", "fav");
  }
 
  @AfterEach
  public void tearDown() {

    verify(cteniDat).nactiVsechnaOsobniCisla(anyString());
    verifyNoMoreInteractions(cteniDat);
  }

  @Test
  public void getSeznamTypuStudia_bakalari(){
    assertEquals(4, generator.getSeznamTypuStudia(seznamyTypuStudia, TypStudia.BAKALARSKY).size());
  }

  @Test
  public void getSeznamTypuStudia_navazujici(){
    assertEquals(5, generator.getSeznamTypuStudia(seznamyTypuStudia, TypStudia.NAVAZUJICI).size());

  }

  @Test
  public void getSeznamTypuStudia_doktorandi(){
    assertEquals(3, generator.getSeznamTypuStudia(seznamyTypuStudia, TypStudia.DOKTORSKY).size());

  }

  @Test
  public void vytvorSeznamChybnychFormatu(){
    List<OsobniCislo> list = generator.vytvorSeznamChybnychFormatu(testovaciData, "A");
    assertEquals(2, list.size());
  }

}
