package ThisIsMyHouse;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Character {
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float vjump;
	private Image image;
	public static final int WIDTH = 105;
	public static final int HEIGHT = 140;
	
	public Character(float x, float y , float vx , float vy) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.vx = vx;
	    this.vy = vy;
	    this.vjump = vy;
	    image = new Image("res/Character/Character0.png");
	}
	
	public void render() {
		image.draw(x,y);
	}
	
	public void update() {
		
	}

	public void MoveLeft() {
		if(x>0){
			x -=vx;
		}
		else{
			x =0;
		}
	}

	public void MoveRight() {
		if(x<(GameMain.GAME_WIDTH-WIDTH)){
			x +=vx;
		}
		else{
			x = GameMain.GAME_WIDTH-WIDTH;
		}
	}

	public void jump() {
		
	}
	
	
}
