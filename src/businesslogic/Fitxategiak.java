package businesslogic;

import java.io.BufferedReader;
import java.io.FileReader;

public class Fitxategiak {

	private static String bidea = "src/datuak/";

	public static String langileak = bidea + "LANGILEAK.txt";
	public static String bezeroak = bidea + "BEZEROAK.txt";
	public static String bezero_telefonoa = bidea + "BEZERO_TELEFONOAK.txt";
	public static String eskaria = bidea + "ESKARIAK.txt";
	public static String eskari_lerroa = bidea + "ESKARI_LERROAK.txt";
	public static String faktura = bidea + "FAKTURA.txt";

	public static String getBidea() {
		return bidea;
	}

	public static String irakurriOsorik(String bidea) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(bidea))) {
			String lerroa;
			while ((lerroa = br.readLine()) != null) {
				sb.append(lerroa).append("\n");
			}
		} catch (Exception e) {
			return "Errorea fitxategia irakurtzean: " + e.toString();
		}
		return sb.toString();
	}
}