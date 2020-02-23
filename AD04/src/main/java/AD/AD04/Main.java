package AD.AD04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;




public class Main {

	public static void main(String[] args) {
		
		 Session sessionA = HibernateUtil.getSessionFactory().openSession();
		
		Compania compania = (Compania)sessionA.get(Compania.class, 1);
		if(compania==null) {
		 compania=new Compania();
		}
		compania.getClientes().size();
		compania.getEmpregados().size();
		compania.getProductos().size();
		compania.getTendas().size();
		compania.getEmpregadotenda().size();
		compania.getProductotenda().size();
		
		sessionA.close();
		System.out.println("Id compñia"+compania.getId());
		Transaction tran = null;
		 try{
	            //Collemos a sesión de Hibernate
	            Session session = HibernateUtil.getSessionFactory().openSession();
	            //Comenzamos unha transacción
	            tran = session.beginTransaction();
	            
	            //Gardamos o equipo
	            session.saveOrUpdate(compania);
	            
	            //Facemos un commit da transacción
	            tran.commit();
	            session.close();
	        }catch(HibernateException e){
	            e.printStackTrace();
	        }
		
		Provincias provincias=new Provincias();

		File arquivoProvincias = new File("provincias.json");

        try {

            //Creamos un fluxo de entrada para o arquivo
            FileReader fluxoDatos;
            fluxoDatos = new FileReader(arquivoProvincias);

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
             provincias = gson.fromJson(json,Provincias.class);
            
             /*
            //Comprobamos que se leron ben os datos
            System.out.println("Provincias:");
            for(int i=0;i<provincias.getArrayProvincias().size();i++){
                Provincia provinciaAux = provincias.getArrayProvincias().get(i);
                
                System.out.println(i+"-"+provinciaAux.getNome() + " " + provinciaAux.getId());
            }
			*/

        } catch (FileNotFoundException e) {
            System.out.println("Non se encontra o arquivo");
        } catch (IOException e) {
            System.out.println("Erro de entrada saída");
        }
        
		//////////////////////////////
        Transaction tran0 = null;
        try{
            //Collemos a sesión de Hibernate
           Session session0 = HibernateUtil.getSessionFactory().openSession();
            //Comenzamos unha transacción
           tran0 = session0.beginTransaction();
            
            //Gardamos o equipo
            session0.save(provincias);
            
            //Facemos un commit da transacción
            tran0.commit();
            session0.close();
        }catch(HibernateException e){
            e.printStackTrace();
        }
		
		
		/////////////////////////////
		
		
		Scanner entrada= new Scanner(System.in);
		int eleccion;
		
		do {

			System.out.println("1-Engadir unha nova tenda.\r\n" + 
					"2-Mostrar as tendas.\r\n" + 
					"3-Eliminar unha tenda.\r\n" + 
					"4-Engadir un producto.\r\n" + 
					"5-Mostrar os productos da franquicia.\r\n" + 
					"6-Mostrar os productos dunha tenda.\r\n" + 
					"7-Engadir un producto a unha tenda.\r\n" + 
					"8-Actualizar o stock dun producto nunha determinada tenda.\r\n" + 
					"9-Mostrar o stock dun producto dunha tenda.\r\n" + 
					"10-Eliminar un producto dunha determinada tenda.\r\n" + 
					"11-Eliminar un producto.\r\n" + 
					"12-Engadir un cliente.\r\n" + 
					"13-Mostrar os clientes\r\n" + 
					"14-Eliminar un cliente.\r\n" + 
					"15-Engadir un empregado.\r\n" +
					"16-Mostrar os empregados da franquicia.\r\n" +
					"17-Engadir un empregado a unha tenda.\r\n" +
					"18-Mostrar os empregado dunha tenda\r\n" +
					"19-Eliminar un empregadoo dunha tenda\r\n" +
					"20-Eliminar un empregado da franquicia\r\n" +
					"21-Xerar un informe json co stock de todos os productos en formato JSON\r\n" +
					"22-Ler os titulares do periódico El Pais. (Explícase máis abaixo)\r\n" + 
					"23-Sair da aplicación");
			
			eleccion= entrada.nextInt();
			String nada= entrada.nextLine();
			
			switch(eleccion) {
			case 1:
				String nome;
				String cidade;
				System.out.println("Nome da tenda");
				nome= entrada.nextLine();
				System.out.println("Cidade da tenda");
				cidade= entrada.nextLine();
				
				//Collemos a sesión de Hibernate
				Session session1 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta en HQL
				Query hql1 = session1.createQuery("SELECT e FROM Provincia e");
				List<Provincia> provincias1 = hql1.list();
				for(Provincia p:provincias1){
				System.out.print(p.getId()+"-"+p.getNome());
				System.out.println();
				}
				int Idprovincia=entrada.nextInt();
				String nsa=entrada.nextLine();
				
	            

				//Collemos a sesión de Hibernate
				//Session session = HibernateUtil.getSessionFactory().openSession();
				//Consulta con só un resultado
				Query q = session1.createQuery("SELECT x FROM Provincia x WHERE x.id=:n");
				q.setParameter("n", Idprovincia);
				Provincia provinciaAux = (Provincia) q.uniqueResult();
				
				Tenda tenda= new Tenda(nome, cidade,provinciaAux,compania);
				compania.addTenda(tenda);
				Transaction tran1 = null;
				try{
		            //Collemos a sesión de Hibernate
		//           Session session3 = HibernateUtil.getSessionFactory().openSession();
		            //Comenzamos unha transacción
		            tran1 = session1.beginTransaction();
		            
		            //Gardamos o equipo
		            session1.saveOrUpdate(tenda);
		            
		            //Facemos un commit da transacción
		            tran1.commit();
		            session1.close();
		        }catch(HibernateException e){
		            e.printStackTrace();
		        }
				
				break;
			case 2:
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q2 = session2.createQuery("SELECT x FROM Tenda x");
				List<Tenda> tendas2 = q2.list();
				for(Tenda t:tendas2){
				System.out.println(t.getNome());
				}
				
				session2.close();
				break;
			case 3:
				Session session3 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q3 = session3.createQuery("SELECT x FROM Tenda x");
				List<Tenda> tendas3 = q3.list();
				for(Tenda t:tendas3){
				System.out.println(t.getId()+"-"+t.getNome());
				}
				System.out.println("Escriba o Id da tenda que desexa eliminar");
				long idTenda=entrada.nextInt();
				String nsa3=entrada.nextLine();

			
				Transaction tran3 = null;
				try{
				//Comenzamos unha transacción
				tran3 = session3.beginTransaction();
				Tenda tendaAux3 = (Tenda)session3.get(Tenda.class, idTenda); 
				session3.delete(tendaAux3);
				tran3.commit();
				}catch(HibernateException e){
				e.printStackTrace();
				}
				session3.close();

				break;
			case 4:
				String identificador;
				String descripcion;
				System.out.println("Identificador do producto");
				identificador=entrada.nextLine();
				System.out.println("Descripcion do producto");
				descripcion=entrada.nextLine();
				Producto producto=new Producto(identificador,descripcion,compania);
				compania.getProductos().add(producto);
				Transaction tran4 = null;
				try{
				//Collemos a sesión de Hibernate
				Session session4 = HibernateUtil.getSessionFactory().openSession();
				//Comenzamos unha transacción
				tran4 = session4.beginTransaction();
				//Gardamos o equipo
				session4.saveOrUpdate(producto);
				//Facemos un commit da transacción
				tran4.commit()
				;
				session4.close();
				}catch(HibernateException e){
				e.printStackTrace();
				}
				
				break;
			case 5:
				Session session5 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q5 = session5.createQuery("SELECT x FROM Producto x");
				List<Producto> productos5 = q5.list();
				for(Producto p:productos5){
				System.out.println(p.getId()+"-"+p.getDescripcion());
				}
				session5.close();
				break;

			case 6:
				Session session6 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q6 = session6.createQuery("SELECT x FROM Tenda x");
				List<Tenda> tendas6 = q6.list();
				for(Tenda t:tendas6){
				System.out.println(t.getId()+"-"+t.getNome());
				}
				System.out.println("Escolla unha tenda");
				int IdTenda6=entrada.nextInt();
				String nsa6=entrada.nextLine();
			   //Consulta con só un resultado
				Query q66 = session6.createQuery("SELECT x.producto FROM ProductoTenda x where idTenda=:n");
				q66.setParameter("n", IdTenda6);
				List<Producto> productos6 = q66.list();
				for(Producto p:productos6){
				System.out.println(p.getId()+"-"+p.getDescripcion());
				}

	
				session6.close();
				
				break;
			case 7:
				Session session7 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q7 = session7.createQuery("SELECT x FROM Tenda x");
				List<Tenda> tendas7 = q7.list();
				for(Tenda t:tendas7){
				System.out.println(t.getId()+"-"+t.getNome());
				}
				System.out.println("Escriba o Id da tenda ");
				long idTenda7=entrada.nextLong();
				String nsa7=entrada.nextLine();
			   //Consulta con só un resultado
				Tenda tendaAux7 = (Tenda)session7.get(Tenda.class, idTenda7);
				System.out.println(tendaAux7.getNome());
				
			//mostramos os productos da franquicia
				//Facemos unha consulta
				Query q7777 = session7.createQuery("SELECT x FROM Producto x");
				List<Producto> productos7 = q7777.list();
				for(Producto p:productos7){
				System.out.println(p.getId()+"-"+p.getDescripcion());
				}
				 System.out.println("Escriba o Id do producto");
				 
				 
				 long idProducto7=entrada.nextLong();
				String nsa777=entrada.nextLine();
				Producto productoAux7 = (Producto)session7.get(Producto.class, idProducto7);
				 System.out.println(productoAux7.getDescripcion());
				 System.out.println("Cantidade");
				 int cantidade=entrada.nextInt();
				 System.out.println("Prezo");
				 double prezo=entrada.nextDouble();
				 ProductoTenda pt=new ProductoTenda(productoAux7,tendaAux7,cantidade,prezo,compania);
				 compania.getProductotenda().add(pt);
				 tendaAux7.addProducto(pt);
				 productoAux7.addTenda(pt);


				 Transaction tran7 = null;
				 try{

				 //Comenzamos unha transacción
				 tran7 = session7.beginTransaction();
				 //Gardamos o equipo
				 session7.saveOrUpdate(pt);
				 //Facemos un commit da transacción
				 tran7.commit();
				 }catch(HibernateException e){
				 e.printStackTrace();
				 }
				 session7.close();
				break;
			case 8:
				Set<Tenda> tendasAux=compania.getTendas();
				for(Tenda t:tendasAux) {
					System.out.println(t.getId()+"-"+t.getNome());
				}
				System.out.println("Elixe o id dunda tenda");
				break;
			case 9:
				Session session9 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q9 = session9.createQuery("SELECT x FROM Tenda x");
				List<Tenda> tendas9 = q9.list();
				for(Tenda t:tendas9){
				System.out.println(t.getId()+"-"+t.getNome());
				}
				System.out.println("Escriba o Id da tenda ");
				long idTenda9=entrada.nextLong();
				String nsa9=entrada.nextLine();
				Tenda tendaAux9 = (Tenda)session9.get(Tenda.class, idTenda9);
				
				//mostramos os productos da tenda
				//Facemos unha consulta
				
				Query q99 = session9.createQuery("SELECT x.producto FROM ProductoTenda x where idTenda=:n");
				q99.setParameter("n", idTenda9);
				List<Producto> productos9 = q99.list();
				for(Producto p:productos9){
				System.out.println(p.getId()+"-"+p.getDescripcion());
				}
				 System.out.println("Escriba o Id do producto");
		 
				 long idProducto9=entrada.nextLong();
				String nsa99=entrada.nextLine();
				Producto productoAux9 = (Producto)session9.get(Producto.class, idProducto9);
				 System.out.println(productoAux9.getDescripcion());
				 
				 Query q999 = session9.createQuery("select distinct x " +
							"from ProductoTenda x " +
							"join x.producto p " +
							"join x.tenda t " +
							"where p.id = :ns and t.id=:n ", ProductoTenda.class );
				 q999.setParameter("ns", idProducto9);
				 q999.setParameter("n", idTenda9);
				 ProductoTenda productotendaAux = (ProductoTenda) q999.uniqueResult();
				 System.out.println("Stock: "+productotendaAux.getCantidade());
				 /*
				 List<ProductoTenda> ptA = q999.list();
				 for(ProductoTenda f:ptA) {
				 System.out.println("Stock: "+f.getCantidade());
				 }*/
				 session9.close();
				break;
			case 10:
				Session session10 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q10= session10.createQuery("SELECT x FROM Tenda x");
				List<Tenda> tendas10 = q10.list();
				for(Tenda t:tendas10){
				System.out.println(t.getId()+"-"+t.getNome());
				}
				System.out.println("Escriba o Id da tenda ");
				long idTenda10=entrada.nextLong();
				String nsa10=entrada.nextLine();
				Tenda tendaAux10 = (Tenda)session10.get(Tenda.class, idTenda10);
				
				//mostramos os productos da franquicia
				//Facemos unha consulta
				
				Query q101 = session10.createQuery("SELECT x.producto FROM ProductoTenda x where idTenda=:n");
				q101.setParameter("n", idTenda10);
				List<Producto> productos10 = q101.list();
				for(Producto p:productos10){
				System.out.println(p.getId()+"-"+p.getDescripcion());
				}
				 System.out.println("Escriba o Id do producto");
		 
				 long idProducto10=entrada.nextLong();
				String nsa101=entrada.nextLine();
				Producto productoAux10 = (Producto)session10.get(Producto.class, idProducto10);
				 System.out.println(productoAux10.getDescripcion());
				 
				 Query q111 = session10.createQuery("select distinct x " +
							"from ProductoTenda x " +
							"join x.producto p " +
							"join x.tenda t " +
							"where p.id = :ns and t.id=:n ", ProductoTenda.class );
				 q111.setParameter("ns", idProducto10);
				 q111.setParameter("n", idTenda10);
				 ProductoTenda productotendaAux10 = (ProductoTenda) q111.uniqueResult();
				
				 Transaction tran10 = null;
					try{
					//Comenzamos unha transacción
					tran10 = session10.beginTransaction();
					session10.delete(productotendaAux10);
					tran10.commit();
					}catch(HibernateException e){
					e.printStackTrace();
					}
					session10.close();
				 
				break;
			case 11:
				Session session11 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q11 = session11.createQuery("SELECT x FROM Producto x");
				List<Producto> producto11 = q11.list();
				for(Producto p:producto11){
				System.out.println(p.getId()+"-"+p.getDescripcion());
				}
				System.out.println("Escriba o Id da tenda que desexa eliminar");
				long idProducto11=entrada.nextInt();
				String nsa11=entrada.nextLine();

			
				Transaction tran11 = null;
				try{
				//Comenzamos unha transacción
				tran11 = session11.beginTransaction();
				Producto productoAux11 = (Producto)session11.get(Producto.class, idProducto11); 
				session11.delete(productoAux11);
				tran11.commit();
				}catch(HibernateException e){
				e.printStackTrace();
				}
				session11.close();

				break;
			case 12:
				String nomeCliente;
				String apelidosCliente;
				String email;
				System.out.println("Nome do cliente");
				nomeCliente=entrada.nextLine();
				System.out.println("Apelido do cliente");
				apelidosCliente=entrada.nextLine();
				System.out.println("E-mail do cliente");
				email=entrada.nextLine();
				Cliente cliente=new Cliente(nomeCliente,apelidosCliente,email);
				compania.addCliente(cliente);
				Session session12 = HibernateUtil.getSessionFactory().openSession();
				 Transaction tran12 = null;
				 try{

				 //Comenzamos unha transacción
				 tran12 = session12.beginTransaction();
				 //Gardamos o equipo
				 session12.saveOrUpdate(cliente);
				 //Facemos un commit da transacción
				 tran12.commit();
				 }catch(HibernateException e){
				 e.printStackTrace();
				 }
				 session12.close();
				break;
			case 13:
				Session session13 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q13 = session13.createQuery("SELECT x FROM Cliente x");
				List<Cliente> clientes13 = q13.list();
				for(Cliente p:clientes13){
				System.out.println(p.getId()+"-"+p.getNome()+" "+p.getApelidos());
				}
				session13.close();
				break;
			case 14:
				Session session14 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q14 = session14.createQuery("SELECT x FROM Cliente x");
				List<Cliente> clientes14 = q14.list();
				for(Cliente p:clientes14){
				System.out.println(p.getId()+"-"+p.getNome()+" "+p.getApelidos());
				}
				System.out.println("Escolla o Id dun cliente");
				int idCliente=entrada.nextInt();
				String nsa14=entrada.nextLine();
				Transaction tran14 = null;

				 try{

				 //Comenzamos unha transacción
			     Cliente clienteAux14 = (Cliente)session14.get(Cliente.class, idCliente); 
				 tran12 = session14.beginTransaction();
				 //Gardamos o equipo
				 session14.delete(clienteAux14);
				 //Facemos un commit da transacción
				 tran12.commit();
				 }catch(HibernateException e){
				 e.printStackTrace();
				 }
				break;
		
			case 15:
				System.out.println("Nome do empregado");
				String nomeEmpregado=entrada.nextLine();
				System.out.println("Apelido primeiro do empregado");
				String apelidoprimeiroEmpregado=entrada.nextLine();
				System.out.println("Apelido segundo do empregado");
				String apelidosegundoEmpregado=entrada.nextLine();
				Empregado empregado=new Empregado(nomeEmpregado,apelidoprimeiroEmpregado,apelidosegundoEmpregado,compania);
				compania.getEmpregados().add(empregado);
				Session session15 = HibernateUtil.getSessionFactory().openSession();
				 Transaction tran15 = null;
				 try{

				 //Comenzamos unha transacción
				 tran15 = session15.beginTransaction();
				 //Gardamos o equipo
				 session15.saveOrUpdate(empregado);
				 //Facemos un commit da transacción
				 tran15.commit();
				 }catch(HibernateException e){
				 e.printStackTrace();
				 }
				 session15.close();
				break;
			case 16:
				Session session16 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q16 = session16.createQuery("SELECT x FROM Empregado x");
				List<Empregado> empregado16 = q16.list();
				for(Empregado e:empregado16){
				System.out.println(e.getId()+"-"+e.getNome()+" "+e.getApelido1()+" "+e.getApelido2());
				}
				session16.close();
				break;
			case 17:
				Session session17 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q17 = session17.createQuery("SELECT x FROM Tenda x");
				List<Tenda> tendas17 = q17.list();
				for(Tenda t:tendas17){
				System.out.println(t.getId()+"-"+t.getNome());
				}
				System.out.println("Escriba o Id da tenda ");
				long idTenda17=entrada.nextLong();
				String nsa17=entrada.nextLine();
			   //Consulta con só un resultado
				Tenda tendaAux17 = (Tenda)session17.get(Tenda.class, idTenda17);
				System.out.println(tendaAux17.getNome());
				
			//mostramos os empregdos da franquicia
				//Facemos unha consulta
				Query q177 = session17.createQuery("SELECT x FROM Empregado x");
				List<Empregado> empregados17 = q177.list();
				for(Empregado e:empregados17){
				System.out.println(e.getId()+"-"+e.getNome());
				}
				 System.out.println("Escriba o Id do empleado");
				 
				 
				 long idEmpleado17=entrada.nextLong();
				String nsa177=entrada.nextLine();
				Empregado empregadoAux17 = session17.get(Empregado.class, idEmpleado17);
				 System.out.println(empregadoAux17.getNome());
				 System.out.println("Horas semanais");
				 int horas=entrada.nextInt();
				 EmpregadoTenda et=new EmpregadoTenda(empregadoAux17,tendaAux17,horas,compania);
				 compania.getEmpregadotenda().add(et);
				 tendaAux17.addEmpregado(et);
				 empregadoAux17.addTenda(et);


				 Transaction tran17 = null;
				 try{

				 //Comenzamos unha transacción
				 tran17 = session17.beginTransaction();
				 //Gardamos o equipo
				 session17.saveOrUpdate(et);
				 //Facemos un commit da transacción
				 tran17.commit();
				 }catch(HibernateException e){
				 e.printStackTrace();
				 }
				 session17.close();
				break;
			case 18:
				break;
			case 19:
				break;
			case 20:
				Session session20 = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta
				Query q20 = session20.createQuery("SELECT x FROM Empregado x");
				List<Empregado> empregado20 = q20.list();
				for(Empregado e:empregado20){
				System.out.println(e.getId()+"-"+e.getNome()+" "+e.getApelido1()+" "+e.getApelido2());
				}
				System.out.println("Escriba o Id do empregado que desexa eliminar");
				int idEmpregado=entrada.nextInt();
				String nsa4=entrada.nextLine();

				
				Transaction tran20 = null;
				try{
				//Comenzamos unha transacción
				tran20 = session20.beginTransaction();
				Empregado empregadoAux20 = (Empregado)session20.get(Empregado.class, idEmpregado); 
				session20.delete(empregadoAux20);
				tran20.commit();
				}catch(HibernateException e){
				e.printStackTrace();
				}
				session20.close();

				
				break;
			case 21:
				Gson gson = new GsonBuilder().setPrettyPrinting().create();

				Session session21 = HibernateUtil.getSessionFactory().openSession();
				Query q21= session21.createQuery("SELECT x FROM ProductoTenda x");
				 List  <ProductoTenda> productost21 = q21.list();
				 
				Type listType = new TypeToken<List<Producto>>(){}.getType();
				 
				//String json = gson.toJson(productost21,listType);
					for(ProductoTenda k:productost21) {
				File arquivoJson = new File("productostenda.json");
		        	try {
		        		//Creamos o fluxo de saida
		        		FileWriter fluxoDatos = new FileWriter(arquivoJson,true);
		        		BufferedWriter buferSaida = new BufferedWriter(fluxoDatos);

		        		buferSaida.write(k.toJSON());
		        		buferSaida.newLine();
		        		//Cerramos o arquivo
		        		buferSaida.close();
		        		} catch (IOException e) {

		        		}

				
					}
		        
				break;
			case 22:
			 XMLReader procesadorXML = null;
			 
			 try {

		            //Creamos un parseador de texto e engadimoslle a nosa clase que vai parsear o texto
		            procesadorXML = XMLReaderFactory.createXMLReader();
		            TitularesXML titularesXML = new TitularesXML();
		            procesadorXML.setContentHandler(titularesXML);

		            //Indicamos o texto donde estan gardadas as persoas
		            InputSource arquivo = new InputSource("http://ep00.epimg.net/rss/elpais/portada.xml");
		            procesadorXML.parse(arquivo);

		            //Imprimimos os datos lidos no XML
		            ArrayList<Titular> titulares = titularesXML.getTitulares();
		            
		            for(int i=0;i<titulares.size();i++){
		                Titular titularAux = titulares.get(i);
		                System.out.println("Titular "+(i+1)+": " + titularAux.getTexto() );
		            }

		        } catch (SAXException e) {
		            System.out.println("Ocurriu un erro ao ler o arquivo XML");
		        } catch (IOException e) {
		            System.out.println("Ocurriu un erro ao ler o arquivo XML");
		        }
			 	break;
			case 23:	
				
				System.exit(0);
				break;
			}
			

			
			
		}while(eleccion!=23);
		
		
		

	}

}
