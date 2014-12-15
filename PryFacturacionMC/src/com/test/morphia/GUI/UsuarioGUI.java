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
import com.test.morphia.model.Usuario;
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.data.DataSource; 
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;  
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class UsuarioGUI extends Window {
	private static DataSource dataUsuario = null;  
	private static UsuarioGUI miUsuario ;
	private static VLayout layout ;
	final IButton bsave = new IButton("Guardar");
	final IButton bdelete = new IButton("Eliminar");
	final IButton bclean = new IButton("Limpiar");
	static ListGrid supplyItemGrid = null; 
	static DynamicForm form = null;  
	static List<Usuario> resultClient =null;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	public UsuarioGUI(){
			if (miUsuario== null){
			//Listar Usuario en tabla
            ListarUsuario();
            //Evento para eliminar un Usuario
            bdelete.addClickHandler(new ClickHandler() {  
        		@Override
        		public void onClick(ClickEvent event) {
        			EliminarUsuario();
        		}
            }); 
            //Evento para agregar un Usuario
			bsave.addClickHandler(new ClickHandler() {  
            	@Override
        		public void onClick(ClickEvent event) {
        			//formgetInstance().saveData(); 
        			GuardarUsuario();
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
            //Evento cuando se cierra UsuarioGUI
            this.addCloseClickHandler(new CloseClickHandler(){
				@Override
				public void onCloseClick(CloseClickEvent event) {
					supplyItemGrid.destroy();
					supplyItemGrid=null;
					miUsuario.close();
					miUsuario.destroy();	
					miUsuario=null;
					}
				}
            );
			}
			}
		
	 private void ListarUsuario(){
		dataSourcegetInstance().setID("supplyItemLocalDS");  
		//establecer formato XML
		dataSourcegetInstance().setDataFormat(DSDataFormat.XML);  
		dataSourcegetInstance().setDataURL("data/dataIntegration/xml/serverValidationErrors/serverResponse.xml");  
		//definir los campos de la fuente de datos
		DataSourceTextField idItemField = new DataSourceTextField("_id", "ID", 50, true);  
		DataSourceTextField cargoField = new DataSourceTextField("itemCargo","Cargo", 50, true);  
		DataSourceTextField lastNameField = new DataSourceTextField("itemLastName","Apellidos",50, true);  
		DataSourceTextField nameField = new DataSourceTextField("itemName","Nombres",100, true);
		DataSourceTextField passField = new DataSourceTextField("itemPassword","Password",50, true);  
		DataSourceTextField nickField = new DataSourceTextField("itemNick","Nick",50, true);
		DataSourceTextField phoneField = new DataSourceTextField("itemPhone","Telefono",50, true);
		DataSourceTextField adressField = new DataSourceTextField("itemAdress","Direccion",50, true);
		DataSourceTextField emailField = new DataSourceTextField("itemEmail","Email",50, true);
		DataSourceTextField DNIField = new DataSourceTextField("itemDNI","DNI",50, true);
		DataSourceTextField codGrupoField = new DataSourceTextField("itemCodGrupo","Grupo",50, true);
		
		
		//DataSourceTextField telefonoField = new DataSourceTextField("itemPhone", "Telefono", 100, true);
		dataSourcegetInstance().setFields(idItemField, cargoField, lastNameField, nameField, 
				passField, nickField,phoneField,adressField,emailField,DNIField,codGrupoField);  
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
        
        ListGridField itemCargo = new ListGridField("itemCargo","Cargo", 80);  
        ListGridField itemLastName = new ListGridField("itemLastName","Apellidos", 150);  
        ListGridField itemName = new ListGridField("itemName","Nombres",150);
        //ListGridField itemPassword = new ListGridField("itemPassword","Pass",80);
        ListGridField itemNick = new ListGridField("itemNick","Nick",80);
        ListGridField itemPhone = new ListGridField("itemPhone","Telefono",80);
        ListGridField itemAdress = new ListGridField("itemAdress","Direccion",80);
        ListGridField itemEmail = new ListGridField("itemEmail","Email",80);
        ListGridField itemDNI = new ListGridField("itemDNI","DNI",80);
        ListGridField itemCodGrupo = new ListGridField("itemCodGrupo","Razon Social", 50);  
        
        //ListGridField phoneField = new ListGridField("itemPhone","Telefono", 80);
        //Armando la tabla
        supplyItemGridgetInstance().setFields(idField,itemCodGrupo,itemCargo,itemLastName,itemName,
        		itemNick,itemPhone,itemAdress,itemEmail,itemDNI);  
        //Establecer evento de la tabla
        supplyItemGridgetInstance().addRecordClickHandler(new RecordClickHandler() {  
            public void onRecordClick(RecordClickEvent event) {  
                form.reset();
                //Coincidencia entre el formulario y la tabla
                form.editSelectedData(supplyItemGrid);  
            }  
        });  
        
        greetingService.greetServerUsuario("no necesario", new AsyncCallback<List<Usuario>>() {
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
            public void onSuccess(List<Usuario> result) {
             //Llenar la tabla con lo recogido del result
             Record record ;
       	     for (int i = 0; i < result.size(); i++) {
       	   	  	record= new Record();
       	   	  	record.setAttribute("_id", result.get(i).id); // record attribute names must match grid field name
       	        record.setAttribute("itemCodGrupo", result.get(i).nombres);
       	        record.setAttribute("itemCargo", result.get(i).direccion);
	       	    record.setAttribute("itemLastName", result.get(i).apellidos);
	   	        record.setAttribute("itemName", result.get(i).nombres);
	   	        record.setAttribute("itemPassword", result.get(i).password);
	   	        record.setAttribute("itemNick", result.get(i).nick);
	   	        record.setAttribute("itemPhone", result.get(i).telefono);
	   	        record.setAttribute("itemAdress", result.get(i).direccion);
	   	        record.setAttribute("itemEmail", result.get(i).correo);
	   	        record.setAttribute("itemDNI", result.get(i).DNI);
	   	           	         
       	         supplyItemGridgetInstance().addData(record); 
       	         record= null;
       			}
            	}}
          );
	 }

	 private void EliminarUsuario(){
 		 //Crear Usuario
			final Usuario usuario = new Usuario();
			//Pendiente: Generar de manera automatica el nuevo codigo
			usuario.setId(form.getValue("_id").toString());
			usuario.setCodGrupo(form.getValue("itemCodGrupo").toString());
			usuario.setCargo(form.getValue("itemCargo").toString());
			usuario.setApellidos(form.getValue("itemLastName").toString());
			usuario.setNombres(form.getValue("itemName").toString());
			usuario.setPassword(form.getValue("itemPassword").toString());
			usuario.setNick(form.getValue("itemNick").toString());
			usuario.setTelefono(form.getValue("itemPhone").toString());
			usuario.setDireccion(form.getValue("itemAdress").toString());
			usuario.setCorreo(form.getValue("itemEmail").toString());
			usuario.setDNI(form.getValue("itemDNI").toString());
			   	         
			greetingService.eliminarUsuario(usuario, new AsyncCallback<Void>(){
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
					aux.setTitle("Se elimino el Usuario Exitosamente ");
					aux.centerInPage();
					aux.draw();
	            }
			});
 	 }
	 private void GuardarUsuario(){
            //Crear Usuario
			final Usuario usuario = new Usuario();
			//Generar de manera automatica el nuevo codigo
			usuario.setId(form.getValue("_id").toString());
			usuario.setCodGrupo(form.getValue("itemCodGrupo").toString());
			usuario.setCargo(form.getValue("itemCargo").toString());
			usuario.setApellidos(form.getValue("itemLastName").toString());
			usuario.setNombres(form.getValue("itemName").toString());
			usuario.setPassword(form.getValue("itemPassword").toString());
			usuario.setNick(form.getValue("itemNick").toString());
			usuario.setTelefono(form.getValue("itemPhone").toString());
			usuario.setDireccion(form.getValue("itemAdress").toString());
			usuario.setCorreo(form.getValue("itemEmail").toString());
			usuario.setDNI(form.getValue("itemDNI").toString());

			//Agregar Usuario
			greetingService.setUsuario(usuario, new AsyncCallback<Void>(){
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
    					aux.setTitle("Se agrego el Usuario Exitosamente");
    					aux.draw();
    					supplyItemGrid.destroy();
    					supplyItemGrid=null;
    					ListarUsuario();
    				}
    			});
			}
 
	 public static DataSource dataSourcegetInstance() {  
	    if (dataUsuario == null) {  
	        	dataUsuario = new DataSource() {  
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
	        return dataUsuario;  
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
		public static UsuarioGUI UsuarioGUIgetInstance() {
		    if(miUsuario == null) {
		  	  miUsuario  = new UsuarioGUI();
		    }
		    return miUsuario;
		} 
}
