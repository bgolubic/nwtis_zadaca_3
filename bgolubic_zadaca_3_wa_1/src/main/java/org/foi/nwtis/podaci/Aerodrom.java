package org.foi.nwtis.podaci;

import java.io.Serializable;
import org.foi.nwtis.bgolubic.zadaca_3.konverteri.LokacijaKonverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Dragutin Kermek
 * @version 2.3.0
 */
@Entity
@Table(name = "AIRPORTS")
public class Aerodrom implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "ICAO")
  private String icao;
  @Column(name = "NAME")
  private String naziv;
  @Column(name = "ISO_COUNTRY")
  private String drzava;
  @Column(name = "COORDINATES")
  @Convert(converter = LokacijaKonverter.class)
  private Lokacija lokacija;

  public Aerodrom() {}

  public Aerodrom(String icao) {
    this.icao = icao;
  }

  public Aerodrom(String icao, String naziv, String drzava, Lokacija lokacija) {
    this.icao = icao;
    this.naziv = naziv;
    this.drzava = drzava;
    this.lokacija = lokacija;
  }

  public String getIcao() {
    return icao;
  }

  public void setIcao(String icao) {
    this.icao = icao;
  }

  public String getNaziv() {
    return naziv;
  }

  public void setNaziv(String naziv) {
    this.naziv = naziv;
  }

  public String getDrzava() {
    return drzava;
  }

  public void setDrzava(String drzava) {
    this.drzava = drzava;
  }

  public Lokacija getLokacija() {
    return lokacija;
  }

  public void setLokacija(Lokacija lokacija) {
    this.lokacija = lokacija;
  }


}
