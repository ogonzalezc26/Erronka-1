package eredua;

import ikuspegia.Kontsola;

public class Logoa {
	public static void erakutsi() {
		erakutsi(7);
	}

	public static void erakutsi(int zabalera) {
		if (zabalera < 1) {
			zabalera = 1;
		}
		// par = par + 1 (no se puede hacer un reloj de arena con base par)
		if (zabalera % 2 == 0) {
			zabalera = zabalera + 1;
		}
		int erdia = zabalera / 2;
		int muga1 = zabalera; // ordu-erlojua
		int muga2 = muga1 + erdia + 1; // piramidea
		int muga3 = muga2 + 1; // oinarria
		int muga4 = muga3 + erdia; // zutabea
		int muga5 = muga4 + 1; // oinarria
		int guztira = muga5 + erdia + 1; // piramidea
		
		for (int lerroa = 0; lerroa < guztira; lerroa++) {
			int ezkerra;
			int eskuina;
			boolean beteta = false; // if true == estrella

			if (lerroa < muga1) {
				int distantzia = lerroa - erdia;
				if (distantzia < 0) {
					distantzia = -distantzia;
				}
				ezkerra = erdia - distantzia;
				eskuina = erdia + distantzia;
				beteta = true;
			} else if (lerroa < muga2) {
				int i = lerroa - muga1;
				ezkerra = erdia - i;
				eskuina = erdia + i;
			} else if (lerroa < muga3 || (lerroa >= muga4 && lerroa < muga5)) {
				ezkerra = 0;
				eskuina = zabalera - 1;
				beteta = true;
			} else if (lerroa < muga4) {
				ezkerra = erdia;
				eskuina = erdia;
			} else {
				int i = lerroa - muga5;
				ezkerra = erdia - i;
				eskuina = erdia + i;
			}
			String estrella = "";
			for (int zutabea = 0; zutabea <= eskuina; zutabea++) {
				if (zutabea >= ezkerra && (beteta || zutabea == ezkerra || zutabea == eskuina)) {
					estrella = estrella + "*";
				} else {
					estrella = estrella + " ";
				}
			}
			Kontsola.imprimatu(estrella);
		}
		Kontsola.imprimatu("");
	}
}