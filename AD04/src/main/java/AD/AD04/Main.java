package AD.AD04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.gson.Gson;



public class Main {

	public static void main(String[] args) {
			
		Compania compania=new Compania();
		Transaction tran = null;
		
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

        try{
            //Collemos a sesión de Hibernate
            Session session = HibernateUtil.getSessionFactory().openSession();
            //Comenzamos unha transacción
            tran = session.beginTransaction();
            
            //Gardamos o equipo
            session.save(provincias);
            
            //Facemos un commit da transacción
            tran.commit();
            session.close();
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
				Session session = HibernateUtil.getSessionFactory().openSession();
				//Facemos unha consulta en HQL
				Query hql1 = session.createQuery("SELECT e FROM Provincia e");
				List<Provincia> provincias1 = hql1.list();
				for(Provincia p:provincias1){
				System.out.print(p.getId()+"-"+p.getNome());
				System.out.println();
				}
				int Idprovincia=entrada.nextInt();
				String nsa=entrada.nextLine();
				
	            

				//Collemos a sesión de Hibernate
	//			Session session = HibernateUtil.getSessionFactory().openSession();
				//Consulta con só un resultado
				Query q = session.createQuery("SELECT x FROM Provincia x WHERE x.id=:n");
				q.setParameter("n", Idprovincia);
				Provincia provinciaAux = (Provincia) q.uniqueResult();
				
				Tenda tenda= new Tenda(nome, cidade,provinciaAux);
				compania.addTenda(tenda);
				Transaction tran1 = null;
				try{
		            //Collemos a sesión de Hibernate
		//           Session session3 = HibernateUtil.getSessionFactory().openSession();
		            //Comenzamos unha transacción
		            tran1 = session.beginTransaction();
		            
		            //Gardamos o equipo
		            session.save(compania);
		            
		            //Facemos un commit da transacción
		            tran1.commit();
		            session.close();
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
				int idTenda=entrada.nextInt();
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
				Producto producto=new Producto(identificador,descripcion);
				compania.getProductos().add(producto);
				Transaction tran4 = null;
				try{
				//Collemos a sesión de Hibernate
				Session session4 = HibernateUtil.getSessionFactory().openSession();
				//Comenzamos unha transacción
				tran4 = session4.beginTransaction();
				//Gardamos o equipo
				session4.save(compania);
				//Facemos un commit da transacción
				tran4.commit();
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
				Query q66 = session6.createQuery("SELECT x FROM Tenda x WHERE x.id=:n");
				q6.setParameter("n", IdTenda6);
				Tenda tendaAux6 = (Tenda) q66.uniqueResult();
				for(Producto p:tendaAux6.getProductos()) {
					System.out.println(p.getDescripcion());
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
				int IdTenda7=entrada.nextInt();
				String nsa7=entrada.nextLine();
			   //Consulta con só un resultado
				Query q77 = session7.createQuery("SELECT x FROM Tenda x WHERE x.id=:n");
				q77.setParameter("n", IdTenda7);
				Tenda tendaAux7 = (Tenda) q77.uniqueResult();
				
			//mostramos os productos da franquicia
				//Facemos unha consulta
				Query q7777 = session7.createQuery("SELECT x FROM Producto x");
				List<Producto> productos7 = q7777.list();
				for(Producto p:productos7){
				System.out.println(p.getId()+"-"+p.getDescripcion());
				}
				 System.out.println("Escriba o Id do producto");
				 
				 
				 int IdProducto7=entrada.nextInt();
				String nsa777=entrada.nextLine();
				  //Consulta con só un resultado
				Query q777 = session7.createQuery("SELECT x FROM Producto x WHERE x.id=:n");
				q777.setParameter("n", IdProducto7);
				 Producto productoAux7 = (Producto) q777.uniqueResult();
				 System.out.println("Cantidade");
				 int cantidade=entrada.nextInt();
				 System.out.println("Prezo");
				 double prezo=entrada.nextDouble();
				 ProductoTenda pt=new ProductoTenda(productoAux7,tendaAux7,cantidade,prezo);
				 compania.getProductotenda().add(pt);
				 tendaAux7.addProducto(productoAux7);
				 productoAux7.addTenda(tendaAux7);


				 Transaction tran7 = null;
				 try{

				 //Comenzamos unha transacción
				 tran7 = session7.beginTransaction();
				 //Gardamos o equipo
				 session7.save(compania);
				 //Facemos un commit da transacción
				 tran7.commit();
				 }catch(HibernateException e){
				 e.printStackTrace();
				 }
				 session7.close();
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
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
				 session12.save(compania);
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
				Empregado empregado=new Empregado(nomeEmpregado,apelidoprimeiroEmpregado,apelidosegundoEmpregado);
				compania.getEmpregados().add(empregado);
				Session session15 = HibernateUtil.getSessionFactory().openSession();
				 Transaction tran15 = null;
				 try{

				 //Comenzamos unha transacción
				 tran15 = session15.beginTransaction();
				 //Gardamos o equipo
				 session15.save(compania);
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
