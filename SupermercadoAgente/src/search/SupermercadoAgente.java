package search;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.GreedySearch;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.UniformCostSearch;
import search.actions.*;
import search.util.CostFunctionType;

public class SupermercadoAgente extends SearchBasedAgent {

    public SupermercadoAgente() {

        // The Agent Goal
        SupermercadoAgenteGoal agGoal = new SupermercadoAgenteGoal();

        // The Agent State
        SupermercadoAgenteState agState = new SupermercadoAgenteState();
        this.setAgentState(agState);

        // Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();
        operators.addElement(new ComprarProducto());
        operators.addElement(new EntrarSupermercado());	
        operators.addElement(new SalirSupermercado());
        operators.addElement(new MoveUp());	
        operators.addElement(new MoveDown());	
        operators.addElement(new MoveRight());	
        operators.addElement(new MoveLeft());			

        // Create the Problem which the agent will resolve
        Problem problem = new Problem(agGoal, agState, operators);
        this.setProblem(problem);
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {

        // Create the search strategy
    	//CostFunction.Type=CostFunctionType.TRAVELMONEY;
        //UniformCostSearch strategy = new UniformCostSearch(new CostFunction());
        //GreedySearch strategy = new GreedySearch(new Heuristic());
    	//AStarSearch strategy = new AStarSearch(new CostFunction(), new Heuristic());
        BreathFirstSearch strategy = new BreathFirstSearch();
    	//DepthFirstSearch strategy = new DepthFirstSearch();
    	
        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.EFAIA_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(SupermercadoAgente.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;

    }

    /**
     * This method is executed by the simulator to give the agent a perception.
     * Then it updates its state.
     * @param p
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
}
