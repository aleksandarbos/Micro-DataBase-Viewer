/**
 * 
 */
package localisation.converters;

import javax.swing.KeyStroke;

import localisation.StringConverter;



/**
 * Konvertor {@code String} objekta u {@link javax.swing.KeyStroke} objekat.
 * String je u formatu {@code vk_mask}, gde je {@code vk} int vrednost tastera
 * na tastaturi, dok je {@code mask} int vrednost maske.
 * 
 * @author danijel
 * @version 1.0, 15.11.2015.
 * @since 1.0
 * @see javax.swing.KeyStroke#getKeyStroke(int, int)
 * 
 */
public class KeyStrokeConverter implements StringConverter<KeyStroke> {

	@Override
	public KeyStroke convert(String s) {
		KeyStroke ret = null;
		String[] ss = s.split("_");
		ret = KeyStroke.getKeyStroke(new Integer(ss[0]), new Integer(ss[1]));
		return ret;
	}

}
