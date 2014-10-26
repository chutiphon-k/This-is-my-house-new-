package ThisIsMyHouse;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Heart {
	private float x;
	private float y;
	private Image image;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 60;
	public static int Heart_Amount = 5;
	
	public Heart(float x, float y){
	    this.x = x;
	    this.y = y;
	    try {
			image = new Image("res/Heart/heart5.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render() {
		image.draw(x,y);
	}
	
	public void update() throws SlickException{
		--Heart_Amount;
		if(Heart_Amount>=0){
			image.destroy();
			image = new Image("res/Heart/heart"+Heart_Amount+".png");
		}	
	}
	
	public boolean CheckHeart(){
		if(Heart_Amount==0){
			return false;
		}
		return false;
	}
}
