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
	private int id;
	@Column (name="identificador")
	private String identificador;
	@Column(name="descripcion")
	private String descripcion;
	@ManyToOne
	@JoinColumn(name="idCompania")
	private Compania compania;
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="EmpregadoTenda",joinColumns={@JoinColumn(name="idEmpregado")},inverseJoinColumns={@JoinColumn(name="idTenda")})
	private Set <Tenda> tendas;

	
	
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(String identificador, String descripcion) {
		super();
		this.identificador = identificador;
		this.descripcion = descripcion;
		this.tendas=new HashSet<Tenda>();
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
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Set<Tenda> getTendas() {
		return tendas;
	}

	public void setTendas(Set<Tenda> tendas) {
		this.tendas = tendas;
	}

	public void addTenda(Tenda tenda){
        this.tendas.add(tenda);
    }



}
