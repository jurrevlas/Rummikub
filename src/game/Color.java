package game;

public enum Color {
	Black, 
	Blue, 
	Red,
	Yellow;
	
	@Override
	public String toString(){
		switch(this){
			case Black	: return "Blk";
			case Blue	: return "Blu";
			case Red	: return "Red";
			default		: return "Yel";
		}
	}
}
