package org.foi.nwtis.bgolubic.zadaca_3.konverteri;

import org.foi.nwtis.podaci.Lokacija;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LokacijaKonverter implements AttributeConverter<Lokacija, String> {

  @Override
  public String convertToDatabaseColumn(Lokacija attribute) {
    return null;
  }

  @Override
  public Lokacija convertToEntityAttribute(String dbLokacija) {
    if (dbLokacija == null || dbLokacija.isEmpty())
      return null;

    String[] koordinate = dbLokacija.split(",");

    if (koordinate == null || koordinate.length == 0)
      return null;

    Lokacija lokacija = new Lokacija();
    lokacija.postavi(koordinate[1].trim(), koordinate[0].trim());

    return lokacija;
  }


}
