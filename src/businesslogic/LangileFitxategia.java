package businesslogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import eredua.Langilea;
import ikuspegia.Kontsola;

public class LangileFitxategia {

	private String fitxategiIzena;

	public List<Langilea> langileGuztiak() {
		return new ArrayList<Langilea>(langileakIdz.values());
	}

	private TreeMap<String, TreeMap<Integer, Langilea>> langileakHitzez = new TreeMap<String, TreeMap<Integer, Langilea>>(
			String.CASE_INSENSITIVE_ORDER);

	private TreeMap<Integer, Langilea> langileakIdz = new TreeMap<Integer, Langilea>();

	private TreeMap<Integer, TreeMap<Integer, Langilea>> langileakNagusiz = new TreeMap<Integer, TreeMap<Integer, Langilea>>();

	public LangileFitxategia() {
	}

	public LangileFitxategia(String fitxategiIzena) {
		this.fitxategiIzena = fitxategiIzena;
	}

	public void setIzena(String fitxategiIzena) {
		this.fitxategiIzena = fitxategiIzena;
	}

	private void indexatu(String hitza, Langilea l) {
		langileakHitzez.computeIfAbsent(hitza, k -> new TreeMap<Integer, Langilea>()).put(l.getId(), l);
	}

	public void fitxategiaIrakurri() {
		langileakHitzez.clear();
		langileakIdz.clear();
		langileakNagusiz.clear();
		try {
			FileReader fr = new FileReader(fitxategiIzena);
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			do {
				String lerroa = br.readLine();
				if (lerroa == null)
					break;
				if (lerroa.isBlank())
					continue;
				String[] tokenak = lerroa.split("\t");

				Langilea l = new Langilea();
				l.setId(Integer.valueOf(tokenak[0].trim()));
				l.setIzena(tokenak[1].trim());
				l.setAbizena(tokenak[2].trim());
				l.setEmaila(tokenak[3].trim());
				l.setTelefonoa(tokenak[4].trim());
				l.setKontratazioaData(tokenak[5].trim());
				l.setNagusiaId(Integer.valueOf(tokenak[6].trim()));
				indexatu(l.getIzena(), l);
				if (!l.getAbizena().equalsIgnoreCase(l.getIzena())) {
					indexatu(l.getAbizena(), l);
				}
				langileakIdz.put(l.getId(), l);
				langileakNagusiz.computeIfAbsent(l.getNagusiaId(), k -> new TreeMap<Integer, Langilea>()).put(l.getId(),
						l);
			} while (true);
			br.close();
			fr.close();
		} catch (Exception e) {
			Kontsola.imprimatu("Errorea fitxategia irakurtzean: " + e.toString());
		}
	}

	public List<Langilea> langileakBilatu(String bilaketa) {
		TreeMap<Integer, Langilea> emaitza = new TreeMap<Integer, Langilea>();
		if (bilaketa != null && !bilaketa.isBlank()) {
			String[] hitzak = bilaketa.trim().split("\\s+");
			for (String hitza : hitzak) {
				TreeMap<Integer, Langilea> taldea = langileakHitzez.get(hitza);
				if (taldea != null) {
					emaitza.putAll(taldea);
				}
			}
		}
		return new ArrayList<Langilea>(emaitza.values());
	}

	public Langilea langileaBilatuIdz(Integer id) {
		return langileakIdz.get(id);
	}

	public void langilearenInformazioaInprimatu(Langilea l) {
		Kontsola.imprimatu("");
		Kontsola.imprimatu("ID           : " + l.getId());
		Kontsola.imprimatu("Izen-abizena : " + l.getIzena() + " " + l.getAbizena());
		Kontsola.imprimatu("Telefonoa    : " + l.getTelefonoa());
		Kontsola.imprimatu("Kontratazio-Data   : " + l.getKontratazioaData());
		Kontsola.imprimatu("");
	}

	public void bilaketaInprimatu(List<Langilea> langileak) {
		Kontsola.imprimatu("");
		Kontsola.imprimatu("Aurkitutako langileak: " + langileak.size());
		for (Langilea l : langileak) {
			langilearenInformazioaInprimatu(l);
		}
	}

	public List<Langilea> nagusiarenLangileak(Integer nagusiId) {
		TreeMap<Integer, Langilea> taldea = langileakNagusiz.get(nagusiId);
		if (taldea == null) {
			return new ArrayList<Langilea>();
		}
		return new ArrayList<Langilea>(taldea.values());
	}

	public void langileZerrendaInprimatu(Langilea nagusia, List<Langilea> langileak) {
		Kontsola.imprimatu("");
		Kontsola.imprimatu("Nagusia: " + nagusia.getIzena() + " " + nagusia.getAbizena());
		Kontsola.imprimatu("Langile kopurua: " + langileak.size());
		Kontsola.imprimatu("");
		for (Integer i = 0; i < langileak.size(); ++i) {
			Langilea l = langileak.get(i);
			Kontsola.imprimatu((i + 1) + ". " + l.getIzena() + " " + l.getAbizena() + " - " + l.getEmaila());
		}
		Kontsola.imprimatu("");
	}
}