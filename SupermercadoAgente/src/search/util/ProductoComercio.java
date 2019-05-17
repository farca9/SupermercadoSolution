package search.util;

public class ProductoComercio {

	private Producto producto;
	private Double costo;
	private Comercio comercio;
	
	public ProductoComercio() {
		
	}
	
	public ProductoComercio (Producto producto, Comercio comercio, Double costo) {
		this.producto=producto;
		this.comercio = comercio;
		this.costo=costo;
	}
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
	public ProductoComercio clone() {
		
		ProductoComercio clon = new ProductoComercio();
		clon.setProducto(producto.clone());
		clon.setCosto(new Double(costo.doubleValue()));
		clon.setComercio(comercio.clone());
		
		return clon;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProductoComercio))
			return false;
		ProductoComercio other = (ProductoComercio) obj;
		if (comercio == null) {
			if (other.comercio != null)
				return false;
		} else if (!comercio.equals(other.comercio))
			return false;
		if (costo == null) {
			if (other.costo != null)
				return false;
		} else if (!costo.equals(other.costo))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "<"+ producto + " , " + costo + " , " + comercio + "> ";
	}

	
	
	
	
	
	
}
