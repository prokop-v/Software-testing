package oks09.tests;

/**
 * Prepravka pro parametrizovany test
 *
 * @author Pavel Herout
 * @version 2020-03-28
 */

public class Prepravka {
  public String osobniCislo;
  public String fakulta;
  public String rok;
  public String typ;
  public String poradi;
  public String forma;
  public String zahranicni;

  public Prepravka(String osobniCislo, String fakulta,
                   String rok, String typ, String poradi,
                   String forma, String zahranicni) {
    this.osobniCislo = osobniCislo;
    this.fakulta = fakulta;
    this.rok = rok;
    this.typ = typ;
    this.poradi = poradi;
    this.forma = forma;
    this.zahranicni = zahranicni;
  }

  // pro ladeni
//  @Override
//  public String toString() {
//    return "Prepravka{" +
//            "osobniCislo='" + osobniCislo + '\'' +
//            ", fakulta='" + fakulta + '\'' +
//            ", rok='" + rok + '\'' +
//            ", typ='" + typ + '\'' +
//            ", poradi='" + poradi + '\'' +
//            ", forma='" + forma + '\'' +
//            ", zahranicni='" + zahranicni + '\'' +
//            '}';
//  }

  // pro ucely vypisu testu
  @Override
  public String toString() {
    return osobniCislo;
  }
}
