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
import com.test.morphia.model.Articulo;
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.data.DataSource; 
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;  
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class ProductoGUI extends Window {
	
	private static DataSource dataArticulo = null;  
	private static ProductoGUI miArticulo ;
	private static VLayout layout ;
	final IButton bsave = new IButton("Guardar");
	final IButton bdelete = new IButton("Eliminar");
	final IButton bclean = new IButton("Limpiar");
	static ListGrid supplyItemGrid = null; 
	static DynamicForm form = null;  
	//final DataSource dataSource = ArticuloSupplyLocalDS.getInstance(); 
	static List<Articulo> resultClient =null;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
	public ProductoGUI(){
		    //this.resultClient = result;  
			if (miArticulo== null){
			//Listar Articulo en tabla
            ListarArticulo();
            //Evento para eliminar un Articulo
            bdelete.addClickHandler(new ClickHandler() {  
        		@Override
        		public void onClick(ClickEvent event) {
        			EliminarArticulo();
        		}
            }); 
            //Evento para agregar un Articulo
			bsave.addClickHandler(new ClickHandler() {  
            	@Override
        		public void onClick(ClickEvent event) {
        			//formgetInstance().saveData(); 
        			GuardarArticulo();
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
            //Evento cuando se cierra ArticuloGUI
            this.addCloseClickHandler(new CloseClickHandler(){
				@Override
				public void onCloseClick(CloseClickEvent event) {
					supplyItemGrid.destroy();
					supplyItemGrid=null;
					miArticulo.close();
					miArticulo.destroy();	
					miArticulo=null;
					}
				}
            );
			}
			}
		
	 private void ListarArticulo(){
		dataSourcegetInstance().setID("supplyItemLocalDS");  
		//establecer formato XML
		dataSourcegetInstance().setDataFormat(DSDataFormat.XML);  
		dataSourcegetInstance().setDataURL("data/dataIntegration/xml/serverValidationErrors/serverResponse.xml");  
		//definir los campos de la fuente de datos
		DataSourceTextField idItemField = new DataSourceTextField("_id", "ID", 50, true);  
		DataSourceTextField familiaField = new DataSourceTextField("itemFamily", "Familia", 50, true);  
		DataSourceTextField nombreField = new DataSourceTextField("itemName", "Descripcion", 50, true);  
		DataSourceTextField marcaField = new DataSourceTextField("itemBrand", "Marca", 100, true);
		DataSourceTextField precioCompraField = new DataSourceTextField("itemPCompra", "Precio Compra", 50, true);  
		DataSourceTextField precioVentaField = new DataSourceTextField("itemPVenta", "Precio Venta", 100, true);
		DataSourceTextField presentacionField = new DataSourceTextField("itemPresentation", "Presentacion", 100, true);
		DataSourceTextField tipoArticuloField = new DataSourceTextField("itemTypeProduct", "Tipo Producto", 100, true);
		     
		dataSourcegetInstance().setFields(idItemField, familiaField, nombreField, marcaField, precioCompraField,precioVentaField,presentacionField,tipoArticuloField);  
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
        ListGridField familyField = new ListGridField("itemFamily", "Familia", 150);  
        ListGridField nameField = new ListGridField("itemName", "Descripcion", 225);  
        ListGridField brandField = new ListGridField("itemBrand", "Marca", 200);  
        ListGridField pcompraField = new ListGridField("itemPCompra", "Precio Compra",80);
        ListGridField pventaField = new ListGridField("itemPVenta", "Precio Venta",  80);
        ListGridField presentationField = new ListGridField("itemPresentation", "Presentacion",  80);
        ListGridField typeproductField = new ListGridField("itemTypeProduct", "Tipo Producto",  80);
        
        //Armando la tabla
        supplyItemGridgetInstance().setFields(idField,familyField,nameField,brandField,
        		pcompraField,pventaField,presentationField,typeproductField);  
        //Establecer evento de la tabla
        supplyItemGridgetInstance().addRecordClickHandler(new RecordClickHandler() {  
            public void onRecordClick(RecordClickEvent event) {  
                form.reset();
                //Coincidencia entre el formulario y la tabla
                form.editSelectedData(supplyItemGrid);  
            }  
        });  
        
        greetingService.greetServerArticulo("no necesario", new AsyncCallback<List<Articulo>>() {
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
            public void onSuccess(List<Articulo> result) {
             //Llenar la tabla con lo recogido del result
             Record record ;
       	     for (int i = 0; i < result.size(); i++) {
       	   	  	record= new Record();
       	   	  	record.setAttribute("_id", result.get(i).id); // record attribute names must match grid field name
       	         record.setAttribute("itemFamily", result.get(i).familia);
       	         record.setAttribute("itemName", result.get(i).nombre);
       	         record.setAttribute("itemBrand", result.get(i).marca);
       	         record.setAttribute("itemPCompra", result.get(i).precioCompra);
       	         record.setAttribute("itemPVenta", result.get(i).precioVenta);
       	         record.setAttribute("itemPresentation", result.get(i).presentacion);
       	         record.setAttribute("itemTypeProduct", result.get(i).tipoArticulo);
       	         supplyItemGridgetInstance().addData(record); 
       	         record= null;
       			}
            	}}
          );
    	
	 }
 	 private void EliminarArticulo(){
 		 //Crear Articulo
			final Articulo articulo = new Articulo();
			//Pendiente: Generar de manera automatica el nuevo codigo
			articulo.setId(form.getValue("_id").toString());
			articulo.setFamilia(form.getValue("itemFamily").toString());
			articulo.setNombre(form.getValue("itemName").toString());
			articulo.setMarca(form.getValue("itemBrand").toString());
			articulo.setPrecioCompra(Double.parseDouble(form.getValue("itemPCompra").toString()));
			articulo.setPrecioVenta(Double.parseDouble(form.getValue("itemPVenta").toString()));
			articulo.setPresentacion(form.getValue("itemPresentation").toString());
			articulo.setTipoArticulo(form.getValue("itemTypeProduct").toString());
			
			greetingService.eliminarArticulo(articulo, new AsyncCallback<Void>(){
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
					aux.setTitle("Se elimino el Articulo Exitosamente ");
					aux.centerInPage();
					aux.draw();
	            }
			});
 	 }
	 private void GuardarArticulo(){
            //Crear Articulo
			final Articulo articulo = new Articulo();
			//Pendiente: Generar de manera automatica el nuevo codigo
			articulo.setId(form.getValue("_id").toString());
			articulo.setFamilia(form.getValue("itemFamily").toString());
			articulo.setNombre(form.getValue("itemName").toString());
			articulo.setMarca(form.getValue("itemBrand").toString());
			articulo.setPrecioCompra(Double.parseDouble(form.getValue("itemPCompra").toString()));
			articulo.setPrecioVenta(Double.parseDouble(form.getValue("itemPVenta").toString()));
			articulo.setPresentacion(form.getValue("itemPresentation").toString());
			articulo.setTipoArticulo(form.getValue("itemTypeProduct").toString());
			
			//Agregar Articulo
			greetingService.setArticulo(articulo, new AsyncCallback<Void>(){
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
    					aux.setTitle("Se agrego el Articulo Exitosamente");
    					aux.draw();
    					supplyItemGrid.destroy();
    					supplyItemGrid=null;
    					ListarArticulo();
    				}
    			});
			}
 
	 public static DataSource dataSourcegetInstance() {  
	    if (dataArticulo == null) {  
	        	dataArticulo = new DataSource() {  
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
	        return dataArticulo;  
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
		public static ProductoGUI articuloGUIgetInstance() {
		    if(miArticulo == null) {
		  	  miArticulo  = new ProductoGUI();
		    }
		    return miArticulo;
		} 
}

