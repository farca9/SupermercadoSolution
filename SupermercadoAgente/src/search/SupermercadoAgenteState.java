package search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import search.util.*;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class SupermercadoAgenteState extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private Point Ubicacion;
    private Point UbicacionAnterior;
    private	HashMap<Producto,Boolean> ListaProductos;
    private ArrayList<ProductoComercio> MatrizProductoComercio;
    private MapUnit[][] Mapa;
	private double costo = 0.0;

    public SupermercadoAgenteState() {
    
        this.initState();
    }
    
    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SupermercadoAgenteState clone() {
        
    	SupermercadoAgenteState clonedState = new SupermercadoAgenteState();
    	
    	HashMap<Producto,Boolean> clonHM = new HashMap();
    	for(Map.Entry<Producto,Boolean> entry : ListaProductos.entrySet()) {
    		clonHM.put(entry.getKey().clone() , entry.getValue());
    	}
    	
    	ArrayList<ProductoComercio> clonAL = new ArrayList();
    	for(ProductoComercio pc : MatrizProductoComercio) {
    		clonAL.add(pc.clone());
    	}
    	
    	MapUnit[][] clonMapa = new MapUnit[Mapa.length][Mapa[0].length];  //Se asume que el mapa es cuadrado
    	for(int i = 0; i<Mapa.length; i++) {
    		for(int j = 0; j<Mapa[i].length; j++) {
    			clonMapa[i][j]=Mapa[i][j].clone();
    		}
    	}
    	
    	clonedState.setUbicacion(new Point(this.getUbicacion().x,this.getUbicacion().y));
    	clonedState.setUbicacionAnterior(new Point(this.getUbicacionAnterior().x,this.getUbicacionAnterior().y));
    	clonedState.setListaProductos(clonHM);
    	clonedState.setMatrizProductoComercio(clonAL);
    	clonedState.setMapa(clonMapa);
    	
    	return clonedState;
		
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
    	SupermercadoAgentePerception sap=(SupermercadoAgentePerception)p;
    	
    	//El agente actualiza su estado con los valores del mapa percibido
    	for(int i=0;i<sap.getMapaPerception().length;i++) {
    		for(int j=0;j<sap.getMapaPerception()[0].length;j++) {
    			
    			this.Mapa[i][j]=new MapUnit(sap.getMapaPerception()[i][j].getTipo(),
    					sap.getMapaPerception()[i][j].getTiempo(),sap.getMapaPerception()[i][j].getCosto(),
    					sap.getMapaPerception()[i][j].isUp(), sap.getMapaPerception()[i][j].isDown(),
    					sap.getMapaPerception()[i][j].isLeft(),sap.getMapaPerception()[i][j].isRight());
    			
    		}
    	}
    	
    	//El agente actualiza su lista de productos segun la percibida
    	
    	HashMap<Producto,Boolean> listaProductosNew = new HashMap();
    	
    	for(Producto pPercibido : sap.getListaProductosPerception()) {
    		
    		if(this.ListaProductos.containsKey(pPercibido)) { //Si vino en percibido y ya esta, se copia el status
    			
    			listaProductosNew.put(new Producto(pPercibido.getId(),pPercibido.getNombre()), ListaProductos.get(pPercibido));
    			
    		}else { //Si vino en percibido y no está, se agrega con false
    			
    			listaProductosNew.put(new Producto(pPercibido.getId(),pPercibido.getNombre()), false);
    			
    		}
    		
    		//Los productos que no estan en percibidos y si en agente, se ignoran, ya que fueron eliminados de la lista
    		
    	}
    	
    	this.ListaProductos=listaProductosNew;
    	
    	//El agente actualiza su matriz de producto comercio
    	this.MatrizProductoComercio=(ArrayList<ProductoComercio>)sap.getMatrizProductoComercioPerception();
    	
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
    	//Estado inicial del Agente
    	
    	//Inicializacion del mapa
    	Mapa = new MapUnit[4][4];
    	
    	Mapa[0][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[0][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[0][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	Mapa[0][3]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	
    	Mapa[1][0]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	Mapa[1][1]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	Mapa[1][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, true);
    	Mapa[1][3]= new MapUnit(TipoEnum.SUPERMERCADO, 0, 0, false, false, false, false);
    	
    	Mapa[2][0]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	Mapa[2][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, true);
    	Mapa[2][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, false);
    	Mapa[2][3]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	
    	Mapa[3][0]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	Mapa[3][1]= new MapUnit(TipoEnum.SUPERMERCADO, 0, 0, false, false, false, false);
    	Mapa[3][2]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);    	    			
    	Mapa[3][3]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);

    	
    	//Ubicacion Inicial
    	this.Ubicacion=new Point(0,0);
    	this.UbicacionAnterior=new Point(0,0);
    	
    	//Producto(s)
    	Producto p1 = new Producto(1,"Huevos");
    	Producto p2 = new Producto(2,"Pan");
    	
    	//Comercio(s)
    	Comercio alvear = new Comercio(1,"Alvear", new Point(3,1));
    	Comercio kilbel = new Comercio(2,"Kilbel", new Point(1,3));
    	
    	//Lista productos
    	this.ListaProductos=new HashMap();
    	ListaProductos.put(p1, false);
    	ListaProductos.put(p2, false);
    	
    	//Matriz producto comercio
    	this.MatrizProductoComercio=new ArrayList();
    	MatrizProductoComercio.add(new ProductoComercio(p1, alvear, 5.0));
    	MatrizProductoComercio.add(new ProductoComercio(p2, kilbel, 3.0));
    	
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
        String str = "\n";

        str+="- Ubicacion Actual: ("+ Ubicacion.x+","+Ubicacion.y+")\n";
        if(UbicacionAnterior != null) {
        	str+="- Ubicacion Anterior: ("+UbicacionAnterior.x+","+UbicacionAnterior.y+")\n";
        } else {
        	str+="- Ubicacion Anterior: null";
        }
        str+="- Lista Productos: "+ListaProductos.toString()+"\n";
        str+="- MatrizProductoComercio:\n";
        str+="  "+MatrizProductoComercio.toString();
        
        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
    	
    	if (!(obj instanceof SupermercadoAgenteState)) {
            return false;
        } else {
        	
        	SupermercadoAgenteState state = (SupermercadoAgenteState) obj;
        	
        	if(Ubicacion.x != state.getUbicacion().x || Ubicacion.y != state.getUbicacion().y) return false;
        	
        	if(UbicacionAnterior.x != state.getUbicacionAnterior().x || UbicacionAnterior.y != state.getUbicacionAnterior().y) return false;
        
        	Set<Entry<Producto, Boolean>> receivedSet = state.ListaProductos.entrySet();
        	Set<Entry<Producto, Boolean>> thisSet = this.ListaProductos.entrySet();
        	if(ListaProductos.size()!=state.ListaProductos.size()) {
        		return false;
        	} else if (!ListaProductos.equals(state.ListaProductos)) {
        		return false; 
        	}
        	
        	if(!state.getMatrizProductoComercio().equals(this.MatrizProductoComercio)) {
        		return false;
        	}
        	
        	MapUnit[][] receivedMapa = state.getMapa();
        	
        	if(receivedMapa.length != Mapa.length || receivedMapa[0].length != Mapa[0].length) {
        		return false;
        	}
        	
        	for(int i = 0; i<Mapa.length; i++) {
        		for(int j = 0; j<Mapa[i].length; j++) {
        			if(!receivedMapa[i][j].equals(Mapa[i][j])) {
        				return false;
        			}
        		}
        	}
        	
        	return true;
        }
    }

	public Point getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(Point ubicacion) {
		Ubicacion = ubicacion;
	}

	public Point getUbicacionAnterior() {
		return UbicacionAnterior;
	}

	public void setUbicacionAnterior(Point ubicacionAnterior) {
		UbicacionAnterior = ubicacionAnterior;
	}

	public HashMap<Producto, Boolean> getListaProductos() {
		return ListaProductos;
	}

	public void setListaProductos(HashMap<Producto, Boolean> listaProductos) {
		ListaProductos = listaProductos;
	}

	public ArrayList<ProductoComercio> getMatrizProductoComercio() {
		return MatrizProductoComercio;
	}

	public void setMatrizProductoComercio(ArrayList<ProductoComercio> matrizProductoComercio) {
		MatrizProductoComercio = matrizProductoComercio;
	}

	public MapUnit[][] getMapa() {
		return Mapa;
	}

	public void setMapa(MapUnit[][] mapa) {
		Mapa = mapa;
	}
    
	// The following methods are agent-specific:

	public void calcularCosto() {
		
		TipoEnum tipo = getMapa()[getUbicacion().x][getUbicacion().y].getTipo();
    			
		costo +=  getMapa()[getUbicacion().x][getUbicacion().y].getCosto();
				
    		if (tipo == TipoEnum.BACHE) {
    			
    			costo += costo*(0.3);
    			
    		} 
    		else if (tipo == TipoEnum.CONGESTION) {
    			
    			costo += costo*(2);
    			
    		} 
    		else if (tipo == TipoEnum.EVENTO) {
    			
    			costo += costo*(0.8);
    			
    		}
		
	}
	
	public double getCosto() {
		return costo;
	}
	
}

