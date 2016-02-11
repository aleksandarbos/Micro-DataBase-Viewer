package controller.actions.login;

import java.awt.event.ActionEvent;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.actions.AbstractActionPrototype;
import controller.database.DataBase;
import controller.listeners.LocListener;
import gui.frame.MainFrame;
import localisation.Loc;
import localisation.LocEvent;
import localisation.LocException;
import main.Main;
/**
 * Klasa koja upravlja dogadjajem tj. obradjuje akciju za pokusaj prijavljivanja na sistem. 
 * U slucaju uspesnog logovanja, otvara aplikacioni prozor, u suprotnom otvara dialog sa obavestenjem
 * i resetuje polja za unos podataka za prijavu.
 * 
 * @author Aleksandar
 */
@SuppressWarnings("serial")
public class ActionLoginLogin extends AbstractActionPrototype {

	public ActionLoginLogin(String title, String icon, String tooltip, String accelertor, int mnemonic) {
		super(title, icon, tooltip, accelertor, mnemonic);
	}
	
	public ActionLoginLogin(String title, String icon, String tooltip) {
		super(title,icon,tooltip);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = Main.login.getUsername().getText();
		String password = new String(Main.login.getPassword().getPassword());
		
		if (username.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Unsuccessful login. No username/password parameters.",
				    "Login error",
				    JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//podesavanje defaultnog jezika lokalizacije
		if (DataBase.getInstance().logIn(username, password)) {
			Main.login.setVisible(false);
		
			
			Loc.getInstance().changeLanguage(new Locale("en","US"), "localisation.bundle.bundle");

			Loc.getInstance().addLocListener(new LocListener() {

				@Override
				public void localizationChanged(LocEvent event) {
					System.out.println("Promenjen jezik na: " + Loc.getInstance().getLanguage());

				}
			});

			try {
				Loc.getInstance().localizeAndReload();
			} catch (LocException e1) {
				e1.printStackTrace();
			}
			
			MainFrame mainFrame = MainFrame.getInstance();
			mainFrame.setVisible(true);
			
			
		} else {
			errorHandler();
		}
		
	}

	private void errorHandler() {
		JOptionPane.showMessageDialog(new JFrame(),
			    "Unsuccessful login. Invalid username/password combination.",
			    "Login error",
			    JOptionPane.WARNING_MESSAGE);
		//Main.login.getUsername().setText("");
		Main.login.getUsername().requestFocus();
		Main.login.getPassword().setText("");
	}
}
