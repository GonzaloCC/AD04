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
@Table(name="Empregado")
public class Empregado implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="nome")
	private String nome;
	@Column(name="apelido1")
	private String apelido1;
	@Column(name="apelido2")
	private String apelido2;
	@ManyToOne
	@JoinColumn(name="idCompania")
	private Compania compania;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="EmpregadoTenda",joinColumns={@JoinColumn(name="idEmpregado")},inverseJoinColumns={@JoinColumn(name="idTenda")})
	private Set <Tenda> tendas;
	
	

	public Empregado() {
		super();
		
	}
	public Empregado(String nome, String apelido1, String apelido2) {
		super();
		this.nome = nome;
		this.apelido1 = apelido1;
		this.apelido2 = apelido2;
		this.tendas = new HashSet<Tenda>();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
    public String getApelido1() {
		return apelido1;
	}
	public void setApelido1(String apelido1) {
		this.apelido1 = apelido1;
	}
	public String getApelido2() {
		return apelido2;
	}
	public void setApelido2(String apelido2) {
		this.apelido2 = apelido2;
	}
	
	public void addTenda(Tenda tenda){
	        this.tendas.add(tenda);
	    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
}
