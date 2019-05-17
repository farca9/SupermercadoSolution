package search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class SupermercadoAgenteMain {

    public static void main(String[] args) throws PrologConnectorException {
        
    	//TEST
    	SupermercadoAgenteState test1 = new SupermercadoAgenteState();
    	SupermercadoAgenteState test2 = test1.clone();
    	System.out.println(test1.equals(test2));
    	//
    	
    	SupermercadoAgente agent = new SupermercadoAgente();

        SupermercadoEnvironment environment = new SupermercadoEnvironment();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
