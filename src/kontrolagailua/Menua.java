package kontrolagailua;

import java.util.List;
import businesslogic.LangileFitxategia;
import businesslogic.BezeroFitxategia;
import businesslogic.Fakturak;
import eredua.Langilea;
import eredua.Bezeroa;
import eredua.Eskaria;
import eredua.EskariLerroa;
import ikuspegia.Kontsola;
import businesslogic.Fitxategiak;

public class Menua {

	private LangileFitxategia langileFitxategia = new LangileFitxategia(Fitxategiak.langileak);
	private BezeroFitxategia bezeroFitxategia = new BezeroFitxategia(Fitxategiak.bezeroak, Fitxategiak.bezero_telefonoa,
			Fitxategiak.eskaria);
	private Fakturak fakturak = new Fakturak(Fitxategiak.eskari_lerroa);

	public Menua() {
		langileFitxategia.fitxategiaIrakurri();
		bezeroFitxategia.fitxategiakIrakurri();
		fakturak.fitxategiaIrakurri();
	}

	private Integer menuaErakutsi() {
		Kontsola.imprimatu("1. Langileak");
		Kontsola.imprimatu("2. Bezeroak");
		Kontsola.imprimatu("3. Fitxategiak");
		Kontsola.imprimatu("4. Saioa itxi");
		Kontsola.imprimatu("Aukeratu:");
		return Teklatua.irakurriZenbakia();
	}

	public void nagusiaMenua() {
		boolean irten = false;
		do {
			Integer aukera = menuaErakutsi();
			switch (aukera) {
			case 1:
				langileakMenua();
				break;
			case 2:
				bezeroakMenua();
				break;
			case 3:
				fitxategiakMenua();
				break;
			case 4:
				irten = saioaItxi();
				break;
			default:
				Kontsola.imprimatu("Aukera okerra.\n");
			}
		} while (irten == false);
	}

	public static boolean saioaItxi() {
		String erantzuna;
		do {
			Kontsola.imprimatu("Saioa itxi nahi duzu? (bai/ez)");
			erantzuna = Teklatua.irakurri().trim().toLowerCase();
		} while (!erantzuna.equals("bai") && !erantzuna.equals("ez"));
		return erantzuna.equals("bai");
	}

	// LANGILEAK
	public void langileakMenua() {
		boolean atzera = false;
		do {
			Kontsola.imprimatu("\n LANGILEAK");
			Kontsola.imprimatu("1. Kontsultatu langile baten informazioa");
			Kontsola.imprimatu("2. Nagusi baten langileen zerrenda");
			Kontsola.imprimatu("3. Atzera joan");
			Kontsola.imprimatu("Aukeratu:");
			Integer aukera = Teklatua.irakurriZenbakia();
			switch (aukera) {
			case 1:
				langilearenInformazioa();
				break;
			case 2:
				nagusiarenLangileak();
				break;
			case 3:
				atzera = true;
				break;
			default:
				Kontsola.imprimatu("Aukera okerra.");
			}
		} while (atzera == false);
	}

	private void langilearenInformazioa() {
		Kontsola.imprimatu("Langilearen izena eta abizena (utzi hutsik guztiak ikusteko):");
		String bilaketa = Teklatua.irakurri();

		if (bilaketa == null || bilaketa.isBlank()) {
			langileFitxategia.bilaketaInprimatu(langileFitxategia.langileGuztiak());
			return;
		}

		List<Langilea> emaitza = langileFitxategia.langileakBilatu(bilaketa);
		if (emaitza.isEmpty()) {
			Kontsola.imprimatu("Ez da langilerik aurkitu: " + bilaketa);
		} else {
			langileFitxategia.bilaketaInprimatu(emaitza);
		}
	}

	private void nagusiarenLangileak() {
		Kontsola.imprimatu("Nagusiaren izena edo abizena (edo biak):");
		String bilaketa = Teklatua.irakurri();

		List<Langilea> nagusiak = langileFitxategia.langileakBilatu(bilaketa);
		if (nagusiak.isEmpty()) {
			Kontsola.imprimatu("Ez da langilerik aurkitu: " + bilaketa);
			return;
		}
		for (Langilea nagusia : nagusiak) {
			List<Langilea> langileak = langileFitxategia.nagusiarenLangileak(nagusia.getId());
			if (langileak.isEmpty()) {
				Kontsola.imprimatu(
						nagusia.getIzena() + " " + nagusia.getAbizena() + "-ek ez du langilerik bere kargu.");
			} else {
				langileFitxategia.langileZerrendaInprimatu(nagusia, langileak);
			}
		}
	}

	// BEZEROAK
	public void bezeroakMenua() {
		boolean atzera = false;
		do {
			Kontsola.imprimatu("\n BEZEROAK");
			Kontsola.imprimatu("1. Bezeroaren kontaktuak kontsultatu");
			Kontsola.imprimatu("2. Bezero baten erosketaren faktura sortu");
			Kontsola.imprimatu("3. Atzera joan");
			Kontsola.imprimatu("Aukeratu:");
			Integer aukera = Teklatua.irakurriZenbakia();
			switch (aukera) {
			case 1:
				bezeroKontaktuak();
				break;
			case 2:
				fakturaSortu();
				break;
			case 3:
				atzera = true;
				break;
			default:
				Kontsola.imprimatu("Aukera okerra.");
			}
		} while (atzera == false);
	}

	private void bezeroKontaktuak() {
		Kontsola.imprimatu("Bezeroaren izena eta abizena (hutsik utzi guztiak ikusteko):");
		String bilaketa = Teklatua.irakurri();

		if (bilaketa == null || bilaketa.isBlank()) {
			bezeroFitxategia.bilaketaInprimatu(bezeroFitxategia.bezeroGuztiak());
			return;
		}

		List<Bezeroa> emaitza = bezeroFitxategia.bezeroakBilatu(bilaketa);
		if (emaitza.isEmpty()) {
			Kontsola.imprimatu("Ez da bezerorik aurkitu: " + bilaketa);
		} else {
			bezeroFitxategia.bilaketaInprimatu(emaitza);
		}
	}

	private void fakturaSortu() {
		Kontsola.imprimatu("Bezeroaren izena eta abizena:");
		String bilaketa = Teklatua.irakurri();

		if (bilaketa == null || bilaketa.isBlank()) {
			Kontsola.imprimatu("Izena edo abizena sartu behar duzu.");
			return;
		}

		List<Bezeroa> bezeroak = bezeroFitxategia.bezeroakBilatu(bilaketa);
		if (bezeroak.isEmpty()) {
			Kontsola.imprimatu("Ez da bezerorik aurkitu: " + bilaketa);
			return;
		}

		Bezeroa bezeroa = bezeroak.get(0);
		if (bezeroak.size() > 1) {
			bezeroFitxategia.bezeroZerrendaInprimatu(bezeroak);
			bezeroa = bezeroak.get(aukeraEskatu(bezeroak.size()) - 1);
		}

		List<Eskaria> eskariak = bezeroFitxategia.bezeroarenEskariak(bezeroa.getId());
		if (eskariak.isEmpty()) {
			Kontsola.imprimatu(bezeroa.getIzena() + " " + bezeroa.getAbizena() + "-ek ez du eskaririk.");
			return;
		}

		bezeroFitxategia.eskariakInprimatu(bezeroa, eskariak);
		Eskaria eskaria = eskariak.get(aukeraEskatu(eskariak.size()) - 1);
		fakturak.fakturaInprimatu(eskaria);
	}

	private Integer aukeraEskatu(int max) {
		Integer aukera;
		do {
			Kontsola.imprimatu("Aukeratu (1-" + max + "):");
			aukera = Teklatua.irakurriZenbakia();
		} while (aukera < 1 || aukera > max);
		return aukera;
	}

	// FITXATEGIAK
	public static void fitxategiakMenua() {
		String[] izenak = { "langileak", "bezeroak", "bezero_telefonoa", "eskaria", "eskari_lerroa" };
		String[] bideak = { Fitxategiak.langileak, Fitxategiak.bezeroak, Fitxategiak.bezero_telefonoa,
				Fitxategiak.eskaria, Fitxategiak.eskari_lerroa };
		boolean atzera = false;
		do {
			Kontsola.imprimatu("\n FITXATEGIAK");
			for (int i = 0; i < izenak.length; i++) {
				Kontsola.imprimatu((i + 1) + ". " + izenak[i]);
			}
			Kontsola.imprimatu((izenak.length + 1) + ". Atzera joan");
			Kontsola.imprimatu("Aukeratu:");
			Integer aukera = Teklatua.irakurriZenbakia();
			if (aukera >= 1 && aukera <= izenak.length) {
				Kontsola.imprimatu("\n-" + izenak[aukera - 1] + "-");
				Kontsola.imprimatu(Fitxategiak.irakurriOsorik(bideak[aukera - 1]));
			} else if (aukera == izenak.length + 1) {
				atzera = true;
			} else {
				Kontsola.imprimatu("Aukera okerra.");
			}
		} while (atzera == false);
	}
}