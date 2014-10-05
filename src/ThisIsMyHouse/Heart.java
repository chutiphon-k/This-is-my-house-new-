package ThisIsMyHouse;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Heart {
	private float x;
	private float y;
	private Image image;
	public static final int WIDTH = 180;
	public static final int HEIGHT = 60;
	public static int i=3;
	
	
	public Heart() throws SlickException {
	}
	
	public Heart(float x, float y) throws SlickException {
	    this.x = x;
	    this.y = y;
	    image = new Image("res/Heart/3Heart.png");
	}
	
	public void render() {
		image.draw(x,y);
	}
	
	public void update() throws SlickException{
		--i;
		if(i>=0){
			image.destroy();
			image = new Image("res/Heart/" + i + "Heart.png");
		}
		
	}
}
