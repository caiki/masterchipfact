package com.test.morphia.GUI;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.XMLTools;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.test.morphia.client.GreetingService;
import com.test.morphia.client.GreetingServiceAsync;
import com.test.morphia.model.Proveedor;
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.data.DataSource; 
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;  
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class ProveedorGUI extends Window {
	private static DataSource dataProveedor = null;  
	private static ProveedorGUI miProveedor ;
	private static VLayout layout ;
	final IButton bsave = new IButton("Guardar");
	final IButton bdelete = new IButton("Eliminar");
	final IButton bclean = new IButton("Limpiar");
	static ListGrid supplyItemGrid = null; 
	static DynamicForm form = null;  
	static List<Proveedor> resultClient =null;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	public ProveedorGUI(){
			if (miProveedor== null){
			//Listar Proveedor en tabla
            ListarProveedor();
            //Evento para eliminar un Proveedor
            bdelete.addClickHandler(new ClickHandler() {  
        		@Override
        		public void onClick(ClickEvent event) {
        			EliminarProveedor();
        		}
            }); 
            //Evento para agregar un Proveedor
			bsave.addClickHandler(new ClickHandler() {  
            	@Override
        		public void onClick(ClickEvent event) {
        			//formgetInstance().saveData(); 
        			GuardarProveedor();
        		}
            }); 
			//Evento para limpiar el formulario
            bclean.addClickHandler(new ClickHandler() {  
                public void onClick(ClickEvent event) {  
                	formgetInstance().clearValues();                 
                }  
            }); 
            //Agregando los miembros al layout, para pintarlos
            layoutgetInstance().addMember(supplyItemGridgetInstance());  
            layoutgetInstance().addMember(formgetInstance());  
            HLayout footerLayout = new HLayout();
            footerLayout.addMember(bclean);
            footerLayout.addMember(bsave);  
            footerLayout.addMember(bdelete);
            layoutgetInstance().addMember(footerLayout); 
            this.addItem(layoutgetInstance()); 
            //Evento cuando se cierra ProveedorGUI
            this.addCloseClickHandler(new CloseClickHandler(){
				@Override
				public void onCloseClick(CloseClickEvent event) {
					supplyItemGrid.destroy();
					supplyItemGrid=null;
					miProveedor.close();
					miProveedor.destroy();	
					miProveedor=null;
					}
				}
            );
			}
			}
		
	 private void ListarProveedor(){
		dataSourcegetInstance().setID("supplyItemLocalDS");  
		//establecer formato XML
		dataSourcegetInstance().setDataFormat(DSDataFormat.XML);  
		dataSourcegetInstance().setDataURL("data/dataIntegration/xml/serverValidationErrors/serverResponse.xml");  
		//definir los campos de la fuente de datos
		DataSourceTextField idItemField = new DataSourceTextField("_id", "ID", 50, true);  
		DataSourceTextField correoField = new DataSourceTextField("itemName", "Razon Social", 50, true);  
		DataSourceTextField direccionField = new DataSourceTextField("itemAdress", "Direccion", 50, true);  
		DataSourceTextField razonSocialField = new DataSourceTextField("itemRUC", "RUC", 100, true);
		DataSourceTextField ciudadField = new DataSourceTextField("itemCity", "Ciudad", 50, true);  
		//DataSourceTextField telefonoField = new DataSourceTextField("itemPhone", "Telefono", 100, true);
		dataSourcegetInstance().setFields(idItemField, correoField, direccionField, razonSocialField, ciudadField,ciudadField);  
		//Asignar la fuente de datos al form
		//final DynamicForm form = new DynamicForm();  
		formgetInstance().setDataSource(dataSourcegetInstance());  
		formgetInstance().setUseAllDataSourceFields(true);  
		formgetInstance().setIsGroup(true);  
		formgetInstance().setGroupTitle("Mantenimiento");  
		formgetInstance().setNumCols(4);  
		formgetInstance().setDataSource(dataSourcegetInstance());
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
        //Definiendo campos de la tabla
        ListGridField idField = new ListGridField("_id", 35); new ListGridField();
        ListGridField emailField = new ListGridField("itemName","Razon Social", 150);  
        ListGridField addressField = new ListGridField("itemAdress","Direccion", 225);  
        ListGridField nameField = new ListGridField("itemRUC","RUC", 200);  
        ListGridField cityField = new ListGridField("itemCity","Ciudad",80);
        //ListGridField phoneField = new ListGridField("itemPhone","Telefono", 80);
        //Armando la tabla
        supplyItemGridgetInstance().setFields(idField,nameField,emailField,addressField,cityField);  
        //Establecer evento de la tabla
        supplyItemGridgetInstance().addRecordClickHandler(new RecordClickHandler() {  
            public void onRecordClick(RecordClickEvent event) {  
                form.reset();
                //Coincidencia entre el formulario y la tabla
                form.editSelectedData(supplyItemGrid);  
            }  
        });  
        
        greetingService.greetServerProveedor("no necesario", new AsyncCallback<List<Proveedor>>() {
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
            public void onSuccess(List<Proveedor> result) {
             //Llenar la tabla con lo recogido del result
             Record record ;
       	     for (int i = 0; i < result.size(); i++) {
       	   	  	record= new Record();
       	   	  	record.setAttribute("_id", result.get(i).id); // record attribute names must match grid field name
       	         record.setAttribute("itemName", result.get(i).razonSocial);
       	         record.setAttribute("itemAdress", result.get(i).direccion);
       	         record.setAttribute("itemRUC", result.get(i).ruc);
       	         record.setAttribute("itemCity", result.get(i).ciudad);
       	         
       	         supplyItemGridgetInstance().addData(record); 
       	         record= null;
       			}
            	}}
          );
	 }

	 private void EliminarProveedor(){
 		 //Crear Proveedor
			final Proveedor Proveedor = new Proveedor();
			//Pendiente: Generar de manera automatica el nuevo codigo
			Proveedor.setId(form.getValue("_id").toString());
			Proveedor.setRazonSocial(form.getValue("itemEmail").toString());
			Proveedor.setDireccion(form.getValue("itemAdress").toString());
			Proveedor.setRuc(form.getValue("itemRUC").toString());
			Proveedor.setCiudad(form.getValue("itemCity").toString());
			
			greetingService.eliminarProveedor(Proveedor, new AsyncCallback<Void>(){
				@Override
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
                Window aux = new Window ();
				aux.setTitle("Error. No se pudo Eliminar");
				aux.centerInPage();
				aux.draw();
               	}

				@Override
				public void onSuccess(Void result) {
					Window aux = new Window ();
					aux.setTitle("Se elimino el Proveedor Exitosamente ");
					aux.centerInPage();
					aux.draw();
	            }
			});
 	 }
	 private void GuardarProveedor(){
            //Crear Proveedor
			final Proveedor Proveedor = new Proveedor();
			//Generar de manera automatica el nuevo codigo
			Proveedor.setId(form.getValue("_id").toString());
			Proveedor.setRazonSocial(form.getValue("itemName").toString());
			Proveedor.setDireccion(form.getValue("itemAdress").toString());
			Proveedor.setRuc(form.getValue("itemRUC").toString());
			Proveedor.setCiudad(form.getValue("itemCity").toString());

			//Agregar Proveedor
			greetingService.setProveedor(Proveedor, new AsyncCallback<Void>(){
    				@Override
    				public void onFailure(Throwable caught) {
    				 // Show the RPC error message to the user
                     //String details = caught.getMessage();
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
            		Window aux = new Window ();
    				aux.setWidth(200);
    				aux.setTitle("Error. No se pudo agregar");
    				aux.draw();
    				}
    				public void onSuccess(Void result) {
    					Window aux = new Window ();
    					aux.setWidth(200);
    					aux.setTitle("Se agrego el Proveedor Exitosamente");
    					aux.draw();
    					//supplyItemGrid.destroy();
    					//supplyItemGrid=null;
    					ListarProveedor();
    				}
    			});
			}
 
	 public static DataSource dataSourcegetInstance() {  
	    if (dataProveedor == null) {  
	        	dataProveedor = new DataSource() {  
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
	        return dataProveedor;  
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
		public static DynamicForm formgetInstance() {
			if(form == null) {
			    	  form= new DynamicForm();
			}
			return form;
		}
		public static ProveedorGUI ProveedorGUIgetInstance() {
		    if(miProveedor == null) {
		  	  miProveedor  = new ProveedorGUI();
		    }
		    return miProveedor;
		} 
}
