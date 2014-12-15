package com.test.morphia.server;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;

public class MongoFactory {

  private static Mongo mongo = null;
  private static String DBHOST = "192.167.1.1";
  private static int DBPORT = 27017;
  public static String DBNAME = "facturacion";
  private static DB db = null;
  private static String username = "facturacion";
  private static String password = "#facturacionMC#";
  //mongodb://<dbuser>:<dbpassword>@ds063180.mongolab.com:63180/facturacion
  public static Mongo getMongo()  {
    if (mongo == null) {
      try {
        mongo = new Mongo(DBHOST, DBPORT);
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
  public static DBCollection getCollection(DB dataBase, String collectionName) {
		return dataBase.getCollection(collectionName);
	}

	public static DB getDataBase(String databaseName)
			throws UnknownHostException, MongoException {
		return getDataBase(databaseName, 27017);
	}

	public static DB getDataBase(String databaseName, int port)
			throws UnknownHostException, MongoException {
		return getDataBase(DBHOST, databaseName, port);
	}

	public static DB getDataBase(String ip, String databaseName, int port)
			throws UnknownHostException, MongoException {
		String uriString = "mongodb://" + ip + ":" + port + "/" + databaseName;
		MongoURI uri = new MongoURI(uriString);
		DB database = null;
		database = uri.connectDB();
		return database;
	}
}
