package message;

import game.Table;

public class SendTable extends GameMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7538216548008703884L;
	
	private Table table;

	public SendTable(String sender, Table table) {
		super(sender);
		this.table = table;
	}
	
	public Table getTable(){
		return table;
	}
	
}
