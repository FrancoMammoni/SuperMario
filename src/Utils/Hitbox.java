package Utils;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Hitbox extends Rectangle{
	
	protected int posX;
	protected int posY;
	protected int alto;
	protected int ancho;
	
	public Hitbox(int x, int y, int ancho, int alto) {
		super(x,y,ancho,alto);
	}

}
