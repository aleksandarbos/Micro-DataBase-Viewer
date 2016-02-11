package model.table.row;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import enums.ColumnType;
import gui.RowUpdateDialog;
import localisation.Loc;
import model.table.MyDefaultTableModel;

/**
 * Model podataka klase {@link RowUpdateDialog} koji za cilj ima prikupljanje podataka iz iste forme,
 * i na osnovu njih formira java objekte tipa u zavisnosti kog tipa su vrednosti definisane u bazi podataka.
 * Svrha je postojanje 2 atributa:
 * <ul>
 * 	<li>
 * 		<code>enteredValues</code> koji predstavlja torku vrednosti u izvornim java tipovima vrednosti
 * 	</li>
 * 	<li>
 * 		<code>enteredValuePairs</code> koji predstavlja vektor objekata tipa {@link ColumnEntryPair} gde je
 * 		svaki od njih zapisan kao NazivKljuca-Vrednost, gde je vrednost u izvornim java tipovima vrednosti.
 * 	</li>
 * </ul>
 * @author Aleksandar
 *
 */
public class RowUpdateModel {
	private Vector<Object> enteredValues = new Vector<>();
	private Vector<ColumnEntryPair> enteredValuePairs = new Vector<>();
	private MyDefaultTableModel tableModel;
	
	public RowUpdateModel(MyDefaultTableModel tableModel, Vector<ColumnEntryPair> enteredPairValues){
		this.tableModel = tableModel;
		formDataModel(enteredPairValues);
	}
	
	/**
	 * Metoda koji preuzete podatke iz forme, pretvara u model koji u sebi sadrzi kolekcije(kao atribute):
	 * <ul>
	 * 	<li>
	 * 		<code>eneredValues</code> Sto predstavlja sve podatke koje su uneti iz forme u vidu torke.
	 * 	</li>
	 * 	<li>
	 * 		<code>enteredValuePairs</code> Sto predstavlja sve podatke u vidu parova NazivKolone-VrednostPodatka
	 * 	</li>
	 * </ul> 
	 * @param enteredPairValues Pocetni parovi koji se preuzimaju iz klase {@link RowUpdateDialog}
	 */
	public void formDataModel(Vector<ColumnEntryPair> enteredPairValues) {
		// parse, and make enteredValues
		ColumnType type;
		Object value;
		ColumnEntryPair pair;
		String tableName = this.tableModel.getTableName();
		String columnName;
		@SuppressWarnings("rawtypes")
		Iterator it = enteredPairValues.iterator();
		
		while(it.hasNext()) {
			
			pair = (ColumnEntryPair) it.next();
			type = pair.getDataType();
			value = pair.getDataValue();
			columnName = pair.getColumnName();
			
			if(!value.equals("") && value != null) {
				switch(type) {
					case NUMERIC:
						int intVal = Integer.parseInt((String) value);
						this.enteredValues.add(intVal);
						this.enteredValuePairs.addElement(new ColumnEntryPair(tableName, columnName, intVal));
						break;
					case DATE:
					case DATETIME:
						Date dateVal = dateFromString((String) value);
						this.enteredValues.add(dateVal);
						this.enteredValuePairs.addElement(new ColumnEntryPair(tableName, columnName, dateVal));
						break;
					case DECIMAL:
						value = ((String) value).replace(",", ".");		// ako ima zameni..
						double doubleVal = Double.parseDouble((String) value);
						this.enteredValues.add(doubleVal);
						this.enteredValuePairs.addElement(new ColumnEntryPair(tableName, columnName, doubleVal));
						break;
					default:		// string
						//String strVal = value;
						this.enteredValues.add(value);
						this.enteredValuePairs.addElement(new ColumnEntryPair(tableName, columnName,value));
				}
			} else {
				this.enteredValues.add(null);
				this.enteredValuePairs.addElement(new ColumnEntryPair(tableName, columnName, null));
			}
		}
	}

	public Vector<Object> getEnteredValues() {
		return enteredValues;
	}

	public Vector<ColumnEntryPair> getEnteredValuePairs() {
		return enteredValuePairs;
	}
	
	/**
	 * Metoda koja preuzima datum u vidu {@link String}-ga i od njega formira novi
	 * {@link Date} objekat u zavisnosti od trenutno postavljenje lokalizacije {@link Loc}.
	 * @param value Vrednost datuma kao string.
	 * @return {@link Date} novi objekat klase datum formiran od unetog stringa.
	 */
	public static Date dateFromString(String value) {
		if(value == null)
			return null;
		
		if (value.contains("."))
			value = value.replace(".", "/");
		
		Calendar myCal = Calendar.getInstance();
		
		myCal.clear();
		
		if(value.length() > 6)
			value = value.substring(0, value.length());
		
		String[] strDate = ((String) value).split("/");
		
		if(strDate[0].startsWith("0")) {
			strDate[0] = strDate[0].substring(1, strDate[0].length());
		} else if(strDate[1].startsWith("0")) {
			strDate[1] = strDate[1].substring(1, strDate[1].length());
		}
		
		if(Loc.getInstance().getLanguage().toString().equals("en_US")) {
			myCal.set(Calendar.MONTH, Integer.parseInt(strDate[0])-1);
			myCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDate[1]));
			myCal.set(Calendar.YEAR, Integer.parseInt(strDate[2]));
		} else {		// serbian
			myCal.set(Calendar.MONTH, Integer.parseInt(strDate[1])-1);
			myCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDate[0]));
			myCal.set(Calendar.YEAR, Integer.parseInt(strDate[2]));
		}
		
		//myCal.set(Calendar.DAY_OF_WEEK, 0);		// inicijalno podesi ponedeljak - posle bitno za testiranje..
		
		Date dateVal = myCal.getTime();
		
		return dateVal;
	}
	
}
