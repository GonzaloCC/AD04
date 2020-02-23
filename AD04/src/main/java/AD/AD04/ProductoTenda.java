package AD.AD04;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "ProductoTenda")
public class ProductoTenda implements Serializable {

		
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	   @Id
	    @ManyToOne
	    @JoinColumn(name = "idProducto", referencedColumnName = "id")
	    private Producto producto;
	    
	    @Id
	    @ManyToOne
	    @JoinColumn(name = "idTenda", referencedColumnName = "id")
	    private Tenda tenda;

	    @Column(name = "cantidade")
	    private int cantidade ;
	    @Column(name = "prezo")
	    private double prezo;
	    
		@ManyToOne
		@JoinColumn(name="idCompania")
		private Compania compania;
	    



	    
		public ProductoTenda() {
			super();
			// TODO Auto-generated constructor stub
		}



		public ProductoTenda( Producto producto, Tenda tenda, int cantidade, double prezo,Compania compania) {
			super();
			this.producto = producto;
			this.tenda = tenda;
			this.cantidade = cantidade;
			this.prezo = prezo;
			this.compania=compania;
		}







		public Producto getProducto() {
			return producto;
		}



		public void setProducto(Producto producto) {
			this.producto = producto;
		}



		public Tenda getTenda() {
			return tenda;
		}



		public void setTenda(Tenda tenda) {
			this.tenda = tenda;
		}



		public int getCantidade() {
			return cantidade;
		}



		public void setCantidade(int cantidade) {
			this.cantidade = cantidade;
		}



		public double getPrezo() {
			return prezo;
		}



		public void setPrezo(double prezo) {
			this.prezo = prezo;
		}


	    
	    //Metodo que pasa a JSON
		
	    public String toJSON(){
	        String json = new String();
	        json = json + "{ ";
	        json = json + "\"tenda\" : \"" + this.tenda.toJSON() + "\",";
	        
	        json = json + "\"producto\" : \"" + this.producto.toJSON() + "\",";
	        json = json + "\"cantidade\" : " + this.cantidade + " }";
	        return json;
	    }
		



		
	    
}
