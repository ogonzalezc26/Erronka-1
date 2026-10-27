package businesslogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;
import eredua.Eskaria;
import eredua.EskariLerroa;
import ikuspegia.Kontsola;

public class Fakturak {

	private String fitxategiIzena;

	// ID_ESKARI:
	private TreeMap<Integer, TreeMap<Integer, EskariLerroa>> lerroakEskarika = new TreeMap<Integer, TreeMap<Integer, EskariLerroa>>();

	public Fakturak(String fitxategiIzena) {
		this.fitxategiIzena = fitxategiIzena;
	}

	// ESKARI_LERROAK: ID_ESKARI, ID_LERRO, ID_PRODUKTU, KOPURUA, SALNEURRIA
	public void fitxategiaIrakurri() {
		lerroakEskarika.clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fitxategiIzena));
			br.readLine();
			String lerroa;
			while ((lerroa = br.readLine()) != null) {
				if (lerroa.isBlank())
					continue;
				String[] tokenak = lerroa.split("\t");

				EskariLerroa l = new EskariLerroa();
				l.setEskariZenbakia(Integer.valueOf(tokenak[0].trim()));
				l.setProduktua(tokenak[2].trim());
				l.setKopurua(Integer.valueOf(tokenak[3].trim()));
				l.setSalneurria(Double.valueOf(tokenak[4].trim().replace(',', '.')));

				Integer lerroId = Integer.valueOf(tokenak[1].trim());
				lerroakEskarika.computeIfAbsent(l.getEskariZenbakia(), k -> new TreeMap<Integer, EskariLerroa>())
						.put(lerroId, l);
			}
			br.close();
		} catch (Exception e) {
			Kontsola.imprimatu("Errorea eskari-lerroen fitxategia irakurtzean: " + e.toString());
		}
	}

	public void fakturaInprimatu(Eskaria eskaria) {
		TreeMap<Integer, EskariLerroa> lerroak = lerroakEskarika.get(eskaria.getZenbakia());
		StringBuilder sb = new StringBuilder();
		sb.append("\nFAKTURA - Eskaria ").append(eskaria.getZenbakia()).append(" (").append(eskaria.getData())
				.append(")\n");

		if (lerroak == null) {
			sb.append("Eskari honek ez du lerrorik.\n");
			Kontsola.imprimatu(sb.toString());
			return;
		}

		sb.append(String.format("%-10s %-12s %-10s %-12s %-12s%n", "ID_ESKARI", "ID_PRODUKTU", "KOPURUA", "SALNEURRIA",
				"PREZIOA"));

		double totala = 0;
		for (EskariLerroa l : lerroak.values()) {
			double prezioa = l.getKopurua() * l.getSalneurria();
			totala += prezioa;
			sb.append(String.format("%-10d %-12s %-10d %-12.2f %-12.2f%n", l.getEskariZenbakia(), l.getProduktua(),
					l.getKopurua(), l.getSalneurria(), prezioa));
		}
		totala = Math.round(totala * 100.0) / 100.0;
		sb.append(String.format("%nGUZTIRA: %.2f%n", totala));

		Kontsola.imprimatu(sb.toString());
	}
}