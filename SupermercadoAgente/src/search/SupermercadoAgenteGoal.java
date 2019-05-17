package search;

import java.awt.Point;
import java.util.Map;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;
import search.util.Producto;

public class SupermercadoAgenteGoal extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
    	
    	/*for(Map.Entry<Producto,Boolean> entry : ((SupermercadoAgenteState)agentState).getListaProductos().entrySet()) {
    		if(!entry.getValue()) return false;
    	}*/
    	
    	//GOAL TEST
    	/*if(!((SupermercadoAgenteState)agentState).getMatrizProductoComercio().get(0).getComercio().getUbicacion().equals(((SupermercadoAgenteState)agentState).getUbicacion())) {
    		return false;
    	}*/
    	
    	if(((SupermercadoAgenteState)agentState).getUbicacion().equals(new Point(3,1))) {
    		return true;
    	}
    	
        return false;
	}
}
