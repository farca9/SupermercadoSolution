package search.actions;

import java.awt.Point;
import java.util.Map;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.SupermercadoAgenteState;
import search.SupermercadoEnvironmentState;
import search.util.*;

public class ComprarProducto extends SearchAction {
	//private static SupermercadoAgenteState state;
    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        SupermercadoAgenteState agState = (SupermercadoAgenteState) s;
        //state = (SupermercadoAgenteState) s;
        
        MapUnit ubicacionActual = agState.getMapa()[agState.getUbicacion().x][agState.getUbicacion().y];
        Point coordinadas = new Point(agState.getUbicacion().x,agState.getUbicacion().y);
        
        //Chequeo que estoy en un supermercado
        if(ubicacionActual.getTipo() == TipoEnum.SUPERMERCADO) {
        	
        	//Recorro todos los productos de la lista
        	for(Map.Entry<Producto,Boolean> producto : agState.getListaProductos().entrySet()) {
        		
        		//Recorro todos los productos de la matriz
        		for(ProductoComercio pc : agState.getMatrizProductoComercio()) {
        			
        			//Chequeo que efectivamente el agente esta en un comercio de la Matriz
        			if(coordinadas.equals(pc.getComercio().getUbicacion())) {
        				
        				//Chequeo si en el supermercado actual esta el producto que busco
            			if(producto.getKey().equals(pc.getProducto()) && !producto.getValue()) {
            				
            				//Compro el producto
            				producto.setValue(true);
            				//agState.setCosto(getCost());
            				return agState;
            				//agState.setMontoGastado(agState.getMontoGastado() + pc.getCosto());
            			}
        			}		
            	}
        	}
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

        //state = (SupermercadoAgenteState) ast;
        
        MapUnit ubicacionActual = agState.getMapa()[agState.getUbicacion().x][agState.getUbicacion().y];
        Point coordinadas = new Point(agState.getUbicacion().x,agState.getUbicacion().y);
        
        //Chequeo que estoy en un supermercado
        if(ubicacionActual.getTipo() == TipoEnum.SUPERMERCADO) {
        	
        	//Recorro todos los productos de la lista
        	for(Map.Entry<Producto,Boolean> producto : agState.getListaProductos().entrySet()) {
        		
        		//Recorro todos los productos de la matriz
        		for(ProductoComercio pc : agState.getMatrizProductoComercio()) {
        			
        			//Chequeo que efectivamente el agente esta en un comercio de la Matriz
        			if(coordinadas.equals(pc.getComercio().getUbicacion())) {
        				
        				//Chequeo si en el supermercado actual est� el producto que busco
            			if(producto.getKey().equals(pc.getProducto()) && !producto.getValue()) {
            				
            				//Compro el producto
            				producto.setValue(true);
            				//agState.setCosto(getCost());
            				//agState.setMontoGastado(agState.getMontoGastado() + pc.getCosto());
            			}
        			}		
            	}
        	}
        }
        
        return environmentState;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost(SearchBasedAgentState s) {
    	/*SupermercadoAgenteState state = (SupermercadoAgenteState)s;
    	
    	double costo = 0.0;
    	costo +=  state.getMapa()[state.getUbicacion().x][state.getUbicacion().y].calcularCosto();
        return costo;*/
    	return 0.1;
        
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "ComprarProducto";
    }

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}
}