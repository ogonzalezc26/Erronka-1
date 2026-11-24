package businesslogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Fitxategiak {

	private static String bidea = "/datuak/";

	public static String langileak = bidea + "LANGILEAK.txt";
	public static String bezeroak = bidea + "BEZEROAK.txt";
	public static String bezero_telefonoa = bidea + "BEZERO_TELEFONOAK.txt";
	public static String eskaria = bidea + "ESKARIAK.txt";
	public static String eskari_lerroa = bidea + "ESKARI_LERROAK.txt";
	public static String faktura = "FAKTURA.txt";

	public static BufferedReader irakurgailua(String bidea) throws IOException {
		InputStream is = Fitxategiak.class.getResourceAsStream(bidea);
		if (is == null) {
			throw new FileNotFoundException("Ez da aurkitu: " + bidea);
		}
		return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
	}

	public static String irakurriOsorik(String bidea) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = irakurgailua(bidea)) {
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