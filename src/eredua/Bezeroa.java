package eredua;

import java.util.ArrayList;
import java.util.List;

//BEAN
public class Bezeroa {
	private Integer id;
	private String izena;
	private String abizena;
	private String postaElektronicoa;
	private List<String> telefonoak = new ArrayList<String>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena() {
		return abizena;
	}

	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	public String getPostaElektronicoa() {
		return postaElektronicoa;
	}

	public void setPostaElektronicoa(String postaElektronicoa) {
		this.postaElektronicoa = postaElektronicoa;
	}

	public List<String> getTelefonoak() {
		return telefonoak;
	}

	public void addTelefonoa(String telefonoa) {
		this.telefonoak.add(telefonoa);
	}
}
