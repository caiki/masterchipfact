package com.test.morphia.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.test.morphia.model.Employee;
import com.test.morphia.server.MongoFactory;
import com.test.morphia.server.MorphiaFactory;

public class EmployeeDAO extends BasicDAO<Employee, String> {

  public static EmployeeDAO getEmployeeDAO() {
    return new EmployeeDAO(MorphiaFactory.getMorphia(), MongoFactory.getMongo(),
        MongoFactory.DBNAME);
  }

  private EmployeeDAO(Morphia morphia, Mongo mongo, String dbName) {
    super(mongo, morphia, dbName);
  }
}