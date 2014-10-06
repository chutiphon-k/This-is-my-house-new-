package ThisIsMyHouse;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Character {
	public  static float x;
	public  static float y;
	private float vx;
	private float vy;
	private float vjump;
	private Image image;
	private Monster monster = new Monster();
	public static final int WIDTH = 83;
	public static final int WIDTH_Attack = 10;
	public static final int HEIGHT = 110;
	public static int PointJump;
	public static Shape rec;
	public static int Score = 0;
	private Time time = new Time();
	public static boolean m = true;
	public static int Caltime1s = 1;
	public static int Caltime1sMax = Caltime1s;
	
	public Character(float x, float y , float vx , float vy) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.vx = vx;
	    this.vy = vy;
	    this.vjump = vy;
	    rec = new Rectangle(x,y,WIDTH,HEIGHT);
	    image = new Image("res/Character/Character0.png");
	}
	
	public void render(Graphics g) {
		image.draw(x,y);
		g.drawString("Score : "+Score,200,10);
	}
	
	public void update(GameContainer c,int delta) throws SlickException {
		time.update(delta);
		rec.setLocation(x, y);
		Jump();
		collider();
		DelayAttack();
		time.setCurrentTime();
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

	public void jumpUp() throws SlickException {
		image.destroy();
		image = new Image("res/Character/CharacterJump.png");
		if(PointJump==1){
			vy = -vjump;
			PointJump = 0;
		}
	}
	
	public void jumpDown() {
		vy += GameMain.G;
		}
	
	public void Jump() throws SlickException {
		y +=vy;
		if(y<GameMain.GAME_HEIGHT_ASSUM-HEIGHT){
			jumpDown();
		}
		if(y>=GameMain.GAME_HEIGHT_ASSUM-HEIGHT){
			y = GameMain.GAME_HEIGHT_ASSUM-HEIGHT;
			vy=0;
			PointJump = 1;
			if(m==true){
			image.destroy();
			image = new Image("res/Character/Character0.png");
			}
		}
		if(y<=0){
			y = 0;
			jumpDown();
		}
		if(x>=Podium.WIDTH + 25 - Character.WIDTH && x<=GameMain.GAME_WIDTH - Podium.WIDTH - 25 
				&& (y<GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDown  - HEIGHT/2 && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp- HEIGHT)){
			PointJump = 0;
		}
		if(((x<GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 - Character.WIDTH +25 && x>=0) || (x>GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - 25 && x<=GameMain.GAME_WIDTH))
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDownCenter  && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter  - HEIGHT)){
			PointJump = 0;
		}
	}
	
	public void Attack() throws SlickException{
		image.destroy();
		image = new Image("res/Character/Character0.png");
		Score +=1;
		m = false;
	}
	
	public boolean ColliderWithPodiumDown(){
		if((x>=0 && x<=Podium.WIDTH-25) 
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDown  && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp)){
			return true;
		}
		if((x>=GameMain.GAME_WIDTH+25 - Podium.WIDTH - Character.WIDTH && x<=GameMain.GAME_WIDTH) 
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDown  && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp)){
			return true;
		}
		return false;
	}
	
	public boolean ColliderWithPodiumUp(){
		if((x>=0 && x<=Podium.WIDTH - WIDTH/2) 
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp- HEIGHT/1.2 && y>= GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp- HEIGHT)){
			return true;
		}
		if((x>=GameMain.GAME_WIDTH + WIDTH/2 - Podium.WIDTH - Character.WIDTH && x<=GameMain.GAME_WIDTH) 
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp- HEIGHT/1.2 && y>= GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp- HEIGHT)){
			return true;
		}
		return false;
	}
	
	public boolean ColliderWithPodiumDownCenter(){
		if((x>=GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 - Character.WIDTH + 25 && x<=GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - 25) 
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDownCenter && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter)){
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
	
	public void collider() throws SlickException {
		if(ColliderWithPodiumDown()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDown ;
			jumpDown();
		}
		else if(ColliderWithPodiumUp()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp- HEIGHT ;
			vy = 0;
			PointJump = 1;
			image.destroy();
			if(m==true){
			image.destroy();
			image = new Image("res/Character/Character0.png");
			}
		}
		if(ColliderWithPodiumDownCenter()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDownCenter;
			jumpDown();
		}
		else if(ColliderWithPodiumUpCenter()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter - HEIGHT;
			vy = 0;
			PointJump = 1;
			image.destroy();
			if(m==true){
			image.destroy();
			image = new Image("res/Character/Character0.png");
			}
		}
	}
	
	public void DelayAttack(){
		if(time.getOneSec()==1){
			--Caltime1s;
		}
		if(Caltime1s==0){
			Caltime1s = Caltime1sMax;
			m = true;
		}
	}
		
}
