package search;

import java.util.List;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import search.util.MapUnit;
import search.util.Producto;
import search.util.ProductoComercio;

public class SupermercadoAgentePerception extends Perception {

	
	private MapUnit[][] mapaPerception;
	private List<Producto> listaProductosPerception;
	private List<ProductoComercio> matrizProductoComercioPerception;
 

    public  SupermercadoAgentePerception() {
    	//TODO: Complete Method
    }

    public SupermercadoAgentePerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
    	//TODO: Complete Method
        
        SupermercadoAgente agent = (SupermercadoAgente) agentIn;
        SupermercadoEnvironment environment = (SupermercadoEnvironment) environmentIn;
        SupermercadoEnvironmentState environmentState =environment.getEnvironmentState();
        
        this.mapaPerception=environmentState.getMapa();
        this.listaProductosPerception=environmentState.getListaProductos();
        this.matrizProductoComercioPerception=environmentState.getMatrizProductoComercio();       
        
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method

        return str.toString();
    }

	public MapUnit[][] getMapaPerception() {
		return mapaPerception;
	}

	public void setMapaPerception(MapUnit[][] mapaPerception) {
		this.mapaPerception = mapaPerception;
	}

	public List<Producto> getListaProductosPerception() {
		return listaProductosPerception;
	}

	public void setListaProductosPerception(List<Producto> listaProductosPerception) {
		this.listaProductosPerception = listaProductosPerception;
	}

	public List<ProductoComercio> getMatrizProductoComercioPerception() {
		return matrizProductoComercioPerception;
	}

	public void setMatrizProductoComercioPerception(List<ProductoComercio> matrizProductoComercioPerception) {
		this.matrizProductoComercioPerception = matrizProductoComercioPerception;
	}

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
	
    
	
   
}
