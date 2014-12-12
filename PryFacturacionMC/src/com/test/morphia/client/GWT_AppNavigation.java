// Copyright 2014 Tina Steiger

// This file is part of GWT_AppNavigation.
// GWT_AppNavigation is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// GWT_AppNavigation is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with GWT_AppNavigation.  If not, see <http://www.gnu.org/licenses/>.

package com.test.morphia.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.test.morphia.client.databaseview.DatabaseView;
import com.test.morphia.client.loginview.LoginView;
import com.test.morphia.client.mainview.MainView;


/**
 * This is the Entry point class which defines the onModuleLoad()-Method to start the application, 
 * creates important objects and sets the content of the HTML-Page with the method SetContent().
 * 
 * @author Tina Steiger
 */

public class GWT_AppNavigation implements EntryPoint {
	
	/* Main Elements of the Website which represent the divs in the HTML-Page*/
	private FlowPanel header  = new FlowPanel();
	private FlowPanel content = new FlowPanel();
	private FlowPanel footer = new FlowPanel();
	
	/*Element of the Loginview*/
	private LoginView loginView = new LoginView();
	
	/*Element of the database view*/
	private DatabaseView databaseView = new DatabaseView();
	
	/*Element of the MainMenuView*/
	/*private MainMenuView mainmenuView = new MainMenuView();
	
	/*Element of the main view*/
	private MainView mainView = new MainView();

	/**************************************************************
	 * This is the entry point method which starts the application.
	 **************************************************************/
	public void onModuleLoad() {
		
		/* Adding an Eventhandler to the Login-Button */
		this.loginView.getLoginbutton().addClickHandler(new ClickHandler() {
	          public void onClick(ClickEvent event) {
	     if ( loginView.getUsername().getValue()=="admin" && loginView.getPassword().getValue()=="admin")
	        	  {  setContent(1); } 
	     else
	     {  System.out.println("Usuario o contraseña inválidos,intente nuevamente."); }}});
		
		/* Adding an Eventhandler to the Open Database Button*/
		this.databaseView.getOpenDatabaseButton().addClickHandler(new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  setContent(2);     }
	          });
		
		/*Adds an event handler on the closedatabase button to close the database*/
		this.mainView.getMainheader().getClosedatabaseButton().addClickHandler(new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  
	        	  setContent(1);               
	          }
		 });
	    
		/*Adds an event handler on the logout button of the mainview to log out of the application*/
		
		this.databaseView.getDatabaseHeader().getLogoutButton().addClickHandler(new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  
	        	  setContent(0);        	  
	          }
		 });
		
		this.setContent(0);		
	}
	
	/**Method to set 3 different contents to the HTML-Page, depending on the userstatus and the chosen database
	 * 
	 * @param status Integer value with the status number, 0 for loginview, 1 for databaseview and 2 for mainview
	 * */
	public void setContent(int status){
		switch(status){
        case 0:
            System.out.println("User is not logged in");
            header.clear();
			header.add(loginView.getLoginheader().gethPanel());
			
			content.clear();
			content.add(loginView.getMainPanel());
			
			footer.clear();
			footer.add(loginView.getLoginfooter().getHpanel());
			
			/* Associate the panels with the HTML host page.*/
			RootPanel.get("content").add(content);

			RootPanel.get("header").add(header);

			RootPanel.get("footer").add(footer);
            
            break;
        case 1:
            System.out.println("User is logged in but has not chosen a database");

            header.clear();
			header.add(databaseView.getDatabaseHeader().getHeaderPanel());
			
			content.clear();
			content.add(databaseView.gethPanel());
			
			footer.clear();
			footer.add(databaseView.getDatabaseFooter().getHpanel());		

		/* Associate the panels with the HTML host page.*/
		RootPanel.get("content").add(content);

		RootPanel.get("header").add(header);

		RootPanel.get("footer").add(footer);
		
         break;
        case 2:
            System.out.println("User is logged in and has chosen a database");
            header.clear();
			header.add(mainView.getMainheader().gethPanel());
			
			content.clear();
			content.add(mainView.getMainPanel());
			
			footer.clear();

		/* Associate the panels with the HTML host page.*/
		RootPanel.get("content").add(content);

		RootPanel.get("header").add(header);

            break;
        default:
            System.out.println("switch-case-defaulttext");
        } 		
	}	
}
