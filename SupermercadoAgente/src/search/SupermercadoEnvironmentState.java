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
    	
    	/*
		Mapa = new MapUnit[10][6];
    	
		Mapa[0][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, true, false, false);
    	Mapa[0][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, true);
    	Mapa[0][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[0][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, true);
    	Mapa[0][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[0][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, true);
    	
    	Mapa[1][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[1][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[1][2]= new MapUnit(TipoEnum.SUPERMERCADO, 1, 1, false, false, false, false);
    	Mapa[1][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[1][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[1][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	
    	Mapa[2][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, true, false, false, false);
    	Mapa[2][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, true, true);
    	Mapa[2][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[2][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, true, true);
    	Mapa[2][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[2][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, true, true);
    	
    	Mapa[3][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[3][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[3][2]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[3][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[3][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[3][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	
    	Mapa[4][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, true, false, false);
    	Mapa[4][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, true);
    	Mapa[4][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[4][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, true);
    	Mapa[4][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[4][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, true);
    	
    	Mapa[5][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[5][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[5][2]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[5][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[5][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[5][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false,true, true);
    	
    	Mapa[6][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, true, false, false, false);
    	Mapa[6][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, true, true);
    	Mapa[6][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[6][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, true, true);
    	Mapa[6][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[6][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, true, true);
    	
    	Mapa[7][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[7][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[7][2]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[7][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[7][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[7][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	
    	Mapa[8][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, true, false, false);
    	Mapa[8][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, true);
    	Mapa[8][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[8][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, true);
    	Mapa[8][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[8][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, true);
    	
    	Mapa[9][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[9][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false,true, true);
    	Mapa[9][2]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[9][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[9][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[9][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	
    	
    	//Ubicacion Inicial
    	this.UbicacionAgente=new Point(2,2);
    	
    	//Producto(s)
    	Producto p1 = new Producto(1,"Huevos");
    	Producto p2 = new Producto(2,"Pan");
    	Producto p3 = new Producto(3,"Queso");
    	Producto p4 = new Producto(4,"Salamin");
    	Producto p5 = new Producto(5,"Leche");
    	Producto p6 = new Producto(6,"Papa");
    	
    	//Comercio(s)
    	Comercio alvear = new Comercio(1,"Alvear", new Point(1,2));
    	Comercio kilbel = new Comercio(2,"Kilbel", new Point(7,4));
    	Comercio patricia = new Comercio(3,"Patricia", new Point(7,2));
    	
    	//Lista productos
    	this.ListaProductos=new ArrayList();
    	ListaProductos.add(p1);
    	ListaProductos.add(p2);
    	//ListaProductos.add(p3);
    	//ListaProductos.add(p4);
    	//ListaProductos.add(p5);
    	//ListaProductos.add(p6);
    	
    	
    	//Matriz producto comercio
    	this.MatrizProductoComercio=new ArrayList();
    	MatrizProductoComercio.add(new ProductoComercio(p1, alvear, 5.0));
    	MatrizProductoComercio.add(new ProductoComercio(p2, alvear, 2.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p5, alvear, 10.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p3, alvear, 100.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p2, kilbel, 3.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p6, kilbel, 12.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p4, kilbel, 20.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p5, kilbel, 50.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p3, patricia, 95.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p6, patricia, 14.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p4, patricia, 18.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p1, patricia, 4.0));
    	*/
    	
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


