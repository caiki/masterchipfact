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

package com.test.morphia.client.mainview;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
*  This class represents the header of the main view.
*  
*  @author Tina Steiger
*  
**/
public class Header extends Composite{
	
	/**The main panel of the header*/
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	/**The panel for buttons*/
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	/**The close database button*/
	private Button closedatabaseButton = new Button("<img src='resources/images/close-database.png' height='30px' width='30px'/>");
	
	/**The logout button*/
	private Button logoutButton = new Button("<img src='resources/images/logout.gif' height='30px' width='30px'/>");
	
	
	public Header(){
		
		//configure buttons
		closedatabaseButton.getElement().setClassName("closedatabasebutton");
		closedatabaseButton.getElement().getStyle().setBackgroundColor("white");
		closedatabaseButton.setWidth("40px");
		closedatabaseButton.setTitle("Close Database");
		logoutButton.getElement().setClassName("logoutbutton");
		logoutButton.setWidth("40px");
		logoutButton.setTitle("Logout");
		
		buttonPanel.setWidth("100px");
		buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		buttonPanel.getElement().getStyle().setMarginLeft(500, Unit.PX);
		buttonPanel.add(closedatabaseButton);
		buttonPanel.add(logoutButton);
		hPanel.add(buttonPanel);

		hPanel.getElement().setClassName("mainheader");	
		hPanel.setWidth("600px");
		hPanel.setHeight("50px");
	}

	/**Returns the main panel of the header
	 * 
	 * @return The main panel of the header
	 * */
	public HorizontalPanel gethPanel() {
		return hPanel;
	}
	
	/**Returns the close database button
	 * 
	 * @return The close database button
	 * */
	public Button getClosedatabaseButton() {
		return closedatabaseButton;
	}

	/**Returns the logout button
	 * 
	 * @return The logout button
	 * */
	public Button getLogoutButton() {
		return logoutButton;
	}
}
