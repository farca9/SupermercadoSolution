package search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
import search.util.CostFunctionType;

public class CostFunction implements IStepCostFunction {
	
	public static CostFunctionType Type;
	
    /**
     * This method calculates the cost of the given NTree node.
     */
    @Override
    public double calculateCost(NTree node) {
    	
        return node.getAction().getCost(node.getAgentState()); //es el estado del nodo o del padre
        
    }
}