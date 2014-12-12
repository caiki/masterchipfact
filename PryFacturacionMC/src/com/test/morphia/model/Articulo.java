package com.test.morphia.model;
import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

public class Articulo implements Serializable{
	 private static final long serialVersionUID = -2163813309311000178L;
	 @Id
	  public String id;
	  public String familia;
	  public String nombre;
	  public String marca;
	  public int precioCompra;
	  public int precioVenta;
	  public String presentacion;
	  public String tipoArticulo;

	public Articulo(String username) {
		super();
		// this.razonSocial = username;
	}

	public Articulo() {
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", username=" + familia + "]";
	}
}
