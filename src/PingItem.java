
public interface PingItem {
	
	public int getX();
	public int getY();
	
	public int getDX();
	public void setDX(int dx);
	public int getDY();
	public void setDY(int dy);
	
	public int getOwner();
	public void setOwner(int owner);
	
	public int getHeight();
	public void setHeight(int height);
	public int getWidth();
	public void setWidth(int width);
	
	public void move();
	
}
