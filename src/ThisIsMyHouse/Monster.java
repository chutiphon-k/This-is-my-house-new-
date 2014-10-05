package ThisIsMyHouse;

import java.util.Random;

import org.newdawn.slick.Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Monster {
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float vjump;
	private float vjumpStart;
	private Character character;
	private Image image;
	public static final int WIDTH = 80;
	public static final int HEIGHT = 65;
	private String Direction;
	private int PointJump=1;
	private Random random = new Random();
	
	
	public Monster(float x, float y , float vx , float vy) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.vx = vx;
	    this.vy = vy;
	    this.vjumpStart = vy;
	    this.vjump = RandomJumpHeight();
	    Direction = RandomMovement();
	    image = new Image("res/Monster/slime0.png");
	}
	
	public void render() {
		image.draw(x,y);
	}
	
	public void update(){
		Movement();
		jump();
		collider();
	}
	
	public void Movement(){
		if(CheckAreaBottom(y)==true && CheckAreaBottom(Character.y)==true){
			Direction = RandomMovement();
			if(x<=character.x){
				MoveRight();
			}
			if(x>character.x){
				MoveLeft();
			}	
		}
		else if(CheckAreaMiddle(y)==true && CheckAreaMiddle(Character.y)==true){
			Direction = RandomMovement();
			if(x<=character.x){
				MoveRight();
			}
			if(x>character.x){
				MoveLeft();
			}
		}
		else{
			if(Direction == "L"){
				MoveLeft();
			}
			if(Direction == "R"){
				MoveRight();
			}
		}					
	}
	
	
	public void MoveLeft() {
		if(x>0){
			if(CheckAreaBottom(y)==true){
				x -=vx;
			}
			if(CheckAreaMiddle(y)==true && x<GameMain.GAME_WIDTH/2 - Dimension.WIDTH/2){
				x -=vx;
			}
//			if(CheckAreaMiddle(y)==true && x>=GameMain.GAME_WIDTH/2 - Dimension.WIDTH/2 && x<=GameMain.GAME_WIDTH/2 - Dimension.WIDTH/2 +10){
//				x = GameMain.GAME_WIDTH/2;
//			}
		}
		else{
			if(x<=0){
			x =0;
			Direction = "R";
			}
		}
		
//		if(x>0){
//			x -=vx;
//		}
//		else{
//			if(x<=0){
//			x =0;
//			Direction = "R";
//			}
//		}
	}

	public void MoveRight() {
		if(x<GameMain.GAME_WIDTH-WIDTH){
			if(CheckAreaBottom(y)==true){
				x +=vx;
			}
			if(CheckAreaMiddle(y)==true && x<Podium.WIDTH){
				x +=vx;
			}
			if(CheckAreaMiddle(y)==true && x>=Podium.WIDTH - WIDTH && x<=Podium.WIDTH - WIDTH +2){
				x = Podium.WIDTH - WIDTH;
				Direction = "L";
			}
		}
		else{
			x = GameMain.GAME_WIDTH-WIDTH;
			Direction = "L";
		}
		
		
		
//		if(x<GameMain.GAME_WIDTH-WIDTH){
//			x +=vx;
//		}
//		else{
//			x = GameMain.GAME_WIDTH-WIDTH;
//			Direction = "L";
//		}
	}
	
	public void jump(){
		jumpUp();
		vjump = RandomJumpHeight();
		y +=vy;
		if(y<GameMain.GAME_HEIGHT_ASSUM-HEIGHT){
			jumpDown();
		}
		if(y>=GameMain.GAME_HEIGHT_ASSUM-HEIGHT){
			y = GameMain.GAME_HEIGHT_ASSUM-HEIGHT;
			vy=0;
			PointJump = 1;
		}
		if(y<=0){
			y = 0;
			jumpDown();
		}
	}
	
	public void jumpUp() {
		if(PointJump==1){
			vy = -vjump;
			PointJump = 0;
		}
	}
	
	public void jumpDown() {
		vy += GameMain.GMonster;
		}
	
	public boolean ColliderWithPodiumUp(){
		if((x>=0 && x<=Podium.WIDTH-25) 
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp - HEIGHT/1.2 && y>= GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp - HEIGHT)){
			return true;
		}
		if((x>=GameMain.GAME_WIDTH+25 - Podium.WIDTH - Character.WIDTH && x<=GameMain.GAME_WIDTH) 
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp - HEIGHT/1.2 && y>= GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp - HEIGHT)){
			return true;
		}
		return false;
	}
	
	public boolean ColliderWithPodiumUpCenter(){
		if((x>=GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 - Character.WIDTH +25 && x<=GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - 25) 
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter - HEIGHT/1.2  && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter  - HEIGHT)){
			return true;
		}
		return false;
	}
	
	public void collider() {
		if(ColliderWithPodiumUp()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp - HEIGHT;
			vy = 0;
			PointJump = 1;
		}
		if(ColliderWithPodiumUpCenter()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter - HEIGHT;
			vy = 0;
			PointJump = 1;
		}
	}
	
	public String RandomMovement(){
		int n = random.nextInt(100)+1;
		if(n<=50){
			return "R";
		}
		else{
			return "L";
		}
	}
		
	public float RandomJumpHeight(){
		int n = random.nextInt(90)+1;
		if(n<=30){
			return vjumpStart-2;
			}
		else if(n<=60){
			return vjumpStart;
		}
		else{
			return vjumpStart+2;
			}
	}
	
	public boolean CheckAreaBottom(float y){
		if(y<=GameMain.GAME_HEIGHT_ASSUM && y>=GameMain.GAME_HEIGHT_ASSUM-GameMain.DistanceBottomAndPodiumDown){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean CheckAreaMiddle(float y){
		if(y<=GameMain.GAME_HEIGHT_ASSUM-GameMain.DistanceBottomAndPodiumUp && y>=GameMain.GAME_HEIGHT_ASSUM-GameMain.DistanceBottomAndPodiumDownCenter){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean CheckAreaTop(float y){
		if(y<=GameMain.DistanceBottomAndPodiumUpCenter && y>=0){
			return true;
		}
		else{
			return false;
		}
	}
}

//|| character.y<=GameMain.DistanceBottomAndPodiumUp - Character.HEIGHT && character.y>=GameMain.DistanceBottomAndPodiumDownCenter
//|| character.y<=GameMain.DistanceBottomAndPodiumUpCenter - Character.HEIGHT && character.y>=0
