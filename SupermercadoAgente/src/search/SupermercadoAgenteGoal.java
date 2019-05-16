package search;

import java.util.Map;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;
import search.util.Producto;

public class SupermercadoAgenteGoal extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
    	
    	for(Map.Entry<Producto,Boolean> entry : ((SupermercadoAgenteState)agentState).getListaProductos().entrySet()) {
    		if(!entry.getValue()) return false;
    	}
    	
        return true;
	}
}
