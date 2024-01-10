package org.foi.nwtis.bgolubic.zadaca_3.mvc;


import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.bgolubic.zadaca_3.ws.WsAerodromi.endpoint.Aerodromi;
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

/**
 *
 * @author NWTiS
 */
@Controller
@Path("aerodromi")
@RequestScoped
public class KontrolerAerodroma {

  @WebServiceRef(wsdlLocation = "http://localhost:8080/bgolubic_zadaca_3_wa_1/aerodromi?wsdl")
  private Aerodromi serviceAerodromi;

  @Inject
  ServletContext context;

  @Inject
  private Models model;

  @GET
  @Path("pocetak")
  @View("index.jsp")
  public void pocetak() {}


  @GET
  @Path("svi")
  @View("aerodromi.jsp")
  public void getAerodromi() {
    try {
      Konfiguracija konfig = (Konfiguracija) context.getAttribute("konfig");
      var port = serviceAerodromi.getWsAerodromiPort();
      var aerodromi =
          port.dajSveAerodrome(0, Integer.parseInt(konfig.dajPostavku("stranica.brojRedova")));
      model.put("aerodromi", aerodromi);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("odabirAerodroma")
  @View("odabirAerodroma.jsp")
  public void odabirAerodroma() {}

  @GET
  @Path("icao")
  @View("aerodrom.jsp")
  public void getAerodrom(@QueryParam("icao") String icao) {
    try {
      var port = serviceAerodromi.getWsAerodromiPort();
      var aerodrom = port.dajAerodrom(icao);
      model.put("aerodrom", aerodrom);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("odabirUdaljenostiOdDo")
  @View("odabirUdaljenostiOdDo.jsp")
  public void odabirUdaljenostiOdDo() {}

  @GET
  @Path("udaljenosti2aerodroma")
  @View("aerodromiUdaljenosti.jsp")
  public void getAerodromiUdaljenost(@QueryParam("icaoOd") String icaoOd,
      @QueryParam("icaoDo") String icaoDo) {
    try {
      var port = serviceAerodromi.getWsAerodromiPort();
      var aerodromiUdaljenost = port.dajUdaljenostiAerodroma(icaoOd, icaoDo);
      model.put("aerodromiUdaljenost", aerodromiUdaljenost);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("odabirSvihUdaljenosti")
  @View("odabirSvihUdaljenosti.jsp")
  public void odabirSvihUdaljenosti() {}

  @GET
  @Path("udaljenosti")
  @View("udaljenosti.jsp")
  public void getUdaljenosti(@QueryParam("icao") String icao) {
    try {
      Konfiguracija konfig = (Konfiguracija) context.getAttribute("konfig");
      var port = serviceAerodromi.getWsAerodromiPort();
      var udaljenosti = port.dajSveUdaljenostiAerodroma(icao, 0,
          Integer.parseInt(konfig.dajPostavku("stranica.brojRedova")));
      model.put("udaljenosti", udaljenosti);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("odabirNajduljiPut")
  @View("odabirNajduljiPut.jsp")
  public void odabirNajduljiPut() {}

  @GET
  @Path("najduljiPut")
  @View("najduljiPut.jsp")
  public void getNajduljiPut(@QueryParam("icao") String icao) {
    try {
      var port = serviceAerodromi.getWsAerodromiPort();
      var najduljiPut = port.dajNajduljiPutDrzave(icao);
      model.put("najduljiPut", najduljiPut);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
