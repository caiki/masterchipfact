package com.test.morphia.server;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;

public class MongoFactory {

  private static MongoClient mongo = null;
  private static String DBHOST = "localhost";
  private static int DBPORT = 27017;
  public static String DBNAME = "facturacion";
  private static DB db = null;
  private static String username = "facturacion";
  private static String password = "#facturacionMC#";
  //mongodb://<dbuser>:<dbpassword>@ds063180.mongolab.com:63180/facturacion
  public static Mongo getMongo()  {
    if (mongo == null) {
      try {
        mongo = new MongoClient(DBHOST, DBPORT);
        db = mongo.getDB(DBNAME);
        /*if(!db.authenticate(username, password.toCharArray())){
            try {
				throw new Exception("No se puede autenticar en BD .");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }*/
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
  public static DBCollection getCollection(String collectionName) 
  throws UnknownHostException, MongoException{
	  //return dataBase.getCollection(collectionName);
	  String uriString = "mongodb://" + DBHOST + ":" + DBPORT + "/" + DBNAME;
	  //String uriStringRemote ="mongodb://facturacion:#facturacionMC#@ds063180.mongolab.com:63180/facturacion";
		//MongoClientURI uri = new MongoClientURI(uriString);
		MongoURI uri = new MongoURI(uriString);
		db = uri.connectDB();
	  return db.getCollection(collectionName);
  }

	
}
