package Grafica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Juego.Partida;

public class InputListener implements KeyListener {

	protected boolean 			upPressed;
	protected boolean 			leftPressed;
	protected boolean 			rightPressed;
	protected boolean 			isSpacePressed;

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent evento) {
		int idKey = evento.getKeyCode();
		
		if(idKey == KeyEvent.VK_W) {
			this.upPressed = true;
		} 
		
		if(idKey == KeyEvent.VK_A) {
			this.leftPressed = true;
			
		}
		
		if(idKey == KeyEvent.VK_D) {
			this.rightPressed = true;
		}
		
		if(idKey == KeyEvent.VK_SPACE) {
			this.isSpacePressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent evento) {
		int idKey = evento.getKeyCode();
		
		if(idKey == KeyEvent.VK_W) {
			this.upPressed = false;
		} 
		
		if(idKey == KeyEvent.VK_A) {
			this.leftPressed = false;
		}
		
		if(idKey == KeyEvent.VK_D) {
			this.rightPressed = false;
		}
		
		if(idKey == KeyEvent.VK_SPACE) {
			this.isSpacePressed = false;
		}
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	public void setSpacePressed(boolean isSpacePressed) {
		this.isSpacePressed = isSpacePressed;
	}

	public boolean isUpPressed() {
		return this.upPressed;
	}

	public boolean isLeftPressed() {
		return this.leftPressed;
	}

	public boolean isRightPressed() {
		return this.rightPressed;
	}
	
	public boolean isSpacePressed() {
		return isSpacePressed;
	}

}