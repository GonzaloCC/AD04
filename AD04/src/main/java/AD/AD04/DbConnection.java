package AD.AD04;

public class DbConnection {
	String address;
	String port;
	String name;
	String user;
	String password;
	
	
	public DbConnection() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DbConnection(String address, String port, String name, String user, String password) {
		super();
		this.address = address;
		this.port = port;
		this.name = name;
		this.user = user;
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

	
}
