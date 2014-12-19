package com.test.morphia.GUI;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceBooleanField;  
import com.smartgwt.client.data.fields.DataSourceDateField;  
import com.smartgwt.client.data.fields.DataSourceEnumField;  
import com.smartgwt.client.data.fields.DataSourceFloatField;  
import com.smartgwt.client.data.fields.DataSourceIntegerField;  
import com.smartgwt.client.data.fields.DataSourceTextField; 
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.test.morphia.client.GreetingService;
import com.test.morphia.client.GreetingServiceAsync;
import com.test.morphia.model.Cliente;

public class ClienteSupplyDS extends DataSource {  
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    private static ClienteSupplyDS instance = null;  
	DSResponse dsResponse = new DSResponse();
	DSRequest dsRequest= new DSRequest();
	Record[] recs;
	public DSResponse getDsResponse() {
		return dsResponse;
	}
	public void setDsResponse(DSResponse rpta) {
		this.dsResponse = rpta;
	}
    public static ClienteSupplyDS getInstance() {  
        if (instance == null) {  
            instance = new ClienteSupplyDS("clienteItemLocalDS");  
        }  
        return instance;  
    }  
  
    public ClienteSupplyDS(String id) {  
  
        setID(id);  
        DataSourceTextField idItemField = new DataSourceTextField("_id", "ID", 50, true);  
		DataSourceTextField correoField = new DataSourceTextField("itemEmail", "Correo", 50, true);  
		DataSourceTextField direccionField = new DataSourceTextField("itemAdress", "Direccion", 50, true);  
		DataSourceTextField razonSocialField = new DataSourceTextField("itemName", "Razon Social", 100, true);
		DataSourceTextField ciudadField = new DataSourceTextField("itemCity", "Ciudad", 50, true);  
		DataSourceTextField telefonoField = new DataSourceTextField("itemPhone", "Telefono", 100, true);
		setFields(idItemField, correoField, direccionField, razonSocialField, ciudadField,ciudadField,telefonoField);  
		
	     greetingService.greetServerCliente("no necesario", new AsyncCallback<List<Cliente>>() {
	            public void onFailure(Throwable caught) {
	              // Show the RPC error message to the user
	                String details = caught.getMessage();
	              Label lbl = new Label();
	              try {
	                	throw caught;
	              }catch (IncompatibleRemoteServiceException e) {
	            	  lbl.setContents(e.toString()+ ": this client is not compatible with the server; cleanup and refresh the browser");
	              } catch (InvocationException e) {
	                // the call didn't complete cleanly
	            	  lbl.setContents(e.toString()+ ": the call didn't complete cleanly");
	              } catch (Throwable e) {
	            	  lbl.setContents(e.toString()+ ": unexpected exception");
	              }
	              HeaderItem header = new HeaderItem();  
	              header.setDefaultValue("Error:"+lbl.toString());
	            }
	            @Override
	            public void onSuccess(List<Cliente> result) {
	             //Llenar la tabla con lo recogido del result
	             Record record ;
	             Record[] recs = new Record[result.size()];
	       	     for (int i = 0; i < result.size(); i++) {
	       	   	  	record= new Record();
	       	   	  	record.setAttribute("_id", result.get(i).id); // record attribute names must match grid field name
	       	         record.setAttribute("itemEmail", result.get(i).correo);
	       	         record.setAttribute("itemAdress", result.get(i).direccion);
	       	         record.setAttribute("itemName", result.get(i).razonSocial);
	       	         record.setAttribute("itemCity", result.get(i).ciudad);
	       	         record.setAttribute("itemPhone", result.get(i).telefono);
	       	         recs[i]=record;	
	       	         //supplyItemGridgetInstance().addData(record); 
	       	         record= null;
	       	         
	       	     }
	       	     //getDsResponse().setData(recs);
	       	     setRegistros(recs);
	             }
	            }
	          );
	     	//setClientOnly(true);
	    	//setTestData(getRegistros());
	    	setCacheData(getRegistros());
		 }
		
    public void setRegistros(Record [] regs){
    	recs=regs;
    }
    public Record [] getRegistros(){
    	return recs;
    }
        //setClientOnly(true);  
        //setTestData(recs);  
    }  
    
