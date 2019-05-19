package gui;

import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import search.SupermercadoAgenteState;
import search.util.MapUnit;
import search.util.Producto;
import search.util.ProductoComercio;
import search.util.TipoEnum;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Interfaz {
	
	private static JPanel board;
	
    private static SupermercadoAgenteState agente = null;
	
	public static void main(String[] args){
		
		agente = new SupermercadoAgenteState();
		inicializarInterfaz(agente);
		
	}
	
	public static void inicializarInterfaz(SupermercadoAgenteState agente) {
		
		JFrame frame = new JFrame("SupermercadoSolution - IA");
		frame.setSize(900,1000);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		int width = agente.getMapa().length;
        int height = agente.getMapa()[0].length;
		
		Square[][] mapaGrafico = new Square[width][height];
		
		for (int i = 0; i < agente.getMapa().length; i++) {
            for (int j = 0; j < agente.getMapa()[i].length; j++) {
            	Square square = new Square();
            	square.setMapUnit(agente.getMapa()[i][j]);
                mapaGrafico[i][j] = square;  
            }
        }

        board = new BoardPanel(mapaGrafico);
	
        //Panel Principal
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(board);
        
        //Fuentes
        Font titulo = new Font("Arial", Font.BOLD, 20);
		Font elemento = new Font("Arial", Font.PLAIN, 18);
        
		//Panel de Datos
		JPanel datos = new JPanel();
        datos.setLayout(new GridLayout(0,3));
        
        	//Panel de Lista de Productos
	        JPanel listaProductos = new JPanel();
	        listaProductos.setLayout(new BoxLayout(listaProductos, BoxLayout.PAGE_AXIS));
			
				JLabel tituloListaProductos = new JLabel("PRODUCTOS A COMPRAR");
				tituloListaProductos.setFont(titulo);
				tituloListaProductos.setForeground(new Color(0,128,192));
				
				listaProductos.add(tituloListaProductos);
				
		        for(Producto pr : agente.getListaProductos().keySet()) {
		        	 JLabel labelProductos = new JLabel(" - " + pr.getNombre().toString());
				     labelProductos.setFont(elemento);
				     listaProductos.add(labelProductos);
		        }
		        
		    //Panel de Matriz de Productos    
		    JPanel matrizProductos = new JPanel();
		    matrizProductos.setLayout(new BoxLayout(matrizProductos, BoxLayout.PAGE_AXIS));	        
			 
			    JLabel tituloMatrizProductos = new JLabel("PRODUCTOS DISPONIBLES");
			    tituloMatrizProductos.setFont(titulo);
			    tituloMatrizProductos.setForeground(new Color(0,128,192)); 
			    
			    matrizProductos.add(tituloMatrizProductos);
			    
			    JLabel labelProductosDisponibles = new JLabel();
			    for(ProductoComercio pc : agente.getMatrizProductoComercio()) {
			    	JLabel labelProductoDisponible = new JLabel(" - " + pc.getProducto().getNombre().toString()+" a $"+pc.getCosto()+" en "+pc.getComercio().getNombre().toString());
			    	labelProductoDisponible.setFont(elemento);
			    	matrizProductos.add(labelProductoDisponible);
			    }

		
		//Panel de Comandos
		JPanel comandos = new JPanel();	   
		comandos.setLayout(new BoxLayout(comandos, BoxLayout.PAGE_AXIS));
		
			JButton btnProximoMovimiento = new JButton ("Proximo Movimiento");
			JButton btnAgregarProductos = new JButton("Agregar Productos a Comprar");
			JButton btnAgregarProductosDisponibles = new JButton("Agregar Productos Disponibles");
			JButton btnAgregarComercios = new JButton("Agregar Comercios");
	
			btnProximoMovimiento.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnAgregarProductos.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnAgregarProductosDisponibles.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnAgregarComercios.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			btnProximoMovimiento.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        //TODO Ver que onda acá
		      }
		    });
			
			btnAgregarProductos.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String nombre_producto;
		    	  int codigo_producto = agente.getListaProductos().size()+1;
		    	  nombre_producto = JOptionPane.showInputDialog("Nombre del Producto");
		    	  agente.getListaProductos().put(new Producto(codigo_producto,nombre_producto), false);
		    	  frame.dispose();
		    	  actualizarInterfaz(agente);
		    	  	  
		      }
		    });
			
			btnAgregarProductosDisponibles.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        //TODO Ver que onda acá
		      }
		    });
			
			btnAgregarComercios.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        //TODO Ver que onda acá
		      }
		    });
			
			comandos.add(btnProximoMovimiento);
			comandos.add(new JLabel("\n"));
			comandos.add(btnAgregarProductos);
			comandos.add(new JLabel("\n"));
			comandos.add(btnAgregarProductosDisponibles);
			comandos.add(new JLabel("\n"));
			comandos.add(btnAgregarComercios);
			comandos.add(new JLabel("\n"));
		
			  
        datos.add(listaProductos);
        datos.add(matrizProductos);
        datos.add(comandos);
        
        contenedor.add(datos,BorderLayout.PAGE_END);
        
        frame.setContentPane(contenedor);     
             
        frame.setVisible(true);
        
        
	}
	
	public static void actualizarInterfaz(SupermercadoAgenteState agente) {
		
		inicializarInterfaz(agente);
		
	}
		
	 private static class BoardPanel extends JPanel {

	        Square[][] squares;

	        public BoardPanel(final Square[][] squares) {

	            this.squares = squares;
	            setToolTipText("");

	        }
	        
	        @Override
	        public String getToolTipText(MouseEvent event) {
	        	
	        	int x = event.getPoint().x;
	        	int y = event.getPoint().y;
	        	
	        	x = x/100;
	        	y = y/100;
	        	
	        	if(x<squares.length && y<squares[0].length) {
	        		return squares[x][y].getMapUnit().getTipo().toString()+" @ ("+x+";"+y+")";
	        	} else return null;
	        	
	        }

	        @Override
	        public void paintComponent(final Graphics g) {
	        	
	            super.paintComponent(g);

	            int width = 500;
	            int height = 450;

	            for (int i = 0; i < squares.length; i++) {
	                for (int j = 0; j < squares[i].length; j++) {

	                	//Calculo de las dimensiones de cada cuadrado de la cuadricula
	                    Square currentSquare = squares[i][j];
	                    int x = i * (width / squares.length);
	                    int y = j * (height / squares.length);
	                    int xx = x + ((width / squares.length)/2) - (currentSquare.getImage().getWidth()/2);
	                    int yy = y + ((height / squares.length)/2) - (currentSquare.getImage().getHeight()/2);

	                    //Relleno de cada cuadrado
	                    g.setColor(currentSquare.getBackground());
	                    g.fillRect(x, y, width / squares.length, height / squares.length);
	                    g.drawImage(currentSquare.getImage(),xx,yy,null);
	                    
	                    //Dibujo de las flechas
	                    float mitadX = (width / squares.length)/2;
	                    float mitadY = (height / squares.length)/2;
	                    float x1 = x + mitadX - 6;
	                    float y1 = y + mitadY - 6;
	                    float x2 = x + (width/squares.length) - 12;
	                    float y2 = y + (height/squares.length) - 12;
	                    g.drawImage(currentSquare.getDirecciones()[0],(int)x1,(int)y,null);
	                    g.drawImage(currentSquare.getDirecciones()[1],(int)x1,(int)y2,null);
	                    g.drawImage(currentSquare.getDirecciones()[2],(int)x,(int)y1,null);
	                    g.drawImage(currentSquare.getDirecciones()[3],(int)x2,(int)y1,null);
	                    
	                    
	                    //Borde de cada cuadrado
	                    Graphics2D borde = this.crearBorde(g,1,new Color(240,240,240));
	    	            borde.drawRect(x, y, width / squares.length, height / squares.length);
	                }
	            }

	        }
	        
	        public Graphics2D crearBorde(Graphics g, double grosor, Color color) {
	        	Graphics2D g2 = (Graphics2D)g;
                g2.setColor(color);
	            g2.setStroke(new BasicStroke((float) grosor));
	            return g2;
	        }

	    }
	
	private static class Square {

		MapUnit mapUnit;
        boolean isSelected;
        Color background;
        BufferedImage imagen;
        BufferedImage[] direcciones = new BufferedImage[4];
        
        public Square() {
        	
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(final boolean isSelected) {
            this.isSelected = isSelected;
        }

        public Color getBackground() {
            return background;
        }

        public void setBackground(final Color background) {
            this.background = background;
        }
        
        public MapUnit getMapUnit() {
        	return mapUnit;
        }
        
        public void setMapUnit(final MapUnit mapUnit) {
        	this.mapUnit = mapUnit;
        	
        	if(this.mapUnit.getTipo().equals(TipoEnum.SUPERMERCADO)) {
        		this.setBackground(new Color(198,239,206));
        	} else if(this.mapUnit.getTipo().equals(TipoEnum.MANZANA)) {
        		this.setBackground(new Color(255,235,156));
        	} else if(this.mapUnit.getTipo().equals(TipoEnum.SUPERMERCADOCERRADO)) {
        		this.setBackground(Color.BLUE);
        	} else {
        		this.setBackground(Color.WHITE);
        	}
        	
        	this.setImage();
        	this.setDirecciones();
        
        }
        
        public void setImage() {
        	File imageFile = null;
        	BufferedImage image = null; 
            if(this.mapUnit.getTipo().equals(TipoEnum.BACHE)) imageFile = new File("src/gui/drawable/Bache.png");
            else if(this.mapUnit.getTipo().equals(TipoEnum.EVENTO)) imageFile = new File("src/gui/drawable/Evento.png");
            else if(this.mapUnit.getTipo().equals(TipoEnum.CALLECORTADA)) imageFile = new File("src/gui/drawable/Cortada.png");
            else if(this.mapUnit.getTipo().equals(TipoEnum.CONGESTION)) imageFile = new File("src/gui/drawable/Congestion.png");
            else imageFile = new File("src/gui/drawable/Pixel.png");
            
            if(imageFile != null){
            	try {
            		image = ImageIO.read(imageFile);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}        	
            }
            
            this.imagen = image;
            
        }
        
        public BufferedImage getImage() {
        	return imagen;
        }
        
        public BufferedImage[] getDirecciones() {
        	return direcciones;
        }
        
        public void setDirecciones() {
        	        	
        	File up = null, down = null, left = null, right = null;
        	BufferedImage imageUp = null, imageDown = null, imageLeft = null, imageRight = null; 
            if(this.mapUnit.isUp()) up = new File("src/gui/drawable/up.png");  else up = new File("src/gui/drawable/Pixel.png");
            if(this.mapUnit.isDown()) down = new File("src/gui/drawable/down.png");  else down = new File("src/gui/drawable/Pixel.png");
            if(this.mapUnit.isLeft()) left = new File("src/gui/drawable/left.png");  else left = new File("src/gui/drawable/Pixel.png");
            if(this.mapUnit.isRight()) right = new File("src/gui/drawable/right.png");  else right = new File("src/gui/drawable/Pixel.png");   
            
            if(up != null && down != null && left != null && right != null){
            	try {
            		imageUp = ImageIO.read(up);
            		imageDown = ImageIO.read(down);
            		imageLeft = ImageIO.read(left);
            		imageRight = ImageIO.read(right);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}        	
            }
            
            this.direcciones[0] = imageUp;
            this.direcciones[1] = imageDown;
            this.direcciones[2] = imageLeft;
            this.direcciones[3] = imageRight;
        	
        }
        

    }

}


