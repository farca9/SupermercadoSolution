package frsf.cidisi.exercise.agentesupermercado.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class SupermercadoAgenteMain {

    public static void main(String[] args) throws PrologConnectorException {
        SupermercadoAgente agent = new SupermercadoAgente();

        SupermercadoEnvironment environment = new SupermercadoEnvironment();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
