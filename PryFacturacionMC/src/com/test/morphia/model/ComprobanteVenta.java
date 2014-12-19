package com.test.morphia.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;


@Entity
public class ComprobanteVenta  implements Serializable  {
  private static final long serialVersionUID = -2163813309311000178L;
  @Id
  public int id;
  public String codVendedor;
  public String RUC;
 // public Date fecha;
  public String sucursal;
  public String [] detalle;

  public ComprobanteVenta(int pid) {
    super();
    this.id = pid;
  }
  public ComprobanteVenta(int pid,String pcodvendedor,String pruc,Date pfecha,String psucursal,String[] pdetalle) {
	    super();
	    setId(pid);
	    setCodVendedor(pcodvendedor);
	    setRUC(pruc);
	    setFecha(pfecha);
	    setSucursal(psucursal);
	    setDetalle(pdetalle);
  }
public ComprobanteVenta() {}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCodVendedor() {
	return codVendedor;
}
public void setCodVendedor(String codVendedor) {
	this.codVendedor = codVendedor;
}
public String getRUC() {
	return RUC;
}
public void setRUC(String rUC) {
	RUC = rUC;
}
/*public Date getFecha() {
	return fecha;
}*/
public void setFecha(Date fecha) {
//	this.fecha = fecha;
}
public String getSucursal() {
	return sucursal;
}
public void setSucursal(String sucursal) {
	this.sucursal = sucursal;
}
public String[] getDetalle() {
	return detalle;
}
public void setDetalle(String[] detalle) {
	this.detalle = detalle;
}
  

}
