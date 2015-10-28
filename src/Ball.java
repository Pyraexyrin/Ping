
public class Ball extends PingItemAbstract {

	public Ball(int owner){
		super(owner);
		this.x = Pong.getPongSizeX()/2;
		this.y = Pong.getPongSizeY()/2;
		this.dx = 2;
		this.dy = 2;
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
