package com.test.morphia.GUI;

import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.fields.DataSourceBooleanField;  
import com.smartgwt.client.data.fields.DataSourceDateField;  
import com.smartgwt.client.data.fields.DataSourceEnumField;  
import com.smartgwt.client.data.fields.DataSourceFloatField;  
import com.smartgwt.client.data.fields.DataSourceIntegerField;  
import com.smartgwt.client.data.fields.DataSourceTextField;  
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;  
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;  
  
public class ClienteSupplyLocalDS extends DataSource {  
  
    private static ClienteSupplyLocalDS instance = null;  
  
    public static ClienteSupplyLocalDS getInstance() {  
        if (instance == null) {  
            instance = new ClienteSupplyLocalDS("supplyItemLocalDS");  
        }  
        return instance;  
    }  
  
    public ClienteSupplyLocalDS(String id) {  
  
        setID(id);  
        DataSourceIntegerField pkField = new DataSourceIntegerField("_id");  
        pkField.setHidden(true);  
        pkField.setPrimaryKey(true);  
  
        DataSourceTextField itemEmailField = new DataSourceTextField("itemEmail", "Email", 128, true);  
        DataSourceTextField itemAddressField = new DataSourceTextField("itemAdress", "Direccion", 128, true); 
        DataSourceTextField itemPhoneField = new DataSourceTextField("itemPhone", "Telefono", 128, true); 
        DataSourceTextField itemNameField = new DataSourceTextField("itemName", "Razon Social", 128, true); 
 
  
        setFields(pkField, itemNameField , itemAddressField, itemPhoneField, itemEmailField);  
  
        setClientOnly(true);  
       // setTestData(ClienteItemData.getRecords());  
    }  
}  
