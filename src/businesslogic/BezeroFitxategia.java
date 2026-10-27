package businesslogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import eredua.Bezeroa;
import eredua.Eskaria;
import eredua.EskariLerroa;
import ikuspegia.Kontsola;

public class BezeroFitxategia {

	private String bezeroFitxategia;
	private String telefonoFitxategia;
	private String eskariFitxategia;

	private TreeMap<String, TreeMap<Integer, Bezeroa>> bezeroakHitzez = new TreeMap<String, TreeMap<Integer, Bezeroa>>(
			String.CASE_INSENSITIVE_ORDER);

	private TreeMap<Integer, Bezeroa> bezeroakIdz = new TreeMap<Integer, Bezeroa>();

	private TreeMap<Integer, TreeMap<Integer, Eskaria>> eskariakBezeroz = new TreeMap<Integer, TreeMap<Integer, Eskaria>>();

	public BezeroFitxategia(String bezeroFitxategia, String telefonoFitxategia, String eskariFitxategia) {
		this.bezeroFitxategia = bezeroFitxategia;
		this.telefonoFitxategia = telefonoFitxategia;
		this.eskariFitxategia = eskariFitxategia;
	}

	private void indexatu(String hitza, Bezeroa b) {
		bezeroakHitzez.computeIfAbsent(hitza, k -> new TreeMap<Integer, Bezeroa>()).put(b.getId(), b);
	}

	// BEZEROAK: ID, IZENA, ABIZENA, HELBIDEA, EMAILA
	private void bezeroakIrakurri() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(bezeroFitxategia));
			br.readLine();
			String lerroa;
			while ((lerroa = br.readLine()) != null) {
				if (lerroa.isBlank())
					continue;
				String[] tokenak = lerroa.split("\t");

				Bezeroa b = new Bezeroa();
				b.setId(Integer.valueOf(tokenak[0].trim()));
				b.setIzena(tokenak[1].trim());
				b.setAbizena(tokenak[2].trim());
				b.setPostaElektronicoa(tokenak[4].trim());
				indexatu(b.getIzena(), b);
				if (!b.getAbizena().equalsIgnoreCase(b.getIzena())) {
					indexatu(b.getAbizena(), b);
				}
				bezeroakIdz.put(b.getId(), b);
			}
			br.close();
		} catch (Exception e) {
			Kontsola.imprimatu("Errorea bezeroen fitxategia irakurtzean: " + e.toString());
		}
	}

	// BEZERO_TELEFONOAK: ID, ID_BEZERO, ZENBAKIA
	private void telefonoakIrakurri() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(telefonoFitxategia));
			br.readLine();
			String lerroa;
			while ((lerroa = br.readLine()) != null) {
				if (lerroa.isBlank())
					continue;
				String[] tokenak = lerroa.split("\t");

				Bezeroa b = bezeroakIdz.get(Integer.valueOf(tokenak[1].trim()));
				if (b != null) {
					b.addTelefonoa(tokenak[2].trim());
				}
			}
			br.close();
		} catch (Exception e) {
			Kontsola.imprimatu("Errorea telefonoen fitxategia irakurtzean: " + e.toString());
		}
	}

	public List<Bezeroa> bezeroGuztiak() {
		return new ArrayList<Bezeroa>(bezeroakIdz.values());
	}

	public List<Bezeroa> bezeroakBilatu(String bilaketa) {
		TreeMap<Integer, Bezeroa> emaitza = new TreeMap<Integer, Bezeroa>();
		if (bilaketa != null && !bilaketa.isBlank()) {
			String[] hitzak = bilaketa.trim().split("\\s+");
			for (String hitza : hitzak) {
				TreeMap<Integer, Bezeroa> taldea = bezeroakHitzez.get(hitza);
				if (taldea != null) {
					emaitza.putAll(taldea);
				}
			}
		}
		return new ArrayList<Bezeroa>(emaitza.values());
	}

	public void bezeroarenInformazioaInprimatu(Bezeroa b) {
		Kontsola.imprimatu("");
		Kontsola.imprimatu("ID           : " + b.getId());
		Kontsola.imprimatu("Izen-abizena : " + b.getIzena() + " " + b.getAbizena());
		Kontsola.imprimatu("Emaila       : " + b.getPostaElektronicoa());
		Kontsola.imprimatu("Telefonoa    : " + String.join(", ", b.getTelefonoak()));
		Kontsola.imprimatu("");
	}

	public void bilaketaInprimatu(List<Bezeroa> bezeroak) {
		Kontsola.imprimatu("");
		Kontsola.imprimatu("Aurkitutako bezeroak: " + bezeroak.size());
		for (Bezeroa b : bezeroak) {
			bezeroarenInformazioaInprimatu(b);
		}
	}

	// ESKARIAK: ID, ID_BEZERO, ID_EGOERA, ESKAERA_DATA
	private void eskariakIrakurri() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(eskariFitxategia));
			br.readLine();
			String lerroa;
			while ((lerroa = br.readLine()) != null) {
				if (lerroa.isBlank())
					continue;
				String[] tokenak = lerroa.split("\t");

				Eskaria e = new Eskaria();
				e.setZenbakia(Integer.valueOf(tokenak[0].trim()));
				e.setBezeroId(Integer.valueOf(tokenak[1].trim()));
				e.setData(tokenak[3].trim());
				eskariakBezeroz.computeIfAbsent(e.getBezeroId(), k -> new TreeMap<Integer, Eskaria>())
						.put(e.getZenbakia(), e);
			}
			br.close();
		} catch (Exception ex) {
			Kontsola.imprimatu("Errorea eskarien fitxategia irakurtzean: " + ex.toString());
		}
	}

	public void eskariakInprimatu(Bezeroa b, List<Eskaria> eskariak) {
		Kontsola.imprimatu("");
		Kontsola.imprimatu(b.getIzena() + " " + b.getAbizena() + "-ren eskariak: " + eskariak.size());
		for (int i = 0; i < eskariak.size(); i++) {
			Eskaria e = eskariak.get(i);
			Kontsola.imprimatu((i + 1) + ". Eskaria " + e.getZenbakia() + " - " + e.getData());
		}
	}

	public List<Eskaria> bezeroarenEskariak(Integer bezeroId) {
		TreeMap<Integer, Eskaria> taldea = eskariakBezeroz.get(bezeroId);
		if (taldea == null) {
			return new ArrayList<Eskaria>();
		}
		return new ArrayList<Eskaria>(taldea.values());
	}

	public void bezeroZerrendaInprimatu(List<Bezeroa> bezeroak) {
		Kontsola.imprimatu("");
		Kontsola.imprimatu("Bezero hauek aurkitu dira:");
		for (int i = 0; i < bezeroak.size(); i++) {
			Bezeroa b = bezeroak.get(i);
			Kontsola.imprimatu((i + 1) + ". " + b.getIzena() + " " + b.getAbizena() + " (ID " + b.getId() + ")");
		}
	}

	public void fitxategiakIrakurri() {
		bezeroakHitzez.clear();
		bezeroakIdz.clear();
		eskariakBezeroz.clear();
		bezeroakIrakurri();
		telefonoakIrakurri();
		eskariakIrakurri();
	}
}