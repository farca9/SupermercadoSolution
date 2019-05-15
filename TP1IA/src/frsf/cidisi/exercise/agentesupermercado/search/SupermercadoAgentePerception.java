package frsf.cidisi.exercise.agentesupermercado.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class SupermercadoAgentePerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
	private int bache;
	private int congestion;
	private int eventosocial;
	private int cortecalle;
	private int comerciocerrado;
	private int nuevoproductolista;
	private int productoeliminadolista;
	private int nuevocomercio;
	private int comercioeliminado;
	private int modificacionprecio;
	
 

    public  SupermercadoAgentePerception() {
    	//TODO: Complete Method
    }

    public SupermercadoAgentePerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
    	//TODO: Complete Method
        
        //SupermercadoAgente agent = (SupermercadoAgente) agentIn;
        //SupermercadoEnvironment environment = (SupermercadoEnvironment) environmentIn;
        //SupermercadoEnvironmentState environmentState =
        //        environment.getEnvironmentState();
       
        
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method

        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
	
     public int getbache(){
        return bache;
     }
     public void setbache(int arg){
        this.bache = arg;
     }
     public int getcongestion(){
        return congestion;
     }
     public void setcongestion(int arg){
        this.congestion = arg;
     }
     public int geteventosocial(){
        return eventosocial;
     }
     public void seteventosocial(int arg){
        this.eventosocial = arg;
     }
     public int getcortecalle(){
        return cortecalle;
     }
     public void setcortecalle(int arg){
        this.cortecalle = arg;
     }
     public int getcomerciocerrado(){
        return comerciocerrado;
     }
     public void setcomerciocerrado(int arg){
        this.comerciocerrado = arg;
     }
     public int getnuevoproductolista(){
        return nuevoproductolista;
     }
     public void setnuevoproductolista(int arg){
        this.nuevoproductolista = arg;
     }
     public int getproductoeliminadolista(){
        return productoeliminadolista;
     }
     public void setproductoeliminadolista(int arg){
        this.productoeliminadolista = arg;
     }
     public int getnuevocomercio(){
        return nuevocomercio;
     }
     public void setnuevocomercio(int arg){
        this.nuevocomercio = arg;
     }
     public int getcomercioeliminado(){
        return comercioeliminado;
     }
     public void setcomercioeliminado(int arg){
        this.comercioeliminado = arg;
     }
     public int getmodificacionprecio(){
        return modificacionprecio;
     }
     public void setmodificacionprecio(int arg){
        this.modificacionprecio = arg;
     }
	
   
}
