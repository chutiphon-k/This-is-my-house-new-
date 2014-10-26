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

public class GameEnd extends BasicGameState {
	private Animation Anima_Press;
	private SpriteSheet Sheet_Press;
	private Image BG;
	private Image Score0;
	private Image Score1;
	private Image Score2;
	private Image Score;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
	    Sheet_Press = new SpriteSheet("res/Other/pressenter.png",318,70);
	    Anima_Press = new Animation(Sheet_Press,250);
	    Anima_Press.setPingPong(true);
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		if(Heart.Heart_Amount==0){
			BG = new Image("res/BG/BGgameover.png");
			BG.draw(0,0);
			Anima_Press.draw(240,410);
		}
		if(GameMain.Monster_Rest == 0){
			BG = new Image("res/BG/BGwin.png");
			Score = new Image("res/Number/score.png");
			Score0 = new Image("res/Number/"+GameMain.Time%10+".png");
			Score1 = new Image("res/Number/"+(GameMain.Time/10)%10+".png");
			Score2 = new Image("res/Number/"+(GameMain.Time/100)%10+".png");
			BG.draw(0,0);
			Score.draw(275,200);
			Score2.draw(310,270);
			Score1.draw(373,270);
			Score0.draw(436,270);
			Anima_Press.draw(240,500);
		}
		
		
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
		return 2;
	}

}
