/**
 * Starting point of the Pong application
 */
public class Main  {
	public static void main(String[] args) {
		Pong pong = new Pong(4);
		Window window = new Window(pong);
		window.displayOnscreen();
	}
}
