package com.test.morphia.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.test.morphia.model.Distribucion;
import com.test.morphia.server.MongoFactory;
import com.test.morphia.server.MorphiaFactory;

public class DistribucionDAO extends BasicDAO<Distribucion, String> {

  public static DistribucionDAO getDistribucionDAO() {
	 
    return new DistribucionDAO(MorphiaFactory.getMorphia(), MongoFactory.getMongo(),MongoFactory.DBNAME);
  }

  private DistribucionDAO(Morphia morphia, Mongo mongo, String dbName) {
    super(mongo, morphia, dbName);
  }
}