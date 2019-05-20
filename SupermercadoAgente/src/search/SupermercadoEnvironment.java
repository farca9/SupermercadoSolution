package search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.environment.Environment;
import search.util.Comercio;
import search.util.MapUnit;
import search.util.Producto;
import search.util.ProductoComercio;
import search.util.SupermercadoGoalBasedAgentSimulator;
import search.util.TipoEnum;

public class SupermercadoEnvironment extends Environment {

    public SupermercadoEnvironment() {
        // Create the environment state
        this.environmentState = new SupermercadoEnvironmentState();
    }

    public SupermercadoEnvironmentState getEnvironmentState() {
        return (SupermercadoEnvironmentState) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public  SupermercadoAgentePerception getPercept() {
        // Create a new perception to return
        SupermercadoAgentePerception perception = new SupermercadoAgentePerception();
		
		SupermercadoEnvironmentState state = (SupermercadoEnvironmentState)this.environmentState;
		
		perception.setMapaPerception(state.getMapa());
		perception.setListaProductosPerception(state.getListaProductos());
		perception.setMatrizProductoComercioPerception(state.getMatrizProductoComercio());
		
		
        return perception;
    }

    
    public String toString() {
        return environmentState.toString();
    }

    
    public boolean agentFailed(Action actionReturned) {

        SupermercadoEnvironmentState envState =
                this.getEnvironmentState();

        // TODO: Complete Method        

        return false;
    }
    
	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
    
    public List<Producto> getListaProductos(){
    	return getEnvironmentState().getListaProductos();
    }
    
    public List<ProductoComercio> getMatrizProductoComercio(){
    	return getEnvironmentState().getMatrizProductoComercio();
    }
    
    public MapUnit[][] getMapa(){
    	return getEnvironmentState().getMapa();
    }
    
    public Point getUbicacionAgente() {
    	return getEnvironmentState().getUbicacionAgente();
    }
    
    public void setUbicacionAgente(Point ubicacion) {
    	getEnvironmentState().setUbicacionAgente(ubicacion);
    }
    
	public ArrayList<Comercio> getComercios(){
		Set<Comercio> setComercios = new HashSet<Comercio>();
		for(ProductoComercio pc : this.getMatrizProductoComercio()) {
			setComercios.add(pc.getComercio());
		}
		
		ArrayList<Comercio> comercios = new ArrayList<Comercio>();
		for(Comercio c : setComercios) {
			comercios.add(c);
		}
		
		return comercios;
	}
	
	public ArrayList<Producto> getProductos(){
		Set<Producto> setProductos = new HashSet<Producto>();
		for(ProductoComercio pc : this.getMatrizProductoComercio()) {
			setProductos.add(pc.getProducto());
		}
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for(Producto p : setProductos) {
			if(!productos.contains(p)) {
				productos.add(p);
			}
		}
		
		return productos;
	}
	
}
