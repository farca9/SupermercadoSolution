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
    	
    	for(Boolean b : ((SupermercadoAgenteState)agentState).getListaProductos().values()) {
    		if(!b) {
    			return false;
    		}
    	}
    	return true;
    	
    	
    	
    	/*if(((SupermercadoAgenteState)agentState).getUbicacion().equals(new Point(3,1))) {
    		return true;
    	}else{
    		return false;
    	}*/
    	
        
	}
}
