package com.test.morphia.server;

import java.util.List;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.test.morphia.client.GreetingService;
import com.test.morphia.dao.ArticuloDAO;
import com.test.morphia.dao.ClienteDAO;
import com.test.morphia.dao.EmployeeDAO;
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
