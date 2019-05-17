package search.actions;

import java.awt.Point;
import java.util.ArrayList;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.SupermercadoAgenteState;
import search.SupermercadoEnvironmentState;
import search.util.Comercio;
import search.util.ProductoComercio;

public class EntrarSupermercado extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        SupermercadoAgenteState agState = (SupermercadoAgenteState) s;
        SupermercadoAgenteState nextState = agState.clone();
        
        Point ubicacionAgente = agState.getUbicacion();
        Point ubicacionSupermercado = this.getNearbySupermarket(nextState);
        
        if(ubicacionSupermercado != null) {
        	
        	nextState.setUbicacion(new Point(ubicacionSupermercado.x,ubicacionSupermercado.y));
        	nextState.setUbicacionAnterior(new Point(ubicacionAgente.x,ubicacionAgente.y));
        	
        	//Ver si agregar costo/tiempo
        	
        }
        
        
        return null;
    }

	/**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        SupermercadoEnvironmentState environmentState = (SupermercadoEnvironmentState) est;
        SupermercadoAgenteState agState = ((SupermercadoAgenteState) ast);

        // TODO: Use this conditions
        // PreConditions: null
        // PostConditions: null
        
        if (true) {
            // Update the real world
            
            // Update the agent state
            
            return environmentState;
        }

        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "EntrarSupermercado";
    }
    
	private Point getNearbySupermarket(SupermercadoAgenteState s) { //Se asume que los supermercados estan espaciados
			
	    	ArrayList<ProductoComercio> MatrizProductoComercio = s.getMatrizProductoComercio();
	    	Point location = null;
	    	
	    	for(ProductoComercio pc : MatrizProductoComercio) {
	    		Comercio c = pc.getComercio();
	    		if(new Double(c.getUbicacion().distance(s.getUbicacion())).equals(new Double(1.0))) {
	    			if (location == null) {
	    				return null; //Hay mas de un supermercado aledaño, se asume que no ocurre y el operador no se ejecuta
	    			} else {
	    				location = new Point(c.getUbicacion().x,c.getUbicacion().y);
	    			}
	    		}
	    	}
	    	
			return location;
	}
}
