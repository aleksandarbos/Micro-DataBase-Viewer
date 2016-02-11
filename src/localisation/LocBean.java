/**
 * 
 */
package localisation;


/**
 * Pomoćna klasa za definisanje metode koja će se pozivati. To znači da objekti
 * ove klase čuvaju naziv metode, parametre, tipove parametra, kao i dodatnu
 * informaciju, lokaciju ključa u okviru niza parametra i klasa ključa.
 * 
 * @author danijel
 * @version 1.0, 15.11.2015.
 * @since 1.0
 * 
 */
public class LocBean extends MethodBean {
	private int keyIndex;
	private Class<?> keyClass;

	/**
	 * Inicijalizacija metode koja prima parametar {@code String}.
	 * 
	 * @param method
	 *            Naziv metode
	 * @param key
	 *            Ključ
	 * @throws NullPointerException
	 *             Ako je vrednost {@code method} ili {@code key} {@code null}.
	 */
	public LocBean(String method, String key) {
		this(method, String.class, key);
	}

	/**
	 * Inicijalizacija metode sa zadatim tipom parametra i ključem
	 * 
	 * @param method
	 *            Naziv metode.
	 * @param paramClass
	 *            Tip parametra.
	 * @param key
	 *            Ključ
	 * @throws NullPointerException
	 *             Ako je vrednost {@code method}, {@code key} ili
	 *             {@code paramClass} {@code null}.
	 */
	public LocBean(String method, Class<?> paramClass, String key) {
		this(method, new Class<?>[] { paramClass }, new Object[] { key }, 0);
	}

	/**
	 * Iniijalizacija metode sa zadatim tipovima parametra i vrednostima.
	 * 
	 * @param method
	 *            Naziv metode.
	 * @param paramClasses
	 *            Tipovi parametra.
	 * @param params
	 *            Vrednosti parametra od kojih je jedan ključ.
	 * @param keyIndex
	 *            Indeks ključa u okviru liste parametra.
	 * @throws NullPointerException
	 *             Ako je vrednost {@code method}, {@code paramClass} ili
	 *             {@code params} {@code null}.
	 */
	public LocBean(String method, Class<?>[] paramClasses, Object[] params,
			int keyIndex) {
		this(method, paramClasses, params, keyIndex, paramClasses[keyIndex]);
	}

	/**
	 * Inicijalizacija metode sa zadatim tipovima parametra i vrednostia. Takođe
	 * se definiše koji tip je vrednost ključa.
	 * 
	 * @param method
	 *            Naziv metode.
	 * @param paramClasses
	 *            Tiopovi parametra.
	 * @param params
	 *            Vrednosti parametra od kojih je jedan ključ.
	 * @param keyIndex
	 *            Indeks ključa u okviru liste parametra.
	 * @param keyClass
	 *            Tip vrednosti ključa.
	 * @throws NullPointerException
	 *             Ako je vrednost {@code method}, {@code paramClass},
	 *             {@code params} ili {@code keyClass} {@code null}.
	 */
	public LocBean(String method, Class<?>[] paramClasses, Object[] params,
			int keyIndex, Class<?> keyClass) {
		super(method, paramClasses, params);
		setKeyIndex(keyIndex);
		setKeyClass(keyClass);
	}

	public int getKeyIndex() {
		return keyIndex;
	}

	/**
	 * Postavljanje indeksa ključa u okviru liste parametra metode koja je
	 * reprezentovana preko objekta ove klase.
	 * 
	 * @param keyIndex
	 *            Indeks ključa. Vrednost počinje od 0.
	 */
	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}

	public Class<?> getKeyClass() {
		return keyClass;
	}

	/**
	 * Postavljanje novog tipa vrednosti ključa.
	 * 
	 * @param keyClass
	 *            Tip vrednosti ključa.
	 * @throws NullPointerException
	 *             Ako je vrednost {@code keyClass null}.
	 */
	public void setKeyClass(Class<?> keyClass) {
		if (keyClass == null) {
			throw new NullPointerException();
		}
		this.keyClass = keyClass;
	}

}
