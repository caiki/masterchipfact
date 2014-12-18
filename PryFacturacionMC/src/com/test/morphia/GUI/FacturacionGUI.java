package com.test.morphia.GUI;

import sun.awt.windows.WComponentPeer;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.XMLTools;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.form.fields.PickerIcon;  
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;  
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;

public class FacturacionGUI extends Window {
	final HLayout hLayoutAlignCenter = new HLayout();
	final VLayout vLayoutAlignCenter2 = new VLayout(); 
	final DynamicForm formClient = new DynamicForm();  
    final ListGrid supplyItemGrid = new ListGrid();
    final TextItem nroFacturaField ;
    private static ComprobanteGUI Wcomprobante=null;
    private static FacturacionGUI mifacturacion ;
    private static DataSource dataComprobante = null; 
    
    static DynamicForm form = null;  
    
	public FacturacionGUI(){
		
		// Specifying the width creates space within which to center the members.  
	    hLayoutAlignCenter.setWidth100();  
	    hLayoutAlignCenter.setHeight100();  
	    hLayoutAlignCenter.setLayoutMargin(6);  
	    hLayoutAlignCenter.setMembersMargin(6);  
	    hLayoutAlignCenter.setBorder("1px dashed blue");  
	    hLayoutAlignCenter.setDefaultLayoutAlign(Alignment.CENTER);
	    
	    // Specifying the width creates space within which to center the members.  
	    vLayoutAlignCenter2.setWidth100();  
	    vLayoutAlignCenter2.setHeight100();  
	    vLayoutAlignCenter2.setLayoutMargin(6);  
	    vLayoutAlignCenter2.setMembersMargin(6);  
	    vLayoutAlignCenter2.setBorder("1px dashed blue"); 
	    vLayoutAlignCenter2.setDefaultLayoutAlign(Alignment.CENTER);
        
		dataSourcegetInstance().setID("supplyItemLocalDS");  
		//establecer formato XML
		dataSourcegetInstance().setDataFormat(DSDataFormat.XML);  
		dataSourcegetInstance().setDataURL("data/dataIntegration/xml/serverValidationErrors/serverResponse.xml");  
	
		//definir los campos de la fuente de datos
		DataSourceTextField idItemField = new DataSourceTextField("_id", "ID", 50, true);  
		
		DataSourceTextField correoField = new DataSourceTextField("itemEmail", "Correo", 50, true);  
		DataSourceTextField direccionField = new DataSourceTextField("itemAdress", "Direccion", 50, true);  
		DataSourceTextField razonSocialField = new DataSourceTextField("itemName", "Razon Social", 100, true);
		DataSourceTextField ciudadField = new DataSourceTextField("itemCity", "Ciudad", 50, true);  
		DataSourceTextField telefonoField = new DataSourceTextField("itemPhone", "Telefono", 100, true);
		
		dataSourcegetInstance().setFields(idItemField, correoField, direccionField, razonSocialField, ciudadField,ciudadField,telefonoField);  
		
		formgetInstance().setDataSource(dataSourcegetInstance());
	    PickerIcon clearPicker = new PickerIcon(PickerIcon.CLEAR, new FormItemClickHandler() {  
            public void onFormItemClick(FormItemIconClickEvent event) {  
                nroFacturaField.clearValue();
            }  
        });  
  
        PickerIcon searchPicker = new PickerIcon(PickerIcon.SEARCH, new FormItemClickHandler() {  
            public void onFormItemClick(FormItemIconClickEvent event) {  
             	ComprobanteGUI sucursalW = ComprobanteGUI.clienteGUIgetInstance();
             	sucursalW.setTitle("Listado de Facturas");  
             	sucursalW.setWidth(600);  
             	sucursalW.setHeight(400);  
             	sucursalW.setCanDragResize(false); 
             	sucursalW.centerInPage();
             	sucursalW.draw();
             }  
        }); 
	    
        nroFacturaField = new TextItem("nroFactura", "Nro Factura");  
        nroFacturaField.setIcons(clearPicker, searchPicker);  
        dataSourcegetInstance().setFields(idItemField, correoField, direccionField, razonSocialField, ciudadField,ciudadField,telefonoField);
        TextItem nroRUCField = new TextItem("nroRUC", "RUC");  
	    TextItem adressField = new TextItem("adressRUC", "Direccion");  
	    TextItem razonField = new TextItem("razonRUC", "Razon");  
	    
	    nroRUCField.setHint("[0-9.]");          
	    nroRUCField.setKeyPressFilter("[0-9.]");
	    //Debe recibir a lo mucho 11 caracteres.
	    
	    HeaderItem header = new HeaderItem();  
	    header.setDefaultValue("Datos Cliente");  

	    IButton validateItem = new IButton();  
	    validateItem.setLeft(300);
	    validateItem.setTitle("Consultar");  
	    
	    formClient.setFields(header,nroFacturaField,nroRUCField,adressField,razonField);  
	    formClient.setTitleOrientation(TitleOrientation.TOP);
	    hLayoutAlignCenter.addMember(formClient);
	    hLayoutAlignCenter.addMember(validateItem);
	    
	    //Detalle Venta
	    
	    supplyItemGrid.setWidth(573);  
	    supplyItemGrid.setHeight(225);  
	    supplyItemGrid.setCanEdit(true);  
	    supplyItemGrid.setCanFreezeFields(true);

	    ListGridField quantityField = new ListGridField("Cantidad",50);  
	    ListGridField unitsField = new ListGridField("Unidad",40);  
	    ListGridField descriptiongField = new ListGridField("Descripcion",290);  
	    ListGridField priceField = new ListGridField("Precio",80);  
	    ListGridField importeField = new ListGridField("Importe",95);  
	    
	    supplyItemGrid.setFields(quantityField, unitsField, descriptiongField, priceField, importeField);  

	    supplyItemGrid.fetchData(new Criteria(), new DSCallback() {  

	        public void execute(DSResponse response, Object rawData, DSRequest request) {  
	            supplyItemGrid.startEditing(0, 0, false);  
	        }  
	    });  
	    vLayoutAlignCenter2.addMember(supplyItemGrid);
	    
	    TextItem subTotalField = new TextItem("subtotal", "SubTotal"); 
	    
	    TextItem igvField = new TextItem("igv", "IGV(18%)");
	    
	    TextItem totalField = new TextItem("total", "Total");
	    
	    final DynamicForm formDetailVenta = new DynamicForm();  
	    formDetailVenta.setFields(new FormItem[] {subTotalField,igvField,totalField});
	    //formDetailVenta.setTitleOrientation(TitleOrientation.LEFT);
	    HLayout mainLayout = new HLayout();  
	    mainLayout.setWidth100();  
	    mainLayout.setHeight100();  
	    mainLayout.addMember(new LayoutSpacer());
	    mainLayout.addMember(formDetailVenta);
	    vLayoutAlignCenter2.addMember(mainLayout);
	    this.addItem(hLayoutAlignCenter);
	    this.addItem(vLayoutAlignCenter2);
	}
	
	public static FacturacionGUI facturacionGUIgetInstance() {
	      if(mifacturacion == null) {
	    	  mifacturacion  = new FacturacionGUI();
	      }
	      return mifacturacion;
	 }
	public static ComprobanteGUI getWComprobante()
	{
		if(Wcomprobante == null) {
		   Wcomprobante  = new ComprobanteGUI();
			
	      }
	      return Wcomprobante;
	}
	public static DynamicForm formgetInstance() {
		if(form == null) {
		    	  form= new DynamicForm();
		}
		return form;
	}  
	
	   public static DataSource dataSourcegetInstance() {  
		    if (dataComprobante == null) {  
		        	dataComprobante = new DataSource() {  
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
		        return dataComprobante;  
		    } 

}

