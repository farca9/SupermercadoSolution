package search;

import javax.swing.*;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import search.util.MapUnit;
import search.util.SupermercadoGoalBasedAgentSimulator;
import search.util.TipoEnum;

public class SupermercadoAgenteMain {

    public static void main(String[] args) throws PrologConnectorException {
    	
    	MapUnit m1 = new MapUnit(TipoEnum.BACHE,10,10,false,true,true,false);
    	MapUnit m2 = m1.clone();
    	System.out.println(m1.equals(m2));
    	
    	
    	SupermercadoAgente agent = new SupermercadoAgente();

        SupermercadoEnvironment environment = new SupermercadoEnvironment();

        SearchBasedAgentSimulator simulator =
                new SupermercadoGoalBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
