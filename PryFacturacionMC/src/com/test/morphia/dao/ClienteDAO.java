package com.test.morphia.dao;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.test.morphia.model.Cliente;
import com.test.morphia.model.ComprobanteVenta;
import com.test.morphia.server.MongoFactory;
import com.test.morphia.server.MorphiaFactory;

public class ClienteDAO extends BasicDAO<Cliente, String> {
  Datastore datastore;
  public static ClienteDAO getClienteDAO() {
    return new ClienteDAO(MorphiaFactory.getMorphia(), MongoFactory.getMongo(),MongoFactory.DBNAME);
  }

  private ClienteDAO(Morphia morphia, Mongo mongo, String dbName) {
    super(mongo, morphia, dbName);
  }
  public void create(Cliente e )
  {
  this.datastore.save( e );
  }
}