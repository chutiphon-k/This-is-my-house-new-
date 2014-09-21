package ThisIsMyHouse;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Podium {
	private float x;
	private float y;
	private Image image;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 30;
	
	
	public Podium(float x, float y) throws SlickException {
	    this.x = x;
	    this.y = y;
	    image = new Image("res/Other/podium.png");
	}
	
	public void render() {
		image.draw(x,y);
	}
}
