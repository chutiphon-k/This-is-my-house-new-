package ThisIsMyHouse;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameMain extends BasicGame {
	private Image BGImage;
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	
	
	
	

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
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		BGImage = new Image("res/BG/BGbedroom.png");
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
