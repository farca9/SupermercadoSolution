package search;

import frsf.cidisi.faia.state.EnvironmentState;

public class SupermercadoEnvironmentState extends EnvironmentState {
	
	//TODO: Setup Variables
    private int[][] Mapa;
    private int[] UbicacionAgente;
	
    public SupermercadoEnvironmentState() {
        
        //TODO: Complete Method
    	/*
			// Mapa = initData0;
			// UbicacionAgente = initData1;
        */
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

        //TODO: Complete Method
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
	
     public int[][] getMapa(){
        return Mapa;
     }
     public void setMapa(int[][] arg){
        Mapa = arg;
     }
     public int[] getUbicacionAgente(){
        return UbicacionAgente;
     }
     public void setUbicacionAgente(int[] arg){
        UbicacionAgente = arg;
     }
	

}


