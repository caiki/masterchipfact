package com.test.morphia.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class Cliente implements Serializable {
  private static final long serialVersionUID = -2163813309311000178L;
  @Id
  public String id;
  public String correo;
  public String direccion;
  public String razonSocial;
  public String ciudad;
  public String telefono;

  public Cliente(String username) {
    super();
    //this.razonSocial = username;
  }

  public Cliente() {}

  @Override
  public String toString() {
    return "Cliente [id=" + id + ", username=" + correo + "]";
  }

}
