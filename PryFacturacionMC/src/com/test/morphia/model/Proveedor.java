package com.test.morphia.model;
import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

public class Proveedor implements Serializable {
	private static final long serialVersionUID = -2163813309311000178L;
	@Id
	public String id;
	public String razonSocial;
	public String direccion;
	public String ruc;
	public String ciudad;

	public Proveedor(String username) {
		super();
		// this.razonSocial = username;
	}

	public Proveedor() {
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", username=" + razonSocial + "]";
	}
}
