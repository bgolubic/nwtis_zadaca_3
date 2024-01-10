package org.foi.nwtis.bgolubic.zadaca_3.zrna;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.Konfiguracija;

public class SakupljacLetovaAviona extends Thread {

  private Konfiguracija konf;

  public SakupljacLetovaAviona(Konfiguracija konfig) {
    this.konf = konfig;
  }

  @Override
  public void start() {
    Logger.getGlobal().log(Level.INFO, "Dretva: " + this.getName());
    super.start();
  }

  @Override
  public void run() {

  }
}
