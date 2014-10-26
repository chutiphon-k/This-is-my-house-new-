package ThisIsMyHouse;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Character {
	public  static float x;
	public  static float y;
	private float vx;
	private float vy;
	private float vjump;
	private Image image;
	public static final int WIDTH = 83;
	public static final int WIDTH_Attack = 10;
	public static final int HEIGHT = 110;
	public static Shape rec;
	public static Shape recAttack;
	private Time time = new Time();
	public static String Action = "stand";
	public static int Caltime2s = 1;
	public static int CaltimeMax2s = Caltime2s;
	private SpriteSheet Sheet_Attack_L;
	private Animation Anima_Attack_L;
	private SpriteSheet Sheet_Attack_R;
	private Animation Anima_Attack_R;
	private SpriteSheet Sheet_Walk_L;
	private Animation Anima_Walk_L;
	private SpriteSheet Sheet_Walk_R;
	private Animation Anima_Walk_R;
	public static int PointJump;
	private String Direction;
	
	
	
	public Character(float x, float y , float vx , float vy) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.vx = vx;
	    this.vy = vy;
	    this.vjump = vy;
	    rec = new Rectangle(x,y,WIDTH,HEIGHT);
	    recAttack = new Rectangle(x-20,y,WIDTH+40,HEIGHT);
	    image = new Image("res/Character/Character0L.png");
	    Sheet_Attack_L = new SpriteSheet("res/Character/characterattackL.png",83,110);
	    Anima_Attack_L = new Animation(Sheet_Attack_L,100);
	    Sheet_Attack_R = new SpriteSheet("res/Character/characterattackR.png",83,110);
	    Anima_Attack_R = new Animation(Sheet_Attack_R,100);
	    Sheet_Walk_L = new SpriteSheet("res/Character/characterwalkL.png",83,110);
	    Anima_Walk_L = new Animation(Sheet_Walk_L,100);
	    Sheet_Walk_R = new SpriteSheet("res/Character/characterwalkR.png",83,110);
	    Anima_Walk_R = new Animation(Sheet_Walk_R,100);
	}
	
	public void render(Graphics g) throws SlickException{
		if(Action == "attack"){
			if(Direction == "L"){
				Anima_Attack_L.draw(x,y);
			}
			if(Direction == "R"){
				Anima_Attack_R.draw(x,y);
			}
		}
		else if(Action == "move"){
			if(Direction == "L"){
				Anima_Walk_L.draw(x,y);
			}
			if(Direction == "R"){
				Anima_Walk_R.draw(x,y);
			}
		}
		else{
			image.draw(x,y);
		}
		CharacterAction();
	}
	
	public void update(GameContainer c,int delta) throws SlickException {
		time.update(delta);
		rec.setLocation(x, y);
		recAttack.setLocation(x-20, y);
		Jump();
		collider();
		DelayAttack();
		time.setCurrentTime();
	}

	public void MoveLeft() {
		Action = "move";
		Direction = "L";
		if(x>0){
			x -=vx;
		}
		else{
			x =0;
		}
	}

	public void MoveRight() {
		Action = "move";
		Direction = "R";
		if(x<(GameMain.GAME_WIDTH-WIDTH)){
			x +=vx;
		}
		else{
			x = GameMain.GAME_WIDTH-WIDTH;
		}
	}

	public void jumpUp() throws SlickException {
		if(PointJump == 1){
			vy = -vjump;
			Action = "jump";
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
			if(Action != "attack"){
			Action = "stand";
			}
			PointJump = 1;
		}
		if(y<=0){
			y = 0;
			jumpDown();
		}
		if(x>=Podium.WIDTH + 25 - Character.WIDTH && x<=GameMain.GAME_WIDTH - Podium.WIDTH - 25 
				&& (y<GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDown  - HEIGHT/2 && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp- HEIGHT)){
			Action = "jump";
			PointJump = 0;
		}
		if(((x<GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 - Character.WIDTH +25 && x>=0) || (x>GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - 25 && x<=GameMain.GAME_WIDTH))
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDownCenter  && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter  - HEIGHT)){
			Action = "jump";
			PointJump = 0;
		}
	}
	
	public void Attack(){
		GameMain.Monster_Rest--;
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
			if(Action != "attack"){
			Action = "stand";
			}
			PointJump = 1;
		}
		if(ColliderWithPodiumDownCenter()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDownCenter;
			jumpDown();
		}
		else if(ColliderWithPodiumUpCenter()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter - HEIGHT;
			vy = 0;
			if(Action != "attack"){
			Action = "stand";
			}
			PointJump = 1;
		}
	}
	
	public void CharacterAction() throws SlickException{
		if(Action == "attack"){
			if(Direction == "L"){
				Anima_Attack_L.start();
			}
			if(Direction == "R"){
				Anima_Attack_R.start();
			}
		}
		else if(Action == "stand"){
			Anima_Attack_L.stop();
			Anima_Walk_L.stop();
			Anima_Walk_R.stop();
			if(Direction == "L"){
				image.destroy();
				image = new Image("res/Character/character0L.png");
			}
			if(Direction == "R"){
				image.destroy();
				image = new Image("res/Character/character0R.png");
			}
		}
		else if(Action == "jump"){
			if(Direction == "L"){
				image.destroy();
				image = new Image("res/Character/characterjumpL.png");
			}
			if(Direction == "R"){
				image.destroy();
				image = new Image("res/Character/characterjumpR.png");
			}
		}
		else if(Action == "move"){
			if(Direction == "L"){
				Anima_Walk_L.start();
			}
			if(Direction == "R"){
				Anima_Walk_R.start();
			}
		}
	}
	
	public void DelayAttack(){
		if(time.getOneSec()==1){
			Caltime2s -= 1;
		}
		if(Caltime2s==0){
			Caltime2s = CaltimeMax2s;
			Action = "stand";
		}
	}
	
	
}
