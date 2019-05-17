package search.util;

import java.awt.Point;

public class Comercio {

	private int id;
	private String nombre;
	private Point ubicacion;
	
	public Comercio () {
		
	}
	
	public Comercio(int id, String nombre, Point ubicacion) {
		this.id=id;
		this.nombre=nombre;
		this.ubicacion=ubicacion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Point getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Point ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String toString() {
		
		return "<"+id+","+nombre+",("+ubicacion.x+","+ubicacion.y+")>";
		
	}
	
	public Comercio clone() {
		
		Comercio c = new Comercio();
		c.setId(id);
		c.setNombre(new String(nombre));
		c.setUbicacion(new Point(ubicacion.x,ubicacion.y));
		return c;
	
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Comercio))
			return false;
		Comercio other = (Comercio) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (ubicacion == null) {
			if (other.ubicacion != null)
				return false;
		} else if (!ubicacion.equals(other.ubicacion))
			return false;
		return true;
	}
	
	
	
	
	
}
