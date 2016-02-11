package model.table.row;

import javax.swing.JTextField;

import controller.database.DataBase;
import enums.ColumnType;

/**
 * Klasa koja definise par tip podatka iz tabele na osnovu {@link ColumnType} i vrednosti untar 
 * nje tipa {@link Object}.
 * 
 * @author Aleksandar
 */
public class ColumnEntryPair {

	private Object tfDataValue;
	private JTextField tf;
	private String columnName;
	private String tableName;
	
	public ColumnEntryPair(String tableName, String columnName, Object tfDataValue) {
		if(tfDataValue instanceof JTextField) {
			this.tf = (JTextField) tfDataValue;
			this.tfDataValue = tf.getText();
		}
		else
			this.tfDataValue = tfDataValue;
		
		this.columnName = columnName;
		this.tableName = tableName;
			
	}
	
	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Object getDataValue() {
		if(this.tf != null)
			return this.tf.getText();
		else
			return this.tfDataValue;
	}
	
	@Override
	public String toString() {
		return this.columnName + "-" + getDataValue();
	}
	
	public ColumnType getDataType() {
		return DataBase.getInstance().getColumnType(this.tableName, this.columnName);
	}
	
}
