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

package com.test.morphia.client.loginview;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * This class contains the header of the login view.
 *  
 * @author Tina Steiger
 */
public class LoginHeader  extends Composite{
	
	/**The main panel of the header*/
	private FlowPanel hPanel = new FlowPanel();
	
	public LoginHeader (){		
	}
	
	/**Returns the main panel of the header
	 * 
	 * @return The main panel of the header
	 * */
	public FlowPanel gethPanel() {
		return hPanel;
	}
}
