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

package com.test.morphia.client.databaseview;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * This class represents the footer of the database view.
 * 
 * @author Tina Steiger
 */
public class DatabaseFooter  extends Composite{
	
	/**The main panel of the footer*/
	private HorizontalPanel hpanel = new HorizontalPanel();
	
	public DatabaseFooter(){
		
		hpanel.setHeight("100px");
		hpanel.setWidth("600px");		
	}

	/**Returns the main panel of the footer
	 * 
	 * @return The main panel of the footer
	 * */
	public HorizontalPanel getHpanel() {
		return hpanel;
	}
}
