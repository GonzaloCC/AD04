package AD.AD04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="Provincias")
public class Provincias implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToMany(mappedBy = "provincias",cascade=CascadeType.ALL)
	private List<Provincia> provincias;

	
	
	public Provincias() {
		super();
		this.provincias= new ArrayList<Provincia>();
	}

	public List<Provincia> getArrayProvincias() {
		return provincias;
	}

	public void setArrayProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}
	
	

}
