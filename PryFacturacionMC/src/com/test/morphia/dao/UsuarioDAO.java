package com.test.morphia.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.test.morphia.model.Usuario;
import com.test.morphia.server.MongoFactory;
import com.test.morphia.server.MorphiaFactory;

public class UsuarioDAO extends BasicDAO<Usuario, String> {

  public static UsuarioDAO getUsuarioDAO() {
	 
    return new UsuarioDAO(MorphiaFactory.getMorphia(), MongoFactory.getMongo(),MongoFactory.DBNAME);
  }

  private UsuarioDAO(Morphia morphia, Mongo mongo, String dbName) {
    super(mongo, morphia, dbName);
  }
}