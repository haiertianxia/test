/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

public class TestCaseFailed extends Exception {

	private static final long serialVersionUID = 1L;
	String message;

	public TestCaseFailed() {
		message = null;
	}

	public TestCaseFailed(String message) {
		super(message);
		this.message = null;
		this.message = message;
	}
}