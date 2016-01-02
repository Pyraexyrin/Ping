

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
	private static Color backgroundColor = new Color(0x99ccff);

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
	public static final int timestep = 20;

	/**
	 * Pixel data buffer for the Pong rendering
	 */
	private Image buffer = null;
	/**
	 * Graphic component context derived from buffer Image
	 */
	private Graphics graphicContext = null;

	public static int getPongSizeX(){
		return SIZE_PONG_X;
	}
	
	public static int getPongSizeY(){
		return SIZE_PONG_Y;
	}
	
	private Paddle paddles[] = {null, null, null, null};
	private Ball balls[] = {null, null, null, null, null, null, null, null, null, null};
	
	private Image ball_image = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("img/ball_default.png"));
	private Image paddle_images[] = {
			Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("img/paddle_left_default.png")), 
			Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("img/paddle_right_default.png")), 
			Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("img/paddle_up_default.png")), 
			Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("img/paddle_down_default.png"))};
	private int keyEvents[][] = {{KeyEvent.VK_Z, KeyEvent.VK_S},
									{KeyEvent.VK_O, KeyEvent.VK_L},
									{KeyEvent.VK_Q, KeyEvent.VK_D},
									{KeyEvent.VK_K, KeyEvent.VK_M}};

	
	public Pong(int nb_players) {
		ImageIcon icon;
		
		icon = new ImageIcon(ball_image);
		this.balls[0] = new Ball(0, Pong.getPongSizeX()/2, Pong.getPongSizeY()/2, -2, -2);
		this.balls[0].setWidth(icon.getIconWidth());
		this.balls[0].setHeight(icon.getIconHeight());

		icon = new ImageIcon(paddle_images[0]);
		this.paddles[0] = new Paddle(0, 0, (Pong.getPongSizeY()-icon.getIconHeight())/2, 0, 0);
		this.paddles[0].setWidth(icon.getIconWidth());
		this.paddles[0].setHeight(icon.getIconHeight());

		if(nb_players > 1){
			icon = new ImageIcon(paddle_images[1]);
			this.paddles[1] = new Paddle (1, Pong.getPongSizeX()-icon.getIconWidth(), (Pong.getPongSizeY()-icon.getIconHeight())/2, 0, 0);
			this.paddles[1].setWidth(icon.getIconWidth());
			this.paddles[1].setHeight(icon.getIconHeight());
		}
		
		if(nb_players > 2){
			icon = new ImageIcon(paddle_images[2]);
			this.paddles[2] = new Paddle (2, (Pong.getPongSizeX()-icon.getIconWidth())/2, 0, 0, 0);
			this.paddles[2].setWidth(icon.getIconWidth());
			this.paddles[2].setHeight(icon.getIconHeight());
		}
		
		if(nb_players > 3){
			icon = new ImageIcon(paddle_images[3]);
			this.paddles[3] = new Paddle (3, (Pong.getPongSizeX()-icon.getIconWidth())/2, Pong.getPongSizeY()-icon.getIconHeight(), 0, 0);
			this.paddles[3].setWidth(icon.getIconWidth());
			this.paddles[3].setHeight(icon.getIconHeight());
		}
		
		this.setPreferredSize(new Dimension(SIZE_PONG_X, SIZE_PONG_Y));
		this.addKeyListener(this);
	}

	/**
         * Proceeds to the movement of the ball and updates the screen
	 */
	public void animate() {
		System.out.println("Did you animate ?\n");
		/* Update ball position */
		for (int i = 0; i<balls.length; i++)
			if (balls[i] != null)
				balls[i].move();
		/* Update racket position */
		for(int i = 0; i<paddles.length; i++)
			if (paddles[i] != null)
				paddles[i].move();
		

		for(int i = 0; i<paddles.length; i++)
			if (paddles[i] != null)
				for (int j = 0; j<balls.length; j++)
					if (balls[j] != null)
						checkIntersection(paddles[i], balls[j]);

		/* And update output */
		updateScreen();
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		int event1;
		int event2;
		for(int i = 0; i<paddles.length; i++){
			if (paddles[i] != null){
				event1 = keyEvents[i][0];
				event2 = keyEvents[i][1];
				if (keyCode == event1) {
					if (i > 1)
						paddles[i].setDX(-Paddle.PADDLE_SPEED);
					else
						paddles[i].setDY(-Paddle.PADDLE_SPEED);
				}
				else if (keyCode == event2){
					if (i > 1)
						paddles[i].setDX(Paddle.PADDLE_SPEED);
					else
						paddles[i].setDY(Paddle.PADDLE_SPEED);
				}
			}
		}
		System.out.println("Got pressed "+e);
	}
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		int event1;
		int event2;
		for(int i = 0; i<paddles.length; i++){
			if (paddles[i] != null){
				event1 = keyEvents[i][0];
				event2 = keyEvents[i][1];
				if (keyCode == event1) {
					paddles[i].setDX(0);
					paddles[i].setDY(0);
				}
				else if (keyCode == event2){
					paddles[i].setDX(0);
					paddles[i].setDY(0);
				}
			}
		}
		System.out.println("Got released "+e);
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

	public boolean checkIntersection(Paddle P, Ball B){
		
		int Bx = B.getX();
		int By = B.getY();
		int Bh = B.getHeight();
		int Bw = B.getWidth();
		
		int Px = P.getX();
		int Py = P.getY();
		int Ph = P.getHeight();
		int Pw = P.getWidth();
		
		int v1 = (Bx) - (Px + Pw);
		int v2 = (Bx + Bw) - (Px);
		int v3 = (By + Bh) - (Py);
		int v4 = (By) - (Py + Ph);
		
		boolean b = true;
		
		if ((v1*v2 < 0) && (v3*v4 < 0)){
			// Il y a collision
			// ATTENTION ! x et y ne sont pas dans le sens habituel :
			// ---------> x
			// ||
			// ||
			// \/ y
			
			// Vecteur MILIEU_paddle -> MILIEU_balle
			int x2 = (Bx + Bh/2) - (Px + Ph/2);
			int y2 = (By + Bw/2) - (Py + Pw/2);
			
			// Cas particulier d'un vecteur vertical, ou nul
			if (x2 == 0)
				b = false;
			
			// S'il n'est pas vertical ou nul, on calcule les coefficients directeurs de deux vecteurs :
			// Le précédent (x2, y2), et la diagonale de la raquette.
			if (b){
				int c1 = Pw / Ph;
				int c2 = y2 / x2;
				b = ((-c1 < c2) && (c2 < c1));
			}
			
			// En les comparant, on connaît de quel côté arrive la balle
			if (b){
				// La balle arrive des côtés
				if (x2 > 0){
					B.setDX( Math.abs(B.getDX()) );
				}
				else {
					B.setDX( - Math.abs(B.getDX()) );
				}
			}
			else {
				// La balle arrive du haut ou du bas
				if (y2 > 0){
					B.setDY( Math.abs(B.getDY()) );
				}
				else {
					B.setDY( - Math.abs(B.getDY()) );
				}
			}
			
			b = false ;
		}
		
		return !b;
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
		for (int i = 0; i<balls.length ; i++)
			if (balls[i] != null)
				graphicContext.drawImage(ball_image, balls[i].getX(), balls[i].getY(), balls[i].getWidth(), balls[i].getHeight(), null);
		
		for (int i = 0; i<paddles.length ; i++)
			if (paddles[i] != null)
				graphicContext.drawImage(paddle_images[i], paddles[i].getX(), paddles[i].getY(), paddles[i].getWidth(), paddles[i].getHeight(), null);
		
		this.repaint();
	}
	
}