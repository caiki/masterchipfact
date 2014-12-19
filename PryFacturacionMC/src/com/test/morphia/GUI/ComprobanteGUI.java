package com.test.morphia.GUI;

import java.util.List;

import com.google.appengine.api.search.GetResponse;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.XMLTools;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.test.morphia.client.GreetingService;
import com.test.morphia.client.GreetingServiceAsync;
import com.test.morphia.model.ComprobanteVenta;
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.data.DataSource; 
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;  
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class ComprobanteGUI extends Window {
	private static DataSource dataCliente = null;  
	private static ComprobanteGUI miComprobante ;
	private static VLayout layout ;
	static ListGrid supplyItemGrid = null; 
	
	DSResponse rpta = new DSResponse();
	DSRequest dsRequest= new DSRequest();
	Record[] recs=null;
	public DSResponse getRpta() {
		return rpta;
	}

	public void setRpta(DSResponse rpta) {
		this.rpta = rpta;
	}
	//final DataSource dataSource = ClienteSupplyLocalDS.getInstance(); 
	//static List<Cliente> resultClient =null;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	public ComprobanteGUI(){
		    //this.resultClient = result;  
			if (miComprobante== null){
			//Listar Cliente en tabla
            ListarCliente();
           
            //Agregando los miembros al layout, para pintarlos
            layoutgetInstance().addMember(supplyItemGridgetInstance());  
            //layoutgetInstance().addMember(formgetInstance());  
            HLayout footerLayout = new HLayout();
       
            layoutgetInstance().addMember(footerLayout); 
            this.addItem(layoutgetInstance()); 
            //Evento cuando se cierra ClienteGUI
            this.addCloseClickHandler(new CloseClickHandler(){
				@Override
				public void onCloseClick(CloseClickEvent event) {
					supplyItemGrid.destroy();
					supplyItemGrid=null;
					miComprobante.close();
					miComprobante.destroy();	
					miComprobante=null;
					}
				}
            );
			}
			}
		
	 private void ListarCliente(){
		dataSourcegetInstance().setID("supplyItemLocalDS");  
		//establecer formato XML
		dataSourcegetInstance().setDataFormat(DSDataFormat.XML);  
		dataSourcegetInstance().setDataURL("data/dataIntegration/xml/serverValidationErrors/serverResponse.xml");  
		
		//definir los campos de la fuente de datos
		DataSourceTextField idItemField = new DataSourceTextField("_id", "ID", 50, true);  
		DataSourceTextField itemVendedor = new DataSourceTextField("codVendedor", "Vendedor", 50, true);  
		DataSourceTextField itemRUC = new DataSourceTextField("RUC", "Cliente RUC", 50, true);  
		DataSourceTextField itemDate = new DataSourceTextField("fecha", "Fecha", 100, true);
		DataSourceTextField itemSucursal = new DataSourceTextField("sucursal","Sucursal", 50, true);  
		//DataSourceTextField telefonoField = new DataSourceTextField("itemDetalle", "", 100, true);
		
		dataSourcegetInstance().setFields(idItemField, itemVendedor, itemRUC, itemDate, itemSucursal,itemSucursal);  
		
	    //Definiendo caracteristicas de la tabla    
    	supplyItemGridgetInstance().setHeight(100);
    	supplyItemGridgetInstance().setHeight100();  
    	supplyItemGridgetInstance().setWidth100();  
    	supplyItemGridgetInstance().setPadding(5); 
    	supplyItemGridgetInstance().setAutoFetchData(true);  
    	supplyItemGridgetInstance().setShowFilterEditor(true);  
    	supplyItemGridgetInstance().setFilterOnKeypress(true);  
    	supplyItemGridgetInstance().setFetchDelay(500);                  
    	supplyItemGridgetInstance().setLayoutAlign(VerticalAlignment.BOTTOM);
    	//supplyItemGridgetInstance().setDataSource(client);
    	//Definiendo campos de la tabla
        ListGridField idField = new ListGridField("_id", 35); new ListGridField();
        ListGridField emailField = new ListGridField("codVendedor","Cod Vendedor", 150);  
        ListGridField addressField = new ListGridField("RUC","RUC", 225);  
        ListGridField nameField = new ListGridField("fecha","Fecha", 200);  
        ListGridField cityField = new ListGridField("sucursal","Sucursal",80);
        ListGridField phoneField = new ListGridField("itemDetalle","Detalle Venta", 80);
        //Armando la tabla
        supplyItemGridgetInstance().setFields(idField,nameField,emailField,addressField,cityField,phoneField);  
        //Establecer evento de la tabla
        
        supplyItemGridgetInstance().addRecordClickHandler(new RecordClickHandler() {  
            public void onRecordClick(RecordClickEvent event) {  
                //form.reset();
                //Coincidencia entre el formulario y la tabla
               // form.editSelectedData(supplyItemGrid);  
            }  
        });  
        
	     greetingService.greetServerCVenta("no necesario", new AsyncCallback<List<ComprobanteVenta>>() {
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
	            public void onSuccess(List<ComprobanteVenta> result) {
	             //Llenar la tabla con lo recogido del result
	             Record record ;
	             Record[] recs = new Record[result.size()];
	       	     for (int i = 0; i < result.size(); i++) {
	       	   	  	record= new Record();
	       	   	  	record.setAttribute("_id", result.get(i).id); // record attribute names must match grid field name
	       	         record.setAttribute("codVendedor", result.get(i).codVendedor);
	       	         record.setAttribute("RUC", result.get(i).RUC);
	       	         //record.setAttribute("fecha", result.get(i).fecha);
	       	         record.setAttribute("sucursal", result.get(i).sucursal);
	       	         record.setAttribute("itemDetalle", result.get(i).detalle);
	       	         //recs[i]=record;	
	       	         supplyItemGridgetInstance().addData(record); 
	       	         record= null;
	       	         
	       	     }
	       	     //getDsResponse().setData(recs);
	       	     
	       	     setRegistros(recs);
	             }
	            }
	          );
	    dataSourcegetInstance().setTestData(getRegistros());
	    // setCacheData(getRegistros());
	   
	    //supplyItemGridgetInstance().setDataSource(dataSourcegetInstance());
	 }
	    public void setRegistros(Record [] regs){
	    	recs=regs;
	    }
	    public Record [] getRegistros(){
	    	return recs;
	    }
  
	   public static DataSource dataSourcegetInstance() {  
	    if (dataCliente == null) {  
	        	dataCliente = new DataSource() {  
	                @Override  
	                protected void transformResponse(DSResponse response, DSRequest request, Object xmlData) {  
	                    String status = XMLTools.selectString(xmlData, "/response/status");  
	                    if(!status.equals("success")) {  
	                        response.setStatus(RPCResponse.STATUS_VALIDATION_ERROR);  
	                        Object errors = XMLTools.selectNodes(xmlData, "/response/errors");  
	                        JavaScriptObject errorsJS = XMLTools.toJS(errors);  
	                        response.setErrors(errorsJS);  
	                    }  
	                }  
	            };

	        }  
	        return dataCliente;  
	    } 
		public static ListGrid supplyItemGridgetInstance() {
		    		if(supplyItemGrid == null) {
		    		    	  supplyItemGrid = new ListGrid();
		    		}
		    		return supplyItemGrid;
		}
		public static VLayout layoutgetInstance() {
			if(layout == null) {
				layout = new VLayout(15);;
			}
			return layout;
		}    		

		public static ComprobanteGUI clienteGUIgetInstance() {
		    if(miComprobante == null) {
		  	  miComprobante  = new ComprobanteGUI();
		    }
		    return miComprobante;
		} 
}