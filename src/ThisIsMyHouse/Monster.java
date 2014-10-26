package ThisIsMyHouse;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Monster {
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float vjump;
	private float vjumpStart;
	private Character character;
	private String Direction;
	private int PointJump=1;
	private Random random = new Random();
	private Shape rec;
	private Image image;
	public static final int WIDTH = 80;
	public static final int HEIGHT = 65;
	public static int score = 0;
	
	public Monster() throws SlickException {
	}
	
	public Monster(float vx , float vy) throws SlickException {
		RandomPosition();
	    this.vx = vx;
	    this.vy = vy;
	    this.vjumpStart = vy;
	    this.vjump = RandomJumpHeight();
	    Direction = RandomMovement();
	    rec = new Rectangle(x,y,80,65);
	    image = new Image("res/Monster/slime0R.png");
	    RandomVX();
	}
	
	public void render(Graphics g) throws SlickException {
		if(Direction == "L"){
			image = new Image("res/Monster/slime0L.png");
		}
		if(Direction == "R"){
			image = new Image("res/Monster/slime0R.png");
		}
		image.draw(x,y);
	}
	
	public void update(GameContainer c){
		rec.setLocation(x, y);
		Movement();
		jump();
		collider();
	}
	
	public void Movement(){
		if(CheckAreaBottom(y) && CheckAreaBottom(Character.y)){
			Direction = RandomMovement();
			if(x<=character.x){
				Direction = "R";
				MoveRight();
			}
			if(x>character.x){
				Direction = "L";
				MoveLeft();
			}	
		}
		
		else if(CheckAreaMiddle(y) && CheckAreaMiddle(Character.y) && Character.x<=Podium.WIDTH && x<=Podium.WIDTH){
			Direction = RandomMovement();
			if(Character.x<=Podium.WIDTH && x<=Podium.WIDTH){
				if(x<=character.x){
					Direction = "R";
					MoveRight();
				}
				if(x>character.x){
					Direction = "L";
					MoveLeft();
				}
			}
		}
		
		else if(CheckAreaMiddle(y) && CheckAreaMiddle(Character.y) && Character.x>=GameMain.GAME_WIDTH - Podium.WIDTH - Character.WIDTH && x>=GameMain.GAME_WIDTH - Podium.WIDTH ){
			Direction = RandomMovement();
			if(x<=character.x){
				Direction = "R";
				MoveRight();
			}
			if(x>character.x){
				Direction = "L";
				MoveLeft();
			}
		}
		
		else if(CheckAreaTop(y) && CheckAreaTop(Character.y)){
			Direction = RandomMovement();
			if(x<=character.x){
				Direction = "R";
				MoveRight();
			}
			if(x>character.x){
				Direction = "L";
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
			if(CheckAreaBottom(y)){
				x -=vx;
			}
			
			if(CheckAreaMiddle(y)&& (x<=Podium.WIDTH || x>=GameMain.GAME_WIDTH - Podium.WIDTH)){
				x -=vx;
			}
			if(CheckAreaMiddle(y) && (x<GameMain.GAME_WIDTH - Podium.WIDTH && x>=GameMain.GAME_WIDTH - Podium.WIDTH - vx)){
				x = GameMain.GAME_WIDTH - Podium.WIDTH;
				Direction = "R";
			}
			
			if(CheckAreaTop(y) && (x>=GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 && x<=GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - WIDTH)){
				x -=vx;
			}
			if(CheckAreaTop(y) && (x<GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 && x>=GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 - vx)){
				x = GameMain.GAME_WIDTH/2 - Podium.WIDTH/2;
				Direction = "R";
			}
		}
		else{
			x=0;
			Direction = "R";
			}
		}

	public void MoveRight() {
		if(x<GameMain.GAME_WIDTH-WIDTH){
			if(CheckAreaBottom(y)){
				x +=vx;
			}
			
			if(CheckAreaMiddle(y) && (x<=Podium.WIDTH || x>=GameMain.GAME_WIDTH - Podium.WIDTH)){
				x +=vx;
			}
			if(CheckAreaMiddle(y) && x>Podium.WIDTH - WIDTH && x<=Podium.WIDTH - WIDTH + vx){
				x = Podium.WIDTH - WIDTH;
				Direction = "L";
			}
			
			if(CheckAreaTop(y) && (x>=GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 && x<=GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - WIDTH)){
				x +=vx;
			}
			if(CheckAreaTop(y) && (x>GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - WIDTH && x<=GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - WIDTH + vx)){
				x = GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - WIDTH;
				Direction = "L";
			}
		}
		else{
			x = GameMain.GAME_WIDTH-WIDTH;
			Direction = "L";
		}
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
		if(ColliderWithPodiumUp()){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp - HEIGHT;
			vy = 0;
			PointJump = 1;
		}
		if(ColliderWithPodiumUpCenter()){
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
	
	public void RandomVX(){
		int n = random.nextInt(90)+1;
		if(n>=80){
			vx =  GameMain.Monster_MOVE_VX - 0.25f;
			}
		else if(n>=60){
			vx = GameMain.Monster_MOVE_VX - 0.5f;
		}
		else if(n>=40){
			vx = GameMain.Monster_MOVE_VX;
		}
		else if(n>=20){
			vx = GameMain.Monster_MOVE_VX + 0.25f;
		}
		else{
			vx = GameMain.Monster_MOVE_VX + 0.5f;
		}
	}
	
	public boolean CheckAreaBottom(float y){
		if(y<=GameMain.GAME_HEIGHT_ASSUM && y>=GameMain.GAME_HEIGHT_ASSUM-GameMain.DistanceBottomAndPodiumDown ){
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
		if(y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter && y>=0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean CheckMonIntersectsChar(){
		if((this.rec).intersects(Character.rec)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void RandomPosition(){
		int n = random.nextInt(100)+1;
		if(n>80){
			x = 0;
			y = GameMain.GAME_HEIGHT_ASSUM-Dimension.HEIGHT/2;
		}
		else if(n>=60){
			x = 0;
			y = GameMain.GAME_HEIGHT_ASSUM-GameMain.DistanceBottomAndPodiumUp - Dimension.HEIGHT/2;
		}
		else if(n>=40){
			x = GameMain.GAME_WIDTH/2;
			y = GameMain.GAME_HEIGHT_ASSUM-GameMain.DistanceBottomAndPodiumUpCenter - Dimension.HEIGHT/2;
		}
		else if(n>=20){
			x = GameMain.GAME_WIDTH - Dimension.WIDTH/2;
			y = GameMain.GAME_HEIGHT_ASSUM-GameMain.DistanceBottomAndPodiumUp - Dimension.HEIGHT/2;
		}
		else{
			x = GameMain.GAME_WIDTH - Dimension.WIDTH/2;
			y = GameMain.GAME_HEIGHT_ASSUM-Dimension.HEIGHT/2;
		}
	}
	
	public void DestroyMonster(){
		RandomPosition();
	}
}