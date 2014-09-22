package ThisIsMyHouse;

public class Time {
	public int TimeStart =0;
			
	public int getTime(){
		return this.TimeStart/1000;
	}
	
	public void update(int delta){
		this.TimeStart += delta;
	}
	
}
