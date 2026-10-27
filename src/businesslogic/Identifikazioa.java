package businesslogic;

import ikuspegia.Kontsola;
import kontrolagailua.Teklatua;

public class Identifikazioa {
	private static String Erabiltzailea = "admin";
	private static String Pasahitza = "1234";

	public Identifikazioa() {
	}

	public boolean autentikatu() {
		boolean zuzena = false;
		do {
			Kontsola.imprimatu("Erabiltzailea:");
			String erabiltzailea = Teklatua.irakurri();
			Kontsola.imprimatu("Pasahitza:");
			String pasahitza = Teklatua.irakurri();

			if (erabiltzailea.equals(Erabiltzailea) && pasahitza.equals(Pasahitza)) {
				zuzena = true;
			} else {
				Kontsola.imprimatu("Erabiltzaile edo pasahitz okerra. Saiatu berriro.\n");
			}
		} while (zuzena == false);

		return zuzena;
	}
}