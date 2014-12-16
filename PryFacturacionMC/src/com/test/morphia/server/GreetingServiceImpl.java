package com.test.morphia.server;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.test.morphia.client.GreetingService;
import com.test.morphia.dao.*;
import com.test.morphia.model.*;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

 

	  @SuppressWarnings("deprecation")
	  public List<Employee> greetServerEmployee(String input) throws IllegalArgumentException {

	    EmployeeDAO dao = EmployeeDAO.getEmployeeDAO();
	    QueryResults<Employee> find = dao.find();
	    List<Employee> asList2 = find.asList();
	    return asList2;
	  }

	  @Override
	  public void greet2Employee(Employee e) {
	    EmployeeDAO dao = EmployeeDAO.getEmployeeDAO();
	    Query<Employee> q = dao.getDatastore().createQuery(Employee.class).field("id").equal(e.id);
	    QueryResults<Employee> find = dao.find(q);
	    System.out.println(find.asList().get(0).toString());
	  }


	  
	  //-------------------------Cliente---------------------------
	  @SuppressWarnings("deprecation")
	  public List<Cliente> greetServerCliente(String input) throws IllegalArgumentException {

		ClienteDAO dao = ClienteDAO.getClienteDAO();
	    QueryResults<Cliente> find = dao.find();
	    List<Cliente> asList2 = find.asList();
	    return asList2;
	  }

	  @Override
	  public void greet2Cliente(Cliente e) {
		  ClienteDAO dao = ClienteDAO.getClienteDAO();
	    Query<Cliente> q = dao.getDatastore().createQuery(Cliente.class).field("id").equal(e.id);
	    QueryResults<Cliente> find = dao.find(q);
	    System.out.println(find.asList().get(0).toString());
	  }
	  
	  	@Override
		public void setCliente(Cliente cliente) {
			try {
				DB dataBase = MongoFactory.getDataBase("facturacion");
				DBCollection userCollection = MongoFactory.getCollection(dataBase, "Cliente");
				BasicDBObject document = new BasicDBObject();
				document.put("_id", cliente.getId());
				document.put("correo", cliente.getCorreo());
				document.put("direccion",cliente.getDireccion());
				document.put("razonSocial",cliente.getRazonSocial());
				document.put("ciudad", cliente.getCiudad());
				document.put("telefono",cliente.getTelefono());
				// insert into database
				userCollection.insert(document);
				// commit into database
				userCollection.save(document);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
		}
	  	
	  	@Override
		public void eliminarCliente(Cliente cliente) {
			try {
				DB dataBase = MongoFactory.getDataBase("facturacion");
				DBCollection userCollection = MongoFactory.getCollection(dataBase,"Cliente");
				BasicDBObject document = new BasicDBObject();
				document.put("_id", cliente.getId());
				document.put("correo", cliente.getCorreo());
				document.put("direccion",cliente.getDireccion());
				document.put("razonSocial",cliente.getRazonSocial());
				document.put("ciudad", cliente.getCiudad());
				document.put("telefono",cliente.getTelefono());
				//document.removeField("_id");
				// insert into database				
				userCollection.findAndRemove(document);
				// commit into database
				//userCollection.dropIndex(document);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
		}
	  	
	  	@Override
		public void actualizarCliente(Cliente clienteAntiguo,Cliente clienteNuevo) {
			try {
				DB dataBase = MongoFactory.getDataBase("facturacion");
				DBCollection userCollection = MongoFactory.getCollection(dataBase,"Cliente");
				BasicDBObject documentA = new BasicDBObject();
				documentA.put("_id", clienteAntiguo.getId());
				documentA.put("correo", clienteAntiguo.getCorreo());
				documentA.put("direccion",clienteAntiguo.getDireccion());
				documentA.put("razonSocial",clienteAntiguo.getRazonSocial());
				documentA.put("ciudad", clienteAntiguo.getCiudad());
				documentA.put("telefono",clienteAntiguo.getTelefono());
				
				BasicDBObject documentN = new BasicDBObject();
				documentN.put("_id", clienteNuevo.getId());
				documentN.put("correo", clienteNuevo.getCorreo());
				documentN.put("direccion",clienteNuevo.getDireccion());
				documentN.put("razonSocial",clienteNuevo.getRazonSocial());
				documentN.put("ciudad", clienteNuevo.getCiudad());
				documentN.put("telefono",clienteNuevo.getTelefono());
				
				//document.removeField("_id");
				// insert into database				
				userCollection.findAndModify(documentA,documentN);
				// commit into database
				//userCollection.dropIndex(document);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
		}
	  //-------------------------Articulo---------------------------
	  @SuppressWarnings("deprecation")
	  public List<Articulo> greetServerArticulo(String input) throws IllegalArgumentException {

		ArticuloDAO dao = ArticuloDAO.getArticuloDAO();
	    QueryResults<Articulo> find = dao.find();
	    List<Articulo> asList2 = find.asList();
	    return asList2;
	  }

	  @Override
	  public void greet2Articulo(Articulo e) {
		  ArticuloDAO dao = ArticuloDAO.getArticuloDAO();
	    Query<Articulo> q = dao.getDatastore().createQuery(Articulo.class).field("id").equal(e.id);
	    QueryResults<Articulo> find = dao.find(q);
	    System.out.println(find.asList().get(0).toString());}
	  
	  @Override
		public void setArticulo(Articulo articulo) {
			try {
				DB dataBase = MongoFactory.getDataBase("facturacion");
				DBCollection userCollection = MongoFactory.getCollection(dataBase, "Articulo");
				BasicDBObject document = new BasicDBObject();
				document.put("_id", articulo.getId());
				document.put("familia", articulo.getFamilia());
				document.put("nombre",articulo.getNombre());
				document.put("marca",articulo.getMarca());
				document.put("precioCompra", articulo.getPrecioCompra());
				document.put("precioVenta",articulo.getPrecioVenta());
				document.put("presentacion",articulo.getPresentacion());
				document.put("tipoArticulo",articulo.getTipoArticulo());
				// insert into database
				userCollection.insert(document);
				// commit into database
				userCollection.save(document);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
		}
	  	
	  	@Override
		public void eliminarArticulo(Articulo articulo) {
			try {
				DB dataBase = MongoFactory.getDataBase("facturacion");
				DBCollection userCollection = MongoFactory.getCollection(dataBase,"Articulo");
				BasicDBObject document = new BasicDBObject();
				document.put("_id", articulo.getId());
				document.put("familia", articulo.getFamilia());
				document.put("nombre",articulo.getNombre());
				document.put("marca",articulo.getMarca());
				document.put("precioCompra", articulo.getPrecioCompra());
				document.put("precioVenta",articulo.getPrecioVenta());
				document.put("presentacion",articulo.getPresentacion());
				document.put("tipoArticulo",articulo.getTipoArticulo());
				
				//document.removeField("_id");
				// insert into database				
				userCollection.findAndRemove(document);
				// commit into database
				//userCollection.dropIndex(document);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
		}
	  //-------------------------Distribucion---------------------------
	    @SuppressWarnings("deprecation")
	    public List<Distribucion> greetServerDistribucion(String input) throws IllegalArgumentException {

	    	DistribucionDAO dao = DistribucionDAO.getDistribucionDAO();
	      QueryResults<Distribucion> find = dao.find();
	      List<Distribucion> asList2 = find.asList();
	      return asList2;
	    }

	    @Override
	    public void greet2Distribucion(Distribucion e) {
	    	DistribucionDAO dao = DistribucionDAO.getDistribucionDAO();
	      Query<Distribucion> q = dao.getDatastore().createQuery(Distribucion.class).field("id").equal(e.id);
	      QueryResults<Distribucion> find = dao.find(q);
	      System.out.println(find.asList().get(0).toString());}
	   //-------------------------Proveedor---------------------------
	      @SuppressWarnings("deprecation")
	      public List<Proveedor> greetServerProveedor(String input) throws IllegalArgumentException {

	    	  ProveedorDAO dao = ProveedorDAO.getProveedorDAO();
	        QueryResults<Proveedor> find = dao.find();
	        List<Proveedor> asList2 = find.asList();
	        return asList2;
	      }

	      @Override
	      public void greet2Proveedor(Proveedor e) {
	    	  ProveedorDAO dao = ProveedorDAO.getProveedorDAO();
	        Query<Proveedor> q = dao.getDatastore().createQuery(Proveedor.class).field("id").equal(e.id);
	        QueryResults<Proveedor> find = dao.find(q);
	        System.out.println(find.asList().get(0).toString());}
	      
	      @Override
	      public void setProveedor(Proveedor proveedor) {
				try {
					DB dataBase = MongoFactory.getDataBase("facturacion");
					DBCollection userCollection = MongoFactory.getCollection(dataBase, "Proveedor");
					BasicDBObject document = new BasicDBObject();
					document.put("_id", proveedor.getId());
					document.put("razonSocial",proveedor.getRazonSocial());
					document.put("direccion",proveedor.getDireccion());
					document.put("ruc",proveedor.getRuc());
					document.put("ciudad",proveedor.getCiudad());
					// insert into database
					userCollection.insert(document);
					// commit into database
					userCollection.save(document);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (MongoException e) {
					e.printStackTrace();
				}
			}
		  	@Override
			public void eliminarProveedor(Proveedor proveedor) {
				try {
					DB dataBase = MongoFactory.getDataBase("facturacion");
					DBCollection userCollection = MongoFactory.getCollection(dataBase,"Proveedor");
					BasicDBObject document = new BasicDBObject();
					document.put("_id", proveedor.getId());
					document.put("razonSocial", proveedor.getRazonSocial());
					document.put("direccion",proveedor.getDireccion());
					document.put("ruc",proveedor.getRuc());
					document.put("ciudad", proveedor.getCiudad());
						
					userCollection.findAndRemove(document);
					
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (MongoException e) {
					e.printStackTrace();
				}
			}
	  //-------------------------Sucursal---------------------------
	        @SuppressWarnings("deprecation")
	        public List<Sucursal> greetServerSucursal(String input) throws IllegalArgumentException {

	        	SucursalDAO dao = SucursalDAO.getSucursalDAO();
	          QueryResults<Sucursal> find = dao.find();
	          List<Sucursal> asList2 = find.asList();
	          return asList2;
	        }

	        @Override
	        public void greet2Sucursal(Sucursal e) {
	        	SucursalDAO dao = SucursalDAO.getSucursalDAO();
	          Query<Sucursal> q = dao.getDatastore().createQuery(Sucursal.class).field("id").equal(e.id);
	          QueryResults<Sucursal> find = dao.find(q);
	          System.out.println(find.asList().get(0).toString());}
	  
	        @Override
			public void setSucursal(Sucursal sucursal) {
				try {
					DB dataBase = MongoFactory.getDataBase("facturacion");
					DBCollection userCollection = MongoFactory.getCollection(dataBase, "Sucursal");
					BasicDBObject document = new BasicDBObject();
					document.put("_id", sucursal.getId());
					document.put("direccion",sucursal.getDireccion());
					document.put("telefono",sucursal.getTelefono());
					// insert into database
					userCollection.insert(document);
					// commit into database
					userCollection.save(document);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (MongoException e) {
					e.printStackTrace();
				}
			}
		  	@Override
			public void eliminarSucursal(Sucursal sucursal) {
				try {
					DB dataBase = MongoFactory.getDataBase("facturacion");
					DBCollection userCollection = MongoFactory.getCollection(dataBase,"Sucursal");
					BasicDBObject document = new BasicDBObject();
					document.put("_id", sucursal.getId());
					document.put("direccion",sucursal.getDireccion());
					document.put("telefono",sucursal.getTelefono());
					//Corregir ya que este es un 
					document.put("distribucion",sucursal.getDistribucion());
					//document.removeField("_id");
					// insert into database				
					userCollection.findAndRemove(document);
					// commit into database
					//userCollection.dropIndex(document);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (MongoException e) {
					e.printStackTrace();
				}
			}
	   //-------------------------Usuario---------------------------
	          @SuppressWarnings("deprecation")
	          public List<Usuario> greetServerUsuario(String input) throws IllegalArgumentException {

	        	  UsuarioDAO dao = UsuarioDAO.getUsuarioDAO();
	            QueryResults<Usuario> find = dao.find();
	            List<Usuario> asList2 = find.asList();
	            return asList2;
	          }

	          @Override
	          public void greet2Usuario(Usuario e) {
	        	  UsuarioDAO dao = UsuarioDAO.getUsuarioDAO();
	            Query<Usuario> q = dao.getDatastore().createQuery(Usuario.class).field("id").equal(e.id);
	            QueryResults<Usuario> find = dao.find(q);
	            System.out.println(find.asList().get(0).toString());}
	  
	          @Override
	  		public void setUsuario(Usuario usuario) {
	  			try {
	  				DB dataBase = MongoFactory.getDataBase("facturacion");
	  				DBCollection userCollection = MongoFactory.getCollection(dataBase, "Usuario");
	  				BasicDBObject document = new BasicDBObject();
	  				document.put("_id", usuario.getId());
	  				document.put("codGrupo", usuario.getCodGrupo());
	  				document.put("cargo",usuario.getCargo());
	  				document.put("apellidos",usuario.getApellidos());
	  				document.put("nombres", usuario.getNombres());
	  				document.put("password",usuario.getPassword());
	  				document.put("nick",usuario.getNick());
	  				document.put("telefono",usuario.getTelefono());
	  				document.put("direccion",usuario.getDireccion());
	  				document.put("correo",usuario.getCorreo());
	  				document.put("DNI",usuario.getDNI());
	  				// insert into database
	  				userCollection.insert(document);
	  				// commit into database
	  				userCollection.save(document);
	  			} catch (UnknownHostException e) {
	  				e.printStackTrace();
	  			} catch (MongoException e) {
	  				e.printStackTrace();
	  			}
	  		}
	  	  	
	  	  	@Override
	  		public void eliminarUsuario(Usuario usuario) {
	  			try {
	  				DB dataBase = MongoFactory.getDataBase("facturacion");
	  				DBCollection userCollection = MongoFactory.getCollection(dataBase,"Usuario");
	  				BasicDBObject document = new BasicDBObject();
	  				document.put("_id", usuario.getId());
	  				document.put("codGrupo", usuario.getCodGrupo());
	  				document.put("cargo",usuario.getCargo());
	  				document.put("apellidos",usuario.getApellidos());
	  				document.put("nombres", usuario.getNombres());
	  				document.put("password",usuario.getPassword());
	  				document.put("nick",usuario.getNick());
	  				document.put("telefono",usuario.getTelefono());
	  				document.put("direccion",usuario.getDireccion());
	  				document.put("correo",usuario.getCorreo());
	  				document.put("DNI",usuario.getDNI());
	  				//document.removeField("_id");
	  				// insert into database				
	  				userCollection.findAndRemove(document);
	  				// commit into database
	  				//userCollection.dropIndex(document);
	  			} catch (UnknownHostException e) {
	  				e.printStackTrace();
	  			} catch (MongoException e) {
	  				e.printStackTrace();
	  			}
	  		}	  
  /**
   * Escape an html string. Escaping data received from the client helps to prevent cross-site
   * script vulnerabilities.
   * 
   * @param html
   *          the html string to escape
   * @return the escaped string
   */
  // private String escapeHtml(String html) {
  // if (html == null) {
  // return null;
  // }
  // return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
  // .replaceAll(">", "&gt;");
  // }
}