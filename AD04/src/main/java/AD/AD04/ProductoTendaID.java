package AD.AD04;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductoTendaID implements Serializable{
	private static final long serialVersionUID = 1L;

    private Long idProducto;
    private Long idTenda;

    public ProductoTendaID() {

    }
    
    

   
    public ProductoTendaID(Long idEmpregado, Long idTenda) {
		super();
		this.idProducto = idEmpregado;
		this.idTenda = idTenda;
	}









	public Long getIdProducto() {
		return idProducto;
	}




	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
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
                + ((idProducto == null) ? 0 : idProducto.hashCode());
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
        ProductoTendaID other = (ProductoTendaID) obj;
        return Objects.equals(getIdProducto(), other.getIdProducto()) && Objects.equals(getIdTenda(), other.getIdTenda());
    }


}
