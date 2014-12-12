package com.test.morphia.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.smartgwt.client.widgets.grid.ListGridRecord;

@Entity
public class Cliente  implements Serializable  {
  private static final long serialVersionUID = -2163813309311000178L;
  @Id
  public String id;
  public String correo;
  public String direccion;
  public String razonSocial;
  public String ciudad;
  public String telefono;

  public Cliente(String pid) {
    super();
    this.id = pid;
  }
  public Cliente(String pid,String pcorreo,String pdireccion,String prazonSocial,String ciudad,String telefono) {
	    super();
	    setId(pid);
	    setCorreo(pcorreo);
	    setDireccion(pdireccion);
	    setRazonSocial(prazonSocial);
	    setCiudad(ciudad);
	    setTelefono(telefono);
  }
  public Cliente() {}

  @Override
  public String toString() {
    return "Cliente [id=" + id + ", username=" + correo + "]";
  }
  
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCorreo() {
	return correo;
}
public void setCorreo(String correo) {
	this.correo = correo;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getRazonSocial() {
	return razonSocial;
}
public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
}
public String getCiudad() {
	return ciudad;
}
public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}

}
