package eredua;

//BEAN
public class Langilea {
	private Integer id;
	private String izena;
	private String abizena;
	private String telefonoa;
	private String emaila;
	private String kontratazioaData;
	private Integer nagusiaId;

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

	public String getTelefonoa() {
		return telefonoa;
	}

	public void setTelefonoa(String telefonoa) {
		this.telefonoa = telefonoa;
	}

	public String getEmaila() {
		return emaila;
	}

	public void setEmaila(String emaila) {
		this.emaila = emaila;
	}

	public String getKontratazioaData() {
		return kontratazioaData;
	}

	public void setKontratazioaData(String kontratazioaData) {
		this.kontratazioaData = kontratazioaData;
	}

	public Integer getNagusiaId() {
		return nagusiaId;
	}

	public void setNagusiaId(Integer nagusiaId) {
		this.nagusiaId = nagusiaId;
	}
}
