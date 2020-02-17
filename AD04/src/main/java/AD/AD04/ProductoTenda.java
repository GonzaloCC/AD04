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
public class ProductoTenda {
	 @EmbeddedId
	 @GeneratedValue(strategy=GenerationType.AUTO)
	    private ProductoTendaID id = new ProductoTendaID();
		
	    @ManyToOne
	    @MapsId("idProducto")
	    private Producto producto;
	 
	    @ManyToOne
	    @MapsId("idTenda")
	    private Tenda tenda;

	    private int cantidade ;
	    private double prezo;
	    
		@ManyToOne
		@JoinColumn(name="idCompania")
		private Compania compania;


	    
		public ProductoTenda() {
			super();
			// TODO Auto-generated constructor stub
		}



		public ProductoTenda( Producto producto, Tenda tenda, int cantidade, double prezo) {
			super();
			this.producto = producto;
			this.tenda = tenda;
			this.cantidade = cantidade;
			this.prezo = prezo;
		}



		public ProductoTendaID getId() {
			return id;
		}



		public void setId(ProductoTendaID id) {
			this.id = id;
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


		

		



		
	    
}
