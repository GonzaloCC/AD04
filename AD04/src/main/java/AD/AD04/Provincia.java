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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Provincia")
public class Provincia implements Serializable{
	@Id
	@Column(name="id")
	private int id;
	@Column(name="nome")
	String nome;
	
	@OneToMany(mappedBy = "provincia",cascade=CascadeType.ALL)
	private Set<Tenda> tendas;
	
	@ManyToOne
	@JoinColumn(name="idProvincia")
	private Provincias provincias;
	
	
	
	public Provincia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Provincia(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		this.tendas = new HashSet<Tenda>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void addTenda(Tenda tenda){
	    this.tendas.add(tenda);
	}

}
