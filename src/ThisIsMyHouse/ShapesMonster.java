package ThisIsMyHouse;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ShapesMonster {
	private Shape rec;
	public static Shape rec0;
	
	public ShapesMonster(float x, float y,float xx,float yy) throws SlickException {
	    rec = new Rectangle(x,y,xx,yy);
	}
	
	public void render(Graphics g) {
	    g.setColor( Color.blue );
	    g.draw(rec);
		if((rec).intersects(ShapesCharacter.rec))
		{
			g.setColor(Color.red);
		}
		g.draw(ShapesCharacter.rec);
	}
	
	public void update() {
	    rec.setX(Monster.getX);
		rec.setY(Monster.getY);
	}
}
