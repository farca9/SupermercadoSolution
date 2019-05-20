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
    	
    	this.cargarAmbienteFinal();
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "\n- Lista Productos: " + ListaProductos.toString() + "\n";
        str+="- Matriz ProductoComercio: \n" +"  "+ MatrizProductoComercio.toString() + "\n";
        str += "- Ubicacion Actual: ("+ UbicacionAgente.x+","+UbicacionAgente.y+")\n";
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
	
    private void cargarAmbienteFinal() {
		
    	//Producto(s)
    	Producto p1 = new Producto(1,"Huevos");
    	Producto p2 = new Producto(2,"Pan");
    	Producto p3 = new Producto(3,"Queso");
    	
    	//Comercio(s)
    	Comercio alvear = new Comercio(1,"Alvear", new Point(2,7));
    	Comercio kilbel = new Comercio(2,"Kilbel", new Point(0,14));
    	Comercio patricia = new Comercio(3,"Patricia", new Point(8,1));
    	
    	//Lista productos
    	this.ListaProductos=new ArrayList();
    	ListaProductos.add(p1);
    	ListaProductos.add(p2);
    	//ListaProductos.add(p3);
    	
    	
    	//Matriz producto comercio
    	this.MatrizProductoComercio=new ArrayList();
    	MatrizProductoComercio.add(new ProductoComercio(p1, patricia, 7.0));
    	MatrizProductoComercio.add(new ProductoComercio(p2, patricia, 10.0));
    	MatrizProductoComercio.add(new ProductoComercio(p2, kilbel, 7.0));
    	MatrizProductoComercio.add(new ProductoComercio(p3, kilbel, 10.0));
    	MatrizProductoComercio.add(new ProductoComercio(p3, alvear, 7.0));
    	MatrizProductoComercio.add(new ProductoComercio(p1, alvear, 10.0));
    	
    	//Ubicacion Inicial
    	this.UbicacionAgente=new Point(5,14);
    	
    	//Cargar el mapa
    	Mapa = new MapUnit[10][17];
    	
    	int normalCost = 5;
    	int normalTime = 5;
    	int help=0;
    	
    	//0
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.SUPERMERCADO, normalTime, normalCost, false, false, false, false);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//1
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true,true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true,true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true,true,false);
    	help++;
    	
    	//2
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.SUPERMERCADO, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true,true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//3
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false,true,true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true,true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true,true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true,true);
    	Mapa[help][13]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	help++;
    	
    	//4
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false,true, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true,true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//5
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true, false, true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false,true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true,true);
    	Mapa[help][13]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	help++;
    	
    	//6
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//7
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true,false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	help++;
    	
    	//8
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.SUPERMERCADO, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//9
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false,  true, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	help++;
    	
    	
    	
	}
	

}


