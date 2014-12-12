package com.test.morphia.model;

import java.io.Serializable;
import org.bson.types.ObjectId;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class Employee implements Serializable {

  private static final long serialVersionUID = -2163813309311000178L;
  @Id
  public String id;
  public String correo;

  public Employee(String username) {
    super();
    this.correo = username;
  }

  public Employee() {

  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", username=" + correo + "]";
  }

}
