package AD.AD04;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Producto")

public class Producto implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column (name="identificador")
	private String identificador;
	@Column(name="descripcion")
	private String descripcion;
	@ManyToOne
	@JoinColumn(name="idCompania")
	private Compania compania;
	
	@OneToMany(mappedBy = "producto")	
	private Set <ProductoTenda> tendas;

	
	
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(String identificador, String descripcion,Compania compania) {
		super();
		this.identificador = identificador;
		this.descripcion = descripcion;
		this.tendas=new HashSet<ProductoTenda>();
		this.compania=compania;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Set<ProductoTenda> getTendas() {
		return tendas;
	}

	public void setTendas(Set<ProductoTenda> tendas) {
		this.tendas = tendas;
	}

	public void addTenda(ProductoTenda tenda){
        this.tendas.add(tenda);
    }

	public Compania getCompania() {
		return compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

	//Metodo que pasa a JSON
    public String toJSON(){
        String json = new String();
        json = json + "{ ";
        json = json + "\"identificador\" : \"" + this.identificador +  " }";
        return json;
    }

}
