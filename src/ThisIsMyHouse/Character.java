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
	public static int k = 0;
	private SpriteSheet Sheet_Walk;
	private Animation Anima_Walk;
	public static String Action = "stand";
	public static int Caltime2s = 3;
	public static int CaltimeMax2s = Caltime2s;
	
	public Character(float x, float y , float vx , float vy) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.vx = vx;
	    this.vy = vy;
	    this.vjump = vy;
	    rec = new Rectangle(x,y,WIDTH,HEIGHT);
	    image = new Image("res/Character/Character0.png");
	    Sheet_Walk = new SpriteSheet("res/Character/q.png",83,110);
	    Anima_Walk = new Animation(Sheet_Walk,50);
	}
	
	public void render(Graphics g) throws SlickException{
		CharacterAction();
		if(Action != "move"){
		image.draw(x,y);
		}
		g.drawString("score : " + k,200,200);
		g.drawString("Action : " + Action,300,00);
	}
	
	public void update(GameContainer c,int delta) throws SlickException {
		time.update(delta);
		rec.setLocation(x, y);
		Jump();
		collider();
		time.setCurrentTime();
	}

	public void MoveLeft() {
		Action = "move";
		Anima_Walk.start();
		if(x>0){
			x -= vx;
		}
		else{
			x = 0;
		}
	}

	public void MoveRight() {
		Action = "move";
		Anima_Walk.start();
		if(x<(GameMain.GAME_WIDTH-WIDTH)){
			x +=vx;
		}
		else{
			x = GameMain.GAME_WIDTH-WIDTH;
		}
	}

	public void jumpUp() throws SlickException {
		if(Action == "stand"){
			vy = -vjump;
			Action = "jump";
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
			Action = "stand";
		}
		if(y<=0){
			y = 0;
			jumpDown();
		}
		if(x>=Podium.WIDTH + 25 - Character.WIDTH && x<=GameMain.GAME_WIDTH - Podium.WIDTH - 25 
				&& (y<GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDown  - HEIGHT/2 && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUp- HEIGHT)){
			Action = "jump";
		}
		if(((x<GameMain.GAME_WIDTH/2 - Podium.WIDTH/2 - Character.WIDTH +25 && x>=0) || (x>GameMain.GAME_WIDTH/2 + Podium.WIDTH/2 - 25 && x<=GameMain.GAME_WIDTH))
				&& (y<=GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDownCenter  && y> GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter  - HEIGHT)){
			Action = "jump";
		}
	}
	
	public void Attack(){
		k++;
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
			Action = "stand";
		}
		if(ColliderWithPodiumDownCenter()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumDownCenter;
			jumpDown();
		}
		else if(ColliderWithPodiumUpCenter()==true){
			y = GameMain.GAME_HEIGHT_ASSUM - GameMain.DistanceBottomAndPodiumUpCenter - HEIGHT;
			vy = 0;
				Action = "stand";
		}
	}
	
	public void CharacterAction() throws SlickException{
		if(Action == "attack"){
			image.destroy();
			image = new Image("res/Character/characterattack.png");
		}
		else if(Action == "stand"){
			Anima_Walk.stop();
			image.destroy();
			image = new Image("res/Character/Character0.png");
		}
		else if(Action == "jump"){
			image.destroy();
			image = new Image("res/Character/CharacterJump.png");
		}
		else if(Action == "move"){
			Anima_Walk.draw(x,y);
			Action = "stand";
		}
	}
	
//	public void DelayAttack(){
//		if(time.getOneSec()==1){
//			--Caltime2s;
//		}
//		if(Caltime2s==0){
//			Caltime2s = CaltimeMax2s;
//			Action = "stand";
//		}
//	}
}
