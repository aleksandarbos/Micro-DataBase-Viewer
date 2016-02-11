package localisation;

/**
 * Pomoćna klasa za definisanje metode koja će se pozivati prilikom promene
 * lokalizacije.
 * 
 * @author danijel
 * @version 1.0, 15.11.2015.
 * @since 1.0
 * 
 */

public class MethodBean {
	
	private String method;
	private Class<?>[] paramClasses;
	private Object[] params;
	
	/**
	 * Konstrukcija opisa metode.
	 * 
	 * @param method
	 *            Naziv metode.
	 * @param paramClasses
	 *            Niz tipova parametara koji jednoznačno identifikuje metodu.
	 * @param params
	 *            Niz vrednosti parametara koji će se proslediti prilikom poziva
	 *            metode.
	 * @throws NullPointerException
	 *             Ako vrednost {@code method}, {@code paramClasses} ili
	 *             {@code params} {@code null}.
	 */
	public MethodBean(String method, Class<?>[] paramClasses, Object[] params) {
		super();
		setMethod(method);
		setParamClasses(paramClasses);
		setParams(params);
	}

	public String getMethod() {
		return method;
	}

	/**
	 * Postavljanje novog naziv metode.
	 * 
	 * @param method
	 *            Naziv metode.
	 * @throws NullPointerException
	 */
	public void setMethod(String method) {
		if (method == null) {
			throw new NullPointerException();
		}
		this.method = method;
	}

	public Class<?>[] getParamClasses() {
		return paramClasses;
	}

	/**
	 * Postavljanje niz tipova parametara koji prima data metoda.
	 * 
	 * @param paramClasses
	 *            Niz tipova.
	 * @throws NullPointerException
	 *             Ako je vrednost parametra {@code paramClasses null}.
	 */
	public void setParamClasses(Class<?>[] paramClasses) {
		if (paramClasses == null) {
			throw new NullPointerException();
		}
		this.paramClasses = paramClasses;
	}

	public Object[] getParams() {
		return params;
	}

	/**
	 * Postavljanje vrednosti parametara metode.
	 * 
	 * @param params
	 *            Vrednosti parametara metode.
	 * @throws NullPointerException
	 *             Ako je vrednost {@code params null}.
	 */
	public void setParams(Object[] params) {
		if (params == null) {
			throw new NullPointerException();
		}
		this.params = params;
	}

}
