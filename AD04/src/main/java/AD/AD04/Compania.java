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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Compania")
public class Compania implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  int id;
	@OneToMany(mappedBy = "compania",cascade=CascadeType.ALL)
	private Set<Tenda> tendas ;
	@OneToMany(mappedBy = "compania",cascade=CascadeType.ALL)
	private Set<Cliente> clientes;
	@OneToMany(mappedBy = "compania",cascade=CascadeType.ALL)
	private Set<Producto> productos;
	@OneToMany(mappedBy = "compania",cascade=CascadeType.ALL)
	private Set<Empregado> empregados;
	@OneToMany(mappedBy = "compania",cascade=CascadeType.ALL)
	private Set<ProductoTenda> productotenda;
	@OneToMany(mappedBy = "compania",cascade=CascadeType.ALL)
	private Set<EmpregadoTenda> empregadotenda;
	
	public Compania() {
		this.tendas = new HashSet<Tenda>();
		this.clientes = new HashSet<Cliente>();
		this.productos = new HashSet<Producto>();
		this.empregados = new HashSet<Empregado>();
		this.productotenda = new HashSet<ProductoTenda>();
		this.empregadotenda = new HashSet<EmpregadoTenda>();
	}

	public Set<Tenda> getTendas() {
		return tendas;
	}

	public void setTendas(Set<Tenda> tendas) {
		this.tendas = tendas;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
	

	public void addTenda(Tenda tenda){
	        this.tendas.add(tenda);
	    }
	public void addCliente(Cliente cliente){
	        this.clientes.add(cliente);
	    }

	public int getId() {
		return id;
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

	public Set<ProductoTenda> getProductotenda() {
		return productotenda;
	}

	public void setProductotenda(Set<ProductoTenda> productotenda) {
		this.productotenda = productotenda;
	}

	public Set<EmpregadoTenda> getEmpregadotenda() {
		return empregadotenda;
	}

	public void setEmpregadotenda(Set<EmpregadoTenda> empregadotenda) {
		this.empregadotenda = empregadotenda;
	}
	

	

}
