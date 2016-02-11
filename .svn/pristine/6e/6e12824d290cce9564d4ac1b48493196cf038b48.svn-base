package controller.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import enums.ColumnType;
import localisation.Loc;
import localisation.LocaliseName;
import main.Main;
import model.table.MyDefaultTableModel;
import model.tree.JsonModel;

/**
 * Klasa modeluje vezu aplikacije sa bazom podataka.
 * Osim veze sa bazom podataka, klasa sadrzi i metode za rad sa bazom podataka.
 * Klasa je modelovana kao singleton.
 * 
 * @author NemanjaM
 *
 */
public class DataBase {
	
	private static DataBase instance = null;

	/**
	 * Atribut koji u sebi sadrzi sve informacije o bazi podataka i njenoj organizaciji.
	 */
	private DatabaseMetaData metaData;
	
	/**
	 * Atribut koji u sebi sadrzi sve informacije o konekciji sa bazom podataka. Inicijalizuje se
	 * prilikom pokretanja aplikacije, a uklanja se pozivom odgovarajuce metode prilikom zatvaranja 
	 * aplikacije.
	 */
	private Connection connection = null;
	
	private DataBase() {
		super();
	}	
		
	/**
	 * Metoda vrsi inicijalizaciju baze podataka. Postavljaju se odgovarajuci parametri i aplikacija se
	 * povezuje sa bazom podataka.<br/>
	 * U slucaju neuspeha prilikom otvaranja baze podataka, korisniku se ispisuje poruka o gresci
	 * i prekida se dalji rad sa aplikacijom.
	 */
	private void initialize() {
		
		String dbip = "147.91.175.155";
		String localIp = "192.168.77.230";
		String dbport = "1433";
		String dbname = "psw-2015-tim6-4";
		String dbusername = "psw-2015-tim6-4";
		String dbpassword = "tim6-415969727";

		System.out.println("Connectiong to database...");
		try {
			connection = DriverManager.getConnection("jdbc:jtds:sqlserver://" + dbip + ":" + dbport + "/" + dbname, dbusername, dbpassword);
			System.out.println("Database connection: SUCCESSFUL!");
		} catch (SQLException e) {
			System.out.println("First connection failed, STARTING SECOND connection...");
			try {
				connection = DriverManager.getConnection("jdbc:jtds:sqlserver://" + localIp + ":" + dbport + "/" + dbname, dbusername, dbpassword);
				System.out.println("Database connection: SUCCESSFUL!");
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(new JFrame(),
					    "Application failed to establish the connection to DataBase. Please try again later.\nApplication will shut down",
					    "DataBase Connection Error",
					    JOptionPane.ERROR_MESSAGE);
				Main.exitApplication();
			}
		}
		try {
			metaData = connection.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda vrsi proveru da li korisnik definisan prosledjenim parametrima postoji u bazi podataka.<br/>
	 *  
	 * @param username Korisnicko ime koje se prosledjuje bazi podataka na proveru 
	 * @param password Lozinka koja se prosledjuje bazi podataka na poveru
	 * @return {@code true} ukoliko prosledjeni parametri odgovaraju nekom od korisnika iz baze podataka, u suprotnom vraca {@code false}
	 */
	public boolean logIn (String username, String password) {

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from KORISNICI");
			
			while (rs.next()) {
				if (rs.getString(2).equals(username)) {
					if (rs.getString(3).equals(password)) {
						rs.close();
						stmt.close();
						return true;
					} else {
						rs.close();
						stmt.close();
						return false;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Metoda vrsi iscitavanje tabele podataka, ciji je naziv prosledjen kao parametar. Procitane podatke
	 * smesta u model koji je takodje prosledjen kao parametar.<br/>
	 * Ovo omogucava prikaz podataka koji se nalaze u bazi podataka.
	 * 
	 * @param model u koji ce biti smesteni procitani podaci
	 * @param tableId naziv tabele kojoj odgovara model, tj iz koje ce biti procitane vrednosti
	 * @return metoda vraca {@code true} ukoliko je uspesno izvrsena, odnosno {@code false} ako je doslo do greske
	 */
	public boolean readTableData(MyDefaultTableModel model, String tableId) {
		int columns = JsonModel.map.get(tableId).getColumnCount();
		Vector<String> headers = new Vector<String>(JsonModel.map.get(tableId).getColumns());
		boolean result = false;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from " + tableId);
			
			while (rs.next()) {
				Vector<String> column = new Vector<String>();
				
				for (int i = 1; i <= columns; i++) { 
					if (rs.getString(i) == null)
						column.add(rs.getString(i));
					else {
						ColumnType type = getColumnType(tableId, headers.get(i-1));
						if (type == ColumnType.DATE)
							column.add(parseDateFromDB(rs.getString(i)));
						else if (type == ColumnType.DATETIME)
							column.add(parseDateTimeFromDB(rs.getString(i)));
						else if (type == ColumnType.DECIMAL)
							column.add(parseDecimalFromDB(rs.getString(i)));
						else
							column.add(rs.getString(i));
					}
				}
				
				model.insertRow(model.getRowCount(), column);				
			}
			rs.close();
			stmt.close();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		
		return result;
	}
	
	/**
	 * Metoda sluzi za upis podataka u bazu podataka, u tabelu koja se prosledjuje kao parametar. Metoda je
	 * fleksibilna sa strane broja i tipa parametara, i izvrsice upis bez obzira na tip i broj parametara. Jedini uslov
	 * koji mora biti ispunjen je da broj prosledjenih naziva kolona i broj prosledjenih podataka za ubis bude jednak. 
	 * Ukoliko se dodavanje u bazu podataka iz nekog razloga ne izvrsi, metoda prikazuje dijalog o gresci.
	 * 
	 * @param tableId naziv tabele u koju se upisuju podaci
	 * @param headers nazivi kolona u tabeli u koju se ubacuju podaci. Njihov redosled treba da odgovara redosledu
	 *  podataka koji su poslati za upis ({@code data} parametar)
	 * @param data podaci koji se upisuju u tabelu, tacno onim redosledom kojim su zadate kolone
	 * @return ukoliko su prosledjeni podaci ispravni i pisanje podataka u bazu je proslo uspesno vraca {@code true}, 
	 * ukoliko neki od ovih uslova nije ispunjen, vraca {@code false}
	 */
	public boolean insertToDatabase(String tableId, Vector<String> headers, Vector<Object> data) {
		int columns = headers.size();
		boolean result = false;
		
		if (headers == null || data == null)
			return result;
		
		if (headers != null && data != null && (headers.size() != data.size() || data.size() == 0))
			return result;
		
		//INSERT INTO NASELJENO_MESTO (DR_OZNAKA,NM_IDENTIFIKATOR,NM_NAZIV,NM_PTT_OZNAKA) VALUES ('srb','13','Veternik','21203')
		String query = "insert into " + tableId + " (";
		String rows = "", values = "";
		for (int i =0; i < columns; i++) {
			rows += (i == 0) ? headers.get(i) : "," + headers.get(i);
			values += (i == 0) ? "?" : ",?" ;			
		}
		query += rows + ") values (" + values + ")"; 

		try {
			PreparedStatement stm = connection.prepareStatement(query);			
			for (int i = 0; i < columns; i++)
				if (data.get(i) instanceof Date)
					stm.setObject(i+1, parseDateToDB((Date) data.get(i)));						
				else if (data.get(i) != null)
					stm.setObject(i+1, data.get(i).toString());
				else
					stm.setObject(i+1, data.get(i));
			stm.executeUpdate();
			stm.close();
			result = true;
		} catch (SQLException e) {
			sendErrorMessage(e.getErrorCode());
		}

		return result;
	}
	
	/**
	 * Metoda sluzi za izmenu podataka u bazi podataka, u tabeli koja se prosledjuje kao parametar. Metoda je
	 * fleksibilna sa strane broja i tipa parametara, i izvrsice izmenu bez obzira na tip i broj parametara. Jedini uslov
	 * koji mora biti ispunjen je da broj prosledjenih naziva kolona i broj prosledjenih podataka za izmenu bude jednak. 
	 * Na osnuvu prosledjenog imena tabele prepoznaju se kljucavi ove tabele, i na osnovu njih se prepoznaje red
	 * tabele cije se vrednosti manjaju.
	 * Ukoliko se izmena podataka u bazi podataka iz nekog razloga ne izvrsi, metoda prikazuje dijalog o gresci.
	 * 
	 * @param tableId naziv tabele u kojoj se menjaju podaci
	 * @param headers nazivi kolona u tabeli u koju se ubacuju podaci. Njihov redosled treba da odgovara redosledu
	 * podataka koji su poslati za upis ({@code data} parametar)
	 * @param data vrednosti koje ce biti upisane na mestu starih u tabeli, tacno onim redosledom kojim su zadate kolone
	 * @return ukoliko su prosledjeni podaci ispravni i pisanje podataka u bazu je proslo uspesno vraca {@code true}, 
	 * ukoliko neki od ovih uslova nije ispunjen, vraca {@code false}
	 */
	public boolean updateInDatabase(String tableId, Vector<String> headers, Vector<Object> data) {
		int columns = headers.size();
		boolean result = false;
		
		if (headers == null || data == null)
			return result;
		
		if (headers != null && data != null && (headers.size() != data.size() || data.size() == 0))
			return result;

		//UPDATE table_name SET column1=value1, column2=value2 WHERE some_column = some_value
		String query = "update " + tableId + " set ";
		String rows = "", condition = "";
		boolean found;
		Vector<String> keys = getPrimaryKeys(tableId);
		
		for(int i = 0, r = 0, c = 0; i < columns; i++) {
			found = false;
			for (String key : keys) {
				if (headers.get(i).equals(key)) {
					found = true;
					break;
				}
			}
			if (!found)
				rows += ((r++ == 0) ? "" : ", ") + headers.get(i) + " = ?";
			else
				condition += ((c++ == 0) ? "" : " and ") + headers.get(i) + " = ?";
		}
		query += rows + " where " + condition;		

		try {
			PreparedStatement stm = connection.prepareStatement(query);			
			for (int i = 0, r = 1, c = columns - keys.size() + 1; i < columns; i++) {
				found = false;
				for (String key : keys) {
					if (headers.get(i).equals(key)) {
						found = true;
						break;
					}
				}
				if (!found)
					if (data.get(i) instanceof Date)
						stm.setObject(r++, parseDateToDB((Date) data.get(i)));						
					else
						stm.setObject(r++, data.get(i).toString());
				else
					stm.setObject(c++, data.get(i));
			}
			stm.executeUpdate();
			stm.close();
			result = true;
		} catch (SQLException e) {
			sendErrorMessage(e.getErrorCode());
		}

		return result;
	}
	
	/**
	 * Metoda sluzi za brisanje reda u bazi podataka, u tabeli koja se prosledjuje kao parametar. Metoda je
	 * fleksibilna sa strane broja i tipa parametara, i izvrsice brisanje bez obzira na tip i broj parametara. Jedini uslov
	 * koji mora biti ispunjen je da broj prosledjenih naziva kolona i broj prosledjenih podataka za izmenu bude jednak. 
	 * Prepoznavanje reda za brisanje vrsi se na prema parovima prosledjenih vrednosti ime_kolone-vrednost_u_koloni, koji se
	 * prenose u ulaznim parametrima {@code headers} i {@code data}.
	 * Ukoliko se brisanje u bazi podataka iz nekog razloga ne izvrsi, metoda prikazuje dijalog o gresci.
	 * 
	 * @param tableId naziv tabele u kojoj se menjaju podaci
	 * @param headers nazivi kolona u tabeli u koju se ubacuju podaci. Njihov redosled treba da odgovara redosledu
	 * podataka koji su poslati za upis ({@code data} parametar)
	 * @param data vrednosti koje ce biti upisane na mestu starih u tabeli, tacno onim redosledom kojim su zadate kolone
	 * @return ukoliko su prosledjeni podaci ispravni i pisanje podataka u bazu je proslo uspesno vraca {@code true}, 
	 * ukoliko neki od ovih uslova nije ispunjen, vraca {@code false}
	 */
	public boolean deleteFromDatabase(String tableId, Vector<String> headers, Vector<String> data) {
		int columns = headers.size();
		boolean result = false;
		
		if (headers == null || data == null)
			return result;
		
		if (columns != JsonModel.map.get(tableId).getColumnCount())
			return result;
		
		if (headers != null && data != null && (headers.size() != data.size() || data.size() == 0))
			return result;
			
		String query = "delete from " + tableId + " where ";
		for (int i = 0; i < columns; i++)
			if (data.get(i) != null)
				query += ((i == 0) ? "" : " and ") + headers.get(i) + " = ?";
		

		try {
			PreparedStatement stm = connection.prepareStatement(query);			
			for (int i = 0; i < columns; i++)
				stm.setObject(i+1, data.get(i));
			stm.executeUpdate();
			stm.close();
			result = true;
		} catch (SQLException e) {
			sendErrorMessage(e.getErrorCode());
		}

		return result;
	}
	
	/**
	 * Metoda iz baze podataka izvlaci informaciju o tome koji su primarni kljuceve prosledjene tabele. 
	 * 
	 * @param tableId ime tabele za koju trazimo kljuceve
	 * @return vraca se stuktura tipa {@code Vector<String>}, u kojoj su upisani nazivi kolona koji odgovaraju
	 * primarnom kljucu prosledjene tabele.
	 */
	public Vector<String> getPrimaryKeys(String tableId) {
		Vector<String> result = new Vector<String>();
		ResultSet rs;

		try {
			rs = metaData.getPrimaryKeys(null, null, tableId);
			while (rs.next())
				result.add(rs.getString("COLUMN_NAME"));
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Metoda iz baze podataka izvlaci informaciju o tome koje kljuceve jedna tabela (prosledjena) referencira
	 * iz druge tabele (takodje prosledjene). U parovima vraca informacije o imenima njihovih kolona. 
	 * 
	 * @param tableId ime tabele unutar koje se nalaze srtani kljucevi
	 * @param tableParentId ime tabele iz koje se referenciraju strani kljucevi
	 * @return vraca se stuktura tipa {@code Vector<Vector<String>>}.<br/>Struktura {@code Vector<String>} 
	 * sadrzi dve {@code String} vrednosti: <br/>
	 * 	- prva je naziv kolone koja odgovara stranom kljucu, u tabeli koja koristi strani kljuc <br/>
	 *  - druga je naziv kolone koja odgovara kljucu, u tabeli iz kojoj se referencira strani kljuc<br/>
	 *  Spoljnja struktura Vector obuhvata parove ime_stranog_kljuca - ime_kljuca (opisano malocas), i sadrzi ih
	 *  onoliko puta, koliko je kljuceva iz prosledjenih tabela referencirano 
	 */
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getForeignKeys(String tableId, String tableParentId) {
		Vector<Vector> result = new Vector<Vector>();
		Vector<String> singleResult;
		ResultSet rs;

		try {
			rs = metaData.getImportedKeys(null, null, tableId);
			while (rs.next()) {
				if (rs.getString("PKTABLE_NAME").equals(tableParentId)) { // 2 // 6 fktablename
					singleResult = new Vector<String>();
					singleResult.add(rs.getString("FKCOLUMN_NAME"));	// 7// ime kljuc kolone u child (donjoj) tabeli
					singleResult.add(rs.getString("PKCOLUMN_NAME"));	// 3// ime kljuc kolone u parent (gornjoj) tabeli
					result.add(singleResult);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Metoda iz baze podataka izvlaci informaciju o tome da li prosledjena kolona pripada primarnom kljucu 
	 * prosledjene tabele. 
	 * 
	 * @param tableId ime tabele za koju vrsimo proveru
	 * @param column ime kolone za koju proveravamo da li pripada primarnom kljucu
	 * @return vraca se vrednost {@code true} ukoliko pripada, u suprotnom vraca se {@code false}
	 */
	public boolean isPrimaryKey(String tableId, String column) {
		boolean result = false;
		ResultSet rs;

		try {
			rs = metaData.getPrimaryKeys(null, null, tableId);
			while (rs.next())
				if (rs.getString("COLUMN_NAME").equals(column)) {
					result = true;
					break;
				}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Metoda sluzi kako bi se ustanovilo kog su tipa parametri koji su upisani u odredjenu kolonu tabele.
	 * Povratna vrednost metode je enumeracija {@code ColumnType} (pogledati {@link ColumnType}).
	 * 
	 * @param tableId ime tabele za koju se vrsi provera
	 * @param column ime kolone iz prosledjene tabele, za koju se vrsi provera
	 * @return tip podataka koji su upisani u kolonu za koju se vrsi provera
	 */
	public ColumnType getColumnType(String tableId, String column) {
		ColumnType result;

		ResultSet rs;
		int typeCode = 0;
		
		try {
			rs = metaData.getColumns(null, null, tableId, column);
			while (rs.next())
				typeCode = Integer.parseInt(rs.getString("DATA_TYPE"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		switch (typeCode) {
		case 1:
			result = ColumnType.ALPHABET;
			break;
		case 12:
			result = ColumnType.ALPHABET;
			break;
		case 16:
			result = ColumnType.BOOLEAN;
			break;
		case 91:
			result = ColumnType.DATE;
			break;
		case 93:
			result = ColumnType.DATETIME;
			break;
		case 2:
			result = ColumnType.DECIMAL;
			break;
		case 3:
			result = ColumnType.DECIMAL;
			break;
		case 6:
			result = ColumnType.DECIMAL;
			break;
		default:
			result = ColumnType.NUMERIC;
		}		
		return result;
	}
	
	/**
	 * Metoda sluzi kako bi na osnovu prosledjenog parametra i trenutno aktivne lokalizacije izvrsila 
	 * prsiranje polja koja su tipa date-time.
	 * 
	 * @param dateString string vrednost promenljive tima date-time koja ce biti parsirana
	 * @return isparsirana vrednost prosledjenog parametra tipa {@code String}
	 */
	public String parseDateTimeFromDB(String dateString) {
		String parsed = "";
		if (dateString == null || dateString.equals(""))
			return parsed;
		
		DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		DateFormat targetFormat;
		Date date = new Date();
		if (Loc.getInstance().getLanguage().toString().equals("en_US"))
			targetFormat = new SimpleDateFormat("MM/dd/yyyy");
		else
			targetFormat = new SimpleDateFormat("dd.MM.yyyy");
		try {
			date = originalFormat.parse(dateString);			
			parsed = targetFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsed;
	}

	/**
	 * Metoda sluzi kako bi na osnovu prosledjenog parametra i trenutno aktivne lokalizacije izvrsilo
	 * parsiranje polja koja su tipa date.
	 * 
	 * @param dateString string vrednost promenljive tima date koja ce biti parsirana
	 * @return isparsirana vrednost prosledjenog parametra tipa {@code String}
	 */
	public String parseDateFromDB(String dateString) {
		String parsed = "";
		if (dateString == null || dateString.equals(""))
			return parsed;
		
		DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat targetFormat;
		Date date = new Date();
		
		if (Loc.getInstance().getLanguage().toString().equals("en_US"))
			targetFormat = new SimpleDateFormat("MM/dd/yyyy");
		else
			targetFormat = new SimpleDateFormat("dd.MM.yyyy");
		try {
			date = originalFormat.parse(dateString);			
			parsed = targetFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			
		}
		return parsed;
	}

	/**
	 * Metoda sluzi kako bi na osnovu prosledjenog datuma i trenutno aktivne lokalizacije izvrsilo 
	 * parsiranje prosledjenog datuma u format baze podataka i to vratilo kao string.
	 * 
	 * @param date string vrednost promenljive tima date koja ce biti parsirana
	 * @return isparsirana vrednost prosledjenog parametra tipa {@code String}
	 */
	public String parseDateToDB(Date date) {
		String parsed = "";
		if (date == null)
			return parsed;
		@SuppressWarnings("unused")
		DateFormat originalFormat;
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (Loc.getInstance().getLanguage().toString().equals("en_US"))
			originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		else
			originalFormat = new SimpleDateFormat("dd.MM.yyyy");
		parsed = targetFormat.format(date);
		return parsed;
	}
	
	/**
	 * Metoda sluzi kako bi na osnovu prosledjenog parametra i trenutno aktivne lokalizacije izvrsila 
	 * prsiranje polja koja su tipa decimalne vrednosti.
	 * 
	 * @param dateString string vrednost promenljive tima decimalne vrednosti koja ce biti parsirana
	 * @return isparsirana vrednost prosledjenog parametra
	 */
	public String parseDecimalFromDB(String decimalString) {
		if (!Loc.getInstance().getLanguage().toString().equals("en_US")) {
			decimalString = decimalString.replaceAll(",", "#!#");
			decimalString = decimalString.replace(".", ",");
			decimalString = decimalString.replace("#!#", ".");
		}
		return decimalString;
	}

	/**
	 * Metoda sluzi kako bi objedinila ispise gresaka koje korisnik pravi prilikom pokusaja da
	 * azurirabazu podataka.
	 * 
	 * @param errorCode je kod greske koja je detektovana, na osnovu njega se vrsi ispis greske
	 */
	private void sendErrorMessage(int errorCode) {
		LocaliseName text = new LocaliseName("");
		LocaliseName title = new LocaliseName("");
		
		switch (errorCode) {
			case 0:
				Loc.getInstance().register(text, "setName", "error.0.text");
				Loc.getInstance().register(title, "setName", "error.0.title");
				JOptionPane.showMessageDialog(null, text.getName(), title.getName(), JOptionPane.ERROR_MESSAGE);
				break;
			case 547:
				Loc.getInstance().register(text, "setName", "error.547.text");
				Loc.getInstance().register(title, "setName", "error.547.title");
				JOptionPane.showMessageDialog(null, text.getName(), title.getName(), JOptionPane.ERROR_MESSAGE);
				break;
			case 2627:
				Loc.getInstance().register(text, "setName", "error.2627.text");
				Loc.getInstance().register(title, "setName", "error.2627.title");
				JOptionPane.showMessageDialog(null, text.getName(), title.getName(), JOptionPane.ERROR_MESSAGE);
				break;
			default:
				Loc.getInstance().register(text, "setName", "error.default.text");
				Loc.getInstance().register(title, "setName", "error.default.title");
				JOptionPane.showMessageDialog(null, text.getName(), title.getName(), JOptionPane.ERROR_MESSAGE);
				break;
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
			instance.initialize();
		}
		return instance;
	}
}
