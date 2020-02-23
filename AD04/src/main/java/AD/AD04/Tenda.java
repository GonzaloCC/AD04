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
	private long id;
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

	@OneToMany(mappedBy="tenda")
	private Set <ProductoTenda> productos;
	
	
	@OneToMany(mappedBy="tenda")
	private Set <EmpregadoTenda> empregados;
	
	public Tenda(String nome, String cidade,Provincia provincia,Compania compania) {
		super();
		this.nome = nome;
		this.cidade = cidade;
		this.provincia=provincia;
		this.productos = new HashSet<ProductoTenda>();
		this.empregados = new HashSet<EmpregadoTenda>();
		this.compania=compania;
	}



	public Tenda() {}
	
	
	
	
	
	
	 public long getId() {
		return id;
	}



	public void setId(long id) {
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



	public Set<ProductoTenda> getProductos() {
		return productos;
	}



	public void setProductos(Set<ProductoTenda> productos) {
		this.productos = productos;
	}



	public Set<EmpregadoTenda> getEmpregados() {
		return empregados;
	}



	public void setEmpregados(Set<EmpregadoTenda> empregados) {
		this.empregados = empregados;
	}



	public void addEmpregado(EmpregadoTenda empregado){
	        this.empregados.add(empregado);
	    }
	
	public void addProducto(ProductoTenda producto){
        this.productos.add(producto);
    }


    //Metodo que pasa a JSON
    public String toJSON(){
        String json = new String();
        json = json + "{ ";
        json = json + "\"nome\" : " + this.nome + " }";
        return json;
    }








}
