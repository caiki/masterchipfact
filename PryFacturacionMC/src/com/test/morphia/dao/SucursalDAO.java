package com.test.morphia.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.test.morphia.model.Sucursal;
import com.test.morphia.server.MongoFactory;
import com.test.morphia.server.MorphiaFactory;

public class SucursalDAO extends BasicDAO<Sucursal, String> {

  public static SucursalDAO getSucursalDAO() {
	 
    return new SucursalDAO(MorphiaFactory.getMorphia(), MongoFactory.getMongo(),MongoFactory.DBNAME);
  }

  private SucursalDAO(Morphia morphia, Mongo mongo, String dbName) {
    super(mongo, morphia, dbName);
  }
}