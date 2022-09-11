package com.bolsaideas.springboot.app.util.paginator;

public class PageItem {

	private int num;
	
	private boolean actual;

	public PageItem(int num, boolean actual) {
		super();
		this.num = num;
		this.actual = actual;
	}

	public int getNum() {
		return num;
	}

	public boolean isActual() {
		return actual;
	}

	
}
