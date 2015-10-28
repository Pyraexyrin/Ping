

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * An Pong is a Java graphical container that extends the JPanel class in
 * order to display graphical elements.
 */
public class Pong extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Constant (c.f. final) common to all Pong instances (c.f. static)
	 * defining the background color of the Pong
	 */
	private static final Color backgroundColor = new Color(0xFF, 0x40, 0);

	/**
	 * Width of pong area
	 */
	private static final int SIZE_PONG_X = 800;
	/**
	 * Height of pong area
	 */
	private static final int SIZE_PONG_Y = 600;
	/**
	 * Time step of the simulation (in ms)
	 */
	public static final int timestep = 10;
	/**
	 * Speed of ball (in pixels per second)
	 */
	public static final int BALL_SPEED = 2;
	/**
	 * Speed of racket (in pixels per second)
	 */
	public static final int RACKET_SPEED = 4;

	/**
	 * Pixel data buffer for the Pong rendering
	 */
	private Image buffer = null;
	/**
	 * Graphic component context derived from buffer Image
	 */
	private Graphics graphicContext = null;

	
	private PingItem balle;
	private PingItem paddle;
	/**
	 * Ball to be displayed
	 */
	private final Image ball;

	/**
	 * One Racket to be displayed
	 */
	private final Image racket;

	public static int getPongSizeX(){
		return SIZE_PONG_X;
	}
	
	public static int getPongSizeY(){
		return SIZE_PONG_Y;
	}
	
	public Pong() {
		ImageIcon icon;

		this.balle = new Ball(0);
		this.paddle = new Paddle(0);
		
		this.ball = Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("img/ball_default.png"));
		icon = new ImageIcon(ball);
		this.balle.setWidth(icon.getIconWidth());
		this.balle.setHeight(icon.getIconHeight());

		this.racket = Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("img/paddle_default.png"));
		icon = new ImageIcon(racket);
		this.paddle.setWidth(icon.getIconWidth());
		this.paddle.setHeight(icon.getIconHeight());

		this.setPreferredSize(new Dimension(SIZE_PONG_X, SIZE_PONG_Y));
		this.addKeyListener(this);
	}

	/**
         * Proceeds to the movement of the ball and updates the screen
	 */
	public void animate() {
		System.out.println("Did you animate ?\n");
		/* Update ball position */
		balle.move();
		/* Update racket position */
		paddle.move();

		/* And update output */
		updateScreen();
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				paddle.setDX(-RACKET_SPEED);
				paddle.setDY(-RACKET_SPEED);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				paddle.setDX(RACKET_SPEED);
				paddle.setDY(RACKET_SPEED);
				break;
			default:
				System.out.println("got press "+e);
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				paddle.setDX(0);
				paddle.setDY(0);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				paddle.setDX(0);
				paddle.setDY(0);
				break;
			default:
				System.out.println("got release "+e);
		}
	}
	public void keyTyped(KeyEvent e) { }

	/*
	 * (non-Javadoc) This method is called by the AWT Engine to paint what
	 * appears in the screen. The AWT engine calls the paint method every time
	 * the operative system reports that the canvas has to be painted. When the
	 * window is created for the first time paint is called. The paint method is
	 * also called if we minimize and after we maximize the window and if we
	 * change the size of the window with the mouse.
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

	/**
	 * Draw each Pong item based on new positions
	 */
	public void updateScreen() {
		if (buffer == null) {
			/* First time we get called with all windows initialized */
			buffer = createImage(SIZE_PONG_X, SIZE_PONG_Y);
			if (buffer == null)
				throw new RuntimeException("Could not instanciate graphics");
			else
				graphicContext = buffer.getGraphics();
		}
		/* Fill the area with blue */
		graphicContext.setColor(backgroundColor);
		graphicContext.fillRect(0, 0, SIZE_PONG_X, SIZE_PONG_Y);

		/* Draw items */
		graphicContext.drawImage(ball, balle.getX(), balle.getY(), balle.getWidth(), balle.getHeight(), null);
		graphicContext.drawImage(racket, paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), null);

		this.repaint();
	}
}
