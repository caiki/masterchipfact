package com.test.morphia.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo; 
import com.test.morphia.model.ComprobanteVenta;
import com.test.morphia.server.MongoFactory;
import com.test.morphia.server.MorphiaFactory;
import com.google.code.morphia.Datastore;

public class ComprobanteVentaDAO extends BasicDAO<ComprobanteVenta, String> {
  Datastore datastore ;	
  public static ComprobanteVentaDAO getComprobanteVentaDAO() {
    return new ComprobanteVentaDAO(MorphiaFactory.getMorphia(), MongoFactory.getMongo(),MongoFactory.DBNAME);
  }

  private ComprobanteVentaDAO(Morphia morphia, Mongo mongo, String dbName) {
    super(mongo, morphia, dbName);
  }
  
  public void create(ComprobanteVenta e )
  {
  this.datastore.save( e );
  }
}