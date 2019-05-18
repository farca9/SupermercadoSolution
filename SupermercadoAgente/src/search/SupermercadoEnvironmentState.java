package search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import frsf.cidisi.faia.state.EnvironmentState;
import search.util.Comercio;
import search.util.MapUnit;
import search.util.Producto;
import search.util.ProductoComercio;
import search.util.TipoEnum;

public class SupermercadoEnvironmentState extends EnvironmentState {
	
	//TODO: Setup Variables - Definir bien en funcion de percepciones
    private MapUnit[][] Mapa;
    private List<Producto> ListaProductos;
    private List<ProductoComercio> MatrizProductoComercio;    
    private Point UbicacionAgente; //hace falta?
	
    public SupermercadoEnvironmentState() {
        
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

	//Estado inicial del Ambiente
    	
    	//Inicializacion del mapa
    	Mapa = new MapUnit[4][4];
    	
    	Mapa[0][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[0][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[0][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	Mapa[0][3]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	
    	Mapa[1][0]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	Mapa[1][1]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	Mapa[1][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[1][3]= new MapUnit(TipoEnum.SUPERMERCADO, 0, 0, false, false, false, false);
    	
    	Mapa[2][0]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	Mapa[2][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, true);
    	Mapa[2][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, false);
    	Mapa[2][3]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	
    	Mapa[3][0]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	Mapa[3][1]= new MapUnit(TipoEnum.SUPERMERCADO, 0, 0, false, false, false, false);
    	Mapa[3][2]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);    	    			
    	Mapa[3][3]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);

    	
    	//Ubicacion Inicial
    	this.UbicacionAgente=new Point(0,0);
    	
    	//Producto(s)
    	Producto p1 = new Producto(1,"Huevos");
    	Producto p2 = new Producto(2,"Pan");
    	
    	//Comercio(s)
    	Comercio alvear = new Comercio(1,"Alvear", new Point(3,1));
    	Comercio kilbel = new Comercio(2,"Kilbel", new Point(1,3));
    	
    	//Lista productos
    	this.ListaProductos=new ArrayList();
    	ListaProductos.add(p1);
    	ListaProductos.add(p2);
    	
    	//Matriz producto comercio
    	this.MatrizProductoComercio=new ArrayList();
    	MatrizProductoComercio.add(new ProductoComercio(p1, alvear, 5.0));
    	MatrizProductoComercio.add(new ProductoComercio(p2, kilbel, 3.0));
    	
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

	public MapUnit[][] getMapa() {
		return Mapa;
	}

	public void setMapa(MapUnit[][] mapa) {
		Mapa = mapa;
	}

	public List<Producto> getListaProductos() {
		return ListaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		ListaProductos = listaProductos;
	}

	public List<ProductoComercio> getMatrizProductoComercio() {
		return MatrizProductoComercio;
	}

	public void setMatrizProductoComercio(List<ProductoComercio> matrizProductoComercio) {
		MatrizProductoComercio = matrizProductoComercio;
	}

	public Point getUbicacionAgente() {
		return UbicacionAgente;
	}

	public void setUbicacionAgente(Point ubicacionAgente) {
		UbicacionAgente = ubicacionAgente;
	}
	
	

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
	
    
	

}


