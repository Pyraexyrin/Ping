
public class Paddle extends PingItemAbstract {

	public static int PADDLE_SPEED = 4;
	
	public Paddle(int owner, int x, int y, int dx, int dy){
		super(owner, x, y, dx, dy);
	}
	
	public void setHeight(int height){
		this.height=height;
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public void move(){
		System.out.println("Show me your moves, paddle !\n");
		this.x += this.dx;
		this.y += this.dy;
		if (this.x < 0)
			this.x = 0;
		if (this.x > Pong.getPongSizeX() - this.width)
			this.x = Pong.getPongSizeX() - this.width;
		if (this.y < 0)
			this.y = 0;
		if (this.y > Pong.getPongSizeY() - this.height)
			this.y = Pong.getPongSizeY() - this.height;
	}
	
}
