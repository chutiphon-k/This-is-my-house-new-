package ThisIsMyHouse;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameMain extends BasicGame {
	private Image BGImage;
	private Podium[] podium;
	private Dimension[] dimension;
	private Monster[] monster;
	private static Character character;
	private Color color;
	private Time time;
	public static final int platform = 55;
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static final int GAME_HEIGHT_ASSUM = 600 - GameMain.platform;
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
	public static int caltime = 2;
	public static boolean BeforeCrash = true;

	public GameMain(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
		      GameMain game = new GameMain("This is my world!!!");
		      AppGameContainer container = new AppGameContainer(game);
		      container.setDisplayMode(GAME_WIDTH,GAME_HEIGHT, false);
		      container.setMinimumLogicUpdateInterval(1000 / 60);
		      container.start();
		    } catch (SlickException e) {
		      e.printStackTrace();
		    }

	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		BGImage.draw(0,0);
	    for (Podium podiums : podium) {
	    	podiums.render();
	    }
	    for (Dimension dimensions : dimension) {
	    	dimensions.render();
	    }
		character.render(g);
	    color  = new Color(255,0,0);
	    g.setColor(color);
	    g.drawString("Time : " + time.getTime(),100, 10);
	    for(int i = 0;i<5;i++){
	    	monster[i].render(g);
	    }
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		BGImage = new Image("res/BG/BG.png");
		character = new Character(GAME_WIDTH/2 - Character.WIDTH/2,GAME_HEIGHT_ASSUM-Character.HEIGHT,Character_MOVE_VX,Character_JUMP_VY);
		time = new Time();
		initPodium();
		initDimension();
		initMonster();
	}

	@Override
	public void update(GameContainer c, int delta) throws SlickException {
		time.update(delta);
		if(time.getTime()-time.currentTime==1 && Monster.AfterCrash==true){
			--caltime;
		}
		if(caltime==0){
			System.out.print(time.getTime());
			caltime = 2;
			BeforeCrash = true;
			Monster.AfterCrash = false;
		}
		character.update(c);
		Input input = c.getInput();
		updateCharacterMovement(input,delta);
	    for(int i = 0;i<5;i++){
	    	monster[i].update(c);
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
	    if (key == Input.KEY_UP) {
	    	character.jumpUp();
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
		monster = new Monster[5];
	    monster[0] = new Monster(0,GAME_HEIGHT_ASSUM-Dimension.HEIGHT,Monster_MOVE_VX,Monster_JUMP_VY);
	    monster[1] = new Monster(0,GAME_HEIGHT_ASSUM-DistanceBottomAndPodiumUp - Dimension.HEIGHT,Monster_MOVE_VX,Monster_JUMP_VY);
	    monster[2] = new Monster(GAME_WIDTH/2 - Dimension.WIDTH/2,GAME_HEIGHT_ASSUM-DistanceBottomAndPodiumUpCenter - Dimension.HEIGHT,Monster_MOVE_VX,Monster_JUMP_VY);
	    monster[3] = new Monster(GAME_WIDTH - Dimension.WIDTH,GAME_HEIGHT_ASSUM-DistanceBottomAndPodiumUp - Dimension.HEIGHT,Monster_MOVE_VX,Monster_JUMP_VY);
	    monster[4] = new Monster(GAME_WIDTH - Dimension.WIDTH,GAME_HEIGHT_ASSUM-Dimension.HEIGHT,Monster_MOVE_VX,Monster_JUMP_VY);
	}
	
}
