package search;

import javax.swing.*;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import search.util.SupermercadoGoalBasedAgentSimulator;

public class SupermercadoAgenteMain {

    public static void main(String[] args) throws PrologConnectorException {
    	
    	
    	SupermercadoAgente agent = new SupermercadoAgente();

        SupermercadoEnvironment environment = new SupermercadoEnvironment();

        SearchBasedAgentSimulator simulator =
                new SupermercadoGoalBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
