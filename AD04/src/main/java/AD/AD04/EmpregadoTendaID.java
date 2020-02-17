package AD.AD04;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmpregadoTendaID implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Long idEmpregado;
    private Long idTenda;

    public EmpregadoTendaID() {

    }
    
    

   
    public EmpregadoTendaID(Long idEmpregado, Long idTenda) {
		super();
		this.idEmpregado = idEmpregado;
		this.idTenda = idTenda;
	}




	public Long getIdEmpregado() {
		return idEmpregado;
	}





	public void setIdEmpregado(Long idEmpregado) {
		this.idEmpregado = idEmpregado;
	}





	public Long getIdTenda() {
		return idTenda;
	}





	public void setIdTenda(Long idTenda) {
		this.idTenda = idTenda;
	}


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idEmpregado == null) ? 0 : idEmpregado.hashCode());
        result = prime * result
                + ((idTenda == null) ? 0 : idTenda.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmpregadoTendaID other = (EmpregadoTendaID) obj;
        return Objects.equals(getIdEmpregado(), other.getIdEmpregado()) && Objects.equals(getIdTenda(), other.getIdTenda());
    }


}
