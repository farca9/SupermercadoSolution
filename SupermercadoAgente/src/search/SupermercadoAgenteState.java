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
	//private double costo = 0.0;

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
    	
    	//La nueva lista tiene siempre todos los elementos de la lista percibida.
    	HashMap<Producto,Boolean> listaProductosNew = new HashMap();
    	
    	for(Producto pPercibido : sap.getListaProductosPerception()) {
    		listaProductosNew.put(new Producto(pPercibido.getId(),pPercibido.getNombre()), false);
    	}
    	
    	for(Producto pInNew : listaProductosNew.keySet()) {
    		
    		for(Producto pInOld : this.ListaProductos.keySet()) {
    			
    			if(pInNew.equals(pInOld)) {
    				listaProductosNew.put(pInNew, new Boolean(this.ListaProductos.get(pInOld).booleanValue()));
    			}
    			
    		}
    		
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
    	/*Mapa = new MapUnit[3][3];
    	
    	Mapa[0][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, true);
    	Mapa[0][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[0][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	
    	Mapa[1][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	Mapa[1][1]= new MapUnit(TipoEnum.MANZANA, 0, 0, false, false, false, false);
    	Mapa[1][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	
    	Mapa[2][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	Mapa[2][1]= new MapUnit(TipoEnum.SUPERMERCADO, 1, 1, false, false, false, false);
    	Mapa[2][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false,false,true);

		
		//Ubicacion Inicial
    	this.Ubicacion=new Point(0,0);
    	this.UbicacionAnterior=new Point(0,0);
    	
    	//Producto(s)
    	Producto p1 = new Producto(1,"Huevos");
    	Producto p2 = new Producto(2,"Pan");
    	
    	//Comercio(s)
    	Comercio alvear = new Comercio(1,"Alvear", new Point(2,1));
    	
    	//Lista productos
    	this.ListaProductos=new HashMap<Producto, Boolean>();
    	ListaProductos.put(p1,false);
    	ListaProductos.put(p2,false);
    	
    	//Matriz producto comercio
    	this.MatrizProductoComercio=new ArrayList<ProductoComercio>();
    	MatrizProductoComercio.add(new ProductoComercio(p1, alvear, 5.0));
    	MatrizProductoComercio.add(new ProductoComercio(p2, alvear, 5.0));
    	*/
    	/*
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
    	this.ListaProductos=new HashMap<Producto, Boolean>();
    	ListaProductos.put(p1,false);
    	ListaProductos.put(p2,false);
    	
    	//Matriz producto comercio
    	this.MatrizProductoComercio=new ArrayList<ProductoComercio>();
    	MatrizProductoComercio.add(new ProductoComercio(p1, alvear, 5.0));
    	MatrizProductoComercio.add(new ProductoComercio(p2, kilbel, 3.0));
		*/
    	/*
    	Mapa = new MapUnit[10][6];
    	
    	Mapa[0][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[0][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, true);
    	Mapa[0][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[0][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, false);
    	Mapa[0][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[0][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, true);
    	
    	Mapa[1][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[1][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	Mapa[1][2]= new MapUnit(TipoEnum.SUPERMERCADO, 1, 1, false, false, false, false);
    	Mapa[1][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, false);
    	Mapa[1][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[1][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	
    	Mapa[2][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[2][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, true);
    	Mapa[2][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[2][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, true, false);
    	Mapa[2][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[2][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, true);
    	
    	Mapa[3][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[3][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	Mapa[3][2]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[3][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, false);
    	Mapa[3][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[3][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	
    	Mapa[4][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[4][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, true);
    	Mapa[4][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[4][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, false);
    	Mapa[4][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[4][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, true);
    	
    	Mapa[5][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[5][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	Mapa[5][2]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[5][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, false);
    	Mapa[5][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[5][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	
    	Mapa[6][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[6][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, true);
    	Mapa[6][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[6][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, true, false);
    	Mapa[6][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, false);
    	Mapa[6][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, true, false, false, true);
    	
    	Mapa[7][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[7][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	Mapa[7][2]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[7][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, false);
    	Mapa[7][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[7][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	
    	Mapa[8][0]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[8][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, true);
    	Mapa[8][2]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[8][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, true, false);
    	Mapa[8][4]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, false);
    	Mapa[8][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, true, false, true);
    	
    	Mapa[9][0]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[9][1]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	Mapa[9][2]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[9][3]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, true, false);
    	Mapa[9][4]= new MapUnit(TipoEnum.MANZANA, 1, 1, false, false, false, false);
    	Mapa[9][5]= new MapUnit(TipoEnum.CALLENORMAL, 1, 1, false, false, false, true);
    	
    	
    	//Ubicacion Inicial
    	this.Ubicacion=new Point(8,5);
    	this.UbicacionAnterior=new Point(8,5);
    	
    	//Producto(s)
    	Producto p1 = new Producto(1,"Huevos");
    	Producto p2 = new Producto(2,"Pan");
    	Producto p3 = new Producto(3,"Queso");
    	Producto p4 = new Producto(4,"Salamin");
    	Producto p5 = new Producto(5,"Leche");
    	Producto p6 = new Producto(6,"Papa");
    	
    	//Comercio(s)
    	Comercio alvear = new Comercio(1,"Alvear", new Point(1,2));
    	Comercio kilbel = new Comercio(2,"Kilbel", new Point(7,4));
    	Comercio patricia = new Comercio(3,"Patricia", new Point(7,2));
    	
    	//Lista productos
    	this.ListaProductos=new HashMap();
    	ListaProductos.put(p1, false);
    	ListaProductos.put(p2, false);
    	ListaProductos.put(p3, false);
    	//ListaProductos.put(p4, false);
    	ListaProductos.put(p5, false);
    	//ListaProductos.put(p6, false);
    	
    	
    	//Matriz producto comercio
    	this.MatrizProductoComercio=new ArrayList();
    	MatrizProductoComercio.add(new ProductoComercio(p1, alvear, 5.0));
    	MatrizProductoComercio.add(new ProductoComercio(p2, alvear, 2.0));
    	MatrizProductoComercio.add(new ProductoComercio(p5, alvear, 10.0));
    	MatrizProductoComercio.add(new ProductoComercio(p3, alvear, 100.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p2, kilbel, 3.0));
    	MatrizProductoComercio.add(new ProductoComercio(p6, kilbel, 12.0));
    	MatrizProductoComercio.add(new ProductoComercio(p4, kilbel, 20.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p5, kilbel, 50.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p3, patricia, 95.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p6, patricia, 14.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p4, patricia, 18.0));
    	//MatrizProductoComercio.add(new ProductoComercio(p1, patricia, 4.0));
    	*/
    	this.cargarAmbienteFinal();
    }

    private void cargarAmbienteFinal() {
		
    	//Producto(s)
    	Producto p1 = new Producto(1,"Huevos");
    	Producto p2 = new Producto(2,"Pan");
    	Producto p3 = new Producto(3,"Queso");
    	
    	//Comercio(s)
    	Comercio alvear = new Comercio(1,"Alvear", new Point(2,7));
    	Comercio kilbel = new Comercio(2,"Kilbel", new Point(0,14));
    	//Comercio patricia = new Comercio(3,"Patricia", new Point(8,1));
    	
    	//Lista productos
    	this.ListaProductos=new HashMap();
    	ListaProductos.put(p1, false);
    	ListaProductos.put(p2, false);
    	ListaProductos.put(p3, false);
    	
    	
    	//Matriz producto comercio
    	this.MatrizProductoComercio=new ArrayList();
    	MatrizProductoComercio.add(new ProductoComercio(p1, kilbel, 5.0));
    	MatrizProductoComercio.add(new ProductoComercio(p2, alvear, 2.0));
    	MatrizProductoComercio.add(new ProductoComercio(p3, kilbel, 10.0));
    	
    	//Ubicacion Inicial
    	this.Ubicacion=new Point(8,6);
    	this.UbicacionAnterior=new Point(8,6);
    	
    	//Cargar el mapa
    	Mapa = new MapUnit[10][17];
    	
    	int normalCost = 10;
    	int normalTime = 10;
    	int help=0;
    	
    	//0
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.SUPERMERCADO, normalTime, normalCost, false, false, false, false);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//1
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true,true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true,true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true,true,true,false);
    	help++;
    	
    	//2
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.SUPERMERCADO, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true,true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//3
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false,true,true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true,true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true,true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true,true);
    	Mapa[help][13]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	help++;
    	
    	//4
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false,true, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true,true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//5
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true, false, true,true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false,true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true,true);
    	Mapa[help][13]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	help++;
    	
    	//6
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//7
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true,false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, true, true, false);
    	help++;
    	
    	//8
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.SUPERMERCADO, normalTime, normalCost, false, false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][11]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.MANZANA, normalTime, normalCost, false, false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, false, false, true, false);
    	help++;
    	
    	//9
    	Mapa[help][0]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, true);
    	Mapa[help][1]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][2]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	Mapa[help][3]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][4]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][5]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][6]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	Mapa[help][7]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][8]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][9]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][10]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][11]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][12]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false,  true, true);
    	Mapa[help][13]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][14]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, true);
    	Mapa[help][15]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, false, false);
    	Mapa[help][16]= new MapUnit(TipoEnum.CALLENORMAL, normalTime, normalCost, true , false, true, false);
    	help++;
    	
    	
    	
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
        	if(receivedSet.size()!=thisSet.size()) {
        		return false;
        	} else {
        		
        		for(Producto pTS : this.ListaProductos.keySet()) {	
        			for(Producto pRS : state.getListaProductos().keySet()) {
        				if(pTS.equals(pRS)) {
        					if(state.getListaProductos().get(pRS) != this.ListaProductos.get(pTS)) {
        						return false;
        					}
        				}
        				
        			}
        			
        		}
        		
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
	/*
	public void setCosto(double c) {
		this.costo = c;
	}
	
	public double getCosto() {
		return costo;
	}
	*/
}

