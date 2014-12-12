package com.test.morphia.server;
/*package com.test.morphia.server;

import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.DBCursor;
import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.fields.DataSourceBooleanField;  
import com.smartgwt.client.data.fields.DataSourceDateField;  
import com.smartgwt.client.data.fields.DataSourceEnumField;  
import com.smartgwt.client.data.fields.DataSourceFloatField;  
import com.smartgwt.client.data.fields.DataSourceIntegerField;  
import com.smartgwt.client.data.fields.DataSourceTextField;  
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;  
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;  
  
public class ItemSupplyXmlDS extends DataSource {  
  
    private static ItemSupplyXmlDS instance = null;  
  
    public static ItemSupplyXmlDS getInstance() {  
        if (instance == null) {  
            instance = new ItemSupplyXmlDS("supplyItemDS");  
        }  
        return instance;  
    }  
    public void ListarClientes(){
    Morphia morphia = new Morphia();
    Datastore ds = morphia.createDatastore(MongoConnection.getInstance().getConnection(),
    MongoConnection.getInstance().getDatabase().getName());
    Query <Cliente> a  = ds.find(Cliente.class);
    List<Cliente> c = a.asList();
            for (int i = 0; i < c.size(); i++) {
                System.out.println(c.get(i).getId());
                System.out.println(c.get(i).getCorreo());
            } 
    }

    public ItemSupplyXmlDS(String id) {  
   try {
		MongoConnection m =  MongoConnection.getInstance();
        m.connect("", "", "localhost","facturacion", 27017);
		} catch (Exception e) {}
   
        setID(id);  
        //setRecordXPath("/List/supplyItem");  
  
        DataSourceTextField idField = new DataSourceTextField("itemName", "Id", 50, true); 
        //idField.setHidden(true);  
        idField.setPrimaryKey(true);
        
        DataSourceTextField emailField = new DataSourceTextField("SKU", "Email", 150, true);  
  
        DataSourceTextField addressField = new DataSourceTextField("description", "Direccion", 2000);  
      
        DataSourceTextField nameField = new DataSourceTextField("category", "Razon Social", 150, true);  

        DataSourceTextField cityField = new DataSourceTextField("description", "Ciudad", 150);  
        
        DataSourceTextField phoneField = new DataSourceTextField("category", "Telefono", 50, true);  

        setFields(idField, emailField, addressField, nameField, cityField,phoneField);  
  
        //setDataURL("/resources/supplyItem.data.xml");  
        setClientOnly(true);          
    }  
}  */