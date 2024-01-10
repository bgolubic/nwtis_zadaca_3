package org.foi.nwtis.bgolubic.zadaca_3.ws;

import java.util.ArrayList;
import java.util.List;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.podaci.UdaljenostAerodromDrzavaKlasa;
import org.foi.nwtis.podaci.UdaljenostAerodromDrzavaKlasaProsirena;
import org.foi.nwtis.podaci.UdaljenostAerodromKlasa;
import org.foi.nwtis.podaci.UdaljenostAerodromKlasaProsirena;
import org.foi.nwtis.podaci.UdaljenostKlasa;
import org.foi.nwtis.podaci.UdaljenostKlasaProsirena;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.xml.bind.annotation.XmlElement;

@WebService(serviceName = "aerodromi")
public class WsAerodromi {
  @PersistenceContext
  private EntityManager em;

  @WebMethod
  public List<Aerodrom> dajSveAerodrome(@WebParam @XmlElement(required = true) int odBroja,
      @WebParam @XmlElement(required = true) int broj) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Aerodrom> cq = cb.createQuery(Aerodrom.class);
    List<Aerodrom> aerodromi =
        em.createQuery(cq).setFirstResult(odBroja).setMaxResults(broj).getResultList();

    return aerodromi;
  }

  @WebMethod
  public Aerodrom dajAerodrom(@WebParam @XmlElement(required = true) String icao) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Aerodrom> cq = cb.createQuery(Aerodrom.class);
    Root<Aerodrom> root = cq.from(Aerodrom.class);
    cq.where(cb.like(root.get("icao"), icao));

    Aerodrom aerodrom = (Aerodrom) em.createQuery(cq).getSingleResult();

    return aerodrom;

  }

  @WebMethod
  public List<UdaljenostKlasa> dajUdaljenostiAerodroma(
      @WebParam @XmlElement(required = true) String icaoOd,
      @WebParam @XmlElement(required = true) String icaoDo) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<UdaljenostKlasaProsirena> cq = cb.createQuery(UdaljenostKlasaProsirena.class);
    Root<UdaljenostKlasaProsirena> root = cq.from(UdaljenostKlasaProsirena.class);
    cq.where(cb.and(cb.like(root.get("icaoOd"), icaoOd), cb.like(root.get("icaoDo"), icaoDo)));
    List<UdaljenostKlasaProsirena> udaljenostiPodaci = em.createQuery(cq).getResultList();

    List<UdaljenostKlasa> udaljenosti = new ArrayList<UdaljenostKlasa>();

    for (UdaljenostKlasa u : udaljenostiPodaci)
      udaljenosti.add(u);

    return udaljenosti;
  }

  @WebMethod
  public List<UdaljenostAerodromKlasa> dajSveUdaljenostiAerodroma(
      @WebParam @XmlElement(required = true) String icao,
      @WebParam @XmlElement(required = true) int odBroja,
      @WebParam @XmlElement(required = true) int broj) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<UdaljenostAerodromKlasaProsirena> cq =
        cb.createQuery(UdaljenostAerodromKlasaProsirena.class);
    Root<UdaljenostAerodromKlasaProsirena> root = cq.from(UdaljenostAerodromKlasaProsirena.class);
    cq.where(cb.like(root.get("icaoOd"), icao));
    List<UdaljenostAerodromKlasaProsirena> udaljenostiPodaci =
        em.createQuery(cq).setFirstResult(odBroja).setMaxResults(broj).getResultList();

    List<UdaljenostAerodromKlasa> udaljenosti = new ArrayList<UdaljenostAerodromKlasa>();

    for (UdaljenostAerodromKlasa u : udaljenostiPodaci)
      udaljenosti.add(u);

    return udaljenosti;
  }

  @WebMethod
  public UdaljenostAerodromDrzavaKlasa dajNajduljiPutDrzave(
      @WebParam @XmlElement(required = true) String icao) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<UdaljenostAerodromDrzavaKlasaProsirena> cq =
        cb.createQuery(UdaljenostAerodromDrzavaKlasaProsirena.class);
    Root<UdaljenostAerodromDrzavaKlasaProsirena> root =
        cq.from(UdaljenostAerodromDrzavaKlasaProsirena.class);
    cq.where(cb.like(root.get("icaoOd"), icao)).orderBy(cb.desc(root.get("km")));

    UdaljenostAerodromDrzavaKlasa najduljiPut =
        em.createQuery(cq).setMaxResults(1).getSingleResult();

    return najduljiPut;
  }
}
