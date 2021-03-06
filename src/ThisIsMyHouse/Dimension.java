package ThisIsMyHouse;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Dimension {
		public float x;
		public float y;
		private Image image;
		public static final int WIDTH = 120;
		public static final int HEIGHT = 140;
		
		
		public Dimension(float x, float y) throws SlickException {
		    this.x = x;
		    this.y = y;
		    image = new Image("res/Other/dimension4.png");
		}
		
		public void render() {
			image.draw(x,y);
			image.rotate(+0.3f);
		}
	
}
