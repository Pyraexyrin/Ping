
public class Paddle extends PingItemAbstract {

	private char firstTrigger;
	private char secondTrigger;
	
	public Paddle(int owner){
		super(owner);
		this.x = 0;
		this.y = 0;
		this.dx = 0;
		this.dy = 0;
		switch(owner){
		case 0:
		}
	}
	
	public void setHeight(int height){
		this.height=height;
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public void move(){
		System.out.println("Show me your moves, paddle !\n");
		//this.x += this.dx;
		this.y += this.dy;
		//if (this.x < 0)
		//	this.x = 0;
		//if (this.x > Pong.getPongSizeX() - this.width)
		//	this.x = Pong.getPongSizeX() - this.width;
		if (this.y < 0)
			this.y = 0;
		if (this.y > Pong.getPongSizeY() - this.height)
			this.y = Pong.getPongSizeY() - this.height;
	}
	
}
