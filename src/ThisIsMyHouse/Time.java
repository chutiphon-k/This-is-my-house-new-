package ThisIsMyHouse;

public class Time {
	public int time =0;
	public int currentTime=0;
	
	public int getTime(){
		
		return this.time/1000;
	}
	
	public int getOneSec(){
		
		return time/1000-currentTime;
	}
	
	public void update(int delta){
		
		this.time += delta;
	}
	
	public void setCurrentTime(){
		
		currentTime = time/1000;
	}
	
	
}
