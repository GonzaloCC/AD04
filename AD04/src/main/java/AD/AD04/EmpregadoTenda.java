package AD.AD04;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="EmpregadoTenda")
public class EmpregadoTenda implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @ManyToOne
    @JoinColumn(name = "idEmpregado", referencedColumnName = "id")
    private Empregado empregado;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "idTenda", referencedColumnName = "id")
    private Tenda tenda;

    @Column(name="horas")
    private int horas ;
    
	@ManyToOne
	@JoinColumn(name="idCompania")
	private Compania compania;


    
	public EmpregadoTenda() {
		super();
		// TODO Auto-generated constructor stub
	}




	public EmpregadoTenda(Empregado empregado, Tenda tenda, int horas,Compania compania) {
		super();
		this.empregado = empregado;
		this.tenda = tenda;
		this.horas = horas;
		this.compania=compania;
	}




	public int getHoras() {
		return horas;
	}


	public void setHoras(int horas) {
		this.horas = horas;
	}


	public Empregado getEmpregado() {
		return empregado;
	}


	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}


	public Tenda getTenda() {
		return tenda;
	}


	public void setTenda(Tenda tenda) {
		this.tenda = tenda;
	}

	
    
}
