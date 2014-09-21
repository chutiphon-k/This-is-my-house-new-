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
	public static final int WIDTH_Collider = 90;
	public static final int HEIGHT_Collider = 130;
	public static int PointJump;
	
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
		y +=vy;
		if(y<GameMain.GAME_HEIGHT-HEIGHT){
			jumpDown();
		}
		if(y>=GameMain.GAME_HEIGHT-HEIGHT){
			y = GameMain.GAME_HEIGHT-HEIGHT;
			vy=0;
			PointJump = 1;
		}
		if(y<=0){
			y = 0;
			jumpDown();
		}
		if(x>=Podium.WIDTH + 25 - Character.WIDTH && x<=GameMain.GAME_WIDTH - Podium.WIDTH - 25 
				&& (y<GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumDown - HEIGHT/2 && y> GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUp - HEIGHT)){
			PointJump = 0;
		}
		collider();
		
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

	public void jumpUp() {
		if(PointJump==1){
			vy = -vjump;
			PointJump = 0;
		}
	}
	
	public void jumpDown() {
		vy += GameMain.G;
		}
	
	public boolean ColliderWithPodiumDown(){
		if((x>=0 && x<=Podium.WIDTH-25) && (y<=GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumDown && y> GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUp)){
			return true;
		}
		if((x>=GameMain.GAME_WIDTH+25 - Podium.WIDTH - Character.WIDTH && x<=GameMain.GAME_WIDTH) && (y<=GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumDown && y> GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUp)){
			return true;
		}
		return false;
	}
	
	public boolean ColliderWithPodiumUp(){
		if((x>=0 && x<=Podium.WIDTH-25) 
				&& (y<=GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUp - HEIGHT/1.2 && y>= GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUp - HEIGHT)){
			return true;
		}
		if((x>=GameMain.GAME_WIDTH+25 - Podium.WIDTH - Character.WIDTH && x<=GameMain.GAME_WIDTH) 
				&& (y<=GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUp - HEIGHT/1.2 && y>= GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUp - HEIGHT)){
			return true;
		}
		return false;
	}
	
	public boolean ColliderWithPodiumDownCenter(){
		if((x>=GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 - Character.WIDTH + 25 && x<=GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - 25) 
				&& (y<=GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumDownCenter && y> GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUpCenter)){
			return true;
		}
		return false;
	}
	
	public boolean ColliderWithPodiumUpCenter(){
		if((x>=GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 - Character.WIDTH +25 && x<=GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - 25) 
				&& (y<=GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUpCenter - HEIGHT/1.2  && y> GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUpCenter  - HEIGHT)){
			return true;
		}
		return false;
	}
	
	public void collider() {
		if(ColliderWithPodiumDown()==true){
			y = GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumDown;
			jumpDown();
		}
		else if(ColliderWithPodiumUp()==true){
			y = GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUp - HEIGHT;
			vy = 0;
			PointJump = 1;
		}
		if(ColliderWithPodiumDownCenter()==true){
			y = GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumDownCenter;
			jumpDown();
		}
		else if(ColliderWithPodiumUpCenter()==true){
			y = GameMain.GAME_HEIGHT - GameMain.DistanceBottomAndPodiumUpCenter - HEIGHT;
			vy = 0;
			PointJump = 1;
		}
	}
	
}
