package search.util;

public class MapUnit {

	private TipoEnum Tipo;
	private int Tiempo;
	private int Costo;
	private boolean Up;
	private boolean Down;
	private boolean Left;
	private boolean Right;
	
	public TipoEnum getTipo() {
		return Tipo;
	}
	public void setTipo(TipoEnum tipo) {
		Tipo = tipo;
	}
	public int getTiempo() {
		return Tiempo;
	}
	public void setTiempo(int tiempo) {
		Tiempo = tiempo;
	}
	public int getCosto() {
		return Costo;
	}
	public void setCosto(int costo) {
		Costo = costo;
	}
	public boolean isUp() {
		return Up;
	}
	public void setUp(boolean up) {
		Up = up;
	}
	public boolean isDown() {
		return Down;
	}
	public void setDown(boolean down) {
		Down = down;
	}
	public boolean isLeft() {
		return Left;
	}
	public void setLeft(boolean left) {
		Left = left;
	}
	public boolean isRight() {
		return Right;
	}
	public void setRight(boolean right) {
		Right = right;
	}
	public MapUnit clone() {
		
		MapUnit clon = new MapUnit();
		
		clon.setCosto(this.Costo);
		clon.setTiempo(this.Tiempo);
		clon.setUp(this.Up);
		clon.setDown(this.Down);
		clon.setLeft(this.Left);
		clon.setRight(this.Right);
		clon.setTipo(this.Tipo);
		
		return clon;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MapUnit))
			return false;
		MapUnit other = (MapUnit) obj;
		if (Costo != other.Costo)
			return false;
		if (Down != other.Down)
			return false;
		if (Left != other.Left)
			return false;
		if (Right != other.Right)
			return false;
		if (Tiempo != other.Tiempo)
			return false;
		if (Tipo != other.Tipo)
			return false;
		if (Up != other.Up)
			return false;
		return true;
	}
	
	
}
