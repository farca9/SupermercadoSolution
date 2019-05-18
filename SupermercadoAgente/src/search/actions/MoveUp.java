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
		SupermercadoAgenteState agState = (SupermercadoAgenteState) s;
        SupermercadoAgenteState nextState = agState.clone();
        
        Point ubicacionAgente = agState.getUbicacion();
        
        
        if(ubicacionAgente.y+1 < agState.getMapa()[0].length) { //No esta en el borde
        	MapUnit unitUp = agState.getMapa()[ubicacionAgente.x][ubicacionAgente.y+1];
        	MapUnit thisUnit = agState.getMapa()[ubicacionAgente.x][ubicacionAgente.y];
        	
        	if(thisUnit.isUp()) {
        		TipoEnum tipo = unitUp.getTipo();
        		
        		if(tipo!=TipoEnum.CALLECORTADA && tipo!=TipoEnum.MANZANA && tipo!=TipoEnum.SUPERMERCADO && tipo!=TipoEnum.SUPERMERCADOCERRADO) {
        			
        			nextState.calcularCosto();
        			
        			//Se realiza el movimiento y se devuelve el nuevo estado
        			nextState.setUbicacion(new Point(nextState.getUbicacion().x, nextState.getUbicacion().y+1));
        			nextState.setUbicacionAnterior(new Point(agState.getUbicacion().x, agState.getUbicacion().y));
        			
        			return nextState;
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

        Point ubicacionAgente = agState.getUbicacion();
          
        if(ubicacionAgente.y+1 < agState.getMapa()[0].length) { //No esta en el borde
        	MapUnit unitUp = agState.getMapa()[ubicacionAgente.x][ubicacionAgente.y+1];
        	MapUnit thisUnit = agState.getMapa()[ubicacionAgente.x][ubicacionAgente.y];
        	
        	if(thisUnit.isUp()) {
        		TipoEnum tipo = unitUp.getTipo();
        		
        		if(tipo!=TipoEnum.CALLECORTADA && tipo!=TipoEnum.MANZANA && tipo!=TipoEnum.SUPERMERCADO && tipo!=TipoEnum.SUPERMERCADOCERRADO) {

        			agState.calcularCosto();
        			
        			//Se realiza el movimiento y se devuelve el nuevo estado
        			Point nuevaUbicacion = new Point(agState.getUbicacion().x, agState.getUbicacion().y+1);
        			Point ubicacionPrevia = new Point(agState.getUbicacion().x, agState.getUbicacion().y);
        			
        			agState.setUbicacion(new Point(nuevaUbicacion.x, nuevaUbicacion.y));
        			agState.setUbicacionAnterior(new Point(ubicacionPrevia.x, ubicacionPrevia.y));
        			
        			environmentState.setUbicacionAgente(new Point(nuevaUbicacion.x, nuevaUbicacion.y));
        			return environmentState;
        			
        		}
        		
        	}
        	
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
