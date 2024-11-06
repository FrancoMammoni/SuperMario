package Utils;
import Enemigos.*;
import PowerUps.*;

public interface Visitor {
	
	public void visit(Enemigo k);
	
	public void visit(PowerUp b);
	
}
