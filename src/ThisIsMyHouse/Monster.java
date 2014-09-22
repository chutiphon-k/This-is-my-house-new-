package ThisIsMyHouse;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Monster {
	private float x;
	private float y;
	private float vx;
	private float vy;
	private Random random = new Random();
	private Image image;
	public static final int WIDTH = 120;
	public static final int HEIGHT = 95;
	public static String Direction = "R";
	
	
	
	public Monster(float x, float y , float vx , float vy) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.vx = vx;
	    this.vy = vy;
	    image = new Image("res/Monster/slime12.png");
	}
	
	public void render() {
		image.draw(x,y);
	}
	
	public void update(){
		Movement();
	}
	
	public void MoveLeft() {
		if(x>0){
			x -=vx;
		}
		else{
			x =0;
			Direction = "R";
		}
	}

	public void MoveRight() {
		if(x<(GameMain.GAME_WIDTH-WIDTH)){
			x +=vx;
		}
		else{
			x = GameMain.GAME_WIDTH-WIDTH;
			Direction = "L";
		}
	}
	
	public void Movement(){
		if(Direction == "L"){
			MoveLeft();
		}
		if(Direction == "R"){
			MoveRight();
		}
	}
}
