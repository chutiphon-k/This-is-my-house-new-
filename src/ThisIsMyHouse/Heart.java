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
	
	public Heart(float x, float y){
	    this.x = x;
	    this.y = y;
	    try {
			image = new Image("res/Heart/3Heart.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public boolean CheckHeart(){
		if(i==0){
		return false;
		}
		return true;
	}
}
