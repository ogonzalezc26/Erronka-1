package kontrolagailua;

import java.util.Scanner;

public class Teklatua {
	public static Scanner sc = new Scanner(System.in);

	public static String irakurri() {
		String erantzuna = sc.nextLine();
		return erantzuna;
	}

	public static Integer irakurriZenbakia() {
		Integer zenbakia = null;
		while (zenbakia == null) {
			try {	
				zenbakia = Integer.valueOf(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				ikuspegia.Kontsola.imprimatu("Zenbaki baliogabea, saiatu berriro:");
			}
		}
		return zenbakia;
	}
}
