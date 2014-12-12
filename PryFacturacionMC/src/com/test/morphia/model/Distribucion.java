package com.test.morphia.model;
import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
public class Distribucion implements Serializable{
	 private static final long serialVersionUID = -2163813309311000178L;
	 @Id
	  public String id;
	  public String codArticulo;
	  public int cantidad;
	  
	public Distribucion(String username) {
		super();
		// this.razonSocial = username;
	}

	public Distribucion() {
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", username=" + codArticulo + "]";
	}

}
