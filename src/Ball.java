
public class Ball extends PingItemAbstract {

	public static int BALL_SPEED = 2;
	
	public Ball(int owner, int x, int y, int dx, int dy){
		super(owner, x, y, dx, dy);
	}
	
	public void setHeight(int height){
		this.height = this.width = height;
	}
	
	public void setWidth(int width){
		this.height = this.width = width;
	}
	
	public void move(){
		System.out.println("Try to move ball !\n");
		
		this.x += this.dx;
		this.y += this.dy;
		
		if (this.x < 0)
		{
			this.x = 0;
			this.dx = -this.dx;
		}
		if (this.y < 0)
		{
			this.y = 0;
			this.dy = -this.dy;
		}
		if (this.x > Pong.getPongSizeX() - this.width)
		{
			this.x = Pong.getPongSizeX() - this.width;
			this.dx = -this.dx;
		}
		if (this.y > Pong.getPongSizeY() - this.height)
		{
			this.y = Pong.getPongSizeY() - this.height;
			this.dy = -this.dy;
		}
	}
	
}
