package com.test.morphia.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.test.morphia.model.*;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	 //-------------------------Empleado---------------------------
	  List<Employee> greetServerEmployee(String input);

	  void greet2Employee(Employee e);
	  
	  //-------------------------Cliente---------------------------
	  List<Cliente> greetServerCliente(String input);

	  void greet2Cliente(Cliente e);
	  
	  void setCliente(Cliente e);
	  
	  void eliminarCliente(Cliente e);
	  
	  void actualizarCliente(Cliente clienteAntiguo, Cliente cliente);

	  
	 //-------------------------Articulo---------------------------
	  List<Articulo> greetServerArticulo(String input);

	  void greet2Articulo(Articulo e);
	  //-------------------------Distribucion---------------------------
	  List<Distribucion> greetServerDistribucion(String input);

	  void greet2Distribucion(Distribucion e);
	  //-------------------------Proveedor---------------------------
	  List<Proveedor> greetServerProveedor(String input);

	  void greet2Proveedor(Proveedor e);
	  
	  void setProveedor(Proveedor e);
	  
	  void eliminarProveedor(Proveedor e);
	  //-------------------------Sucursal---------------------------
	  List<Sucursal> greetServerSucursal(String input);

	  void greet2Sucursal(Sucursal e);
	  
	  void setSucursal(Sucursal e);
	  
	  void eliminarSucursal(Sucursal e);
	  //-------------------------Usuario---------------------------
	  List<Usuario> greetServerUsuario(String input);

	  void greet2Usuario(Usuario e);
	  
	  void setUsuario(Usuario e);
	  
	  void eliminarUsuario(Usuario e);
}