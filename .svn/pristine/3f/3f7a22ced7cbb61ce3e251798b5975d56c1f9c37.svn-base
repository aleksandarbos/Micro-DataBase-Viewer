package gui.frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.actions.login.ActionCancelLogin;
import controller.actions.login.ActionLoginLogin;
import gui.imagebackgrounds.ImageBackgroundPanel;
import localisation.Loc;

public class LoginFrame extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5984402396385157841L;
	private static LoginFrame instance = null;
	private Dimension screenDimensions;
	private JPanel panel;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JButton btnLogin;
	
	
	private LoginFrame() {
		super("GradientTranslucentWindow");
	}
	
	public static LoginFrame getInstance() {
		if(instance == null)
			instance = new LoginFrame();
		instance.initialize();
		return instance;
	}

	private void initialize() {
		GridBagConstraints c = new GridBagConstraints();
		
		setFrame();
		setIconImage(new ImageIcon("images/database.png").getImage());
		
		JLabel picLabelLogo = new JLabel(new ImageIcon("images/logoBolji.png"));
		JLabel labelHeading = new JLabel("Please login to continue...");
		Loc.getInstance().registerSetText(labelHeading, "login.heading");
		JLabel labelUsername = new JLabel("Username:");
		Loc.getInstance().registerSetText(labelUsername, "login.username");
		JLabel labelPassword = new JLabel("Password:");
		Loc.getInstance().registerSetText(labelPassword, "login.password");
		JButton btnCancel = new JButton(new ActionCancelLogin("Cancel", "images/cancelBtn.png", "Cancel application login and exit."));

		btnLogin = new JButton(new ActionLoginLogin("Login","images/loginLock.png","Button for logging on system."));

		this.tfUsername = new JTextField(20);
		
		Loc.getInstance().registerSetTooltipText(this.tfUsername, "login.username.tooltip");
		
		this.pfPassword = new JPasswordField(20);
		this.pfPassword.addKeyListener(this);
		Loc.getInstance().registerSetTooltipText(this.pfPassword, "login.password.tooltip");

		
		labelHeading.setFont(new Font("Arial", Font.ITALIC, 15));
		labelUsername.setFont(new Font("Arial", Font.PLAIN, 15));
		labelPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		
		c.fill = GridBagConstraints.HORIZONTAL;		// na nivou mreze

		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 15;
		panel.add(picLabelLogo, c);
		
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipady = 40;
		panel.add(labelHeading, c);

		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1; 
		panel.add(labelUsername, c);
		
		c.ipady = 1;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		panel.add(tfUsername, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(labelPassword, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		panel.add(pfPassword, c);

		c.gridwidth = 1;
	    c.weighty = 1.0;   
	    c.gridx = 0;       
	    c.gridy = 4;       
		panel.add(btnLogin, c);
		
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		panel.add(Box.createHorizontalStrut(10), c);
		
		c.gridwidth = 1;
		c.ipadx = 5;
		c.gridx = 2;
		c.gridy = 4;
		panel.add(btnCancel, c);
		
		setContentPane(panel);
	}

	private void setFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		screenDimensions = kit.getScreenSize();
		
		int frameWidth = screenDimensions.width/3;
		int frameHeight = screenDimensions.height/2;
		
		panel = new ImageBackgroundPanel("images/login.jpg");
		panel.setLayout(new GridBagLayout());
			
		panel.setSize(frameWidth, frameHeight);
		
		setTitle("MicroDBase™ Login");
		setSize(frameWidth, frameHeight+100);
		setResizable(false);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTextField getUsername() {
		return tfUsername;
	}

	public JPasswordField getPassword() {
		return pfPassword;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			btnLogin.doClick();
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}
	
	
	
}
