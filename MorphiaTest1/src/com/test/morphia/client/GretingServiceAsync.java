package com.test.morphia.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.test.morphia.model.Cliente;
import com.test.morphia.model.Employee;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GretingServiceAsync {
  void greetServerEmployee(String input, AsyncCallback<List<Employee>> asyncCallback);

  void greet2Employee(Employee e, AsyncCallback<Void> callback);

  void greetServerCliente(String input, AsyncCallback<List<Cliente>> asyncCallback);

  void greet2Cliente(Cliente e, AsyncCallback<Void> callback);
  
  //svoid listCliente(DataSource l);
}
