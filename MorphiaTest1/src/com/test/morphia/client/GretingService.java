package com.test.morphia.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.test.morphia.model.Cliente;
import com.test.morphia.model.Employee;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GretingService extends RemoteService {
  List<Employee> greetServerEmployee(String input);

  void greet2Employee(Employee e);
  
  List<Cliente> greetServerCliente(String input);

  void greet2Cliente(Cliente e);
}
