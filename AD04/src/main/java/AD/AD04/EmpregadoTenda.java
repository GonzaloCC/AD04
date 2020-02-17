package AD.AD04;

import java.io.Serializable;
import java.util.Objects;

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
	
    @EmbeddedId
    private EmpregadoTendaID id = new EmpregadoTendaID();
	
    @ManyToOne
    @MapsId("idEmpregado")
    private Empregado empregado;
 
    @ManyToOne
    @MapsId("idTenda")
    private Tenda tenda;

    private int horas ;
    
	@ManyToOne
	@JoinColumn(name="idCompania")
	private Compania compania;


    
	public EmpregadoTenda() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	



	public EmpregadoTendaID getId() {
		return id;
	}








	public void setId(EmpregadoTendaID id) {
		this.id = id;
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
