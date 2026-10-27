package kontrolagailua;

import eredua.Logoa;
import ikuspegia.Kontsola;
import businesslogic.Identifikazioa;

public class Main {

	public Main() {
		run();
	}

	public void run() {

		Identifikazioa id = new Identifikazioa();
		id.autentikatu();
		Logoa.erakutsi();
		Menua m = new Menua();
		m.nagusiaMenua();
		Kontsola.imprimatu("Agur");
	}

	public static void main(String[] args) {
		new Main();
	}
}