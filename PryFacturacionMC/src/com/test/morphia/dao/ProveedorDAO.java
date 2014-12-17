package com.test.morphia.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.test.morphia.model.Proveedor;
import com.test.morphia.server.MongoFactory;
import com.test.morphia.server.MorphiaFactory;

public class ProveedorDAO extends BasicDAO<Proveedor, String> {

  public static ProveedorDAO getProveedorDAO() {
	 
    return new ProveedorDAO(MorphiaFactory.getMorphia(), MongoFactory.getMongo(),MongoFactory.DBNAME);
  }

  private ProveedorDAO(Morphia morphia, Mongo mongo, String dbName) {
    super(mongo, morphia, dbName);
    //BasicDAO<Proveedor, String> l = new BasicDAO<Proveedor, String>(ds);
    //l.
  }
}
