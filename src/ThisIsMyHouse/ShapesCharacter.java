package ThisIsMyHouse;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ShapesCharacter {
	public static Shape rec;
	
	public ShapesCharacter(float x, float y,float xx,float yy) throws SlickException {
	    rec = new Rectangle(x,y,xx,yy);
	}
	
	public void render(Graphics g) {
	    g.setColor( Color.green );
	    g.draw(rec);
	}
	
	public void update() {
	    rec.setX(Character.x);
		rec.setY(Character.y);
	}
}
