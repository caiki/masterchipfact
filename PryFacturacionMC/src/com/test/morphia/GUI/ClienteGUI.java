package com.test.morphia.GUI;

import java.util.List;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
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
import com.test.morphia.model.Cliente;
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.data.DataSource; 
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;  
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class ClienteGUI extends Window {
	private static DataSource dataCliente = null;  
	private static ClienteGUI miCliente ;
	private static VLayout layout ;
	final IButton bsave = new IButton("Guardar");
	final IButton bdelete = new IButton("Eliminar");
	static ListGrid supplyItemGrid = null; 
	 static DynamicForm form = null;  
	 final DataSource dataSource = ClienteSupplyLocalDS.getInstance(); 
	 
	public ClienteGUI(List<Cliente> result){
			if (miCliente== null){
				dataSourcegetInstance().setID("supplyItemLocalDS");  
		        DataSourceIntegerField pkField = new DataSourceIntegerField("_id");  
		        pkField.setHidden(true);  
		        pkField.setPrimaryKey(true);  
		        
		        DataSourceTextField itemCityField = new DataSourceTextField("itemCity","Ciudad", 128, true);
		        DataSourceTextField itemEmailField = new DataSourceTextField("itemEmail", "Email", 128, true);  
		        DataSourceTextField itemAddressField = new DataSourceTextField("itemAdress", "Direccion", 128, true); 
		        DataSourceTextField itemPhoneField = new DataSourceTextField("itemPhone", "Telefono", 128, true); 
		        DataSourceTextField itemNameField = new DataSourceTextField("itemName", "Razon Social", 128, true); 
		        dataSourcegetInstance().setFields(pkField, itemNameField , itemCityField , itemAddressField, itemPhoneField, itemEmailField);  
		  
		        //dataSourcegetInstance().setClientOnly(true);  
		        
		       // setTestData(ClienteItemData.getRecords());  
				formgetInstance().setIsGroup(true);  
				formgetInstance().setGroupTitle("Mantenimiento");  
				formgetInstance().setNumCols(4);  
				formgetInstance().setDataSource(dataSourcegetInstance());
		        
        	supplyItemGridgetInstance().setHeight(100);
        	supplyItemGridgetInstance().setHeight100();  
        	supplyItemGridgetInstance().setWidth100();  
        	supplyItemGridgetInstance().setPadding(5); 
        	supplyItemGridgetInstance().setAutoFetchData(true);  
        	supplyItemGridgetInstance().setShowFilterEditor(true);  
        	supplyItemGridgetInstance().setFilterOnKeypress(true);  
        	supplyItemGridgetInstance().setFetchDelay(500);                  
        	supplyItemGridgetInstance().setLayoutAlign(VerticalAlignment.BOTTOM);
            
            ListGridField idField = new ListGridField("_id", 35); new ListGridField();
            ListGridField emailField = new ListGridField("itemEmail","E-mail", 150);  
            ListGridField addressField = new ListGridField("itemAdress","Direccion", 225);  
            ListGridField nameField = new ListGridField("itemName","Razon Social", 200);  
            ListGridField cityField = new ListGridField("itemCity","Ciudad",80);
            ListGridField phoneField = new ListGridField("itemPhone","Telefono", 80);
            
            supplyItemGridgetInstance().setFields(idField,nameField,emailField,addressField,cityField,phoneField);  
            
            supplyItemGridgetInstance().addRecordClickHandler(new RecordClickHandler() {  
                public void onRecordClick(RecordClickEvent event) {  
                    form.reset();  
                    form.editSelectedData(supplyItemGrid);  
                }  
            });  
            
           Record record ;
            for (int i = 0; i < result.size(); i++) {
          	  	record= new Record();
          	  	record.setAttribute("_id", result.get(i).id); // record attribute names must match grid field name
                record.setAttribute("itemEmail", result.get(i).correo);
                record.setAttribute("itemAdress", result.get(i).direccion);
                record.setAttribute("itemName", result.get(i).razonSocial);
                record.setAttribute("itemCity", result.get(i).ciudad);
                record.setAttribute("itemPhone", result.get(i).telefono);
                supplyItemGridgetInstance().addData(record); 
                record= null;
			}
              
            bsave.addClickHandler(new ClickHandler() {  
                public void onClick(ClickEvent event) {  
                	formgetInstance().saveData();                  
                }  
            }); 
            layoutgetInstance().addMember(supplyItemGridgetInstance());  
            layoutgetInstance().addMember(formgetInstance());  
            HLayout footerLayout = new HLayout();
            footerLayout.addMember(bsave);  
            footerLayout.addMember(bdelete);
            layoutgetInstance().addMember(footerLayout);  
            this.addItem(layoutgetInstance()); 
            this.addCloseClickHandler(new CloseClickHandler(){

				@Override
				public void onCloseClick(CloseClickEvent event) {
					supplyItemGrid.destroy();
					supplyItemGrid=null;
					miCliente.close();
					miCliente.destroy();	
					miCliente=null;
					}}
            );
			}}
 
	 public static DataSource dataSourcegetInstance() {  
	        if (dataCliente == null) {  
	        	dataCliente = new DataSource("supplyItemLocalDS");  
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
public static DynamicForm formgetInstance() {
	if(form == null) {
	    	  form= new DynamicForm();
	}
	return form;
}
public static ClienteGUI clienteGUIgetInstance(List<Cliente> result) {
    if(miCliente == null) {
  	  miCliente  = new ClienteGUI(result);
    }
    return miCliente;
} 


}
