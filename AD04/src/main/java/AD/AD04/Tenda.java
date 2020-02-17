package AD.AD04;


import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name="Tenda")
public class Tenda implements Serializable  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="nome")
	private String nome;
	@Column(name="cidade")
	private String cidade;
	
	@ManyToOne
	@JoinColumn(name="idCompania")
	private Compania compania;
	@ManyToOne
	@JoinColumn(name="idProvincia")
	private Provincia provincia;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="ProductoTenda",joinColumns={@JoinColumn(name="idTenda")},inverseJoinColumns={@JoinColumn(name="idProducto")})
	private Set <Producto> productos;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="EmpregadoTenda",joinColumns={@JoinColumn(name="idTenda")},inverseJoinColumns={@JoinColumn(name="idEmpregado")})
	private Set <Empregado> empregados;
	
	public Tenda(String nome, String cidade,Provincia provincia) {
		super();
		this.nome = nome;
		this.cidade = cidade;
		this.provincia=provincia;
		this.productos = new HashSet<Producto>();
		this.empregados = new HashSet<Empregado>();
	}



	public Tenda() {}
	
	
	
	
	
	
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



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	public Compania getCompania() {
		return compania;
	}



	public void setCompania(Compania compania) {
		this.compania = compania;
	}



	public Provincia getProvincia() {
		return provincia;
	}



	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}



	public Set<Producto> getProductos() {
		return productos;
	}



	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}



	public Set<Empregado> getEmpregados() {
		return empregados;
	}



	public void setEmpregados(Set<Empregado> empregados) {
		this.empregados = empregados;
	}



	public void addEmpregado(Empregado empregado){
	        this.empregados.add(empregado);
	    }
	
	public void addProducto(Producto producto){
        this.productos.add(producto);
    }











}
