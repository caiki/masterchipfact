package com.test.morphia.model;
import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

public class Usuario implements Serializable {
	private static final long serialVersionUID = -2163813309311000178L;
	@Id
	public String id;
	public String codGrupo;
	public String cargo;
	public String apellidos;
	public String nombres;
	public String password;
	public String nick;
	public String telefono;
	public String direccion;
	public String correo;
	public String DNI;

	public Usuario(String username) {
		super();
		// this.razonSocial = username;
	}

	public Usuario() {
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", username=" + codGrupo + "]";
	}
}
