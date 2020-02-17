package AD.AD04;

public class Hibernate {
	String driver;
	String dialect;
	String HBM2DDL_AUTO;
	String SHOW_SQL;
	
	
	public Hibernate() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Hibernate(String driver, String dialect, String hBM2DDL_AUTO, String sHOW_SQL) {
		super();
		this.driver = driver;
		this.dialect = dialect;
		HBM2DDL_AUTO = hBM2DDL_AUTO;
		SHOW_SQL = sHOW_SQL;
	}


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public String getDialect() {
		return dialect;
	}


	public void setDialect(String dialect) {
		this.dialect = dialect;
	}


	public String getHBM2DDL_AUTO() {
		return HBM2DDL_AUTO;
	}


	public void setHBM2DDL_AUTO(String hBM2DDL_AUTO) {
		HBM2DDL_AUTO = hBM2DDL_AUTO;
	}


	public String getSHOW_SQL() {
		return SHOW_SQL;
	}


	public void setSHOW_SQL(String sHOW_SQL) {
		SHOW_SQL = sHOW_SQL;
	}
	
	

}
