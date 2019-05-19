package gui;

import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import search.SupermercadoAgenteState;
import search.SupermercadoEnvironment;
import search.util.Comercio;
import search.util.MapUnit;
import search.util.Producto;
import search.util.ProductoComercio;
import search.util.SupermercadoGoalBasedAgentSimulator;
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
	private static JFrame frame=null;
	
    public static SupermercadoAgenteState agente = null;
    public static SupermercadoEnvironment ambiente = null;
    private static boolean estadoInicial = true;
	
	
	public static void inicializarInterfaz() {
		
		if(frame==null) {
			frame = new JFrame("SupermercadoSolution - IA | Arca - López - Rossini");
			frame.setSize(1080,1055);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		
		
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

        board = new BoardPanel(mapaGrafico,ambiente);
	
        //Panel Principal
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(board);
        
        //Fuentes
        Font superior = new Font("Verdana", Font.BOLD, 24);
        Font titulo = new Font("Arial", Font.BOLD, 20);
		Font elemento = new Font("Arial", Font.PLAIN, 18);
        
		//Panel de Datos
		JPanel datos = new JPanel();
        datos.setLayout(new GridLayout(3,0));
        
        	//Panel de Lista de Productos
	        JPanel listaProductos = new JPanel();
	        listaProductos.setLayout(new BoxLayout(listaProductos, BoxLayout.PAGE_AXIS));
	        
	        	JLabel tituloSuperior = new JLabel("SupermercadoSolution    ");
	        	tituloSuperior.setFont(superior);
	        	tituloSuperior.setForeground(new Color(255,127,39));
	        
				JLabel tituloListaProductos = new JLabel("Mi Lista:");
				tituloListaProductos.setFont(titulo);
				tituloListaProductos.setForeground(new Color(0,128,192));
				
				listaProductos.add(tituloSuperior);
				listaProductos.add(new JLabel("\n"));
				listaProductos.add(tituloListaProductos);
				
		        for(Producto pr : agente.getListaProductos().keySet()) {
		        	String s="";
		        	if(agente.getListaProductos().get(pr)) {
		        		s+=" (x)";
		        	}
		        	 JLabel labelProductos = new JLabel(" - " + pr.getNombre().toString()+s);
				     labelProductos.setFont(elemento);
				     listaProductos.add(labelProductos);
		        }
		        
		    //Panel de Matriz de Productos    
		    JPanel matrizProductos = new JPanel();
		    matrizProductos.setLayout(new BoxLayout(matrizProductos, BoxLayout.PAGE_AXIS));	        
			 
			    JLabel tituloMatrizProductos = new JLabel("Productos Disponibles:  ");
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
		
			JButton btnProximoMovimiento = new JButton ("Iniciar Simulación");
			btnProximoMovimiento.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			try {
				
				File imageFile1 = new File("src/gui/drawable/play.png"); 
				BufferedImage img1 = ImageIO.read(imageFile1);
				
				File imageFile2 = new File("src/gui/drawable/next.png"); 
				BufferedImage img2 = ImageIO.read(imageFile2);
				
				if(estadoInicial) btnProximoMovimiento.setIcon(new ImageIcon(img1));
				else btnProximoMovimiento.setIcon(new ImageIcon(img2));
				
				} catch (Exception ex) {
				System.out.println(ex);
			}
			
			
			btnProximoMovimiento.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(estadoInicial) {
					try {
		    			File imageFile2 = new File("src/gui/drawable/next.png"); 
		    			BufferedImage img2 = ImageIO.read(imageFile2);
		    			btnProximoMovimiento.setIcon(new ImageIcon(img2));
		    		} catch (Exception ex) {
		    			System.out.println(ex);
		    		}
					estadoInicial = false;
					btnProximoMovimiento.setText("Próximo Movimiento");
					/*ambiente.setUbicacionAgente(new Point(0,0));*/
					
			  		/*frame.invalidate();
					frame.validate();
					frame.repaint();*/
					
					SupermercadoGoalBasedAgentSimulator.stop=false;

					
		    	}
				else {
		    		try {
		    			File imageFile1 = new File("src/gui/drawable/play.png"); 
		    			BufferedImage img1 = ImageIO.read(imageFile1);
		    			btnProximoMovimiento.setIcon(new ImageIcon(img1));
		    		} catch (Exception ex) {
		    			System.out.println(ex);
		    		}
		    		estadoInicial = true;
		    		btnProximoMovimiento.setText("Iniciar Simulación");
		    		//ambiente.setUbicacionAgente(new Point(8,0));

		    		
		    		/*frame.invalidate();
					frame.validate();
					frame.repaint();*/
		    		
		    		SupermercadoGoalBasedAgentSimulator.stop=false;

				}
		    	
		      }
		    });
			
			JButton btnAgregarProductos = new JButton("Añadir Productos a Mi Lista");
			btnAgregarProductos.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnAgregarProductos.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  JTextField nombre_producto = new JTextField();
		    	  final JComponent[] inputs = new JComponent[] {
		    	          new JLabel("Nombre del Producto"),
		    	          nombre_producto
		    	  };
		    	  int codigo_producto = ambiente.getListaProductos().size()+1;
		    	  int result = JOptionPane.showConfirmDialog(null, inputs, "Nuevo producto", JOptionPane.PLAIN_MESSAGE);
		    	  if (result == JOptionPane.OK_OPTION) {
			    	  ambiente.getListaProductos().add(new Producto(codigo_producto,nombre_producto.getText()));
			    	  frame.dispose();
			    	  actualizarInterfaz(agente,ambiente);
		    	  }
		    	  	  
		      }
		    });
			
			JButton btnAgregarProductosDisponibles = new JButton("Agregar Productos Disponibles");
			btnAgregarProductosDisponibles.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnAgregarProductosDisponibles.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        String[] lista_comercios = new String[ambiente.getComercios().size()];
		        for(int i = 0; i < ambiente.getComercios().size(); i++) {
		        	lista_comercios[i] = ambiente.getComercios().get(i).getNombre();
		        }
		        
		    	JComboBox cmbComercios = new JComboBox(lista_comercios);
		    	JTextField nombre_producto = new JTextField();
		    	JTextField codigo_producto = new JTextField();
				JTextField precio_producto = new JTextField();
		    	  
				final JComponent[] inputs = new JComponent[] {
		    	          new JLabel("Seleccione el Comercio"),
		    	          cmbComercios,
		    	          new JLabel("Nombre del Producto"),
		    	          nombre_producto,
		    	          new JLabel("Codigo del Producto"),
		    	          codigo_producto,
		    	          new JLabel("Costo del Producto"),
		    	          precio_producto,
				};
				
				int result = JOptionPane.showConfirmDialog(null, inputs, "Agregar productos disponibles", JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					Comercio comercioSeleccionado = ambiente.getComercios().get(cmbComercios.getSelectedIndex());
		    		Producto nuevoProducto = new Producto(Integer.parseInt(codigo_producto.getText()),nombre_producto.getText());
		    		ProductoComercio nuevo = new ProductoComercio(nuevoProducto,comercioSeleccionado,Double.parseDouble(precio_producto.getText()));
		    		ambiente.getMatrizProductoComercio().add(nuevo);
			    	frame.dispose();
			    	actualizarInterfaz(agente,ambiente);
		    	}
				
		      }
		    });
			
			JButton btnAgregarComercios = new JButton("Agregar Supermercado");
			btnAgregarComercios.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnAgregarComercios.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  ArrayList<Point> manzanas = new ArrayList<Point>();
		    	  		    	  
		    	  for (int i = 0; i < ambiente.getMapa().length; i++) {
		                for (int j = 0; j < ambiente.getMapa()[i].length; j++) {
		                	if(ambiente.getMapa()[i][j].getTipo().equals(TipoEnum.MANZANA) || ambiente.getMapa()[i][j].getTipo().equals(TipoEnum.SUPERMERCADOCERRADO)) {
		                		manzanas.add(new Point(i,j));
		                	}
		                }
		          }
		    	  
		    	  String[] lista_manzanas = new String[manzanas.size()];
		    	  for(int i = 0; i < manzanas.size(); i++) {
		    		  lista_manzanas[i] = "(" + manzanas.get(i).x + ";" + manzanas.get(i).y + ")";
		    	  }
		    	  
		    	  JTextField nombre_comercio = new JTextField();
		    	  JTextField codigo_comercio = new JTextField();
		    	  JComboBox cmbManzanas = new JComboBox(lista_manzanas);
		    	  JTextField nombre_producto = new JTextField();
		    	  JTextField codigo_producto = new JTextField();
		    	  JTextField precio_producto = new JTextField();
		    	  
		    	  final JComponent[] inputs = new JComponent[] {
		    			  new JLabel("Codigo del Comercio"),
		    	          codigo_comercio,
		    			  new JLabel("Nombre del Comercio"),
		    	          nombre_comercio,
		    	          new JLabel("Ubicacion"),
		    	          cmbManzanas,
		    	          new JLabel("Nombre del Producto"),
		    	          nombre_producto,
		    	          new JLabel("Codigo del Producto"),
		    	          codigo_producto,
		    	          new JLabel("Costo del Producto"),
		    	          precio_producto,
		    	  };
		    	  
		    	  int result = JOptionPane.showConfirmDialog(null, inputs, "Nuevo comercio", JOptionPane.PLAIN_MESSAGE);
		    	  
		    	  if (result == JOptionPane.OK_OPTION) {
		    		  int codigoComercio = Integer.parseInt(codigo_comercio.getText());
		    		  String nombreComercio = nombre_comercio.getText();
		    		  Point ubicacionComercio = manzanas.get(cmbManzanas.getSelectedIndex());
		    		  Comercio nuevoComercio = new Comercio(codigoComercio,nombreComercio,ubicacionComercio);
		    		  Producto nuevoProducto = new Producto(Integer.parseInt(codigo_producto.getText()),nombre_producto.getText());
		    		  ProductoComercio nuevo = new ProductoComercio(nuevoProducto,nuevoComercio,Double.parseDouble(precio_producto.getText()));
		    		  ambiente.getMatrizProductoComercio().add(nuevo);
		    		  agente.getMapa()[ubicacionComercio.x][ubicacionComercio.y].setTipo(TipoEnum.SUPERMERCADO);
			    	  frame.dispose();
			    	  actualizarInterfaz(agente,ambiente);
		    	  }
		    	  
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
        
        contenedor.add(datos,BorderLayout.LINE_END);
        
        frame.setContentPane(contenedor);     
             
        frame.setVisible(true);
        
        
	}
	
	public static void actualizarInterfaz(SupermercadoAgenteState agenteR, SupermercadoEnvironment ambienteR) {
		
		agente=agenteR;
		ambiente=ambienteR;
		
		inicializarInterfaz();
		
	}
		
	 private static class BoardPanel extends JPanel {

	        Square[][] squares;
	        SupermercadoEnvironment ambiente;

	        public BoardPanel(final Square[][] squares, SupermercadoEnvironment ambiente) {

	            this.squares = squares;
	            this.ambiente = ambiente;
	            setToolTipText("");

	        }
	        
	        @Override
	        public String getToolTipText(MouseEvent event) {
	        	
	        	int x = event.getPoint().x;
	        	int y = event.getPoint().y;
	        	
	        	x = x/70;
	        	y = y/60;
	        	
	        	if(x<squares.length && y<squares[0].length) {
	        		return squares[x][y].getMapUnit().getTipo().toString()+" @ ("+x+";"+y+")";
	        	} else return null;
	        	
	        }

	        @Override
	        public void paintComponent(final Graphics g) {
	        	
	            super.paintComponent(g);

	            int width = 700;
	            int height = 600;

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
	    	            
	    	            //Borde del agente
	    	            int agenteX = ambiente.getUbicacionAgente().x;
	    	            int agenteY = ambiente.getUbicacionAgente().y;
	    	            if(agenteX==i && agenteY==j) {
		                    Graphics2D bordeAgente = this.crearBorde(g,3,Color.BLUE);
		    	            borde.drawRect(x, y, width / squares.length-2, height / squares.length-2);
	    	            }
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
        		this.setBackground(new Color(153,217,234));
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


