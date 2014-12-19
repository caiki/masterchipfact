package com.test.morphia.server;

import java.util.List;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.test.morphia.client.GretingService;
import com.test.morphia.dao.ClienteDAO;
import com.test.morphia.dao.EmployeeDAO;
import com.test.morphia.model.Cliente;
import com.test.morphia.model.Employee;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GretingServiceImpl extends RemoteServiceServlet implements GretingService {

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
