package search;

import java.awt.Point;
import java.util.List;

import frsf.cidisi.faia.state.EnvironmentState;
import search.util.MapUnit;
import search.util.Producto;
import search.util.ProductoComercio;

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

        //Estado inicial del ambiente
    	
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


