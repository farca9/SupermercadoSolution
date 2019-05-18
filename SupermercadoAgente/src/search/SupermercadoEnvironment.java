package search;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.environment.Environment;

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
    
    
}
