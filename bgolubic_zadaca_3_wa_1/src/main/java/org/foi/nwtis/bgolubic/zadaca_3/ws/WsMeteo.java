package org.foi.nwtis.bgolubic.zadaca_3.ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.klijenti.LIQKlijent;
import org.foi.nwtis.rest.klijenti.NwtisRestIznimka;
import org.foi.nwtis.rest.klijenti.OWMKlijent;
import org.foi.nwtis.rest.podaci.Lokacija;
import org.foi.nwtis.rest.podaci.MeteoPodaci;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.ServletContext;
import jakarta.xml.bind.annotation.XmlElement;

@WebService(serviceName = "meteo")
public class WsMeteo {

  @Inject
  private ServletContext context;

  @PersistenceContext
  private EntityManager em;

  @WebMethod
  public MeteoPodaci dajMeteo(@WebParam @XmlElement(required = true) String icao) {
    Konfiguracija konf = (Konfiguracija) context.getAttribute("konfig");
    OWMKlijent klijent = new OWMKlijent(konf.dajPostavku("OpenWeatherMap.apikey"));

    Aerodrom aerodrom = dajAerodrom(icao);

    try {
      MeteoPodaci podaci = klijent.getRealTimeWeather(aerodrom.getLokacija().getLatitude(),
          aerodrom.getLokacija().getLongitude());
      return podaci;
    } catch (NwtisRestIznimka e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return null;
  }

  @WebMethod
  public MeteoPodaci dajMeteoAdresa(@WebParam @XmlElement(required = true) String adresa) {
    Konfiguracija konf = (Konfiguracija) context.getAttribute("konfig");
    LIQKlijent klijentLokacija = new LIQKlijent(konf.dajPostavku("LocationIQ.apikey"));
    OWMKlijent klijentVrijeme = new OWMKlijent(konf.dajPostavku("OpenWeatherMap.apikey"));

    try {
      Lokacija lokacija = klijentLokacija.getGeoLocation(adresa);
      MeteoPodaci podaci =
          klijentVrijeme.getRealTimeWeather(lokacija.getLatitude(), lokacija.getLongitude());
      return podaci;
    } catch (NwtisRestIznimka e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return null;
  }

  private Aerodrom dajAerodrom(String icao) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Aerodrom> cq = cb.createQuery(Aerodrom.class);
    Root<Aerodrom> root = cq.from(Aerodrom.class);
    cq.where(cb.like(root.get("icao"), icao));

    Aerodrom aerodrom = (Aerodrom) em.createQuery(cq).getSingleResult();

    return aerodrom;

  }
}
