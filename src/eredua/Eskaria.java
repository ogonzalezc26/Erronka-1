package eredua;

import java.util.ArrayList;
import java.util.List;

//BEAN
public class Eskaria {
	private Integer zenbakia;
	private Integer bezeroId;
	private String data;
	private List<EskariLerroa> lerroak = new ArrayList<EskariLerroa>();

	public Integer getZenbakia() {
		return zenbakia;
	}

	public void setZenbakia(Integer zenbakia) {
		this.zenbakia = zenbakia;
	}

	public Integer getBezeroId() {
		return bezeroId;
	}

	public void setBezeroId(Integer bezeroId) {
		this.bezeroId = bezeroId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<EskariLerroa> getLerroak() {
		return lerroak;
	}

	public void addLerroa(EskariLerroa lerroa) {
		this.lerroak.add(lerroa);
	}
}
