package AD.AD04;

public class Configuracion {

	DbConnection dbConnection;
	Hibernate hibernate;
	public Configuracion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Configuracion(DbConnection dbConnection, Hibernate hibernate) {
		super();
		this.dbConnection = dbConnection;
		this.hibernate = hibernate;
	}
	public DbConnection getDbConnection() {
		return dbConnection;
	}
	public void setDbConnection(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	public Hibernate getHibernate() {
		return hibernate;
	}
	public void setHibernate(Hibernate hibernate) {
		this.hibernate = hibernate;
	}
	
	
}
