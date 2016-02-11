package gui.tree;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.tree.node.NodeTree;
/**
 * Klasa pomoću kojeg nameštamo izgled ikonica stabla.
 * 
 * @author Ivana
 */

@SuppressWarnings("serial")
public class TreeCellRenderer extends DefaultTreeCellRenderer {
	
	public TreeCellRenderer(){
		
	}
	
	
	/**
	 * Funkcija iz nadklase koja na osnovu nivoa hijerarhije cvora u stablu postavlja odredjenu ikonicu.
	 * 0 - koren
	 * 1 i 2 - paketi
	 * 3 - tabele
	 */
	
	public Component getTreeCellRendererComponent(JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus){
	     
		super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
          
         NodeTree cvor = (NodeTree) value;
        	  if(cvor.getType()==0){ //za korenski direktorijum
        		  ImageIcon im = new ImageIcon(new ImageIcon("images/database.png").getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        		       		  
            	  setIcon(im);
        	  } else if(cvor.getType() == 1 || cvor.getType() == 2){ //za pakete
        		  ImageIcon im = new ImageIcon(new ImageIcon("images/otvorenakutija.png").getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
	       		  
            	  setIcon(im);
        	  } else if(cvor.getType() == 3){
        		  ImageIcon im = new ImageIcon(new ImageIcon("images/tabela1.png").getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
	       		   setIcon(im);
        	  } else if(cvor.getType() == 5){	//za precice tabela, druge moguce slike su: tabelal, tabelal1 i tabelal2
        		  ImageIcon im = new ImageIcon(new ImageIcon("images/tabelal1.png").getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
	       		   setIcon(im);
       	  }
        	
      
          return this;
	}

}
