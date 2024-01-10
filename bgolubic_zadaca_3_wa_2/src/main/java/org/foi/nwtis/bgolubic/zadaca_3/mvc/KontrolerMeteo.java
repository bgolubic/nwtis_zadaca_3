package org.foi.nwtis.bgolubic.zadaca_3.mvc;

import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.bgolubic.zadaca_3.ws.WsMeteo.endpoint.Meteo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.xml.ws.WebServiceRef;

@Controller
@Path("meteo")
@RequestScoped
public class KontrolerMeteo {
  @WebServiceRef(wsdlLocation = "http://localhost:8080/bgolubic_zadaca_3_wa_1/meteo?wsdl")
  private Meteo serviceMeteo;

  @Inject
  ServletContext context;

  @Inject
  private Models model;

  @GET
  @Path("pocetak")
  @View("index.jsp")
  public void pocetak() {}

  @GET
  @Path("odabirMeteoAerodrom")
  @View("odabirMeteoAerodrom.jsp")
  public void odabirAerodroma() {}

  @GET
  @Path("aerodrom")
  @View("meteoAerodrom.jsp")
  public void getMeteoPodaciAerodrom(@QueryParam("icao") String icao) {
    try {
      Konfiguracija konfig = (Konfiguracija) context.getAttribute("konfig");
      var port = serviceMeteo.getWsMeteoPort();
      var podaci = port.dajMeteo(icao);
      model.put("podaci", podaci);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("odabirMeteoAdresa")
  @View("odabirMeteoAdresa.jsp")
  public void odabirAdrese() {}

  @GET
  @Path("adresa")
  @View("meteoAdresa.jsp")
  public void getMeteoPodaciAdresa(@QueryParam("adresa") String adresa) {
    try {
      Konfiguracija konfig = (Konfiguracija) context.getAttribute("konfig");
      var port = serviceMeteo.getWsMeteoPort();
      var podaci = port.dajMeteoAdresa(adresa);
      model.put("podaci", podaci);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
