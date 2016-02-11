/**
 * 
 */
package localisation;

import java.awt.Component;
import java.awt.Frame;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;

import controller.listeners.LocListener;
import localisation.converters.ImageIconConverter;
import localisation.converters.IntegerConverter;
import localisation.converters.KeyStrokeConverter;
import model.tree.node.NodeTree;


/**
 * {@code Singleton} klasa za podršku lokalizaciji teksta u različitim
 * kontekstima (labele, akcije, table modeli, tree modeli, ...). Koraci
 * korišćenja:
 * <ul>
 * <li>Registrovati sve objekte čije stanje zavisi od lokalizacije, tako što je
 * potrebno registrovati sve metode koje menjaju stanje datog objekta i
 * definisati im {@code ResourceBundle} ključ. Na primer:
 * <ul>
 * <li><b>Tekst za labelu, tekstualno polje, dugme, ... (setText metoda)</b>:
 * {@code Loc.getInstance().registerSetText(lbl, "key")}</li>
 * <li><b>Title prozora (setTitle)</b>:
 * {@code Loc.getInstance().registerSetTitle(frame, "key")}</li>
 * <li><b>Osobina akcije (putValue(NAME, value))</b>:
 * {@code Loc.getInstance().register(action, "putValue", 1, String.class, String.class, NAME, String.class "key")}
 * </li>
 * </ul>
 * </li>
 * <li>Prilikom promene jezika pozvati metodu
 * {@link #changeLanguage(Locale, String)} koja postavlja novu default
 * lokalizaciju</li>
 * <li>Zatim eksplicitno izvšiti lokalizaciju na primer preko metode
 * {@link #localizeAndReload()}. U ovom trenutku će se pozivati registrovane
 * metode za objekte preko Java refleksije.</li>
 * </ul>
 * <b>Napomena</b>: registrovani objekti se čuvaju po slaboj referenci tako da
 * nije potrebno voditi računa o deregistraciji kada oni više nisu potrebni u
 * memoriji. Objekti ove klase nisu {@code ThreadSafe}.
 * 
 * @author danijel
 * @version 1.0, 15.11.2015.
 * @since 1.0
 * 
 */
public class Loc {

	// Singleton.
	private static Loc instance = new Loc();

	private ResourceBundle bundle = null;

	// Registrovani objekti i metode koje će se pozivati preko Java refleksije
	// prilikom promene objekta
	private Map<WeakReference<?>, LocBean> map = new ConcurrentHashMap<WeakReference<?>, LocBean>();

	// Dodatna registracija metoda koja će se pozivati nakon promene
	// lokalizacije.
	private Map<WeakReference<?>, MethodBean> afterMap = new ConcurrentHashMap<WeakReference<?>, MethodBean>();

	// Predefinisani konvertori String objekta u odgovarajući objekat druge
	// klase.
	// Tako da se u ResourceBundle-u može čuvati i KeyStroke, Integer, ...
	private Map<Class<?>, StringConverter<?>> converters = null;

	protected EventListenerList listenerList = new EventListenerList();

	/**
	 * Inicijalizacija objekta. Podešavanje podrazumevanih konvertora teksta:
	 * (Integer, KeyStroke, ImageIcon)
	 */
	private Loc() {
		converters = new HashMap<Class<?>, StringConverter<?>>();
		converters.put(Integer.class, new IntegerConverter());
		converters.put(KeyStroke.class, new KeyStrokeConverter());
		converters.put(ImageIcon.class, new ImageIconConverter());
	}

	/**
	 * Promena default jezika. Poziva se interno metoda
	 * {@link java.util.Locale#setDefault(Locale)} i inicijalizuje se novi
	 * {@code ResourceBundle}.
	 * 
	 * @param nloc
	 *            Nova lokalizacija. Ako se prosledi isti objekat kao i
	 *            trenutni, i dalje će se izvršiti inicijalizacija
	 *            {@code ResourceBundle} objekta. Ne može biti {@code null}.
	 * @param resourceBundle
	 *            Lokacija {@code ResourceBundle} properties fajlova. Ne može
	 *            biti {@code null}
	 * @see java.util.Locale#setDefault(Locale)
	 * @see java.util.ResourceBundle#getBundle(String, Locale)
	 */
	public void changeLanguage(Locale nloc, String resourceBundle) {
		Locale.setDefault(nloc);
		initBundle(resourceBundle);
	}

	/**
	 * 
	 * @return {@link java.util.Locale#getDefault()}
	 */
	public Locale getLanguage() {
		return Locale.getDefault();
	}

	/**
	 * Izvršavanje poziva svih registrovanih metoda nad svim registrovanim
	 * objektima. Ključevi će se zameniti sa vrednostima iz
	 * {@code ResourceBundle} objekta. Nije zagarantovano da će se GUI osvežiti.
	 * Ukoliko ima potrebe za osvežavanjem pozvati varijantu:
	 * {@link #localize(Component)} ili {@link #localizeAndReload()}.
	 * 
	 * @throws LocException
	 *             Ako dođe do bilo kakvog izuzetka prilikom poziva registrovane
	 *             metode. Takav izuzetak će se umotati u ovaj izuzetak.
	 * @see #localize(Component)
	 * @see #localizeAndReload()
	 */
	public void localize() throws LocException {
		for (Map.Entry<WeakReference<?>, LocBean> entry : map.entrySet()) {
			Object o = entry.getKey().get();
			if (o == null) {
				map.remove(entry.getKey());
				continue;
			}
			LocBean l = entry.getValue();
			try {
				callMethod(l, o);
			} catch (Exception e) {
				throw new LocException(e);
			}
		}

		fireLocalizationChanged();
	}

	/**
	 * Izvršavanje poziva svih registrovanih metoda nad svim registrovanim
	 * objektima.
	 * 
	 * @param root
	 *            Komponenta koja će se osvežiti nakon lokalizacije. Podesiće se
	 *            njen {@code locale}, izvršiće se revalidacija komponente i
	 *            zatim ponovno iscrtavanje. Dozvoljeno je proslediti
	 *            {@code null}
	 * @throws LocException
	 *             Ako dođe do bilo kakvog izuzetka prilikom poziva registrovane
	 *             metode. Takav izuzetak će se umotati u ovaj izuzetak.
	 * @see #localize()
	 * @see #localizeAndReload()
	 */
	public void localize(Component root) throws LocException {
		localize();
		if (root != null) {
			root.setLocale(Locale.getDefault());
			root.validate();
			SwingUtilities.updateComponentTreeUI(root);

		}
	}

	/**
	 * Izvršavanje poziva svih registrovanih metoda nad svim registrovanim
	 * objektima. Nakon toga će se osvežiti svi aktivni prozori. Svakom prozoru
	 * će se podesiti {@code locale}, izvršiće se revalidacija i zatim ponovno
	 * iscrtavanje.
	 * 
	 * @throws LocException
	 *             Ako dođe do bilo kakvog izuzetka prilikom poziva registrovane
	 *             metode. Takav izuzetak će se umotati u ovaj izuzetak.
	 * @see #localize()
	 * @see #localize(Component)
	 */
	public void localizeAndReload() throws LocException {
		localize();
		Frame[] frames = Frame.getFrames();
		if (frames != null) {
			for (Frame f : frames) {
				f.setLocale(Locale.getDefault());
				f.validate();
				
				SwingUtilities.updateComponentTreeUI(f);
			}
		}
	}

	/**
	 * Izvršavanje poziva dodatnih registrovanih metoda nad objektima. Pozivati
	 * nakon metoda: {@link #localize()}, {@link #localize(Component)} i
	 * {@link #localizeAndReload()}.
	 * 
	 * @throws LocException
	 *             Ako dođe do bilo kakvog izuzetka prilikom poziva registrovane
	 *             metode. Takav izuzetak će se umotati u ovaj izuzetak.
	 */
	public void after() throws LocException {
		for (Map.Entry<WeakReference<?>, MethodBean> entry : afterMap.entrySet()) {
			Object o = entry.getKey().get();
			if (o == null) {
				afterMap.remove(entry.getKey());
				continue;
			}
			try {
				MethodBean l = entry.getValue();
				o.getClass().getMethod(l.getMethod(), l.getParamClasses()).invoke(o, l.getParams());
			} catch (Exception e) {
				throw new LocException(e);
			}
		}
	}

	// Pomocna metoda za poziv metode preko java refleksije.
	private void callMethod(LocBean l, Object o) {
		try {
			Object[] copyParams = Arrays.copyOf(l.getParams(), l.getParams().length);
			String text = bundle.getString(l.getParams()[l.getKeyIndex()].toString());
			Object oText = convertString(text, l.getKeyClass());

			copyParams[l.getKeyIndex()] = oText;
			o.getClass().getMethod(l.getMethod(), l.getParamClasses()).invoke(o, copyParams);
		} catch (Exception e) {
			throw new IllegalArgumentException("Method invocation failed!", e);
		}
	}

	/**
	 * Registrovanje zadate metode nad zadatim objektom. Prilikom lokalizacije
	 * će se pozvati ova metoda i kao parametar će se proslediti vrednost
	 * ključa. Objekat se registruje kao slaba referenca, tako da će se ukloniti
	 * prilikom sledeće lokalizacije, ako ga više niko ne referencira. Metoda
	 * mora primati parametar tipa {@code String}. Odmah nakon registracije,
	 * metoda se automatski poziva.
	 * 
	 * @param o
	 *            Objekat nad kojim će se pozvati metoda. Ne sme biti
	 *            {@code null}.
	 * @param method
	 *            Naziv metode koja će se pozvati Ne sme biti {@code null}.
	 * @param key
	 *            Ključ vrednosti iz objekta {@code ResourceBundle}. Ne sme biti
	 *            {@code null}.
	 * @throws IllegalArgumentException
	 *             Ako definicija metode nije ispravna prilikom njenog poziva.
	 */
	public void register(Object o, String method, String key) {
		if (o == null) {
			throw new NullPointerException();
		}
		LocBean lc = new LocBean(method, key);
		map.put(new WeakReference<Object>(o), lc);
		if (bundle != null) {
			callMethod(lc, o);
		}
	}

	/**
	 * Registracija zadate metode nad zadatim objektom. Odmah nakon
	 * registracije, metoda se automatski poziva.
	 * 
	 * @param o
	 *            Objekat nad kojim će se pozvati metoda. Ne sme biti
	 *            {@code null}.
	 * @param method
	 *            Naziv metode koja će se pozvati nad datim objektom. Ne sme
	 *            biti {@code null}.
	 * @param keyIndex
	 *            Indeks u listu parametara metode gde će se nalaziti ključ koji
	 *            će se zameniti sa vrednošću. Počinje od 0.
	 * @param keyClass
	 *            Definisanje klase vrednosti ključa. Vrednost koja je
	 *            {@code String} će se pokušati konvertovati u objekat klase
	 *            {@code keyClass}.
	 * @param params
	 *            Ide u formatu {@code klasa, vrednost, klasa, vrednost, ...} i
	 *            to onoliko puta (može biti i nula) koliki je broj parametara
	 *            metode {@code method}. Na taj način će se pokušati pronaći
	 *            odgovarajuća metoda sa datim tipom parametara.
	 * @throws IllegalArgumentException
	 *             Ako definicija metode nije ispravna prilikom njenog poziva.
	 * @see #register(Object, String, String)
	 */
	public void register(Object o, String method, int keyIndex, Class<?> keyClass, Object... params) {
		if (o == null) {
			throw new NullPointerException();
		}
		int paramLength = params.length / 2;
		Class<?>[] paramClasses = new Class<?>[paramLength];
		Object[] oParams = new Object[paramLength];
		for (int i = 0; i < paramLength; i++) {
			paramClasses[i] = (Class<?>) params[2 * i];
			oParams[i] = params[2 * i + 1];
		}

		LocBean lc = new LocBean(method, paramClasses, oParams, keyIndex, keyClass);
		map.put(new WeakReference<Object>(o), lc);

		if (bundle != null) {
			callMethod(lc, o);
		}
	}

	/**
	 * Registtracija metode koja će se pozvati nakon lokalizacije preko metode
	 * {@link #after()}.
	 * 
	 * @param o
	 *            Objekat nad kojim će se pozvati data metoda.
	 * @param method
	 *            Naziv metode koja će se pozvati.
	 * @param params
	 *            Ide u formatu {@code klasa, vrednost, klasa, vrednost, ...} i
	 *            to onoliko puta (može biti i nula) koliki je broj parametara
	 *            metode {@code method}. Na taj način će se pokušati pronaći
	 *            odgovarajuća metoda sa datim tipom parametara.
	 * 
	 */
	public void registerAfter(Object o, String method, Object... params) {
		if (o == null) {
			throw new NullPointerException();
		}
		int paramLength = params.length / 2;
		Class<?>[] paramClasses = new Class<?>[paramLength];
		Object[] oParams = new Object[paramLength];
		for (int i = 0; i < paramLength; i++) {
			paramClasses[i] = (Class<?>) params[2 * i];
			oParams[i] = params[2 * i + 1];
		}
		afterMap.put(new WeakReference<Object>(o), new MethodBean(method, paramClasses, oParams));
	}

	/**
	 * Registracija metode {@code setTitle} koja prima parametar {@code String}.
	 * 
	 * @param o
	 *            Objekat nad kojim će se pozvati metoda. Ne sme biti
	 *            {@code null}.
	 * @param key
	 *            Ključ koji će se zameniti sa vrednošću. Ne sme biti
	 *            {@code null}.
	 */
	public void registerSetTitle(Object o, String key) {
		register(o, "setTitle", key);
	}

	/**
	 * Registracija metode {@code setText} koja prima parametar {@code String}.
	 * Korisni kod metoda {@link javax.swing.JButton#setText(String)},
	 * {@link javax.swing.JLabel#setText(String)},
	 * {@link javax.swing.JTextField#setText(String)}, ...
	 * 
	 * @param o
	 *            Objekat nad kojim će se pozvati metoda. Ne sme biti
	 *            {@code null}.
	 * @param key
	 *            Ključ koji će se zameniti sa vrednošću. Ne sme biti
	 *            {@code null}.
	 */
	public void registerSetText(Object o, String key) {
		register(o, "setText", key);
	}

	/**
	 * Registracija metode {@code setToolTipText} koja prima parametar
	 * {@code String}.
	 * 
	 * @param o
	 *            Objekat nad kojim će se pozvati metoda. Ne sme biti
	 *            {@code null}.
	 * @param key
	 *            Ključ koji će se zameniti sa vrednošću. Ne sme biti
	 *            {@code null}.
	 */
	public void registerSetTooltipText(Object o, String key) {
		register(o, "setToolTipText", key);
	}

	/**
	 * Dodavanje novog tipa konvertera {@code String} objekta u odgovarajući tip
	 * objekta.
	 * 
	 * @param c
	 *            Klasa vrednosti u koju će se {@code String} objekat
	 *            konvertovati.
	 * @param sc
	 *            Konverter koji će raditi taj posao.
	 * @throws NullPointerException
	 *             Ako je parametar {@code c} ili {@code sc} {@code null}.
	 */
	public void putConverter(Class<?> c, StringConverter<?> sc) {
		if (c == null || sc == null) {
			throw new NullPointerException();
		}
		converters.put(c, sc);
	}

	/**
	 * Deregistracija konvertora. Izbacuje se iz interne mape ako takav postoji.
	 * 
	 * @param c
	 *            Konvertor koji se deregistruje.
	 */
	public void removeConverter(Class<?> c) {
		converters.remove(c);
	}

	// Konvertovanje vrednosti iz ResourceBundle-a u odgovarajući objekat klase.
	private Object convertString(String text, Class<?> c) {
		if (c.equals(String.class)) {
			return text;
		} else if (c.equals(Object.class)) {
			return text;
		} else {
			StringConverter<?> sc = converters.get(c);
			if (sc == null) {
				throw new IllegalArgumentException("String conversion not supported to class: " + c);
			}
			return sc.convert(text);
		}

	}

	/**
	 * Registracija osluškivača na događaj neposredno nakon lokalizacije.
	 * 
	 * @param ll
	 *            Osluškivač na događaj. Ne sme biti {@code null}.
	 */
	public void addLocListener(LocListener ll) {
		if (ll == null) {
			throw new NullPointerException();
		}
		listenerList.add(LocListener.class, ll);
	}

	/**
	 * Izbacivanje zadatog osluškivača iz liste ako takav postoji u istoj.
	 * 
	 * @param ll
	 *            Osluškivač koji se izbacuje.
	 */
	public void removeLocListener(LocListener ll) {
		listenerList.remove(LocListener.class, ll);
	}

	/**
	 * Ispaljivanje događaja lokalizacije. Ispaljuje se neposredno nakon
	 * završetka metode {@link #localize()}.
	 */
	protected void fireLocalizationChanged() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		LocEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == LocListener.class) {
				// Lazily create the event:
				if (e == null) {
					e = new LocEvent(this);
				}
				((LocListener) listeners[i + 1]).localizationChanged(e);
			}
		}
	}

	/**
	 * Ponovna inicijalizacija {@code ResourceBundle} objekta na osnovu
	 * trenutnog lokala.
	 */
	public void initBundle(String resourceBundle) {
		bundle = ResourceBundle.getBundle(resourceBundle, Locale.getDefault());
	}

	/**
	 * 
	 * @return Jedan jedini objekat ove klase ({@code Singleton}).
	 */
	public static Loc getInstance() {
		return instance;
	}
	
	
	

}
