package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import controller.database.DataBase;
import controller.listeners.LocListener;
import gui.frame.LoginFrame;
import gui.frame.MainFrame;
import localisation.Loc;
import localisation.LocEvent;
import localisation.LocException;

public class Main {
	
	public static LoginFrame login = LoginFrame.getInstance();
	
	public static void exitApplication() {
		if (DataBase.getInstance().getConnection() != null) {
			try {
				DataBase.getInstance().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
	
	// 1 onemoguciti izmenu polja koja pripadaju kljucu prilikom izmene podataka
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		
		//MainFrame.getInstance().setVisible(true);
	
		login.setVisible(true);
		
	}
}
