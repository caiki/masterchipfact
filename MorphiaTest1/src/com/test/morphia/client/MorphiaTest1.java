package com.test.morphia.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.test.morphia.model.Cliente;
import com.test.morphia.shared.FieldVerifier;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MorphiaTest1 implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GretingServiceAsync greetingService = GWT.create(GretingService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  final ListGrid supplyItemGrid = new ListGrid();  
      greetingService.greetServerCliente("no necesario", new AsyncCallback<List<Cliente>>() {
          public void onFailure(Throwable caught) {
            // Show the RPC error message to the user
            }

          @Override
          public void onSuccess(List<Cliente> result) {
            // TODO Auto-generated method stub

        	  
              supplyItemGrid.setHeight100();  
              supplyItemGrid.setWidth100();  
              supplyItemGrid.setPadding(5); 
              supplyItemGrid.setAutoFetchData(true);  
              supplyItemGrid.setShowFilterEditor(true);  
              supplyItemGrid.setFilterOnKeypress(true);  
              supplyItemGrid.setFetchDelay(500);                  
              supplyItemGrid.setLayoutAlign(VerticalAlignment.BOTTOM);
              
              ListGridField idField = new ListGridField("_id", 100);
              ListGridField emailField = new ListGridField("E-mail", 150);  
              ListGridField addressField = new ListGridField("Direccion", 250);  
              ListGridField nameField = new ListGridField("Razon Social", 200);  
              ListGridField cityField = new ListGridField("Ciudad", 200);
              ListGridField phoneField = new ListGridField("Telefono", 200);
              
              supplyItemGrid.setFields(idField,emailField,addressField,nameField,cityField,phoneField);  
                 
              //List<Cliente> c = a.asList();
              Record record ;
              for (int i = 0; i < result.size(); i++) {
            	  record= new Record();
            	  record.setAttribute("_id", result.get(i).id); // record attribute names must match grid field name
                  record.setAttribute("E-mail", result.get(i).correo);
                  record.setAttribute("Direccion", result.get(i).direccion);
                  record.setAttribute("Razon Social", result.get(i).razonSocial);
                  record.setAttribute("Ciudad", result.get(i).ciudad);
                  record.setAttribute("Telefono", result.get(i).telefono);
                  supplyItemGrid.addData(record); 
                  record= null;
			}
               supplyItemGrid.draw();          
          }
        });
      
  }
}
