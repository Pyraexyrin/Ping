
public abstract class PingItemAbstract implements PingItem {
	
	protected int x;		// Coordonn�e X
	protected int y;		// Coordonn�e Y
	protected int dx;		// Vecteur de d�placement horizontal
	protected int dy;		// Vecteur de d�placement vertical
	protected int owner;	// ID du joueur propri�taire
	
	protected int height;	// Hauteur de l'item
	protected int width;	// Largeur de l'item
	
	///////////////////
	// CONSTRUCTEURS //
	///////////////////
	
	public PingItemAbstract(int owner, int x, int y, int dx, int dy){
		this.owner = owner;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	////////////////
	// ACCESSEURS //
	////////////////
	
	// x et y ne sont accessibles qu'en lecture.
	// Les mouvements sont g�r�s par dx et dy.
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	// dx et dy permettent de g�rer la vitesse et l'orientation.
	// Il permettent de g�rer facilement les rebonds.

	public int getDX(){
		return this.dx;
	}
	
	public void setDX(int dx){
		this.dx = dx;
	}
	public int getDY(){
		return this.dy;
	}
	
	public void setDY(int dy){
		this.dy = dy;
	}
	
	// owner permet d'associer un joueur � un item.
	
	public int getOwner(){
		return this.owner;
	}
	
	public void setOwner(int owner){
		this.owner = owner;
	}
	
	// Les setters sont sp�cifiques � chaque instance
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	/////////////////
	// CANNONIQUES //
	/////////////////
	
	// toString et clone sont inutiles ici.
	
	//////////////
	// METHODES //
	//////////////
	
	
}
