package game;


import java.util.Collections;
import java.util.LinkedList;

public class Table extends LinkedList<Set>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -717092403487944807L;
	
	private Set stack;
	
	
	public Table(){	
		super();
		stack = new Set();
		
		//stack generation		
		for(int i = 0; i < 26; i++)
			for(Color c: Color.values())
				stack.add(new Tile(c,i%13+1));
		//shuffle stack
		Collections.shuffle(stack);
	}
	
	public Set newSet(Tile tile){
		Set set = new Set();
		set.add(tile);
		add(set);
		return set;
	}	

	public void addToSet(Set set, Tile tile){
		System.out.println(set.getID());
		System.out.println(getFirst().getID());
		get(indexOf(set)).add(tile);
	}
	
	public boolean removeFromSet(Set set, Tile tile){
		boolean success =get(indexOf(set)).remove(tile);
		if(success && set.size() == 0)
			remove(set);
		return  success;
	}
			
	public boolean isValid(){
		for(Set set : this)
			if(!set.isValid())
				return false;
		return true;
	}
	
	public Tile popFromStack(){
		return stack.pop();
	}
	
	public int numTilesOnTable(){
		int count = 0;
		for(Set set : this)
			count += set.size();
		return count;
	}
	
	public int numTilesOnStack(){
		return stack.size();
	}
	
	@Override
	public Object clone(){
		Table table = new Table();
		for(Set set : this)
			table.add((Set) set.clone());
		table.stack = (Set) stack.clone();
		
		return table;
		
	}
	
	
	@Override
	public String toString(){
		String str = new String("Cards on Stack: "+stack.size()+"\n");
		
		for(Set set : this)
			str += set.toString() +"\n";
		return str;
			
	}	
	
	
	
}
