package ThisIsMyHouse;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Dimension {
		private float x;
		private float y;
		private Image image;
		public static final int WIDTH = 150;
		public static final int HEIGHT = 145;
		
		
		public Dimension(float x, float y) throws SlickException {
		    this.x = x;
		    this.y = y;
		    image = new Image("res/Other/dimension4.png");
		}
		
		public void render() {
			image.draw(x,y);
		}
	
}
