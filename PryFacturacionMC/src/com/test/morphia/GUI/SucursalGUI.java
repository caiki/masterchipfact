package com.test.morphia.GUI;

import java.util.List;

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
import com.test.morphia.model.Sucursal;
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.data.DataSource; 
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;  
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class SucursalGUI extends Window {
	private static DataSource dataSucursal = null;  
	private static SucursalGUI miSucursal ;
	private static VLayout layout ;
	final IButton bsave = new IButton("Guardar");
	final IButton bdelete = new IButton("Eliminar");
	final IButton bclean = new IButton("Limpiar");
	static ListGrid supplyItemGrid = null; 
	static DynamicForm form = null;  
	//final DataSource dataSource = SucursalSupplyLocalDS.getInstance(); 
	static List<Sucursal> resultClient =null;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	public SucursalGUI(){
		    //this.resultClient = result;  
			if (miSucursal== null){
			//Listar Sucursal en tabla
            ListarSucursal();
            //Evento para eliminar un Sucursal
            bdelete.addClickHandler(new ClickHandler() {  
        		@Override
        		public void onClick(ClickEvent event) {
        			EliminarSucursal();
        		}
            }); 
            //Evento para agregar un Sucursal
			bsave.addClickHandler(new ClickHandler() {  
            	@Override
        		public void onClick(ClickEvent event) {
        			//formgetInstance().saveData(); 
        			GuardarSucursal();
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
            //Evento cuando se cierra SucursalGUI
            this.addCloseClickHandler(new CloseClickHandler(){
				@Override
				public void onCloseClick(CloseClickEvent event) {
					supplyItemGrid.destroy();
					supplyItemGrid=null;
					miSucursal.close();
					miSucursal.destroy();	
					miSucursal=null;
					}
				}
            );
			}
			}
		
	 private void ListarSucursal(){
		dataSourcegetInstance().setID("supplyItemLocalDS");  
		//establecer formato XML
		dataSourcegetInstance().setDataFormat(DSDataFormat.XML);  
		dataSourcegetInstance().setDataURL("data/dataIntegration/xml/serverValidationErrors/serverResponse.xml");  
		//definir los campos de la fuente de datos
		DataSourceTextField idItemField = new DataSourceTextField("_id", "ID", 50, true);  
		DataSourceTextField adressItemField = new DataSourceTextField("itemDireccion", "Direccion", 50, true);  
		DataSourceTextField phoneItemField = new DataSourceTextField("itemPhone", "Telefono", 50, true);  
		DataSourceTextField distributionItemField = new DataSourceTextField("itemDistribucion", "Distribucion", 100, true);
	    dataSourcegetInstance().setFields(idItemField,adressItemField , phoneItemField, distributionItemField );  
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
        ListGridField adressField = new ListGridField("itemDireccion","Direccion", 150);  
        ListGridField phoneField = new ListGridField("itemPhone","Telefono", 225);  
        ListGridField distributionField = new ListGridField("itemDistribucion","Distribucion", 200);  
        //Armando la tabla
        supplyItemGridgetInstance().setFields(idField,adressField,phoneField,distributionField);  
        //Establecer evento de la tabla
        supplyItemGridgetInstance().addRecordClickHandler(new RecordClickHandler() {  
            public void onRecordClick(RecordClickEvent event) {  
                form.reset();
                //Coincidencia entre el formulario y la tabla
                form.editSelectedData(supplyItemGrid);  
            }  
        });  
        
        greetingService.greetServerSucursal("no necesario", new AsyncCallback<List<Sucursal>>() {
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
            public void onSuccess(List<Sucursal> result) {
             //Llenar la tabla con lo recogido del result
                Record record ;
                for (int i = 0; i < result.size(); i++) {
              	  	record= new Record();
              	  	record.setAttribute("_id", result.get(i).id); // record attribute names must match grid field name
                    record.setAttribute("itemDireccion", result.get(i).direccion);
                    record.setAttribute("itemPhone", result.get(i).telefono);
                    record.setAttribute("itemDistribucion", result.get(i).distribucion);
                    supplyItemGridgetInstance().addData(record); 
                    record= null;
  			}
               
            	}}
          );
    	
	 }
 	 private void EliminarSucursal(){
 		 //Crear Sucursal
			final Sucursal sucursal = new Sucursal();
			//Pendiente: Generar de manera automatica el nuevo codigo
			
			sucursal.setId(form.getValue("_id").toString());
			sucursal.setDireccion(form.getValue("itemDireccion").toString());
			sucursal.setTelefono(form.getValue("itemPhone").toString());
			
			//sucursal.setDistribucion(form.getValue("itemDistribucion").toString());

			
			greetingService.eliminarSucursal(sucursal, new AsyncCallback<Void>(){
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
					aux.setTitle("Se elimino el Sucursal Exitosamente ");
					aux.centerInPage();
					aux.draw();
	            }
			});
 	 }
	 private void GuardarSucursal(){
            //Crear Sucursal
			final Sucursal sucursal = new Sucursal();
			//Generar de manera automatica el nuevo codigo
			sucursal.setId(form.getValue("_id").toString());
			sucursal.setDireccion(form.getValue("itemDireccion").toString());
			sucursal.setTelefono(form.getValue("itemPhone").toString());
			
			//sucursal.setDireccion(form.getValue("itemDistribucion").toString());

			//Agregar Sucursal
			greetingService.setSucursal(sucursal, new AsyncCallback<Void>(){
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
    					aux.setTitle("Se agrego el Sucursal Exitosamente");
    					aux.draw();
    					supplyItemGrid.destroy();
    					supplyItemGrid=null;
    					ListarSucursal();
    				}
    			});
			}
 
	 public static DataSource dataSourcegetInstance() {  
	    if (dataSucursal == null) {  
	        	dataSucursal = new DataSource() {  
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
	        return dataSucursal;  
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
		public static SucursalGUI sucursalGUIgetInstance() {
		    if(miSucursal == null) {
		  	  miSucursal  = new SucursalGUI();
		    }
		    return miSucursal;
		} 
}