package AD.AD04;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Cliente")

public class Cliente implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="nome")
	private String nome;
	@Column(name="apelidos")
	private String apelidos;
	@Column(name="email")
	private String email;
	@ManyToOne
	@JoinColumn(name="idCompania")
	private Compania compania;
	
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nome, String apelidos, String email) {
		super();
		this.nome = nome;
		this.apelidos = apelidos;
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelidos() {
		return apelidos;
	}
	public void setApelidos(String apelidos) {
		this.apelidos = apelidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
 
	
	

}
