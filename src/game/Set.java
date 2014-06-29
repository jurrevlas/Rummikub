package game;


import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;

public class Set extends LinkedList<Tile>{
	
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -46223845867742988L;
	private static long IDcreator = 0;
	private final long ID;
	
	
	
	public Set(){
		super();		
		ID = IDcreator++;
	}	
	
	public boolean isValid(){
		if(size() < 3 || size() > 13)
			return false;		
		
		sort();	
		
		boolean sameNumber = true;
		boolean contNumber = true;
		boolean sameColor = true;
		boolean diffColor = true;
		
		TreeSet<Color> temp = new TreeSet<Color>();
		for(int i = 0 ; i < size()-1; i++){
			if(get(i).getNumber() != get(i+1).getNumber())
				sameNumber = false;
			if(get(i).getNumber()+1 != get(i+1).getNumber())
				contNumber = false;
			if(!get(i).getColor().equals(get(i+1).getColor()))
				sameColor = false;
			temp.add(get(i).getColor());
		}
		temp.add(peekLast().getColor());
		if(temp.size() < size())
			diffColor = false;
		return sameNumber && diffColor || contNumber && sameColor;		
	}
	
	public void sort(){
		Collections.sort(this);
	}	
	
	public long getID(){
		return ID;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Set){
			//return this.ID == (Set)(o).I;
			return false;
		}else{
			return super.equals(o);
		}
			
	}
	
	@Override
	public String toString(){
		String str = new String();
		for(Tile tile : this)
			str += tile.toString() +" ";
		return str;		
	}	
}
