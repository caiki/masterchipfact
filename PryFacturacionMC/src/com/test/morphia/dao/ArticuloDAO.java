package com.test.morphia.dao;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.test.morphia.model.Articulo;
import com.test.morphia.server.MongoFactory;
import com.test.morphia.server.MorphiaFactory;

public class ArticuloDAO extends BasicDAO<Articulo, String> {

  public static ArticuloDAO getArticuloDAO() {
	 
    return new ArticuloDAO(MorphiaFactory.getMorphia(), MongoFactory.getMongo(),MongoFactory.DBNAME);
  }

  private ArticuloDAO(Morphia morphia, Mongo mongo, String dbName) {
    super(mongo, morphia, dbName);
  }
}
