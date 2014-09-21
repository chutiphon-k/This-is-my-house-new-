package ThisIsMyHouse;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameMain extends BasicGame {
	private Image BGImage;
	private static Character character;
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	public static final float G =  (float)1;
	public static final float Character_JUMP_VY =  (float)22;
	public static final float Character_MOVE_VX =  (float)5;
	
	
	

	public GameMain(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
		      GameMain game = new GameMain("This is my house!!!");
		      AppGameContainer container = new AppGameContainer(game);
		      container.setDisplayMode(GAME_WIDTH,GAME_HEIGHT, false);
		      container.setMinimumLogicUpdateInterval(1000 / 60);
		      container.start();
		    } catch (SlickException e) {
		      e.printStackTrace();
		    }

	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		BGImage.draw(0,0);
		character.render();
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		BGImage = new Image("res/BG/BGbedroom.png");
		character = new Character(GAME_WIDTH/2 - Character.WIDTH/2,GAME_HEIGHT-Character.HEIGHT,Character_MOVE_VX,Character_JUMP_VY);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		character.update();
		Input input = container.getInput();
		updateCharacterMovement(input,delta);
		
	}
	
	public static void updateCharacterMovement(Input input, int delta){
	    if (input.isKeyDown(Input.KEY_LEFT)) {
	        character.MoveLeft();
	      }
	    if (input.isKeyDown(Input.KEY_RIGHT)) {
	        character.MoveRight();
	      }
	}
	
	
//	@Override
//	public void keyPressed(int key, char c) {
//		// TODO Auto-generated method stub
//	    if (key == Input.KEY_UP) {
//	    	character.jump();
//	    }
//	}

}
