package search.actions;

import java.awt.Point;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.SupermercadoAgenteState;
import search.SupermercadoEnvironmentState;
import search.util.TipoEnum;

public class SalirSupermercado extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
    	
    	
    	SupermercadoAgenteState agState = (SupermercadoAgenteState) s;
        
    	if(agState.getMapa()[agState.getUbicacion().x][agState.getUbicacion().y].getTipo()==TipoEnum.SUPERMERCADO) {
            //TODO ver si evaluar algunas condiciones con respecto a la ubicacion anterior (por si aparece algun corte mientras esta comprando)
            Point ubicacionSuperAux = new Point (agState.getUbicacion().x,agState.getUbicacion().y);
            agState.setUbicacion(new Point(agState.getUbicacionAnterior().x,agState.getUbicacionAnterior().y));
            agState.setUbicacionAnterior(new Point(ubicacionSuperAux.x,ubicacionSuperAux.y));
            
            System.out.println(this.toString());
            
            return agState;
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

        if(agState.getMapa()[agState.getUbicacion().x][agState.getUbicacion().y].getTipo()==TipoEnum.SUPERMERCADO) {
        
        Point ubicacionSuperAux = new Point (agState.getUbicacion().x,agState.getUbicacion().y);
        agState.setUbicacion(new Point(agState.getUbicacionAnterior().x,agState.getUbicacionAnterior().y));
        environmentState.setUbicacionAgente(new Point(agState.getUbicacionAnterior().x,agState.getUbicacionAnterior().y));
        
        agState.setUbicacionAnterior(new Point(ubicacionSuperAux.x,ubicacionSuperAux.y));
             
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
        return "SalirSupermercado";
    }
}
