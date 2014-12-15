package com.test.morphia.client.databaseview;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;


//import com.test.morphia.server.ItemSupplyXmlDS;

import com.smartgwt.client.widgets.events.ButtonClickEvent;
import com.smartgwt.client.widgets.events.ButtonClickHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;  
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC; 
import com.smartgwt.client.data.Record; 
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.test.morphia.GUI.ClienteGUI;
import com.test.morphia.GUI.FacturacionGUI;
import com.test.morphia.GUI.ProveedorGUI;
import com.test.morphia.GUI.SucursalGUI;
import com.test.morphia.GUI.UsuarioGUI;
import com.test.morphia.client.GreetingService;
import com.test.morphia.client.GreetingServiceAsync;
import com.test.morphia.model.Cliente;
import com.test.morphia.model.Proveedor;
import com.test.morphia.model.Sucursal;
//import com.test.morphia.server.MongoConnection;
import com.smartgwt.client.widgets.events.ClickHandler; 
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.data.DSCallback;  
/**
 * This class represents the header of the database view.
 */
public class DatabaseHeader extends Composite{
	private FlowPanel headerPanel = new FlowPanel();
	private Image logo = new Image("resources/images/logo.jpg");
	private HTML secondHeadline = new HTML("<h3>Menu Principal</h3>");
	private Menu menu = new Menu();
	private HStack layout = new HStack();
	private Button logoutButton = new Button("<img src='resources/images/logout.gif' height='30px' width='30px'/>");
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	static ListGrid supplyItemGrid = null; 
	final MenuItemSeparator separator = new MenuItemSeparator();
	static Window windowclient = null;  
	static Window windowtienda = null; 
	static Window windowproveedor = null; 
	static Window windowusuario = null; 
	static Window windowproducto = null; 
	static Window windowcompra = null; 
	static Window windowventa = null; 
	static Window windowreporte = null; 
	static ListGrid gridTienda = null;
	static Window windowAddtienda = null; 
	public DatabaseHeader(){
	     // New Menu for Tiendas
 		Menu menuTienda = new Menu();
         MenuItem listTienda = new MenuItem("Listar Tienda/Sucursal" ); 
         listTienda.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {  
             public void onClick(MenuItemClickEvent event) {  
             	SucursalGUI sucursalW = SucursalGUI.sucursalGUIgetInstance();
             	sucursalW.setTitle("Mantenimiento Cliente");  
             	sucursalW.setWidth(600);  
             	sucursalW.setHeight(400);  
             	sucursalW.setCanDragResize(false); 
             	sucursalW.centerInPage();
             	sucursalW.draw();	
             	}  
         }
      );   
   
         MenuItem addTienda = new MenuItem("Agregar Tienda/Sucursal" );  
         menuTienda.setItems(listTienda,separator, addTienda);
         
		// New Menu for Cliente
		Menu menuCliente = new Menu();
		MenuItem listClient = new MenuItem("Listar Cliente"); 
        listClient.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {  
            public void onClick(MenuItemClickEvent event) {  
            	ClienteGUI proveedorW = ClienteGUI.clienteGUIgetInstance();
            	proveedorW.setTitle("Mantenimiento Cliente");  
            	proveedorW.setWidth(600);  
            	proveedorW.setHeight(400);  
            	proveedorW.setCanDragResize(false); 
            	proveedorW.centerInPage();
            	proveedorW.draw();	
            }  
        });   
        menuCliente.setItems(listClient);
        
        // New Menu for Proveedor
		Menu menuProveedor = new Menu();
        MenuItem listProveedor = new MenuItem("Listar Proveedor"); 
        listProveedor.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler(){  
            public void onClick(MenuItemClickEvent event) {  
            	ProveedorGUI proveedorW = ProveedorGUI.ProveedorGUIgetInstance();
            	proveedorW.setTitle("Mantenimiento Proveedor");  
            	proveedorW.setWidth(600);  
            	proveedorW.setHeight(400);  
            	proveedorW.setCanDragResize(false); 
            	proveedorW.centerInPage();
            	proveedorW.draw();
            }  
        } );   
        menuProveedor.setItems(listProveedor);
        
        // New Menu for Usuarios
        Menu menuUsuarios = new Menu();
		MenuItem listUsuario = new MenuItem("Listar Usuarios" ); 
		listUsuario.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {  
            public void onClick(MenuItemClickEvent event) {  
            	UsuarioGUI usuarioW = UsuarioGUI.UsuarioGUIgetInstance();
            	usuarioW.setTitle("Mantenimiento Usuario");  
            	usuarioW.setWidth(600);  
            	usuarioW.setHeight(400);  
            	usuarioW.setCanDragResize(false); 
            	usuarioW.centerInPage();
            	usuarioW.draw();                }  
        });   
		  
		menuUsuarios.setItems(listUsuario,separator);
		
        // New Menu for Productos
		Menu menuProductos = new Menu();
		MenuItem listProductos = new MenuItem("Listar Productos" ); 
		listProductos.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {  
            public void onClick(MenuItemClickEvent event) {  
            	Window window = new Window();  
                window.setTitle("Listar Productos");  
                window.setWidth(300);  
                window.setHeight(85);  
                window.setCanDragResize(true);  
                window.draw();  
                }  
        });   
		MenuItem addProductos = new MenuItem("Agregar Productos" );  
		menuProductos.setItems(listProductos,separator, addProductos);
		
		// New Menu for Compras
		Menu menuCompras = new Menu();
		MenuItem listCompras = new MenuItem("Listar Compras" ); 
		listCompras.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {  
		            public void onClick(MenuItemClickEvent event) {  
		            	Window window = new Window();  
		                window.setTitle("Listar Compras");  
		                window.setWidth(300);  
		                window.setHeight(85);  
		                window.setCanDragResize(true);  
		                window.draw();  
		                }  
		        });   
		MenuItem addCompras = new MenuItem("Realizar Orden de Compra" );  
		menuCompras.setItems(listCompras,separator, addCompras);

		// New Menu for Ventas
		Menu menuVentas = new Menu();
		MenuItem listVentas = new MenuItem("Listar Ventas" ); 
		listVentas.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {  
		            public void onClick(MenuItemClickEvent event) {  
		            	Window window = new Window();  
		                window.setTitle("Listar Ventas");  
		                window.setWidth(300);  
		                window.setHeight(85);  
		                window.setCanDragResize(true);  
		                window.draw();  
		                }  
		        });   
		MenuItem addVentas = new MenuItem("Realizar Facturacion" );  
		menuVentas.setItems(listVentas,separator, addVentas);
		addVentas.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {  
            public void onClick(MenuItemClickEvent event) {  
            	FacturacionGUI facturacionW = FacturacionGUI.facturacionGUIgetInstance();
            	facturacionW.setTitle("Realizar Facturacion");  
            	facturacionW.setWidth(605);  
            	facturacionW.setHeight(600);  
            	facturacionW.setCanDragResize(false); 
            	facturacionW.centerInPage();
            	facturacionW.draw();
            } }); 
		
		// New Menu for Reportes
		Menu menuReportes = new Menu();

        /*Defining a submenu */
		
        IMenuButton menuButton1 = new IMenuButton("Inicio",menu);
        menuButton1.setShowRollOver(true);  
        menuButton1.setIcon("resources/images/home.png");
        menuButton1.setIconOrientation("right");  
        menuButton1.setShowDownIcon(true); 
        menuButton1.setWidth(100);  
        
        IMenuButton menuButton2 = new IMenuButton("Tienda/Sucursal",menuTienda);  
        menuButton2.setIcon("/resources/images/bank.png");
        menuButton2.setWidth(100);  
        
        IMenuButton menuButton3 = new IMenuButton("Clientes", menuCliente);  
        menuButton3.setIcon("/resources/images/customers.png");
        menuButton3.setWidth(100);  
        
        IMenuButton menuButton4 = new IMenuButton("Proveedores", menuProveedor);  
        menuButton4.setIcon("/resources/images/suppliers.png");
        menuButton4.setWidth(100);
        
        IMenuButton menuButton5 = new IMenuButton("Usuarios", menuUsuarios);  
        menuButton5.setIcon("/resources/images/user.png");
        menuButton5.setWidth(100);
        
        IMenuButton menuButton6 = new IMenuButton("Productos", menuProductos);  
        menuButton6.setIcon("/resources/images/product.png");
        menuButton6.setWidth(100);
        
        IMenuButton menuButton7 = new IMenuButton("Compras", menuCompras);
        menuButton7.setIcon("/resources/images/sign-in.png");
        menuButton7.setWidth(100);
        
        IMenuButton menuButton8 = new IMenuButton("Ventas", menuVentas);  
        menuButton8.setIcon("/resources/images/sign-out.png");
        menuButton8.setWidth(100);
        
        IMenuButton menuButton9 = new IMenuButton("Reportes", menuReportes);  
        menuButton9.setIcon("/resources/images/cv.png");
        menuButton9.setWidth(100);

        layout.setWidth100();  
        layout.setMembers(menuButton1,menuButton2,menuButton3,menuButton4,menuButton5,menuButton6,menuButton7,menuButton8,menuButton9);  
  
        headerPanel.add(layout);
        headerPanel.add(logoutButton);
        logo.setWidth("300px");
        
		headerPanel.add(logo);
		headerPanel.add(secondHeadline);			

		//define logout button
		logoutButton.getElement().setClassName("logoutbutton");
		logoutButton.setWidth("40px");
		logoutButton.setTitle("Cerrar Sesion");

	}

	public static ListGrid supplyItemGridgetInstance() {
	      if(supplyItemGrid == null) {
	    	  supplyItemGrid = new ListGrid();
	      }
	      return supplyItemGrid;
	   }
	public static Window windowClientgetInstance() {
	      if(windowclient == null) {
	    	  windowclient = new Window();
	      }
	      return windowclient;
	   }
	public static Window windowTiendagetInstance() {
	      if(windowtienda == null) {
	    	  windowtienda = new Window();
	      }
	      return windowtienda;
	   }
	public static Window windowProveedorgetInstance() {
	      if(windowproveedor == null) {
	    	  windowproveedor = new Window();
	      }
	      return windowproveedor;
	   }
	public static Window windowAddTiendagetInstance() {
	      if(windowAddtienda == null) {
	    	  windowAddtienda  = new Window();
	      }
	      return windowAddtienda ;
	   }
	public static ListGrid gridTiendagetInstance() {
	      if(gridTienda == null) {
	    	  gridTienda = new ListGrid();
	      }
	      return gridTienda;
	   }

	
	public Button getLogoutButton() {
		return logoutButton;
	}
	
	/**Returns the main panel of the header
	 * 
	 * @return The main panel of the header
	 * */
	public FlowPanel getHeaderPanel() {
		return headerPanel;
	}
	  
}
