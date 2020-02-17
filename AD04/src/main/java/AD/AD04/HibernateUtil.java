package AD.AD04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import com.google.gson.Gson;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	//Este método devolve a sesión para poder facer operacións coa base de
	//datos
	public static SessionFactory getSessionFactory() {
		Configuracion configuracion=new Configuracion();
		File arquivoConf = new File("config.json");
		 try {	
		        //Creamos un fluxo de entrada para o arquivo
		        FileReader fluxoDatos;
		        fluxoDatos = new FileReader(arquivoConf);

		        //Creamos o bufer de entrada
		        BufferedReader buferEntrada = new BufferedReader(fluxoDatos);

		        //Imos lendo linea a linea
		        StringBuilder jsonBuilder = new StringBuilder();
		        String linea;
		        while ((linea=buferEntrada.readLine()) != null) {
		            jsonBuilder.append(linea).append("\n");
		        }

		        //Temos que cerrar sempre o ficheiro
		        buferEntrada.close();

		        //Construimos o String con todalas lineas lidas
		        String json = jsonBuilder.toString();

		        //Pasamos o json a clase ca cal se corresponde
		        Gson gson = new Gson();
		         configuracion = gson.fromJson(json,Configuracion.class);
		     } catch (FileNotFoundException e) {
		         System.out.println("Non se encontra o arquivo");
		     } catch (IOException e) {
		         System.out.println("Erro de entrada saída");
		     }
	//Se a sesion non se configurou, creamolo
	if(sessionFactory == null){
	try{
	Configuration conf = new Configuration();
	//Engadimos as propiedades
	Properties settings = new Properties();
	//Indicamos o conector da base de datos que vamos a usar
	settings.put(Environment.DRIVER,configuracion.getHibernate().getDriver());
	//Indicamos a localización da base de datos que vamos a
	//utilizar
	settings.put(Environment.URL,"jdbc:mysql://"+configuracion.getDbConnection().getAddress()+":"+configuracion.getDbConnection().getPort()+"/"+configuracion.getDbConnection().getName());
	//Indicamos o usuario da base de datos con cal nos vamos
	//conectar e o seu contrasinal
	settings.put(Environment.USER,configuracion.getDbConnection().getUser());
	settings.put(Environment.PASS,configuracion.getDbConnection().getPassword());
	//Indicamos o dialecto que ten que usar Hibernate
	settings.put(Environment.DIALECT,configuracion.getHibernate().getDialect());
	//Indicamos que se as táboas todas se borren e se volvan crear
	settings.put(Environment.HBM2DDL_AUTO, "update");
	//Indicamos que se mostre as operacións SQL que Hibernate leva
	//a cabo
	settings.put(Environment.SHOW_SQL,configuracion.getHibernate( ).getSHOW_SQL());
	conf.setProperties(settings);
	//Engaidmos aquelas clases nas que queremos facer persistencia
	conf.addAnnotatedClass(Compania.class);
	conf.addAnnotatedClass(Tenda.class);
	conf.addAnnotatedClass(Cliente.class);
	conf.addAnnotatedClass(Empregado.class);
	conf.addAnnotatedClass(Producto.class);
	conf.addAnnotatedClass(Provincia.class);
	conf.addAnnotatedClass(Provincias.class);
	conf.addAnnotatedClass(EmpregadoTenda.class);
	conf.addAnnotatedClass(ProductoTenda.class);
	ServiceRegistry serviceRegistry = new
	StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
	sessionFactory = conf.buildSessionFactory(serviceRegistry);
	}catch(HibernateException e){
	e.printStackTrace();
	}
	}
	return sessionFactory;
	}

}
