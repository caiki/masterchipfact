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
	  public double precioCompra;
	  public double precioVenta;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getTipoArticulo() {
		return tipoArticulo;
	}

	public void setTipoArticulo(String tipoArticulo) {
		this.tipoArticulo = tipoArticulo;
	}
	
	
}
