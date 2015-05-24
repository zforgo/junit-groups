package hu.zforgo.resteasy;

import java.io.Serializable;

public class SampleBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private int foo;
	private String bar;
	private String name;

	public SampleBody() {
	}

	public int getFoo() {
		return foo;
	}

	public void setFoo(int foo) {
		this.foo = foo;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
