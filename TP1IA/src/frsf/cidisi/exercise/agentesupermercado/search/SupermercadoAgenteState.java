package frsf.cidisi.exercise.agentesupermercado.search;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class SupermercadoAgenteState extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private int[] Ubicacion;
    //private Other ListaProductos;
    private int[][] MatrizProductoComercio;
    //private Other CriterioEconomia;
	

    public SupermercadoAgenteState() {
    
    	//TODO: Complete Method
    	/*
			// Ubicacion = initData0;
			// ListaProductos = initData1;
			// MatrizProductoComercio = initData2;
			// CriterioEconomia = initData3;
        */
        this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
		//TODO: Complete Method
		
        return null;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
        //TODO: Complete Method
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
	//TODO: Complete Method

    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
       //TODO: Complete Method
        
        return true;
    }

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
     public int[] getUbicacion(){
        return Ubicacion;
     }
     public void setUbicacion(int[] arg){
        Ubicacion = arg;
     }
//     public Other getListaProductos(){
//        return ListaProductos;
//     }
//     public void setListaProductos(Other arg){
//        ListaProductos = arg;
//     }
     public int[][] getMatrizProductoComercio(){
        return MatrizProductoComercio;
     }
     public void setMatrizProductoComercio(int[][] arg){
        MatrizProductoComercio = arg;
     }
//     public Other getCriterioEconomia(){
//        return CriterioEconomia;
//     }
//     public void setCriterioEconomia(Other arg){
//        CriterioEconomia = arg;
//     }
	
}

