package com.test.morphia.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.test.morphia.model.*;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	  void greetServerEmployee(String input, AsyncCallback<List<Employee>> asyncCallback);

	  void greet2Employee(Employee e, AsyncCallback<Void> callback);

	  
	  //svoid listCliente(DataSource l);
	  
	   //-------------------------Cliente---------------------------
	  void greetServerCliente(String input, AsyncCallback<List<Cliente>> asyncCallback);

	  void greet2Cliente(Cliente e, AsyncCallback<Void> callback);
	  
	  void setCliente(Cliente e, AsyncCallback<Void> callback);

	  void eliminarCliente(Cliente e, AsyncCallback<Void> callback);
	  
	  void actualizarCliente(Cliente clienteAntiguo, Cliente cliente,AsyncCallback<Void> asyncCallback);

	  //-------------------------Articulo---------------------------
	  void greetServerArticulo(String input, AsyncCallback<List<Articulo>> asyncCallback);

	  void greet2Articulo(Articulo e, AsyncCallback<Void> callback);
	  
	  void setArticulo(Articulo e, AsyncCallback<Void> callback);
	  
	  void eliminarArticulo(Articulo e, AsyncCallback<Void> callback);
	  
	  //-------------------------Distribucion---------------------------
	  void greetServerDistribucion(String input, AsyncCallback<List<Distribucion>> asyncCallback);

	  void greet2Distribucion(Distribucion e, AsyncCallback<Void> callback);

	   //-------------------------Proveedor---------------------------
	  void greetServerProveedor(String input, AsyncCallback<List<Proveedor>> asyncCallback);

	  void greet2Proveedor(Proveedor e, AsyncCallback<Void> callback);
	  
	  void setProveedor(Proveedor e, AsyncCallback<Void> callback);
	  
	  void eliminarProveedor(Proveedor e, AsyncCallback<Void> callback);
	  //-------------------------Sucursal---------------------------
	  void greetServerSucursal(String input, AsyncCallback<List<Sucursal>> asyncCallback);

	  void greet2Sucursal(Sucursal e, AsyncCallback<Void> callback);
	  
	  void setSucursal(Sucursal e, AsyncCallback<Void> callback);
	  
	  void eliminarSucursal(Sucursal e, AsyncCallback<Void> callback);
	  //-------------------------Usuario---------------------------
	  void greetServerUsuario(String input,AsyncCallback<List<Usuario>> asyncCallback);

	  void greet2Usuario(Usuario e, AsyncCallback<Void> callback);
	  
	  void setUsuario(Usuario e, AsyncCallback<Void> callback);

	  void eliminarUsuario(Usuario e, AsyncCallback<Void> callback);

	  //-------------------------ComprobanteVenta---------------------------
	  void greetServerCVenta(String input,AsyncCallback<List<ComprobanteVenta>> asyncCallback);

	  void searchCVenta(ComprobanteVenta e, AsyncCallback<Void> callback);
	  
	  void setCVenta(ComprobanteVenta e, AsyncCallback<Void> callback);

	  void eliminarCVenta(ComprobanteVenta e, AsyncCallback<Void> callback);
	
}
