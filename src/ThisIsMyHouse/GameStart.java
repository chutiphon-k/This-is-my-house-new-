package ThisIsMyHouse;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;

public class GameStart extends BasicGameState {
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawString("GameStart", 200, 200);
		
	}

	@Override
	public void update(GameContainer c, StateBasedGame sbg, int arg2)
			throws SlickException {
		if(c.getInput().isKeyPressed(Input.KEY_ENTER)){
			sbg.enterState(1,new BlobbyTransition(Color.green),new BlobbyTransition());
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
