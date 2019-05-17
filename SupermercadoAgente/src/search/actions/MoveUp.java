package search.actions;

import java.awt.Point;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.SupermercadoAgenteState;
import search.SupermercadoEnvironmentState;
import search.util.MapUnit;
import search.util.TipoEnum;

public class MoveUp extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	@Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        SupermercadoAgenteState state = (SupermercadoAgenteState) s;
        
        Point posActual = new Point(state.getUbicacion().x, state.getUbicacion().y);
        Point posSig = new Point(state.getUbicacion().x, state.getUbicacion().y+1);
        
        
        if(posActual.y+1 < state.getMapa().length) {
        	MapUnit unitUp = state.getMapa()[posSig.x][posSig.y];
        	
        	if(unitUp.isUp()) {
        		TipoEnum tipo = unitUp.getTipo();
        			
        			if(tipo == TipoEnum.CALLENORMAL) {
 	
            		} 
        			else if (tipo == TipoEnum.BACHE) {

            		} 
            		else if (tipo == TipoEnum.CONGESTION) {
  	
            		} 
            		else if (tipo == TipoEnum.EVENTO) {

            		}
        			
        			state.setUbicacion(posSig);
        			state.setUbicacionAnterior(posActual);
        			return state;
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
        return "MoveUp";
    }
}
