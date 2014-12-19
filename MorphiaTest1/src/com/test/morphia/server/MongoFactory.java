package com.test.morphia.server;

import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoFactory {

  private static Mongo mongo = null;
  private static String DBHOST = "localhost";
  private static int DBPORT = 27017;
  public static String DBNAME = "facturacion";

  public static Mongo getMongo() {
    if (mongo == null) {
      try {
        mongo = new Mongo(DBHOST, DBPORT);
      } catch (NumberFormatException e) {
        e.printStackTrace();
      } catch (UnknownHostException e) {
        e.printStackTrace();
      } catch (MongoException e) {
        e.printStackTrace();
      }
    }
    return mongo;
  }
}
