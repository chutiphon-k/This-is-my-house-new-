package ThisIsMyHouse;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;

public class GameStart extends BasicGameState {
	private Animation Anima_Press;
	private SpriteSheet Sheet_Press;
	private Image BG;

	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
	    Sheet_Press = new SpriteSheet("res/Other/pressenter.png",318,70);
	    Anima_Press = new Animation(Sheet_Press,250);
	    Anima_Press.setPingPong(true);
	    BG = new Image("res/BG/BGstart.png");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		BG.draw(0,0);
		Anima_Press.draw(235,270);
		
	}

	@Override
	public void update(GameContainer c, StateBasedGame sbg, int delta)
			throws SlickException {
		Anima_Press.update(delta);
		if(c.getInput().isKeyPressed(Input.KEY_ENTER)){
			sbg.enterState(1,new BlobbyTransition(Color.darkGray),new BlobbyTransition());
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
