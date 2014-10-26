package ThisIsMyHouse;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;

public class GameMain extends BasicGameState {
	private Image BGImage;
	private Podium[] podium;
	private Dimension[] dimension;
	private Monster[] monster;
	private static Character character;
	private Color color;
	private Time time;
	private Heart heart;
	public static int Caltime3s = 3;
	public static int CaltimeMax3s = Caltime3s;
	public static boolean Crash = true;
	public static final float G =  (float)1;
	public static final float GMonster =  (float)0.5;
	public static final float Character_JUMP_VY =  (float)22;
	public static final float Character_MOVE_VX =  (float)5;
	public static final float DistanceBottomAndPodiumUp = 200;
	public static final float DistanceBottomAndPodiumDown = DistanceBottomAndPodiumUp - Podium.HEIGHT;
	public static final float DistanceBottomAndPodiumUpCenter = DistanceBottomAndPodiumUp*2;
	public static final float DistanceBottomAndPodiumDownCenter = DistanceBottomAndPodiumUpCenter - Podium.HEIGHT;
	public static final float Monster_JUMP_VY =  (float)7;
	public static final float Monster_MOVE_VX =  (float)2;
	public static final int Monster_Amount = 10;
	public static boolean IsAttack = false;
	public static int Monster_Rest = 50;
	public static final int platform = SetupClass.platform;
	public static final int GAME_WIDTH = SetupClass.GAME_WIDTH;
	public static final int GAME_HEIGHT = SetupClass.GAME_HEIGHT;
	public static final int GAME_HEIGHT_ASSUM = SetupClass.GAME_HEIGHT - platform;
	public static int Time;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		BGImage = new Image("res/BG/BG.png");
		character = new Character(GAME_WIDTH/2 - Character.WIDTH/2,GAME_HEIGHT_ASSUM-Character.HEIGHT,Character_MOVE_VX,Character_JUMP_VY);
		time = new Time();
		initPodium();
		initDimension();
		initMonster();
		heart = new Heart(GameMain.GAME_WIDTH-Heart.WIDTH-10,0);
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
			BGImage.draw(0,0);
			g.drawString("Time : " + time.getTime() + " second",100, 10);
			g.drawString("Monster : " + Monster_Rest,100,30);
			for (Podium podiums : podium) {
				podiums.render();
			}
			for (Dimension dimensions : dimension) {
				dimensions.render();
			}
			character.render(g);
			
			for(Monster monsters : monster){
				monsters.render(g);
			}
			heart.render();
	}

	@Override
	public void update(GameContainer c, StateBasedGame sbg, int delta)
			throws SlickException {
			time.update(delta);
			character.update(c,delta);
			Input input = c.getInput();
			updateCharacterMovement(input,delta);
			for(Monster monsters : monster){
				monsters.update(c);
				if(monsters.CheckMonIntersectsCharAttack()&&c.getInput().isKeyPressed(Input.KEY_SPACE)){
					character.Attack();
					monsters.DestroyMonster();
					IsAttack = false;
				}
				else if(monsters.CheckMonIntersectsChar()&&Crash){
					heart.update();
					Crash = false;
				}
			}
			DelayMonsterCrash();
			if(heart.CheckHeart() || Monster_Rest == 0){
				Time = time.getTime();
				sbg.enterState(2,new BlobbyTransition(Color.darkGray),new BlobbyTransition());
			}
			time.setCurrentTime();
	}
	
	public static void updateCharacterMovement(Input input, int delta){
	    if (input.isKeyDown(Input.KEY_LEFT)) {
	        character.MoveLeft();
	      }
	    if (input.isKeyDown(Input.KEY_RIGHT)) {
	        character.MoveRight();
	      }
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
	    if(key == Input.KEY_UP) {
	    	try {
				character.jumpUp();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    if(key == Input.KEY_SPACE){
				IsAttack = true;
				Character.Action = "attack";
	    }
	}
	
	public void initPodium() throws SlickException {
	    podium = new Podium[3];
		podium[0] = new Podium(0,GAME_HEIGHT_ASSUM-DistanceBottomAndPodiumUp);
		podium[1] = new Podium(GAME_WIDTH/2 - Podium.WIDTH/2,GAME_HEIGHT_ASSUM - DistanceBottomAndPodiumUpCenter);
		podium[2] = new Podium(GAME_WIDTH-Podium.WIDTH,GAME_HEIGHT_ASSUM-DistanceBottomAndPodiumUp);
		
	}
	
	public void initDimension() throws SlickException {
	    dimension = new Dimension[5];
	    dimension[0] = new Dimension(0,GAME_HEIGHT_ASSUM-Dimension.HEIGHT);
	    dimension[1] = new Dimension(0,GAME_HEIGHT_ASSUM-DistanceBottomAndPodiumUp - Dimension.HEIGHT);
	    dimension[2] = new Dimension(GAME_WIDTH/2 - Dimension.WIDTH/2,GAME_HEIGHT_ASSUM-DistanceBottomAndPodiumUpCenter - Dimension.HEIGHT);
	    dimension[3] = new Dimension(GAME_WIDTH - Dimension.WIDTH,GAME_HEIGHT_ASSUM-DistanceBottomAndPodiumUp - Dimension.HEIGHT);
	    dimension[4] = new Dimension(GAME_WIDTH - Dimension.WIDTH,GAME_HEIGHT_ASSUM-Dimension.HEIGHT);
	}

	public void initMonster() throws SlickException {
		monster = new Monster[Monster_Amount];
		for(int i = 0;i<monster.length;i++){
			monster[i] = new Monster(Monster_MOVE_VX,Monster_JUMP_VY);
		}
	}
	
	public void DelayMonsterCrash(){
		if(time.getOneSec()==1){
			--Caltime3s;
		}
		if(Caltime3s==0){
			Caltime3s = CaltimeMax3s;
			Crash = true;
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
